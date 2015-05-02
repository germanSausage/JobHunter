package bestest.coderz.job.hunter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class baza extends SQLiteOpenHelper{

	
	
	
	
	public baza(Context context) {
		super(context, "TAG_DATABASE", null, 11);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String query="CREATE TABLE tag(id INTEGER PRIMARY KEY,ime TEXT, podrucje INTEGER)";
		db.execSQL(query);
        query="CREATE TABLE filteri(id INTEGER PRIMARY KEY, naziv TEXT,tagovi TEXT,podrucje INTEGER, lokacija INTEGER, aktivan INTEGER)";
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
    public boolean checkDatabaseForFilters()
    {
        boolean coffee;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM filteri",null);
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

    public Filter getActiveFilter()
    {
        Filter filter=null;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM filteri WHERE aktivan==1",null);
        if(cursor.moveToFirst())
        {

            filter=new Filter(cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4));
        }

        db.close();
        return filter;
    }
    public void insertData()
    {
        String query; ContentValues values;
        SQLiteDatabase db=getWritableDatabase();
        ArrayList<TAG> neuro=new ArrayList<>();

        neuro.add(new TAG("Java",1));

        neuro.add(new TAG("C++",1));
        neuro.add(new TAG("Perl",1));
        neuro.add(new TAG("Konobar/ica",26));
        neuro.add(new TAG("Kuhar/ica",26));
        neuro.add(new TAG("Čišćenje",26));
        neuro.add(new TAG("Python",1));
        neuro.add(new TAG("Android",1));
        neuro.add(new TAG("ASP.NET",1));
        neuro.add(new TAG("HTML",1));
        neuro.add(new TAG("CSS",1));

        neuro.add(new TAG("Sistemski admin",1));
        neuro.add(new TAG("Linux",1));
        neuro.add(new TAG("Urara",21));
        neuro.add(new TAG("Pekar",21));
        neuro.add(new TAG("Bravar",21));
        neuro.add(new TAG("KMljekar",21));
        neuro.add(new TAG("Šumar",21));
        neuro.add(new TAG("Farma",21));
        neuro.add(new TAG("Unix",1));
        neuro.add(new TAG("Linux",1));
        neuro.add(new TAG("Robotika",1));
        neuro.add(new TAG("PHP",1));
        neuro.add(new TAG("MSA",1));
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
                newList.add(new Filter(cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4)));
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
