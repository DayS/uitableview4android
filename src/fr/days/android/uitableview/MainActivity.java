package fr.days.android.uitableview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import fr.days.android.uitableview.adapter.UITableViewAdapter;
import fr.days.android.uitableview.adapter.UITableViewListener;
import fr.days.android.uitableview.view.UITableCellView;
import fr.days.android.uitableview.view.UITableHeaderView;
import fr.days.android.uitableview.view.UITableView;

public class MainActivity extends Activity {

	private UITableView tableView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tableView = (UITableView) findViewById(R.id.tableView);
		SimpleUITableViewAdapter tableViewAdapter = new SimpleUITableViewAdapter();
		tableView.setTableViewHandler(tableViewAdapter);
		tableView.setTableViewListener(tableViewAdapter);
	}

	class SimpleUITableViewAdapter extends UITableViewAdapter implements UITableViewListener {

		@Override
		public int numberOfGroups() {
			return 2;
		}

		@Override
		public int numberOfRows(int group) {
			return group == 0 ? 4 : 2;
		}

		@Override
		public UITableHeaderView headerForGroup(Context context, int group) {
			return new UITableHeaderView(context, "group " + group);
		}

		@Override
		public UITableCellView cellViewForRow(Context context, int group, int row) {
			return new UITableCellView(context, "Cell number " + row + " in group " + group, "Subtitle " + row);
		}

		public void itemClicked(int group, int row, UITableCellView cell) {
			System.out.println(group + ", " + row);
		}

	}
}
