package com.example.submissionexam

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submissionexam.adapter.ListBeautyProductAdapter
import com.example.submissionexam.data.BeautyProductData
import com.example.submissionexam.ui.ProfileScreen

class MainActivity : AppCompatActivity() {
    private lateinit var rvBeautyProducts : RecyclerView
    private val list = ArrayList<BeautyProductData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        rvBeautyProducts = findViewById(R.id.rv_beauty_products)
        rvBeautyProducts.setHasFixedSize(true)

        val profileImageView: ImageView = findViewById(R.id.user_profile_image)
        profileImageView.setOnClickListener { view ->
            showPopupMenu(view)
        }

        list.addAll(getListBeautyProduct())
        showRecyclerList()
    }

    private fun getListBeautyProduct(): ArrayList<BeautyProductData> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listHero = ArrayList<BeautyProductData>()

        for (i in dataName.indices) {
            val hero = BeautyProductData(dataName[i], dataDescription[i], dataPrice[i],dataPhoto[i])
            listHero.add(hero)
        }
        return listHero

    }

    private fun showRecyclerList() {
        rvBeautyProducts.layoutManager = GridLayoutManager(this, 2)
        val listBeautyProductAdapter = ListBeautyProductAdapter(list)
        rvBeautyProducts.adapter = listBeautyProductAdapter

    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        val menuInflater: MenuInflater = popupMenu.menuInflater
        menuInflater.inflate(R.menu.profile_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.action_edit_profile -> {
                    val intent = Intent(this, ProfileScreen::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}