package com.example.wishlistapp.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {
    fun addWish(wish: Wish){
        wishDao.addWish(Wish())
    }

    fun getWishes(): Flow<List<Wish>> = wishDao.getAllWishes()

    fun getWishById(id:Long): Flow<Wish>{
        return wishDao.getWishById(id)
    }

    suspend fun updateWish(wish: Wish){
        wishDao.updateWish(wish)
    }

    suspend fun deleteWish(wish:Wish){
        wishDao.deleteWish(wish)
    }
}