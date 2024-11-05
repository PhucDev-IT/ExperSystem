package vn.mobile.expersystem.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.core.util.Consumer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.mobile.expersystem.R
import vn.mobile.expersystem.adapters.RvNhomAdapter
import vn.mobile.expersystem.database.AppDatabase
import vn.mobile.expersystem.database.core.DataSample
import vn.mobile.expersystem.databinding.FragmentNhomBinding
import vn.mobile.expersystem.databinding.PopupAddNhomBinding
import vn.mobile.expersystem.models.Nhom

class NhomFragment : Fragment() {
   private lateinit var _binding:FragmentNhomBinding
    private val binding get() = _binding
    private lateinit var adapter:RvNhomAdapter
    private var dialog:Dialog?=null
    private var popupBinding:PopupAddNhomBinding?=null
    private var groups = listOf<Nhom>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNhomBinding.inflate(inflater,container,false)
        adapter = RvNhomAdapter {
            showDialog(it)
        }
        initView()
        setListener()
        return binding.root
    }

    private fun initPopup(){
        dialog = Dialog(requireContext(),R.style.Custom_Dialog)

        popupBinding = PopupAddNhomBinding.inflate(LayoutInflater.from(context))
        dialog!!.setContentView(popupBinding!!.root)
    }

    private fun initView(){
        getData()
        binding.rvGroup.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        binding.rvGroup.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(binding.rvGroup.context, DividerItemDecoration.VERTICAL)
        binding.rvGroup.addItemDecoration(dividerItemDecoration)
        initPopup()
    }

    private fun setListener(){
        binding.btnNew.setOnClickListener {
            showDialog(null)
        }

        popupBinding?.btnEdit?.setOnClickListener {
            if(popupBinding?.edtMaNhom?.text.isNullOrEmpty()){
                popupBinding?.tlMaNhom?.error = "Vui lòng nhập mã nhóm"
            }else
            if(popupBinding?.edtName?.text.isNullOrEmpty()){
                popupBinding?.tlName?.error = "Vui lòng nhập tên nhóm"
            }else if(popupBinding?.edtDescription?.text.isNullOrEmpty()){
                popupBinding?.tlDescription?.error = "Vui lòng nhập mô tả nhóm"
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    val nhom = popupBinding?.container?.tag as Nhom
                    val nhomNew = Nhom(id = nhom.id,name = popupBinding?.edtName?.text.toString(), description = popupBinding?.edtDescription?.text.toString())
                    AppDatabase.APPDATABASE.nhomDao().update(nhomNew)
                    withContext(Dispatchers.Main){
                        adapter.updateItem(nhomNew)
                    }
                }
                dialog?.dismiss()
            }
        }

        popupBinding?.btnAddGroup?.setOnClickListener {
            if(popupBinding?.edtMaNhom?.text.isNullOrEmpty()){
                popupBinding?.tlMaNhom?.error = "Vui lòng nhập mã nhóm"
            }else
            if(popupBinding?.edtName?.text.isNullOrEmpty()){
                popupBinding?.tlName?.error = "Vui lòng nhập tên nhóm"
            }else if(popupBinding?.edtDescription?.text.isNullOrEmpty()){
                popupBinding?.tlDescription?.error = "Vui lòng nhập mô tả nhóm"
            }else{
                val nhom = Nhom(id = popupBinding?.edtMaNhom?.text.toString(),name = popupBinding?.edtName?.text.toString(), description = popupBinding?.edtDescription?.text.toString())
                addData(nhom)
                dialog?.dismiss()
            }
        }

        popupBinding?.btnDelete?.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val nhom = popupBinding?.container?.tag as Nhom
                AppDatabase.APPDATABASE.nhomDao().delete(nhom)
                withContext(Dispatchers.Main){
                    adapter.removeItem(nhom)
                }
            }
            dialog?.dismiss()
        }
    }

    private fun addData(nhom:Nhom){
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.APPDATABASE.nhomDao().insert(nhom)
            withContext(Dispatchers.Main){
                adapter.addItem(nhom)
            }
        }
    }

    private fun showDialog(nhom: Nhom?){
        popupBinding?.tlName?.error = null
        popupBinding?.tlDescription?.error = null

        popupBinding?.container?.tag = nhom

        popupBinding?.edtName?.setText(nhom?.name)
        popupBinding?.edtDescription?.setText(nhom?.description)
        popupBinding?.edtMaNhom?.setText(nhom?.id)
        if(nhom == null){
            popupBinding?.edtMaNhom?.isEnabled = true
            popupBinding?.btnEdit?.visibility = View.GONE
            popupBinding?.btnDelete?.visibility = View.GONE
            popupBinding?.btnAddGroup?.visibility = View.VISIBLE

        }else{
            popupBinding?.edtMaNhom?.isEnabled = false
            popupBinding?.btnAddGroup?.visibility = View.GONE
            popupBinding?.btnEdit?.visibility = View.VISIBLE
            popupBinding?.btnDelete?.visibility = View.VISIBLE
        }

        dialog?.show()
    }

    private fun getData(){
        CoroutineScope(Dispatchers.IO).launch {
            val nhomResult = async { AppDatabase.APPDATABASE.nhomDao().getAll() }
            groups = nhomResult.await()
            withContext(Dispatchers.Main){
                adapter.setData(groups)
            }
        }
    }
}