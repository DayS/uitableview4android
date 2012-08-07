package fr.days.android.uitableview.model;

/**
 * This class represent an item by the group to which it belongs and his position in this group.
 */
public class IndexPath {
	private final int position;
	private final int group;
	private final int row;
	private final int numberOfRows;

	public IndexPath(int position, int group) {
		this(position, group, -1, 0);
	}

	public IndexPath(int position, int group, int row, int numberOfRows) {
		this.position = position;
		this.group = group;
		this.row = row;
		this.numberOfRows = numberOfRows;
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

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public boolean isHeader() {
		return row == -1;
	}

	@Override
	public String toString() {
		return "IndexPath [position=" + position + ", group=" + group + ", row=" + row + "/" + numberOfRows + "]";
	}

}