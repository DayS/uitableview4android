package fr.days.android.uitableview.adapter;

import fr.days.android.uitableview.view.UITableCellView;
import fr.days.android.uitableview.view.UITableHeaderView;

import android.content.Context;

public abstract class UITableViewAdapter {

	public int numberOfGroups() {
		return 1;
	}

	public int numberOfRows(int group) {
		return 0;
	}

	public float heightForHeader(int group) {
		return 60;
	}

	public float heightForRow(int group, int row) {
		return 60;
	}

	public abstract UITableHeaderView headerForGroup(Context context, int group);

	public abstract UITableCellView cellViewForRow(Context context, int group, int row);

}
