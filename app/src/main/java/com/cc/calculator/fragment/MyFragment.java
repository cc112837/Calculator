package com.cc.calculator.fragment;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.cc.calculator.R;
import com.cc.calculator.activity.AboutActivity;
import com.cc.calculator.activity.AdviceActivity;
import com.cc.calculator.activity.CollectionActivity;
import com.cc.calculator.activity.SettingActivity;
import com.cc.calculator.utils.CacheUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MyFragment extends Fragment implements View.OnClickListener {
    private ImageView iv_head;
    private LinearLayout ll_set, ll_share, ll_collect, ll_about, ll_record;
    String dateTime;
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
        iv_head.setOnClickListener(this);
        ll_set.setOnClickListener(this);
        ll_share.setOnClickListener(this);
        ll_collect.setOnClickListener(this);
        ll_about.setOnClickListener(this);
        ll_record.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
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
                startActivity(intent);
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
                    if (extras != null) {
                        Bitmap bitmap = extras.getParcelable("data");
//                        String uri="";//服务端的头像地址
//                        ImageLoader
//                                .getInstance()
//                                .displayImage(uri,iv_head, PhotoUtils.avatarImage);
                        iv_head.setImageBitmap(bitmap);
                    }
                }

                break;
            default:
                break;
        }

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
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("灭火助手");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.wandoujia.com/apps/com.cc.calculator/download");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("灭火助手");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl("http://img.wdjimg.com/mms/icon/v1/e/ff/2a699806f4202b232e3d8a0c08ecaffe_96_96.png");//
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.wandoujia.com/apps/com.cc.calculator/download");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("灭火助手");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.wandoujia.com/apps/com.cc.calculator/download");
        // 启动分享GUI
        oks.show(getContext());
    }
}
