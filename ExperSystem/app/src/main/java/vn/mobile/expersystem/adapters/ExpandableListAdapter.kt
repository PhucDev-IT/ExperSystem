package vn.mobile.expersystem.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.util.Consumer
import vn.mobile.expersystem.R
import vn.mobile.expersystem.models.TapSuKien

class ExpandableListAdapter(
    private val context: Context,
    private val groupList: List<String>,
    private val childMap: Map<String, List<TapSuKien>>,
    private val selectedItem: List<String>, // Pass split here
    private val onCheckedItemsChanged:Consumer<List<TapSuKien>>
) : BaseExpandableListAdapter() {

    private val checkedItems = mutableMapOf<String, MutableSet<TapSuKien>>()

    init {
        // Initialize checked items map with empty sets for each group
        groupList.forEach { group ->
            val childList = childMap[group] ?: emptyList()
            val checkedSet = childList.filter { child ->
                selectedItem.contains(child.suKienId)
            }.toMutableSet()
            checkedItems[group] = checkedSet
        }
    }

    override fun getGroupCount() = groupList.size

    override fun getChildrenCount(groupPosition: Int): Int {
        val groupName = groupList[groupPosition]
        return childMap[groupName]?.size ?: 0
    }

    override fun getGroup(groupPosition: Int) = groupList[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int): String {
        val groupName = groupList[groupPosition]
        return childMap[groupName]?.get(childPosition)?.description ?: ""
    }

    override fun getGroupId(groupPosition: Int) = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int) = childPosition.toLong()

    override fun hasStableIds() = true

    override fun isChildSelectable(groupPosition: Int, childPosition: Int) = true

    override fun getGroupView(
        groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup
    ): View {
        val groupName = getGroup(groupPosition)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.group_expand_item, parent, false)
        val groupText = view.findViewById<TextView>(R.id.groupTextView)
        groupText.text = groupName
        return view
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup
    ): View {

        val childName = getChild(groupPosition, childPosition)
        val currentTapSuKien = childMap[groupList[groupPosition]]?.get(childPosition)

        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.child_expand_item, parent, false)
        val checkBox = view.findViewById<CheckBox>(R.id.checkbox)
        checkBox.setOnCheckedChangeListener(null)
        checkBox.text = childName
        checkBox.isChecked = checkedItems[groupList[groupPosition]]?.contains(currentTapSuKien) == true

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (currentTapSuKien != null) {
                    checkedItems[groupList[groupPosition]]?.add(currentTapSuKien)
                }
            } else {
                checkedItems[groupList[groupPosition]]?.remove(currentTapSuKien)
            }
            onCheckedItemsChanged.accept(getCheckedItems())
        }

        return view
    }

    private fun getCheckedItems():List<TapSuKien> = checkedItems.values.flatten().toList()
}
