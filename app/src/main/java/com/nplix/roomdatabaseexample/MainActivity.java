package com.nplix.roomdatabaseexample;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProfileDatabase db;
    String DB_NAME="MyDB";
    RecyclerView recyclerView;
    ProfileAdapter adapter;
    List<ProfileEntity> entities;
    FloatingActionButton fab;
    Snackbar snackbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Migration MIGRATION_1_2 = new Migration(1, 2) {
            @Override
            public void migrate(SupportSQLiteDatabase database) {
                database.execSQL("ALTER TABLE `ProfileEntity` ADD COLUMN `About` TEXT");
            }
        };

       // db = Room.databaseBuilder(getApplicationContext(),
         //       ProfileDatabase.class, DB_NAME).addMigrations(MIGRATION_1_2).build();
        db=Room.databaseBuilder(getApplicationContext(),ProfileDatabase.class,DB_NAME)
                .fallbackToDestructiveMigration().build();

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new ProfileAdapter(this,false,false);
        recyclerView.setAdapter(adapter);
        SelectAsyncTask selectAsyncTask=new SelectAsyncTask();
       selectAsyncTask.execute();

        View.OnClickListener DeleteList=new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInput();
            }
        });


    }

    private class InsertTask extends AsyncTask<ProfileEntity,Integer,Integer> {

        @Override
        protected Integer doInBackground(ProfileEntity... users) {
            db.profileDao().insertAll(users);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            Log.d("InsertTask","One Row Inserted");

            SelectAsyncTask selectAsyncTask=new SelectAsyncTask();
            selectAsyncTask.execute();
        }
    }
//5036314291fd8f54
    private class SelectAsyncTask extends AsyncTask<Void,Integer,List<ProfileEntity>> {

        @Override
        protected List<ProfileEntity> doInBackground(Void... voids) {
            return db.profileDao().getAll();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<ProfileEntity> profileEntities) {
            super.onPostExecute(profileEntities);
            Log.d("Select",profileEntities.size()+" row found");
            adapter.setData(profileEntities);
            adapter.notifyDataSetChanged();
        }
    }

    private void showInput(){
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.entity_details, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setView(promptsView);
        final EditText userName = (EditText) promptsView
                .findViewById(R.id.editTextName);
        final EditText userEmail=(EditText) promptsView.findViewById(R.id.editTextEmail);
        final EditText userAbout = (EditText) promptsView
                .findViewById(R.id.editTextAbout);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input from user for password

                                ProfileEntity entity=new ProfileEntity();
                                entity.setEmail(userEmail.getText().toString());
                                entity.setName(userName.getText().toString());
                                entity.setAbout(userAbout.getText().toString());

                                InsertTask insertTask=new InsertTask();
                                insertTask.execute(entity);

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }
}
