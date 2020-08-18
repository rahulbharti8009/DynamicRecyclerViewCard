package com.dynamicrecyclerview.activity

import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.dynamicrecyclerview.R
import com.dynamicrecyclerview.activity.adapter.DynamicAdapter
import com.dynamicrecyclerview.databinding.ActivityMainBinding
import com.dynamicrecyclerview.entity.ResponseEntity
import com.dynamicrecyclerview.utils.BaseActivity
import com.room.app.AddSpaceInCell
import kotlin.random.Random

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    private var updateValue = 0
    private val data: MutableList<ResponseEntity> = mutableListOf()
    private var existElementList: MutableList<Int> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.toolbar.apply {
            title = "Dynamic RecyclerViewCard"
            setSubtitleTextColor(ContextCompat.getColor(this@MainActivity, R.color.color_white))
        }
        setData()
        binding.swipeRefreshLayout.setOnRefreshListener {updateAdapter() }

        binding.rvComponent.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = DynamicAdapter(this@MainActivity, data)
            val cardWidthPixels = this.resources.displayMetrics.widthPixels * 0.92f
            val cardHintPercent = 0.01f
            addItemDecoration(
                AddSpaceInCell(
                    true,
                    this@MainActivity,
                    cardWidthPixels,
                    cardHintPercent
                )
            )
        }
    }

    // refresh data
    private fun updateAdapter() {
        binding.swipeRefreshLayout.isRefreshing = false
        existElementList.clear()
        existElementList.add(0)
        for (i in 0 until 3) {
            data[i] = ResponseEntity(recursion(i))
        }
        binding.rvComponent.adapter!!.notifyDataSetChanged()
    }

    private fun setData() {
        existElementList.add(0)
        for (i in 0 until 3) {
            data.add(ResponseEntity(recursion(i)))
        }
    }

    //  invoked first time
    private fun recursion(i: Int): Int {
        updateValue = Random.nextInt(0, 4)
        if (existElementList.contains(updateValue)) {
            recursion(i)
        } else {
            existElementList.add(updateValue)
        }
        Log.e("TAG", "update value $updateValue")
        return updateValue
    }
}