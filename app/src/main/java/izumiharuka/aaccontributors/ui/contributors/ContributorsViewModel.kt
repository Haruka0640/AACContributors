package izumiharuka.aaccontributors.ui.contributors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import izumiharuka.aaccontributors.data.Contributor
import izumiharuka.aaccontributors.data.ContributorsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContributorsViewModel(
    private val contributorsRepository: ContributorsRepository
): ViewModel() {

    private val _contributors = MutableLiveData<Result<List<Contributor>>>()
    val contributors: LiveData<Result<List<Contributor>>> = _contributors

    private val _selectedContributor = MutableLiveData<Contributor>()
    val selectedContributor: LiveData<Contributor> = _selectedContributor

    fun getContributors(repositoryId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                contributorsRepository.getContributors(repositoryId)
            }.let{
                _contributors.postValue(it)
            }
        }
    }

    fun select(contributor: Contributor){
        _selectedContributor.postValue(contributor)
    }
}
