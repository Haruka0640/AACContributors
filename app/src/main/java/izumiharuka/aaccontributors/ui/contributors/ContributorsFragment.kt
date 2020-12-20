package izumiharuka.aaccontributors.ui.contributors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import izumiharuka.aaccontributors.R
import izumiharuka.aaccontributors.databinding.FragmentContributorsBinding
import izumiharuka.aaccontributors.utils.autoCleared
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
            this@ContributorsFragment.bottomSheetBehavior =
                BottomSheetBehavior.from(containerBottomSheet)
            list.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = ContributorsListAdapter {
                    viewModel.select(it)
                }.also {
                    this@ContributorsFragment.adapter = it
                }
            }
        }

        viewModel.contributors.observe(viewLifecycleOwner) { result ->
            result?.fold(
                onSuccess = { adapter.submitList(it) },
                onFailure = { TODO() }
            )
        }

        viewModel.selectedContributor.observe(viewLifecycleOwner) {
            bottomSheetBehavior.state = STATE_EXPANDED
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
