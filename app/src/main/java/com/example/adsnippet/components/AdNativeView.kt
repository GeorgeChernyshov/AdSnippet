package com.example.adsnippet.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.adsnippet.databinding.ViewNativeAdBinding

class AdNativeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    val binding = ViewNativeAdBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )
}