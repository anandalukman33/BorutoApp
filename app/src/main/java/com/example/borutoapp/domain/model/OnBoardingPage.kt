package com.example.borutoapp.domain.model

import androidx.annotation.DrawableRes
import com.example.borutoapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First: OnBoardingPage(
        image = R.drawable.greetings,
        title = "Perkenalan",
        description = "Apa kamu termasuk fan Boruto? Jika benar ini merupakan kabar bagus untukmu!"
    )

    object Second: OnBoardingPage(
        image = R.drawable.explore,
        title = "Pencarian",
        description = "Kamu dapat mencari karakter di Boruto, dan ada keterangan yang lengkap disana yang disertai design yang keren!"
    )

    object Third: OnBoardingPage(
        image = R.drawable.power,
        title = "Kemampuan Karakter",
        description = "Dari sekian banyak karakter di Boruto disini menjelaskan masing-masing kekuatan pada setiap karakter di Anime Boruto!"
    )
}
