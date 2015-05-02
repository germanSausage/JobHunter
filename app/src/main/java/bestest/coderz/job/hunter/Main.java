package bestest.coderz.job.hunter;


import android.app.ActionBar;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import bestest.coderz.job.hunter.R.id;

public class Main extends FragmentActivity implements Options.chaplin{
	ArrayList<Fragment> fragmentList;
    String podrucje="empty";
    String lokacija="empty";
    String tagovi;
    String adresa;
    ArrayList<Oglas> oglList=new ArrayList<>();
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

            temp.add(new TAG("Java",1));

            temp.add(new TAG("C++",1));
            temp.add(new TAG("Perl",1));
            temp.add(new TAG("Konobar/ica",26));
            temp.add(new TAG("Kuhar/ica",26));
            temp.add(new TAG("Čišćenje",26));
            temp.add(new TAG("Python",1));
            temp.add(new TAG("Android",1));
            temp.add(new TAG("ASP.NET",1));
            temp.add(new TAG("HTML",1));
            temp.add(new TAG("CSS",1));
            db.insertData(temp);
            Toast.makeText(this, temp.get(0).ime, Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"Base created.",Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Base already exists,no need for another one.", Toast.LENGTH_SHORT).show();
        }


        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


    }

    public void comeWithMeIfYouWantToLive(String podrucje,String lokacija,String tagovi,String adresa)
    {
        this.podrucje=podrucje;this.lokacija=lokacija;this.tagovi=tagovi;this.adresa=adresa;
    }

    public void fff(String tmp,String tmpPodrucje,String tmpLokacija)
    {
      ViewPager newViewPager=(ViewPager)findViewById(id.pager);
        newViewPager.setCurrentItem(1);
        oglasi.setter(podrucje,lokacija,tagovi,oglList);
        new GetAdds().execute(tmp,tmpPodrucje,tmpLokacija);
        oglasi.hideShow(0);

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

        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this,Main.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

       // return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("UnusedDeclaration")
    private class GetAdds extends AsyncTask<String,Integer ,ArrayList<Oglas>>
    {
        @SafeVarargs
        @Override
        protected final ArrayList<Oglas> doInBackground(String...  params) {



            ArrayList<Oglas> returnList=new ArrayList<>();


            try {
                HttpClient client= new DefaultHttpClient();
                HttpPost post=new HttpPost("http://"+adresa+":8080/db/getAds");





                List<NameValuePair> pair=new ArrayList<NameValuePair>(2);
                pair.add(new BasicNameValuePair("tags",params[0]));
                pair.add(new BasicNameValuePair("podrucje",params[1]));
                pair.add(new BasicNameValuePair("lokacija",params[2]));

                post.setEntity(new UrlEncodedFormEntity(pair));

                HttpResponse response=client.execute(post);

                InputStream input=response.getEntity().getContent();
                BufferedReader reader=new BufferedReader(new InputStreamReader(input));

                StringBuilder buider=new StringBuilder();
                String line;
                while((line=reader.readLine())!=null)
                {
                    buider.append(line);
                }
                reader.close();

                Log.d("sadrzaj buffera", buider.toString());

                JSONObject jobjekt= new JSONObject(buider.toString());

                JSONArray jarray=jobjekt.getJSONArray("oglasi");

                for(int i=0;i<jarray.length();i++)
                {
                    JSONObject rec = jarray.getJSONObject(i);
                    returnList.add(new Oglas(rec.getString("naslov"),"","",rec.getString("opis"),rec.getString("tags"),"","",""));
                }





           }
           catch (Exception e){e.getStackTrace();e.getMessage();e.printStackTrace();}


            return returnList;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(ArrayList<Oglas> oglasitmp) {

            oglasi.setter("","","",oglasitmp);



        }
    }
}
