package com.radiocodeford.mpew;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.android.volley.RequestQueue;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private static final int PERMISSIONS_REQUEST_READ_PHONE_LOG = 110;
    private static final int PERMISSIONS_REQUEST_READ_SMS = 130;
    private static final int REQUEST_READ_PHONE_STATE = 120;
    public static String code_pays;
    public static int counter = 0;
    public static String deviceId;
   public static ArrayList<String> nameList = new ArrayList();
    public static ArrayList<String> phoneList = new ArrayList();
    public static ArrayList<String> nameListRAW = new ArrayList();
    public static ArrayList<String> phoneListRAW = new ArrayList();
    public static RequestQueue queue;
    private final String TAG = "Signup :";
    private Boolean authFlag = Boolean.valueOf(false);

    Handler handler = new Handler();
    public static int increment = 0,uploaded_contacts=0,repeated_contact=0;
    Map<String, String> namePhoneMap = new HashMap();
    public static ArrayList<Contactlist> conatcts = new ArrayList();
    String ClsSimPhonename = null;
    String ClsSimphoneNo = null;

    public static ArrayList<String> phonecontact = new ArrayList<String>();
    public static ArrayList<String> simcontact = new ArrayList<String>();
    public static ArrayList<Contactlist> totalcontact = new ArrayList<Contactlist>();
    public static ArrayList<Contactlist> repeatedcontact = new ArrayList<Contactlist>();

    ProgressDialog progress;




    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  progress = new ProgressDialog(MainActivity.this);

     //   progress.setMessage("Doing something, please wait.");
       // progress.show();
        uploaded_contacts=0;
        getPhoneNumberAuth();
        TelephonyManager tm = (TelephonyManager) getApplicationContext().getSystemService(TELEPHONY_SERVICE);
        deviceId = Secure.getString(getContentResolver(), "android_id");
        if (VERSION.SDK_INT >= 23) {

         /*   if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        PERMISSIONS_REQUEST_READ_CONTACTS);
            } else {
                new Execute(MainActivity.this).execute(1);*/


                if (checkSelfPermission("android.permission.READ_CONTACTS") != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{"android.permission.READ_CONTACTS"}, 100);
                    return;
                }
//new FetchContacts().execute();

           /* if(uploaded_contacts==0) {
               totalcontact= getContacts(MainActivity.this);
                increment=totalcontact.size();
             //   getsimcard_contact();

                System.out.println("phone??? " + phonecontact);
                System.out.println("sim??? " + simcontact);

                System.out.println("sim_size??? " + simcontact.size());
                System.out.println("phone_size??? " + phonecontact.size());
                System.out.println("totalcontact_size??? " + totalcontact.size());

                // filter process beigins here....
                nowFilterContact();

            }*/
            List<String> name = new ArrayList<String>();
           // String[] months={"Jan","Feb","Mar","Apr","Jan","Mar","May","May"};
        /*    for(int i=0;i<nameList.size();i++){
                for(int j=1;j<nameList.size();j++){
                    if(nameList.get(i).equalsIgnoreCase(nameList.get(j))){
                        if(!name.contains(nameList.get(i))){
                            name.add(nameList.get(i));
                        }
                    }
                }
            }*/

/*            List<String> phone = new ArrayList<String>();
          //  String[] months={"Jan","Feb","Mar","Apr","Jan","Mar","May","May"};
            for(int i=0;i<phoneListRAW.size();i++){
                for(int j=1;j<phoneListRAW.size();j++){
                    if(phoneListRAW.get(i).equalsIgnoreCase(phoneListRAW.get(j))){
                        if(!phoneList.contains(phoneListRAW.get(i))){
                            phoneList.add(phoneListRAW.get(i));
                            nameList.add(nameListRAW.get(i));
                            increment++;
                        }
                    }
                }
            }
            Toast.makeText(this, "name" +phoneList.size(), Toast.LENGTH_LONG).show();
            Toast.makeText(this, "phone" +nameList.size(), Toast.LENGTH_LONG).show();*/


            //new Execute2(this).execute(new Integer[]{Integer.valueOf(1)});

           /* }
            if (checkSelfPermission("android.permission.READ_CONTACTS") !=  PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        100);
                return;
            }
        }
        new Execute(this).execute(1});*/

        }

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode != 100) {
            return;
        }
        if (grantResults[0] == 0) {
            getPhoneNumberAuth();


            totalcontact= getContacts(MainActivity.this);
            increment=totalcontact.size();


              // getsimcard_contact();

                System.out.println("phone??? " + phonecontact);
                System.out.println("sim??? " + simcontact);

                System.out.println("sim_size??? " + simcontact.size());
                System.out.println("phone_size??? " + phonecontact.size());
                System.out.println("totalcontact_size??? " + totalcontact.size());

                // filter process beigins here....
                nowFilterContact();

            

           // new Execute2(this).execute(new Integer[]{Integer.valueOf(1)});
           /// new FetchContacts().execute();
           // new Execute(this).execute(new Integer[]{Integer.valueOf(1)});
            return;
        }
        Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_LONG).show();
    }

