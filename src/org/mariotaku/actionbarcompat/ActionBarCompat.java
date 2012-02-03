package org.mariotaku.actionbarcompat;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class ActionBarCompat {

	public static ActionBarCompat getInstance(Activity activity) {
		if (activity == null) return null;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return new ActionBarCompatICS(activity);
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			return new ActionBarCompatHoneycomb(activity);
		} else {
			return new ActionBarCompatBase(activity);
		}
	}

	public void initCompat() {

	}
	
	public void initActionBar() {
		
	}

	public void setCustomView(View paramView) {
	}

	public void setCustomView(View paramView, LayoutParams paramLayoutParams) {
	}

	public void setCustomView(int paramInt) {
	}

	public void setIcon(int resId) {
	}

	public void setIcon(Drawable icon) {
	}

	public void setLogo(int resId) {
	}

	public void setLogo(Drawable logo) {
	}

	public void setSelectedNavigationItem(int paramInt) {
	}

	public int getSelectedNavigationIndex() {
		return 0;
	}

	public int getNavigationItemCount() {
		return 0;
	}

	public void setTitle(CharSequence paramCharSequence) {
	}

	public void setTitle(int paramInt) {
	}

	public void setSubtitle(CharSequence paramCharSequence) {
	}

	public void setSubtitle(int paramInt) {
	}

	public void setDisplayOptions(int paramInt) {
	}

	public void setDisplayOptions(int paramInt1, int paramInt2) {
	}

	public void setDisplayUseLogoEnabled(boolean paramBoolean) {
	}

	public void setDisplayShowHomeEnabled(boolean paramBoolean) {
	}

	public void setDisplayHomeAsUpEnabled(boolean paramBoolean) {
	}

	public void setDisplayShowTitleEnabled(boolean paramBoolean) {
	}

	public void setDisplayShowCustomEnabled(boolean paramBoolean) {
	}

	public void setBackgroundDrawable(Drawable paramDrawable) {
	}

	public View getCustomView() {
		return null;
	}

	public CharSequence getTitle() {
		return null;
	}

	public CharSequence getSubtitle() {
		return null;
	}

	public int getNavigationMode() {
		return 0;
	}

	public void setNavigationMode(int paramInt) {
	}

	public int getDisplayOptions() {
		return 0;
	}

	public int getHeight() {
		return 0;
	}

	public void show() {
	}

	public void hide() {
	}

	public boolean isShowing() {
		return false;
	}

}
