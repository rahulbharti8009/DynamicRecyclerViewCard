package com.room.app

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class AddSpaceInCell(
    var vertical: Boolean = true,
    context: Context,
    itemWidth: Float,
    itemPeekingPercent: Float = .035f,
    var extraLeftWidth: Int = 5
) :
    RecyclerView.ItemDecoration() {
    private var mInterItemsGap: Int = 0
    private var mNetOneSidedGap: Int = 0
//     var  context : Context? = context

    init {
        val cardPeekingWidth = (itemWidth * itemPeekingPercent + .5f).toInt()
        mInterItemsGap = ((context.resources.displayMetrics.widthPixels - itemWidth) / 2).toInt()
        mNetOneSidedGap = mInterItemsGap / 2 - cardPeekingWidth
        Log.e("TAG", "cardPeekingWidth $cardPeekingWidth")

    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        recyclerView: RecyclerView,
        state: RecyclerView.State
    ) {
        val index = recyclerView.getChildAdapterPosition(view)
        val isFirstItem = isFirstItem(index)
        val isLastItem = isLastItem(index, recyclerView)

        val leftInset = if (isFirstItem) mInterItemsGap else mNetOneSidedGap
        val rightInset = if (isLastItem) mInterItemsGap else mNetOneSidedGap

        Log.e("TAG", "mInterItemsGap $mInterItemsGap")
        Log.e("TAG", "mNetOneSidedGap $mNetOneSidedGap")

        if (vertical)
            outRect.set(0, leftInset, 0, rightInset)
        else
            outRect.set(leftInset,0 , rightInset,0 )
    }

    private fun isFirstItem(index: Int) = index == 0
    private fun isLastItem(index: Int, recyclerView: RecyclerView) =
        index == recyclerView.adapter!!.itemCount - 1
}