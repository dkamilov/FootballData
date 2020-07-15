package com.damir.android.myscore.utils.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.damir.android.myscore.Result

abstract class BaseViewModel : ViewModel() {

    protected val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading
    protected val _errorMessage = MutableLiveData<Result.Error?>()
    val errorMessage: LiveData<Result.Error?> = _errorMessage
}