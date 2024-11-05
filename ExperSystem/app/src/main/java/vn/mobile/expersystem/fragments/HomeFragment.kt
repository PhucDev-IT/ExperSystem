package vn.mobile.expersystem.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatRadioButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.mobile.expersystem.R
import vn.mobile.expersystem.activities.RecommendActivity
import vn.mobile.expersystem.adapters.ExpandableListAdapter
import vn.mobile.expersystem.common.DataManager
import vn.mobile.expersystem.database.AppDatabase
import vn.mobile.expersystem.database.core.DataSample
import vn.mobile.expersystem.database.core.DataSample.groupEvents
import vn.mobile.expersystem.databinding.FragmentHomeBinding
import vn.mobile.expersystem.models.TapSuKien
import vn.mobile.expersystem.utils.Util.combinations

import java.time.LocalTime
import java.util.Calendar
import kotlin.math.log

class HomeFragment : Fragment() {
   private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding
    private var mHandler = Handler(Looper.getMainLooper())
    private val listRecommend = listOf("Nhu cầu của bạn là gì?", "Giá cả phải chăng?", "Động cơ mạnh mẽ", "Thương hiệu nổi tiếng", "Thiết kế sang trọng")
    private var job: Job? = null
    private var isShowMessageRecommend:Boolean = true
    private var selectedTapLuat = ""


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.tvHello.text = "Chào buổi ${getTimePeriod()}, hôm nay bạn muốn mua xe gì?"
        displayCategories()
        startAutoUpdateTextView()
        setListener()
        return binding.root
    }

    private fun setListener(){
        binding.btnSearch.setOnClickListener {
            if(selectedTapLuat.isNotEmpty()){
                val intent = Intent(requireContext(), RecommendActivity::class.java)
                intent.putExtra("selectedEvent",selectedTapLuat)
                startActivity(intent)
            }else{
                Toast.makeText(requireContext(), "Vui lòng chọn sự kiện", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getCategories(){
        CoroutineScope(Dispatchers.IO).launch {
            val allCombinations = mutableListOf<Set<TapSuKien>>()
            for (i in 1..groupEvents.size) {
               // allCombinations.addAll(groupEvents.combinations(i))
            }

            // Hiển thị các tổ hợp
            allCombinations.forEach { combination ->
                println(combination.joinToString(" ^ ") { it.suKienId })
            }
        }
    }

    private fun displayCategories(){
        val groupedEvents = DataManager.groupEvents.groupBy { it.nhomId }
        Log.d("Phuc","groupedEvents ${DataManager.groupEvents}")
        val childMap:MutableMap<String,List<TapSuKien>> = mutableMapOf()

        for(group in DataManager.groups){
            val id = group.id
            val des = group.description
            childMap[des] = groupedEvents[id]?: emptyList()
            Log.d("Phuc","$id ${childMap[des]}")
        }
        val adapter = ExpandableListAdapter(requireContext(), childMap.keys.toList(), childMap,
            emptyList()
        ) { selected->
            selectedTapLuat = ""
            for(item in selected){
                selectedTapLuat += "${item.suKienId} ^ "
            }
            binding.tvSelectedEvent.text = selectedTapLuat.dropLast(3)
        }
        binding.expandableListView.setAdapter(adapter)
        binding.expandableListView.setGroupIndicator(null)
//        setExpandableListViewHeight(binding.expandableListView)
//        binding.expandableListView.setOnGroupExpandListener { setExpandableListViewHeight(binding.expandableListView) }
//        binding.expandableListView.setOnGroupCollapseListener { setExpandableListViewHeight(binding.expandableListView) }


    }

    private fun setExpandableListViewHeight(listView: ExpandableListView) {
        val listAdapter = listView.expandableListAdapter ?: return

        var totalHeight = 0
        for (i in 0 until listAdapter.groupCount) {
            val groupView = listAdapter.getGroupView(i, false, null, listView)
            groupView.measure(
                View.MeasureSpec.makeMeasureSpec(listView.width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.UNSPECIFIED
            )
            totalHeight += groupView.measuredHeight

            if (listView.isGroupExpanded(i)) {
                for (j in 0 until listAdapter.getChildrenCount(i)) {
                    val childView = listAdapter.getChildView(i, j, false, null, listView)
                    childView.measure(
                        View.MeasureSpec.makeMeasureSpec(listView.width, View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.UNSPECIFIED
                    )
                    totalHeight += childView.measuredHeight
                }
            }
        }

        val params = listView.layoutParams
        params.height = totalHeight + listView.dividerHeight * (listAdapter.groupCount - 1)
        listView.layoutParams = params
        listView.requestLayout()
    }


    private fun startAutoUpdateTextView() {
        job = CoroutineScope(Dispatchers.Main).launch {
            while (selectedTapLuat.isEmpty()) {
                val randomText = listRecommend.random()

                binding.tvSelectedEvent.animate().alpha(0f).setDuration(500).withEndAction {
                    // Cập nhật chuỗi khi đã mờ hoàn toàn
                    binding.tvSelectedEvent.text = randomText
                    // Tạo hiệu ứng fade in
                    binding.tvSelectedEvent.animate().alpha(1f).setDuration(500).start()
                }.start()
                // Chờ 2 giây
                delay(4500)
            }
        }
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }

    private fun getTimePeriod(): String {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        return when (hour) {
            in 0..5 -> "Đêm"
            in 6..11 -> "Sáng"
            in 12..13 -> "Trưa"
            in 14..17 -> "Chiều"
            in 18..23 -> "Tối"
            else -> "Không xác định"
        }
    }


}