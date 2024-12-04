package vn.mobile.expersystem.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.mobile.expersystem.R
import vn.mobile.expersystem.adapters.RvImageAdapter
import vn.mobile.expersystem.adapters.RvRecommendMoreThanAdapter
import vn.mobile.expersystem.database.AppDatabase
import vn.mobile.expersystem.databinding.ActivityRecommendBinding

class RecommendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecommendBinding
    private var selectedEvent: String = ""
    private var adapter: RvImageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecommendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (intent.hasExtra("selectedEvent")) {
            selectedEvent = intent.getStringExtra("selectedEvent").toString()
            if (selectedEvent.length >= 3 && selectedEvent.endsWith(" ^ ")) {
                selectedEvent = selectedEvent.dropLast(3).trim()
            }
            //searchData()
            recommendMoreThan()
        } else {
            binding.tvResult.text = "Lỗi hệ thống"
            binding.tvResult.setTextColor(resources.getColor(R.color.color_red))
        }
        binding.llHeader.btnBack.setOnClickListener { finish() }
    }

    private fun searchData() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = async {
                AppDatabase.APPDATABASE.tapLuatDao().getLuatBySuKienId(selectedEvent.trim())
            }.await()
            if (result != null) {
              //  showImage(result.id)
                withContext(Dispatchers.Main) {
                    binding.tvResult.text = result.ketLuan.toString()
                }
            } else {
                binding.tvResult.text = "Không tìm thấy dữ liệu phù hợp!"
            }
        }
    }

//    private suspend fun showImage(tapLuatId: Int) {
//        val images = AppDatabase.APPDATABASE.imageDao().getAllImagesForTapLuat(tapLuatId)
//        Log.d("Images", images.toString())
//        withContext(Dispatchers.Main) {
//            adapter = RvImageAdapter(images.map { it.hinhAnhXe })
//            binding.rvImage.adapter = adapter
//            binding.rvImage.layoutManager =
//                LinearLayoutManager(this@RecommendActivity, LinearLayoutManager.VERTICAL, false)
//
//        }
//    }

    private fun recommendMoreThan() {
        val more = selectedEvent.reversed()
        CoroutineScope(Dispatchers.IO).launch {
            val result = async {
                AppDatabase.APPDATABASE.tapLuatDao().selectMoreLike(more, selectedEvent)
            }.await().toMutableList()
            withContext(Dispatchers.Main) {
                if (result.isNotEmpty()) {
                    val needTapLuat = result.find { it.suKienId == selectedEvent }
                    if (needTapLuat != null) {
                        binding.tvResult.text = needTapLuat.ketLuan.toString()
                    } else {
                        binding.tvResult.text = "Không tìm thấy dữ liệu phù hợp!"
                    }
                        result.remove(needTapLuat)
                    val adapter =
                        RvRecommendMoreThanAdapter(result.map { it.ketLuan }.toMutableList())
                    binding.rvMoreThan.adapter = adapter
                    binding.rvMoreThan.layoutManager = LinearLayoutManager(
                        this@RecommendActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                }else{
                    binding.tvResult.text = "Không tìm thấy dữ liệu phù hợp!"
                }
            }
        }
    }
}