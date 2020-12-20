package izumiharuka.aaccontributors

import izumiharuka.aaccontributors.data.source.GitHubDataSource
import izumiharuka.aaccontributors.data.source.remote.GitHubApi
import izumiharuka.aaccontributors.data.source.remote.GitHubRemoteDataSource
import retrofit2.Retrofit

object Injection {

    fun provideContributorsDataSource(retrofit: Retrofit): GitHubDataSource =
        GitHubRemoteDataSource(retrofit.create(GitHubApi::class.java))
}
