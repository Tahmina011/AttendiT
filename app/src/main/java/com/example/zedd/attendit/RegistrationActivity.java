package com.example.zedd.attendit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    EditText edittname,edittbd,edittphone,edittuid,edittemail,edittpass,edittroll;
    Spinner tgender;
    Button tsave;
    DatabaseReference tdataref;
    DatabaseReference mDatabaseStudent,statusr;
    private FirebaseAuth mFirebaseAuth;
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mProgressDialog = new ProgressDialog(this);
        mFirebaseAuth = FirebaseAuth.getInstance();

        mDatabaseStudent= FirebaseDatabase.getInstance().getReference("Teachers");
        statusr=FirebaseDatabase.getInstance().getReference("status");
        edittname=(EditText)findViewById(R.id.r_name);
        edittbd=(EditText)findViewById(R.id.r_bd);
        edittphone=(EditText)findViewById(R.id.r_phone);
        edittuid=(EditText)findViewById(R.id.r_uid);
        edittemail=(EditText)findViewById(R.id.r_email);
        edittpass=(EditText)findViewById(R.id.r_pass);
        edittroll=(EditText)findViewById(R.id.r_id);
        tgender=(Spinner)findViewById(R.id.r_gender);
        tsave=(Button)findViewById(R.id.r_save);
        tsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addfTeacher();
            }
        });

    }
    private void addfTeacher(){
        String name =edittname.getText().toString().trim();
        String bd=edittbd.getText().toString();
        String phone=edittphone.getText().toString();
        final String s_roll=edittroll.getText().toString();
        String uid=edittuid.getText().toString();
        String email=edittemail.getText().toString();
        String pass=edittpass.getText().toString();
        String gen=tgender.getSelectedItem().toString();

        if (TextUtils.isEmpty(s_roll))
        {
            Toast.makeText(this,"Please enter  ID",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(name))
        {
            Toast.makeText(this,"Please enter  name",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(bd))
        {
            Toast.makeText(this,"Please enter  birth date",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this,"Please enter  phone",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(uid))
        {
            Toast.makeText(this,"Please enter  user_id",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please enter  Email",Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(this,"Please enter  Password",Toast.LENGTH_LONG).show();
        }
        else {
            mProgressDialog.setMessage("Registering...");
            mProgressDialog.show();
            mFirebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        mProgressDialog.dismiss();
                        String Uid = mFirebaseAuth.getCurrentUser().getUid();
                        Status status = new Status(Uid,"teacher",s_roll);
                        statusr.child(Uid).setValue(status);
                        Intent intent = new Intent(RegistrationActivity.this,MainNavActivity.class);
                        startActivity(intent);

                    }
                }
            });
            RTeacher teacherr=new RTeacher(name,s_roll,bd,phone,email,gen,uid,pass);
            mDatabaseStudent.child(s_roll).setValue(teacherr);
            edittname.setText("");
            edittbd.setText("");
            edittphone.setText("");
            edittpass.setText("");
            edittroll.setText("");
            edittuid.setText("");
            edittemail.setText("");
            Toast.makeText(this,"Teacher's Information added to the list",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegistrationActivity.this,MainNavActivity.class);
            startActivity(intent);
        }

    }
}
