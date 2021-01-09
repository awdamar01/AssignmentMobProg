package com.example.ezyfood3.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ezyfood3.R;
import com.example.ezyfood3.databinding.FragmentDetailBinding;
import com.example.ezyfood3.viewmodels.DrinkViewModel;


public class DetailFragment extends Fragment {

    FragmentDetailBinding fragmentDetailBinding;
    DrinkViewModel drinkViewModel;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentDetailBinding = FragmentDetailBinding.inflate(inflater,container,false);
        return fragmentDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        drinkViewModel = new ViewModelProvider(requireActivity()).get(DrinkViewModel.class);
        fragmentDetailBinding.setDrinkViewModel(drinkViewModel);
    }
}