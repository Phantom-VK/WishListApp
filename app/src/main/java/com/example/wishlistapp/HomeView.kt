import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlistapp.AppBarView
import com.example.wishlistapp.Screen
import com.example.wishlistapp.data.Wish

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController, // Navigation controller for screen transitions
    viewModel: WishViewModel // ViewModel to manage wish data
) {
    Scaffold(
        topBar = { AppBarView(title = "WishList", onBackNavClicked = {}) }, // Top AppBar with title
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddScreen.route + "/0L") }, // Navigate to Add screen on FAB click
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add an item") // Add icon in FAB
            }
        }
    ) {
        val wishList by viewModel.getAllWishes.collectAsState(initial = listOf()) // Collecting wish list from ViewModel

        // Display list of wishes using LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(wishList, key = { wish -> wish.id }) { wish ->
                val dismissState = rememberDismissState(
                    confirmValueChange = {
                        if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                            viewModel.deleteWish(wish) // Delete wish on swipe to dismiss
                        }
                        true
                    }
                )
                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color by animateColorAsState(
                            if (dismissState.dismissDirection == DismissDirection.EndToStart) Color.Red else Color.Transparent
                        )
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(20.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
                        }
                    },
                    dismissContent = {
                        WishItem(wish = wish) {
                            navController.navigate(Screen.AddScreen.route + "/${wish.id}") // Navigate to Edit screen on item click
                        }
                    },
                    directions = setOf(DismissDirection.EndToStart) // Only allow dismiss from end to start
                )
            }
        }
    }
}

@Composable
fun WishItem(
    wish: Wish, // Data for each wish item
    onClick: () -> Unit // Lambda for handling click on the wish item
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = wish.title, fontWeight = FontWeight.Bold)
            Text(text = wish.description)
        }
    }
}
