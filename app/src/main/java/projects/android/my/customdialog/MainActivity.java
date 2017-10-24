package projects.android.my.customdialog;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView list;
    String[] name;
    String[] phone;
    String[] dob;
    SQLiteDatabase db;
    CustomAdapter customAdapter;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.listView);

        CustomDB customDB = new CustomDB(this);
        db = customDB.getWritableDatabase();

        LoadValues();




   }

    private void LoadValues()
    {
        Cursor records = db.query("People",null,null,null,null,null,null);
        if(records.getCount() > 0)
        {
            records.moveToFirst();
            name = new String[records.getCount()];
            phone = new String[records.getCount()];
            dob = new String[records.getCount()];
            int pos = 0;
            do {
                name[pos] = records.getString(0);
                phone[pos] = records.getString(1);
                dob[pos] = records.getString(2);
                pos++;
            } while (records.moveToNext());

            customAdapter = new CustomAdapter(this, name, phone, dob);
            list.setAdapter(customAdapter);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId())
        {
            case R.id.add :
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                view = getLayoutInflater().inflate(R.layout.customdialog,null);
                 builder.setView(view);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        EditText txtName = (EditText)view.findViewById(R.id.txtName);
                        EditText txtPhone = (EditText)view.findViewById(R.id.txtPhone);
                        EditText txtDOB = (EditText)view.findViewById(R.id.txtDOB);

                        ContentValues values = new ContentValues();
                        values.put("username",txtName.getText().toString());
                        values.put("phone",txtPhone.getText().toString());
                        values.put("dob",txtDOB.getText().toString());

                        db.insert("People",null,values);

                        Toast.makeText(MainActivity.this,"Saved",Toast.LENGTH_LONG).show();
                        LoadValues();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }
        return  true;
    }



}