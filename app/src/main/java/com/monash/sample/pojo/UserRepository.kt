package com.monash.sample.pojo


import com.monash.sample.network.ApiCallback
import com.monash.sample.network.RemoteDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    /*
     * method to call get user data api
     */
    fun executeGetUserData(apiCallback: ApiCallback<UserData>) {

        val apiInterface = RemoteDataSource.apiInterface

        val call = apiInterface.getUserData()
        call.enqueue(object : Callback<UserData> {

            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                if (response.isSuccessful && response.body() != null) {
                    apiCallback.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {
                apiCallback.onFailure()
            }
        })
    }

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null


        val instance: UserRepository?
            get() {
                if (INSTANCE == null) {
                    synchronized(UserRepository::class.java) {
                        if (INSTANCE == null) {
                            INSTANCE = UserRepository()
                        }
                    }
                }
                return INSTANCE
            }
    }
}
