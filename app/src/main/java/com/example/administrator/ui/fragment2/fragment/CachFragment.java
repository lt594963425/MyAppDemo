package com.example.administrator.ui.fragment2.fragment;

import android.support.v4.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.R;

public class CachFragment extends Fragment {
	// ɨ�����
	protected static final int SUCCESS = 1;
	// ���ֻ���
	protected static final int FOND = 2;
	// ����ɨ��
	protected static final int SCANING = 3;
	private ProgressBar pb;
	private TextView tv_scan_status;
	private LinearLayout ll_container;
	private Button bt_clean_all;
	private PackageManager pm;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = View.inflate(getActivity(), R.layout.fragment_clean_cach,
				null);
		pb = (ProgressBar) view.findViewById(R.id.pb);
		tv_scan_status = (TextView) view.findViewById(R.id.tv_scan_stat);
		ll_container = (LinearLayout) view.findViewById(R.id.ll_container);
		bt_clean_all = (Button) view.findViewById(R.id.bt_clean_all);
		pm = getActivity().getPackageManager();
		return view;
	}

}
