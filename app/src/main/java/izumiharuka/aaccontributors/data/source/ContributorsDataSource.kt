package izumiharuka.aaccontributors.data.source

import izumiharuka.aaccontributors.data.Contributors
import retrofit2.Retrofit

interface ContributorsDataSource {

    fun getContributors(repositoryId: Int): List<Contributors>

}
