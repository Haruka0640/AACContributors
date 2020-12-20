package izumiharuka.aaccontributors.data.source.remote

import izumiharuka.aaccontributors.data.Contributor
import izumiharuka.aaccontributors.data.source.ContributorsDataSource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ContributorsApi{

    @GET("repositories/{repositoryId}/contributors")
    suspend fun getContributors(
        @Path("repositoryId") repositoryId: Int
    ): List<Contributor>
}
