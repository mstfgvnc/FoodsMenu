package com.mustafaguvenc.foodmenu.network

import com.mustafaguvenc.foodmenu.model.FoodModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {
    @GET("mstfgvnc/39ff84439cdca663bd0e8879746edd55/raw/9089a37d867e72d293150289a60093f4faac99ae/FoodMenu")
    fun getFoods(): Single<FoodModel>
}