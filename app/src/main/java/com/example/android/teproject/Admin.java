package com.example.android.teproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {

private FirebaseDatabase database;
private FirebaseAuth mAuth;
private FirebaseUser mUser;
private DatabaseReference ref;
private String userid;
private TextView tv;
private ListView lv;
private Users ui;
private ArrayList<String> list;
private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
//        Intent i = getIntent();
//        userid = i.getStringExtra("user");
        lv = findViewById(R.id.listView);
        ui = new Users();
        database = FirebaseDatabase.getInstance();
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.user_info, R.id.userInfo, list);
        ref = database.getReference("users");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Toast.makeText(Admin.this, "" + ui, Toast.LENGTH_SHORT).show();
                    ui = ds.getValue(Users.class);
                    list.add(ui.getCustomer_name() + "\n"+ ui.getCompany() + "\n" + ui.getAddress() + "\n" + ui.getService() + "\n" + ui.getDate() + "\n" + ui.getMobile_no() + "\n" + ui.getUser_type());
//                    ui.setAddress(ds.child(userid).getValue(Users.class).getAddress());
//                    ui.setCompany(ds.child(userid).getValue(Users.class).getCompany());
//                    ui.setCustomer_name(ds.child(userid).getValue(Users.class).getCustomer_name());
//                    ui.setDate(ds.child(userid).getValue(Users.class).getDate());
//                    ui.setMobile_no(ds.child(userid).getValue(Users.class).getMobile_no());
//                    ui.setUser_type(ds.child(userid).getValue(Users.class).getUser_type());
//                    String address = dataSnapshot.child(userid).child("address").getValue(String.class);
//                    String company = dataSnapshot.child(userid).child("company").getValue(String.class);
//                    String custName = dataSnapshot.child(userid).child("customer_name").getValue(String.class);
//                    String date = dataSnapshot.child(userid).child("date").getValue(String.class);
//                    String mobNo = dataSnapshot.child(userid).child("mobile_no").getValue(String.class);
//                    String uType = dataSnapshot.child(userid).child("user_type").getValue(String.class);
//                m1.setAddress(address);
//                m1.setCompany(company);
//                m1.setCustomer_name(custName);
//                m1.setDate(date);
//                m1.setMobile_no(mobNo);
//                m1.setUser_type(uType);
//                    Toast.makeText(Admin.this, "Address: \" + address + \"\\n\" + \"Company: \" + company + \"\\n\" + \"Customer Name: \" + custName + \"\\n\" + \"Date: \" + date + \"\\n\" + \"Mobile: \" + mobNo + \"\\n\" + \"User Type: \" + uType", Toast.LENGTH_SHORT).show();
//                    tv.setText("Address: " + address + "\n" + "Company: " + company + "\n" + "Customer Name: " + custName + "\n" + "Date: " + date + "\n" + "Mobile: " + mobNo + "\n" + "User Type: " + uType);
                }
                lv.setAdapter(adapter);

            }


        public void onCancelled(DatabaseError error) {
            // Failed to read value
            Log.w("tmz", "Failed to read value.", error.toException());
        }


    });
    }
}