package com.assignment08_sudoku;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseManager  extends SQLiteOpenHelper {
    final Context context;

    static final String DATABASE_NAME="sudoku";
    static final int VERSION=1;
    static final String TABLENAME="board";
    static final String CREATE_TABLE="create table "+TABLENAME+"( _id integer primary key autoincrement, 'difficulty' integer ,'name' TXT, " +
            "'_00' integer,'_10' integer,'_20' integer,'_30' integer,'_40' integer,'_50' integer,'_60' integer,'_70' integer,'_80' integer," +
            "'_01' integer,'_11' integer,'_21' integer,'_31' integer,'_41' integer,'_51' integer,'_61' integer,'_71' integer,'_81' integer, " +
            "'_02' integer,'_12' integer,'_22' integer,'_32' integer,'_42' integer,'_52' integer,'_62' integer,'_72' integer,'_82' integer, " +
            "'_03' integer,'_13' integer,'_23' integer,'_33' integer,'_43' integer,'_53' integer,'_63' integer,'_73' integer,'_83' integer, " +
            "'_04' integer,'_14' integer,'_24' integer,'_34' integer,'_44' integer,'_54' integer,'_64' integer,'_74' integer,'_84' integer,  " +
            "'_05' integer,'_15' integer,'_25' integer,'_35' integer,'_45' integer,'_55' integer,'_65' integer,'_75' integer,'_85' integer, " +
            "'_06' integer,'_16' integer,'_26' integer,'_36' integer,'_46' integer,'_56' integer,'_66' integer,'_76' integer,'_86' integer, " +
            "'_07' integer,'_17' integer,'_27' integer,'_37' integer,'_47' integer,'_57' integer,'_67' integer,'_77' integer,'_87' integer, " +
            "'_08' integer, '_18' integer, '_28' integer, '_38' integer, '_48' integer, '_58' integer, '_68' integer, '_78' integer, '_88' integer )";

    public DatabaseManager(Context context) throws Exception{
        super(context,DATABASE_NAME,null,VERSION);
        this.context = context;
    }

    public ArrayList<String> getBoard(int difficulty, String name){
        Log.i(getClass().getSimpleName(), "getBoard: ");
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor =db.rawQuery("Select * from "+TABLENAME+" where difficulty=? and name=?",new String[]{String.valueOf(difficulty),name});
        Cursor cursor =db.rawQuery("Select * from board where difficulty=? and name=? limit 1",new String[]{String.valueOf(difficulty),name});

        ArrayList<String> res = new ArrayList<>();
        if (cursor != null) {
            cursor.moveToFirst();
            try {
                for (int i = 0; i <cursor.getColumnCount()-3 ; i++) {
                    res.add(cursor.getString(i+3));

                }
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
        db.close();
        return res;


    }



    public void insertBoard(ArrayList<int[]> board,String navn,int difficulty){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues input = new ContentValues();
        input.put("name",navn);
        input.put("difficulty",difficulty);
        for (int y = 0; y < board.size(); y++) {
            for (int x = 0; x <board.get(y).length ; x++) {
                input.put("_"+x+""+y,board.get(y)[x]);
                Log.i("dbLoading","_"+x+""+y+" "+board.get(y)[x]);
            }

        }

        db.insert(TABLENAME,null,input);
        db.close();
    }




    public ArrayList<String[]> listNames(int difficulty){
        final String NAME_QUERY="select _id,name from "+TABLENAME+" where difficulty=?";
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String[]> res = new ArrayList<>();
        Cursor cursor =
                db.rawQuery(NAME_QUERY, new String[]{difficulty+""});
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Log.i(getClass().getSimpleName(),"navn: "+cursor.getString(1));
                String tempRes[]={cursor.getString(0).replace("_",""),cursor.getString(1)};
                res.add(tempRes);
                cursor.moveToNext();
            }
        }
        db.close();
        return res;


    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(getClass().getSimpleName(), "onCreate: ");
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }



//tatt fra eksemplekode i faget
 @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            Log.d(getClass().getSimpleName(), "onUpdate");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLENAME);
            onCreate(sqLiteDatabase);

    }


    //tatt fra eksemplekode
    public void clean() {
        SQLiteDatabase db = getWritableDatabase();
        onUpgrade(db, 0, 0);
        db.close();
    }
}
