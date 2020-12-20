package izumiharuka.aaccontributors.ui.contributors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import izumiharuka.aaccontributors.R
import izumiharuka.aaccontributors.databinding.FragmentContributorsBinding
import izumiharuka.aaccontributors.utils.autoCleared
import izumiharuka.aaccontributors.utils.showErrorMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A fragment representing a list of Items.
 */
class ContributorsFragment : Fragment() {

    private val viewModel: ContributorsViewModel by viewModel()

    private var adapter: ContributorsListAdapter by autoCleared()

    private var binding: FragmentContributorsBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_contributors, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentContributorsBinding.bind(view).apply {
            lifecycleOwner = this@ContributorsFragment
            viewModel = this@ContributorsFragment.viewModel
            list.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = ContributorsListAdapter {
                    findNavController().navigate(
                        ContributorsFragmentDirections
                            .actionContributorsFragmentToContributorDetailFragment(it)
                    )
                }.also {
                    this@ContributorsFragment.adapter = it
                }
            }
            fab.setOnClickListener {
                this@ContributorsFragment.viewModel.getRepositoryContributors()
            }
        }

        viewModel.contributors.observe(viewLifecycleOwner) { result ->
            result?.fold(
                onSuccess = { adapter.submitList(it) },
                onFailure = {
                    showErrorMessage(
                        view = binding.coordinator,
                        messageText = R.string.error_api_get_contributors_common
                    )
                    {
                        viewModel.getRepositoryContributors()
                    }
                }
            )
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.start()
    }
}
