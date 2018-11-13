package com.example.zedd.attendit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

public class AddStudent extends DialogFragment {


        interface AddStudentDialogListener{

            void onSaveButtonClick(DialogFragment dialog);
            //void onCancelButtonClick(DialogFragment dialog);

        }


        AddStudentDialogListener addStudentListener;
        Context context;

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);

            try {

                addStudentListener = (AddStudentDialogListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement AddStudentDialogListener");
            }
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {


            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            LayoutInflater inflater = getActivity().getLayoutInflater();

            builder.setView(inflater.inflate(R.layout.add_student, null))


                    .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            addStudentListener.onSaveButtonClick(AddStudent.this);
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            AddStudent.this.getDialog().cancel();
                        }
                    });
            return builder.create();
        }
}
