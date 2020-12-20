package izumiharuka.aaccontributors.ui.contributors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN
import izumiharuka.aaccontributors.R
import izumiharuka.aaccontributors.databinding.FragmentContributorsBinding
import izumiharuka.aaccontributors.utils.autoCleared
import izumiharuka.aaccontributors.utils.showErrorMessage
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A fragment representing a list of Items.
 */
class ContributorsFragment : Fragment() {

    private val viewModel: ContributorsViewModel by sharedViewModel()

    private var adapter: ContributorsListAdapter by autoCleared()

    private var binding: FragmentContributorsBinding by autoCleared()

    private var bottomSheetBehavior: BottomSheetBehavior<FragmentContainerView> by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_contributors, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentContributorsBinding.bind(view).apply {
            lifecycleOwner = this@ContributorsFragment
            BottomSheetBehavior.from(containerBottomSheet).apply {
                state = STATE_HIDDEN
            }.also {
                this@ContributorsFragment.bottomSheetBehavior = it
            }
            list.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = ContributorsListAdapter {
                    viewModel.selectContributor(it)
                }.also {
                    this@ContributorsFragment.adapter = it
                }
            }
        }

        viewModel.contributors.observe(viewLifecycleOwner) { result ->
            result?.fold(
                onSuccess = { adapter.submitList(it) },
                onFailure = {
                    showErrorMessage(
                        it,
                        messageText = R.string.error_api_get_contributors_common
                    )
                    {
                        viewModel.getRepositoryContributors()
                    }
                }
            )
        }

        viewModel.repository.observe(viewLifecycleOwner) { result ->
            result?.fold(
                onSuccess = { },
                onFailure = {
                    showErrorMessage(
                        it,
                        messageText = R.string.error_api_get_repository_common
                    ) {
                        viewModel.getRepositoryInfo()
                    }
                }
            )
        }

        viewModel.selectedContributor.observe(viewLifecycleOwner) {
            bottomSheetBehavior.state = STATE_COLLAPSED
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getInfo()
    }
}
