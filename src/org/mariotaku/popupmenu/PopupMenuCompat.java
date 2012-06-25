package org.mariotaku.popupmenu;

import org.mariotaku.actionbarcompat.R;
import org.mariotaku.internal.menu.MenuAdapter;
import org.mariotaku.internal.menu.MenuImpl;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

public class PopupMenuCompat extends PopupMenu implements OnDismissListener, OnItemClickListener, OnTouchListener {
	private ListView mListView;
	private View mRootView;

	private OnMenuItemClickListener mItemClickListener;
	private OnDismissListener mDismissListener;

	private Menu mMenu;
	private final Context mContext;
	private final View mView;
	private PopupWindow mWindow;
	private WindowManager mWindowManager;

	private boolean mDidAction;

	private int rootWidth = 0, mPosX, mPosY;

	private MenuAdapter mAdapter;

	private OnTouchListener mViewTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			return true;
		}

	};

	/**
	 * Constructor for default vertical layout
	 * 
	 * @param context Context
	 */
	public PopupMenuCompat(Context context, View view) {
		mContext = context;
		mView = view;
		mWindow = new PopupWindow(context);
		mWindow.setTouchInterceptor(this);
		mWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
		mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		mAdapter = new MenuAdapter(context);
		mMenu = new MenuImpl(mContext, mAdapter);
		setView();

	}

	/**
	 * Dismiss the popup window.
	 */
	@Override
	public void dismiss() {
		if (isPopupWindowShowing()) {
			mWindow.dismiss();
		}
	}

	private boolean isPopupWindowShowing() {
		if (mWindow == null) return false;
		return mWindow.isShowing();
	}
	
	@Override
	public Menu getMenu() {
		return mMenu;
	}

	public MenuInflater getMenuInflater() {
		return new MenuInflater(mContext);
	}
	
	@Override
	public void inflate(int menuRes) {
		new MenuInflater(mContext).inflate(menuRes, mMenu);
	}

	@Override
	public void onDismiss() {
		if (!mDidAction && mDismissListener != null) {
			mDismissListener.onDismiss(this);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		mDidAction = true;

		final MenuItem item = mAdapter.getItem(position);
		if (item.hasSubMenu()) {
			dismiss();
			showMenu(item.getSubMenu(), false);
		} else {
			if (mItemClickListener != null) {
				mItemClickListener.onMenuItemClick(item);
				dismiss();
			}
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
			mWindow.dismiss();

			return true;
		}

		return false;
	}

	public void setAnchorByTouch(boolean enabled) {
		mView.setOnTouchListener(enabled ? mViewTouchListener : null);
	}

	@Override
	public void setMenu(Menu menu) {
		mMenu = menu;
	}

	/**
	 * Set listener for window dismissed. This listener will only be fired if
	 * the quickaction dialog is dismissed by clicking outside the dialog or
	 * clicking on sticky item.
	 */
	@Override
	public void setOnDismissListener(PopupMenu.OnDismissListener listener) {
		mWindow.setOnDismissListener(listener != null ? this : null);

		mDismissListener = listener;
	}

	/**
	 * Set listener for action item clicked.
	 * 
	 * @param listener Listener
	 */
	@Override
	public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
		mItemClickListener = listener;
	}

	@Override
	public void show() {
		if (isPopupWindowShowing()) {
			dismiss();
		}
		showMenu(getMenu(), true);
	}

	/**
	 * On pre show
	 */
	private void preShow() {
		if (mRootView == null)
			throw new IllegalStateException("setContentView was not called with a view to display.");

		mWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

		mWindow.setWidth(mContext.getResources().getDimensionPixelSize(R.dimen.popup_window_width));
		mWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
		mWindow.setTouchable(true);
		mWindow.setFocusable(true);
		mWindow.setOutsideTouchable(true);

		mWindow.setContentView(mRootView);
	}

	@SuppressWarnings("deprecation")
	private void setAnchor(View anchor) {
		preShow();

		View rootView = mView.getRootView();
		if (rootView == null) rootView = mView;
		
		mDidAction = false;

		final int[] location = new int[2];
		anchor.getLocationInWindow(location);
		
		final int[] location2 = new int[2];

		rootView.getLocationOnScreen(location2);
		final Rect rootRect = new Rect(location2[0], location2[1], location2[0] + rootView.getWidth(), location2[1] + rootView.getHeight());
		
		
		final Rect visibleRect = new Rect();
		anchor.getGlobalVisibleRect(visibleRect);
		

		final Rect anchorRect = new Rect(location[0], location[1], location[0] + anchor.getWidth(), location[1]
				+ anchor.getHeight());
		
		if (rootRect.top - visibleRect.top == 0 && rootRect.bottom - visibleRect.top > 0) {
			mGravity = Gravity.TOP;
			if (rootRect.left - visibleRect.right == 0) {
				mWindow.setAnimationStyle(R.style.Animations_PopDownMenu_Left);
			} else if (rootRect.right - visibleRect.right == 0) {
				mWindow.setAnimationStyle(R.style.Animations_PopDownMenu_Right);
			}
		} else if (rootRect.bottom - visibleRect.top == 0 && rootRect.top - visibleRect.top > 0) {
			mGravity = Gravity.BOTTOM;
			if (rootRect.left - visibleRect.right == 0) {
				mWindow.setAnimationStyle(R.style.Animations_PopUpMenu_Left);
			} else if (rootRect.right - visibleRect.right == 0) {
				mWindow.setAnimationStyle(R.style.Animations_PopUpMenu_Right);
			}
		}


		if (rootWidth == 0) {
			rootWidth = mRootView.getMeasuredWidth();
		}

		final Display display = mWindowManager.getDefaultDisplay();
		final int screenWidth = display.getWidth();

		// automatically get X coord of popup (top left)
		if (anchorRect.left + rootWidth > screenWidth) {
			mPosX = anchorRect.left - (rootWidth - anchor.getWidth());
			mPosX = mPosX < 0 ? 0 : mPosX;

		} else {
			if (anchor.getWidth() > rootWidth) {
				mPosX = anchorRect.centerX() - rootWidth / 2;
			} else {
				mPosX = anchorRect.left;
			}

		}

		mPosY = visibleRect.top;

		//setAnimationStyle(screenWidth, anchorRect.centerX(), onTop);

	}

	/**
	 * Set animation style
	 * 
	 * @param screenWidth screen width
	 * @param requestedX distance from left edge
	 * @param onTop flag to indicate where the popup should be displayed. Set
	 *            TRUE if displayed on top of anchor view and vice versa
	 */
	private void setAnimationStyle(int screenWidth, int requestedX, boolean onTop) {

		if (requestedX <= screenWidth / 4) {
			mWindow.setAnimationStyle(onTop ? R.style.Animations_PopUpMenu_Left : R.style.Animations_PopDownMenu_Left);
		} else if (requestedX > screenWidth / 4 && requestedX < 3 * (screenWidth / 4)) {
			mWindow.setAnimationStyle(onTop ? R.style.Animations_PopUpMenu_Center
					: R.style.Animations_PopDownMenu_Center);
		} else {
			mWindow.setAnimationStyle(onTop ? R.style.Animations_PopUpMenu_Right : R.style.Animations_PopDownMenu_Right);
		}
	}

	/**
	 * Set root view.
	 * 
	 */
	private void setView() {
		mRootView = LayoutInflater.from(mContext).inflate(R.layout.popup_list, null);
		mListView = (ListView) mRootView.findViewById(android.R.id.list);

		// This was previously defined on show() method, moved here to prevent
		// force close that occured
		// when tapping fastly on a view to show quickaction dialog.
		// Thanx to zammbi (github.com/zammbi)
		mRootView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
		mWindow.setContentView(mRootView);
	}
private int mGravity = Gravity.NO_GRAVITY;
	private void showMenu(Menu menu, boolean set_anchor) {
		mAdapter.setMenu(menu);
		if (set_anchor) {
			setAnchor(mView);
		}
		mWindow.showAtLocation(mView, mGravity, mPosX, mPosY);
	}

}
