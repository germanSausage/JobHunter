package bestest.coderz.job.hunter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tomislav on 26.04.15..
 */
public class OglasApapter extends ArrayAdapter<Oglas> {

    Context context;
    ArrayList<Oglas> lista;

    public OglasApapter(Context context,  ArrayList<Oglas> objects) {
        super(context, 0, objects);

        this.context=context;
        this.lista=objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view=inflater.inflate(R.layout.oglaslistitem,parent,false);

        TextView naslov= (TextView) view.findViewById(R.id.textViewOglasNaslov);
        TextView opis= (TextView) view.findViewById(R.id.textViewOglasOpis);
        TextView tvrtka= (TextView) view.findViewById(R.id.textViewOglasTvrtka);
        TextView izvor= (TextView) view.findViewById(R.id.textViewOglasIzvor);

        naslov.setText(lista.get(position).naslov);
        opis.setText(lista.get(position).opisPosla);
        tvrtka.setText(lista.get(position).tvrtka);
        izvor.setText(lista.get(position).izvor);

        return view;
    }
}
