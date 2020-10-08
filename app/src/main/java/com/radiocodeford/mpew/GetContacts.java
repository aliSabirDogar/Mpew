package com.radiocodeford.mpew;


import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.HashMap;




/**
 * Created by thiba on 29/01/2017.
 */

public class GetContacts {

    public static ProgressDialog progress;
    public static HashMap<String, Contact> contacts;
    public Context context;
    public static ArrayList<String> contactlist;
    public String address;

    /**
     * Getting all user's contacts
     **/

    public static void getContact(Context context) {
        contactlist = new ArrayList<String>();

        new Execute(context).execute(1);
    }

    public static void refreshContact(Context context) {
        new Execute(context).execute(2);

    }


   /* static class Execute extends AsyncTask<Integer, Void, Void> {
        Context context;

        public Execute(Context context)
        {
            this.context =context;
        }


        @Override
        protected Void doInBackground(Integer... params) {
            GetContacts.contacts = new HashMap<>();
            String name = "", phoneNo = "", firstName = "", fixe = "";

            ContentResolver cr = context.getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null,  ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

            if (cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    phoneNo = "";
                    fixe = "";
                    String image = "";
                    String imageThumnail = "";
                    String email  = "";
                    String id = cur.getString(
                            cur.getColumnIndex(ContactsContract.Contacts._ID));
                    name = cur.getString(cur.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE));

                    firstName = cur.getString(cur.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));


                    image = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));
                    imageThumnail = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));

                    //email = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DISPLAY_NAME));

                    Cursor emailCursor = cr.query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (emailCursor.moveToNext()) {
                        email = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                    }

                    emailCursor.close();
                    if (cur.getInt(cur.getColumnIndex(
                            ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {

                        Cursor pCur = cr.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{id}, null);
                        while (pCur.moveToNext()) {
                            String number = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            int type = pCur.getInt(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                            switch (type) {
                                case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                    fixe = number;
                                    if (fixe.length() == 10) {
                                        fixe = CurrentUser.getInstance().getCode_pays() + fixe.substring(1);
                                        ;
                                    }
                                    break;
                                case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                    phoneNo = number;
                                    if (phoneNo.length() == 10)
                                        phoneNo = CurrentUser.getInstance().getCode_pays() + phoneNo.substring(1);
                                    ;
                                    break;

                                default:
                                    if (phoneNo == null || phoneNo.length() == 0) {
                                        phoneNo = number;

                                        if (phoneNo.length() == 10)
                                            phoneNo = CurrentUser.getInstance().getCode_pays() + phoneNo.substring(1);
                                        ;
                                    }
                                    break;
                            }
                        }
                        pCur.close();
                    }
                    if (!(phoneNo.compareTo("") == 0 || phoneNo.contains(".") || phoneNo.contains("/") || phoneNo.contains("#") || phoneNo.contains("$") || phoneNo.contains("[") || phoneNo.contains("]"))) {
                        phoneNo = phoneNo.replace(" ", "");

                        String CompareCode="+"+LoginActivity.DialCode;
                        if(phoneNo.contains(CompareCode)) {

                        }

                 *//*       else {
                            int flag_match = 0;
                            for (int i = 0; i < CountryCodes.m_Codes.length; i++) {
                                if (phoneNo.matches("+" + CountryCodes.m_Codes[i])) {
                                    flag_match = 1;

                                }
                            }

                            if (flag_match == 0) {
                                if (phoneNo.contains("+")) {
                                    phoneNo.replace("+", "");
                                    phoneNo = "+" + LoginActivity.DialCode + phoneNo;

                                } else if (phoneNo.contains("null")) {
                                    phoneNo.replace("+", "");
                                    phoneNo = "+" + LoginActivity.DialCode + phoneNo;

                                } else {
                                    phoneNo = "+" + LoginActivity.DialCode + phoneNo;
                                }

                            }

                        }*//*

                        String addrWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
                        String[] addrWhereParams = new String[]{id,
                                ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE};
                        Cursor addrCur = cr.query(ContactsContract.Data.CONTENT_URI,
                                null, null, null, null);

                        phoneNo = phoneNumber.checkPhoneNumber(phoneNo, CurrentUser.getInstance().getCode_pays());
                        fixe = phoneNumber.checkPhoneNumber(fixe, CurrentUser.getInstance().getCode_pays());
                        if (!CurrentUser.getInstance().getContacts().containsKey(phoneNo)) {
                            Contactlist contact = new Contactlist(name, firstName, phoneNo, phoneNo);
                            contact.setFixe(fixe);
                            contact.setAndroidContactId(id);
                            contact.setMail(email);

                            int match_name=0;
                            if(contactlist.size()>0) {
                                for (int i = 0; i < contactlist.size(); i++) {
                                    if (contactlist.get(i).contains(name)) {
                                        match_name = 1;

                                    }
                                }
                            }
                            if(match_name==0) {
                                GetContacts.contacts.put(phoneNo, contact);
                            }
                            if (params[0] == 2)

                                if(match_name==0) {
                                    UserService.sendOnlyOneContact(context, contact);
                                }

                            contactlist.add(name);
                        }
                    }
                }
                CurrentUser.getInstance().getContacts().putAll(GetContacts.contacts);
                if (params[0] == 1)
                    UserService.sendContact(context, GetContacts.contacts);

                cur.close();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (GetContacts.progress!= null && GetContacts.progress.isShowing() == true)
                GetContacts.progress.dismiss();
        }

    }
}*/


