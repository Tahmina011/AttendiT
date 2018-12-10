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

public class studentRegistration extends AppCompatActivity {
    EditText editsrname,editsrbd,editsrphone,editsruid,editsremail,editsrpass,editsrroll;
    Spinner srgender;
    Button srsave;
    DatabaseReference srdataref,statusref;
    DatabaseReference srDatabaseStudent;
    private FirebaseAuth srFirebaseAuth;
    private ProgressDialog srProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

            srProgressDialog = new ProgressDialog(this);
            srFirebaseAuth = FirebaseAuth.getInstance();
            statusref = FirebaseDatabase.getInstance().getReference("status");

            srDatabaseStudent= FirebaseDatabase.getInstance().getReference("Students");
            editsrname=(EditText)findViewById(R.id.esr_name);
            editsrbd=(EditText)findViewById(R.id.esr_bd);
            editsrphone=(EditText)findViewById(R.id.esr_phone);
            editsruid=(EditText)findViewById(R.id.esr_uid);
            editsremail=(EditText)findViewById(R.id.esr_email);
            editsrpass=(EditText)findViewById(R.id.esr_pass);
            editsrroll=(EditText)findViewById(R.id.esr_roll);
            srgender=(Spinner)findViewById(R.id.esr_gender);
            srsave=(Button)findViewById(R.id.esr_save);
            srsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addStudent();
                }
            });

        }
        private void addStudent(){
            String name =editsrname.getText().toString().trim();
            String bd=editsrbd.getText().toString();
            String phone=editsrphone.getText().toString();
            final String s_roll=editsrroll.getText().toString();
            String uid=editsruid.getText().toString();
            String email=editsremail.getText().toString();
            String pass=editsrpass.getText().toString();
            String gen=srgender.getSelectedItem().toString();

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
                srProgressDialog.setMessage("Registering...");
                srProgressDialog.show();
                srFirebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            srProgressDialog.dismiss();
                            String Uid = srFirebaseAuth.getCurrentUser().getUid();
                            Status status = new Status(Uid,"student",s_roll);
                            statusref.child(Uid).setValue(status);

                            Intent intent = new Intent(studentRegistration.this,MainNavActivity.class);
                            startActivity(intent);

                        }
                    }
                });
                StudentF teacherr=new StudentF(name,s_roll,bd,phone,email,gen,uid,pass);
                srDatabaseStudent.child(s_roll).setValue(teacherr);
                editsrname.setText("");
                editsrbd.setText("");
                editsrphone.setText("");
                editsrpass.setText("");
                editsrroll.setText("");
                editsruid.setText("");
                editsremail.setText("");
                Toast.makeText(this,"Student's Information added to the list",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(studentRegistration.this,MainNavActivity.class);
                startActivity(intent);
            }

        }

}
