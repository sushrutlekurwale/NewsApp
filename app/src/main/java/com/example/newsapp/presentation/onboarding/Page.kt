package com.example.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)

val pages = listOf(
    Page(
        title = "Stay Informed, Always",
        description = "Get the latest headlines and breaking news from trusted sources around the world—all in one place",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Personalized for You",
        description = "Choose your favorite topics and get a news feed tailored to your interests—whether it’s tech, sports, politics, or entertainment.",
        image = R.drawable.ic_launcher_background
    ),
    Page(
        title = "Read, Save & Share",
        description = "Easily bookmark articles to read later, and share important stories with your friends and family with a single tap.",
        image = R.drawable.ic_launcher_background
    )
)
