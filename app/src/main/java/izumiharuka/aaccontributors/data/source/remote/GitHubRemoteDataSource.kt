package izumiharuka.aaccontributors.data.source.remote

import izumiharuka.aaccontributors.data.Account
import izumiharuka.aaccontributors.data.Repository
import izumiharuka.aaccontributors.data.source.GitHubDataSource

class GitHubRemoteDataSource(
    private val api: GitHubApi
) : GitHubDataSource {
    override suspend fun getRepositoryContributors(owner: String, repo: String): List<Account> =
        api.getRepositoryContributors(owner, repo)

    override suspend fun getRepository(owner: String, repo: String): Repository =
        api.getRepository(owner, repo)
}
