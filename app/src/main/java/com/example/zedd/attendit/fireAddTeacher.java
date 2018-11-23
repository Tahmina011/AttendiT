package com.example.zedd.attendit;

  import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class fireAddTeacher extends AppCompatActivity {
    EditText edittname,edittbd,edittphone,edittuid,edittemail,edittpass,edittroll;
    Spinner tgender;
    Button tsave;
    DatabaseReference tdataref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_add_teacher);
        edittname=(EditText)findViewById(R.id.ett_name);
        edittbd=(EditText)findViewById(R.id.ett_bd);
        edittphone=(EditText)findViewById(R.id.ett_phone);
        tdataref= FirebaseDatabase.getInstance().getReference("Teachers");

        edittuid=(EditText)findViewById(R.id.ett_uid);
        edittemail=(EditText)findViewById(R.id.ett_email);
        edittpass=(EditText)findViewById(R.id.ett_pass);
        edittroll=(EditText)findViewById(R.id.ett_id);
        tgender=(Spinner)findViewById(R.id.ett_gender);
        tsave=(Button)findViewById(R.id.ett_save);
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
        String s_roll=edittroll.getText().toString();
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
            RTeacher teacherr=new RTeacher(name,s_roll,bd,phone,email,gen,uid,pass);
            tdataref.child(s_roll).setValue(teacherr);
            edittname.setText("");
            edittbd.setText("");
            edittphone.setText("");
            edittpass.setText("");
            edittroll.setText("");
            edittuid.setText("");
            edittemail.setText("");
            Toast.makeText(this,"Teacher's Information added to the list",Toast.LENGTH_LONG).show();
        }
    }
}
