package com.example.ezyfood3.models;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class Drink {
    private String id;
    private String name;
    private double price;
    private String imageUrl;

    public Drink(String id, String name, double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return Double.compare(drink.getPrice(), getPrice()) == 0 &&
                getId().equals(drink.getId()) &&
                getName().equals(drink.getName()) &&
                getImageUrl().equals(drink.getImageUrl());
    }

    public static DiffUtil.ItemCallback<Drink> itemCallback = new DiffUtil.ItemCallback<Drink>() {
        @Override
        public boolean areItemsTheSame(@NonNull Drink oldItem, @NonNull Drink newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Drink oldItem, @NonNull Drink newItem) {
            return oldItem.equals(newItem);
        }
    };

    @BindingAdapter("android:drinkImage")
    public static void loadImage(ImageView imageView, String imageUrl){
        Glide.with(imageView)
                .load(imageUrl)
                .fitCenter()
                .into(imageView);
    }

}
