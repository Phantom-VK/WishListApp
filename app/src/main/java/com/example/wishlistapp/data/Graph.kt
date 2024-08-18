package com.example.wishlistapp.data

import android.content.Context
import androidx.room.Room

// Graph object provides centralized access to the database and repositories
object Graph {
    private lateinit var database: WishDatabase

    // Lazy initialization of the WishRepository, which depends on the WishDao from the database
    val wishRepository by lazy {
        WishRepository(wishDao = database.wishDao())
    }

    // Initializes the Room database with the provided context
    fun provide(context: Context) {
        database = Room.databaseBuilder(
            context,
            WishDatabase::class.java,
            "wishlist.db" // The name of the database
        ).build()
    }
}
