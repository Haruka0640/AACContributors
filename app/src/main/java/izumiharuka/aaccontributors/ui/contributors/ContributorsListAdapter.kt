package izumiharuka.aaccontributors.ui.contributors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import izumiharuka.aaccontributors.data.Contributor
import izumiharuka.aaccontributors.databinding.ViewItemContributorsBinding

class ContributorsListAdapter(
    private val onItemClickListener: (Contributor) -> Unit
) : ListAdapter<Contributor, ContributorsListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Contributor>() {
        override fun areItemsTheSame(
            oldItem: Contributor,
            newItem: Contributor
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Contributor,
            newItem: Contributor
        ): Boolean = oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewItemContributorsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ViewItemContributorsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contributor: Contributor) {
            binding.contributor = contributor
            binding.root.setOnClickListener { onItemClickListener.invoke(contributor) }
        }
    }
}
