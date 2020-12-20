package izumiharuka.aaccontributors.ui.contributorsdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import izumiharuka.aaccontributors.R
import izumiharuka.aaccontributors.databinding.FragmentContributorDetailBinding
import izumiharuka.aaccontributors.utils.autoCleared
import izumiharuka.aaccontributors.utils.showErrorMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContributorDetailFragment : BottomSheetDialogFragment() {

    private val viewModel: ContributorsDetailViewModel by viewModel()

    private var binding: FragmentContributorDetailBinding by autoCleared()

    private val args: ContributorDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_contributor_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentContributorDetailBinding.bind(view).apply {
            lifecycleOwner = this@ContributorDetailFragment
        }

        viewModel.accountDetail.observe(viewLifecycleOwner) { result ->
            result?.fold(
                onSuccess = { binding.contributor = it },
                onFailure = {
                    showErrorMessage(it) { viewModel.getAccountDetail(args.contributor.login) }
                }
            )

        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAccountDetail(args.contributor.login)
    }


}
