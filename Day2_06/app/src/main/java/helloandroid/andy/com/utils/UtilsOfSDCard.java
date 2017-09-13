package helloandroid.andy.com.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lingnet on 2017/9/12.
 */

public class UtilsOfSDCard {

    /**
     * 保存QQ号+密码
     * @param number
     * @param pwd
     * @return
     */
    public static boolean saveUserInfo(Context context, String number, String pwd){
        //判断当前手机是否有SD卡
        String state = Environment.getExternalStorageState();

        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            //已经挂载
            return false;
        }

        File sdCardFile = Environment.getExternalStorageDirectory();
        File file = new File(sdCardFile, "qq1.txt");

        try {
            FileOutputStream fos = new FileOutputStream(file);

            String data = number + "##" + pwd;

            fos.write(data.getBytes());
            fos.flush();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static Map<String, String> getUserInfo(Context context) {
        //判断当前手机是否有sd卡
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return null;
        }

        File sdCardFile = Environment.getExternalStorageDirectory();
        File file = new File(sdCardFile,"qq1.txt");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String text = reader.readLine();
            reader.close();
            if (!TextUtils.isEmpty(text)) {
                Map<String, String>userInfoMap =  new HashMap<String, String>();
                String[] split = text.split("##");
                userInfoMap.put("number",split[0]);
                userInfoMap.put("password",split[1]);
                return userInfoMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
