
package com.common.lib.net;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.AutoLoginRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.UploadRequest;
import com.android.volley.toolbox.Volley;
import com.common.lib.utils.LoadingProgressWrapper;

import org.json.JSONObject;

import java.util.Map;

public class RequestBuilder {

    /** StringRequest */
    public static final int STRING = 1;

    /** JsonObjectRequest */
    public static final int JSON_OBJECT = 2;

    /** UploadRequest */
    public static final int UPLOAD = 3;

    /** AutoLoginRequest */
    public static final int LOGIN = 4;

    private Context mContext;
    private String mUrl;
    private Map<String, Object> mParams;
    private String mTag;
    private Listener<?> mListener;
    private String mMessage;
    private boolean mShouldCache;
    private boolean mIsRefresh;
    private boolean mIsOuterRequest;
    private long mCacheTime;
    private Response.ErrorListener mErrorListener;

    /**
     * context 必须是Activity的context
     * @param context
     * @param url
     * @param params
     * @param tag
     * @param listener
     */
    public RequestBuilder(Context context, String url, Map<String, Object> params,
            String tag, Listener<?> listener) {
        mContext = context;
        mUrl = url;
        mParams = params;
        mTag = tag;
        mListener = listener;
    }

    public RequestBuilder setResId(int resId) {
        mMessage = mContext.getString(resId);
        return this;
    }

    public RequestBuilder setMessage(String msg) {
        mMessage = msg;
        return this;
    }

    public RequestBuilder setErrorListener(ErrorListener errorListener) {
        mErrorListener = errorListener;
        return this;
    }

    /**
     * 默认缓存是关闭的
     * 
     * @param shouldCache
     * @return
     */
    public RequestBuilder setShouldCache(boolean shouldCache) {
        mShouldCache = shouldCache;
        return this;
    }

    /**
     * 默认缓存超时是3秒
     * 
     * @param cacheTime
     * @return
     */
    public RequestBuilder setCacheTime(long cacheTime) {
        mCacheTime = cacheTime;
        mShouldCache = true;
        return this;
    }

    public RequestBuilder setIsRefresh() {
        return setIsRefresh(true);
    }

    public RequestBuilder setIsRefresh(boolean isRefresh) {
        mIsRefresh = isRefresh;
        return this;
    }

    public RequestBuilder setIsOuterRequest(boolean isOuterRequest) {
        mIsOuterRequest = isOuterRequest;
        return this;
    }

    public Request<?> create() {
        return create(STRING);
    }

    /**
     * @param type RequestBuilder.STRING RequestBuilder.JSON_OBJECT RequestBuilder.UPLOAD
     *            RequestBuilder.REFRESH
     * @return
     */
    @SuppressWarnings("unchecked")
    public Request<?> create(int type) {
        if (mErrorListener == null) {
            mErrorListener = new ErrorOperator(mContext);
        }
        Request<?> request;
        switch (type) {
            case STRING:
                request = new StringRequest(mUrl, mParams, (Listener<String>) mListener,
                        mErrorListener);
                break;
            case JSON_OBJECT:
                request = new JsonObjectRequest(mUrl, mParams, (Listener<JSONObject>) mListener,
                        mErrorListener);
                break;
            case UPLOAD:
                request = new UploadRequest(mUrl, mParams, (Listener<String>) mListener,
                        mErrorListener);
                break;
            case LOGIN:
                request = new AutoLoginRequest(mUrl,
                        mParams, (Listener<String>) mListener
                        , mErrorListener);
                break;
            default:
                request = new StringRequest(mUrl, mParams, (Listener<String>) mListener,
                        mErrorListener);
        }

        if (!TextUtils.isEmpty(mMessage)) {
            LoadingProgressWrapper.wrapRequest(mContext, request, mTag,
                                               mMessage);
        } else {
            request.setTag(mTag);
        }
        request.setIsOuterRequest(mIsOuterRequest);
        request.setForceRequestNet(mIsRefresh);
        request.setShouldCache(mShouldCache);
        if (mCacheTime > 0) {
            request.setCacheTime(mCacheTime);
        }
        return request;
    }

    /**
     * 以StringRequest方式请求网络
     */
    public void requestNet() {
        requestNet(STRING);
    }

    /**
     * @param type RequestBuilder.STRING RequestBuilder.JSON_OBJECT RequestBuilder.UPLOAD
     *            RequestBuilder.REFRESH
     * @return
     */
    public void requestNet(int type) {
        Request<?> request = create(type);
        if (type == LOGIN) {
            RequestQueue.setAutoLoginRequest((AutoLoginRequest) request);
        }
        Volley.getRequestQueue(mContext).add(request);
    }
}
