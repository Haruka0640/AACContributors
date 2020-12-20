package izumiharuka.aaccontributors.data

import izumiharuka.aaccontributors.data.source.ContributorsDataSource

class ContributorsRepository(
    private val dataSource: ContributorsDataSource
) {
    suspend fun getContributors(repositoryId: Int): List<Contributor> =
        dataSource.getContributors(repositoryId)
}
