package com.cxyzy.note.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.cxyzy.note.viewmodels.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    protected abstract fun viewModel(): VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        observeVM()
        prepareBeforeInitView()
        setToolbar()
        initView()
        initListeners()
        startObserve()
    }

    private fun setToolbar() {
        providerToolBar()?.let { setSupportActionBar(it) }
    }

    /**
     * 布局文件id
     */
    abstract fun layoutId(): Int

    open fun prepareBeforeInitView() {}
    open fun initView() {}
    open fun initListeners() {}
    open fun startObserve() {}

    private fun observeVM() {
        lifecycle.addObserver(viewModel())
    }

    /**
     *设置[Toolbar]
     */
    open fun providerToolBar(): Toolbar? = null


    override fun onDestroy() {
        viewModel().let {
            lifecycle.removeObserver(it)
        }

        super.onDestroy()
    }
}