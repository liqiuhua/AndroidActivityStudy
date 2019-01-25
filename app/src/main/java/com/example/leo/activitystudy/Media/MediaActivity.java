package com.example.leo.activitystudy.Media;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentUris;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.os.Bundle;

import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.leo.activitystudy.ActivityCollector;
import com.example.leo.activitystudy.BaseActivity;
import com.example.leo.activitystudy.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MediaActivity extends BaseActivity implements View.OnClickListener {

    private EditText editTextNotificationText;
    private static final int TAKE_PHOTO = 1;
    private static final int SELECT_PHOTO = 2;
    private ImageView imageViewCameraPicture;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_layout);
        findViewById(R.id.pbtnSendNotification).setOnClickListener(this);
        editTextNotificationText = (EditText)findViewById(R.id.editTextNotificationText);
        findViewById(R.id.pbtnTakePhoto).setOnClickListener(this);
        findViewById(R.id.pbtnSelectPhoto).setOnClickListener(this);
        imageViewCameraPicture = (ImageView)findViewById(R.id.ImgViewShowCameraImg);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.pbtnSendNotification:
                NotificationManager notificationManager;
                notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                String channelId= "LeoNotificationChannelId";
                String strNotificationText = editTextNotificationText.getText().toString();
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelId);
                    if(null == notificationChannel)
                    {
                        notificationChannel= new NotificationChannel(channelId, "default channel", NotificationManager.IMPORTANCE_DEFAULT);
                        notificationChannel.enableVibration(true);
                        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

                        notificationManager.createNotificationChannel(notificationChannel);
                    }
                    NotificationCompat.Builder builder= new NotificationCompat.Builder(this ,channelId);//这个channelId与NotificatioChannel中的id要保持一致
                    Notification notification = builder.setContentTitle("This is Notification title")
                                                        .setContentText(strNotificationText)
                                                        .setWhen(System.currentTimeMillis())
                                                        .setSmallIcon(R.mipmap.ic_launcher)
                                                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                                        .setAutoCancel(true)
                                                        .setDefaults(Notification.DEFAULT_ALL)
                                                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                                        .build();
                    notificationManager.notify(1, notification);
                }
                else
                {
                    Notification notification = new NotificationCompat.Builder(this,channelId)
                                                                        .setContentTitle("This is Notification title")
                                                                        .setContentText(strNotificationText)
                                                                        .setWhen(System.currentTimeMillis())
                                                                        .setSmallIcon(R.mipmap.ic_launcher)
                                                                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                                                        .build();
                    notificationManager.notify(1, notification);
                }
                break;
            case R.id.pbtnTakePhoto:
            {
                File outputImage = new File(getExternalCacheDir(),"output_image.jpg");
                try {
                    if(outputImage.exists())
                    {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT>=24)
                {
                    imageUri = FileProvider.getUriForFile(getBaseContext(),"com.example.leo.activitystudy.fileProvider",outputImage);
                }
                else
                    {
                        imageUri= Uri.fromFile(outputImage);
                    }

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
            }
                break;
            case R.id.pbtnSelectPhoto:
                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                    }
                    else
                    {
                        OpenAlbum();
                    }
                break;
                default:
                    break;
        }
    }
    private void OpenAlbum()
    {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,SELECT_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case 1:
                if(grantResults.length> 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    OpenAlbum();
                }
                else
                {
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch (requestCode)
        {

            case TAKE_PHOTO:

                if(resultCode==RESULT_OK)
                {
                    Toast.makeText(this,"12", Toast.LENGTH_SHORT).show();
                    try{
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        imageViewCameraPicture.setImageBitmap(bitmap);
                    }catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
                break;
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK)
                {
                    if(Build.VERSION.SDK_INT>=19)
                    {
                        handleImageOnKitKat(data);
                    }
                }
                break;
                default:break;
        }
    }
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data)
    {
        String imagePath=null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this,uri))
        {
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority()))//如果是document类型的Uri，则通过document id 处理
            {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "="+id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);

            }
            else if ("com.android.providers.downloads.documents".equals(uri.getAuthority()))
            {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }

        }
        else if ("content".equalsIgnoreCase(uri.getScheme()))//如果是content类型的Uri
        {
            imagePath=getImagePath(uri,null);
        }
        else if("file".equalsIgnoreCase(uri.getScheme()))
        {
            imagePath= uri.getPath();
        }
        displayImage(imagePath);
    }
    private String getImagePath(Uri uri,String selection)
    {
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor != null)
        {
            if(cursor.moveToNext())
            {
                path= cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

            }
            cursor.close();
        }
        return path;
    }
    private void displayImage(String imagePath)
    {
        if(imagePath !=null)
        {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageViewCameraPicture.setImageBitmap(bitmap);
        }
        else
        {
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }
}
