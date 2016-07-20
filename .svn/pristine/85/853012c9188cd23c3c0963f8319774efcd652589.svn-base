/**
 * 
 */

package com.common.lib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 自适应ListView
 * 
 * @author luchonghui
 */
public class AutoListView extends ListView {

    public AutoListView(Context context) {
        super(context);
    }

    public AutoListView(Context context, AttributeSet as) {
        super(context, as);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
