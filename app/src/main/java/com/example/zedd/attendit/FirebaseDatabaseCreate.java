package com.example.zedd.attendit;

import android.icu.text.DateFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class FirebaseDatabaseCreate extends AppCompatActivity {

           EditText editname,editbd,editphone,edituid,editemail,editpass,editroll;
           Spinner gender;
           Button save;
           DatabaseReference dataref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_info_student);
        editname=(EditText)findViewById(R.id.es_name);
        editbd=(EditText)findViewById(R.id.es_bd);
        editphone=(EditText)findViewById(R.id.es_phone);
        dataref= FirebaseDatabase.getInstance().getReference("Students");

        edituid=(EditText)findViewById(R.id.es_uid);
        editemail=(EditText)findViewById(R.id.es_email);
        editpass=(EditText)findViewById(R.id.es_pass);
        editroll=(EditText)findViewById(R.id.es_roll);
        gender=(Spinner)findViewById(R.id.es_gender);
        save=(Button)findViewById(R.id.es_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addfStudent();
            }
        });

    }
    private void addfStudent(){
        String name =editname.getText().toString().trim();
        String bd=editbd.getText().toString();
        String phone=editphone.getText().toString();
        String s_roll=editroll.getText().toString();
        String uid=edituid.getText().toString();
        String email=editemail.getText().toString();
        String pass=editpass.getText().toString();
        String gen=gender.getSelectedItem().toString();
         if (!TextUtils.isEmpty(s_roll)&&!TextUtils.isEmpty(gen) &&!TextUtils.isEmpty(name) && !TextUtils.isEmpty(bd) &&  !TextUtils.isEmpty(phone) &&   !TextUtils.isEmpty(uid) && !TextUtils.isEmpty(email) &&  !TextUtils.isEmpty(pass))
         {
            // String ss=dataref.push().getKey();
            StudentF studennt=new StudentF(name,bd,gen,uid,email,pass,s_roll,phone);
            dataref.child(s_roll).setValue(studennt);
            Toast.makeText(this,"Student Information added to the list",Toast.LENGTH_LONG).show();
         }
         else{
             Toast.makeText(this,"you should enter all  the fields",Toast.LENGTH_LONG).show();
         }
    }

}
