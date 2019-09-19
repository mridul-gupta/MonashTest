package com.monash.sample.network

object RemoteDataSource {
    val apiInterface: ApiInterface = ApiClient.client.create(ApiInterface::class.java)
}
