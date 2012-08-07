package fr.days.android.uitableview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import fr.days.android.uitableview.adapter.UITableCellListener;
import fr.days.android.uitableview.adapter.UITableHeaderListener;
import fr.days.android.uitableview.adapter.UITableViewAdapter;
import fr.days.android.uitableview.adapter.UITableViewAdapterInternal;
import fr.days.android.uitableview.adapter.UITableViewInternalAccessoryListener;
import fr.days.android.uitableview.model.IndexPath;

public class UITableView extends ListView implements android.widget.AdapterView.OnItemClickListener, android.widget.AdapterView.OnItemLongClickListener, UITableViewInternalAccessoryListener {

	private UITableViewAdapterInternal tableViewAdapterInternal;
	private UITableCellListener tableCellListener;
	private UITableHeaderListener tableHeaderListener;

	public UITableView(Context context) {
		super(context);
		initInternalListeners();
	}

	public UITableView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initInternalListeners();
	}

	public UITableView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initInternalListeners();
	}

	private void initInternalListeners() {
		setOnItemClickListener(this);
		setOnItemLongClickListener(this);
	}

	public void setAdapter(UITableViewAdapter tableViewAdapter) {
		tableViewAdapterInternal = new UITableViewAdapterInternal(getContext(), tableViewAdapter);
		tableViewAdapterInternal.setInternalAccessoryListener(this);
		super.setAdapter(tableViewAdapterInternal);
	}

	public void setTableCellListener(UITableCellListener tableCellListener) {
		this.tableCellListener = tableCellListener;
	}

	public void setTableHeaderListener(UITableHeaderListener tableHeaderListener) {
		this.tableHeaderListener = tableHeaderListener;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (tableCellListener != null) {
			IndexPath indexPath = tableViewAdapterInternal.retrieveIndexPathByPosition(position);
			if (indexPath != null) {
				if (indexPath.isHeader()) {
					tableHeaderListener.onHeaderClick(indexPath);
				} else {
					tableCellListener.onCellClick(indexPath);
				}
			}
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		if (tableCellListener != null) {
			IndexPath indexPath = tableViewAdapterInternal.retrieveIndexPathByPosition(position);
			if (indexPath != null) {
				if (indexPath.isHeader()) {
					return tableHeaderListener.onHeaderLongClick(indexPath);
				} else {
					return tableCellListener.onCellLongClick(indexPath);
				}
			}
		}
		return false;
	}

	@Override
	public void onCellAccessoryClick(IndexPath indexPath) {
		if (tableCellListener != null) {
			if (indexPath != null && !indexPath.isHeader()) {
				tableCellListener.onCellAccessoryClick(indexPath);
			}
		}
	}

}
