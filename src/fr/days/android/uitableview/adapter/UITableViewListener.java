package fr.days.android.uitableview.adapter;

import fr.days.android.uitableview.model.IndexPath;

public interface UITableViewListener {

	void onCellClick(IndexPath indexPath);

	boolean onCellLongClick(IndexPath indexPath);

	void onCellAccessoryClick(IndexPath indexPath);

}
