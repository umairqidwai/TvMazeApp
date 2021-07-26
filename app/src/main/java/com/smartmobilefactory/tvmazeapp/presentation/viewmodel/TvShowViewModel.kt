package com.smartmobilefactory.tvmazeapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartmobilefactory.tvmazeapp.domain.interactors.ShowSearchUseCase
import com.smartmobilefactory.tvmazeapp.domain.model.TvShowResponseItem
import kotlinx.coroutines.*
import javax.inject.Inject

class TvShowViewModel @Inject constructor(val showSearchUseCase: ShowSearchUseCase) :
    ViewModel() {
    var job: Job? = null
    val tvshowList = MutableLiveData<ArrayList<TvShowResponseItem>>()
    val tvShowSearchError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun searchTvShow(search: String) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = showSearchUseCase.run(ShowSearchUseCase.Params(search))
            withContext(Dispatchers.Main) {
                if (response.isSuccess) {
                    tvshowList.value = response.data as ArrayList<TvShowResponseItem>
                    tvShowSearchError.value = null
                    loading.value = false
                } else {
                    onError(response.errMsg)
                }

            }
        }
    }


    private fun onError(message: String) {
        CoroutineScope(Dispatchers.Main).launch {
            tvShowSearchError.value = message
            loading.value = false

        }

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}