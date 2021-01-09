package com.example.ezyfood3.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ezyfood3.R;
import com.example.ezyfood3.adapters.DrinkListAdapter;
import com.example.ezyfood3.databinding.FragmentDrinkBinding;
import com.example.ezyfood3.models.Drink;
import com.example.ezyfood3.viewmodels.DrinkViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class DrinkFragment extends Fragment implements DrinkListAdapter.DrinkInterface {

    private static final String TAG = "DrinkFragment";
    FragmentDrinkBinding fragmentDrinkBinding;
    private DrinkListAdapter drinkListAdapter;
    private DrinkViewModel drinkViewModel;
    private NavController navController;

    public DrinkFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentDrinkBinding = FragmentDrinkBinding.inflate(inflater, container, false);
        return fragmentDrinkBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        drinkListAdapter = new DrinkListAdapter(this);
        fragmentDrinkBinding.drinkRecyclerView.setAdapter(drinkListAdapter);
        fragmentDrinkBinding.drinkRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));
        fragmentDrinkBinding.drinkRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.HORIZONTAL));

        drinkViewModel = new ViewModelProvider(requireActivity()).get(DrinkViewModel.class);
        drinkViewModel.getDrinks().observe(getViewLifecycleOwner(), new Observer<List<Drink>>() {
            @Override
            public void onChanged(List<Drink> drinks) {
                drinkListAdapter.submitList(drinks);
            }
        });

        navController = Navigation.findNavController(view);

    }

    @Override
    public void addDrink(Drink drink) {
        boolean isAdded =  drinkViewModel.addDrinkToCart(drink);
        if (isAdded){
            Snackbar.make(requireView(), drink.getName() + " added to cart." , Snackbar.LENGTH_LONG)
                    .setAction("Checkout", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            navController.navigate(R.id.action_drinkFragment_to_cartFragment);
                        }
                    })
            .show();
        }
        else {
            Snackbar.make(requireView(), drink.getName() + " max quantity in cart reached" , Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onDrinkClick(Drink drink) {
        drinkViewModel.setDrink(drink);
        navController.navigate(R.id.action_drinkFragment_to_detailFragment);
    }
}