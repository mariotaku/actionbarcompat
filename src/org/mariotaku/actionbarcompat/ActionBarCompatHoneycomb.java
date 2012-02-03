package org.mariotaku.actionbarcompat;

import android.app.ActionBar;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class ActionBarCompatHoneycomb extends ActionBarCompat {

	private Activity mActivity;

	public ActionBarCompatHoneycomb(Activity activity) {
		mActivity = activity;
	}

	public View getCustomView() {
		return mActivity.getActionBar().getCustomView();
	}

	public void setCustomView(View view) {
		mActivity.getActionBar().setCustomView(view);
	}

	public void setCustomView(int resId) {
		mActivity.getActionBar().setCustomView(resId);
	}

	public void setCustomView(View view, LayoutParams params) {
		mActivity.getActionBar().setCustomView(view, (ActionBar.LayoutParams) params);
	}
}
