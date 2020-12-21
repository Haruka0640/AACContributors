package izumiharuka.aaccontributors.utils

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.google.android.material.snackbar.Snackbar
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import izumiharuka.aaccontributors.R


fun <T> Moshi.listAdapter(type: Class<T>): JsonAdapter<List<T>> = adapter(
    Types.newParameterizedType(List::class.java, type::class.java)
)

fun Fragment.showErrorMessage(
    @StringRes
    messageText: Int = R.string.error_api_common,
    rootView: View? = view?.rootView,
    @StringRes
    actionText: Int? = null,
    action: (View) -> Unit = {}
) {
    rootView?.let{
        with(Snackbar.make(it, messageText, Snackbar.LENGTH_INDEFINITE)){
            actionText?.let{ setAction(actionText, action) }
            show()
        }
    }
}

fun NavController.navigateSafe(direction: NavDirections) {
    kotlin.runCatching {
        navigate(direction)
    }.onFailure {
        Log.i("info", "navigation skipped due to: ", it)
    }
}

fun Fragment.launchUri(uri: String) {
    kotlin.runCatching {
        CustomTabsIntent
            .Builder()
            .build()
            .launchUrl(requireContext(), Uri.parse(uri))
    }.onFailure{
        showErrorMessage(
            messageText = R.string.error_open_twitter
        )
    }
}

fun Fragment.launchMail(
    vararg addresses: String,
    subject: String = "",
    text: String = "",
    onFailure: (Throwable) -> Unit = {}
) {
    kotlin.runCatching {
        with(Intent(Intent.ACTION_SENDTO)) {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, text)
            startActivity(this)
        }
    }.onFailure(onFailure)
}

fun PackageManager.isAppInstalled(packageName: String) =
    kotlin.runCatching {
        getPackageInfo(packageName, 0)
    }.isSuccess
