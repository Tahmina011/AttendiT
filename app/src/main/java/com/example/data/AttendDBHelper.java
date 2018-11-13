package com.example.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.data.AttendItContract.CourseEntry;
import com.example.data.AttendItContract.StudentEntry;
import com.example.data.AttendItContract.TeacherEntry;

import static com.example.data.AttendItContract.CourseEntry.COURSE_ID;
import static com.example.data.AttendItContract.CourseEntry.COURSE_TABLE_NAME;
import static com.example.data.AttendItContract.CourseEntry.COURSE_TITLE;
import static com.example.data.AttendItContract.StudentEntry.STUDENT_NAME;
import static com.example.data.AttendItContract.StudentEntry.STUDENT_PHONE_NO;
import static com.example.data.AttendItContract.StudentEntry.STUDENT_ROLL;
import static com.example.data.AttendItContract.StudentEntry.STUDENT_TABLE_NAME;
import static com.example.data.AttendItContract.TeacherEntry.TEACHER_ID;
import static com.example.data.AttendItContract.TeacherEntry.TEACHER_NAME;
import static com.example.data.AttendItContract.TeacherEntry.TEACHER_PHONE_NO;
import static com.example.data.AttendItContract.TeacherEntry.TEACHER_TABLE_NAME;

public class AttendDBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG=AttendDBHelper.class.getSimpleName();
    private static final String DATABASE_NAME="attend.db";
    private static final int DATABASE_VERSION=1;

    public AttendDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


       String SQL_CREATE_STUDENT_TABLE =  "CREATE TABLE " + STUDENT_TABLE_NAME + " ("
                + STUDENT_ROLL + " INTEGER PRIMARY KEY , "
                + STUDENT_NAME + " TEXT NOT NULL, "
                + STUDENT_PHONE_NO+ " TEXT )";

       sqLiteDatabase.execSQL(SQL_CREATE_STUDENT_TABLE);


       String SQL_CREATE_TEACHER_TABLE =  "CREATE TABLE " + TEACHER_TABLE_NAME + " ("
               + TEACHER_ID + " INTEGER PRIMARY KEY , "
               + TEACHER_NAME + " TEXT NOT NULL, "
               + TEACHER_PHONE_NO+ " TEXT )";

        sqLiteDatabase.execSQL(SQL_CREATE_TEACHER_TABLE);

        String SQL_CREATE_COURSE_TABLE =  "CREATE TABLE " + COURSE_TABLE_NAME + " ("
                + COURSE_ID + " STRING PRIMARY KEY , "
                + COURSE_TITLE + " TEXT NOT NULL)";
        sqLiteDatabase.execSQL(SQL_CREATE_COURSE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
