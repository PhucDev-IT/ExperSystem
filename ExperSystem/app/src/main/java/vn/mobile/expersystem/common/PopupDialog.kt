package vn.mobile.expersystem.common

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import vn.mobile.expersystem.R

object PopupDialog {

    private var dialog: Dialog?=null
    fun showLoading(context:Context){
        dialog = Dialog(context)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setContentView(R.layout.popup_loading)
        dialog?.setCancelable(true)
        dialog?.show()
    }


    fun closeDialog(){
        dialog?.dismiss()
    }
}