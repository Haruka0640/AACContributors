package izumiharuka.aaccontributors.data.source

import izumiharuka.aaccontributors.data.Account
import izumiharuka.aaccontributors.data.Repository

interface GitHubDataSource {

    suspend fun getContributors(owner: String, repo: String): List<Account>

    suspend fun getRepository(owner: String, repo:String): Repository

}
