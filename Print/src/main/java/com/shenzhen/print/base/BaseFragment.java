package com.shenzhen.print.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shenzhen.print.MyApp;


public class BaseFragment extends Fragment {

	protected MyApp myApp;
	protected FragmentManager fm;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

		
		myApp = (MyApp) getActivity().getApplication();
		fm = getActivity().getFragmentManager();
	}
	
	public void addLayoutFragment(int layout, Fragment fragment) {
		addLayoutFragment(layout, fragment, true);
	}

	public void addLayoutFragment(int layout, Fragment fragment, boolean addToBack){
		FragmentManager fm = getActivity().getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(layout, fragment);
		if (addToBack) {
			ft.addToBackStack(null);
		}
		ft.commitAllowingStateLoss();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
	@Override
	public void onResume() {
		super.onResume();
	}
	@Override
	public void onPause() {
		super.onPause();
	}
}
