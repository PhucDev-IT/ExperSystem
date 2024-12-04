package vn.mobile.expersystem.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.mobile.expersystem.R
import vn.mobile.expersystem.common.PopupDialog
import vn.mobile.expersystem.database.AppDatabase
import vn.mobile.expersystem.database.core.DataSample
import vn.mobile.expersystem.databinding.FragmentSuKienBinding
import vn.mobile.expersystem.models.Nhom
import vn.mobile.expersystem.models.TapSuKien

class SuKienFragment : Fragment() {
    private lateinit var _binding: FragmentSuKienBinding
    private val binding get() = _binding
    private var groupEvents = listOf<TapSuKien>()
    private var groups = listOf<Nhom>()
    private var selectedNhom: Nhom? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuKienBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }


    private fun initView() {
        binding.btnEdit.visibility = View.GONE
        binding.btnDelete.visibility = View.GONE
        binding.btnAddNew.visibility = View.VISIBLE

        setListener()
        getData()
    }


    private fun setListener(){
        binding.btnClear.setOnClickListener {
            resetData()
        }

        binding.btnAddNew.setOnClickListener {
            if(binding.edtMaSuKien.text.isNullOrEmpty()){
                binding.tlMaSuKien.error = "Vui lòng nhập mã sự kiện"
            }else if(binding.edtMoTa.text.isNullOrEmpty()){
                binding.tlMoTa.error = "Vui lòng nhập mô tả"
            }else{
                addNewSuKien()
            }
        }

        binding.btnEdit.setOnClickListener {
            if(binding.edtMaSuKien.text.isNullOrEmpty()){
                binding.tlMaSuKien.error = "Vui lòng nhập mã sự kiện"
            }else if(binding.edtMoTa.text.isNullOrEmpty()){
                binding.tlMoTa.error = "Vui lòng nhập mô tả"
            }else{
                updateSuKien()
            }
        }
    }

    private fun updateSuKien(){
        CoroutineScope(Dispatchers.IO).launch {
            val tapSuKien = TapSuKien(
                suKienId = binding.edtMaSuKien.text.toString(),
                nhomId = selectedNhom!!.id,
                description = binding.edtMoTa.text.toString().trim())
            AppDatabase.APPDATABASE.tapSuKienDao().update(tapSuKien)
            withContext(Dispatchers.Main) {
                // Find existing TableRow for suKienId if it exists
                val existingRow = findTableRowBySuKienId(tapSuKien.suKienId)

                if (existingRow != null) {
                    // Update the existing TableRow
                    updateTableRow(existingRow, tapSuKien)
                } else {
                    // Add a new TableRow if it doesn't exist
                    binding.tableLayout.addView(initItem(tapSuKien))
                }

                Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
                resetData()
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
    private fun updateTableRow(row: TableRow, tapSuKien: TapSuKien) {
        val suKienIdTextView: TextView = row.findViewById(R.id.tv_ma_su_kien)
        val nhomIdTextView: TextView = row.findViewById(R.id.tv_ten_nhom)
        val descriptionTextView: TextView = row.findViewById(R.id.tv_mo_ta)

        suKienIdTextView.text = tapSuKien.suKienId
        nhomIdTextView.text = tapSuKien.nhomId
        descriptionTextView.text = tapSuKien.description
    }

    private fun addNewSuKien(){
        CoroutineScope(Dispatchers.IO).launch {
            if(selectedNhom == null){
                Toast.makeText(requireContext(),"Vui lòng chọn nhóm hợp lệ",Toast.LENGTH_SHORT).show()
                return@launch
            }
            val tapSuKien = TapSuKien(
                suKienId = binding.edtMaSuKien.text.toString(),
                nhomId = selectedNhom!!.id,
                description = binding.edtMoTa.text.toString().trim())
            AppDatabase.APPDATABASE.tapSuKienDao().insertTapSuKien(tapSuKien)
            withContext(Dispatchers.Main){
                binding.tableLayout.addView(initItem(tapSuKien))
                Toast.makeText(context, "Thêm mới thành công", Toast.LENGTH_SHORT).show()
                resetData()
            }
        }
    }

    private fun resetData(){
        binding.edtMaSuKien.setText("")
        binding.edtMoTa.setText("")
        binding.atvNhom.setText("Nhóm",false)
        selectedNhom = null
        binding.btnEdit.visibility = View.GONE
        binding.btnDelete.visibility = View.GONE
        binding.btnAddNew.visibility = View.VISIBLE
    }

    private fun displayGroup(nhom: List<Nhom>){
        val values = nhom.map { it.description }
        val adapterGrouped = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, values)
        binding.atvNhom.setAdapter(adapterGrouped)

        binding.atvNhom.setOnItemClickListener { _, _, position, _ ->
            selectedNhom = nhom[position] // Retrieve the Nhom object at the selected position

        }
    }

    private fun getData(){
        PopupDialog.showLoading(requireContext())
        CoroutineScope(Dispatchers.IO).launch {
            val tapSuKien = async { AppDatabase.APPDATABASE.tapSuKienDao().getAllSuKien() }
            groups = AppDatabase.APPDATABASE.nhomDao().getAll()
           groupEvents = tapSuKien.await()
            withContext(Dispatchers.Main){
                displayGroup(groups)
                addItemToTable(groupEvents)
                Handler().postDelayed({
                    PopupDialog.closeDialog()
                }, 300)
            }
        }
    }

    private fun addItemToTable(groups : List<TapSuKien>) {
        for (item in groups) {
           val tableRow = initItem(item)
            // Thêm TableRow vào TableLayout
            binding.tableLayout.addView(tableRow)
        }
    }


    private fun initItem(item: TapSuKien) :TableRow {
        val tableRow = layoutInflater.inflate(R.layout.item_tap_su_kien, null) as TableRow
        val suKienIdTextView: TextView = tableRow.findViewById(R.id.tv_ma_su_kien)
        val nhomIdTextView: TextView = tableRow.findViewById(R.id.tv_ten_nhom)
        val descriptionTextView: TextView = tableRow.findViewById(R.id.tv_mo_ta)
        suKienIdTextView.text = item.suKienId
        nhomIdTextView.text = item.nhomId.toString()
        descriptionTextView.text = item.description
        tableRow.setOnClickListener {
            selectedItem(item)
        }
        return tableRow
    }

    private fun selectedItem(item: TapSuKien) {
        binding.edtMaSuKien.setText(item.suKienId)
        binding.edtMoTa.setText(item.description)

        selectedNhom = groups.find { it.id == item.nhomId }
        binding.atvNhom.setText(selectedNhom?.description,false)

        binding.btnEdit.visibility = View.VISIBLE
        binding.btnDelete.visibility = View.VISIBLE
        binding.btnAddNew.visibility = View.GONE
    }
}