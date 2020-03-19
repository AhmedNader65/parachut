package com.mrerror.parachut.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import static com.mrerror.parachut.utils.Utils.hideSoftKeyboard;


public class GlobalPrefrencies {


    final static String USER_ID = "id";
    final static String SETTINGS_LOGIN = "login";
    final static String APP_lANGUAGE = "language";
    final static String PREFS_NAME = "settings";
    static final String USER_API_TOKEN = "api_token";
    static final String USER_PASSWORD = "password";
    static final String USER_IMAGE = "user_image";
    final static String USER_PHONE = "phone";
    final static String USER_EMAIL = "email";
    private static final String CURRANCY = "carrancy";
    private static final String COUNTRY_ID = "country_id";
    private static final String CiTY = "city";
    private static final String TXTTAB = "txtTab";
    private static final String USER_NAME = "USER_NAME";
    private static final String ALLOW_NOTIFICATION = "allow_notification";
    public final String USERDATA = "MyVariables";
    private Context context;
    private SharedPreferences prefs;
    private SharedPreferences.Editor PrefsEditor;

    public GlobalPrefrencies(Context context) {
        this.context = context;
        prefs = null;
        prefs = context.getSharedPreferences(PREFS_NAME, 0);
        PrefsEditor = prefs.edit();
    }

