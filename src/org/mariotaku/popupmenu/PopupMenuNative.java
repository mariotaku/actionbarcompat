package org.mariotaku.popupmenu;

import android.view.Menu;
import android.content.Context;
import android.view.View;
import android.annotation.TargetApi;
import android.view.MenuItem;
import android.view.SubMenu;

@TargetApi(11)
public class PopupMenuNative extends PopupMenu {


	final android.widget.PopupMenu mPopupMenu;

	PopupMenuNative(Context context, View view) {
		mPopupMenu = new android.widget.PopupMenu(context, view);
		mPopupMenu.setOnMenuItemClickListener(mOnMenuItemClickListenerNative);
		mPopupMenu.setOnDismissListener(mOnDismissListenerNative);
	}

	public void dismiss() {
		mPopupMenu.dismiss();
	}

	public Menu getMenu() {
		return mPopupMenu.getMenu();
	}

	public void inflate(int menuRes) {
		mPopupMenu.inflate(menuRes);
	}

	public void setMenu(Menu menu) {
		final Menu popup_menu = getMenu();
		popup_menu.clear();
		for (int i = 0; i < menu.size(); i++) {
			final MenuItem item = menu.getItem(i);
			if (!item.hasSubMenu()) {
				final MenuItem addedItem = popup_menu.add(item.getGroupId(), item.getItemId(), item.getOrder(), item.getTitle());
				addedItem.setIcon(item.getIcon());
			} else {
				addSubMenu(menu, item.getSubMenu());
			}
		}
	}

	private void addSubMenu(Menu menu, SubMenu subMenu) {
		final MenuItem subItem = subMenu.getItem();
		final SubMenu addedSubMenu = menu.addSubMenu(subItem.getGroupId(), subItem.getItemId(), subItem.getOrder(), subItem.getTitle());
		for (int i = 0; i < subMenu.size(); i++) {
			final MenuItem item = subMenu.getItem(i);
			if (!item.hasSubMenu()) {
				final MenuItem addedItem = addedSubMenu.add(item.getGroupId(), item.getItemId(), item.getOrder(), item.getTitle());
				addedItem.setIcon(item.getIcon());
			} else {
				 addSubMenu(addedSubMenu, item.getSubMenu());
			}
		}
	}
	
	public void setOnDismissListener(PopupMenu.OnDismissListener listener) {
		mOnDismissListener = listener;
	}

	public void setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener listener) {
		mOnMenuItemClickListener = listener;
	}
	
	private android.widget.PopupMenu.OnMenuItemClickListener mOnMenuItemClickListenerNative = new android.widget.PopupMenu.OnMenuItemClickListener() {

		public boolean onMenuItemClick(MenuItem item) {
			if (mOnMenuItemClickListener == null) return false;
			return mOnMenuItemClickListener.onMenuItemClick(item);
		}

	};


	private android.widget.PopupMenu.OnDismissListener mOnDismissListenerNative = new android.widget.PopupMenu.OnDismissListener() {

		public void onDismiss(android.widget.PopupMenu popup) {
			if (mOnDismissListener == null) return;
			mOnDismissListener.onDismiss(PopupMenuNative.this);
		}

	};
	
	private PopupMenu.OnMenuItemClickListener mOnMenuItemClickListener;
	private PopupMenu.OnDismissListener mOnDismissListener;
	
	public void show() {
		mPopupMenu.show();
	}

	public void show(boolean above_view) {
		show();
	}

}
