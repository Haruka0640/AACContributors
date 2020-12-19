package izumiharuka.aaccontributors.data

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter

fun <T> Moshi.listAdapter(type: Class<T>): JsonAdapter<List<T>> = adapter(
    Types.newParameterizedType(List::class.java,type::class.java)
)
