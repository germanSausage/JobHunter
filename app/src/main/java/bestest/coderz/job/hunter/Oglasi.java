package bestest.coderz.job.hunter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bestest.coderz.job.hunter.R.layout;

public class Oglasi extends Fragment{
    TextView grab;
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View b=inflater.inflate(layout.oglasi, container,false);

         grab= (TextView) b.findViewById(R.id.textView);


		return b;
	}
	
	public void setter(String pod,String lok,String tagovi)
    {
        grab.setText(pod+"\n"+lok+"\n"+tagovi);
    }

}
