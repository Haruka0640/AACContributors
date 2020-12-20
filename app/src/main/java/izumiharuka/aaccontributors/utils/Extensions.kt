package izumiharuka.aaccontributors.utils

import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import izumiharuka.aaccontributors.R

fun <T> Moshi.listAdapter(type: Class<T>): JsonAdapter<List<T>> = adapter(
    Types.newParameterizedType(List::class.java, type::class.java)
)

fun Fragment.showErrorMessage(
    e: Throwable,
    @StringRes
    messageText: Int = R.string.error_api_common,
    @StringRes
    actionText: Int = R.string.retry,
    action: (View) -> Unit
) {
    view?.let {
        Snackbar.make(
            it.rootView,
            messageText,
            Snackbar.LENGTH_LONG
        ).setAction(
            actionText, action
        ).show()
    }
}
