package izumiharuka.aaccontributors.ui.contributors

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import izumiharuka.aaccontributors.R
import izumiharuka.aaccontributors.data.Contributors

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ContributorsListAdapter() : ListAdapter<Contributors, ContributorsListAdapter.ViewHolder>(
    object: DiffUtil.ItemCallback<Contributors>() {
        override fun areItemsTheSame(
            oldItem: Contributors,
            newItem: Contributors
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Contributors,
            newItem: Contributors
        ): Boolean = oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_contributors, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}
