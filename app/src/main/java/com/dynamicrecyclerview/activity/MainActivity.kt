package com.dynamicrecyclerview.activity

import android.os.Bundle
import android.util.Log
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
    var updateValue = 0
    var lastValue = 0
    val data: MutableList<ResponseEntity> = mutableListOf()
    var N: MutableList<Int> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // perform operation
        // set data using binding
        setData()
        binding.swipeRefreshLayout.setOnRefreshListener {updateAdapter() }

//        val startSnapHelper = StartSnapHelper()
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
//            startSnapHelper.attachToRecy  clerView(this)
        }
    }

    // refresh data
    private fun updateAdapter() {
        binding.swipeRefreshLayout.isRefreshing = false
        N.clear()
        N.add(0)
        for (i in 0 until 3) {
            data[i] = ResponseEntity(recursion(i))
        }
        binding.rvComponent.adapter!!.notifyDataSetChanged()
    }

    fun setData() {
        N.add(0)
        for (i in 0 until 3) {
            data.add(ResponseEntity(recursion(i)))
        }
    }

    //  invoked first time
    private fun recursion(i: Int): Int {
        updateValue = Random.nextInt(0, 4)
        if (N.contains(updateValue)) {
            recursion(i)
        } else {
            N.add(updateValue)
        }
//        lastValue = updateValue
        Log.e("TAG", "update value $updateValue")
        return updateValue
    }
}