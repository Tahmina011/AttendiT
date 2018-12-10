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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {



    private EditText mEditTextEmail,mEditTextPassword;
    private Button mButtonLogin;
    private TextView mTextViewSignUp;
    private ProgressDialog mProgressDialog;
    public FirebaseAuth mFirebaseAuth;
    public String useremail;
    DatabaseReference stat;
String st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseAuth = FirebaseAuth.getInstance();
        stat= FirebaseDatabase.getInstance().getReference("status");

        /*if(mFirebaseAuth.getCurrentUser()!= null){
            finish();

            startActivity(new Intent(getApplicationContext(),UserActionsActivity.class));

        }*/
        mProgressDialog = new ProgressDialog(this);
        mButtonLogin=(Button) findViewById(R.id.buttonLogin);
        mEditTextEmail =(EditText)findViewById(R.id.editTextEmail);
        mEditTextPassword=(EditText)findViewById(R.id.editTextPassword);
        mTextViewSignUp = (TextView)findViewById(R.id.textViewSignup);
        mTextViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
                // Toast.makeText(LoginActivity.this,"Fields can not be left blank",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void userLogin(){
        final String email = mEditTextEmail.getText().toString();
        String pass = mEditTextPassword.getText().toString();
        useremail=email;


        if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Fields can not be left blank", Toast.LENGTH_LONG).show();
        }
        else{
            mProgressDialog.setMessage("Logging In..");
            mProgressDialog.show();

        }


        mFirebaseAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mProgressDialog.dismiss();
                        if(task.isSuccessful())
                        {

                            String Uid = mFirebaseAuth.getCurrentUser().getUid();
                            Query qu= qu=stat.orderByChild("uid").equalTo(Uid);
                            qu.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot postsnapshot:dataSnapshot.getChildren())
                                    {
                                       final Status sta=postsnapshot.getValue(Status.class);
                                       st=sta.status;
                                       String ssss=st;
                                        if (ssss.equals("teacher")) {
                                            Intent i = new Intent(LoginActivity.this, teacher_front_page.class);
                                            i.putExtra("em", email);
                                            startActivity(i);
                                            break;
                                        }
                                         if(ssss.equals("student"))
                                        {
                                            Intent i = new Intent(LoginActivity.this, student_front_page.class);
                                            i.putExtra("em", email);
                                            startActivity(i);
                                            break;
                                        }


                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            Intent i = new Intent(LoginActivity.this, AdminPage.class);
                            i.putExtra("em", email);
                            startActivity(i);

                        }
                        else{
                            Toast.makeText(LoginActivity.this,"The Email Password combination doesn't match!",Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }



}
