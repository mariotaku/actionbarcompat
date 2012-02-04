package org.mariotaku.actionbarcompat;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class ActionBarCompatICS extends ActionBarCompat {

	private Activity mActivity;
	private View mCustomView;
	
	public ActionBarCompatICS(Activity activity) {
		mActivity = activity;
	}
	
	@Override
	public void initCompat() {
		
	}
	
	@Override
	public void initActionBar() {
	}

	@Override
	public void setCustomView(View view) {
		mActivity.getActionBar().setCustomView(view);
	}

	@Override
	public void setCustomView(View view, LayoutParams params) {
		ActionBar.LayoutParams p = new ActionBar.LayoutParams(params.width, params.height);
		mActivity.getActionBar().setCustomView(view, p);
		mCustomView = view;
	}

	@Override
	public void setCustomView(int resId) {
		setCustomView(mActivity.getLayoutInflater().inflate(resId, null));
	}

	@Override
	public void setIcon(int resId) {
		mActivity.getActionBar().setIcon(resId);
	}

	@Override
	public void setIcon(Drawable icon) {
		mActivity.getActionBar().setIcon(icon);
	}

	@Override
	public void setLogo(int resId) {
		mActivity.getActionBar().setLogo(resId);
	}

	@Override
	public void setLogo(Drawable logo) {
		mActivity.getActionBar().setLogo(logo);
	}

	@Override
	public void setSelectedNavigationItem(int paramInt) {
	}

	@Override
	public int getSelectedNavigationIndex() {
		return 0;
	}

	@Override
	public int getNavigationItemCount() {
		return 0;
	}

	@Override
	public void setTitle(CharSequence title) {
		mActivity.getActionBar().setTitle(title);
	}

	@Override
	public void setTitle(int resId) {
		mActivity.getActionBar().setTitle(resId);
	}

	@Override
	public void setSubtitle(CharSequence subtitle) {
		mActivity.getActionBar().setSubtitle(subtitle);
	}

	@Override
	public void setSubtitle(int resId) {
		mActivity.getActionBar().setSubtitle(resId);
	}

	@Override
	public void setDisplayOptions(int options) {
		mActivity.getActionBar().setDisplayOptions(options);
	}

	@Override
	public void setDisplayOptions(int options, int mask) {
		mActivity.getActionBar().setDisplayOptions(options, mask);
	}

	@Override
	public void setDisplayUseLogoEnabled(boolean useLogo) {
		mActivity.getActionBar().setDisplayUseLogoEnabled(useLogo);
	}

	@Override
	public void setDisplayShowHomeEnabled(boolean showHome) {
		mActivity.getActionBar().setDisplayShowHomeEnabled(showHome);
	}

	@Override
	public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
		mActivity.getActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp);
	}

	@Override
	public void setDisplayShowTitleEnabled(boolean showTitle) {
		mActivity.getActionBar().setDisplayShowTitleEnabled(showTitle);
	}

	@Override
	public void setDisplayShowCustomEnabled(boolean showCustom) {
		mActivity.getActionBar().setDisplayShowCustomEnabled(showCustom);
	}

	@Override
	public void setBackgroundDrawable(Drawable background) {
	}

	@Override
	public View getCustomView() {
		return mActivity.getActionBar().getCustomView();
	}

	@Override
	public CharSequence getTitle() {
		return mActivity.getActionBar().getTitle();
	}

	@Override
	public CharSequence getSubtitle() {
		return mActivity.getActionBar().getSubtitle();
	}

	@Override
	public int getNavigationMode() {
		return 0;
	}

	@Override
	public void setNavigationMode(int paramInt) {
	}

	@Override
	public int getDisplayOptions() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public boolean isShowing() {
		return mActivity.getActionBar().isShowing();
	}
}
