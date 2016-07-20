
package com.common.lib.widget;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;

import com.common.lib.R;

public class DividerLine extends View {

    public DividerLine(Context context) {
        this(context, false);
    }

    public DividerLine(Context context, boolean isVertical) {
        super(context);
        setBackgroundColor(context.getResources().getColor(R.color.divider_dark));
        LayoutParams params;
        if (isVertical) {
            params = new LayoutParams(1, LayoutParams.MATCH_PARENT);
        } else {
            params = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
        }
        setLayoutParams(params);
    }
}
