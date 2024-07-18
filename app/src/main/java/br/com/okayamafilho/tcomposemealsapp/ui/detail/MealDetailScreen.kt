package br.com.okayamafilho.tcomposemealsapp.ui.detail

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import br.com.okayamafilho.tcomposemealsapp.model.MealsRepository
import br.com.okayamafilho.tcomposemealsapp.model.response.MealResponse
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.Companion.DefaultTransform
import coil.transform.CircleCropTransformation
import kotlin.math.min

@Composable
fun MealDetailScreen(meal: MealResponse?) {
//    var profilePictureState by remember { mutableStateOf(MealProfilePictureState.Normal) }
//    val transition = updateTransition(targetState = profilePictureState, label = "")
//    val imageSizeDp by transition.animateDp(targetValueByState = { it.size }, label = "")
//    val color by transition.animateColor(targetValueByState = { it.color }, label = "")
//    val widthSize by transition.animateDp(targetValueByState = { it.borderWidth }, label = "")


//    val scrollState = rememberScrollState()
//    val offset = min(1f, 1 - (scrollState.value / 600f))
    val scrollState = rememberLazyListState()
    val offset = min(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset / 600f) + scrollState.firstVisibleItemIndex
    )
    val size by animateDpAsState(targetValue = max(100.dp, 200.dp * offset))

    Column(modifier = Modifier.padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Card(
                modifier = Modifier.padding(16.dp),
                shape = CircleShape,
                border = BorderStroke(
                    width = 4.dp,
                    color = Color.Green
                )

            ) {
                AsyncImage(
                    model = meal?.imageUrl,
                    contentDescription = "",
                    modifier = Modifier
                        .size((size))
                        .padding(4.dp),
                    contentScale = ContentScale.Crop,
                    transform = DefaultTransform
                )
            }
            Text(
                text = meal?.name ?: "default name",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            )
        }
//        Button(
//            modifier = Modifier.padding(
//                16.dp
//            ),
//            onClick = {
//                profilePictureState = if (profilePictureState == MealProfilePictureState.Normal)
//                    MealProfilePictureState.Expanded
//                else
//                    MealProfilePictureState.Normal
//            }) {
//        Column(modifier = Modifier.verticalScroll(scrollState)) {
//            Text("This is a text element", modifier = Modifier.padding(32.dp))
//            Text("This is a text element", modifier = Modifier.padding(32.dp))
//            Text("This is a text element", modifier = Modifier.padding(32.dp))
//            Text("This is a text element", modifier = Modifier.padding(32.dp))
//            Text("This is a text element", modifier = Modifier.padding(32.dp))
//            Text("This is a text element", modifier = Modifier.padding(32.dp))
//            Text("This is a text element", modifier = Modifier.padding(32.dp))
//            Text("This is a text element", modifier = Modifier.padding(32.dp))
//            Text("This is a text element", modifier = Modifier.padding(32.dp))
//            Text("This is a text element", modifier = Modifier.padding(32.dp))
//        }
        val dummyContentList = (0..100).map { it.toString() }
        LazyColumn(state = scrollState) {
            items(dummyContentList) { dummyItem ->
                Text(dummyItem, modifier = Modifier.padding(32.dp))
            }
        }
    }
}

enum class MealProfilePictureState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Normal(Color.Magenta, 120.dp, 8.dp),
    Expanded(Color.Green, 200.dp, 24.dp)
}