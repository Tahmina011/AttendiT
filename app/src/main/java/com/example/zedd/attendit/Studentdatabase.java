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

public class Studentdatabase extends AppCompatActivity implements
        AddStudent.AddStudentDialogListener,
        AddTeacher.AddTeacherDialogListener,
        AddCourse.AddCourseDialogListener{
    Button btnAddStudent;
    Button btnAddTeacher;
    Button btnAddCourse;
    TextView tvStdInfo;
    private String sTAG = "studentDetails";
    private String tTAG = "teacherDetails";
    private String cTAG = "courseDetails";
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
                dialog.show(getFragmentManager(), sTAG);
            }
        });
        btnAddTeacher = (Button)findViewById(R.id.btnAddTeacher);
        btnAddTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddTeacher dialog = new AddTeacher();
                dialog.show(getFragmentManager(), tTAG);
            }
        });
        btnAddCourse = (Button)findViewById(R.id.btnAddCourse);
        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCourse dialog = new AddCourse();
                dialog.show(getFragmentManager(),cTAG);
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
            Toast.makeText(getApplicationContext(),"\nNo :" + enrollNo + "\nName: " + name + "\nPhone No:" + phnNo,Toast.LENGTH_LONG).show();

        }



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
    public void tonSaveButtonClick(DialogFragment dialog) {
        EditText t_id = (EditText) dialog.getDialog().findViewById(R.id.et_id);
        String tid = t_id.getText().toString();
        int t_idn =Integer.parseInt(tid);



        EditText tName = (EditText) dialog.getDialog().findViewById(R.id.et_name);
        String name = tName.getText().toString();


        EditText entPhnNo = (EditText) dialog.getDialog().findViewById(R.id.et_phone);
        String  phnNo = entPhnNo.getText().toString();

        EditText dept = (EditText) dialog.getDialog().findViewById(R.id.et_dept);
        String  t_dept = dept.getText().toString();




        if(name == "" || phnNo == "" || t_dept=="" || t_idn== 0){

            Toast.makeText(getApplicationContext(),"Enter Data Again.. :P",Toast.LENGTH_LONG).show();
        }else{

            db.addNewTeacher(new teacher(t_idn,name,phnNo,t_dept));

            Toast.makeText(getApplicationContext(),"Student Added to the List.. O_o",Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"\nNo :" + t_idn + "\nName: " + name + "\nPhone No:" + phnNo,Toast.LENGTH_LONG).show();

        }


    }


    public void conSaveButtonClick(DialogFragment dialog) {



        EditText cou = (EditText) dialog.getDialog().findViewById(R.id.c_no);
        String co_no = cou.getText().toString();


        EditText cot = (EditText) dialog.getDialog().findViewById(R.id.c_title);
        String  cott = cot.getText().toString();


        if(co_no == "" || cott==""){

            Toast.makeText(getApplicationContext(),"Enter Data Again.. :P",Toast.LENGTH_LONG).show();
        }else{

            db.addNewCourse(new course(co_no,cott));

            Toast.makeText(getApplicationContext(),"Course Added to the List.. O_o",Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"\nCourse No :" + co_no + "\nCourse Title: " + cott,Toast.LENGTH_LONG).show();

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