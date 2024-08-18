package com.example.wishlistapp.data

import kotlinx.coroutines.flow.Flow

// Repository class to handle data operations. Provides a clean API for data access to the rest of the application.
class WishRepository(private val wishDao: WishDao) {

    // Adds a new wish to the database
    fun addWish(wish: Wish) {
        wishDao.addWish(wish)
    }

    // Retrieves all wishes as a Flow, which allows observing the data
    fun getWishes(): Flow<List<Wish>> = wishDao.getAllWishes()

    // Retrieves a specific wish by its ID as a Flow
    fun getWishById(id: Long): Flow<Wish> {
        return wishDao.getWishById(id)
    }

    // Updates an existing wish in the database
    suspend fun updateWish(wish: Wish) {
        wishDao.updateWish(wish)
    }

    // Deletes a wish from the database
    suspend fun deleteWish(wish: Wish) {
        wishDao.deleteWish(wish)
    }
}
