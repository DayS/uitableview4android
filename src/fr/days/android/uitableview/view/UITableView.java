package fr.days.android.uitableview.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import fr.days.android.uitableview.adapter.UITableViewAdapter;
import fr.days.android.uitableview.adapter.UITableViewAdapterInternal;
import fr.days.android.uitableview.adapter.UITableViewInternalAccessoryListener;
import fr.days.android.uitableview.listener.OnCellAccessoryClickListener;
import fr.days.android.uitableview.listener.OnCellClickListener;
import fr.days.android.uitableview.listener.OnCellLongClickListener;
import fr.days.android.uitableview.listener.OnHeaderClickListener;
import fr.days.android.uitableview.listener.OnHeaderLongClickListener;
import fr.days.android.uitableview.model.IndexPath;

public class UITableView extends ListView implements android.widget.AdapterView.OnItemClickListener, android.widget.AdapterView.OnItemLongClickListener, UITableViewInternalAccessoryListener {

	private UITableViewAdapterInternal tableViewAdapterInternal;
	private OnCellClickListener onCellClickListener;
	private OnCellLongClickListener onCellLongClickListener;
	private OnCellAccessoryClickListener onCellAccessoryClickListener;
	private OnHeaderClickListener onHeaderClickListener;
	private OnHeaderLongClickListener onHeaderLongClickListener;

	public UITableView(Context context) {
		super(context);
		init();
	}

	public UITableView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public UITableView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		setBackgroundColor(Color.parseColor("#f1f6f9"));
		setDivider(new ColorDrawable(Color.TRANSPARENT));
		setSelector(new ColorDrawable(Color.TRANSPARENT));

		// Init internal listeners
		setOnItemClickListener(this);
		setOnItemLongClickListener(this);
	}

	public void setAdapter(UITableViewAdapter tableViewAdapter) {
		tableViewAdapterInternal = new UITableViewAdapterInternal(getContext(), tableViewAdapter);
		tableViewAdapterInternal.setInternalAccessoryListener(this);
		super.setAdapter(tableViewAdapterInternal);
	}

	public void setOnCellClickListener(OnCellClickListener tableCellListener) {
		this.onCellClickListener = tableCellListener;
	}

	public void setOnCellLongClickListener(OnCellLongClickListener onCellLongClickListener) {
		this.onCellLongClickListener = onCellLongClickListener;
	}

	public void setOnCellAccessoryClickListener(OnCellAccessoryClickListener onCellAccessoryClickListener) {
		this.onCellAccessoryClickListener = onCellAccessoryClickListener;
	}

	public void setOnHeaderClickListener(OnHeaderClickListener onHeaderClickListener) {
		this.onHeaderClickListener = onHeaderClickListener;
	}

	public void setOnHeaderLongClickListener(OnHeaderLongClickListener onHeaderLongClickListener) {
		this.onHeaderLongClickListener = onHeaderLongClickListener;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		IndexPath indexPath = tableViewAdapterInternal.retrieveIndexPathByPosition(position);
		if (indexPath != null) {
			if (indexPath.isHeader()) {
				if (onHeaderClickListener != null) {
					onHeaderClickListener.onHeaderClick(indexPath);
				}
			} else {
				if (onCellClickListener != null) {
					onCellClickListener.onCellClick(indexPath);
				}
			}
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		IndexPath indexPath = tableViewAdapterInternal.retrieveIndexPathByPosition(position);
		if (indexPath != null) {
			if (indexPath.isHeader()) {
				if (onHeaderLongClickListener != null) {
					return onHeaderLongClickListener.onHeaderLongClick(indexPath);
				}
			} else {
				if (onCellLongClickListener != null) {
					return onCellLongClickListener.onCellLongClick(indexPath);
				}
			}
		}
		return false;
	}

	@Override
	public void onCellAccessoryClick(IndexPath indexPath) {
		if (indexPath != null && !indexPath.isHeader()) {
			// Trigger the accessory listener if set, else tigger the cell listener
			if (onCellAccessoryClickListener != null) {
				onCellAccessoryClickListener.onCellAccessoryClick(indexPath);
			} else if (onCellClickListener != null) {
				onCellClickListener.onCellClick(indexPath);
			}
		}
	}

	@Override
	public boolean onCellAccessoryLongClick(IndexPath indexPath) {
		if (indexPath != null && !indexPath.isHeader()) {
			if (onCellLongClickListener != null) {
				return onCellLongClickListener.onCellLongClick(indexPath);
			}
		}
		return false;
	}

}
