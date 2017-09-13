package helloandroid.andy.com.dat2_10_sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lingnet on 2017/9/13.
 */

public class UtilsOfSharedPreferences {

    /**
     * 存信息
     * @param context
     * @param number
     * @param pwd
     * @return
     */
    public static boolean saveUserInfo(Context context, String number, String pwd) {
        SharedPreferences sp = context.getSharedPreferences("qq",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();

        editor.putString("number",number);
        editor.putString("password",pwd);

        editor.commit();
        return true;
    }

    /**
     * 取信息
     * @param context
     * @return
     */
    public static Map<String,String> getUserInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("qq",Context.MODE_PRIVATE);
        String number = sp.getString("number",null);
        String password = sp.getString("password",null);

        if (!TextUtils.isEmpty(number) && !TextUtils.isEmpty(password)) {
            Map<String,String>userInfoMap = new HashMap<String, String>();
            userInfoMap.put("number",number);
            userInfoMap.put("password",password);
            return userInfoMap;
        }
        return null;
    }
}
