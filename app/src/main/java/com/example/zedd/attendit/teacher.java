package com.example.zedd.attendit;

public class teacher {

       int t_id;
        String t_name;
        String t_phone;
        String t_dept;



        public teacher()
        {


        }
        public teacher(int _id,String name,String phone,String dep)
        {
            this.t_id=_id;
            this.t_name=name;
            this.t_phone=phone;
            this.t_dept=dep;
        }
        public int get_tid() {
            return t_id;
        }

        public String get_tName() {
            return t_name;
        }

        public String get_tPhone() {
            return t_phone;
        }

         public String get_tDept() {
              return t_dept;
         }

        public void set_tid(int _id) {
            this.t_id = _id;
        }

        public void set_tName(String name) {
            this.t_name = name;
        }

        public void set_tPhone(String phone) {
            this.t_phone = phone;
        }

        public void set_tDept(String dept)
        {
            this.t_dept=dept;
        }
    }

