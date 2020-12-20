package izumiharuka.aaccontributors.ui.contributorsdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import izumiharuka.aaccontributors.R
import izumiharuka.aaccontributors.databinding.FragmentContributorDetailBinding
import izumiharuka.aaccontributors.ui.contributors.ContributorsViewModel
import izumiharuka.aaccontributors.utils.autoCleared
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ContributorDetailFragment : Fragment() {

    private val viewModel: ContributorsViewModel by sharedViewModel()

    private var binding: FragmentContributorDetailBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_contributor_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentContributorDetailBinding.bind(view).apply {
            lifecycleOwner = this@ContributorDetailFragment
        }

        viewModel.selectedContributor.observe(viewLifecycleOwner) {
            binding.contributor = it
        }
    }


}
