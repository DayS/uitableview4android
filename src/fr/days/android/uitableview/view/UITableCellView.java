package fr.days.android.uitableview.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.days.android.uitableview.R;
import fr.days.android.uitableview.drawable.UITableCellDrawable;
import fr.days.android.uitableview.model.AccessoryType;
import fr.days.android.uitableview.model.IndexPath;

public class UITableCellView extends LinearLayout {

	private static final int INSET = 10;

	private static int[] color_line1_default;
	private static int[] color_line2_default;
	private static int[] color_line1_pressed;
	private static int[] color_line2_pressed;

	private final IndexPath indexPath;
	private ImageView imageView;
	private TextView titleView;
	private TextView subtitleView;
	private ImageView accessoryView;

	public UITableCellView(Context context, IndexPath indexPath) {
		super(context);
		this.indexPath = indexPath;

		initColors();

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (inflater != null) {
			inflater.inflate(R.layout.table_cell, this);
		}

		imageView = (ImageView) findViewById(R.id.image);
		titleView = (TextView) findViewById(R.id.title);
		subtitleView = (TextView) findViewById(R.id.subtitle);
		accessoryView = (ImageView) findViewById(R.id.accessory);

		int[] colorDefault = indexPath.getRow() % 2 == 0 ? color_line1_default : color_line2_default;
		int[] colorPressed = indexPath.getRow() % 2 == 0 ? color_line1_pressed : color_line2_pressed;

		// Assign the right backgroundDrawable according to the cell's position in the group
		Drawable backgroundDrawable;
		if (indexPath.getRowsCount() == 1) {
			backgroundDrawable = new UITableCellDrawable(10.0f, 10.0f, colorDefault, colorPressed);
		} else {
			if (indexPath.isFirstCellOfGroup()) {
				backgroundDrawable = new UITableCellDrawable(10.0f, 0, colorDefault, colorPressed);
			} else if (indexPath.isLastCellOfGroup()) {
				backgroundDrawable = new UITableCellDrawable(0, 10.0f, colorDefault, colorPressed);
			} else {
				backgroundDrawable = new UITableCellDrawable(0, 0, colorDefault, colorPressed);
			}
		}

		// Add extra space if this cell is the last one
		int bottomInset = 0;
		if (indexPath.isLastCell()) {
			bottomInset = INSET;
		}
		setBackgroundDrawable(new InsetDrawable(backgroundDrawable, INSET, 0, INSET, bottomInset));
	}

	public UITableCellView(Context context, IndexPath indexPath, String title, String subtitle) {
		this(context, indexPath);
		setTitle(title);
		setSubtitle(subtitle);
	}

	private void initColors() {
		if (color_line1_default == null) {
			color_line1_default = new int[] { getColor(R.color.base_start_color_line1_default), getColor(R.color.base_end_color_line1_default) };
			color_line2_default = new int[] { getColor(R.color.base_start_color_line2_default), getColor(R.color.base_end_color_line2_default) };
			color_line1_pressed = new int[] { getColor(R.color.base_start_color_line1_pressed), getColor(R.color.base_end_color_line1_pressed) };
			color_line2_pressed = new int[] { getColor(R.color.base_start_color_line2_pressed), getColor(R.color.base_end_color_line2_pressed) };
		}
	}

	private int getColor(int colorId) {
		return getResources().getColor(colorId);
	}

	public IndexPath getIndexPath() {
		return indexPath;
	}

	public ImageView getImage() {
		return imageView;
	}

	public void setImage(Integer imageResource) {
		if (imageResource == null) {
			imageView.setVisibility(View.GONE);
		} else {
			imageView.setVisibility(View.VISIBLE);
		}
		imageView.setImageResource(imageResource);
	}

	public void setImage(Drawable drawable) {
		if (drawable == null) {
			imageView.setVisibility(View.GONE);
		} else {
			imageView.setVisibility(View.VISIBLE);
		}
		imageView.setImageDrawable(drawable);
	}

	public String getTitle() {
		return titleView.getText().toString();
	}

	public void setTitle(String title) {
		this.titleView.setText(title);
	}

	public String getSubtitle() {
		return subtitleView.getText().toString();
	}

	public void setSubtitle(String subtitle) {
		if (subtitle == null || subtitle.isEmpty()) {
			subtitleView.setVisibility(View.GONE);
		} else {
			subtitleView.setVisibility(View.VISIBLE);
		}
		subtitleView.setText(subtitle);
	}

	public ImageView getAccessory() {
		return accessoryView;
	}

	public void setAccessory(AccessoryType accessoryType) {
		if (accessoryType == AccessoryType.NONE) {
			accessoryView.setVisibility(View.GONE);
		} else {
			accessoryView.setVisibility(View.VISIBLE);

			switch (accessoryType) {
			case DISCLOSURE:
				accessoryView.setImageResource(R.drawable.accessory_disclosure);
				break;
			}
		}
	}

	public void setAccessory(Drawable drawable) {
		if (drawable == null) {
			imageView.setVisibility(View.GONE);
		} else {
			imageView.setVisibility(View.VISIBLE);
		}
		imageView.setImageDrawable(drawable);
	}

}
