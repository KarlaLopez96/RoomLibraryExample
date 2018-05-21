package com.example.alejandro.roomexampleproject.activities;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.alejandro.roomexampleproject.R;
import com.example.alejandro.roomexampleproject.database.AppDatabase;
import com.example.alejandro.roomexampleproject.database.daos.NoteDao;
import com.example.alejandro.roomexampleproject.database.daos.UserDao;
import com.example.alejandro.roomexampleproject.fragments.AddUser;
import com.example.alejandro.roomexampleproject.fragments.ExtraFregament;
import com.example.alejandro.roomexampleproject.fragments.NotesFragment;
import com.example.alejandro.roomexampleproject.fragments.UserInfoFragment;
import com.example.alejandro.roomexampleproject.models.Note;
import com.example.alejandro.roomexampleproject.models.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    AppDatabase database;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.add:
                android.support.v4.app.FragmentManager fragmentManager;
                android.support.v4.app.FragmentTransaction fragmentTransaction;

                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                AddUser fragment = new AddUser();

                fragmentTransaction.replace(R.id.contentFrame, fragment);
                fragmentTransaction.commit();
                return true;
            default:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //database
        database = AppDatabase.getInstance(getApplicationContext());
        new FillInitialDbAsync(database).execute();
        //setting up the toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        //setting up drawerlayout
        drawerLayout = findViewById(R.id.drawerLayout);

        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();

                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                switch (item.getItemId()){
                    case R.id.user:
                        new GetUsersAsync(database).execute();
                        break;

                    case R.id.notes:
                        new GetNoteAsync(database).execute();
                        break;
                    case R.id.extra:
                        new GetExtra(database).execute();
                        break;
                }
                return true;
            }
        });

        new GetUName(database).execute();
    }

    private class GetExtra extends AsyncTask<Void, ArrayList<Note>, ArrayList<Note>>{
        private final NoteDao notedao;

        private GetExtra(AppDatabase db) {
            this.notedao = db.noteDao();
        }

        @Override
        protected ArrayList<Note> doInBackground(Void... voids) {
            ArrayList<Note> notes2 = new ArrayList<>();
            List<Note> note = notedao.getAllNotesByUser(1);
            for (int i=0;i<notedao.getAll().size();i++){
                notes2.add(notedao.getAll().get(i));
            }
            return notes2;
        }

        @Override
        protected void onPostExecute(ArrayList<Note> notes) {
            super.onPostExecute(notes);
            ExtraFregament fragment = new ExtraFregament();
            fragment.setExtraNotes(notes);

            fragmentTransaction.replace(R.id.contentFrame, fragment);
            fragmentTransaction.commit();
        }
    }

    private class GetUsersAsync extends AsyncTask<Void, User, User>{
        private final UserDao userdao;

        private GetUsersAsync(AppDatabase db) {
            this.userdao = db.userDao();
        }

        @Override
        protected User doInBackground(Void... voids) {
            return userdao.getAll().get(0);
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            UserInfoFragment fragment = new UserInfoFragment();
            fragment.setUser(user);

            fragmentTransaction.replace(R.id.contentFrame, fragment);
            fragmentTransaction.commit();
        }
    }

    //ejecutamos para el usuario de la pos 0
    private class GetUName extends AsyncTask<Void, User, User>{
        private final UserDao userdao;

        private GetUName(AppDatabase db) {
            this.userdao = db.userDao();
        }

        @Override
        protected User doInBackground(Void... voids) {

            ((TextView)navigationView.getHeaderView(0).findViewById(R.id.firstName_nav)).setText(
                    userdao.getAll().get(0).getFirstName()
            );
            ((TextView)navigationView.getHeaderView(0).findViewById(R.id.lastName_nav)).setText(
                    userdao.getAll().get(0).getLastName()
            );

            return userdao.getAll().get(0);
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private class GetNoteAsync extends AsyncTask<Void, ArrayList<Note>, ArrayList<Note>>{
        private final NoteDao notedao;

        private GetNoteAsync(AppDatabase db) {
            this.notedao = db.noteDao();
        }

        @Override
        protected ArrayList<Note> doInBackground(Void... voids) {
            ArrayList<Note> notes2 = new ArrayList<>();
            List<Note> note = notedao.getAllNotesByUser(1);
            for (int i=0;i<notedao.getAll().size();i++){
                notes2.add(notedao.getAll().get(i));
            }
            return notes2;
    }

        @Override
        protected void onPostExecute(ArrayList<Note> notes) {
            super.onPostExecute(notes);
            NotesFragment fragment = new NotesFragment();
            fragment.setUserNotes(notes);

            fragmentTransaction.replace(R.id.contentFrame, fragment);
            fragmentTransaction.commit();
        }
    }



    private class FillInitialDbAsync extends AsyncTask<Void, Void, Void>{
        private final UserDao userdao;
        private final NoteDao notedao;

        private FillInitialDbAsync(AppDatabase db) {
            this.userdao = db.userDao();this.notedao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userdao.insert(new User("Manuel", "Callejas", "22660931",
                            "manu_poeta@hotmail","Col.JuanitoSanchez,pje.'A',pol.A-5"),
                   new User("Enrique", "Palacios", "22577777","emailhotmail@yahoo",
                            "Col.Jardines de la Hacienda,pje'C',pol.D-55"));
            notedao.insert(new Note("Hola me gusta Karla",1),new Note("Te amo mi amorcito precioso <3",1));
            return null;
        }
    }
 }
