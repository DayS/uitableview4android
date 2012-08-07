package fr.days.android.uitableview.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.days.android.uitableview.R;
import fr.days.android.uitableview.model.IndexPath;

public class UITableHeaderView extends LinearLayout {

	private final IndexPath indexPath;
	private TextView title;

	public UITableHeaderView(Context context, IndexPath indexPath) {
		super(context);
		this.indexPath = indexPath;

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (inflater != null) {
			inflater.inflate(R.layout.table_header, this);
		}

		title = (TextView) findViewById(R.id.title);
	}

	public UITableHeaderView(Context context, IndexPath indexPath, String title) {
		this(context, indexPath);
		setTitle(title);
	}

	public IndexPath getIndexPath() {
		return indexPath;
	}

	public String getTitle() {
		return title.getText().toString();
	}

	public void setTitle(String title) {
		this.title.setText(title);
	}

}
