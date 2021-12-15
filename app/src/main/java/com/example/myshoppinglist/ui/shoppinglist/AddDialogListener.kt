package com.example.myshoppinglist.ui.shoppinglist

import com.example.myshoppinglist.data.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}
//this interface helps us add an item to our
// activity main.xml through the addshoppingitemdialog class