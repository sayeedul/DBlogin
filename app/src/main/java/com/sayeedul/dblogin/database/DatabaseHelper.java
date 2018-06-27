package com.sayeedul.dblogin.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.sayeedul.dblogin.model.ContactData;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1 ;
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME="registeration";
    public static final String ID ="Id";
    public static final String COLUMN_USERNAME="UserName";
    public static final String COLUMN_PASSWORD="Password";
    public static final String COLUMN_COUNTRY="Country";
    public static final String COLUMN_GENDER="Gender";
    public static final String COLUMN_TYPE="Type";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS registeration(ID INTEGER PRIMARY KEY AUTOINCREMENT not null,UserName TEXT not null,Password TEXT not null,Country TEXT not null,Gender TEXT not null,Type TEXT not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE); this.db = db;
    }

    public void insertdata(ContactData cd)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_USERNAME,cd.getUser());
        contentValues.put(DatabaseHelper.COLUMN_PASSWORD,cd.getPass());
        contentValues.put(DatabaseHelper.COLUMN_COUNTRY,cd.getCountry());
        contentValues.put(DatabaseHelper.COLUMN_GENDER,cd.getGender());
        contentValues.put(DatabaseHelper.COLUMN_TYPE,cd.getType());

       long id = db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
    }

    public String searchPass(String user)
    {
        db = this.getReadableDatabase();
        String query = "Select UserName,Password from "+DatabaseHelper.TABLE_NAME;
        Cursor c = db.rawQuery(query,null);

        String a , b;
        b = "not found";

        if(c.moveToFirst())
        {
            do{
                a = c.getString(0).toString();
                if(a.equals(user))
                {
                    b = c.getString(1).toString();
                    break;
                }

            }while(c.moveToNext());
        }
        return b;
    }

    public String searchType(String user)
    {

        db = this.getReadableDatabase();
        String query = "Select UserName,Type from " + DatabaseHelper.TABLE_NAME;
        Cursor c = db.rawQuery(query,null);

        String a , b;
        b = "not found";

        if(c.moveToFirst())
        {
            do{
                a = c.getString(0).toString();
                if(a.equals(user))
                {
                    b = c.getString(1).toString();
                    break;
                }

            }while(c.moveToNext());
        }
        return b;

    }

    public String readData(String USER)
    {
        db = this.getReadableDatabase();
       // String query = "Select * from " + DatabaseHelper.TABLE_NAME + " where UserName = "+USER +" ; "; ????? doubt!!!
        String query = "Select UserName,Password,Country,Gender,Type from " + DatabaseHelper.TABLE_NAME ;

        Cursor C = db.rawQuery(query,null);

        String a ,FINAL="";

        if(C.moveToFirst())
        {
            do{
                a = C.getString(0);
                if(a.equals(USER))
                {
                    FINAL = C.getString(0) +  C.getString(1)+ C.getString(2)
                               + C.getString(3) +  C.getString(4);
                    break;
                }

            }while(C.moveToNext());
        }
        return FINAL;
    }


    public void updateData(String user, String pass,String old)
    {
        db = this.getReadableDatabase();
        String query = "Select UserName,Password from " + DatabaseHelper.TABLE_NAME;
        Cursor c = db.rawQuery(query,null);

        String a;

        if(c.moveToFirst())
        {
            do{
                a = c.getString(0);
                if(a.equals(old))
                {
                    String update = "UPADATE "+TABLE_NAME+" SET UserName = "+user+" , Password = "+pass+" WHERE Username = "+old+";" ;
                    db.execSQL(update);
                    break;
                }

            }while(c.moveToNext());
        }

    }

    public Cursor getListContents(){
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE Type = Student", null);
        return data;
    }

    public void deleteData(String USER)
    {
        try{
            String query = "DELETE from "+TABLE_NAME+" WHERE UserName = "+USER+" ; ";
            db.beginTransaction();
            db.execSQL(query);
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); //Drop older table if exists
        onCreate(db);
    }
}
