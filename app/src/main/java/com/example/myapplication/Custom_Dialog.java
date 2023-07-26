package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Custom_Dialog extends AppCompatDialogFragment {

    Custom_DialogInterFace dialogInterFace;
    EditText date,date1;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.date_popup,null);

        builder.setView(view)
                .setTitle("Teset")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String datetxt = date.getText().toString();
                        String date1txt = date1.getText().toString();
                        dialogInterFace.applyTexts(datetxt,date1txt);
                    }
                });

        date = view.findViewById(R.id.date);
        date1 = view.findViewById(R.id.date1);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        dialogInterFace = (Custom_DialogInterFace) context;
    }

    public interface Custom_DialogInterFace{
        void applyTexts(String datetxt, String date1txt);
    }

}
