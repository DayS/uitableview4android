package fr.days.android.uitableview.drawable;

import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.StateListDrawable;

/**
 * This class will ceate a the cell style at runtime. It's necessary to allow multiple background color in cells
 * 
 * @author dvilleneuve
 * 
 */
public class UITableCellDrawable extends StateListDrawable {

	public UITableCellDrawable(float topRadius, float bottomRadius, int[] colorDefault, int[] colorPressed, int borderColor) {
		addState(new int[] { -android.R.attr.state_pressed }, createGradientDrawable(topRadius, bottomRadius, colorDefault, borderColor));
		addState(new int[] { android.R.attr.state_pressed }, createGradientDrawable(topRadius, bottomRadius, colorPressed, borderColor));
	}

	private GradientDrawable createGradientDrawable(float topRadius, float bottomRadius, int[] color, int borderColor) {
		GradientDrawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, color);
		gradientDrawable.setShape(GradientDrawable.RECTANGLE);
		gradientDrawable.setGradientRadius(270.0f);
		gradientDrawable.setStroke(1, borderColor);
		gradientDrawable.setCornerRadii(getRadii(topRadius, bottomRadius));

		return gradientDrawable;
	}

	private float[] getRadii(float top, float bottom) {
		return new float[] { top, top, //
				top, top, //
				bottom, bottom, //
				bottom, bottom //
		};
	}
}
