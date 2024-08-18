package com.example.wishlistapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// DAO for performing database operations on the Wish entity
@Dao
abstract class WishDao {

    // Inserts a new wish into the table, ignores if the wish already exists
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun addWish(wishEntity: Wish)

    // Retrieves all wishes from the wish table
    @Query("SELECT * FROM `wish-table`")
    abstract fun getAllWishes(): Flow<List<Wish>>

    // Updates an existing wish
    @Update
    abstract suspend fun updateWish(wishEntity: Wish)

    // Deletes a specific wish
    @Delete
    abstract suspend fun deleteWish(wishEntity: Wish)

    // Retrieves a wish by its ID
    @Query("SELECT * FROM `wish-table` WHERE id = :id")
    abstract fun getWishById(id: Long): Flow<Wish>
}
