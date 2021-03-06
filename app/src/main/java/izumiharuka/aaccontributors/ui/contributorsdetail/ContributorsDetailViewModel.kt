package izumiharuka.aaccontributors.ui.contributorsdetail

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

class ContributorsDetailViewModel(
    private val gitHubRepository: GitHubRepository
): ViewModel() {

    private val _accountDetail = MutableLiveData<Result<AccountDetail>>()
    val accountDetail: LiveData<Result<AccountDetail>> = _accountDetail

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading : LiveData<Boolean> = _isLoading

    fun getAccountDetail(login: String){
        viewModelScope.launch {
            _isLoading.postValue(true)
            kotlin.runCatching {
                gitHubRepository.getAccountDetail(login)
            }.let{
                _accountDetail.postValue(it)
                _isLoading.postValue(false)
            }
        }
    }
}
