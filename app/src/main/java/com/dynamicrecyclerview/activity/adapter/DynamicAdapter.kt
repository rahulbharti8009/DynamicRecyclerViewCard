package com.dynamicrecyclerview.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dynamicrecyclerview.R
import com.dynamicrecyclerview.entity.ResponseEntity
import com.dynamicrecyclerview.utils.Constant
import com.dynamicrecyclerview.utils.PeekingLinearLayoutManager
import kotlinx.android.synthetic.main.custom_layout_two.view.*

class DynamicAdapter(var context: Context, var data: MutableList<ResponseEntity>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            Constant.one -> {
                One(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.custom_layout_one,
                        parent,
                        false
                    )
                )
            }
            Constant.two -> Two(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.custom_layout_two,
                    parent,
                    false
                )
            )
            Constant.three -> Three(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.custom_layout_three,
                    parent,
                    false
                )
            )
            else -> {
                One(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.custom_layout_one,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position].type) {
            Constant.one -> data[position].type
            Constant.two -> data[position].type
            Constant.three -> data[position].type
            else -> {
                data[position].type
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (data[position].type) {
            Constant.one -> oneLayout(holder, position, context)
            Constant.two -> twoLayout(holder, position, context)
            Constant.three -> threeLayout(holder, position, context)
            else -> oneLayout(holder, position, context)
        }
    }

    private fun oneLayout(
        holder: RecyclerView.ViewHolder,
        position: Int,
        context: Context
    ) { // set Data
        if (holder is One) {
            //TODO
        }
    }

    private fun twoLayout(
        holder: RecyclerView.ViewHolder,
        position: Int,
        context: Context) { // set Data
        if (holder is Two) {
            holder.rv.apply {
                layoutManager = PeekingLinearLayoutManager(context, ratio = 0.40f)
                adapter = SimpleCardAdapter(context)
//                val cardWidthPixels = this.resources.displayMetrics.widthPixels * 0.92f
//                val cardHintPercent = 0.01f
//                addItemDecoration(RVPagerSnapFancyDecorator(vertical = false, context = context, itemWidth = cardWidthPixels, itemPeekingPercent = cardHintPercent))
//                val snapHelper: SnapHelper = StartSnapHelper()
//                snapHelper.attachToRecyclerView(holder.rv)

            }
        }
    }

    private fun threeLayout(
        holder: RecyclerView.ViewHolder,
        position: Int,
        context: Context) { // set Data
        if (holder is Three) {
            //TODO
        }
    }

    class One(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        override fun onClick(p0: View?) {
        }

    }

    class Two(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        internal var rv: RecyclerView = itemView.rvTwo
        override fun onClick(p0: View?) {
        }

    }

    class Three(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        override fun onClick(p0: View?) {
        }
    }

}