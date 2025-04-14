package com.example.newsapp.presentation.onboarding

import android.graphics.pdf.PdfDocument
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.newsapp.presentation.Dimensions
import com.example.newsapp.presentation.Dimensions.PageIndicatorWidth
import com.example.newsapp.presentation.common.NewsButton
import com.example.newsapp.presentation.common.NewsTextButton
import com.example.newsapp.presentation.onboarding.components.OnBoardingPage
import com.example.newsapp.presentation.onboarding.components.PageIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        val PagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val buttonState = remember {
            derivedStateOf {
                when (PagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(state = PagerState) { index ->
            OnBoardingPage(page = pages[index])
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = Dimensions.MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier.width(PageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = PagerState.currentPage
            )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            val scope = rememberCoroutineScope()
            if (buttonState.value[0].isNotEmpty()) {
                NewsTextButton(
                    text = buttonState.value[0],
                    onClick = {
                        scope.launch {
                            PagerState.animateScrollToPage(page = PagerState.currentPage - 1)
                        }
                    }
                )
            }

            NewsButton(
                text = buttonState.value[1],
                onClick = {
                    scope.launch {
                        if (PagerState.currentPage == 3) {
                            //TODO: Navigate to the main screen
                        } else {
                            PagerState.animateScrollToPage(page = PagerState.currentPage + 1)
                        }
                    }
                }
            )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}