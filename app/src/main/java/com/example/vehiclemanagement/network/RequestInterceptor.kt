package com.example.vehiclemanagement.network

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.run {
            proceed(
                request()
                    .newBuilder()
                    .addHeader("Authorization", HEADER_API_KEY)
                    .addHeader("Account-Token", HEADER_ACCOUNT_KEY)
                    .build()
            )
        }
    }
}