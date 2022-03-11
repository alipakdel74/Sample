package com.achareh.component.util

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import java.util.*

class LanguageHelper(private val context: Context) {

    private val getCurrentLanguage = "fa"

    fun updateBaseContextLocale(): Context {
        val locale = Locale(getCurrentLanguage)
        Locale.setDefault(locale)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResourcesLocale()
        } else context
    }

    /**
     * Change locale for API 24 and above.
     * */
    private fun updateResourcesLocale(): Context {
        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(Locale(getCurrentLanguage))
        return context.createConfigurationContext(configuration)
    }

    /**
     * Change locale for API 23 and below.
     * */
    fun updateResourcesLocaleLegacy(): Context {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) return context

        val resources: Resources = context.resources
        val configuration: Configuration = resources.configuration
        configuration.setLocale(Locale(getCurrentLanguage))
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }

}
