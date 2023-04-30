package com.example.financialtips.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class FinancialTip(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @StringRes val imageRef: Int,
    @DrawableRes val image: Int
)
