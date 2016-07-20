
package com.common.lib.net;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.common.lib.utils.ErrorHelper;
import com.common.lib.utils.ToastUtils;

import java.lang.ref.WeakReference;

public class ErrorOperator implements Response.ErrorListener {

    private WeakReference<Context> mWf;

    public ErrorOperator(Context context) {
        mWf = new WeakReference<Context>(context);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if(mWf != null) {
            Context context = mWf.get();
            ToastUtils.show(ErrorHelper.getMessage(error, context));
        }
    }
}
