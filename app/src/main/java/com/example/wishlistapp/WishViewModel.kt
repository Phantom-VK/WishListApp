package com.example.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WishViewModel : ViewModel(){

    var wishTitleState by mutableStateOf("")
    var wishDescription by mutableStateOf("")

    fun onWishTitleChange(newString:String){
        wishTitleState = newString
    }

    fun onWishDescriptionChange(newString: String){
        wishDescription = newString
    }

}