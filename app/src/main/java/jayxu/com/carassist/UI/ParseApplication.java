package jayxu.com.carassist.UI;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.service.XGPushService;

/**
 * Created by Yuchen on 12/1/2015.
 */
public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

		/*
		 * Add Parse initialization code here
		 */
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "yHw3ct2piwkgTiNlPfQE8fqd7Tyabn21BRvoctmO", "WwucV7c4ljt47lvrKRcd7FSjsA1Ph0b5mW7w9HQ5");

        //Commenting out the Parse Notification system, and using XinGe.
//        ParseInstallation.getCurrentInstallation().saveInBackground();
//        ParsePush.subscribeInBackground("Tether");
        XGPushManager.registerPush(this, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                Log.d("TPush", "Register Successful!token is：" + data);
            }
            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "!+++++++++Register Failed" + errCode + ", Error message：" + msg);
            }
        });

//        // 2.36（不包括）之前的版本需要调用以下2行代码
        Intent service = new Intent(this, XGPushService.class);
        this.startService(service);

        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }

}
