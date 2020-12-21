package izumiharuka.aaccontributors.ui.contributorsdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import izumiharuka.aaccontributors.R
import izumiharuka.aaccontributors.databinding.FragmentContributorDetailBinding
import izumiharuka.aaccontributors.utils.*
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
            viewModel = this@ContributorDetailFragment.viewModel
            Glide.with(requireContext()).load(args.contributor.avatarUrl).into(imageAvatar)
        }

        viewModel.accountDetail.observe(viewLifecycleOwner) { result ->
            result?.fold(
                onSuccess = { account ->
                    binding.apply {
                        contributor = account
                        buttonGitHub.setOnClickListener {
                            launchUri(account.htmlUrl)
                        }
                        buttonMail.setOnClickListener {
                            account.email?.let { mail -> launchMail(mail) }
                        }
                        buttonTwitter.setOnClickListener {
                            if (requireContext().packageManager.isAppInstalled("com.twitter.android")) {
                                kotlin.runCatching {
                                    val uri = Uri.parse("twitter://user?user_id=$it")
                                    with(Intent(Intent.ACTION_VIEW, uri)){
                                        startActivity(this)
                                    }
                                }.onFailure {
                                    showErrorMessage(
                                        messageText = R.string.error_open_twitter
                                    )
                                }
                            } else {
                                launchUri("https://twitter.com/$it")
                            }
                        }
                    }
                },
                onFailure = {
                    showErrorMessage(
                        actionText = R.string.retry,
                        action = {
                            viewModel.getAccountDetail(args.contributor.login)
                        }
                    )
                }
            )

        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAccountDetail(args.contributor.login)
    }


}
