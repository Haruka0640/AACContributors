package izumiharuka.aaccontributors.data.source

import izumiharuka.aaccontributors.data.Account
import izumiharuka.aaccontributors.data.AccountDetail
import izumiharuka.aaccontributors.data.Repository

interface GitHubDataSource {

    suspend fun getRepositoryContributors(owner: String, repo: String): List<Account>

    suspend fun getAccountDetail(login: String): AccountDetail
}
