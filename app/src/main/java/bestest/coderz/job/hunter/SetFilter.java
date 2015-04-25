package bestest.coderz.job.hunter;

import android.app.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by tomislav on 23.04.15..
 */
public class SetFilter extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View temp=inflater.inflate(R.layout.filteri,container,false);

        baza tempBaza=new baza(getActivity());

        ArrayList<Filter> tempList=tempBaza.getAllFilters();

        ListView listView= (ListView) temp.findViewById(R.id.listViewForFilters);
        FilterListAdapter adapter=new FilterListAdapter(getActivity(),tempList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {




            }
        });
       /* listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });*/

        return temp;
    }
}
