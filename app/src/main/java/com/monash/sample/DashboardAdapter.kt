package com.monash.sample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monash.sample.pojo.*
import kotlinx.android.synthetic.main.timetable_card.view.*

class DashboardAdapter() : RecyclerView.Adapter<DashboardAdapter.BaseViewHolder<*>>() {
    private val data: MutableList<Comparable<*>>
    lateinit var context: Context

    companion object {
        const val TYPE_TIMETABLE = 0
        const val TYPE_CAR_PARK = 1
        const val TYPE_SHUTTLE = 2
    }

    init {
        data = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        context = parent.context
        return when (viewType) {
            TYPE_TIMETABLE -> {
                val view =
                    LayoutInflater.from(context).inflate(R.layout.timetable_card, parent, false)
                TimetableViewHolder(view, context, data)
            }
            TYPE_CAR_PARK -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.carpark_card, parent, false)
                CarparkViewHolder(view, context, data)
            }
            TYPE_SHUTTLE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.shuttle_card, parent, false)
                ShuttleViewHolder(view, context, data)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    fun updateData(newData: List<Comparable<*>>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        val comparable = data[position]

        return when (comparable) {
            is Lectures -> TYPE_TIMETABLE
            is CarParkings -> TYPE_CAR_PARK
            is Shuttles -> TYPE_SHUTTLE
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = data[position]
        when (holder) {
            is TimetableViewHolder -> holder.bind(element as Lectures)
            is CarparkViewHolder -> holder.bind(element as CarParkings)
            is ShuttleViewHolder -> holder.bind(element as Shuttles)
            else -> throw IllegalArgumentException()
        }
    }


    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }


    class TimetableViewHolder(val view: View, val context: Context, val data: List<Comparable<*>>) :
        BaseViewHolder<Lectures>(view) {
        override fun bind(item: Lectures) {
            view.ll_list.removeAllViews()
            if (item.lectures.isNotEmpty()) {
                for (i in 0 until item.lectures.size) {
                    val newEntry =
                        LayoutInflater.from(context)
                            .inflate(R.layout.timetable_row, view.ll_list, false)
                    view.ll_list.addView(newEntry)
                }
            } else {
                val textView = TextView(context)
                textView.text = "Nothing for today!"
                textView.gravity = 1
                view.ll_list.addView(textView)
            }
        }
    }

    class CarparkViewHolder(val view: View, val context: Context, val data: List<Comparable<*>>) :
        BaseViewHolder<CarParkings>(view) {
        override fun bind(item: CarParkings) {
            view.ll_list.removeAllViews()
            if (item.carParkings.isNotEmpty()) {
                for (i in 0 until item.carParkings.size) {
                    val newEntry =
                        LayoutInflater.from(context)
                            .inflate(R.layout.carpark_row, view.ll_list, false)
                    view.ll_list.addView(newEntry)
                }
            } else {
                val textView = TextView(context)
                textView.text = "Nothing for today!"
                textView.gravity = 1
                view.ll_list.addView(textView)
            }
        }
    }

    class ShuttleViewHolder(val view: View, val context: Context, val data: List<Comparable<*>>) :
        BaseViewHolder<Shuttles>(view) {
        override fun bind(item: Shuttles) {
            view.ll_list.removeAllViews()
            if (item.shuttles.isNotEmpty()) {
                for (i in 0 until item.shuttles.size) {
                    val newEntry =
                        LayoutInflater.from(context)
                            .inflate(R.layout.shuttle_row, view.ll_list, false)
                    view.ll_list.addView(newEntry)
                }
            } else {
                val textView = TextView(context)
                textView.text = "Nothing for today!"
                textView.gravity = 1
                view.ll_list.addView(textView)
            }
        }
    }
}
