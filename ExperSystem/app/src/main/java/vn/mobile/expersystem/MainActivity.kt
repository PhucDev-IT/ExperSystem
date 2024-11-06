package vn.mobile.expersystem

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.mobile.expersystem.activities.InformationFragment
import vn.mobile.expersystem.database.AppDatabase
import vn.mobile.expersystem.databinding.ActivityMainBinding
import vn.mobile.expersystem.fragments.HomeFragment
import vn.mobile.expersystem.fragments.NhomFragment
import vn.mobile.expersystem.fragments.SuKienFragment
import vn.mobile.expersystem.fragments.TapLuatFragment
import vn.mobile.expersystem.utils.MySharedPreferences
import vn.mobile.expersystem.utils.Util

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.nav_group ->{
                    replaceFragment(NhomFragment())
                    true
                }

                R.id.nav_event ->{
                    replaceFragment(SuKienFragment())
                    true
                }
                R.id.nav_recommend->{
                    replaceFragment(TapLuatFragment())
                    true
                }
                R.id.nav_info->{
                    replaceFragment(InformationFragment())
                    true

                }
                R.id.nav_delete->{
                    clearAllData()
                    true
                }
                else -> {
                    replaceFragment(NhomFragment())
                    true
                }
            }
        }

        setListener()
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            binding.navView.setCheckedItem(R.id.nav_home)
        }
    }

    private fun setListener(){
        binding.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

    }

    @SuppressLint("CommitTransaction")
    private fun replaceFragment(fragment: Fragment) {
        if(currentFragment != fragment){
            currentFragment = fragment
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null)
                .commit()
        }
        binding.drawerLayout.close()
    }

    private fun clearAllData() {
        // Hiển thị hộp thoại xác nhận với người dùng
        AlertDialog.Builder(this)
            .setTitle("Xác nhận")
            .setMessage("Bạn có chắc chắn muốn xóa toàn bộ dữ liệu không?")
            .setPositiveButton("Đồng ý") { _, _ ->
                // Xóa cache
                Util.clearCache(this@MainActivity)

                // Xóa dữ liệu trong Room database
                CoroutineScope(Dispatchers.IO).launch {
                    AppDatabase.APPDATABASE.clearAllTables()
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, "Dữ liệu đã được xóa.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Hủy", null)
            .show()
    }
}