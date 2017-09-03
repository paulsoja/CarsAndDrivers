package com.paulsojaoutlook.pavelsoya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.paulsojaoutlook.pavelsoya.fragment.TitleFragment;

public class MainActivity extends AppCompatActivity{

    TitleFragment titleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleFragment = new TitleFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Content, titleFragment)
                .addToBackStack(null)
                .commit();
    }
}
