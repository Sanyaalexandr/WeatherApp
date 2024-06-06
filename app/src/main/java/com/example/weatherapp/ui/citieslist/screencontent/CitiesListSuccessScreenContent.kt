package com.example.weatherapp.ui.citieslist.screencontent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.citieslist.City
import kotlinx.coroutines.launch

@Composable
fun CitiesListSuccessScreenContent(
    cities: List<City>,
    onCityClick: (City) -> Unit,
) {
    val listState: LazyListState = rememberLazyListState()
    val textMeasurer = rememberTextMeasurer()
    val itemHeight = remember { mutableIntStateOf(0) }
    val gutterWidth = remember { mutableStateOf(56.dp) }
    val gutterPx = with(LocalDensity.current) {
        gutterWidth.value.toPx()
    }
    val labelPaddingStart = with(LocalDensity.current) {
        8.dp.toPx()
    }
    val labelColor = MaterialTheme.colorScheme.onBackground
    Box {
        Box(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars)
                .drawCityLabels(
                    textColor = labelColor,
                    itemHeight = itemHeight,
                    listState = listState,
                    cities = cities,
                    textMeasurer = textMeasurer,
                    gutterPx = gutterPx,
                    paddingStartPx = labelPaddingStart
                )
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(cities) { city ->
                    CityItem(
                        city = city,
                        onClick = onCityClick,
                        modifier = Modifier.padding(start = gutterWidth.value)
                    )
                }
            }
        }
        Spacer(modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.statusBars)
            .fillMaxWidth()
        )
        val isScrollToTopButtonVisible = remember {
            derivedStateOf {
                listState.firstVisibleItemScrollOffset > 0 ||
                        listState.firstVisibleItemIndex > 0
            }
        }
        ScrollToTopButton(
            isScrollToTopButtonVisible = isScrollToTopButtonVisible,
            listState = listState,
        )
    }
}

@Composable
private fun CityItem(
    city: City,
    onClick: (City) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    bottomStart = 8.dp,
                )
            )
            .clickable {
                onClick.invoke(city)
            }
    ) {
        Text(
            text = city.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun BoxScope.ScrollToTopButton(
    isScrollToTopButtonVisible: State<Boolean>,
    listState: LazyListState,
) {
    AnimatedVisibility(
        visible = isScrollToTopButtonVisible.value,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(22.dp)
    ) {
        val scope = rememberCoroutineScope()
        FloatingActionButton(
            onClick = {
                scope.launch {
                    listState.animateScrollToItem(0)
                }
            },
            modifier = Modifier
                .padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "scroll to top button"
            )
        }
    }
}

private fun Modifier.drawCityLabels(
    textColor: Color,
    itemHeight: MutableIntState,
    listState: LazyListState,
    cities: List<City>,
    textMeasurer: TextMeasurer,
    gutterPx: Float,
    paddingStartPx: Float,
) = this.drawWithCache {
    onDrawBehind {
        var initial: Char? = null
        if (itemHeight.intValue == 0) {
            itemHeight.intValue = listState.layoutInfo.visibleItemsInfo.firstOrNull()?.size ?: 0
        }
        listState.layoutInfo.visibleItemsInfo.forEachIndexed { index, itemInfo ->
            val itemInitial = cities.getOrNull(itemInfo.index)?.name?.uppercase()?.first()
            if (itemInitial != null && itemInitial != initial) {
                initial = itemInitial
                val nextInitial = cities.getOrNull(itemInfo.index + 1)?.name?.uppercase()?.first()
                val textLayout = textMeasurer.measure(
                    text = AnnotatedString(itemInitial.toString()),
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium),
                )
                val horizontalOffset = (gutterPx - textLayout.size.width) / 2 + paddingStartPx
                val verticalOffset = (itemHeight.intValue - textLayout.size.height) / 2
                drawText(
                    textLayoutResult = textLayout,
                    color = textColor,
                    topLeft = Offset(
                        x = horizontalOffset,
                        y = if (index != 0 || itemInitial != nextInitial) {
                            itemInfo.offset.toFloat()
                        } else {
                            0f
                        } + verticalOffset,
                    ),
                )
            }
        }
    }
}