    //for hide keyboard
    public static void setupUI(View view, final Activity activity) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, activity);
            }
        }
    }

    public void storeLanguage(String lang) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("language", lang);
        editor.commit();

    }

    public void storeWieght(String wieght) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("wieght", wieght);
        editor.commit();

    }

    public String getWieght() {

        String value = "";
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        value = prefs.getString("wieght", "70");
        return value;
    }

    public void storeValWaterML(String waterml) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("waterml", waterml);
        editor.commit();

    }

    public String getValWaterML() {

        String value = "";
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        value = prefs.getString("waterml", "2000");
        return value;
    }

    public void storeValWaterMLDrink(String watermlDrink) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("watermlDrink", watermlDrink);
        editor.commit();

    }

    public String getValWaterMLDrink() {

        String value = "";
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        value = prefs.getString("watermlDrink", "5000");
        return value;
    }

    public Boolean getIsSettingBody() {
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        Boolean value = prefs.getBoolean("isDataSet", false);
        return value;
    }

    public void storeIsDataSet(Boolean isDataSet) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("isDataSet", isDataSet);
        editor.commit();

    }

    public Boolean getAllowNotification() {
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        Boolean value = prefs.getBoolean(ALLOW_NOTIFICATION, true);
        return value;
    }

    public void storeAllowNotification(Boolean status) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(ALLOW_NOTIFICATION, status);
        editor.commit();

    }

    public String getLanguage() {

        String value = "";
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        Log.d(APP_lANGUAGE, Locale.getDefault().getDisplayLanguage());
        value = prefs.getString("language", "ar");
        return value;
    }

    public void storeUserImage(String user_image) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(USER_IMAGE, user_image);
        editor.commit();

    }

    public String getUserImage() {
        String value = "";
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        Log.d(APP_lANGUAGE, Locale.getDefault().getDisplayLanguage());
        value = prefs.getString(USER_IMAGE, "");
        return value;
    }

    public void storeCurrancy(String currancy) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(CURRANCY, currancy);
        editor.commit();
    }

    public void storeCountry(String idCountry) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(COUNTRY_ID, idCountry);
        editor.commit();

    }

    public String getName() {
        String value = "";
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        Log.d(APP_lANGUAGE, Locale.getDefault().getDisplayLanguage());
        value = prefs.getString(USER_NAME, "");
        return value;
    }

    public String getTypeCurrancy() {
        String value = "";
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        Log.d(APP_lANGUAGE, Locale.getDefault().getDisplayLanguage());
        value = prefs.getString(CURRANCY, "ر.س");
        return value;
    }

    public Boolean getLoginStatus() {
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        Boolean value = prefs.getBoolean(SETTINGS_LOGIN, false);
        return value;
    }

    public void storeLoginStatus(Boolean status) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(SETTINGS_LOGIN, status);
        editor.commit();

    }

    public String getApi_token() {
        String value = "";
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        Log.d(APP_lANGUAGE, Locale.getDefault().getDisplayLanguage());
        value = prefs.getString(USER_API_TOKEN, "");
        return value;
    }

    public void storeTypeShowRecycle(String txtTab) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(TXTTAB, txtTab);
        editor.commit();
    }

    public String getCountry() {
        String value = "";
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        value = prefs.getString(COUNTRY_ID, "");
        return value;
    }

    public String getCity() {
        String value = "";
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        value = prefs.getString(CiTY, "");
        return value;
    }

    public void storeCity(String city) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(CiTY, city);
        editor.commit();

    }

    public void storePassword(String password) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(USER_PASSWORD, password);
        editor.commit();
    }

    public String getUserPassword() {
        String value = "";
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        Log.d(APP_lANGUAGE, Locale.getDefault().getDisplayLanguage());
        value = prefs.getString(USER_PASSWORD, "0");
        return value;
    }

    public void storePhone(String phone) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(USER_PHONE, phone);
        editor.commit();

    }

    public void storeEmail(String email) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(USER_EMAIL, email);
        editor.commit();

    }

    public String getEmail() {
        String value = "";
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        value = prefs.getString(USER_EMAIL, "0");
        return value;
    }

    public void saveMap(String key, Map<String, String> inputMap) {
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        if (prefs != null) {
            JSONObject jsonObject = new JSONObject(inputMap);
            String jsonString = jsonObject.toString();
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(key).commit();
            editor.putString(key, jsonString);
            editor.commit();
        }
    }

    public Map<String, String> loadMap(String key) {
        Map<String, String> outputMap = new HashMap<String, String>();
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        try {
            if (prefs != null) {
                String jsonString = prefs.getString(key, (new JSONObject()).toString());
                JSONObject jsonObject = new JSONObject(jsonString);
                Iterator<String> keysItr = jsonObject.keys();
                while (keysItr.hasNext()) {
                    String k = keysItr.next();
                    String v = (String) jsonObject.get(k);
                    outputMap.put(k, v);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputMap;
    }

    public void storeFB(String fb) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("startclock", fb);
        editor.commit();

    }

    public String getFB() {
        String value = "";
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);

        value = prefs.getString("startclock", "");
        return value;
    }

    public void storeTW(String TW) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("TW", TW);
        editor.commit();

    }

    public String getTW() {
        String value = "";
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);

        value = prefs.getString("TW", "");
        return value;
    }

    public void storeIns(String Ins) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Ins", Ins);
        editor.commit();

    }

    public String getIns() {
        String value = "";
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);

        value = prefs.getString("Ins", "");
        return value;
    }

    public void storeUserId(int id) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(USER_ID, String.valueOf(id));
        Log.e("DSDSDS", id + "");
        editor.commit();

    }

    public String getUserId() {
        String value = "";
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);

        value = prefs.getString(USER_ID, "0");
        return value;
    }

    public void storeName(String id) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(USER_NAME, String.valueOf(id));
        editor.commit();

    }

    public String getAddress() {
        String value = "";
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);

        value = prefs.getString("address", "1");
        return value;
    }

    public void storeAddress(String Address) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("address", Address);
        editor.commit();

    }

    public void storeApi_token(String api_token) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(USER_API_TOKEN, api_token);
        editor.commit();
    }

    public Boolean getIsFirstOpeen() {
        final SharedPreferences prefs = context.getSharedPreferences(
                PREFS_NAME, 0);
        Boolean value = prefs.getBoolean("firstStart", true);
        return value;
    }

    public void setIsFirstOpeen(Boolean status) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
                0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("firstStart", status);
        editor.commit();

    }

}
