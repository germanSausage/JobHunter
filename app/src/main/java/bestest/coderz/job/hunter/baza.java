package bestest.coderz.job.hunter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class baza extends SQLiteOpenHelper{

	
	
	
	
	public baza(Context context) {
		super(context, "TAG_DATABASE", null, 8);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String query="CREATE TABLE tag(id INTEGER PRIMARY KEY,ime TEXT, podrucje INTEGER)";
		db.execSQL(query);
        query="CREATE TABLE filteri(id INTEGER PRIMARY KEY, naziv TEXT,tagovi TEXT)";
        db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS tag");
        db.execSQL("DROP TABLE IF EXISTS filteri");
		onCreate(db);
		
	}
	public boolean checkDatabase()
    {
        boolean coffee;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM tag",null);
        if(cursor.moveToFirst())
        {

          coffee=true;
        }
        else
        {
            coffee=false;
        }
        db.close();
    return coffee;
    }
    public void insertData(ArrayList<TAG> neuro)
    {
        String query; ContentValues values;
        SQLiteDatabase db=getWritableDatabase();
        for (int i = 0; i < neuro.size(); i++)
        {
           values=new ContentValues();
            values.put("ime",neuro.get(i).ime);
            values.put("podrucje",neuro.get(i).podrucje);
            db.insert("tag",null,values);

        }
        db.close();
    }
	public ArrayList<TAG> getAll(int podrucje)
	{
		ArrayList<TAG> newList=new ArrayList<TAG>();
		
		String query="SELECT * FROM tag WHERE podrucje="+podrucje;
		
	
		SQLiteDatabase db= this.getWritableDatabase();
		
		Cursor cursor=db.rawQuery(query, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				newList.add(new TAG(cursor.getString(1), cursor.getInt(2)));
			}while(cursor.moveToNext());
		}
		
		db.close();
		return newList;
		
	}
    public ArrayList<Filter> getAllFilters()
    {
        ArrayList<Filter> newList=new ArrayList<Filter>();

        String query="SELECT * FROM filteri";


        SQLiteDatabase db= this.getWritableDatabase();

        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do
            {
                newList.add(new Filter(cursor.getString(1), cursor.getString(2)));
            }while(cursor.moveToNext());
        }

        db.close();
        return newList;

    }
    public void newFilter(Filter filter)
    {
        String query; ContentValues values;
        SQLiteDatabase db=getWritableDatabase();

            values=new ContentValues();
            values.put("naziv",filter.naziv);
            values.put("tagovi",filter.tagovi);
            db.insert("filteri",null,values);


        db.close();
    }

}
