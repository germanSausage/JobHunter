package bestest.coderz.job.hunter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    ArrayList<Oglas> oglasi;
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
        oglasi=new ArrayList<>();
         adapter=new OglasApapter(getActivity(),oglasi);

        ListView lw= (ListView) b.findViewById(R.id.listViewOglasi);
        lw.setAdapter(adapter);
         grab= (TextView) b.findViewById(R.id.textView);
        hs= (LinearLayout) b.findViewById(R.id.oglasListHideShow);
        bar=(ProgressBar)b.findViewById(R.id.progressBar);

		return b;
	}
	
	public void setter(String pod,String lok,String tagovi,ArrayList<Oglas> oglasi)
    {
        grab.setText(pod+"\n"+lok+"\n"+tagovi);
        this.oglasi=oglasi;
        adapter.notifyDataSetChanged();
    }
    public  void hideShow(int var)
    {
        if(var==0)
        {
            if(hs.getVisibility()==View.VISIBLE){
            hs.setVisibility(View.GONE);}

            if(bar.getVisibility()==View.GONE){
                bar.setVisibility(View.VISIBLE);}
        }
        else
        {
            if(hs.getVisibility()==View.GONE){
                hs.setVisibility(View.VISIBLE);}

            if(bar.getVisibility()==View.VISIBLE){
                bar.setVisibility(View.GONE);}
        }
    }

}
