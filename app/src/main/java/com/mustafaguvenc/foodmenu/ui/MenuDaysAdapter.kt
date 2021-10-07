package com.mustafaguvenc.foodmenu.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mustafaguvenc.foodmenu.R
import com.mustafaguvenc.foodmenu.model.Fields
import com.mustafaguvenc.foodmenu.model.FoodModel
import kotlinx.android.synthetic.main.item_days.view.*

class MenuDaysAdapter(var dayFoodList : HashMap<String, ArrayList<Fields>>)
    : RecyclerView.Adapter<MenuDaysAdapter.MenuDayItemViewHolder>(){
    class MenuDayItemViewHolder(var view :View) : RecyclerView.ViewHolder(view) {

    }
    lateinit var context:Context
    var keyList= listOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuDayItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context=parent.context
        val view = inflater.inflate(R.layout.item_days,parent,false)
        keyList= dayFoodList.keys.sorted()
        return MenuDayItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuDayItemViewHolder, position: Int) {
        holder.view.dayList.layoutManager= LinearLayoutManager(context)
        holder.view.dayList.adapter= MenuFoodsAdapter(dayFoodList.get(keyList.get(position))!!.toList().sortedBy { it.FoodCategory })
    }

    override fun getItemCount(): Int {
        return dayFoodList.size
    }

    fun updateDayList(newDayList: HashMap<String, ArrayList<Fields>>) {

        dayFoodList=newDayList
        notifyDataSetChanged()
    }



}