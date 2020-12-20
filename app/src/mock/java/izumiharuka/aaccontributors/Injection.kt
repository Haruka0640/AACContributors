package izumiharuka.aaccontributors

import izumiharuka.aaccontributors.data.Account
import izumiharuka.aaccontributors.data.AccountDetail
import izumiharuka.aaccontributors.data.source.GitHubDataSource
import izumiharuka.aaccontributors.data.source.remote.GitHubApi
import retrofit2.mock.MockRetrofit
import kotlin.random.Random

object Injection {

    fun provideGitHubDataSource(mockRetrofit: MockRetrofit) =
        object : GitHubDataSource {
            override suspend fun getRepositoryContributors(
                owner: String,
                repo: String
            ): List<Account> =
                mockRetrofit.create(GitHubApi::class.java).returningResponse(
                    List(randomInt(0..20)) {
                        makeRandomInstance(Account::class.java)?.copy(
                            avatarUrl = "https://loremflickr.com/320/240"
                        )
                    }
                ).getRepositoryContributors(owner, repo)

            override suspend fun getAccountDetail(login: String): AccountDetail =
                mockRetrofit.create(GitHubApi::class.java).returningResponse(
                    makeRandomInstance(AccountDetail::class.java)
                ).getAccountDetail(login)
        }


    private fun randomString(length: Int = 10): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    private fun randomInt(range: IntRange = 0..9999): Int = range.random()

    private fun randomBoolean(): Boolean = (0..1).random() == 0

    @Suppress("UNCHECKED_CAST")
    private fun <T> makeRandomInstance(type: Class<T>): T? =
        type.constructors.firstOrNull()?.let { constructor ->
            constructor.parameters.map { param ->
                when (param.type) {
                    String::class.java -> {
                        randomString()
                    }
                    Int::class.java -> {
                        randomInt()
                    }
                    Boolean::class.java -> {
                        randomBoolean()
                    }
                    else -> makeRandomInstance(param::class.java)
                }
            }.toTypedArray().let {
                constructor.newInstance(*it) as T
            }
        }
}
