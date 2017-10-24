package projects.android.my.customdialog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 24-10-2017.
 */

public class CustomDB extends SQLiteOpenHelper {
    public CustomDB(Context context) {
        super(context, "CustomDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table People(username TEXT,phone TEXT,dob TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
