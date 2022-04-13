package com.example.closedcircuitapplication.common.authentication.utils

import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.authentication.presentation.models.AdapterModel

object DataFactory {

    fun onboardingData(): List<AdapterModel> {
        return listOf(
            AdapterModel(
                onBoardingImage = R.drawable.ic_idea_illustration,
                title = "Easily raise funds for your business or project",
                description = "Start a business or project and get your family, friends or wellwishers to financially support from anywhere in the world."
            ),
            AdapterModel(
                onBoardingImage = R.drawable.ic_project_illustration,
                title = "Get guided execution. Create a budget and action steps",
                description = "Follow the guided steps on execution of your business or project to unlock funds and stay accountable to your sponsors."
            ),
            AdapterModel(
                onBoardingImage = R.drawable.ic_sponsor_illustration,
                title = "Support, incubate and mentor with your financial contributions",
                description = "Ensure your financial contributions are meaningfully used to establish success in the business or project funded."
            ),
            AdapterModel(
                onBoardingImage = R.drawable.ic_investment_illustration,
                title = "Discover diligently executed ideas to invest in",
                description = "Discover investment opportunities in star performing businesses or projects on the platform."
            )
        )
    }
}