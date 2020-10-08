package com.radiocodeford.mpew;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



import java.util.ArrayList;

public class ShopingCart extends AppCompatActivity {

    ListView simpleList;
    ArrayList<CartItemsModel> list = new ArrayList<>();
    TextView total,repeated,filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoping_cart);
        simpleList = (ListView) findViewById(R.id.list_cart);
        total = (TextView) findViewById(R.id.total_contact_value);
        repeated = (TextView) findViewById(R.id.repeated_contact_value);
        filter = (TextView) findViewById(R.id.filter_contact_value);

        total.setText(" " +String.valueOf(MainActivity.increment));
        repeated.setText(" " +String.valueOf(MainActivity.repeated_contact));
        filter.setText(" " +String.valueOf(MainActivity.totalcontact.size()));
   /*     tv_buyer_tray_details = (TextView) findViewById(R.id.tv_customer_order_details);
        tv_buyer_tray_details.setText("Customer : "+ShopTray.customer_id);*/

        for (int i = 0; i < MainActivity.totalcontact.size(); i++) {

            try {



                list.add(new CartItemsModel(MainActivity.totalcontact.get(i).phone, MainActivity.totalcontact.get(i).name, "200","john","1000$",R.drawable.circle));
            } catch (Exception e) {

            }

        }





        CartItemAdapter customAdapter = new CartItemAdapter(getApplicationContext(), list);
        simpleList.setAdapter(customAdapter);
    }
}
