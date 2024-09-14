package com.example.submissionexam.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissionexam.R
import com.example.submissionexam.data.BeautyProductData
import com.example.submissionexam.ui.DetailScreen

class ListBeautyProductAdapter(private val listBeautyProduct: ArrayList<BeautyProductData>) : RecyclerView.Adapter<ListBeautyProductAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_product, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, price, photo) = listBeautyProduct[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)
        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.tvPrice.text = price

        // Setting up click listener for the item
        holder.itemView.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context, DetailScreen::class.java)

            //Passing data to DetailScreen using putExtra
            intent.putExtra("EXTRA_NAME", name)
            intent.putExtra("EXTRA_DESCRIPTION", description)
            intent.putExtra("EXTRA_PRICE", price)
            intent.putExtra("EXTRA_PHOTO", photo)

            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = listBeautyProduct.size



    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_item_price)
    }



}