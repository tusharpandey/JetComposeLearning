package com.example.jetcomposelearning

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetcomposelearning.data.User
import com.example.jetcomposelearning.ui.theme.JetComposeLearningTheme

@Composable
fun HomePage(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxHeight()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(corner = CornerSize(10.dp))
        ) {
            ArtistAvatarContainer {
                ArtistAvatar()
            }
            Divider(thickness = .5.dp)
            UserDetailContainer {
                getUserDetail()
            }
            Divider(thickness = .5.dp)
            getFeed(navController)
        }
    }
}

@Composable
fun ArtistAvatarContainer(content: @Composable() (ColumnScope.() -> Unit)) {
    val displayMetrics = LocalConfiguration.current
    val width = displayMetrics.screenWidthDp.dp

    Column(
        modifier = Modifier.width(width),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        content()
    }
}

@Composable
fun ArtistAvatar() {
    Surface(
        color = Color.White,
        border = BorderStroke(.1.dp, Color.LightGray),
        shape = CircleShape,
        shadowElevation = 4.dp,
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .padding(25.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.ic_user_img),
                contentDescription = "Artist image",
                contentScale = ContentScale.Inside
            )
        }
    }
}


@Composable
fun UserDetailContainer(content: @Composable() (ColumnScope.() -> Unit)) {
    val displayMetrics = LocalConfiguration.current
    val width = displayMetrics.screenWidthDp.dp

    Column(
        modifier = Modifier.width(width),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        content()
    }
}

@Composable
fun getUserDetail() {

    val shouldShowDetails = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp),
    ) {
        if (shouldShowDetails.value) {
            Text(text = "Tushar", fontSize = 40.sp, color = Color.Blue)
            Text(text = "Mobile Lead", fontSize = 24.sp)
            Text(text = "Zego", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(20.dp))
        }
        Button(
            onClick = { shouldShowDetails.value = !shouldShowDetails.value },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            shape = RectangleShape,
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            border = BorderStroke(1.dp, Color.LightGray),
        ) {
            Text(
                text = if (shouldShowDetails.value) "Hide Details" else "Show Details",
                fontSize = 24.sp
            )
        }
    }
}

@Composable
fun getFeed(navController: NavController) {

    var dataSet = mutableListOf<User>()
    dataSet.add(User("tushar"))
    dataSet.add(User("Ravi"))
    dataSet.add(User("kamal"))
    dataSet.add(User("Naresh"))

    LazyColumn(
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 15.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        items(dataSet.size) { it ->
            getFeedItem(it, dataSet[it]) { clickedIndex ->
                navController.navigate("friendDetail/${dataSet[it].name}")
            }
        }
    }
}

@Composable
fun getFeedItem(index: Int, user: User, onClick: (Int) -> Unit) {

    Card(
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.cardElevation(3.dp),
        modifier = Modifier.clickable {
            onClick(index)
        }
    ) {
        Row(
            modifier = Modifier
                .background(color = Color.White)
                .padding(13.dp)
                .fillMaxWidth()
                .height(130.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .align(alignment = Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.ic_user_img),
                contentDescription = "Artist image",
                contentScale = ContentScale.Inside
            )

            Text(
                modifier = Modifier
                    .padding(13.dp)
                    .align(alignment = Alignment.CenterVertically),
                text = user.name,
                fontSize = 30.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetComposeLearningTheme {
        HomePage(navController = NavController(LocalContext.current))
    }
}