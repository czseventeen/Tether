package jayxu.com.carassist.UI;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jayxu.com.carassist.MODEL.Home;
import jayxu.com.carassist.MODEL.MyCar;
import jayxu.com.carassist.MODEL.MyStats;
import jayxu.com.carassist.MODEL.UsefulConstants;
import jayxu.com.carassist.R;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends Activity implements LoaderCallbacks<Cursor> {
    private static final String KEY_USERNAME = "username";
    private static String VALUE_USERNAME= "";
//    /**
//     * A dummy authentication store containing known user names and passwords.
//     * TODO: remove after connecting to a real authentication system.
//     */
//    private static final String[] DUMMY_CREDENTIALS = new String[]{
//            "foo@example.com:hello", "bar@example.com:world"
//    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
//    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button mRegisterButton;
    private static final String TAG = RegisterActivity.class.getSimpleName();

    private final ParseObject homeParseObj = new ParseObject(UsefulConstants.ParseClassNameHome);
    private final ParseObject myCarParseObj = new ParseObject(UsefulConstants.ParseClassNameMyCar);
    private final ParseObject myStatsParseObj = new ParseObject(UsefulConstants.ParseClassNameMyStats);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        mRegisterButton =(Button)findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmailView = (AutoCompleteTextView) findViewById(R.id.register_email);
                mPasswordView = (EditText) findViewById(R.id.register_password);
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                // System.out.println("+++++++++++++++Email was:"+email);
                // System.out.println("+++++++++++++++Password was:"+password);

                final ParseUser user = new ParseUser();
                user.setEmail(email);
                user.setUsername(email.split("@")[0]);
                VALUE_USERNAME = user.getUsername();
                user.setPassword(password);
                /*
                            The following code generates a random Home/MyCar/MyStats Object, convert it into a JSONArray and put into Parse.com associated with the user.
                */
                Home home = new Home(-1, RegisterActivity.this);
                MyCar mycar = new MyCar(-1);
                MyStats mystat = new MyStats(-1);
                JSONObject home_json = new JSONObject();
                JSONObject mycar_json = new JSONObject();
                JSONObject mystats_json = new JSONObject();
                JSONObject allstats=new JSONObject();

                try {
                    home_json = home.getJSON(RegisterActivity.this);

                    mycar_json = mycar.getJSON(RegisterActivity.this);

                    mystats_json = mystat.getJSON(RegisterActivity.this);
                    //putting all data inside the user field as attribute
                    allstats.put(UsefulConstants.ParseClassNameHome,home_json);
                    allstats.put(UsefulConstants.ParseClassNameMyCar,mycar_json);
                    allstats.put(UsefulConstants.ParseClassNameMyStats,mystats_json);

                } catch (JSONException e) {
                    e.printStackTrace();
                }catch (NullPointerException e){
                    Log.e(TAG, e.getMessage());
                }


                //Storing the JSON containing all the stats into an object with an attribute of AllStats, then using cloud code to generate a Obj from the JSON

                // Looping through the JSON to put each JSON object into the ParseObject.
                Iterator<?> home_keys= home_json.keys();
                while(home_keys.hasNext()){
                    String key=(String)home_keys.next();
                    try {
                        homeParseObj.put(key, home_json.getString(key));
                        Log.d(TAG, "Putting JSON to home "+key+";"+home_json.getString(key));
                    }catch(JSONException e){
                        Log.d(TAG, "Got JSON error!!!!!!!!!!!!"+ e.getMessage());
                    }
                }
                homeParseObj.put(UsefulConstants.ParseAttrNameAllStats, home_json);

                Iterator<?> mycar_keys= mycar_json.keys();
                while(mycar_keys.hasNext()){
                    String key=(String)mycar_keys.next();
                    try {
                        myCarParseObj.put(key, mycar_json.getString(key));
                        Log.d(TAG, "Putting JSON to mycar "+key+";"+mycar_json.getString(key));
                    }catch(JSONException e){
                        Log.d(TAG, "Got JSON error!!!!!!!!!!!!"+ e.getMessage());
                    }
                }
                myCarParseObj.put(UsefulConstants.ParseAttrNameAllStats, mycar_json);


                Iterator<?> myStat_keys= mystats_json.keys();
                while(myStat_keys.hasNext()){
                    String key=(String)myStat_keys.next();
                    try {
                        myStatsParseObj.put(key, mystats_json.getString(key));
                        Log.d(TAG, "Putting JSON to mystat "+key+";"+mystats_json.getString(key));
                    }catch(JSONException e){
                        Log.d(TAG, "Got JSON error!!!!!!!!!!!!"+ e.getMessage());
                    }
                }
                myStatsParseObj.put(UsefulConstants.ParseAttrNameAllStats, mystats_json);


//                homeParseObj.put(UsefulConstants.ParseAttrNameAllStats, home_json);
//                myCarParseObj.put(UsefulConstants.ParseAttrNameAllStats, mycar_json);
//                myStatsParseObj.put(UsefulConstants.ParseAttrNameAllStats, mystats_json);
                //Creating Relationships between User and Stats


//                Delete on 01/17/2016, changing the way all Stats is stored inside Parse. Previously we stored all Stats inside the Key Value pair in the User class
//                Now we are creating a class for each page (Home/MyCar/MyStats），and store each Stats as a seperated keyvalue pair inside the corresponding class.
//                Each newly created object will contain a username and a email field to associate with the user.

                /*
                * * Parsing the Home/MyCar/MyStat objects into JSON objects and add them all to one JSONObject, then send to Parse.com
                * */
//                JSONObject allInOne=new JSONObject();
//                try {
//
//                    allInOne.put(getString(R.string.Home_JSON_KEY), home.getJSON(RegisterActivity.this));
//                    allInOne.put(getString(R.string.MyCar_JSON_KEY), mycar.getJSON(RegisterActivity.this));
//                    allInOne.put(getString(R.string.MyStat_JSON_KEY), mystat.getJSON(RegisterActivity.this));
//                }catch (JSONException e){
//                    e.printStackTrace();
//                }
//                user.put(getString(R.string.JSON_KEY),allInOne);
//                Gson gson=new Gson();
//                String homeData=gson.toJson(home);
//                String mycarData=gson.toJson(mycar);
//                String mystatData=gson.toJson(mystat);
//
//                user.put("HOME_DATA",homeData);
//                user.put("MYCAR_DATA",mycarData);
//                user.put("MYSTAT_DATA",mystatData);

                    user.put(UsefulConstants.ParseAttrNameAllStats, allstats);

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.w(TAG, "--------------Sign Up Success!");

                            //creating relationships
                            homeParseObj.put(UsefulConstants.ParseAttrNameParseUser, user);
                            myCarParseObj.put(UsefulConstants.ParseAttrNameParseUser, user);
                            myStatsParseObj.put(UsefulConstants.ParseAttrNameParseUser, user);
                            //Saving the ParseObjs
                            homeParseObj.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if(e!=null)
                                        e.printStackTrace();
                                    else
                                        myCarParseObj.saveInBackground(new SaveCallback() {
                                            @Override
                                            public void done(ParseException e) {
                                                if(e!=null)
                                                    e.printStackTrace();
                                                else
                                                    myStatsParseObj.saveInBackground(new SaveCallback() {
                                                        @Override
                                                        public void done(ParseException e) {
                                                            if(e!=null)
                                                                e.printStackTrace();
                                                            else
                                                                user.put(UsefulConstants.ParseClassNameHome,homeParseObj);
                                                                user.put(UsefulConstants.ParseClassNameMyCar,myCarParseObj);
                                                                user.put(UsefulConstants.ParseClassNameMyStats, myStatsParseObj);
                                                                user.saveInBackground();
                                                        }
                                                    });

                                            }
                                        });

                                }
                            });


                           //Displaying Success Message and switching to MainActivity.
                            Toast toast = Toast.makeText(RegisterActivity.this, "Sign Up Success!", Toast.LENGTH_LONG);
                            toast.show();
                            Intent mIntent = new Intent(RegisterActivity.this, MainActivity.class);
                            mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(mIntent);

                        } else {
                            Toast toast = Toast.makeText(RegisterActivity.this, "Sign Up failed", Toast.LENGTH_LONG);
                            if (e.getMessage().contains("already taken")) {
                                // The user name already exists
                                toast.setText("Username already exist!");
                            }
                            Log.w(TAG, "--------------Sign Up Failed!");
                            Log.w(TAG, e);
                            toast.show();
                        }
                    }
                });



            }
        });
        mLoginFormView = findViewById(R.id.register_form);
        mProgressView = findViewById(R.id.register_progress);
    }


    private void populateAutoComplete() {
        getLoaderManager().initLoader(0, null, this);
    }



    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
