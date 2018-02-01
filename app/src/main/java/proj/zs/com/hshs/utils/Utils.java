package proj.zs.com.hshs.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zsz on 2018/2/1.
 */

public class Utils {
    /**
     * 通过传入图片地址，获取图片
     */
    public static Bitmap getBitmap(String url){
        Bitmap bitmap=null;
        try {
            HttpURLConnection connection= (HttpURLConnection) (new URL(url)).openConnection();
            InputStream is=connection.getInputStream();
            bitmap= BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
