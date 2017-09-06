package com.paulsojaoutlook.pavelsoya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.paulsojaoutlook.pavelsoya.fragment.TitleFragment;

public class MainActivity extends AppCompatActivity{

    public static final String TAG_ACTIVITY = "TAG_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            TitleFragment titleFragment = new TitleFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.Frame_Content, titleFragment, TAG_ACTIVITY)
                    .commit();
        }
    }
}
