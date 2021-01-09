package com.example.ezyfood3.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezyfood3.databinding.DrinkRowBinding;
import com.example.ezyfood3.models.Drink;

public class DrinkListAdapter extends ListAdapter<Drink, DrinkListAdapter.DrinkViewHolder> {

    DrinkInterface drinkInterface;
    public DrinkListAdapter(DrinkInterface drinkInterface) {
        super(Drink.itemCallback);
        this.drinkInterface = drinkInterface;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        DrinkRowBinding drinkRowBinding = DrinkRowBinding.inflate(layoutInflater,parent,false);
        drinkRowBinding.setDrinkInterface(drinkInterface);
        return new DrinkViewHolder(drinkRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, int position) {
        Drink drink = getItem(position);
        holder.drinkRowBinding.setDrink(drink);
        holder.drinkRowBinding.executePendingBindings();
    }

    class DrinkViewHolder extends RecyclerView.ViewHolder{
        DrinkRowBinding drinkRowBinding;

        public DrinkViewHolder(DrinkRowBinding binding) {
            super(binding.getRoot());
            this.drinkRowBinding = binding;
        }
    }

    public interface DrinkInterface{
        void addDrink(Drink drink);
        void onDrinkClick(Drink drink);

    }


}
