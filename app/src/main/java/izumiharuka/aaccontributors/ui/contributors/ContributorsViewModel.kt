package izumiharuka.aaccontributors.ui.contributors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import izumiharuka.aaccontributors.data.Contributor
import izumiharuka.aaccontributors.data.ContributorsRepository
import kotlinx.coroutines.launch

class ContributorsViewModel(
    private val contributorsRepository: ContributorsRepository
): ViewModel() {

    private val _activeRepo = MutableLiveData(Pair(DEFAULT_OWNER, DEFAULT_REPO))
    val activeRepo: LiveData<Pair<String, String>> = _activeRepo

    private val _contributors = MutableLiveData<Result<List<Contributor>>>()
    val contributors: LiveData<Result<List<Contributor>>> = _contributors

    private val _selectedContributor = MutableLiveData<Contributor>()
    val selectedContributor: LiveData<Contributor> = _selectedContributor

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

    fun select(contributor: Contributor){
        _selectedContributor.postValue(contributor)
    }

    companion object {
        const val DEFAULT_OWNER = "android"
        const val DEFAULT_REPO = "architecture-components-samples"
    }
}
