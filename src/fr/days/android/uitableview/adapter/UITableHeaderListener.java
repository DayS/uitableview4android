package fr.days.android.uitableview.adapter;

import fr.days.android.uitableview.model.IndexPath;

public interface UITableHeaderListener {

	void onHeaderClick(IndexPath indexPath);

	boolean onHeaderLongClick(IndexPath indexPath);

}
