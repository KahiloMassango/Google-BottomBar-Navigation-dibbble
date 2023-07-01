package com.example.bottombarnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bottombarnavigation.ui.theme.BottomBarNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomBarNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(){
    Scaffold(
        bottomBar = {
            BottomBar()
        }
    ){
       Column(
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center,
           modifier = Modifier
               .background(color = Color(0xFFcfebef))
               .fillMaxSize()
       ) {
           Text(
               text = "Bottom App Bar",
               fontSize = 32.sp,
               fontWeight = FontWeight.Bold,
               modifier = Modifier.padding(it)
           )
       }
    }
}

data class NavItem(
    val title: String,
    val icon: ImageVector,
    val background: Color,
    val onBackground: Color,
)

val NavigationItemsList = listOf(
    NavItem("Home", Icons.Outlined.Home, Color(0xFFdfd7f3), Color(0xFF5b37b7)),
    NavItem("Likes", Icons.Outlined.Favorite, Color(0xFFf7d7ef), Color(0xFFc9379d)),
    NavItem("Search", Icons.Outlined.Search, Color(0xFFfbefd3), Color(0xFFe6a919)),
    NavItem("Profile", Icons.Outlined.Person, Color(0xFFcfebef), Color(0xFF1194aa))
)

@Composable
fun BottomItem(modifier: Modifier = Modifier, navItem: NavItem, isSelected: Boolean = false,  onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(50.dp),
        color = if(isSelected) navItem.background else Color.White,
        modifier = modifier
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
           // horizontalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 19.dp)
                .animateContentSize(
                    animationSpec = SpringSpec(
                        dampingRatio = 1.5f
                    )
                )
        ) {
            Icon(
                imageVector = navItem.icon,
                contentDescription = null,
                tint = if (isSelected) navItem.onBackground else Color.Unspecified,
                modifier = Modifier.size(32.dp)
            )
            if(isSelected){
                Text(
                    text = navItem.title,
                    color = navItem.onBackground,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

        }
    }
}

@Composable
fun BottomBar(navList: List<NavItem> = NavigationItemsList, modifier: Modifier = Modifier){
    var selectedItem by remember { mutableStateOf(0) }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
        ) {
            navList.forEachIndexed { index, item ->
                BottomItem(navItem = item, isSelected = (selectedItem == index), onClick = { selectedItem = index })
            }
        }
    }
}

@Preview
@Composable
fun Show(){
    BottomBarNavigationTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainApp()
        }
    }
}
