package com.example.mycontactapp


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontactapp.databinding.CategoryListItemBinding


class CategoryAdapter(
    private val dataset: List<String>,
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    class CategoryViewHolder(private val binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val button = binding.categoryButtonItem
        fun bind(category: String) {
            button.text = category
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val adapterLayout = CategoryListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = dataset[position]
        holder.bind(category)
        holder.button.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ContactListActivity::class.java)
            intent.putExtra(ContactListActivity.CATEGORY, holder.button.text.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}