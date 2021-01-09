package com.example.ezyfood3.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ezyfood3.R;
import com.example.ezyfood3.databinding.ActivityTopUpBinding;
import com.example.ezyfood3.models.Balance;

import static com.example.ezyfood3.storage.ListBalance.listBalance;

public class TopUpActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityTopUpBinding binding;

    private int topUpBalance, totalBalance;
    private String sBalance;

    public static String TAG = TopUpActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTopUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        Balance currBalance = intent.getParcelableExtra("CURR_BALANCE");
        totalBalance = currBalance.getBalance();
        Log.d(TAG, "onCreate: " + totalBalance);

        sBalance = String.valueOf(totalBalance);

        binding.idBalance.setText(sBalance);

        binding.btnTopUp.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view == binding.btnTopUp) {
            if (isNotEmpty()) {
                topUpBalance = Integer.parseInt(binding.editTextBalance.getText().toString());

                totalBalance += topUpBalance;
                Log.d(TAG, "onClick: " + totalBalance + " " + topUpBalance);
                Balance balance = new Balance(totalBalance);
                listBalance.add(balance);

                Intent intent = new Intent(TopUpActivity.this, MainActivity.class);
                intent.putExtra("Saldo", balance);
                setResult(RESULT_OK, intent);
                finish();


            }
        }
    }

    private boolean isNotEmpty() {
        if (binding.editTextBalance.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }
}