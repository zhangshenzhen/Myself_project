package com.shenzhen.print;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity {

    private PrinterFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new PrinterFragment();
        addLayoutFragment(R.id.frm, fragment, false);
    }

    public void addLayoutFragment(int layout, Fragment fragment, boolean add) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.replace(layout, fragment);
        if (add) {
            ft.addToBackStack(null);
        }
        ft.commitAllowingStateLoss();
    }
}