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
import fr.days.android.uitableview.model.IndexPath;

public class UITableCellView extends LinearLayout {

	private static final int INSET = 10;

	private static int[] color_line1_default;
	private static int[] color_line2_default;
	private static int[] color_line1_pressed;
	private static int[] color_line2_pressed;

	private final IndexPath indexPath;
	private ImageView image;
	private TextView title;
	private TextView subtitle;
	private View accessory;

	public UITableCellView(Context context, IndexPath indexPath) {
		super(context);
		this.indexPath = indexPath;

		initColors();

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (inflater != null) {
			inflater.inflate(R.layout.table_cell, this);
		}

		image = (ImageView) findViewById(R.id.image);
		title = (TextView) findViewById(R.id.title);
		subtitle = (TextView) findViewById(R.id.subtitle);
		accessory = (View) findViewById(R.id.accessory);

		int[] colorDefault = indexPath.getRow() % 2 == 0 ? color_line1_default : color_line2_default;
		int[] colorPressed = indexPath.getRow() % 2 == 0 ? color_line1_pressed : color_line2_pressed;

		// Assign the right backgroundDrawable according to the cell's position in the group
		Drawable backgroundDrawable;
		if (indexPath.getNumberOfRows() == 1) {
			backgroundDrawable = new UITableCellDrawable(10.0f, 10.0f, colorDefault, colorPressed);
		} else {
			if (indexPath.getRow() == 0) {
				backgroundDrawable = new UITableCellDrawable(10.0f, 0, colorDefault, colorPressed);
			} else if (indexPath.getRow() == indexPath.getNumberOfRows() - 1) {
				backgroundDrawable = new UITableCellDrawable(0, 10.0f, colorDefault, colorPressed);
			} else {
				backgroundDrawable = new UITableCellDrawable(0, 0, colorDefault, colorPressed);
			}
		}

		setBackgroundDrawable(new InsetDrawable(backgroundDrawable, INSET, 0, INSET, 0));
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
		return image;
	}

	public void setImage(ImageView image) {
		if (image == null) {
			this.image.setVisibility(View.VISIBLE);
		} else {
			this.image.setVisibility(View.GONE);
		}

		this.image = image;
	}

	public String getTitle() {
		return title.getText().toString();
	}

	public void setTitle(String title) {
		this.title.setText(title);
	}

	public String getSubtitle() {
		return subtitle.getText().toString();
	}

	public void setSubtitle(String subtitle) {
		if (subtitle == null || subtitle.isEmpty()) {
			this.subtitle.setVisibility(View.VISIBLE);
		} else {
			this.subtitle.setVisibility(View.GONE);
		}

		this.subtitle.setText(subtitle);
	}

	public View getAccessory() {
		return accessory;
	}

	public void setAccessory(View accessory) {
		if (accessory == null) {
			this.accessory.setVisibility(View.VISIBLE);
		} else {
			this.accessory.setVisibility(View.GONE);
		}

		this.accessory = accessory;
	}

}
