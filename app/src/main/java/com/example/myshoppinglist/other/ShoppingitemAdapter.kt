package com.example.myshoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myshoppinglist.R
import com.example.myshoppinglist.data.ShoppingItem
import com.example.myshoppinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shoppingitem.view.*


class ShoppingitemAdapter(
    var item:List<ShoppingItem>,
    private val viewModel:ShoppingViewModel
):RecyclerView.Adapter<ShoppingitemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.shoppingitem,parent,false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = item[position]
        holder.itemView.tvName.text = currentShoppingItem.name
        holder.itemView.tvAmount.text = "${currentShoppingItem.amount}"
        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(currentShoppingItem)
        }
        holder.itemView.ivAdd.setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upsert(currentShoppingItem)
        }
        holder.itemView.ivRemove.setOnClickListener {
            if (currentShoppingItem.amount>0){
                currentShoppingItem.amount--
                viewModel.upsert(currentShoppingItem)
            }
        }
    }


    override fun getItemCount(): Int {
       return item.size
    }
    inner class ShoppingViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

}