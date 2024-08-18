package com.example.wishlistapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

// The Room database class which holds the database configuration and serves as the main access point
@Database(
    entities = [Wish::class], // Specifies the entities associated with this database
    version = 1, // Version of the database, used for migrations
    exportSchema = false // Disables exporting of schema to JSON, useful in production
)
abstract class WishDatabase : RoomDatabase() {

    // Provides the DAO for accessing the Wish table
    abstract fun wishDao(): WishDao
}
