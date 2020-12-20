package izumiharuka.aaccontributors

import izumiharuka.aaccontributors.data.source.ContributorsDataSource
import izumiharuka.aaccontributors.data.source.remote.ContributorsApi
import izumiharuka.aaccontributors.data.source.remote.ContributorsRemoteDataSource
import retrofit2.Retrofit

object Injection {

    fun provideContributorsDataSource(retrofit: Retrofit): ContributorsDataSource =
        ContributorsRemoteDataSource(retrofit.create(ContributorsApi::class.java))
}
