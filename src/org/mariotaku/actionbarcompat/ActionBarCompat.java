package org.mariotaku.actionbarcompat;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Menu;
import android.view.MenuInflater;
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

	public boolean initCompat() {
		return true;
	}
	
	public boolean initActionBar() {
		return true;
	}

	public void setCustomView(View view) {
	}

	public void setCustomView(View view, LayoutParams params) {
	}

	public void setCustomView(int resId) {
	}

	public void setIcon(int resId) {
	}

	public void setIcon(Drawable icon) {
	}

	public void setLogo(int resId) {
	}

	public void setLogo(Drawable logo) {
	}

	public void setSelectedNavigationItem(int item) {
	}

	public int getSelectedNavigationIndex() {
		return 0;
	}

	public int getNavigationItemCount() {
		return 0;
	}

	public void setTitle(CharSequence title) {
	}

	public void setTitle(int resId) {
	}

	public void setSubtitle(CharSequence subtitle) {
	}

	public void setSubtitle(int resId) {
	}

	public void setDisplayOptions(int options) {
	}

	public void setDisplayOptions(int options, int mask) {
	}

	public void setDisplayUseLogoEnabled(boolean useLogo) {
	}

	public void setDisplayShowHomeEnabled(boolean sShowHome) {
	}

	public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
	}

	public void setDisplayShowTitleEnabled(boolean showTitle) {
	}

	public void setDisplayShowCustomEnabled(boolean showCustom) {
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
	
	/**
	 * Returns a {@link MenuInflater} for use when inflating menus. The
	 * implementation of this method in {@link ActionBarHelperBase} returns a
	 * wrapped menu inflater that can read action bar metadata from a menu
	 * resource pre-Honeycomb.
	 */
	public MenuInflater getMenuInflater(MenuInflater superMenuInflater) {
		return superMenuInflater;
	}

	/**
	 * Sets the indeterminate loading state of the item with ID
	 * {@link R.id.menu_refresh}. (where the item ID was menu_refresh).
	 */
	public void setRefreshActionItemState(boolean refreshing) {
		
	}

	public boolean hideMenuInActionBar(Menu menu) {
		return false;
	}
	
}
