package com.arun.shoppinglist.other

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arun.shoppinglist.databinding.ShoppingItemBinding
import com.arun.shoppinglist.db.entities.ShoppingItem
import com.arun.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(var items : List<ShoppingItem>, private val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {


    //class ShoppingViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
    inner class ShoppingViewHolder(val binding: ShoppingItemBinding): RecyclerView.ViewHolder(binding.root){

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val binding = ShoppingItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ShoppingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {

        val curShoppingItem = items[position]

        holder.binding.tvName.text = curShoppingItem.name
        holder.binding.tvAmount.text = "${curShoppingItem.amount}"

        holder.binding.ivDelete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }

        holder.binding.ivPlus.setOnClickListener {
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }

        holder.binding.ivMinus.setOnClickListener {
            if(curShoppingItem.amount > 0){
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }
        }
        }

    override fun getItemCount(): Int {

    return items.size
    }
}
