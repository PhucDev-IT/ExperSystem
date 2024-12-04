package vn.mobile.expersystem.fragments

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ExpandableListView
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.mobile.expersystem.R
import vn.mobile.expersystem.adapters.ExpandableListAdapter
import vn.mobile.expersystem.common.PopupDialog
import vn.mobile.expersystem.database.AppDatabase
import vn.mobile.expersystem.database.AppDatabase.Companion.APPDATABASE
import vn.mobile.expersystem.database.core.DataSample
import vn.mobile.expersystem.database.core.DataSample.groupEvents
import vn.mobile.expersystem.databinding.FragmentTapLuatBinding
import vn.mobile.expersystem.databinding.PopupAddNhomBinding
import vn.mobile.expersystem.databinding.PopupTapLuatManagerBinding
import vn.mobile.expersystem.models.TapLuat
import vn.mobile.expersystem.models.TapSuKien
import java.util.UUID
import kotlin.random.Random

class TapLuatFragment : Fragment() {
    private lateinit var _binding: FragmentTapLuatBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTapLuatBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        PopupDialog.showLoading(requireContext())
        handleEvent()
       CoroutineScope(Dispatchers.IO).launch {
           val tapLuat = AppDatabase.APPDATABASE.tapLuatDao().getAll()
           withContext(Dispatchers.Main){
               displayData(tapLuat)
               Handler(requireContext().mainLooper).postDelayed({
                   PopupDialog.closeDialog()
               },500)
           }
       }
    }

    private fun displayData(tapLuat: List<TapLuat>) {
        for (item in tapLuat) {
            // Thêm TableRow vào TableLayout
            binding.tableLayout.addView(initTableRow(item))
        }
    }

    private fun initTableRow(item:TapLuat): TableRow {
        val tableRow = layoutInflater.inflate(R.layout.item_tap_luat, null) as TableRow
        val tvMaLuat: TextView = tableRow.findViewById(R.id.tv_ma_luat)
        val tvMaSuKien: TextView = tableRow.findViewById(R.id.tv_ma_su_kien)
        val tvKetLuan: TextView = tableRow.findViewById(R.id.tv_ket_luan)

        // Điền dữ liệu vào các TextView
        tvMaLuat.text = item.maLuat
        tvMaSuKien.text = item.suKienId
        tvKetLuan.text = item.ketLuan

        tableRow.setOnClickListener {
            showPopup(item)

        }
        return tableRow
    }

    private fun handleEvent() {
        binding.btnAddNew.setOnClickListener {
            showPopup(null)
        }
    }

    private fun showPopup(tapLuat: TapLuat?) {
        val dialog = Dialog(requireContext(), R.style.Custom_Dialog)
        val popupBinding = PopupTapLuatManagerBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(popupBinding.root)

        if(tapLuat==null){
            popupBinding.btnEdit.isEnabled = false
            popupBinding.btnDelete.isEnabled = false
            popupBinding.btnAdd.isEnabled = true
        }else{
            popupBinding.btnAdd.isEnabled = false
            popupBinding.btnEdit.isEnabled = true
            popupBinding.btnDelete.isEnabled = true
        }

        val groupedEvents = groupEvents.groupBy { it.nhomId }

        val childMap:MutableMap<String,List<TapSuKien>> = mutableMapOf()

        val split = tapLuat?.suKienId?.split(" ^ ")?: emptyList()
        for(group in DataSample.groups){
            val id = group.id
            val des = group.description
            childMap[des] = groupedEvents[id]?: emptyList()
        }
        val selectedItem = split.flatMap { suKienId ->
            groupEvents.filter { it.suKienId == suKienId }.map { it.description }
        }

        var selectedItemFinal = ""
        val adapter = ExpandableListAdapter(requireContext(), childMap.keys.toList(), childMap,selectedItem){ selected->
            selectedItemFinal = ""
            for(item in selected){
                selectedItemFinal += "${item.suKienId} ^ "
            }

            popupBinding.tvMaTapLuat.text = if(selectedItemFinal.isNotEmpty()) selectedItemFinal.dropLast(3) else "Mã sự kiện ?"
        }
        popupBinding.expandableListView.setAdapter(adapter)
        popupBinding.expandableListView.setGroupIndicator(null)

        popupBinding.edtKetLuan.setText(tapLuat?.ketLuan)

        popupBinding.btnDelete.setOnClickListener {
            if (tapLuat != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    AppDatabase.APPDATABASE.tapLuatDao().delete(tapLuat)
                    withContext(Dispatchers.Main){
                        val existingRow = findTableRowBySuKienId(tapLuat.suKienId)
                        if (existingRow != null) {
                            binding.tableLayout.removeView(existingRow)
                            Toast.makeText(requireContext(),"Xóa thành công",Toast.LENGTH_SHORT).show()
                        }
                        dialog.dismiss()
                    }
                }
            }
        }

        popupBinding.btnAdd.setOnClickListener {
            addItem(selectedItemFinal,popupBinding){
                dialog.dismiss()
            }
        }
        popupBinding.btnEdit.setOnClickListener {
            update(tapLuat,selectedItemFinal,popupBinding)
            dialog.dismiss()
        }

        dialog.show()

    }

    private fun addItem(groupEvent:String, popup:PopupTapLuatManagerBinding, callback:()->Unit){
        if(popup.edtKetLuan.text.toString().trim().isEmpty()){
            popup.edtKetLuan.error = "Trường này là bắt buộc"
        }else if(groupEvent.isEmpty()){
            Toast.makeText(requireContext(),"Cần chọn sự kiện",Toast.LENGTH_SHORT).show()
        }else{
            PopupDialog.showLoading(requireContext())
            CoroutineScope(Dispatchers.IO).launch {
                val latestId = APPDATABASE.tapLuatDao().getLatestId()
                val tapLuat = TapLuat(maLuat = "L${latestId+10}",suKienId = groupEvent,ketLuan = popup.edtKetLuan.text.toString())
                APPDATABASE.tapLuatDao().insertTapLuat(tapLuat)
                withContext(Dispatchers.Main){
                    binding.tableLayout.addView(initTableRow(tapLuat))
                    PopupDialog.closeDialog()
                    Toast.makeText(requireContext(),"Thêm thành công",Toast.LENGTH_SHORT).show()
                    callback()
                }
            }
        }
    }


    private fun update(tapLuat: TapLuat?,groupEvent:String,popup: PopupTapLuatManagerBinding){
        if(tapLuat==null) return
        val oldSuKienId = tapLuat.suKienId
        if(popup.edtKetLuan.text.toString().trim().isEmpty()){
            popup.edtKetLuan.error = "Trường này là bắt buộc"
        }else if(groupEvent.isEmpty()){
            Toast.makeText(requireContext(),"Cần chọn sự kiện",Toast.LENGTH_SHORT).show()
        }else{
            tapLuat.suKienId = groupEvent
            tapLuat.ketLuan = popup.edtKetLuan.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.APPDATABASE.tapLuatDao().update(tapLuat)
                withContext(Dispatchers.Main){
                    val existed = findTableRowBySuKienId(oldSuKienId)
                    if(existed!=null){
                        updateTableRow(existed,tapLuat)
                        Toast.makeText(requireContext(),"Cập nhật thành công",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    // Helper function to find a TableRow by suKienId
    private fun findTableRowBySuKienId(suKienId: String): TableRow? {
        for (i in 0 until binding.tableLayout.childCount) {
            val row = binding.tableLayout.getChildAt(i) as? TableRow
            val suKienIdTextView = row?.findViewById<TextView>(R.id.tv_ma_su_kien)
            if (suKienIdTextView?.text == suKienId) {
                return row
            }
        }
        return null
    }

    // Helper function to update an existing TableRow with new data
    private fun updateTableRow(row: TableRow, tapLuat: TapLuat) {
        val suKienIdTextView: TextView = row.findViewById(R.id.tv_ma_su_kien)
        val maLuat: TextView = row.findViewById(R.id.tv_ma_luat)
        val tvKetLuan: TextView = row.findViewById(R.id.tv_ket_luan)


        suKienIdTextView.text = tapLuat.suKienId
        maLuat.text = tapLuat.maLuat
        tvKetLuan.text = tapLuat.ketLuan
    }
}