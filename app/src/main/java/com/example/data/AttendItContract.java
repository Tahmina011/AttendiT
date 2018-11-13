package com.example.data;


import android.provider.BaseColumns;

public class AttendItContract {

    public static abstract class StudentEntry implements BaseColumns{
        public static final String STUDENT_TABLE_NAME="student";
        public static final String STUDENT_ROLL="s_roll";
        public static final String STUDENT_NAME="s_name";
        public static final String STUDENT_PHONE_NO="s_phone_no";

    }
    public static abstract class TeacherEntry implements BaseColumns{
        public static final String TEACHER_TABLE_NAME="teacher";
        public static final String TEACHER_ID="t_id";
        public static final String TEACHER_NAME="t_name";
        public static final String TEACHER_PHONE_NO="t_phone_no";

    }
    public static abstract class CourseEntry implements BaseColumns{
        public static final String COURSE_TABLE_NAME="course";
        public static final String COURSE_ID="c_id";
        public static final String COURSE_TITLE="c_title";
    }

}
