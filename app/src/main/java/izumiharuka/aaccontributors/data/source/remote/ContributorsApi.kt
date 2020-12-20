package izumiharuka.aaccontributors.data.source.remote

import izumiharuka.aaccontributors.data.Contributor
import izumiharuka.aaccontributors.data.source.ContributorsDataSource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ContributorsApi{

    @GET("/repos/{owner}/{repo}/contributors")
    suspend fun getContributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<Contributor>
}
