package de.skash.ticketing.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.skash.ticketing.BuildConfig
import de.skash.ticketing.core.repository.ApiTicketRepository
import de.skash.ticketing.core.repository.ApiUserRepository
import de.skash.ticketing.core.repository.TicketRepository
import de.skash.ticketing.core.repository.UserRepository
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(createHttpClient())
        .baseUrl(BuildConfig.BASE_URL)
        .build()

    @Singleton
    @Provides
    fun provideTicketRepository(): TicketRepository {
        return ApiTicketRepository()
    }

    @Singleton
    @Provides
    fun provideUserRepository(): UserRepository {
        return ApiUserRepository()
    }

    private fun createHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }
}
