package com.arun.shoppinglist.ui.shoppinglist

import com.arun.shoppinglist.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}