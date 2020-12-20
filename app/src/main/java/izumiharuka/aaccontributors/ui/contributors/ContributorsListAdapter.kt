package izumiharuka.aaccontributors.ui.contributors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import izumiharuka.aaccontributors.data.Account
import izumiharuka.aaccontributors.databinding.ViewItemContributorsBinding

class ContributorsListAdapter(
    private val onItemClickListener: (Account) -> Unit
) : ListAdapter<Account, ContributorsListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Account>() {
        override fun areItemsTheSame(
            oldItem: Account,
            newItem: Account
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Account,
            newItem: Account
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
        fun bind(contributor: Account) {
            binding.apply {
                this.contributor = contributor
                root.setOnClickListener { onItemClickListener.invoke(contributor) }
                Glide.with(root.context).load(contributor.avatarUrl).into(imageAvatar)
            }
        }
    }
}
