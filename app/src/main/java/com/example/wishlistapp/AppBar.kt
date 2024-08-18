package com.example.wishlistapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarView(
    title: String, // Title to be displayed in the AppBar
    onBackNavClicked: () -> Unit = {} // Lambda for back navigation, defaults to an empty lambda
) {

    // Conditional navigation icon, only shows if the title does not contain "WishList"
    val navigationIcon: (@Composable () -> Unit)? = {
        if (!title.contains("WishList")) {
            IconButton(onClick = { onBackNavClicked() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Go back",
                    tint = Color.White
                )
            }
        } else {
            null
        }
    }

    // Center aligned top AppBar with title and optional navigation icon
    if (navigationIcon != null) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    maxLines = 1, // Restrict title to one line
                    overflow = TextOverflow.Ellipsis // Ellipsis if the title is too long
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer, // Background color of the AppBar
                titleContentColor = colorResource(id = R.color.white) // Title text color
            ),
            navigationIcon = navigationIcon // Set the navigation icon if available
        )
    }
}
