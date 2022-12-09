package com.yusuftalhaklc.depremtakp.utils

import android.app.Activity
import android.view.WindowManager
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.FragmentActivity

const val BASE_URL="https://api.orhanaydogdu.com.tr/"

fun FragmentActivity.changeStatusBarColor(color: Int, isLight: Boolean) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = color

    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isLight
}
