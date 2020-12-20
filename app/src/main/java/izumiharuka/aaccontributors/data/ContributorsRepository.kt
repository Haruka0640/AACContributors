package izumiharuka.aaccontributors.data

import izumiharuka.aaccontributors.data.source.ContributorsDataSource

class ContributorsRepository(
    private val dataSource: ContributorsDataSource
) {
    suspend fun getContributors(owner: String, repo: String): List<Account> =
        dataSource.getContributors(owner, repo)

    suspend fun getRepository(owner: String, repo: String): Repository =
        dataSource.getRepository(owner, repo)
}
