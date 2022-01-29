package ru.fefu.fitnes_tracker.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.fefu.fitnes_tracker.retrofit.LoginRepository
import ru.fefu.fitnes_tracker.retrofit.Result
import ru.fefu.fitnes_tracker.retrofit.remote.models.TokenUserModel

class LoginViewModel:ViewModel() {
    private val loginRepository = LoginRepository()

    private val _dataFlow = MutableSharedFlow<Result<TokenUserModel>>(replay = 0)

    val dataFlow get() = _dataFlow

    fun login(login:String, password:String) {
        viewModelScope.launch {
            loginRepository.login(login, password)
                .collect {
                    when(it) {
                        is Result.Success<*> -> _dataFlow.emit(it)
                        is Result.Error<*> -> _dataFlow.emit(it)
                    }
                }
        }
    }
}