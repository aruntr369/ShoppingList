package com.arun.shoppinglist.ui.shoppinglist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.arun.shoppinglist.R
import com.arun.shoppinglist.databinding.DialogAddShoppingItemBinding
import com.arun.shoppinglist.db.entities.ShoppingItem

lateinit var binding: DialogAddShoppingItemBinding

class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener) : AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddShoppingItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            if (name.isNullOrEmpty()) {
                Toast.makeText(context,"Please enter a name",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val amount = binding.etAmount.text.toString().toInt()

            val item = ShoppingItem(name,amount)
            addDialogListener.onAddButtonClicked(item)
            dismiss()
            Log.d("TAG", "onCreate: add   ")


        }

        binding.tvCancel.setOnClickListener {
            cancel()
        }
    }
}