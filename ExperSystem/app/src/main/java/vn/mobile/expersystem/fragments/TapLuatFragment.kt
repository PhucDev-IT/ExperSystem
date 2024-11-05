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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.mobile.expersystem.R
import vn.mobile.expersystem.adapters.ExpandableListAdapter
import vn.mobile.expersystem.database.AppDatabase
import vn.mobile.expersystem.database.core.DataSample
import vn.mobile.expersystem.database.core.DataSample.groupEvents
import vn.mobile.expersystem.databinding.FragmentTapLuatBinding
import vn.mobile.expersystem.databinding.PopupAddNhomBinding
import vn.mobile.expersystem.databinding.PopupTapLuatManagerBinding
import vn.mobile.expersystem.models.TapLuat
import vn.mobile.expersystem.models.TapSuKien

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
        handleEvent()
       CoroutineScope(Dispatchers.IO).launch {
           val tapLuat = AppDatabase.APPDATABASE.tapLuatDao().getAll()
           withContext(Dispatchers.Main){
               displayData(tapLuat)
           }
       }
    }

    private fun displayData(tapLuat: List<TapLuat>) {
        for (item in tapLuat) {
            val tableRow = layoutInflater.inflate(R.layout.item_tap_luat, null) as TableRow
            val tvMaLuat: TextView = tableRow.findViewById(R.id.tv_ma_luat)
            val tvMaSuKien: TextView = tableRow.findViewById(R.id.tv_ma_su_kien)
            val tvKetLuan: TextView = tableRow.findViewById(R.id.tv_ket_luan)
            val tvHinhAnh: TextView = tableRow.findViewById(R.id.tv_hinh_anh)

            // Điền dữ liệu vào các TextView
            tvMaLuat.text = item.maLuat
            tvMaSuKien.text = item.suKienId
            tvKetLuan.text = item.ketLuan

            tableRow.setOnClickListener {
                showPopup(item)

            }
            // Thêm TableRow vào TableLayout
            binding.tableLayout.addView(tableRow)
        }
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
        val adapter = ExpandableListAdapter(requireContext(), childMap.keys.toList(), childMap,selectedItem){}
        popupBinding.expandableListView.setAdapter(adapter)
        popupBinding.expandableListView.setGroupIndicator(null)

        popupBinding.edtKetLuan.setText(tapLuat?.ketLuan)

        CoroutineScope(Dispatchers.IO).launch {
            val list = AppDatabase.APPDATABASE.imageDao().getAllImagesForTapLuat(tapLuat?.id ?: 0)
            val imageUrls = list.map { it.hinhAnhXe }
            withContext(Dispatchers.Main) {
                //  popupBinding.edtHinhAnh.setAdapter(adapter)
            }
        }

        dialog.show()

    }
}