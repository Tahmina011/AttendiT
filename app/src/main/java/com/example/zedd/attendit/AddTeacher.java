package com.example.zedd.attendit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

public class AddTeacher extends DialogFragment {


   /* interface AddTeacherDialogListener{

        void onSaveButtonClick(DialogFragment dialog);
        //void onCancelButtonClick(DialogFragment dialog);

    }


    AddTeacherDialogListener addTeacherListener;
    Context context;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {

            addTeacherListener = (AddTeacherDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement AddTeacherDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.teacher_form, null))


                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        addTeacherListener.onSaveButtonClick(AddTeacher.this);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddTeacher.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }*/
}