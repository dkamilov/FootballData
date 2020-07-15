package com.damir.android.myscore.utils.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.damir.android.myscore.R
import com.damir.android.myscore.Result
import com.damir.android.myscore.utils.extensions.showSnackbar

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setBinding(inflater, container)
        return binding.root
    }

    abstract fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): T

    protected fun showErrorMessage(error: Result.Error?) {
        when(error) {
            is Result.Error.HttpError -> { showSnackbar(R.string.error_http) }
            is Result.Error.NetworkError -> { showSnackbar(R.string.error_network) }
        }
    }
}