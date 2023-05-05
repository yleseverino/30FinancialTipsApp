package com.example.financialtips.model

import com.example.financialtips.R

object FinacialRepository {
    val financialTips = listOf<FinancialTip>(
        FinancialTip(
            title = R.string.financial_tip_headline_1,
            description = R.string.financial_tip_desc_1,
            image = R.drawable.financial_tip_image_1,
            imageRef = R.string.financial_tip_image_ref_1
        ),
        FinancialTip(
            title = R.string.financial_tip_headline_2,
            description = R.string.financial_tip_desc_2,
            image = R.drawable.invest,
            imageRef = R.string.financial_tip_image_ref_2
        ),
        FinancialTip(
            title = R.string.financial_tip_headline_3,
            description = R.string.financial_tip_desc_3,
            image = R.drawable.emergency,
            imageRef = R.string.financial_tip_image_ref_3
        ),
        FinancialTip(
            title = R.string.financial_tip_headline_4,
            description = R.string.financial_tip_desc_4,
            image = R.drawable.efficient,
            imageRef = R.string.financial_tip_image_ref_4
        ),
        FinancialTip(
            title = R.string.financial_tip_headline_5,
            description = R.string.financial_tip_desc_5,
            image = R.drawable.cook,
            imageRef = R.string.financial_tip_image_ref_5
        ),
        FinancialTip(
            title = R.string.financial_tip_headline_6,
            description = R.string.financial_tip_desc_6,
            image = R.drawable.fix,
            imageRef = R.string.financial_tip_image_ref_6
        ),
        FinancialTip(
            title = R.string.financial_tip_headline_7,
            description = R.string.financial_tip_desc_7,
            image = R.drawable.extra_money,
            imageRef = R.string.financial_tip_image_ref_7
        ),
        FinancialTip(
            title = R.string.financial_tip_headline_8,
            description = R.string.financial_tip_desc_8,
            image = R.drawable.graduation,
            imageRef = R.string.financial_tip_image_ref_8
        ),
        FinancialTip(
            title = R.string.financial_tip_headline_9,
            description = R.string.financial_tip_desc_9,
            image = R.drawable.meditation,
            imageRef = R.string.financial_tip_image_ref_9
        ),
    )
}