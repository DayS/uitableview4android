package fr.days.android.uitableview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import fr.days.android.uitableview.adapter.UITableViewAdapter;
import fr.days.android.uitableview.adapter.UITableViewAdapterInternal;
import fr.days.android.uitableview.adapter.UITableViewListener;
import fr.days.android.uitableview.model.IndexPath;
import fr.days.android.uitableview.view.UITableCellView;
import fr.days.android.uitableview.view.UITableHeaderView;

public class MainActivity extends Activity {

	private ListView tableView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tableView = (ListView) findViewById(R.id.listView);
		SimpleUITableViewAdapter tableViewAdapter = new SimpleUITableViewAdapter();
		final UITableViewAdapterInternal tableViewAdapterInternal = new UITableViewAdapterInternal(getApplicationContext(), tableViewAdapter);
		tableView.setAdapter(tableViewAdapterInternal);
		tableView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				IndexPath indexPath = tableViewAdapterInternal.retrieveIndexPathByPosition(position);
				System.out.println(indexPath);
			}
		});
	}

	class SimpleUITableViewAdapter extends UITableViewAdapter implements UITableViewListener {

		@Override
		public int numberOfGroups() {
			return 3;
		}

		@Override
		public int numberOfRows(int group) {
			switch (group) {
			case 0:
				return 4;
			case 1:
				return 3;
			case 2:
				return 2;
			}
			return 0;
		}

		@Override
		public UITableHeaderView headerForGroup(Context context, IndexPath indexPath) {
			return new UITableHeaderView(context, indexPath, "group " + indexPath.getGroup());
		}

		@Override
		public UITableCellView cellViewForRow(Context context, IndexPath indexPath) {
			return new UITableCellView(context, indexPath, "Cell number " + indexPath.getRow() + " in group " + indexPath.getGroup(), "Subtitle " + indexPath.getRow());
		}

		public void onItemClicked(int group, int row, UITableCellView cell) {
			System.out.println(group + ", " + row);
		}

	}
}