//    public void attemptLogin() {
//        if (mAuthTask != null) {
//            return;
//        }
//
//        // Reset errors.
//        mEmailView.setError(null);
//        mPasswordView.setError(null);
//
//        // Store values at the time of the login attempt.
//        String email = mEmailView.getText().toString();
//        String password = mPasswordView.getText().toString();
//
//        boolean cancel = false;
//        View focusView = null;
//
//        // Check for a valid password, if the user entered one.
//        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
//            mPasswordView.setError(getString(R.string.error_invalid_password));
//            focusView = mPasswordView;
//            cancel = true;
//        }
//
//        // Check for a valid email address.
//        if (TextUtils.isEmpty(email)) {
//            mEmailView.setError(getString(R.string.error_field_required));
//            focusView = mEmailView;
//            cancel = true;
//        } else if (!isEmailValid(email)) {
//            mEmailView.setError(getString(R.string.error_invalid_email));
//            focusView = mEmailView;
//            cancel = true;
//        }
//
//        if (cancel) {
//            // There was an error; don't attempt login and focus the first
//            // form field with an error.
//            focusView.requestFocus();
//        } else {
//            // Show a progress spinner, and kick off a background task to
//            // perform the user login attempt.
//            showProgress(true);
//            mAuthTask = new UserLoginTask(email, password);
//            mAuthTask.execute((Void) null);
//        }
//    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }


    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(RegisterActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
//    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
//
//        private final String mEmail;
//        private final String mPassword;
//
//        UserLoginTask(String email, String password) {
//            mEmail = email;
//            mPassword = password;
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
//            // TODO: attempt authentication against a network service.
//
//            try {
//                // Simulate network access.
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                return false;
//            }
//
//            for (String credential : DUMMY_CREDENTIALS) {
//                String[] pieces = credential.split(":");
//                if (pieces[0].equals(mEmail)) {
//                    // Account exists, return true if the password matches.
//                    return pieces[1].equals(mPassword);
//                }
//            }
//
//            // TODO: register the new account here.
//            return true;
//        }
//
//        @Override
//        protected void onPostExecute(final Boolean success) {
//            mAuthTask = null;
//            showProgress(false);
//
//            if (success) {
//                finish();
//            } else {
//                mPasswordView.setError(getString(R.string.error_incorrect_password));
//                mPasswordView.requestFocus();
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//            mAuthTask = null;
//            showProgress(false);
//        }
//    }
}

