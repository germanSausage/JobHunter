package bestest.coderz.job.hunter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import bestest.coderz.job.hunter.R.layout;

public class Oglasi extends Fragment{
    TextView grab;
    ArrayList<Oglas> ogl =new ArrayList<>();
    ProgressBar bar;
    LinearLayout hs;
    OglasApapter adapter;
	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View b=inflater.inflate(layout.oglasi, container,false);

         adapter=new OglasApapter(getActivity(),ogl);

        ListView lw= (ListView) b.findViewById(R.id.listViewOglasi);
        lw.setAdapter(adapter);
         grab= (TextView) b.findViewById(R.id.textView);
        hs= (LinearLayout) b.findViewById(R.id.oglasListHideShow);
        bar=(ProgressBar)b.findViewById(R.id.progressBar);

		return b;
	}
	
	public void setter(String pod,String lok,String tagovi,ArrayList<Oglas> array)
    {
        grab.setText(pod+"\n"+lok+"\n"+tagovi);

        this.ogl.clear();
        for(Oglas oglas:array)
        {
            this.ogl.add(oglas);
            Log.d("sadrzaj pola", oglas.naslov);
        }
        if (tagovi.equals(""))
        {
            hideShow(1);
        }
        Log.d("sadrzaj pola", Integer.toString(array.size()));
        adapter.notifyDataSetChanged();
    }
    public  void hideShow(int var)
    {
        if(var==0)
        {

            hs.setVisibility(View.GONE);


                bar.setVisibility(View.VISIBLE);
        }
        else
        {

                hs.setVisibility(View.VISIBLE);


                bar.setVisibility(View.GONE);
        }
    }

}
