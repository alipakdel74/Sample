package com.achareh.sample.ui.activity

import com.achareh.component.ui.activity.BaseActivity
import com.achareh.sample.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun setLayoutBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {

    }
}