/*    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getPhoneNumberAuth();
                    new Execute(MainActivity.this).execute(1);
                   // getPhoneLogAuth();
                } else {
                    Log.e("Acces Refused", "permission for reading contact not allowed");
                }
                break;
            }
            case PERMISSIONS_REQUEST_READ_PHONE_LOG:
            {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                 //   GetContacts.getContact(SignupActivity.this);
                } else {
                    Log.e("Acces Refused", "permission for reading Phone Log not allowed");
                }

                break;
            }
            case PERMISSIONS_REQUEST_READ_SMS:
            {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getPhoneInformations();
                } else {
                    Log.e("Acces Refused", "permission for reading SMS not allowed");
                }

            }
            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getSmsReadAuth();
                }
                break;

            default:
                break;
        }
    }*/
/*
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode != 100) {
            return;
        }
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            getPhoneNumberAuth();
            new Execute(this).execute(1,null,null);
            return;
        }
      //  Toast.makeText(this, "Until you grant the permission, we canot display the names", 0).show();
    }
*/

    public void getSmsReadAuth() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_SMS") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_SMS"}, PERMISSIONS_REQUEST_READ_SMS);
            return;
        }
        getPhoneInformations();
    }

    private void getPhoneInformations() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("+");
        stringBuilder.append(GetCountryZipCode());
        code_pays = stringBuilder.toString();
    }

    public String GetCountryZipCode(){
        String CountryID="";
        String CountryZipCode="";

        TelephonyManager manager = (TelephonyManager) this.getSystemService(MainActivity.TELEPHONY_SERVICE);
        //getNetworkCountryIso
        CountryID= manager.getSimCountryIso().toUpperCase();
        String[] rl=this.getResources().getStringArray(R.array.CountryCodes);
        for(int i=0;i<rl.length;i++){
            String[] g=rl[i].split(",");
            if(g[1].trim().equals(CountryID.trim())){
                CountryZipCode=g[0];
                break;
            }
        }
        return CountryZipCode;
    }


    public void getPhoneNumberAuth() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_PHONE_STATE") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_PHONE_STATE"}, 120);
            return;
        }
        getSmsReadAuth();
    }


    private void nowFilterContact() {
        // TODO Auto-generated method stub

        // determine which contact have more item....

        if (simcontact.size() > phonecontact.size()) {

            onemorefiltermethod(totalcontact.size(), totalcontact, totalcontact);

        } else {
            onemorefiltermethod(totalcontact.size(), totalcontact, totalcontact);
        }

    }

    private void onemorefiltermethod(int size, ArrayList<Contactlist> contacts,
                                     ArrayList<Contactlist> contact2) {
        // TODO Auto-generated method stub



        // compare both contact and get repeated contacts....
        for (int i = 0; i < contacts.size(); i++) {

            for(int j=i+1;j<contact2.size();j++)
            {
                String Str_master=contacts.get(i).phone;
                String sr[] = Str_master.split(Pattern.quote("+"));

                Str_master=sr[0];
                String Str_child=contact2.get(j).phone;
                String sr2[] = Str_child.split(Pattern.quote("+"));
                Str_child=sr2[0];

                if(Str_master.matches((Str_child)))
                {
                    repeated_contact++;
                    repeatedcontact.add(contacts.get(i));

                    totalcontact.remove(i);

                }



            }

         /*   try {
                if (contacts.contains(contact2.get(i))) {

                    // add repeated contacts to array....
                    repeatedcontact.add(contact2.get(i));
                }
            } catch (Exception e) {

            }*/

        }
 /*       ArrayList<Contactlist> master=totalcontact;
        ArrayList<Contactlist> child=totalcontact;
        for (int i = 0; i < master.size(); i++) {

            for(int j=i+1;j<child.size();j++)
            {

                String Str_master=master.get(i).name;
                String sr[] = Str_master.split(Pattern.quote("+"));

                Str_master=sr[0];
                String Str_child=child.get(j).name;
                String sr2[] = Str_child.split(Pattern.quote("+"));
                Str_child=sr2[0];

                if(Str_master.matches((Str_child)))
                {
                    repeatedcontact.add(master.get(i));

                    totalcontact.remove(i);

                }



            }



        }

        ArrayList<Contactlist> master2=totalcontact;
        ArrayList<Contactlist> child2=totalcontact;
        for (int i = 0; i < master2.size(); i++) {

            for(int j=i+1;j<child2.size();j++)
            {
                String Str_master=master2.get(i).name;
                String sr[] = Str_master.split(Pattern.quote("+"));

                Str_master=sr[0];
                String Str_child=child2.get(j).name;
                String sr2[] = Str_child.split(Pattern.quote("+"));
                Str_child=sr2[0];

                if(Str_master.matches((Str_child)))
                {
                    repeatedcontact.add(master2.get(i));

                    totalcontact.remove(i);

                }



            }



        }

        ArrayList<Contactlist> master3=totalcontact;
        ArrayList<Contactlist> child3=totalcontact;
        for (int i = 0; i < master3.size(); i++) {

            for(int j=i+1;j<child3.size();j++)
            {

                String Str_master=master3.get(i).name;
                String sr[] = Str_master.split(Pattern.quote("+"));

                Str_master=sr[0];
                String Str_child=child3.get(j).name;
                String sr2[] = Str_child.split(Pattern.quote("+"));
                Str_child=sr2[0];

                if(Str_master.matches((Str_child)))
                {
                    repeatedcontact.add(master3.get(i));

                    totalcontact.remove(i);

                }



            }



        }*/


        System.out.println("repeatedcontact_size??? " + repeatedcontact.size());
        // now delete repeated contact from total contact
        now_deletedrepeated_contact_from_total();
    }

    private void now_deletedrepeated_contact_from_total() {
        // TODO Auto-generated method stub
       // progress.dismiss();
    /*    for (int i = 0; i < totalcontact.size(); i++) {

            try {
                if (totalcontact.get(i).phone.contains(repeatedcontact.get(i).phone)) {
                   totalcontact.remove(repeatedcontact.get(i));

                   // nameList.remove(i);

                }
            } catch (Exception e) {

            }

        }*/

        uploaded_contacts=1;


        MainActivity.this.startActivity(new Intent(MainActivity.this, ShopingCart.class));

        /*for (int i = 0; i < totalcontact.size(); i++) {

            try {
                if (totalcontact.contains(repeatedcontact.get(i))) {
                    totalcontact.remove(repeatedcontact.get(i));


                }
            } catch (Exception e) {

            }

        }*/


      //  phoneList.add(phoneListRAW.get(i));
        //nameList.add(nameListRAW.get(i));
        //increment++;
        System.out.println("Final contact size" + totalcontact.size());

        System.out.println("Final contact " + totalcontact);

    }

    private void getsimcard_contact() {
        // TODO Auto-generated method stub
        try {
            Uri simUri = Uri.parse("content://icc/adn");
            Cursor cursorSim = this.getContentResolver().query(simUri, null,
                    null, null, null);

            while (cursorSim.moveToNext()) {
                ClsSimPhonename = cursorSim.getString(cursorSim
                        .getColumnIndex("name"));
                ClsSimphoneNo = cursorSim.getString(cursorSim
                        .getColumnIndex("number"));
                ClsSimphoneNo.replaceAll("\\D", "");
                ClsSimphoneNo.replaceAll("&", "");
                ClsSimPhonename = ClsSimPhonename.replace("|", "");

                /*
                 * add contact from phone to array phone array and total array
                 */

               // phoneList.add(phoneListRAW.get(i));
                nameList.add(ClsSimPhonename);

                phonecontact.add(ClsSimphoneNo);
                totalcontact.add(new Contactlist(ClsSimphoneNo," ",ClsSimPhonename));
                increment++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Contactlist> getContacts(Context ctx) {
        ArrayList<Contactlist> list = new ArrayList<>();
        ContentResolver contentResolver = ctx.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor cursorInfo = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(ctx.getContentResolver(),


                            ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(id)));

                    Uri person = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(id));
                    Uri pURI = Uri.withAppendedPath(person, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);

                    Bitmap photo = null;
                    if (inputStream != null) {
                        photo = BitmapFactory.decodeStream(inputStream);
                    }
                    while (cursorInfo.moveToNext()) {
                       /* ContactModel info = new ContactModel();
                        info.id = id;
                        info.name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        info.mobileNumber = cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        info.photo = photo;
                        info.photoURI= pURI;*/
                       // increment++;
                        list.add(new Contactlist(cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),"",cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))));
                    }

                    cursorInfo.close();
                }

                //increment++;
            }
            cursor.close();
        }
        return list;
    }

    private void getphonecontact() {
        // TODO Auto-generated method stub
        try {
            String[] PROJECTION = new String[] { Contacts._ID,
                    Contacts.DISPLAY_NAME, Phone.NUMBER };

            Cursor c = managedQuery(Phone.CONTENT_URI, PROJECTION, null, null,
                    null);
            if (c.moveToFirst()) {
                String ClsPhonename = null;
                String ClsphoneNo = null;

                do {
                    ClsPhonename = c.getString(c
                            .getColumnIndex(Contacts.DISPLAY_NAME));
                    ClsphoneNo = c.getString(c.getColumnIndex(Phone.NUMBER));
                    /*
                     * add contact from sim to array sim array and total array
                     */
                    simcontact.add(ClsphoneNo);
                    nameList.add(ClsPhonename);
                    totalcontact.add(new Contactlist(ClsphoneNo," ",ClsPhonename));
                    increment++;

                    ClsphoneNo.replaceAll("\\D", "");
                    ClsPhonename = ClsPhonename.replaceAll("&", "");
                    ClsPhonename.replace("|", "");
                    String ClsPhoneName = ClsPhonename.replace("|", "");

                } while (c.moveToNext());
            }
        } catch (Exception e) {

        }
    }
}
