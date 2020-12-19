package izumiharuka.aaccontributors.data.source.remote

import izumiharuka.aaccontributors.data.Contributors
import izumiharuka.aaccontributors.data.source.ContributorsDataSource
import retrofit2.http.GET
import retrofit2.http.Path

interface ContributorsRemoteDataSource: ContributorsDataSource {

    @GET("repositories/{repositoryId}/contributors")
    override fun getContributors(
        @Path("repositoryId") repositoryId: Int
    ): List<Contributors>
}
