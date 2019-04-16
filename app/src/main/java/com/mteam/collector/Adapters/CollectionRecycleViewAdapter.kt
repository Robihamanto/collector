package com.mteam.collector.Adapters

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mteam.collector.Model.CollectNumber
import com.mteam.collector.R

class CollectionRecycleViewAdapter(val context: Context, val collectionNumber: List<CollectNumber>, val itemClick: (CollectNumber) -> Unit) : RecyclerView.Adapter<CollectionRecycleViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.collect_number_list, parent, false)
        return Holder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return collectionNumber.count()
    }

    override fun onBindViewHolder(holder: Holder, index: Int) {
        holder.bindCollectNumber(collectionNumber[index], context)
    }

    inner class  Holder(itemView: View, val itemClick: (CollectNumber) -> Unit) : RecyclerView.ViewHolder(itemView) {

        val collectNumberText = itemView.findViewById<TextView>(R.id.collectNumberTextView)
        val collectNumberBackgroundColor = itemView.findViewById<View>(R.id.collectNumberBackgrounView)

        fun bindCollectNumber(collectNumber: CollectNumber, context: Context) {
            collectNumberText.text = collectNumber.toString()

            if (collectNumber.isShouldSelected == true) {
                collectNumberBackgroundColor.setBackgroundColor(Color.parseColor("#7CB63E"))
            } else {
                //collectNumberBackgroundColor.setBackgroundColor(Color.parseColor("##212121"))
            }
            itemView.setOnClickListener{ itemClick(collectNumber) }
        }
    }



}