package izumiharuka.aaccontributors

import android.app.Application
import org.koin.android.ext.android.startKoin

class AACContributors : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            this, listOf(
                CoreModules, ContributorsModule
            )
        )
    }
}
