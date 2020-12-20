package izumiharuka.aaccontributors.data

import izumiharuka.aaccontributors.data.source.ContributorsDataSource

class ContributorsRepository(
    private val dataSource: ContributorsDataSource
) {
    suspend fun getContributors(owner: String, repo: String): List<Contributor> =
        dataSource.getContributors(owner, repo)
}
