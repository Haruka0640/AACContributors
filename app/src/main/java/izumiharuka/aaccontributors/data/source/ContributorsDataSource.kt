package izumiharuka.aaccontributors.data.source

import izumiharuka.aaccontributors.data.Contributors

interface ContributorsDataSource {

    fun getContributors(): List<Contributors>

}
