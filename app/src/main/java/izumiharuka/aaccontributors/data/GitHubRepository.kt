package izumiharuka.aaccontributors.data

import izumiharuka.aaccontributors.data.source.GitHubDataSource

class GitHubRepository(
    private val dataSource: GitHubDataSource
) {
    suspend fun getContributors(owner: String, repo: String): List<Account> =
        dataSource.getContributors(owner, repo)

    suspend fun getRepository(owner: String, repo: String): Repository =
        dataSource.getRepository(owner, repo)
}
