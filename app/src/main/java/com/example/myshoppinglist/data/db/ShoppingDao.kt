package com.example.myshoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.*
import com.example.myshoppinglist.data.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(item: ShoppingItem):Long

    @Delete
    fun delete(item: ShoppingItem):Int

    @Query("SELECT * FROM ShoppingItem")
    fun getAllShoppingItems():LiveData<List<ShoppingItem>>
}