package fr.days.android.uitableview.adapter;

import android.content.Context;
import fr.days.android.uitableview.model.IndexPath;
import fr.days.android.uitableview.model.UITableCellItem;
import fr.days.android.uitableview.model.UITableHeaderItem;
import fr.days.android.uitableview.view.UITableCellView;
import fr.days.android.uitableview.view.UITableHeaderView;

public abstract class UITableViewAdapter {

	public int numberOfGroups() {
		return 1;
	}

	public int numberOfRows(int group) {
		return 0;
	}

	public abstract UITableHeaderItem headerItemForGroup(Context context, IndexPath indexPath);

	public abstract UITableCellItem cellItemForRow(Context context, IndexPath indexPath);

	public abstract UITableHeaderView headerViewForGroup(Context context, IndexPath indexPath, UITableHeaderItem cellItem, UITableHeaderView convertView);

	public abstract UITableCellView cellViewForRow(Context context, IndexPath indexPath, UITableCellItem cellItem, UITableCellView convertView);

}
