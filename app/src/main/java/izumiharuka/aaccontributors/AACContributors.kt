package izumiharuka.aaccontributors

import android.app.Application
import izumiharuka.aaccontributors.data.DataSourceModule
import org.koin.android.ext.android.startKoin

class AACContributors : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            this, listOf(
                DataSourceModule
            )
        )
    }
}
