package izumiharuka.aaccontributors.ui.contributors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import izumiharuka.aaccontributors.R
import izumiharuka.aaccontributors.databinding.FragmentContributorsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A fragment representing a list of Items.
 */
class ContributorsFragment : Fragment() {

    private val viewModel: ContributorsViewModel by viewModel()

    private lateinit var adapter: ContributorsListAdapter

    private lateinit var binding: FragmentContributorsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_contributors, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ContributorsListAdapter()

        binding = FragmentContributorsBinding.bind(view).apply {
            lifecycleOwner = this@ContributorsFragment
            list.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = this@ContributorsFragment.adapter
            }
        }

        viewModel.contributors.observe(viewLifecycleOwner) { result ->
            result?.fold(
                onSuccess = { adapter.submitList(it) },
                onFailure = { TODO() }
            )
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getContributors(DEFAULT_REPOSITORY_ID)
    }

    companion object {
        const val DEFAULT_REPOSITORY_ID = 90792131
    }
}
