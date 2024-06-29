package com.example.wishlistapp


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit = {}
) {

    val navigationIcon: (@Composable () -> Unit) = {
        if(!title.contains("WishList")){
            IconButton(onClick = { onBackNavClicked() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack ,
                    contentDescription = "Go back",
                    tint = Color.White
                )
            }
        }else{
            null
        }

    }

    CenterAlignedTopAppBar(title = {
        Text(text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis)
    },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = colorResource(id = R.color.white)
        ),
        navigationIcon = navigationIcon)
}