package com.codespurt.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.codespurt.fragment.fragments.Fragment1;
import com.codespurt.fragment.fragments.Fragment2;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button fragment1, fragment2;

    // fragments
    Fragment sampleFragment1 = new Fragment1();
    Fragment sampleFragment2 = new Fragment2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initFragment();
    }

    private void initViews() {
        fragment1 = (Button) findViewById(R.id.btn_1);
        fragment2 = (Button) findViewById(R.id.btn_2);
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, sampleFragment1);
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fragment1.setOnClickListener(this);
        fragment2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                doTransaction(sampleFragment1);
                break;
            case R.id.btn_2:
                doTransaction(sampleFragment2);
                break;
        }
    }

    private void doTransaction(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        fragment1.setOnClickListener(null);
        fragment2.setOnClickListener(null);
    }
}
