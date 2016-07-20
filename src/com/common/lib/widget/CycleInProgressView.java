/**
 * 
 */

package com.common.lib.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.lib.R;

/**
 * @author luchonghui
 */
public class CycleInProgressView extends RelativeLayout {

    /**
     * 加载布局
     */
    private View mProgressView;
    /**
     * 加载动画
     */
    private ImageView mProgressDialogImg;

    private TextView msgTxv;

    private Context mContext;
    private Animation mProgressAnim;

    public CycleInProgressView(Context context) {
        super(context, null);
        mContext = context;
        initProgress();
    }

    /**
     * @param context
     * @param attrs
     */
    public CycleInProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initProgress();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CycleInProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initProgress();
    }

    /**
     * 初始化加载布局
     */
    public void initProgress()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        mProgressView = layoutInflater.inflate(R.layout.load_progress_layout, null);
        mProgressDialogImg = (ImageView) mProgressView.findViewById(R.id.progressDialogImg);
        this.msgTxv = (TextView) mProgressView.findViewById(R.id.msgTxv);
        mProgressAnim = AnimationUtils.loadAnimation(mContext, R.anim.public_rotate_anim);
        if (mProgressView != null)
        {
            mProgressView.setVisibility(View.GONE);
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        addView(mProgressView, params);
    }

    /**
     * 展示加载布局
     * 
     * @param msg
     */
    public void showProgress(final String msg)
    {
        ((Activity) mContext).runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                if (mProgressView != null)
                {
                    msgTxv.setText(msg);
                    mProgressView.setVisibility(View.VISIBLE);

                    if (null != mProgressDialogImg)
                    {
                        mProgressDialogImg.clearAnimation();
                        mProgressDialogImg.startAnimation(mProgressAnim);
                    }

                }
            }
        });
    }

    /**
     * 隐藏加载布局
     */
    public void dismissProgress()
    {
        ((Activity) mContext).runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                if (mProgressView != null)
                {
                    mProgressView.setVisibility(View.GONE);
                    mProgressAnim.reset();
                    mProgressAnim.cancel();
                    if (null != mProgressDialogImg)
                    {
                        mProgressDialogImg.clearAnimation();
                    }

                }
            }
        });
    }
}
