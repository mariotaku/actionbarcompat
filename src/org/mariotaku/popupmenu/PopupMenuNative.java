package org.mariotaku.popupmenu;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

@TargetApi(14)
public class PopupMenuNative extends PopupMenu {

	final android.widget.PopupMenu mPopupMenu;
	final Context mContext;

	private android.widget.PopupMenu.OnMenuItemClickListener mOnMenuItemClickListenerNative = new android.widget.PopupMenu.OnMenuItemClickListener() {

		@Override
		public boolean onMenuItemClick(MenuItem item) {
			if (mOnMenuItemClickListener == null) return false;
			return mOnMenuItemClickListener.onMenuItemClick(item);
		}

	};

	private android.widget.PopupMenu.OnDismissListener mOnDismissListenerNative = new android.widget.PopupMenu.OnDismissListener() {

		@Override
		public void onDismiss(android.widget.PopupMenu popup) {
			if (mOnDismissListener == null) return;
			mOnDismissListener.onDismiss(PopupMenuNative.this);
		}

	};

	private PopupMenu.OnMenuItemClickListener mOnMenuItemClickListener;

	private PopupMenu.OnDismissListener mOnDismissListener;

	PopupMenuNative(Context context, View view) {
		mContext = context;
		mPopupMenu = new android.widget.PopupMenu(context, view);
		mPopupMenu.setOnMenuItemClickListener(mOnMenuItemClickListenerNative);
		mPopupMenu.setOnDismissListener(mOnDismissListenerNative);
	}

	public MenuInflater getMenuInflater() {
		return new MenuInflater(mContext);
	}
	
	@Override
	public void dismiss() {
		mPopupMenu.dismiss();
	}

	@Override
	public Menu getMenu() {
		return mPopupMenu.getMenu();
	}

	@Override
	public void inflate(int menuRes) {
		mPopupMenu.getMenuInflater().inflate(menuRes, getMenu());
	}
	
	@Override
	public void setMenu(Menu menu) {
		final Menu popup_menu = getMenu();
		popup_menu.clear();
		for (int i = 0; i < menu.size(); i++) {
			final MenuItem item = menu.getItem(i);
			if (!item.hasSubMenu()) {
				final MenuItem addedItem = popup_menu.add(item.getGroupId(), item.getItemId(), item.getOrder(),
						item.getTitle());
				addedItem.setIcon(item.getIcon());
			} else {
				addSubMenu(menu, item.getSubMenu());
			}
		}
	}

	@Override
	public void setOnDismissListener(PopupMenu.OnDismissListener listener) {
		mOnDismissListener = listener;
	}

	@Override
	public void setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener listener) {
		mOnMenuItemClickListener = listener;
	}

	@Override
	public void show() {
		mPopupMenu.show();
	}

	private void addSubMenu(Menu menu, SubMenu subMenu) {
		final MenuItem subItem = subMenu.getItem();
		final SubMenu addedSubMenu = menu.addSubMenu(subItem.getGroupId(), subItem.getItemId(), subItem.getOrder(),
				subItem.getTitle());
		for (int i = 0; i < subMenu.size(); i++) {
			final MenuItem item = subMenu.getItem(i);
			if (!item.hasSubMenu()) {
				final MenuItem addedItem = addedSubMenu.add(item.getGroupId(), item.getItemId(), item.getOrder(),
						item.getTitle());
				addedItem.setIcon(item.getIcon());
			} else {
				addSubMenu(addedSubMenu, item.getSubMenu());
			}
		}
	}

}
