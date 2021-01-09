package com.example.ezyfood3.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ezyfood3.models.Drink;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopRepository {

    private MutableLiveData<List<Drink>> mutableDrinkList;

    public LiveData<List<Drink>> getDrinks(){
        if (mutableDrinkList == null){
            mutableDrinkList = new MutableLiveData<>();
            loadDrinks();
        }
        return mutableDrinkList;
    }

    private void loadDrinks(){
        List<Drink> drinkList = new ArrayList<>();
        drinkList.add(new Drink(UUID.randomUUID().toString(), "Apple juice", 10000, "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/apple-cider-vs-apple-juice-difference-1565205829.jpg?crop=1.00xw:0.753xh;0,0.247xh&resize=1200:*"));
        drinkList.add(new Drink(UUID.randomUUID().toString(), "Air Mineral", 5000, "https://cf.shopee.co.id/file/56096517df59a7555d47eefe827091d9"));
        drinkList.add(new Drink(UUID.randomUUID().toString(), "Avocado Juice", 15000, "https://chocolatecoveredkatie.com/wp-content/uploads/2019/07/EASY-Creamy-Avocado-Smoothie-Recipe-500x500.jpg"));
        drinkList.add(new Drink(UUID.randomUUID().toString(), "Mango Juice", 20000, "https://www.spiceupthecurry.com/wp-content/uploads/2014/06/Mango-juice-recipe-3.jpg"));

        mutableDrinkList.setValue(drinkList);
    }
}
