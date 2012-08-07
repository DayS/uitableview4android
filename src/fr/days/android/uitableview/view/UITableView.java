package fr.days.android.uitableview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import fr.days.android.uitableview.adapter.UITableViewAdapter;
import fr.days.android.uitableview.adapter.UITableViewAdapterInternal;
import fr.days.android.uitableview.adapter.UITableViewListener;
import fr.days.android.uitableview.model.IndexPath;

public class UITableView extends ListView implements android.widget.AdapterView.OnItemClickListener, android.widget.AdapterView.OnItemLongClickListener {

	private UITableViewAdapterInternal tableViewAdapterInternal;
	private UITableViewListener tableViewListener;

	public UITableView(Context context) {
		super(context);
	}

	public UITableView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public UITableView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setAdapter(UITableViewAdapter tableViewAdapter) {
		tableViewAdapterInternal = new UITableViewAdapterInternal(getContext(), tableViewAdapter);
		super.setAdapter(tableViewAdapterInternal);
	}

	public void setUITableViewListener(UITableViewListener tableViewListener) {
		if (tableViewListener != null) {
			setOnItemClickListener(this);
			setOnItemLongClickListener(this);
		}
		this.tableViewListener = tableViewListener;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (tableViewListener != null) {
			IndexPath indexPath = tableViewAdapterInternal.retrieveIndexPathByPosition(position);
			if (indexPath != null) {
				tableViewListener.onCellClick(indexPath);
			}
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		if (tableViewListener != null) {
			IndexPath indexPath = tableViewAdapterInternal.retrieveIndexPathByPosition(position);
			if (indexPath != null) {
				return tableViewListener.onCellLongClick(indexPath);
			}
		}
		return false;
	}

}
