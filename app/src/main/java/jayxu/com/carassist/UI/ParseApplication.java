package jayxu.com.carassist.UI;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

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

        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }

}
