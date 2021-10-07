package com.mustafaguvenc.foodmenu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.mustafaguvenc.foodmenu.R
import com.mustafaguvenc.foodmenu.model.Fields
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_menu.*

@AndroidEntryPoint
class MenuFragment : Fragment() {


    val viewModel : MenuViewModel by viewModels()

    private val adapter= MenuDaysAdapter(hashMapOf())
    var pagerSnapHelper = PagerSnapHelper()

    var day =""
    var mounth = ""
    var year = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData()

        val linearLayoutManager=
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        foodList.layoutManager=linearLayoutManager
        foodList.adapter=adapter
        pagerSnapHelper.attachToRecyclerView(foodList)

        foodList.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                    day = adapter.keyList.get(linearLayoutManager.findFirstVisibleItemPosition()).substring(8,10)+" "
                    mounth = findMonth(adapter.keyList.get(linearLayoutManager.findFirstVisibleItemPosition()).substring(5,7))+" "
                    if(linearLayoutManager.findFirstVisibleItemPosition()==1){
                        year= "Yarın"
                    }else if(linearLayoutManager.findFirstVisibleItemPosition()==0){
                        year= "Bugün"
                    }
                    else{
                        year= adapter.keyList.get(linearLayoutManager.findFirstVisibleItemPosition()).substring(0,4)
                    }
                    foodDay.text =  day + mounth + year

            }
        })

        rightArrow.setOnClickListener {
            if(linearLayoutManager.findLastVisibleItemPosition()!=adapter.dayFoodList.size-1){
                foodList.smoothScrollToPosition(linearLayoutManager.findLastVisibleItemPosition()+1)
                day = adapter.keyList.get(linearLayoutManager.findLastVisibleItemPosition()+1).substring(8,10)+" "
                mounth = findMonth(adapter.keyList.get(linearLayoutManager.findLastVisibleItemPosition()+1).substring(5,7))+" "
                if(linearLayoutManager.findLastVisibleItemPosition()+1==1){
                    year= "Yarın"
                }else{
                    year= adapter.keyList.get(linearLayoutManager.findLastVisibleItemPosition()+1).substring(0,4)
                }
                foodDay.text =  day + mounth + year


            }
        }

        leftArrow.setOnClickListener {
            if (linearLayoutManager.findFirstVisibleItemPosition() > 0) {
                foodList.smoothScrollToPosition(linearLayoutManager.findFirstVisibleItemPosition() - 1);
                day = adapter.keyList.get(linearLayoutManager.findLastVisibleItemPosition()-1).substring(8,10)+" "
                mounth = findMonth(adapter.keyList.get(linearLayoutManager.findLastVisibleItemPosition()-1).substring(5,7))+" "
                if(linearLayoutManager.findLastVisibleItemPosition()-1==1){
                    year= "Yarın"
                }else if(linearLayoutManager.findLastVisibleItemPosition()-1==0){
                    year= "Bugün"
                }
                else{
                    year= adapter.keyList.get(linearLayoutManager.findLastVisibleItemPosition()+1).substring(0,4)
                }
                foodDay.text =  day + mounth + year
            } else {
                foodList.smoothScrollToPosition(0);
                day = adapter.keyList.get(0).substring(8,10)+" "
                mounth = findMonth(adapter.keyList.get(0).substring(5,7))+" "
                year= "Bugün"
                foodDay.text =  day + mounth + year

            }
        }



        observeLiveData()

    }

    fun observeLiveData(){
        viewModel.foodList.observe(viewLifecycleOwner,  {
            it?.let{

                val days= hashMapOf<String,ArrayList<Fields>>()
                for(i in 0..it.value.size-1){

                    if(days.get(it.value.get(i).fields.ItemStartDate)?.size==null){
                        val xxx = arrayListOf<Fields>()
                        xxx.add(it.value.get(i).fields)
                        days.put(it.value.get(i).fields.ItemStartDate, xxx)
                    }else{
                        val sss = days.get(it.value.get(i).fields.ItemStartDate)
                        sss?.add(it.value.get(i).fields)
                        if (sss != null) {
                            days.put(it.value.get(i).fields.ItemStartDate, sss)
                        }
                    }


                }
                val keyList = days.keys.sorted()
                day = keyList.get(0).substring(8,10)+" "
                mounth = findMonth(keyList.get(0).substring(5,7))+" "
                year= "Bugün"
                foodDay.text =  day + mounth + year

                adapter.updateDayList(days)


            }

        })
        viewModel.loading.observe(viewLifecycleOwner,  {
            it?.let {
                if(it){

                }else{

                }

            }
        })
        viewModel.error.observe(viewLifecycleOwner,  {
            it?.let {
                if(it){


                }else{

                }
            }
        })


    }

    fun findMonth(x:String):String{
         when (x) {
            "01" -> return "Ocak"
            "02" -> return "Şubat"
            "03" -> return "Mart"
            "04" -> return "Nisan"
            "05" -> return "Mayıs"
            "06" -> return "Haziran"
            "07" -> return "Temmuz"
            "08" -> return "Ağustos"
            "09" -> return "Eylül"
            "10" -> return "Ekim"
            "11" -> return "Kasım"
            "12" -> return "Aralık"
             else -> return " "
        }
    }


}