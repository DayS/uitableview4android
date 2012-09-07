package fr.days.android.uitableview.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;
import fr.days.android.uitableview.R;
import fr.days.android.uitableview.model.IndexPath;

public class UITableHeaderView extends UITableItemView {

	private TextView titleView;

	public UITableHeaderView(Context context, IndexPath indexPath) {
		super(context, indexPath);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (inflater != null) {
			inflater.inflate(R.layout.table_header, this);
		}

		titleView = (TextView) findViewById(R.id.title);
	}

	public UITableHeaderView(Context context, IndexPath indexPath, String title) {
		this(context, indexPath);
		setTitle(title);
	}

	public TextView getTitleView() {
		return titleView;
	}

	public String getTitle() {
		return titleView.getText().toString();
	}

	public void setTitle(String title) {
		this.titleView.setText(title);
	}

}
