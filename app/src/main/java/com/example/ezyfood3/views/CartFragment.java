package com.example.ezyfood3.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ezyfood3.R;
import com.example.ezyfood3.adapters.CartListAdapter;
import com.example.ezyfood3.databinding.FragmentCartBinding;
import com.example.ezyfood3.models.CartItem;
import com.example.ezyfood3.viewmodels.DrinkViewModel;

import java.util.List;


public class CartFragment extends Fragment implements CartListAdapter.CartInterface {

    private static final String TAG = "CartFragment";
    DrinkViewModel drinkViewModel;
    FragmentCartBinding fragmentCartBinding;
    NavController navController;

    int total;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCartBinding = FragmentCartBinding.inflate(inflater,container,false);
        return fragmentCartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        navController = Navigation.findNavController(view);
        final CartListAdapter cartListAdapter = new CartListAdapter(this);
        fragmentCartBinding.cartRecyclerView.setAdapter(cartListAdapter);
        fragmentCartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));

        drinkViewModel = new ViewModelProvider(requireActivity()).get(DrinkViewModel.class);
        drinkViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                cartListAdapter.submitList(cartItems);
                fragmentCartBinding.placeOrderButton.setEnabled(cartItems.size() > 0);
            }
        });

        drinkViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fragmentCartBinding.orderTotalText.setText("Total: Rp " + aDouble.toString());


            }
        });

        fragmentCartBinding.placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int balance = sharedPreferences.getInt("saldo",0);

                drinkViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
                    @Override
                    public void onChanged(Double aDouble) {
                        int balance = sharedPreferences.getInt("saldo",0);

                        if ( balance < aDouble) {
                            Log.d(TAG, "onChanged: Kurang duitnya");
                            Toast.makeText(getContext(), "Balance not enough", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d(TAG, "onChanged: CUKUPPP duitnya");
                            editor.putInt("saldo", (int) (balance-aDouble));
                            editor.apply();
                            navController.navigate(R.id.action_cartFragment_to_orderFragment);
                         //   getActivity().finish();
                        }

                    }
                });


            }
        });

    }

    @Override
    public void deleteItem(CartItem cartItem) {
        Log.d(TAG, "deleteItem: " + cartItem.getDrink().getName());
        drinkViewModel.removeItemFromCart(cartItem);
    }

    @Override
    public void changeQuantity(CartItem cartItem, int quantity) {
        drinkViewModel.changeQuantity(cartItem,quantity);
    }
}