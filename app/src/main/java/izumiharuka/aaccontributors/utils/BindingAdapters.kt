package izumiharuka.aaccontributors.utils

import android.view.View
import androidx.databinding.BindingAdapter

@set:BindingAdapter("visibleOrGone")
var View.visibleOrGone
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }
