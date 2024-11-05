package vn.mobile.expersystem.utils

import android.content.Context
import android.content.SharedPreferences


class MySharedPreferences {
    companion object {
        private const val MY_SHARED_PREFERENCES: String = "MY_SHARED_PREFERENCES"
        const val IS_USED_APP: String = "IS_USED_APP"



        fun getStringValues(context: Context, key: String): String? {
            val sharedPreferences =
                context.getSharedPreferences(MY_SHARED_PREFERENCES, Context.MODE_PRIVATE)
            return sharedPreferences.getString(key, null)
        }

        fun getIntValues(context: Context, key: String): Int {
            val sharedPreferences =
                context.getSharedPreferences(MY_SHARED_PREFERENCES, Context.MODE_PRIVATE)
            return sharedPreferences.getInt(key, -1)
        }
        fun setIntValue(context: Context, key: String, value:Int){
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(
                MY_SHARED_PREFERENCES, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt(key,value)
            editor.apply()
        }
        fun setStringValue(context: Context, key: String, value:String){
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(
                MY_SHARED_PREFERENCES, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString(key,value)
            editor.apply()
        }

        fun getBooleanValue(context: Context, key: String):Boolean{
            val sharedPreferences =
                context.getSharedPreferences(MY_SHARED_PREFERENCES, Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean(key, false)
        }

        fun setBooleanValue(context: Context, key: String, value:Boolean){
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(
                MY_SHARED_PREFERENCES, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean(key,value)
            editor.apply()
        }
    }
}