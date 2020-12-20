package izumiharuka.aaccontributors.data.source.remote

import izumiharuka.aaccontributors.data.Contributor
import izumiharuka.aaccontributors.data.source.ContributorsDataSource

class ContributorsRemoteDataSource(
    private val api: ContributorsApi
) : ContributorsDataSource {
    override suspend fun getContributors(owner: String, repo: String): List<Contributor> =
        api.getContributors(owner, repo)
}
