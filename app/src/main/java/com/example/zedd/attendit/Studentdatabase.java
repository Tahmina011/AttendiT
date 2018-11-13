package com.example.zedd.attendit;

import android.app.DialogFragment;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Studentdatabase extends AppCompatActivity implements AddStudent.AddStudentDialogListener {
    Button btnAddStudent;

    TextView tvStdInfo;
    private String TAG = "studentDetails";
    SQLiteDatabase dtb;
    int btnBackPressCounter = 0;
    DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentdatabase);
        db = new DBHandler(this);

        btnAddStudent = (Button)findViewById(R.id.btnAddStudent);
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddStudent dialog = new AddStudent();
                dialog.show(getFragmentManager(), TAG);
            }
        });
    }

    @Override
    public void onSaveButtonClick(DialogFragment dialog) {
        EditText entEnrolNo = (EditText) dialog.getDialog().findViewById(R.id.e_roll);
        String enrollNo = entEnrolNo.getText().toString();
       // int int_enrollNo =Integer.parseInt(entEnrolNo.getText().toString());



        EditText entName = (EditText) dialog.getDialog().findViewById(R.id.e_name);
        String name = entName.getText().toString();


        EditText entPhnNo = (EditText) dialog.getDialog().findViewById(R.id.e_phone);
        String  phnNo = entPhnNo.getText().toString();

        EditText cgapa = (EditText) dialog.getDialog().findViewById(R.id.e_cgpa);
        String  scgpa = cgapa.getText().toString();
        float p=Float.parseFloat(scgpa);

        boolean check_enrollNo = checkEnrollNo(enrollNo);

        boolean check_name = checkName(name);

        boolean check_phnNo = checkPhoneNo(phnNo);

        if(check_enrollNo == false || check_name == false || check_phnNo == false){

            Toast.makeText(getApplicationContext(),"Enter Data Again.. :P",Toast.LENGTH_LONG).show();
        }else{

            db.addNewStudent(new student(enrollNo,name,phnNo,p));

            Toast.makeText(getApplicationContext(),"Student Added to the List.. O_o",Toast.LENGTH_LONG).show();
        }



        Toast.makeText(getApplicationContext(),"\nNo :" + enrollNo + "\nName: " + name + "\nPhone No:" + phnNo,Toast.LENGTH_LONG).show();



    }

    public boolean checkIdno(String Id_No){

        if(Id_No == ""){
            return false;
        }else{
            return true;
        }

    }


    public boolean checkEnrollNo(String enr_No){

        if(enr_No == ""){

            return false;
        }else{
            return true;
        }

    }


    public boolean checkName(String stdName){

        if(stdName == ""){
            return false;
        }else{
            return true;
        }
    }

    //Check Phone Number
    public boolean checkPhoneNo(String phn_No){

        if(phn_No == ""){

            return false;
        }else{
            return true;
        }

    }


}
/*
  TextView numbers=(TextView) findViewById(R.id.numbers);
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent num =new Intent(MainActivity.this,NumbersActivity.class);
                startActivity(num);
            }
        });
 */