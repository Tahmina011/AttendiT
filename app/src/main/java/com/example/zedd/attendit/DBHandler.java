package com.example.zedd.attendit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_NAME = "Attend";


    private static final String TABLE_STUDENT_DETAIL = "studentDetails";
   // private static final String KEY_ID= "t_id";

    private static final String KEY_ROLL_NO = "roll_no";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NO = "phone_number";
    private static final String KEY_CGPA = "cgpa";

/*    private static final String TABLE_TEACHER_DETAIL = "teacherDetails";
    private static final String KEY_ID= "t_id";
    private static final String KEY_TNAME = "tname";
    private static final String KEY_TPHONE_NO = "tphone_number";
    private static final String KEY_DEPT = "dept";*/

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_STUDENT_DETAIL_TABLE = "CREATE TABLE " + TABLE_STUDENT_DETAIL + "("
                + KEY_ROLL_NO + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_PHONE_NO + " TEXT, "
                + KEY_CGPA + " REAL )";

        db.execSQL(CREATE_STUDENT_DETAIL_TABLE);

      /*  String CREATE_TEACHER_DETAIL_TABLE = "CREATE TABLE " + TABLE_TEACHER_DETAIL + "("
                + KEY_ID + " INTEGER,"
                + KEY_TNAME + " TEXT,"
                + KEY_TPHONE_NO + " TEXT, "
                + KEY_DEPT + " STRING )";*/

       // db.execSQL(CREATE_STUDENT_DETAIL_TABLE);
       // db.execSQL(CREATE_TEACHER_DETAIL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT_DETAIL);


        onCreate(db);
    }




    void addNewStudent(student newStud) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ROLL_NO, newStud.getRoll());
        values.put(KEY_NAME, newStud.getName());
        values.put(KEY_PHONE_NO, newStud.getPhone());
        values.put(KEY_CGPA, newStud.getCgpa());



        db.insert(TABLE_STUDENT_DETAIL, null, values);
        db.close();
    }


  /*  void addNewTeacher(teacher newteach) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, newteach.get_tid());
        values.put(KEY_TNAME, newteach.get_tName());
        values.put(KEY_TPHONE_NO, newteach.get_tPhone());
        values.put(KEY_DEPT, newteach.get_tDept());



        db.insert(TABLE_TEACHER_DETAIL, null, values);
        db.close();
    }
*/
 /*   public boolean updateStudentInfo(int updId, int updEnrolNo, String updName, String updPhoneNo, float updCgpa) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(KEY_ROLL_NO, updEnrolNo);
        args.put(KEY_NAME, updName);
        args.put(KEY_PHONE_NO, updPhoneNo);
        args.put(KEY_CGPA, updCgpa);

        return db.update(TABLE_STUDENT_DETAIL, args, KEY_ID + "=" + updId, null) > 0;
    }


    public boolean deleteStudent(int delID) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_STUDENT_DETAIL, KEY_ID + "=" + delID, null) > 0;

    }*/



    public List<student> getAllStudentList() {


        List<student> studentList = new ArrayList<student>();


        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT_DETAIL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {

                student stdnt = new student();
                stdnt.setRoll(cursor.getString(0));
                stdnt.setName(cursor.getString(1));
                stdnt.setPhone(cursor.getString(2));
                stdnt.setCgpa(Float.parseFloat(cursor.getString(3)));


                studentList.add(stdnt);

            } while (cursor.moveToNext());
        }


        return studentList;
    }
}
