package com.cc.calculator.fragment;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cc.calculator.MyApplication;
import com.cc.calculator.R;
import com.cc.calculator.activity.AboutActivity;
import com.cc.calculator.activity.AdviceActivity;
import com.cc.calculator.activity.CollectionActivity;
import com.cc.calculator.activity.LoginActivity;
import com.cc.calculator.activity.MainActivity;
import com.cc.calculator.activity.SettingActivity;
import com.cc.calculator.constant.Constants;
import com.cc.calculator.model.UpdaUser;
import com.cc.calculator.model.User;
import com.cc.calculator.utils.CacheUtils;
import com.cc.calculator.utils.MyHttpUtils;
import com.cc.calculator.utils.PathUtils;
import com.cc.calculator.utils.PhotoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MyFragment extends Fragment implements View.OnClickListener {
    private ImageView iv_head;
    private LinearLayout ll_set, ll_share, ll_collect, ll_about, ll_record;
    String dateTime, path;
    private TextView tv_nickname;
    private AlertDialog avatarDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my, container, false);
        ShareSDK.initSDK(getContext());
        init(v);
        return v;
    }

    private void init(View v) {
        iv_head = (ImageView) v.findViewById(R.id.iv_head);
        ll_set = (LinearLayout) v.findViewById(R.id.ll_set);
        ll_share = (LinearLayout) v.findViewById(R.id.ll_share);
        ll_collect = (LinearLayout) v.findViewById(R.id.ll_collect);
        ll_about = (LinearLayout) v.findViewById(R.id.ll_about);
        ll_record = (LinearLayout) v.findViewById(R.id.ll_record);
        tv_nickname = (TextView) v.findViewById(R.id.tv_nickname);
        boolean sharelogin = MyApplication.sharedPreferences.getBoolean(Constants.SHARELOGIN,
                false);
        String name = ((MainActivity) getActivity()).getName();
        if (sharelogin) {
            tv_nickname.setText("用户名：" + name);
        } else {
            String maskNumber = name.substring(0, 3) + "****" + name.substring(7, name.length());
            tv_nickname.setText("用户名：" + maskNumber);
        }
        iv_head.setOnClickListener(this);
        ll_set.setOnClickListener(this);
        ll_share.setOnClickListener(this);
        ll_collect.setOnClickListener(this);
        ll_about.setOnClickListener(this);
        ll_record.setOnClickListener(this);
        tv_nickname.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_nickname:
                break;
            case R.id.ll_about://关于界面
                intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_share://分享
                showShare();
                break;
            case R.id.ll_collect://收藏
                intent = new Intent(getActivity(), CollectionActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_record://意见反馈
                intent = new Intent(getActivity(), AdviceActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_set://设置
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivityForResult(intent, 5);
                break;
            case R.id.iv_head://图像选择
                AvatarDialog();
                break;
        }
    }

    private void AvatarDialog() {
        avatarDialog = new AlertDialog.Builder(getContext()).create();
        avatarDialog.setCanceledOnTouchOutside(true);
        View v = LayoutInflater.from(getContext()).inflate(R.layout.my_headicon, null);
        avatarDialog.show();
        avatarDialog.setContentView(v);
        avatarDialog.getWindow().setGravity(Gravity.CENTER);
        RadioButton albumPic = (RadioButton) v.findViewById(R.id.album_pic);
        RadioButton cameraPic = (RadioButton) v.findViewById(R.id.camera_pic);
        albumPic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                avatarDialog.dismiss();
                Date date1 = new Date(System.currentTimeMillis());
                dateTime = date1.getTime() + "";
                showAvatarFromAlbum();//从图库选择
            }
        });
        cameraPic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                avatarDialog.dismiss();
                Date date = new Date(System.currentTimeMillis());
                dateTime = date.getTime() + "";
                showAvatarFromCamera();//从相机选择
            }
        });
    }

    private void showAvatarFromCamera() {
        File f = new File(CacheUtils.getCacheDirectory(getContext(), true, "icon") + dateTime);
        if (f.exists()) {
            f.delete();
        }
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri uri = Uri.fromFile(f);

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(camera, 1);
    }

    private void showAvatarFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1:
                String files = CacheUtils.getCacheDirectory(getContext(), true, "icon") + dateTime;
                File file = new File(files);
                if (file.exists() && file.length() > 0) {
                    Uri uri = Uri.fromFile(file);
                    startPhotoZoom(uri);
                } else {
                }
                break;
            case 2:
                if (data == null) {
                    return;
                }
                startPhotoZoom(data.getData());
                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    path = saveCropAvatar(data);
                    if (extras != null) {
                        String uri = Constants.SERVER_URL + "UploadServlet";//服务端的头像地址
                        User user = new User();
                        user.setPhone(MyApplication.sharedPreferences.getString(Constants.LOGIN_ACCOUNT, ""));
                        user.setPassWord(path);
                        MyHttpUtils.handData(handler, 14, uri, user);
                    }
                }

                break;
            case 5:
                if (data != null) {
                    String set = data.getStringExtra("set");
                    if ("set".equals(set)) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                break;
            default:
                break;
        }

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 14:
                    UpdaUser user = (UpdaUser) msg.obj;
                    if ("1".equals(user.getStatus())) {
                        ImageLoader
                                .getInstance()
                                .displayImage(path, iv_head, PhotoUtils.avatarImageOption);
                    } else {
                        Toast.makeText(getActivity(), user.getData(), Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };

    private String saveCropAvatar(Intent data) {
        Bundle extras = data.getExtras();
        String path = null;
        if (extras != null) {
            Bitmap bitmap = extras.getParcelable("data");
            if (bitmap != null) {
                bitmap = PhotoUtils.toRoundCorner(bitmap, 10);
                path = PathUtils.getAvatarCropPath();
                PhotoUtils.saveBitmap(path, bitmap);
                if (bitmap != null && bitmap.isRecycled() == false) {
                    bitmap.recycle();
                }
            }
        }
        return path;
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 120);
        intent.putExtra("outputY", 120);
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK(getContext());
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle("灭火助手");
        oks.setTitleUrl("http://www.wandoujia.com/apps/com.cc.calculator/download");
        oks.setText("灭火助手");
        oks.setImageUrl("http://img.wdjimg.com/mms/icon/v1/e/ff/2a699806f4202b232e3d8a0c08ecaffe_96_96.png");//
        oks.setUrl("http://www.wandoujia.com/apps/com.cc.calculator/download");
        oks.setComment("灭火助手");
        oks.setSite(getString(R.string.app_name));
        oks.setSiteUrl("http://www.wandoujia.com/apps/com.cc.calculator/download");
        oks.show(getContext());
    }
}
