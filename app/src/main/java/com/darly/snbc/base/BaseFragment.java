/**
 * 下午2:33:01
 * @author zhangyh2
 * $
 * BaseActivity.java
 * TODO
 */
package com.darly.snbc.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * @author zhangyh2 BaseActivity $ 下午2:33:01 TODO
 */
public abstract class BaseFragment extends Fragment {

	FragmentManager fragmentManager;

	public View rootView;

	public OnShowLoadingListener onShowLoadingListener;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(root(), container, false);
		return rootView;
	}

	protected abstract int root();

	public void setOnShowLoadingListener(OnShowLoadingListener onShowLoadingListener) {
		this.onShowLoadingListener = onShowLoadingListener;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initGlobe();
		initView(savedInstanceState);

		loadData();

		initListener();

	}

	/**
	 * 下午4:06:44
	 * 
	 * @author zhangyh2 TODO
	 */
	private void initGlobe() {
		// TODO Auto-generated method stub
	}

	/**
	 * @param savedInstanceState
	 *            下午2:34:08
	 * @author zhangyh2 BaseActivity.java TODO 初始化控件
	 */
	protected abstract void initView(Bundle savedInstanceState);

	/**
	 * 
	 * 下午2:34:10
	 * 
	 * @author zhangyh2 BaseActivity.java TODO 加载数据
	 */
	protected abstract void loadData();

	/**
	 * 
	 * 下午2:42:02
	 * 
	 * @author zhangyh2 BaseFragment.java TODO 初始化坚挺事件
	 */
	protected abstract void initListener();


	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}


	public void initFragments(Fragment fragment, Class<?> cls, int resId) {
		fragmentManager = getActivity().getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		if (fragment == null) {
			try {
				fragment = (Fragment) cls.newInstance();
				transaction.add(resId, fragment);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			if (fragment.isVisible())
				return;
			transaction.show(fragment);
		}
		Bundle args = new Bundle();
		fragment.setArguments(args);
		transaction.commit();
	}


	@Override
	public void onDestroy() {
		super.onDestroy();
	}


	public interface OnShowLoadingListener{
		void onFragmentStart();

		void onFragmentFinish();
	}
}
