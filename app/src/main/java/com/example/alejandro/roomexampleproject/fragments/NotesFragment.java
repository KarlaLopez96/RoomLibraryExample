package com.example.alejandro.roomexampleproject.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alejandro.roomexampleproject.R;
import com.example.alejandro.roomexampleproject.adapters.NotesAdapter;
import com.example.alejandro.roomexampleproject.models.Note;

import java.util.ArrayList;
import java.util.List;


public class NotesFragment extends Fragment {

    ArrayList<Note> userNotes;
    boolean flag = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.notesRecycler);
        NotesAdapter adapter = new NotesAdapter(getContext(), userNotes,flag);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void setUserNotes(ArrayList<Note> userNotes){
        this.userNotes = userNotes;
    }

    public void setUserNotes1(ArrayList<Note> userNotes,Boolean flag){
        this.userNotes = userNotes;
        this.flag = flag;
    }

}
