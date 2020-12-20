package izumiharuka.aaccontributors.data.source

import izumiharuka.aaccontributors.data.Contributor

interface ContributorsDataSource {

    suspend fun getContributors(owner: String, repo: String): List<Contributor>

}
