package izumiharuka.aaccontributors

import izumiharuka.aaccontributors.data.Contributor
import izumiharuka.aaccontributors.data.source.ContributorsDataSource
import izumiharuka.aaccontributors.data.source.remote.ContributorsApi
import retrofit2.mock.MockRetrofit

object Injection {

    fun provideContributorsDataSource(mockRetrofit: MockRetrofit) =
        object : ContributorsDataSource {
            override suspend fun getContributors(owner: String, repo: String): List<Contributor> =
                mockRetrofit.create(ContributorsApi::class.java).returningResponse(
                    listOf(
                        Contributor(
                            login = "ianhanniballake",
                            id = 517315,
                            nodeId = "MDQ6VXNlcjUxNzMxNQ==",
                            avatarUrl = "https://avatars1.githubusercontent.com/u/517315?v=4",
                            gravatarId = "",
                            url = "https://api.github.com/users/ianhanniballake",
                            htmlUrl = "https://github.com/ianhanniballake",
                            followersUrl = "https://api.github.com/users/ianhanniballake/followers",
                            followingUrl = "https://api.github.com/users/ianhanniballake/following{/otherUser}",
                            gistsUrl = "https://api.github.com/users/ianhanniballake/gists{/gist_id}",
                            starredUrl = "https://api.github.com/users/ianhanniballake/starred{/owner}{/repo}",
                            subscriptionsUrl = "https://api.github.com/users/ianhanniballake/subscriptions",
                            organizationsUrl = "https://api.github.com/users/ianhanniballake/orgs",
                            reposUrl = "https://api.github.com/users/ianhanniballake/repos",
                            eventsUrl = "https://api.github.com/users/ianhanniballake/events{/privacy}",
                            receivedEventsUrl = "https://api.github.com/users/ianhanniballake/received_events",
                            type = "User",
                            siteAdmin= false,
                            contributions = 118
                        ),
                        Contributor(
                            login = "ianhanniballake",
                            id = 517315,
                            nodeId = "MDQ6VXNlcjUxNzMxNQ==",
                            avatarUrl = "https://avatars1.githubusercontent.com/u/517315?v=4",
                            gravatarId = "",
                            url = "https://api.github.com/users/ianhanniballake",
                            htmlUrl = "https://github.com/ianhanniballake",
                            followersUrl = "https://api.github.com/users/ianhanniballake/followers",
                            followingUrl = "https://api.github.com/users/ianhanniballake/following{/otherUser}",
                            gistsUrl = "https://api.github.com/users/ianhanniballake/gists{/gist_id}",
                            starredUrl = "https://api.github.com/users/ianhanniballake/starred{/owner}{/repo}",
                            subscriptionsUrl = "https://api.github.com/users/ianhanniballake/subscriptions",
                            organizationsUrl = "https://api.github.com/users/ianhanniballake/orgs",
                            reposUrl = "https://api.github.com/users/ianhanniballake/repos",
                            eventsUrl = "https://api.github.com/users/ianhanniballake/events{/privacy}",
                            receivedEventsUrl = "https://api.github.com/users/ianhanniballake/received_events",
                            type = "User",
                            siteAdmin= false,
                            contributions = 118
                        )
                    )
                ).getContributors(owner, repo)
        }
}
