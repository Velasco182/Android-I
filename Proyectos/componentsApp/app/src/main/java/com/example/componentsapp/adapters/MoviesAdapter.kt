package com.example.componentsapp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.componentsapp.R
import com.example.componentsapp.models.MoviesItem

class MoviesAdapter(var context: Context, var arrayList: ArrayList<MoviesItem>) : BaseAdapter(){

    override fun getItem(position: Int): Any {
        return arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getCount(): Int {
        return  arrayList.size
    }
    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        var view: View = View.inflate(context, R.layout.card_view_item_grid, null)
        var icons: ImageView = view.findViewById(R.id.icons)
        var name: TextView = view.findViewById(R.id.name_text_view)

        var listItem: MoviesItem = arrayList.get(position)

        icons.setImageResource(listItem.icons!!)
        name.text = listItem.name

        return view
    }
}