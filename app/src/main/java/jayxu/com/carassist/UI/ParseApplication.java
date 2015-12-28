package jayxu.com.carassist.UI;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParsePush;

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
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParsePush.subscribeInBackground("Tether");
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }

}
