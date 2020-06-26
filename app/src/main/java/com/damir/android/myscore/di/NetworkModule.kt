package com.damir.android.myscore.di

import com.damir.android.myscore.ui.competitions.data.retrofit.CompetitionMatchesService
import com.damir.android.myscore.ui.competitions.data.retrofit.CompetitionsService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://api.football-data.org/v2/"
private const val AUTH_TOKEN = "c4e4bdfb73a74ac3b601e6fa58cb81be"

val networkModule = module {
    single { okHttpClient() }
    single { retrofit(get()) }
    single { competitionsService(get()) }
    single { competitionMatchesService(get()) }
}

private fun okHttpClient() : OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}

private fun retrofit(client: OkHttpClient) : Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun competitionsService(retrofit: Retrofit) : CompetitionsService =
    retrofit.create(CompetitionsService::class.java)

private fun competitionMatchesService(retrofit: Retrofit): CompetitionMatchesService =
    retrofit.create(CompetitionMatchesService::class.java)

private val interceptor = object: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain
            .request()
            .newBuilder()
            .addHeader("X-Auth-Token", AUTH_TOKEN)
        return chain.proceed(builder.build())
    }

}

