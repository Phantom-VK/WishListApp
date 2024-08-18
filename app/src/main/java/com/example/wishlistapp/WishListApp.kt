package com.example.wishlistapp

import android.app.Application
import com.example.wishlistapp.data.Graph

class WishListApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Graph.provide(this) // Initialize the dependency graph when the app is created
    }
}
