package fr.days.android.uitableview.adapter;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import fr.days.android.uitableview.model.IndexPath;
import fr.days.android.uitableview.model.UITableCellItem;
import fr.days.android.uitableview.model.UITableHeaderItem;
import fr.days.android.uitableview.view.UITableCellView;
import fr.days.android.uitableview.view.UITableHeaderView;

/**
 * Internal adapter used by Android ListView.
 * 
 * @author dvilleneuve
 * 
 */
public class UITableViewAdapterInternal extends BaseAdapter {

	private static final int VIEW_TYPE_HEADER = 0;
	private static final int VIEW_TYPE_CELL = 1;

	private final Context context;
	private final UITableViewAdapter tableViewAdapter;

	private UITableViewInternalAccessoryListener internalAccessoryListener;
	private Map<Integer, IndexPath> indexPaths = new HashMap<Integer, IndexPath>();

	/**
	 * @param uiTableViewAdapter
	 */
	public UITableViewAdapterInternal(Context context, UITableViewAdapter uiTableViewAdapter) {
		this.context = context;
		this.tableViewAdapter = uiTableViewAdapter;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		IndexPath indexPath = retrieveIndexPathByPosition(position);
		if (indexPath == null)
			return IGNORE_ITEM_VIEW_TYPE;
		else if (indexPath.isHeader())
			return VIEW_TYPE_HEADER;
		else
			return VIEW_TYPE_CELL;
	}

	@Override
	public int getCount() {
		int numberOfGroups = tableViewAdapter.numberOfGroups();
		int countItems = numberOfGroups;
		for (int group = 0; group < numberOfGroups; group++) {
			countItems += tableViewAdapter.numberOfRows(group);
		}
		return countItems;
	}

	@Override
	public Object getItem(int position) {
		IndexPath indexPath = retrieveIndexPathByPosition(position);
		if (indexPath == null) {
			throw new NullPointerException("Unable to retrieve index path for position " + position);
		} else if (indexPath.isHeader()) {
			return getHeaderItem(indexPath);
		} else {
			return tableViewAdapter.cellItemForRow(context, indexPath);
		}
	}

	public UITableHeaderItem getHeaderItem(IndexPath indexPath) {
		UITableHeaderItem headerItem = tableViewAdapter.headerItemForGroup(context, indexPath);
		return headerItem != null ? headerItem : new UITableHeaderItem();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		IndexPath indexPath = retrieveIndexPathByPosition(position);

		if (indexPath == null) {
			throw new NullPointerException("Unable to retrieve index path for position " + position);
		} else if (indexPath.isHeader()) {
			if (!(convertView instanceof UITableHeaderView)) {
				convertView = null;
			}
			if (convertView != null) {
				UITableHeaderView tableHeaderView = (UITableHeaderView) convertView;
				tableHeaderView.setIndexPath(indexPath);
			}
			UITableHeaderItem headerItem = getHeaderItem(indexPath);
			UITableHeaderView headerView = tableViewAdapter.headerViewForGroup(context, indexPath, headerItem, (UITableHeaderView) convertView);
			return headerView;
		} else {
			if (!(convertView instanceof UITableCellView)) {
				convertView = null;
			}
			if (convertView != null) {
				// Configure the recycling view by reseting his indexpath and background
				UITableCellView tableCellView = (UITableCellView) convertView;
				tableCellView.setIndexPath(indexPath);
				if (!tableCellView.getIndexPath().isStateEquals(indexPath)) {
					tableCellView.setDefaultBackgroundColor();
				}
			}
			UITableCellItem cellItem = tableViewAdapter.cellItemForRow(context, indexPath);
			UITableCellView cellView = tableViewAdapter.cellViewForRow(context, indexPath, cellItem, (UITableCellView) convertView);
			cellView.setInternalAccessoryListener(internalAccessoryListener);
			return cellView;
		}
	}

	/**
	 * Retrieve an {@link IndexPath} according to a ListView's item position.
	 * 
	 * @param position
	 * @return An {@link IndexPath} if position is valid, <code>null</code> else
	 */
	public IndexPath retrieveIndexPathByPosition(final int position) {
		IndexPath indexPath = indexPaths.get(position);
		if (indexPath == null) {
			int numberOfGroups = tableViewAdapter.numberOfGroups();

			if (position == 0) { // Shortcut for the first item
				indexPath = new IndexPath(0, 0, numberOfGroups);

			} else {
				int numberOfRowsBefore = 0;
				for (int group = 0; group < numberOfGroups; group++) {
					int numberOfRows = tableViewAdapter.numberOfRows(group);

					// Header
					if (position == numberOfRowsBefore) {
						indexPath = new IndexPath(position, group, numberOfGroups);
						break;
					}
					// Cell
					if (position <= numberOfRowsBefore + numberOfRows) {
						indexPath = new IndexPath(position, group, position - numberOfRowsBefore - 1, numberOfGroups, numberOfRows);
						break;
					}

					// This position doesn't fit to this group, see the next one
					numberOfRowsBefore += numberOfRows + 1; // rows + header
				}
			}

			indexPaths.put(position, indexPath);
		}
		return indexPath;
	}

	@Override
	public void notifyDataSetChanged() {
		indexPaths.clear();
		super.notifyDataSetChanged();
	}

	@Override
	public void notifyDataSetInvalidated() {
		indexPaths.clear();
		super.notifyDataSetInvalidated();
	}

	public UITableViewInternalAccessoryListener getInternalAccessoryListener() {
		return internalAccessoryListener;
	}

	public void setInternalAccessoryListener(UITableViewInternalAccessoryListener internalAccessoryListener) {
		this.internalAccessoryListener = internalAccessoryListener;
	}

}