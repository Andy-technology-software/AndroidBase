package helloandroid.andy.com.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.path;

/**
 * Created by lingnet on 2017/9/12.
 */

public class Utils {

    /**
     * 保存QQ号+密码
     * @param number
     * @param pwd
     * @return
     */
    public static boolean saveUserInfo(Context context, String number, String pwd){
        //这是放到本机内存
//        File fileDir = context.getFilesDir();
        File fileDir = context.getCacheDir();
        File f = new File(fileDir, "qq.txt");
        try {

            FileOutputStream fos = new FileOutputStream(f);
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
//        File fileDir = context.getFilesDir();
        File fileDir = context.getCacheDir();
        File f = new File(fileDir, "qq.txt");
        try {
            FileInputStream fis = new FileInputStream(f);
            //字符流对象
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String text = reader.readLine();

            if (!TextUtils.isEmpty(text)) {
                String[] split = text.split("##");
                Map<String, String>userInfoMap = new HashMap<String, String>();
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
