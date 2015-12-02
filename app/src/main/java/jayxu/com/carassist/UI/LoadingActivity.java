package jayxu.com.carassist.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.ParseUser;

import jayxu.com.carassist.R;

public class LoadingActivity extends AppCompatActivity {
    private static final String TAG = LoadingActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  Parse.enableLocalDatastore(this);
      //  Parse.initialize(this, "yHw3ct2piwkgTiNlPfQE8fqd7Tyabn21BRvoctmO", "WwucV7c4ljt47lvrKRcd7FSjsA1Ph0b5mW7w9HQ5");

        if(ParseUser.getCurrentUser()!=null) {
            Intent mIntent= new Intent(LoadingActivity.this, HomeActivity.class);
            mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(mIntent);
        }else {
            setContentView(R.layout.activity_loading);
            try {
                // Simulate loading access.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.e(TAG, "ERROR trying to sleep!");
            }
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }


    }

}
