package izumiharuka.aaccontributors

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import izumiharuka.aaccontributors.data.ContributorsRepository
import izumiharuka.aaccontributors.ui.contributors.ContributorsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.mock.MockRetrofit
import java.util.concurrent.TimeUnit

val Modules = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Environment.Api.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
    }

    single<MockRetrofit> {
        MockRetrofit.Builder(get()).build()
    }

    single<OkHttpClient> {
        OkHttpClient
            .Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single<Moshi> {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single { Injection.provideContributorsDataSource(get(), get()) }

    single { ContributorsRepository(get()) }

    viewModel { ContributorsViewModel(get()) }
}
