package com.example.mealzapp.ui.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.mealzapp.model.response.MealsResponse
import kotlin.math.min

@Composable
fun MealDetailsScreen(meals: MealsResponse?) {
    val scrollState = rememberLazyListState()
    val offset = min(1f, 1 - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemIndex))
    val size by animateDpAsState(targetValue = max(100.dp, 200.dp * offset))
    var profilePictureState by remember {
        mutableStateOf(MealProfilePictureState.Normal)
    }
    val transition = updateTransition(targetState = profilePictureState, label = "")
    //var isExpanded by remember { mutableStateOf(false) }
    val imageSizeDp by transition.animateDp(
        targetValueByState = {
            it.size
        }, label = ""
    )
    val color by transition.animateColor(
        targetValueByState = {
            it.color
        }, label = ""
    )
    val widthSize by transition.animateDp(
        targetValueByState = {
            it.borderWidth
        }, label = ""
    )
    Surface(color = MaterialTheme.colors.background) {
        Column {
            Surface(elevation = 4.dp) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 2.dp, color = Color.Green
                        )
                    ) {
                        Image(
                            painter = rememberImagePainter(data = meals?.imageUrl!!,
                                builder = {
                                    transformations(CircleCropTransformation())
                                }),
                            contentDescription = null,
                            modifier = Modifier
                                .size(size)
                        )
                    }
                    Text(
                        meals?.name ?: "",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            val dummyContentList = (0..100).map { it.toString() }
            LazyColumn(state = scrollState) {
                items(dummyContentList){ dummyItem ->
                    Text(text = dummyItem,modifier = Modifier.padding(24.dp))
                }
            }
        }

    }
}

enum class MealProfilePictureState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Normal(Color.Magenta, 120.dp, 8.dp),
    Expanded(Color.Green, 200.dp, 24.dp)
}