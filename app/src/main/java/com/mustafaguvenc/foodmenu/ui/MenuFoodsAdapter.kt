package com.mustafaguvenc.foodmenu.ui

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mustafaguvenc.foodmenu.R
import com.mustafaguvenc.foodmenu.model.Fields
import kotlinx.android.synthetic.main.item_days.view.*
import kotlinx.android.synthetic.main.item_foods.view.*

class MenuFoodsAdapter(var dayFoodList :  List<Fields>)
    : RecyclerView.Adapter<MenuFoodsAdapter.MenuFoodItemViewHolder>(){
    val foodCategoryset = hashSetOf<String>()
    var nowFoodCategory =""
    var dayListPosition =0
    class MenuFoodItemViewHolder(var view : View) : RecyclerView.ViewHolder(view) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuFoodItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_foods,parent,false)
        return MenuFoodItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuFoodItemViewHolder, position: Int) {

        if(!nowFoodCategory.equals(dayFoodList.get(dayListPosition).FoodCategory)){
            holder.view.foodName.text=dayFoodList.get(dayListPosition).FoodCategory
            holder.view.foodName.setTextColor(Color.RED)
            holder.view.foodName.setTextSize(18f)
            nowFoodCategory=dayFoodList.get(dayListPosition).FoodCategory

        }else{

            holder.view.foodCalorie.text=dayFoodList.get(dayListPosition).Calorie + " Kl"
            holder.view.foodName.text=dayFoodList.get(dayListPosition).Title
            holder.view.foodName.setTextColor(Color.BLACK)
            holder.view.foodName.setTextSize(13f)
            holder.view.foodCalorie.setTextColor(Color.BLACK)
            holder.view.foodCalorie.setTextSize(13f)
            dayListPosition++

        }

    }

    override fun getItemCount(): Int {

        for(i in dayFoodList){
            foodCategoryset.add(i.FoodCategory)
        }
        return (dayFoodList.size + foodCategoryset.size )
    }


}