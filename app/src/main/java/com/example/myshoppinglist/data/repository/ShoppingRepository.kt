package com.example.myshoppinglist.data.repository

import com.example.myshoppinglist.data.ShoppingItem
import com.example.myshoppinglist.data.db.ShoppingDatabase

data class ShoppingRepository(
     private var db:ShoppingDatabase
 ) {
    fun upsert(item: ShoppingItem)=db.getShoppingDao().upsert(item)
    fun delete(item: ShoppingItem)=db.getShoppingDao().delete(item)
    fun getAllShoppingItems()=db.getShoppingDao().getAllShoppingItems()
}