package fr.days.android.uitableview.adapter;

import fr.days.android.uitableview.view.UITableCellView;

public interface UITableViewListener {

	void itemClicked(int group, int row, UITableCellView cell);

}
