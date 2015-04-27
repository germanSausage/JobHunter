package bestest.coderz.job.hunter;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.apache.http.HttpConnection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import bestest.coderz.job.hunter.R.id;

public class Main extends FragmentActivity implements Options.chaplin{
	ArrayList<Fragment> fragmentList;
    String podrucje="empty";
    String lokacija="empty";
    String tagovi;
    ArrayList<Oglas> oglList;
    Options options=new Options();
    Oglasi oglasi=new Oglasi();

    SetFilter filterlist=new SetFilter();



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ViewPager newViewPager=(ViewPager) findViewById(id.pager);
		newViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
		fragmentList=fragments();
        baza db=new baza(this);


        if (!db.checkDatabase()) {
            ArrayList<TAG> temp=new ArrayList<>();

            temp.add(new TAG("Java",6));
            temp.add(new TAG("C",6));
            temp.add(new TAG("C++",6));
            temp.add(new TAG("Perl",6));
            temp.add(new TAG("Konobar/ica",9));
            temp.add(new TAG("Kuhar/ica",9));
            temp.add(new TAG("Čišćenje",9));
            temp.add(new TAG("Phyton",6)); temp.add(new TAG("Android",7));
            db.insertData(temp);
            Toast.makeText(this, temp.get(0).ime, Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"Base created.",Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Base already exists,no need for another one.", Toast.LENGTH_SHORT).show();
        }
    }

    public void comeWithMeIfYouWantToLive(String podrucje,String lokacija,String tagovi)
    {
        this.podrucje=podrucje;this.lokacija=lokacija;this.tagovi=tagovi;
    }
    public void fff()
    {
      ViewPager newViewPager=(ViewPager)findViewById(id.pager);
        newViewPager.setCurrentItem(1);
        oglasi.setter(podrucje,lokacija,tagovi,oglList);

    }
	private ArrayList<Fragment> fragments()
	{

		ArrayList<Fragment> tempFragmentList=new ArrayList<>();
		tempFragmentList.add(options);
		tempFragmentList.add(oglasi);
        tempFragmentList.add(filterlist);


		return tempFragmentList;

		
		
	}

	private class PagerAdapter extends FragmentPagerAdapter
	{
		public PagerAdapter(android.support.v4.app.FragmentManager fragmentManager)
		{
			super(fragmentManager);
		}

		@Override
		public Fragment getItem(int arg0) {
		
			return fragmentList.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// oglasi you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);


    }
    @SuppressWarnings("UnusedDeclaration")
    private class GetAdds extends AsyncTask<ArrayList<TAG>,Integer ,ArrayList<Oglas>>
    {
        @SafeVarargs
        @Override
        protected final ArrayList<Oglas> doInBackground(ArrayList<TAG>...  params) {

           oglasi.hideShow(0);

            StringBuilder tagBuilder=new StringBuilder();

            ArrayList<TAG> tempTag=params[0];

            for(TAG objekt: tempTag)
            {
                tagBuilder.append(objekt.ime);
            }

            URL url = null;
            try {
                url = new URL("http://188.129.37.133/JobHunter");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            ArrayList<Oglas> returnList=new ArrayList<>();
            try {
               HttpURLConnection con=(HttpURLConnection)url.openConnection();

               con.setRequestMethod("POST");
               con.setDoOutput(true);
               con.setDoInput(true);
                con.setRequestProperty("Content-Type", "text/plain; charset=utf-8");

               DataOutputStream stream = new DataOutputStream(con.getOutputStream());
                stream.writeUTF(tagBuilder.toString());
                stream.flush();
                stream.close();


                InputStream input=con.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(input));

                StringBuilder buider=new StringBuilder();
                String line;
                while((line=reader.readLine())!=null)
                {
                    buider.append(line);
                }
                reader.close();


                JSONObject jobjekt= new JSONObject(line);

                JSONArray jarray=jobjekt.getJSONArray("oglasi");




/*
* String naslov,
            String opisPosla,
            String tvtrka,
            String podrucje,
            String tagovi,
            String pbrMjesto,
            String izvor,
            String zupanija
*
*
*
*
* */
                for(int i=0;i< jarray.length();i++)
                {
                    JSONObject tmpObjekt=jarray.getJSONObject(i);
                    Oglas tmpOglas=new Oglas(tmpObjekt.getString("naslov"),tmpObjekt.getString("opisPosla"),tmpObjekt.getString("tvtrka"),tmpObjekt.getString("podrucje"),tmpObjekt.getString("tagovi"),tmpObjekt.getString("pbrMjesto"),tmpObjekt.getString("izvor"),tmpObjekt.getString("zupanija"));
                    returnList.add(tmpOglas);

                }

           }
           catch (Exception e){}


            return returnList;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(ArrayList<Oglas> oglasitmp) {

            oglasi.setter("","","",oglasitmp);

            super.onPostExecute(oglasitmp);
           oglasi.hideShow(1);
        }
    }
}
