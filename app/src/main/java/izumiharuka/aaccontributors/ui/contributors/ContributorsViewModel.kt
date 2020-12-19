package izumiharuka.aaccontributors.ui.contributors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import izumiharuka.aaccontributors.data.Contributors
import izumiharuka.aaccontributors.data.ContributorsRepository

class ContributorsViewModel(
    private val contributorsRepository: ContributorsRepository
): ViewModel() {

    private val _contributors = MutableLiveData<List<Contributors>>()
    val contributors: LiveData<List<Contributors>> = _contributors

    fun getContributors(repositoryId: Int) {
        _contributors.value = contributorsRepository.getContributors(repositoryId)
    }
}
