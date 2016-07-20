/**
 * 
 */

package com.common.lib.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.common.lib.R;
import com.common.lib.utils.FileUtils;
import com.common.lib.utils.ImageUtils;
import com.common.lib.utils.StringUtils;

import java.io.File;

/**
 * 获取照片
 * 
 * @author luchonghui
 */
public class TakePictureActivity extends Activity implements OnClickListener {
    private RelativeLayout mTakePictureRlv;
    private Button cameraChooseBtn;
    private Button picChooseBtn;
    private Button cancelBtn;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int IMAGE_REQUEST_CODE = 2;
    public static final String IMAGEFILEPATH = "ImageFilePath";
    private final String IMAGE_TYPE = "image/*";
    private String path = null;
    private String filePath = null;
    private int mResultCode = 0;
    private String mResultKey = null;
    /** 照片文件保存路径 */
    public static String FILE_PHOTO_SAVE_PATH = "zhangmai/photo";
    /** 照片命名格式 */
    public static final String CAMERA_DATE_FORMAT = "yyyyMMdd_HHmmss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            path = savedInstanceState.getString(IMAGEFILEPATH);
        }
        setContentView(R.layout.activity_take_picture);
        initView();
    }

    private void initView()
    {
        mTakePictureRlv = (RelativeLayout) findViewById(R.id.rlv_take_picture);
        mTakePictureRlv.setOnClickListener(this);

        cameraChooseBtn = (Button) findViewById(R.id.camera_choose_btn);
        picChooseBtn = (Button) findViewById(R.id.pic_choose_btn);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);

        cameraChooseBtn.setOnClickListener(this);
        picChooseBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.rlv_take_picture) {
            setResult(RESULT_CANCELED, getIntent());
            finish();
        } else if (v.getId() == R.id.camera_choose_btn) {
            path = openSysImageCamera(this);
        } else if (v.getId() == R.id.pic_choose_btn) {
            // 调用系统相册
            Intent getAlbum = new Intent();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                getAlbum.setAction(Intent.ACTION_OPEN_DOCUMENT);
            } else {
                getAlbum.setAction(Intent.ACTION_GET_CONTENT);
            }
            getAlbum.setType(IMAGE_TYPE);
            startActivityForResult(getAlbum, IMAGE_REQUEST_CODE);
        } else if (v.getId() == R.id.cancel_btn) {
            finish();
        }

        // switch (v.getId())
        // {
        //
        // case R.id.rlv_take_picture: {
        // setResult(RESULT_CANCELED, getIntent());
        // finish();
        // break;
        // }
        // case R.id.camera_choose_btn: {
        // // 调用系统照相
        // path = openSysImageCamera(this);
        // break;
        // }
        // case R.id.pic_choose_btn: {
        // // 调用系统相册
        // Intent getAlbum = new Intent();
        // if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
        // getAlbum.setAction(Intent.ACTION_OPEN_DOCUMENT);
        // } else {
        // getAlbum.setAction(Intent.ACTION_GET_CONTENT);
        // }
        // getAlbum.setType(IMAGE_TYPE);
        // startActivityForResult(getAlbum, IMAGE_REQUEST_CODE);
        // break;
        // }
        // case R.id.cancel_btn: {
        // finish();
        // break;
        // }
        // }

    }

    // 解决三星手机拍照问题
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(IMAGEFILEPATH, path);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {

            case CAMERA_REQUEST_CODE: {
                if (!TextUtils.isEmpty(path))
                {
                    File file = new File(path);
                    Uri originalUri = Uri.fromFile(file);
                    if (originalUri != null && file != null && file.exists() && file.length() > 0
                            && (resultCode == -1))
                    {
                        filePath = ImageUtils.cutBitmap(this, originalUri);
                        mResultCode = ImageUtils.RESULT_CODE_TAKE_SUCCESS;
                        mResultKey = ImageUtils.BITMAP_LOCALPATH_PARAM;
                    }
                    else {
                        finish();
                        return;
                    }
                }
                else {
                    finish();
                    return;
                }

            }
                break;
            case IMAGE_REQUEST_CODE: {
                if (data == null)
                {
                    finish();
                    return;
                }
                Uri originalUri = data.getData();
                filePath = ImageUtils.cutBitmap(this, originalUri);
                mResultCode = ImageUtils.RESULT_CODE_AMBUM_SUCCESS;
                mResultKey = ImageUtils.BITMAP_URI_PARAM;

            }
                break;
            case ImageUtils.REQUEST_CODE_IMAGE_CROP: {
                if (!StringUtils.isEmpty(filePath) && resultCode == RESULT_OK) {
                    Intent intent = new Intent();
                    intent.putExtra(mResultKey, filePath);
                    setResult(mResultCode, intent);
                }
                finish();
            }
                break;
            default:
                break;
        }
    }

    /**
     * 开启系统照相机
     * 
     * @param activity
     */
    @SuppressLint("SimpleDateFormat")
    public static synchronized String openSysImageCamera(Activity activity)
    {
        Intent localIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 图片存放文件夹路径
        String path = FileUtils.getSDRoot() + FILE_PHOTO_SAVE_PATH;

       // Date date = new Date();

        // SimpleDateFormat sDateFormat = new SimpleDateFormat(CAMERA_DATE_FORMAT);
        //
        // String time = sDateFormat.format(date);

        // 文件名称
        String fileName = "zm_pic" + ".jpg";

        File folder = new File(path);

        // 判断文件夹是否存在
        if (!folder.exists())
        {
            folder.mkdirs();
        }

        // 图片文件存放完整路径
        String wholePath = path + "/" + fileName;

        File file = new File(wholePath);

        Uri outputFileUri = Uri.fromFile(file);
        localIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        // 启动
        activity.startActivityForResult(localIntent, CAMERA_REQUEST_CODE);

        return wholePath;
        // return outputFileUri.toString();
    }

    /**
     * 获取SD卡的路径
     * 
     * @return
     */
    public static String getSDCardPath()
    {
        if (isAvaiableSDCard())
        {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }

    /**
     * 判断SD卡是否可用
     * 
     * @return
     */
    public static boolean isAvaiableSDCard()
    {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.push_bottom_out);
    }

}
