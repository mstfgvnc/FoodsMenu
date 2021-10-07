package com.mustafaguvenc.foodmenu.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.mustafaguvenc.foodmenu.model.FoodModel
import com.mustafaguvenc.foodmenu.network.FoodApi
import com.mustafaguvenc.foodmenu.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor
    (val foodApi: FoodApi,application : Application): BaseViewModel(application){

    val foodList = MutableLiveData<FoodModel>()
    private val disposable = CompositeDisposable()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()

    fun getData(){

            launch {
                loading.value=true
                getDataFromAPI()
            }

    }
    private fun getDataFromAPI() {

        disposable.add(
            foodApi.getFoods()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object  : DisposableSingleObserver<FoodModel>(){
                    override fun onSuccess(t: FoodModel) {
                        foodList.value= t
                    }

                    override fun onError(e: Throwable) {
                        error.value=true
                        loading.value=false
                        e.printStackTrace()
                    }

                })

        )
    }

}