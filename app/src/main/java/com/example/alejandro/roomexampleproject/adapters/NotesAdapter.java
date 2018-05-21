package com.example.alejandro.roomexampleproject.adapters;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alejandro.roomexampleproject.R;
import com.example.alejandro.roomexampleproject.activities.MainActivity;
import com.example.alejandro.roomexampleproject.fragments.InfoNote;
import com.example.alejandro.roomexampleproject.fragments.NotesFragment;
import com.example.alejandro.roomexampleproject.models.Note;

import java.util.ArrayList;

/**
 * Created by Karla on 20/5/2018.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{
    Context context;
    ArrayList<Note> NotesList;
    Boolean flag;

    public NotesAdapter(Context context, ArrayList<Note> NotesList,Boolean flag) {
        this.context = context;
        this.NotesList = NotesList;
        this.flag = flag;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.notes_list, parent, false);
        return new NotesViewHolder(v, flag);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, final int position) {
        holder.notes.setText(NotesList.get(position).getData());
        if(flag){
            holder.notes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    android.support.v4.app.FragmentManager fragmentManager;
                    android.support.v4.app.FragmentTransaction fragmentTransaction;

                    fragmentManager = ((MainActivity)v.getContext()).getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();

                    InfoNote fragment = new InfoNote();
                    fragment.setUserNotes(NotesList.get(position));

                    fragmentTransaction.replace(R.id.contentFrame2, fragment);
                    fragmentTransaction.commit();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return NotesList.size();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder{
        TextView notes;
        Boolean flag;

        public NotesViewHolder(View itemView,Boolean flag) {
            super(itemView);
            this.flag = flag;
            this.notes = itemView.findViewById(R.id.notas2);
        }


       /* @Override
        public void onClick(View v) {
            android.support.v4.app.FragmentManager fragmentManager = ((MainActivity)v.getContext()).getSupportFragmentManager();

            fragmentManager.executePendingTransactions();

            //Crea una nueva trasacción.
            android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

            //Crea un fragmento y lo añade.
            InfoNote fragment = new InfoNote();
            fragment.setUserNotes();
            transaction.replace(R.id.contentFrame2, fragment);
            //Realizando cambios.
            transaction.commit();

            /*((FragmentActivity) view.getContext()).getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentC, fragment).commit();*/
    }
}
