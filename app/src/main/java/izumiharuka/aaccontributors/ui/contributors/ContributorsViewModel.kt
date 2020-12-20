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

    private val _owner = MutableLiveData<String>()
    val owner: LiveData<String> = _owner

    private val _repo = MutableLiveData<String>()
    val repo: LiveData<String> = _repo

    private val _contributors = MutableLiveData<Result<List<Account>>>()
    val contributors: LiveData<Result<List<Account>>> = _contributors
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading : LiveData<Boolean> = _isLoading

    fun start(){
        _owner.value = DEFAULT_OWNER
        _repo.value = DEFAULT_REPO
        getRepositoryContributors()
    }

    fun getRepositoryContributors() {
        val owner = _owner.value ?: return
        val repo = _repo.value ?: return
        viewModelScope.launch {
            _isLoading.postValue(true)
            kotlin.runCatching {
                gitHubRepository.getRepositoryContributors(owner, repo)
            }.let{
                _contributors.postValue(it)
                _isLoading.postValue(false)
            }
        }
    }
    companion object {
        const val DEFAULT_OWNER = "android"
        const val DEFAULT_REPO = "architecture-components-samples"
    }
}
