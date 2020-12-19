package izumiharuka.aaccontributors.data

import izumiharuka.aaccontributors.data.source.ContributorsDataSource

class ContributorsRepository(
    private val dataSource: ContributorsDataSource
) {
    fun getContributors(repositoryId: Int): List<Contributors> =
        dataSource.getContributors(repositoryId)
}
