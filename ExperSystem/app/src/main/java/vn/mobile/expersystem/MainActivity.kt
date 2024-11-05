package vn.mobile.expersystem

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import vn.mobile.expersystem.databinding.ActivityMainBinding
import vn.mobile.expersystem.fragments.HomeFragment
import vn.mobile.expersystem.fragments.NhomFragment
import vn.mobile.expersystem.fragments.SuKienFragment
import vn.mobile.expersystem.fragments.TapLuatFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

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
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null)
            .commit()
        binding.drawerLayout.close()
    }
}