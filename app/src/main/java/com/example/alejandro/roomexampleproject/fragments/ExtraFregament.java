package com.example.alejandro.roomexampleproject.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alejandro.roomexampleproject.R;
import com.example.alejandro.roomexampleproject.models.Note;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExtraFregament extends Fragment {

    ArrayList<Note> userNotes;


    public ExtraFregament() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_extra_fregament, container, false);
        NotesFragment fragment = new NotesFragment();
        fragment.setUserNotes1(userNotes,true);
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentFrame1, fragment);
        fragmentTransaction.commit();
        return v;
    }

    public void setExtraNotes(ArrayList<Note> userNotes){
        this.userNotes = userNotes;
    }

}
