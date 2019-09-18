package com.monash.sample

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dashboard_activity.*
import kotlinx.android.synthetic.main.simple_toolbar.view.*
import java.util.*
import kotlin.collections.ArrayList


class DashbordActivity : AppCompatActivity() {

    private lateinit var dashboardRecyclerView: RecyclerView
    private lateinit var dashboardAdapter: DashboardAdapter
    private lateinit var mViewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)
        initViewModels(this)

        dashboardRecyclerView = rv_dashboard
        dashboardAdapter = DashboardAdapter()

        dashboardRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        dashboardRecyclerView.adapter = dashboardAdapter

        updateUI()
    }

    private fun initViewModels(activity: AppCompatActivity) {
        val factory = ViewModelFactory.getInstance(activity.application)
        mViewModel = ViewModelProviders.of(activity, factory).get(DashboardViewModel::class.java)
    }

    fun updateUI() {
        dashboardAdapter.updateData(getData())

        /* update toolbar */
            toolbar.toolbar_name.text =
                resources.getString(R.string.hey_user, mViewModel.userData.firstName)

            /* 17/05 Wednesday, Week 8 */
            val today: Calendar = Calendar.getInstance()
            today.set(Calendar.HOUR_OF_DAY, 0)
            today.set(Calendar.MINUTE, 0)
            today.set(Calendar.SECOND, 0)
            today.set(Calendar.MILLISECOND, 0)

            val locale = Locale.getDefault()

            toolbar.toolbar_dateWeek.text =
                resources.getString(
                    R.string.date_day,
                    SimpleDateFormat("dd/MM", locale).format(today.time),
                    SimpleDateFormat("EEEE", locale).format(today.time),
                    mViewModel.userData.weekNum
                )
    }

    fun getData(): List<Comparable<*>> {
        val combineList: MutableList<Comparable<*>> = ArrayList()

        combineList.add(mViewModel.userData.lectures)
        combineList.add(mViewModel.userData.carparkings)
        combineList.add(mViewModel.userData.shuttles)

        return combineList
    }
}


