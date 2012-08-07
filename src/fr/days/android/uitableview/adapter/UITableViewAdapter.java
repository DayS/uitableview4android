package fr.days.android.uitableview.adapter;

import android.content.Context;
import fr.days.android.uitableview.view.UITableCellView;
import fr.days.android.uitableview.view.UITableHeaderView;

public abstract class UITableViewAdapter {

	public int numberOfGroups() {
		return 1;
	}

	public int numberOfRows(int group) {
		return 0;
	}

	public abstract UITableHeaderView headerForGroup(Context context, int group);

	public abstract UITableCellView cellViewForRow(Context context, int group, int row);

}
