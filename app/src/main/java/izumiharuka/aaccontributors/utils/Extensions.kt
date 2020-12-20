package izumiharuka.aaccontributors.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

fun <T> Moshi.listAdapter(type: Class<T>): JsonAdapter<List<T>> = adapter(
    Types.newParameterizedType(List::class.java, type::class.java)
)
