package com.example.myshoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myshoppinglist.data.ShoppingItem
import com.example.myshoppinglist.data.db.ShoppingDatabase
import com.example.myshoppinglist.data.repository.ShoppingRepository
import com.example.myshoppinglist.other.ShoppingitemAdapter
import com.example.myshoppinglist.ui.shoppinglist.AddDialogListener
import com.example.myshoppinglist.ui.shoppinglist.AddShoppingItemDialog
import com.example.myshoppinglist.ui.shoppinglist.ShoppingViewModel
import com.example.myshoppinglist.ui.shoppinglist.ShoppingViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database=ShoppingDatabase(this)
        val repository=ShoppingRepository(database)
        val factory= ShoppingViewModelFactory(repository)
        val viewModel=ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)

        val adapter=ShoppingitemAdapter(listOf(),viewModel)
        rvShoppingitems.layoutManager=LinearLayoutManager(this)
        rvShoppingitems.adapter=adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.item= it
            adapter.notifyDataSetChanged()
        })
        fab.setOnClickListener {
            AddShoppingItemDialog(this,
                object : AddDialogListener{
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }

    }
}