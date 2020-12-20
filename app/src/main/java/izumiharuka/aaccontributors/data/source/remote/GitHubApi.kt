package izumiharuka.aaccontributors.data.source.remote

import izumiharuka.aaccontributors.data.Account
import izumiharuka.aaccontributors.data.Repository
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi{

    @GET("/repos/{owner}/{repo}/contributors")
    suspend fun getRepositoryContributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<Account>

    @GET("/repos/{owner}/{repo}")
    suspend fun getRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Repository
}