    static class Execute extends AsyncTask<Integer, Void, Void> {
        Context context;

        public Execute(Context context) {
            this.context = context;
        }


        @Override
        protected Void doInBackground(Integer... params) {
            GetContacts.contacts = new HashMap<>();
            String name = "", phoneNo = "", firstName = "", fixe = "";

            ContentResolver cr = context.getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

            if (cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    phoneNo = "";
                    fixe = "";
                    String image = "";
                    String imageThumnail = "";
                    String email = "";
                    String id = cur.getString(
                            cur.getColumnIndex(ContactsContract.Contacts._ID));
                    name = cur.getString(cur.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE));

                    firstName = cur.getString(cur.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));


                    image = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));
                    imageThumnail = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));

                    //email = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DISPLAY_NAME));

                    Cursor emailCursor = cr.query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (emailCursor.moveToNext()) {
                        email = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                    }

                    emailCursor.close();
                    if (cur.getInt(cur.getColumnIndex(
                            ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {

                        Cursor pCur = cr.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{id}, null);
                        while (pCur.moveToNext()) {
                            String number = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            int type = pCur.getInt(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                            switch (type) {
                                case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                    fixe = number;
                                    if (fixe.length() == 10) {
                                        fixe = CurrentUser.getInstance().getCode_pays() + fixe.substring(1);
                                        ;
                                    }
                                    break;
                                case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                    phoneNo = number;
                                    if (phoneNo.length() == 10)
                                        phoneNo = CurrentUser.getInstance().getCode_pays() + phoneNo.substring(1);
                                    ;
                                    break;

                                default:
                                    if (phoneNo == null || phoneNo.length() == 0) {
                                        phoneNo = number;

                                        if (phoneNo.length() == 10)
                                            phoneNo = CurrentUser.getInstance().getCode_pays() + phoneNo.substring(1);
                                        ;
                                    }
                                    break;
                            }
                        }
                        pCur.close();
                    }
                    if (!(phoneNo.compareTo("") == 0 || phoneNo.contains(".") || phoneNo.contains("/") || phoneNo.contains("#") || phoneNo.contains("$") || phoneNo.contains("[") || phoneNo.contains("]"))) {
                        phoneNo = phoneNo.replace(" ", "");
                        fixe = fixe.replace(" ", "");
                        phoneNo = phoneNumber.checkPhoneNumber(phoneNo, CurrentUser.getInstance().getCode_pays());
                        fixe = phoneNumber.checkPhoneNumber(fixe, CurrentUser.getInstance().getCode_pays());

                        if (!CurrentUser.getInstance().getContacts().containsKey(phoneNo)) {
                            Contact contactlist = new Contact(name, firstName, phoneNo, phoneNo,email);
                            contactlist.setFixe(fixe);
                            contactlist.setAndroidContactId(id);
                            contactlist.setMail(email);
                            GetContacts.contacts.put(phoneNo, contactlist);

                                //UserService.sendOnlyOneContact(context, contactlist);
                        }
                    }
                }
                CurrentUser.getInstance().getContacts().putAll(GetContacts.contacts);
                if (params[0] == 1)
                   // UserService.sendContact(context, GetContacts.contacts);

                cur.close();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (GetContacts.progress != null && GetContacts.progress.isShowing() == true)
                GetContacts.progress.dismiss();
        }

    }
}