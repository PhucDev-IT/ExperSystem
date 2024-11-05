package vn.mobile.expersystem.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.mobile.expersystem.MainActivity
import vn.mobile.expersystem.R
import vn.mobile.expersystem.common.DataManager
import vn.mobile.expersystem.database.AppDatabase
import vn.mobile.expersystem.database.core.DataSample
import vn.mobile.expersystem.databinding.ActivitySplashScreenBinding
import vn.mobile.expersystem.utils.MySharedPreferences


class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Glide.with(this).load(R.drawable.oto).into(binding.icon)
        val check = MySharedPreferences.getBooleanValue(this,MySharedPreferences.IS_USED_APP)
        if(check){
            Handler().postDelayed({
                getData()
            },1000)
        }else{
            handleSetupData()
        }
    }

    private fun getData(){
        CoroutineScope(Dispatchers.IO).launch {
            val nhomResult = async { AppDatabase.APPDATABASE.nhomDao().getAll() }
            val groupEventsResult = async { AppDatabase.APPDATABASE.tapSuKienDao().getAllSuKien() }
            DataManager.groups = nhomResult.await()
            DataManager.groupEvents = groupEventsResult.await()
            withContext(Dispatchers.Main){
                startActivity(Intent(this@SplashScreenActivity,MainActivity::class.java))
                finishAffinity()
            }
        }
    }


    private fun handleSetupData(){
        CoroutineScope(Dispatchers.IO).launch {
            val nhomResult = async { AppDatabase.APPDATABASE.nhomDao().insertAll(DataSample.groups.toList()) }
            nhomResult.await()

            val tapSuKienResult = async { AppDatabase.APPDATABASE.tapSuKienDao().insertAll(DataSample.groupEvents.toList()) }
            tapSuKienResult.await()

            val tapLuatResult = async { AppDatabase.APPDATABASE.tapLuatDao().insertAll(DataSample.groupRules.toList()) }
            tapLuatResult.await()

            val imageResult = async { AppDatabase.APPDATABASE.imageDao().insertAll(DataSample.images.toList()) }
            imageResult.await()

            withContext(Dispatchers.Main){

                DataManager.groups = DataSample.groups
                DataManager.groupRules = DataSample.groupRules
                DataManager.groupEvents = DataSample.groupEvents
                DataManager.images = DataSample.images

                MySharedPreferences.setBooleanValue(this@SplashScreenActivity,MySharedPreferences.IS_USED_APP,true)
                startActivity(Intent(this@SplashScreenActivity,MainActivity::class.java))
                finishAffinity()
            }
        }

    }
}