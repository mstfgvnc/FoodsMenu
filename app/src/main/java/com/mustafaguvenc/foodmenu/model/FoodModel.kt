package com.mustafaguvenc.foodmenu.model

data class FoodModel(

    val value: List<Value>
)

data class Value(

    val fields: Fields

)

data class Fields(

    val Calorie: String,
    val FoodCategory: String,
    val ItemStartDate: String,
    val Title: String,
    val id: String
)