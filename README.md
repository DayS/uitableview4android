# What is UITableView4Android ?

UITableView4Android is an Android library providing a customized ListView with the look & field and behavior of UITableView on iOS.

# Motivation

Some companies are asking for mobile applications that must have the same appearance both on Android and iOS. And of course, iOS is almost always the version to fit...
List views are one the most difficult component to adapt on Android because of corners and borders to set correctly.

# Usage example

## 1. Declare layout

Add the UITableView in your layout :
```xml
<fr.days.android.uitableview.view.UITableView
	android:id="@+id/listView"
	style="@style/UITableView"
	android:layout_alignParentLeft="true"
	android:layout_alignParentTop="true" >
</fr.days.android.uitableview.view.UITableView>
```

## 2. Implement the adapter

This `UITableView` is a sub-class of Android ListView and need an implementation of `UITableViewAdapter`.

```java
class SimpleUITableViewAdapter extends UITableViewAdapter {
	@Override
	public int numberOfGroups() {
		return 4;
	}

	@Override
	public int numberOfRows(int group) {
		return (group + 1) * 2;
	}

	@Override
	public UITableHeaderView headerForGroup(Context context, IndexPath indexPath) {
		return new UITableHeaderView(context, indexPath, "Group " + indexPath.getGroup());
	}

	@Override
	public UITableCellView cellViewForRow(Context context, IndexPath indexPath) {
		String title = "Cell number " + indexPath.getRow();
		String subtitle = (indexPath.getRow() % 2 == 0) ? indexPath.toString() : null;
		
		UITableCellView cellView = new UITableCellView(context, indexPath, title, subtitle);
		cellView.setMinimumHeight(80);
		cellView.setAccessory(AccessoryType.DISCLOSURE);
		return cellView;
	}
}
```

## 3. Add listeners

Some listeners are avaible to handle click and long click on cells, headers and accessory's cells. Let's implement them all on our `SimpleUITableViewAdapter`.

```java
@Override
public void onCellClick(IndexPath indexPath) {
	Toast.makeText(getApplicationContext(), "Cell clicked : " + indexPath, 1000).show();
}

@Override
public boolean onCellLongClick(IndexPath indexPath) {
	Toast.makeText(getApplication(), "Cell long clicked : " + indexPath, 1000).show();
	return indexPath.getRow() % 2 == 0;	// Consume the long click one row out of two
}

@Override
public void onCellAccessoryClick(IndexPath indexPath) {
	Toast.makeText(getApplication(), "Cell accessory clicked : " + indexPath, 1000).show();
}

@Override
public void onHeaderClick(IndexPath indexPath) {
	Toast.makeText(getApplicationContext(), "Header clicked : " + indexPath, 1000).show();
}

@Override
public boolean onHeaderLongClick(IndexPath indexPath) {
	Toast.makeText(getApplicationContext(), "Header long clicked : " + indexPath, 1000).show();
	return indexPath.getGroup() % 2 == 0;	// Consume the long click one row out of two
}
```

## 4. Configure UITableView

Now, we have an implementation of `UITableViewAdapter` with all listeners added. It only remains to configure the `UITableView`.

```java
SimpleUITableViewAdapter tableViewAdapter = new SimpleUITableViewAdapter();
tableView = (UITableView) findViewById(R.id.listView);
tableView.setAdapter(tableViewAdapter);
tableView.setOnCellClickListener(tableViewAdapter);
tableView.setOnCellLongClickListener(tableViewAdapter);
tableView.setOnCellAccessoryClickListener(tableViewAdapter);
tableView.setOnHeaderClickListener(tableViewAdapter);
tableView.setOnHeaderLongClickListener(tableViewAdapter);
```

# Result

And here is the result.

![Example result](https://github.com/DayS/uitableview4android/wiki/img/example_result.png)
