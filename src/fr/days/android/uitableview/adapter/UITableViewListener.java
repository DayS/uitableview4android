package fr.days.android.uitableview.adapter;

import fr.days.android.uitableview.view.UITableCellView;

public interface UITableViewListener {

	void onItemClicked(int group, int row, UITableCellView cell);

}
