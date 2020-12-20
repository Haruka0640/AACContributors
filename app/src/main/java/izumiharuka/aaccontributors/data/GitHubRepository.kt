package izumiharuka.aaccontributors.data

import izumiharuka.aaccontributors.data.source.GitHubDataSource

class GitHubRepository(
    private val dataSource: GitHubDataSource
) {
    suspend fun getRepositoryContributors(owner: String, repo: String): List<Account> =
        dataSource.getRepositoryContributors(owner, repo)

    suspend fun getAccountDetail(login: String): AccountDetail =
        dataSource.getAccountDetail(login)
}
