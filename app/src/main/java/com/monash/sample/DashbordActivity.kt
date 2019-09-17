package com.monash.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.dashboard_activity.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager


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

        dashboardAdapter.updateData(getData())
    }

    private fun initViewModels(activity: AppCompatActivity) {
        val factory = ViewModelFactory.getInstance(activity.application)
        mViewModel = ViewModelProviders.of(activity, factory).get(DashboardViewModel::class.java)
    }


    fun getData(): List<Comparable<*>> {
        val combineList: MutableList<Comparable<*>> = ArrayList()

        combineList.add(mViewModel.userData.lectures)
        combineList.add(mViewModel.userData.carparkings)
        combineList.add(mViewModel.userData.shuttles)

        return combineList
    }
}


