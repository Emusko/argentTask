package com.example.argent.di.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.argent.di.factory.ViewModelProviderFactory
import com.example.argent.utils.SharedPreferenceService
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject


abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : DaggerFragment() {

    @Inject
    lateinit var sharedPreferenceService: SharedPreferenceService

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var fragmentBinding: T
    lateinit var fragmentViewModel: V

    lateinit var fragmentContext: Context
    protected val subscription = CompositeDisposable()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        fragmentViewModel = getViewModel()
        return fragmentBinding.root
    }

    override fun onResume() {
        super.onResume()
        fragmentViewModel.onError.subscribe {
            showMessage(it)
        }.addTo(subscription)
    }

    override fun onDetach() {
        super.onDetach()
        subscription.clear()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    fun getBinding(): T {
        return fragmentBinding
    }

    fun showMessage(text:String){
        Snackbar.make(
            fragmentBinding.root,
            text,
            Snackbar.LENGTH_SHORT
        ).show()
    }

}