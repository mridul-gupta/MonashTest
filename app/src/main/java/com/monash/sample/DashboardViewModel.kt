package com.monash.sample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monash.sample.network.ApiCallback
import com.monash.sample.pojo.UserData
import com.monash.sample.pojo.UserRepository

class DashboardViewModel : ViewModel() {
    var userData: UserData

    internal val responseStatus = MutableLiveData(Status.IDLE)

    private var mRepository: UserRepository? = null

    init {
        this.mRepository = UserRepository()

        userData = getData() /* default data */
        getUserData()
    }

    fun getUserData() {
        responseStatus.value = Status.LOADING

        mRepository?.executeGetUserData(
            object : ApiCallback<UserData> {

                override fun onFailure() {
                    responseStatus.value = Status.ERROR
                }

                override fun onSuccess(data: UserData) {
                    userData = data
                    responseStatus.value = Status.SUCCESS
                }
            })
    }
}
