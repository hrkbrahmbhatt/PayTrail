package my.webs2canada.paytrail.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import my.webs2canada.paytrail.model.User;


/**
 * Created by hrsikeshbrahmbhatt on 2018-02-11.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserManager.db";
    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

   // private String CREATE_USER_TABLE = "CREATE TABLE "+ TABLE_USER + "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT," + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT," + ")";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";
    private String DROP_USER_TABLE = "DROP TABLE IF EXITS " + TABLE_USER;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_USER_TABLE);
        onCreate(db);

    }


    public void addUser(User user){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME,user.getName());
        contentValues.put(COLUMN_USER_EMAIL,user.getEmail());
        contentValues.put(COLUMN_USER_PASSWORD,user.getPassword());

        sqLiteDatabase.insert(TABLE_USER, null,contentValues);
        sqLiteDatabase.close();




    }

    public String getUserName(String email){
        String userName = "";
        String[] columns = {
                COLUMN_USER_NAME
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.moveToFirst();
        userName = cursor.getString(0);
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return userName;
        }
        return userName;
    }




    public boolean checkUser(String email){
        String[] columns = {
                COLUMN_USER_ID

        };

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = sqLiteDatabase.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();

        if(cursorCount > 0 ){
            return true;
        }
        return false;
    }



    public boolean checkUser(String email, String password){
        String[] columns = {
                COLUMN_USER_ID

        };

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {email,password};

        Cursor cursor = sqLiteDatabase.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();

        if(cursorCount > 0 ){
            return true;
        }
        return false;
    }
}
