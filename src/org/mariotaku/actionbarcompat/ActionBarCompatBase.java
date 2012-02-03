package org.mariotaku.actionbarcompat;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActionBarCompatBase extends ActionBarCompat {

	private Activity mActivity;
	private View mCustomViewContainer;
	private View mCustomView;

	public ActionBarCompatBase(Activity activity) {
		mActivity = activity;
	}

	@Override
	public void initCompat() {
		mActivity.getWindow().requestFeature(Window.FEATURE_CUSTOM_TITLE);
		
	}
	
	@Override
	public void initActionBar() {
		mActivity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.actionbarcompat_common);
		mCustomViewContainer = (LinearLayout) mActivity.findViewById(R.id.custom_view_container);
	}

	@Override
	public void setCustomView(View view) {
		setCustomView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	@Override
	public void setCustomView(View view, LayoutParams params) {
		((LinearLayout) mCustomViewContainer).removeAllViews();
		((LinearLayout) mCustomViewContainer).addView(view, params.width, params.height);
		mCustomView = view;
	}

	@Override
	public void setCustomView(int resId) {
		setCustomView(mActivity.getLayoutInflater().inflate(resId, null));
	}

	@Override
	public void setIcon(int resId) {
		((ImageButton)mActivity.findViewById(R.id.home)).setImageResource(resId);
	}

	@Override
	public void setIcon(Drawable icon) {
		((ImageButton)mActivity.findViewById(R.id.home)).setImageDrawable(icon);
	}

	@Override
	public void setLogo(int resId) {
	}

	@Override
	public void setLogo(Drawable logo) {
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
		TextView mTitleView = (TextView) mActivity.findViewById(R.id.title);
		if (mTitleView != null) {
			mTitleView.setText(title);
		}
	}

	@Override
	public void setTitle(int resId) {
		setTitle(mActivity.getString(resId));
	}

	@Override
	public void setSubtitle(CharSequence subtitle) {
		TextView mSubtitleView = (TextView) mActivity.findViewById(R.id.subtitle);
		if (mSubtitleView == null) return;
		if (subtitle != null) {
			mSubtitleView.setVisibility(View.VISIBLE);
			mSubtitleView.setText(subtitle);
		} else {
			mSubtitleView.setVisibility(View.GONE);
			mSubtitleView.setText("");
		}
	}

	@Override
	public void setSubtitle(int resId) {
		setSubtitle(mActivity.getString(resId));
	}

	@Override
	public void setDisplayOptions(int paramInt) {
	}

	@Override
	public void setDisplayOptions(int paramInt1, int paramInt2) {
	}

	@Override
	public void setDisplayUseLogoEnabled(boolean paramBoolean) {
	}

	@Override
	public void setDisplayShowHomeEnabled(boolean enabled) {
		mActivity.findViewById(R.id.home).setVisibility(enabled ? View.VISIBLE : View.GONE);
	}

	@Override
	public void setDisplayHomeAsUpEnabled(boolean paramBoolean) {
	}

	@Override
	public void setDisplayShowTitleEnabled(boolean enabled) {
		mActivity.findViewById(R.id.title_view).setVisibility(enabled ? View.VISIBLE : View.GONE);
	}

	@Override
	public void setDisplayShowCustomEnabled(boolean enabled) {
		mCustomViewContainer.setVisibility(enabled ? View.VISIBLE : View.GONE);
	}

	@Override
	public void setBackgroundDrawable(Drawable paramDrawable) {
	}

	@Override
	public View getCustomView() {
		return mCustomView;
	}

	@Override
	public CharSequence getTitle() {
		return null;
	}

	@Override
	public CharSequence getSubtitle() {
		return null;
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
		return false;
	}
}
