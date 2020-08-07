package com.uzair.onesignaldemo;


import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.onesignal.OSNotification;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

public class MyApplication extends Application
{
    static MyApplication mInstance;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public MyApplication() {
        this.mInstance = mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        sharedPreferences = getSharedPreferences("data"  , MODE_PRIVATE);

        editor = sharedPreferences.edit();


        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new NotificationHandler())
                .init();

    }


    public static synchronized MyApplication getInstance()
    {
        return mInstance;
    }


    public class NotificationHandler implements OneSignal.NotificationOpenedHandler
    {

        @Override
        public void notificationOpened(OSNotificationOpenResult result)
        {
            JSONObject jsonObject = result.notification.payload.additionalData;

            if(jsonObject != null && jsonObject.has("name") && jsonObject.has("phone"))
            {
                try {

                    Log.d("nameOfKey", "notificationOpened: "+jsonObject.getString("name"));
                    editor.putString("name", jsonObject.getString("name"));
                    editor.putString("phone", jsonObject.getString("phone"));
                    editor.commit();
                    editor.apply();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
