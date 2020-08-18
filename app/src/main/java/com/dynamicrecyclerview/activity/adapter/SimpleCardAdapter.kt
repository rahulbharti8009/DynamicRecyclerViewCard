package com.dynamicrecyclerview.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dynamicrecyclerview.R
import kotlinx.android.synthetic.main.custom_layout_three.view.*

class SimpleCardAdapter(var context: Context) : RecyclerView.Adapter<SimpleCardAdapter.MyHolder>() {

    class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_layout_three, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        holder.itemView.tvThree.apply {  text = context.resources.getString(R.string.str_cell , position)
                   setTextColor(ContextCompat.getColor(context , R.color.colorAccent))
        }
        if (position % 2 == 0)
            holder.itemView.clthree.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorPrimaryDark
                )
            )
        else
            holder.itemView.clthree.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorPrimary
                )
            )
    }
}