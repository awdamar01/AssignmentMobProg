package com.example.ezyfood3.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ezyfood3.models.CartItem;
import com.example.ezyfood3.models.Drink;
import com.example.ezyfood3.repositories.CartRepository;
import com.example.ezyfood3.repositories.ShopRepository;

import java.util.List;

public class DrinkViewModel extends ViewModel {

    ShopRepository shopRepository = new ShopRepository();
    CartRepository cartRepository = new CartRepository();

    MutableLiveData<Drink> mutableDrink = new MutableLiveData<>();

    public LiveData<List<Drink>> getDrinks(){
        return shopRepository.getDrinks();
    }

    public void setDrink(Drink drink){
        mutableDrink.setValue(drink);
    }

    public LiveData<Drink> getDrink(){
        return mutableDrink;
    }

    public LiveData<List<CartItem>> getCart(){
        return cartRepository.getCart();
    }

    public boolean addDrinkToCart(Drink drink){
        return  cartRepository.addDrinkToCart(drink);
    }

    public void removeItemFromCart(CartItem cartItem){
        cartRepository.removeItemFromCart(cartItem);
    }

    public void changeQuantity(CartItem cartItem, int quantity){
        cartRepository.changeQuantity(cartItem,quantity);
    }

    public LiveData<Double> getTotalPrice(){
        return cartRepository.getTotalPrice();
    }

    public void resetCart(){
        cartRepository.initCart();
    }

}
