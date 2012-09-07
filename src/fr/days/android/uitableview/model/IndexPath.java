package fr.days.android.uitableview.model;

/**
 * This class represent an item by the group to which it belongs and his position in this group.
 */
public class IndexPath {
	private final int position;
	private final int group;
	private final int row;
	private final int groupsCount;
	private final int rowsCount;

	public IndexPath(int position, int group, int groupCount) {
		this(position, group, -1, groupCount, 0);
	}

	public IndexPath(int position, int group, int row, int groupsCount, int rowsCount) {
		this.position = position;
		this.group = group;
		this.row = row;
		this.groupsCount = groupsCount;
		this.rowsCount = rowsCount;
	}

	public int getPosition() {
		return position;
	}

	public int getGroup() {
		return group;
	}

	public int getRow() {
		return row;
	}

	public int getGroupsCount() {
		return groupsCount;
	}

	public int getRowsCount() {
		return rowsCount;
	}

	public boolean isHeader() {
		return row == -1;
	}

	public boolean isFirstGroup() {
		return group == 0;
	}

	public boolean isLastGroup() {
		return group == groupsCount - 1;
	}

	public boolean isFirstCellOfGroup() {
		return row == 0;
	}

	public boolean isLastCellOfGroup() {
		return row == rowsCount - 1;
	}

	public boolean isLastCell() {
		return isLastGroup() && isLastCellOfGroup();
	}

	public boolean isStateEquals(IndexPath indexPath) {
		if (indexPath == null)
			return false;
		if (indexPath == this)
			return true;

		if (isFirstCellOfGroup() && !indexPath.isFirstCellOfGroup())
			return false;
		else if (isLastCellOfGroup() && !indexPath.isLastCellOfGroup())
			return false;
		else {
			if (indexPath.isFirstCellOfGroup() || indexPath.isLastCellOfGroup())
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "IndexPath [position=" + position + ", group=" + group + "/" + groupsCount + ", row=" + row + "/" + rowsCount + "]";
	}
}