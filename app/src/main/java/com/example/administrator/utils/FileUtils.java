package com.example.administrator.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import com.example.administrator.MyApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by LiuTao on 2017/7/21 0021.
 * 文件缓存图片
 */

public class FileUtils {
    //判断是否本地有sd卡，确定是否保存在SD卡内
    String path;//文件存储的地方

    /**
     * 获取缓存路径
     * /mnt/sdcard/Android/data/应用包名/cache/okhttpcache，
     * @param context
     * @return
     */
    public static String getCachePath(Context context) {
        String cachePath = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {

            cachePath = context.getExternalCacheDir().getPath();

        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    /**
     * 通过构造方法传入存储的路径
     */
    public FileUtils(Context context, String dirName) {
        //判断是否本地有sd卡，这里代表的是SD卡在就绪的状态
//这里判断相等状态要使用.equal，使用==会匹配不到？？？
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            LogUtils.e("SD卡就绪状态");
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + dirName + "/";
        } else {
            LogUtils.e("SD卡没有就绪状态");
            //保存在内部存储器中
            path = context.getCacheDir().getAbsolutePath() + "/" + dirName + "/";
        }
        //创建文件
        new File(path).mkdirs();
    }

    /**
     * 文件的写入
     * 传入一个文件的名称和一个Bitmap对象
     * 最后的结果是保存一个图片
     */
    public void saveToSDCard(String key, Bitmap bmp) {
        FileOutputStream fos;
        try {
            File file = new File(path + key);
            fos = new FileOutputStream(file);
            //保存图片的设置，压缩图片
            bmp.compress(Bitmap.CompressFormat.PNG, 50, fos);
            fos.flush();
            fos.close();//关闭流

            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            MyApplication.getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
     /*   Uri uri;
        File file = new File(path);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(MyApplication.getContext(), "com.example.administrator"+ ".fileprovider",file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);//这个权限要添加
        }else
         uri = Uri.fromFile(file);

        intent.setData(uri);
        MyApplication.getContext().sendBroadcast(intent);*/
    //保存图片的设置，压缩图片


    /**
     * 文件的读取，
     * 根据文件的名字，读取出一个Bitmap的对象，
     * 如果之前保存过就有值，否则是null
     */
    public Bitmap readFromSDCard(String key) {
        return BitmapFactory.decodeFile(path + key);
    }

    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmaps(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 设置压缩的图片的大小设置的参数
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;//图片的原始高度
        int width = options.outWidth;  //图片的原始宽度
        int inSampleSize = 1;          //压缩比例
        if (height > reqHeight || width > reqWidth) {  //
            int heightRatio = Math.round(height) / reqHeight;
            int widthRatio = Math.round(width) / reqWidth;
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
}
