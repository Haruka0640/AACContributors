package izumiharuka.aaccontributors.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import izumiharuka.aaccontributors.R

class ContributorDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ContributorDetailFragment()
    }

    private lateinit var viewModel: ContributorDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contributor_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContributorDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
