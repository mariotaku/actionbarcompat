package org.mariotaku.popupmenu;

import org.mariotaku.actionbarcompat.R;
import org.mariotaku.popupmenu.PopupMenu.OnMenuItemClickListener;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class MenuBar extends TableRow {

	private Menu mMenu;
	private final Context mContext;
	private OnMenuItemClickListener mItemClickListener;
	
	public MenuBar(Context context) {
		this(context, null);
	}

	public MenuBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		mMenu = new MenuImpl(context);
	}
	
	public Menu getMenu() {
		return mMenu;
	}

	public void inflate(int menuRes) {
		new MenuInflater(mContext).inflate(menuRes, mMenu);
	}
	
	public void show() {
		removeAllViews();
		for (MenuItem item : ((MenuImpl)mMenu).getMenuItems()) {
			if (item.isVisible()) addMenuButton(item);
		}
	}
	
	private PopupMenu mPopupMenu;
	
	@Override
	protected void onDetachedFromWindow() {
		if (mPopupMenu != null) mPopupMenu.dismiss();
		super.onDetachedFromWindow();
	}

	private View addMenuButton(final MenuItem item) {

		ImageButton actionButton = (ImageButton) LayoutInflater.from(mContext).inflate(R.layout.menu_button_item, null);
		
		LayoutParams params = new LayoutParams((int) getResources().getDimension(
				R.dimen.actionbar_button_width), ViewGroup.LayoutParams.FILL_PARENT);
		params.weight = 1;
		
		actionButton.setLayoutParams(params);

		actionButton.setImageDrawable(item.getIcon());
		actionButton.setScaleType(ScaleType.CENTER);
		actionButton.setContentDescription(item.getTitle());
		actionButton.setAlpha(item.isEnabled() ?  0xFF : 0x80);
		actionButton.setOnClickListener(item.isEnabled() ? new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				if (item.hasSubMenu()) {
					mPopupMenu = new PopupMenu(mContext, view);
					mPopupMenu.setMenu(item.getSubMenu());
					mPopupMenu.show();
				} else {
					if (mItemClickListener != null) mItemClickListener.onMenuItemClick(item);
				}
			}
		} : null);
		actionButton.setOnLongClickListener(new View.OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				if (item.getItemId() == android.R.id.home) return false;
				
				Toast t = Toast.makeText(mContext, item.getTitle(), Toast.LENGTH_SHORT);

				final int[] screenPos = new int[2];
				final Rect displayFrame = new Rect();
				v.getLocationOnScreen(screenPos);
				v.getWindowVisibleDisplayFrame(displayFrame);

				final int width = v.getWidth();
				final int height = v.getHeight();
				final int midy = screenPos[1] + height / 2;
				final int screenWidth = mContext.getResources().getDisplayMetrics().widthPixels;

				if (midy < displayFrame.height()) {
					// Show along the top; follow action buttons
					t.setGravity(Gravity.TOP | Gravity.RIGHT, screenWidth - screenPos[0] - width / 2, height);
				} else {
					// Show along the bottom center
					t.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, height);
				}
				t.show();
				return true;
			}
		});
		

		addView(actionButton);
		return actionButton;
	}
	
	/**
	 * Set listener for action item clicked.
	 * 
	 * @param listener Listener
	 */
	public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
		mItemClickListener = listener;
	}
	
	/**
	 * Listener for item click
	 * 
	 */
	public interface OnMenuItemClickListener {
		public boolean onMenuItemClick(MenuItem item);
	}

}
