package izumiharuka.aaccontributors.ui.contributors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import izumiharuka.aaccontributors.data.Account
import izumiharuka.aaccontributors.data.AccountDetail
import izumiharuka.aaccontributors.data.GitHubRepository
import izumiharuka.aaccontributors.data.Repository
import izumiharuka.aaccontributors.utils.Event
import kotlinx.coroutines.launch

class ContributorsViewModel(
    private val gitHubRepository: GitHubRepository
): ViewModel() {

    private val _activeRepo = MutableLiveData(Pair(DEFAULT_OWNER, DEFAULT_REPO))
    val activeRepo: LiveData<Pair<String, String>> = _activeRepo

    private val _contributors = MutableLiveData<Result<List<Account>>>()
    val contributors: LiveData<Result<List<Account>>> = _contributors

    fun getInfo(){
        getRepositoryContributors()
    }

    fun getRepositoryContributors() {
        val owner = _activeRepo.value?.first ?: return
        val repo = _activeRepo.value?.second ?: return
        viewModelScope.launch {
            kotlin.runCatching {
                gitHubRepository.getRepositoryContributors(owner, repo)
            }.let{
                _contributors.postValue(it)
            }
        }
    }

    companion object {
        const val DEFAULT_OWNER = "android"
        const val DEFAULT_REPO = "architecture-components-samples"
    }
}
