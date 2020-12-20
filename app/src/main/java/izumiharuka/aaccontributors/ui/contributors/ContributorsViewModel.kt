package izumiharuka.aaccontributors.ui.contributors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import izumiharuka.aaccontributors.data.Account
import izumiharuka.aaccontributors.data.GitHubRepository
import kotlinx.coroutines.launch

class ContributorsViewModel(
    private val contributorsRepository: GitHubRepository
): ViewModel() {

    private val _activeRepo = MutableLiveData(Pair(DEFAULT_OWNER, DEFAULT_REPO))
    val activeRepo: LiveData<Pair<String, String>> = _activeRepo

    private val _contributors = MutableLiveData<Result<List<Account>>>()
    val contributors: LiveData<Result<List<Account>>> = _contributors

    private val _selectedContributor = MutableLiveData<Account>()
    val selectedContributor: LiveData<Account> = _selectedContributor

    fun getInfo(){
        getContributorsInfo()
        getRepositoryInfo()
    }

    fun getRepositoryInfo(){
        viewModelScope.launch {
            kotlin.runCatching {  }
        }
    }

    fun getContributorsInfo() {
        val owner = _activeRepo.value?.first ?: return
        val repo = _activeRepo.value?.second ?: return
        viewModelScope.launch {
            kotlin.runCatching {
                contributorsRepository.getContributors(owner, repo)
            }.let{
                _contributors.postValue(it)
            }
        }
    }

    fun select(contributor: Account){
        _selectedContributor.postValue(contributor)
    }

    companion object {
        const val DEFAULT_OWNER = "android"
        const val DEFAULT_REPO = "architecture-components-samples"
    }
}
