package fr.days.android.uitableview.view;

import fr.days.android.uitableview.adapter.UITableViewAdapter;
import fr.days.android.uitableview.adapter.UITableViewListener;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

public class UITableView extends LinearLayout {

	private UITableViewAdapter tableViewHandler;
	private UITableViewListener tableViewListener;
	private boolean updatingCells;

	public UITableView(Context context) {
		super(context);
		initTableView();
	}

	public UITableView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initTableView();
	}

	private void initTableView() {
		setOrientation(VERTICAL);
		// setBackgroundColor(Color.rgb(197, 204, 212));
		setBackgroundColor(Color.parseColor("#f1f6f9"));
		setPadding(10, 0, 10, 10);
		setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	/**
	 * Remove all views and re-create them
	 */
	public void updateAllCells() {
		updatingCells = true;

		removeAllViews();

		int numberOfGroups = tableViewHandler.numberOfGroups();

		for (int group = 0; group < numberOfGroups; group++) {
			int numberOfRows = tableViewHandler.numberOfRows(group);
			if (numberOfRows == 0)
				continue;

			UITableHeaderView headerView = tableViewHandler.headerForGroup(getContext(), group);
			headerView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int) dipHeightForHeader(group)));
			addView(headerView);

			for (int row = 0; row < numberOfRows; row++) {
				final UITableCellView cellView = tableViewHandler.cellViewForRow(getContext(), group, row);
				cellView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int) dipHeightForRow(group, row)));
				cellView.setGroup(group);
				cellView.setRow(row);
				cellView.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						tableViewListener.itemClicked(cellView.getGroup(), cellView.getRow(), cellView);
					}
				});
				addView(cellView);
			}
		}

		updatingCells = false;
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		if (updatingCells) {
			super.dispatchDraw(canvas);
			return;
		}

		final Paint paint = new Paint();
		paint.setColor(Color.LTGRAY);
		paint.setAntiAlias(true);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(2);

		// Clip the view by group
		final Path groupMask = new Path();

		float totalGroupHeight = 0.0f;
		int numberOfGroups = tableViewHandler.numberOfGroups();
		for (int group = 0; group < numberOfGroups; group++) {
			// Header
			float headerHeight = dipHeightForHeader(group);
			groupMask.addRect(new RectF(getPaddingLeft(), totalGroupHeight, getWidth() - getPaddingLeft() - getPaddingRight(), totalGroupHeight + headerHeight), Direction.CW);

			// Rows
			float currentRowsHeight = heightOfAllRowsInGroup(group);
			totalGroupHeight += headerHeight;
			groupMask.addRoundRect(new RectF(getPaddingLeft(), totalGroupHeight, getWidth() - getPaddingLeft() - getPaddingRight(), totalGroupHeight + currentRowsHeight), 10, 10, Direction.CW);
			totalGroupHeight += currentRowsHeight;
		}
		canvas.clipPath(groupMask);

		super.dispatchDraw(canvas);

		// Draw border
		totalGroupHeight = 0.0f;
		for (int group = 0; group < numberOfGroups; group++) {
			float currentRowsHeight = heightOfAllRowsInGroup(group);
			totalGroupHeight += dipHeightForHeader(group);
			canvas.drawRoundRect(new RectF(getPaddingLeft(), totalGroupHeight, getWidth() - getPaddingLeft() - getPaddingRight(), totalGroupHeight + currentRowsHeight), 10, 10, paint);
			totalGroupHeight += currentRowsHeight;
		}
	}

	private int heightOfAllRowsInGroup(int group) {
		int numberOfRows = tableViewHandler.numberOfRows(group);
		if (numberOfRows == 0)
			return 0;

		int groupHeight = 0;
		for (int row = 0; row < numberOfRows; row++) {
			groupHeight += dipHeightForRow(group, row);
		}
		return groupHeight;
	}

	protected float dipHeightForHeader(int group) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) tableViewHandler.heightForHeader(group), getResources().getDisplayMetrics());
	}

	protected float dipHeightForRow(int group, int row) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) tableViewHandler.heightForRow(group, row), getResources().getDisplayMetrics());
	}

	public void setTableViewHandler(UITableViewAdapter tableViewHandler) {
		boolean needToUpdate = false;

		if (this.tableViewHandler != tableViewHandler) {
			needToUpdate = true;
		}
		this.tableViewHandler = tableViewHandler;

		if (needToUpdate) {
			updateAllCells();
		}
	}

	public UITableViewAdapter getTableViewHandler() {
		return tableViewHandler;
	}

	public UITableViewListener getTableViewListener() {
		return tableViewListener;
	}

	public void setTableViewListener(UITableViewListener tableViewListener) {
		this.tableViewListener = tableViewListener;
	}

	public boolean isUpdatingCells() {
		return updatingCells;
	}

	public void setUpdatingCells(boolean updatingCells) {
		this.updatingCells = updatingCells;
	}

}
