package fr.days.android.uitableview.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.days.android.uitableview.R;

public class UITableCellView extends LinearLayout {

	private int group;
	private int row;
	private ImageView image;
	private TextView title;
	private TextView subtitle;
	private View accessory;

	public UITableCellView(Context context) {
		super(context);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (inflater != null) {
			inflater.inflate(R.layout.table_cell, this);
		}

		image = (ImageView) findViewById(R.id.image);
		title = (TextView) findViewById(R.id.title);
		subtitle = (TextView) findViewById(R.id.subtitle);
		accessory = (View) findViewById(R.id.accessory);
	}

	public UITableCellView(Context context, String title, String subtitle) {
		this(context);
		setTitle(title);
		setSubtitle(subtitle);
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
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
