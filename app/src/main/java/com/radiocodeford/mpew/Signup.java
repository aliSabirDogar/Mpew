package com.radiocodeford.mpew;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private static final int PERMISSIONS_REQUEST_READ_PHONE_LOG = 110;
    private static final int PERMISSIONS_REQUEST_READ_SMS = 130;
    private static final int REQUEST_READ_PHONE_STATE = 120;
    public static String code_pays;
    public static int counter = 0;
    public static String deviceId;
    public static EditText email;
    public static EditText firstname;
    public static EditText name;
    public static String nameContact;
    public static TextView nationalite,tvcounter,uploaded;
    public static EditText password;
    public static String phoneContact;
    public static EditText phonenumber;
    public static RequestQueue queue;
    private final String TAG = "Signup :";
    private Boolean authFlag = Boolean.valueOf(false);
    ProgressDialog dialog;
    Handler handler = new Handler();
    int increment = 0;
    Map<String, String> namePhoneMap = new HashMap();
    ProgressDialog progress;
    private Button show;
    private Button signup;
    public static Context con;

    private class ThreadB extends AsyncTask<Void, Void, Void> {
        private Context mContext;

        public ThreadB(Context ctx) {
            this.mContext = ctx;
        }

        /* Access modifiers changed, original: protected|varargs */
        public Void doInBackground(Void... params) {
            Signup.queue.add(new StringRequest(1, "https://ukeepin.com/restapi/index.php/mpew_signup", new Listener<String>() {
                public void onResponse(String ServerResponse) {
                    String result = ServerResponse;
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    Signup.this.dialog.dismiss();
                    Toast.makeText(Signup.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                }
            }) {
                /* Access modifiers changed, original: protected */
                public Map<String, String> getParams() {
                    Map<String, String> params = new HashMap();
                    params.put("code_pays", Signup.nationalite.getText().toString());
                    String str = BuildConfig.FLAVOR;
                    params.put("contacts", str);
                    params.put("email", Signup.email.getText().toString());
                    params.put("firstname", Signup.firstname.getText().toString());
                    params.put("iemi", Signup.deviceId);
                    params.put("name", Signup.nameContact);
                    params.put("phone", Signup.phoneContact);
                    params.put("phoneLogs", str);
                    params.put("PlayerId", str);
                    params.put("pushId", str);
                    params.put("urlImage", str);
                    return params;
                }
            });
            return null;
        }
    }

    static {
        String str = " ";
        nameContact = str;
        phoneContact = str;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con=Signup.this;
        email = (EditText) findViewById(R.id.signup_email);
        password = (EditText) findViewById(R.id.signup_password);
        name = (EditText) findViewById(R.id.signup_name);
        firstname = (EditText) findViewById(R.id.signup_first_name);
        phonenumber = (EditText) findViewById(R.id.signup_phone);
        this.signup = (Button) findViewById(R.id.signup_signup);
        this.show = (Button) findViewById(R.id.signup_show);
        nationalite = (TextView) findViewById(R.id.textView6);
        uploaded = (TextView) findViewById(R.id.counter_uploaded);
        tvcounter = (TextView) findViewById(R.id.counter);
        int name_size=MainActivity.nameList.size();
        int name_size2=MainActivity.totalcontact.size();
        tvcounter.setText(String.valueOf("Total contacts  "+" : "+MainActivity.totalcontact.size()));
        nationalite.setText(MainActivity.code_pays);
        queue = Volley.newRequestQueue(this);
        this.dialog = new ProgressDialog(this);
        this.dialog.setProgressStyle(0);
        this.dialog.setTitle("Loading");
        this.dialog.setMessage("Loading. Please wait...");
        this.dialog.setIndeterminate(true);
        this.dialog.setCanceledOnTouchOutside(false);
        this.show.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (Signup.password.getTransformationMethod() == null) {
                    Signup.password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    Signup.password.setTransformationMethod(null);
                }
            }
        });
        this.signup.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (Signup.this.isFormValid()) {
                    Signup.this.authFlag = Boolean.valueOf(false);
                    Signup.this.PostRequestProduct();
                }
            }
        });



        BroadcastReceiver mysms=new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                String sms=arg1.getExtras().getString("m");
          if(sms.matches("completed"))
          {
              Toast.makeText(Signup.this, "All contacts uploaded ", Toast.LENGTH_LONG).show();
              Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ukeepin.com/"));
              startActivity(browserIntent);
          }

            }
        };
        registerReceiver(mysms, new IntentFilter("mpew"));
    }

    private void PostRequestProduct() {
        StringRequest stringRequest = new StringRequest(1, "https://ukeepin.com/restapi/index.php/user_signup", new Listener<String>() {
            public void onResponse(String ServerResponse) {

                Signup.this.dialog.show();
                Signup.counter = 0;
                Signup.this.startService(new Intent(Signup.this, ScheduledService.class));
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Signup.this.dialog.dismiss();
                    }
                }, 10000);


            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Signup.this.dialog.dismiss();
                Toast.makeText(Signup.this, volleyError.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            /* Access modifiers changed, original: protected */
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap();

                params.put("phone_no", phonenumber.getText().toString());
                params.put("email", Signup.email.getText().toString());
                params.put("name", Signup.firstname.getText().toString());
                params.put("password", password.getText().toString());

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000, 1, 1.0f));
        queue.add(stringRequest);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 100 && grantResults[0] != 0) {
            Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isFormValid() {
        if (name.getText().toString().length() < 2) {
            Toast.makeText(this, "Please fill up name", Toast.LENGTH_LONG).show();
            return false;
        } else if (firstname.getText().toString().length() < 2) {
            Toast.makeText(this, "Please fill up firstname", Toast.LENGTH_LONG).show();
            return false;
        } else if (password.getText().toString().length() < 6) {
            Toast.makeText(this, "Please fill up pasword with at least six characters", Toast.LENGTH_LONG).show();
            return false;
        } else if (email.getText().toString().length() < 4 || !email.getText().toString().contains("@") || !email.getText().toString().contains(".")) {
            Toast.makeText(this, "Please fill up an existing email", Toast.LENGTH_LONG).show();
            return false;
        } else if (phonenumber.getText().toString().length() >= 10) {
            return true;
        } else {
            Toast.makeText(this, "Please fill up  phone number", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void getPhoneLogAuth() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_CALL_LOG") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_CALL_LOG"}, 110);
        }
    }
}
