package bestest.coderz.job.hunter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomislav on 23.04.15..
 */
public class FilterListAdapter  extends ArrayAdapter<Filter>{

    Context context;
    ArrayList<Filter> filters;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view=inflater.inflate(R.layout.filterlistitem,parent,false);

        TextView title= (TextView) view.findViewById(R.id.filterTitle);
        TextView sum= (TextView) view.findViewById(R.id.smallTagList);

        title.setText(filters.get(position).naziv);
        if(filters.get(position).tagovi.length()<50)
        {
            sum.setText(filters.get(position).tagovi);
        }
        else {
            sum.setText(filters.get(position).tagovi.substring(0, 50));

        }
        return view;
    }

    public FilterListAdapter(Context context,ArrayList<Filter> objects) {
        super(context, 0, objects);

        this.context=context;
        this.filters=objects;


    }
}
