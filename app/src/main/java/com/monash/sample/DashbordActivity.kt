package com.monash.sample

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dashboard_activity.*
import kotlinx.android.synthetic.main.simple_toolbar.*
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

        mViewModel.responseStatus.observe(this, Observer { consumeResponse(mViewModel.responseStatus.value) })

        /* button actions */
        iv_refresh.setOnClickListener { mViewModel.getUserData() }
        iv_profile.setOnClickListener {
            Toast.makeText(
                this,
                "Coming soon. Try the button next to it.",
                Toast.LENGTH_SHORT
            ).show()
        }

        updateUI()
    }

    private fun initViewModels(activity: AppCompatActivity) {
        val factory = ViewModelFactory.getInstance()
        mViewModel = ViewModelProviders.of(activity, factory).get(DashboardViewModel::class.java)
    }

    private fun updateUI() {
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

    private fun getData(): List<Comparable<*>> {
        val combineList: MutableList<Comparable<*>> = ArrayList()

        combineList.add(mViewModel.userData.lectures)
        combineList.add(mViewModel.userData.carParkings)
        combineList.add(mViewModel.userData.shuttles)

        return combineList
    }

    private fun consumeResponse(status: Status?) {

        when (status) {

            Status.LOADING -> {
                progressBar.visibility = View.VISIBLE
                toolbar.visibility = View.GONE
                rv_dashboard.visibility = View.GONE
            }

            Status.SUCCESS -> {
                progressBar.visibility = View.GONE
                toolbar.visibility = View.VISIBLE
                rv_dashboard.visibility = View.VISIBLE
                Toast.makeText(this, "Refreshed data from network", Toast.LENGTH_SHORT).show()

                updateUI()
            }

            Status.ERROR -> {
                progressBar.visibility = View.GONE
                toolbar.visibility = View.VISIBLE
                rv_dashboard.visibility = View.VISIBLE
                Toast.makeText(this, "Error fetching from network. Loading saved data.", Toast.LENGTH_SHORT).show()
            }

            else -> {
                Log.e("consumeResponse", "Status not set")
            }
        }
    }
}


