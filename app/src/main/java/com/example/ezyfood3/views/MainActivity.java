package com.example.ezyfood3.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ezyfood3.R;
import com.example.ezyfood3.databinding.ActivityMainBinding;
import com.example.ezyfood3.models.Balance;
import com.example.ezyfood3.storage.ListBalance;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ActivityMainBinding binding;

    int balanceResult = 0;

    public static String TAG = MainActivity.class.getSimpleName();

    String saldo;
    int balancePref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SharedPreferences sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        balancePref = sharedPreferences.getInt("saldo", 0);
        String sBalancePref = String.valueOf(balancePref);
        binding.idActivityMainBalance.setText(sBalancePref);

        binding.topUp.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        balancePref = sharedPreferences.getInt("saldo", 0);
        Log.d(TAG, "onResume: " + balancePref);
        String sBalancePref = String.valueOf(balancePref);
        binding.idActivityMainBalance.setText(sBalancePref);
    }

    public void drinkButtonClick(View view1){
        Intent intent1 = new Intent(this, drink.class);
        startActivity(intent1);
    }

    @Override
    public void onClick(View view) {


        if ( view == binding.topUp) {
            Intent intent = new Intent(this, TopUpActivity.class);
            Log.d(TAG, "onClick: " + balanceResult);
            Balance balance = new Balance(balancePref);
            intent.putExtra("CURR_BALANCE", balance);
            startActivityForResult(intent, 2000);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        SharedPreferences sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if ( requestCode == 2000) {
            if (resultCode == RESULT_OK) {
                Balance balance = data.getParcelableExtra("Saldo");
                balanceResult = balance.getBalance();
                ListBalance.listBalance.add(balance);
                saldo = String.valueOf(balance.getBalance());
         //       binding.idActivityMainBalance.setText(saldo);

                editor.putInt("saldo", balanceResult);
                editor.apply();

                int balancePref = sharedPreferences.getInt("saldo", 0);
                String sBalancePref = String.valueOf(balancePref);
                binding.idActivityMainBalance.setText(sBalancePref);
            }
        }
    }
}