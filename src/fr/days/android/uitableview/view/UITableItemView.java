package fr.days.android.uitableview.view;

import android.content.Context;
import android.widget.LinearLayout;
import fr.days.android.uitableview.model.IndexPath;

public class UITableItemView extends LinearLayout {

	protected IndexPath indexPath;

	public UITableItemView(Context context, IndexPath indexPath) {
		super(context);
		this.indexPath = indexPath;
	}

	/**
	 * Reset the {@link IndexPath} of this cell and so his background according to his position.
	 * 
	 * @param indexPath
	 */
	public void setIndexPath(IndexPath indexPath) {
		this.indexPath = indexPath;
	}

	public IndexPath getIndexPath() {
		return indexPath;
	}

}
