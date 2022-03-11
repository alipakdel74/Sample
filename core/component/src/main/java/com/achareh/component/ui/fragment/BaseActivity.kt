package com.achareh.component.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.achareh.component.util.LanguageHelper

abstract class BaseActivity<b : ViewBinding> : AppCompatActivity() {

    lateinit var binding: b

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LanguageHelper(base).updateBaseContextLocale())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LanguageHelper(this).updateResourcesLocaleLegacy()

        binding = setLayoutBinding()
        setContentView(binding.root)

        initView()
    }

    abstract fun setLayoutBinding(): b


    abstract fun initView()

}
