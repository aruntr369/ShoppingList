package com.arun.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.arun.shoppinglist.databinding.ActivityShoppingBinding
import com.arun.shoppinglist.db.ShoppingDatabase
import com.arun.shoppinglist.db.entities.ShoppingItem
import com.arun.shoppinglist.other.ShoppingItemAdapter
import com.arun.shoppinglist.repositories.ShoppingRepository

class ShoppingActivity : AppCompatActivity() {
        lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // view binding for the activity
//        private var _binding : ActivityShoppingBinding? = null
//        private val binding get() = _binding!!



        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        binding.rvShoppingItems.layoutManager = LinearLayoutManager(this)
        binding.rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingitem().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        binding.fab.setOnClickListener {
            AddShoppingItemDialog(
                this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }
            ).show()
        }
    }
}
