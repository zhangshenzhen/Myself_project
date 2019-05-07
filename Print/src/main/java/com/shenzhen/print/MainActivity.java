package com.shenzhen.print;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shenzhen.print.base.BaseActivity;

import cn.koolcloud.engine.service.aidl.IPrinterService;

public class MainActivity extends FragmentActivity {

    private PrinterFragment2 fragment;
    private CheckVersionUpdate checkVersionUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new PrinterFragment2();
        addLayoutFragment(R.id.frm, fragment, false);

       // callBindService();
        checkVersionUpdate = new CheckVersionUpdate(this);
        //屏蔽更新功能
       // checkVersionUpdate.callBindService();
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //callUnbindService();
        SingletonPrint.unRigiest();//注销
        checkVersionUpdate.callUnbindService();//解绑
    }
}
