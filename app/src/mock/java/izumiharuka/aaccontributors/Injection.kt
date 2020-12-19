package izumiharuka.aaccontributors

import com.squareup.moshi.Moshi
import izumiharuka.aaccontributors.data.Contributors
import izumiharuka.aaccontributors.data.listAdapter
import izumiharuka.aaccontributors.data.source.ContributorsDataSource
import retrofit2.mock.MockRetrofit

object Injection {

    fun provideContributorsDataSource(mockRetrofit: MockRetrofit, moshi: Moshi) =
        object : ContributorsDataSource {
            override fun getContributors(repositoryId: Int): List<Contributors> =
                mockRetrofit.create(ContributorsDataSource::class.java).returningResponse(
                    moshi.listAdapter(Contributors::class.java).fromJson(
                        "{\n" +
                            "    \"login\": \"ianhanniballake\",\n" +
                            "    \"id\": 517315,\n" +
                            "    \"node_id\": \"MDQ6VXNlcjUxNzMxNQ==\",\n" +
                            "    \"avatar_url\": \"https://avatars1.githubusercontent.com/u/517315?v=4\",\n" +
                            "    \"gravatar_id\": \"\",\n" +
                            "    \"url\": \"https://api.github.com/users/ianhanniballake\",\n" +
                            "    \"html_url\": \"https://github.com/ianhanniballake\",\n" +
                            "    \"followers_url\": \"https://api.github.com/users/ianhanniballake/followers\",\n" +
                            "    \"following_url\": \"https://api.github.com/users/ianhanniballake/following{/other_user}\",\n" +
                            "    \"gists_url\": \"https://api.github.com/users/ianhanniballake/gists{/gist_id}\",\n" +
                            "    \"starred_url\": \"https://api.github.com/users/ianhanniballake/starred{/owner}{/repo}\",\n" +
                            "    \"subscriptions_url\": \"https://api.github.com/users/ianhanniballake/subscriptions\",\n" +
                            "    \"organizations_url\": \"https://api.github.com/users/ianhanniballake/orgs\",\n" +
                            "    \"repos_url\": \"https://api.github.com/users/ianhanniballake/repos\",\n" +
                            "    \"events_url\": \"https://api.github.com/users/ianhanniballake/events{/privacy}\",\n" +
                            "    \"received_events_url\": \"https://api.github.com/users/ianhanniballake/received_events\",\n" +
                            "    \"type\": \"User\",\n" +
                            "    \"site_admin\": false,\n" +
                            "    \"contributions\": 118\n" +
                            "  },\n" +
                            "  {\n" +
                            "    \"login\": \"dlam\",\n" +
                            "    \"id\": 831038,\n" +
                            "    \"node_id\": \"MDQ6VXNlcjgzMTAzOA==\",\n" +
                            "    \"avatar_url\": \"https://avatars3.githubusercontent.com/u/831038?v=4\",\n" +
                            "    \"gravatar_id\": \"\",\n" +
                            "    \"url\": \"https://api.github.com/users/dlam\",\n" +
                            "    \"html_url\": \"https://github.com/dlam\",\n" +
                            "    \"followers_url\": \"https://api.github.com/users/dlam/followers\",\n" +
                            "    \"following_url\": \"https://api.github.com/users/dlam/following{/other_user}\",\n" +
                            "    \"gists_url\": \"https://api.github.com/users/dlam/gists{/gist_id}\",\n" +
                            "    \"starred_url\": \"https://api.github.com/users/dlam/starred{/owner}{/repo}\",\n" +
                            "    \"subscriptions_url\": \"https://api.github.com/users/dlam/subscriptions\",\n" +
                            "    \"organizations_url\": \"https://api.github.com/users/dlam/orgs\",\n" +
                            "    \"repos_url\": \"https://api.github.com/users/dlam/repos\",\n" +
                            "    \"events_url\": \"https://api.github.com/users/dlam/events{/privacy}\",\n" +
                            "    \"received_events_url\": \"https://api.github.com/users/dlam/received_events\",\n" +
                            "    \"type\": \"User\",\n" +
                            "    \"site_admin\": false,\n" +
                            "    \"contributions\": 74\n" +
                            "  },"
                    )
                ).getContributors(repositoryId)
        }
}
