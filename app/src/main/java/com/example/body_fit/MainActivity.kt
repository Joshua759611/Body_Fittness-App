package com.example.body_fit

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.body_fit.ui.theme.Body_FitTheme
import java.util.Locale
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.material3.Icon as Icon


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Body_FitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Joshua")
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier:Modifier=Modifier
){
    TextField(
        value = "",
        onValueChange ={},
        leadingIcon={
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription =null
            )
        },
       colors = TextFieldDefaults.textFieldColors(
           //backgroundColor = MaterialTheme.colors.surface
       ),
        placeholder={
            Text(stringResource(R.string.placeholder_search))
        },

        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),

        )

}
@Composable
fun AlignYourBodyElement(
    //drawable: Int, text: Int,
    modifier: Modifier=Modifier,
    @DrawableRes drawable: Int,
    @StringRes text:Int,

){
    Column(
      modifier=Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(
           painter = painterResource(drawable),
            contentDescription =null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)

        )
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.paddingFromBaseline(
                top = 24.dp, bottom = 8.dp
            )
        )
    }
}
@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement=Arrangement.spacedBy(8.dp),
        contentPadding= PaddingValues(horizontal = 16.dp),
        modifier=modifier
    ){
        items(alignYourBodyData) { item -> 
            AlignYourBodyElement(
                drawable = item.drawable,
                text = item.text
            )
        }
    }

}


@Composable
fun FavouriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text:Int,
    modifier: Modifier=Modifier
){
    Surface(
        shape=MaterialTheme.shapes.small,
        modifier=modifier
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(192.dp)
                .height(160.dp)
                )
        {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)

            )
            Text(
                text = stringResource(text),
                style= MaterialTheme.typography.titleSmall,
                modifier=Modifier.padding(horizontal = 16.dp)
            )

        }
    }
}
@Composable
fun FavouriteCollectionGrid(
    modifier: Modifier=Modifier
){
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding= PaddingValues(horizontal = 16.dp),
        verticalArrangement=Arrangement.spacedBy(8.dp),
        horizontalArrangement=Arrangement.spacedBy(8.dp),
        modifier=modifier.height(120.dp)
    ){
       items(favouriteCollectionData) { item ->
           FavouriteCollectionCard(
               drawable = item.drawable,
               text = item.text,
               modifier=Modifier.height(56.dp)

           )
       }
    }
}
@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier=Modifier,
    content: @Composable () ->Unit
){
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        Text(stringResource(title).uppercase(Locale.getDefault()),
        style=MaterialTheme.typography.titleMedium,
            modifier= Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}
/*@Composable
fun HomeScreen(modifier: Modifier=Modifier){
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.title) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favourite_collections) {
            FavouriteCollectionGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}*/
@Composable
fun HomeScreen(modifier: Modifier=Modifier){
    Column(modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.title) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favourite_collections) {
            FavouriteCollectionGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment=Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        var count by remember { mutableStateOf(0) }
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(onClick = { count = +1 }) {
            Text(text = "Click Me")
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text(

                    text = "Hello",
                    fontSize = 20.sp,
                    color = Color.Blue,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            items(10) { index ->
                Text(text = "Item :$index")
            }
            item {

                Text(
                    text = "Thank You For Visiting Us!!",
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                    fontSize = 20.sp
                )
            }

        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(5) { item ->

                Text(text = "Name $item")
            }
        }


        Button(onClick = {}) {
            Text(text = "Hurray")
        }
    }

}
@Composable
fun MessageList(messages: String){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)

    ){
        messages.forEach{message ->
          Text(text = "Greetings $message")

    }
    }
}


@Composable
@ExperimentalMaterial3Api
fun BodyFitBottomNavigation(modifier: Modifier=Modifier){
    //BottomNavigation(modifier){
        //BottomNavigationItem(
            /*icon={
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_home))
            },
            selected=false
        ){}

        BottomNavigationItem(
            icon={
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.botton_navigation_account))
            },
            selected=false
        )

    }*/
    //}

}
@ExperimentalMaterial3Api
@Composable
fun AlertDialog(
    onDismissRequest:() ->Unit,
    modifier: Modifier=Modifier,
    properties:DialogProperties=DialogProperties(),
    content:@Composable () -> Unit
):Unit
{

}
private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversion,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favouriteCollectionData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map {DrawableStringPair(it.first, it.second)}

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

@Preview(showBackground = true, backgroundColor =  0xFFF0EAE2)
@Composable
fun SearchBarPreview() {
    Body_FitTheme { SearchBar(Modifier.padding(8.dp)) }
}
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
 fun AlignYourBodyElementPreview(){
    Body_FitTheme {
        AlignYourBodyElement(
            text= R.string.ab1_inversion,
            drawable = R.drawable.ab1_inversions,
            modifier = Modifier.padding(8.dp)
        )
    }

}
@Preview(showBackground = true)
@Composable
fun FavouriteCollectionCardPreview(){
    Body_FitTheme {
        FavouriteCollectionCard(
            text= R.string.fc2_nature_inversions,
            drawable = R.drawable.fc2_nature_meditations,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyRowPreview() {
    Body_FitTheme { AlignYourBodyRow() }
}

@Preview(showBackground = true, backgroundColor =0xFFF0EAE2 )
@Composable
fun FavouriteCollectionGridPreview(){
    Body_FitTheme {FavouriteCollectionGrid()}
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2, heightDp = 180)
@Composable
fun HomeSectionPreview() {
    Body_FitTheme {
        HomeSection(R.string.title) {
            AlignYourBodyRow()
        }
    }

}
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun ScreenContentPreview(){
    Body_FitTheme { HomeScreen()}
}

@Preview(showBackground = true, backgroundColor =0xFFF0EAE2)
@Composable
fun GreetingPreview() {
    Body_FitTheme { Greeting(name = "Joshua!!")}
}
@Preview(showBackground = true, backgroundColor = 0XFF018786)
@Composable
fun MessageListPreview(){
    Body_FitTheme { MessageList(messages = "Hello Dev")}
}
