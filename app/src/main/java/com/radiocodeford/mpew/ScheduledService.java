package com.radiocodeford.mpew;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduledService extends Service {
    int counter = 0;
    private Timer timer = new Timer();

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.counter = 0;
        this.timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                ScheduledService.this.PostRequestProduct();
                ScheduledService scheduledService = ScheduledService.this;
                scheduledService.counter++;

             /*   new Thread ( new Runnable() {
                    @Override
                    public void run() {


                        // This code will run in another thread. Usually as soon as start() gets called!
                    }
                }).start();*/
                Handler refresh = new Handler(Looper.getMainLooper());
                refresh.post(new Runnable() {
                    public void run()
                    {
                        Signup.uploaded.setText("Contacts Uplaoded  "+" :  "+String.valueOf(counter));
                    }
                });

        /*        Runnable runnable = new Runnable(){
                    public void run() {
                        //some code here
                        Signup.uploaded.setText("Contacts Uplaoded  "+" :  "+String.valueOf(counter));
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();*/
                //runnable.run();
                if (ScheduledService.this.counter >= MainActivity.totalcontact.size()) {
                    ScheduledService.this.timer.cancel();
                    Intent intent = new Intent("mpew");
                    intent.putExtra("m","completed");
                    sendBroadcast(intent);
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ukeepin.com/"));
                    Signup.con.startActivity(browserIntent);
                   // Signup.con.startActivity(new Intent( Signup.con, WebviewLink.class));
                }
            }
        }, 0, 1000);
    }

    public void onDestroy() {
        super.onDestroy();
       // getApplicationContext().startActivity(new Intent(getApplicationContext(), WebviewLink.class));

    }

    private void PostRequestProduct() {
        StringRequest stringRequest = new StringRequest(1, "https://ukeepin.com/restapi/index.php/mpew_signup", new Listener<String>() {
            public void onResponse(String ServerResponse) {
                String result = ServerResponse;
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
            }
        }) {
            /* Access modifiers changed, original: protected */
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("code_pays", MainActivity.code_pays);
                String str = BuildConfig.FLAVOR;
                params.put("contacts", str);
                params.put("email", Signup.email.getText().toString());
                params.put("firstname", Signup.firstname.getText().toString());
                params.put("iemi", MainActivity.deviceId);
                params.put("name", MainActivity.totalcontact.get(ScheduledService.this.counter).getName());
                params.put("phone", MainActivity.totalcontact.get(ScheduledService.this.counter).getPhone());
                params.put("phoneLogs", str);
                params.put("PlayerId", str);
                params.put("pushId", str);
                params.put("urlImage", str);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000, 1, 1.0f));
        Signup.queue.add(stringRequest);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
}
