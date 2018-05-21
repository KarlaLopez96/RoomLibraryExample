package com.example.alejandro.roomexampleproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alejandro.roomexampleproject.R;
import com.example.alejandro.roomexampleproject.models.Note;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoNote extends Fragment {
    Note note;
    TextView textView;
    public InfoNote() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_info_note, container, false);

        textView = v.findViewById(R.id.infoNote);
        textView.setText(note.getData());

        return v;
    }

    public void setUserNotes(Note userNotes){
        this.note = userNotes;
    }

}
