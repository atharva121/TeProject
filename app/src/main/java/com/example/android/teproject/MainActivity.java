package com.example.android.teproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText companyName;
    private EditText custName,mobileNo;
    private RadioGroup empType;
    private EditText address;
    private Spinner spin;
    private CalendarView v;
    private Button b;
    private RadioButton employee,manager;
    private String datedate;
    private FirebaseAuth mAuth;
    private DatabaseReference firebaseRef,userRef;
    private FirebaseUser mUser;
    private String compName,cutName,mobno,userid,add,type,selectService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager=findViewById(R.id.rdManager);
        mAuth = FirebaseAuth.getInstance();
        firebaseRef = FirebaseDatabase.getInstance().getReference();
        companyName=findViewById(R.id.etCompanyName);
        mobileNo=findViewById(R.id.etMobileNo);
        address=findViewById(R.id.etAddress);
        custName= findViewById(R.id.etCustomerName);
        v=findViewById(R.id.cv);

        v.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( @NonNull CalendarView view, int year, int month, int date) {

                datedate = date+"/"+(month+1)+"/"+year;
                Toast.makeText(MainActivity.this, ""+date+"/"+(month+1)+"/"+year, Toast.LENGTH_SHORT).show();
            }
        });

        b=findViewById(R.id.button2);
    b.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mUser = mAuth.getCurrentUser();
            userid = mUser.getUid();
            employee=findViewById(R.id.rdEmployee);


            compName = companyName.getText().toString();
            mobno = mobileNo.getText().toString();
            add = address.getText().toString();
            cutName = custName.getText().toString();
            spin= findViewById(R.id.spinner2);
            selectService= spin.getSelectedItem().toString();

            empType=findViewById(R.id.rdGroup);
            if(employee.isChecked()){
                type="Employee";
            }
            else{
                type="Manager";
            }


           firebaseRef.child("users").child(userid).child("company").setValue(compName);
           firebaseRef.child("users").child(userid).child("user_type").setValue(type);
           firebaseRef.child("users").child(userid).child("customer_name").setValue(cutName);
           firebaseRef.child("users").child(userid).child("service").setValue(selectService);
           firebaseRef.child("users").child(userid).child("date").setValue(datedate);
           firebaseRef.child("users").child(userid).child("mobile_no").setValue(mobno);
           firebaseRef.child("users").child(userid).child("address").setValue(add);
//
//            i.putExtra("user", userid);
//
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        }
    });


    }

}
