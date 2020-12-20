package izumiharuka.aaccontributors.data.source.remote

import izumiharuka.aaccontributors.data.Account
import izumiharuka.aaccontributors.data.Repository
import izumiharuka.aaccontributors.data.source.GitHubDataSource

class GitHubRemoteDataSource(
    private val api: GitHubApi
) : GitHubDataSource {
    override suspend fun getContributors(owner: String, repo: String): List<Account> =
        api.getContributors(owner, repo)

    override suspend fun getRepository(owner: String, repo: String): Repository =
        api.getRepository(owner, repo)
}
