package com.shenzhen.print;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




public class BaseFragment extends Fragment {
	protected ProgressDialog pDialog;
	protected MyApp myApp;
	protected FragmentManager fm;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		pDialog = new ProgressDialog(getActivity());
		pDialog.setMessage("loading");
		pDialog.setCancelable(false);
		
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
