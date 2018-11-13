package com.example.zedd.attendit;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

public class AddCourse extends DialogFragment{
    interface AddCourseDialogListener
    {
        void conSaveButtonClick(DialogFragment dialog);
    }
    AddCourseDialogListener addCourseDialogListener;
    Context context;

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {

            addCourseDialogListener = (AddCourseDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement AddCourseDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.course_form, null))


                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        addCourseDialogListener.conSaveButtonClick(AddCourse.this);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddCourse.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
