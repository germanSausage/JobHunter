package bestest.coderz.job.hunter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.ArrayList;

import bestest.coderz.job.hunter.R.layout;

public class Options extends Fragment{

    chaplin charlie;
    private Spinner lokacija;
    private Spinner podrucje;
    private static ArrayList<ToggleButton> toggleButtonArray;
    private static int sendingInt;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        charlie=(chaplin)activity;


    }

    @Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final baza db=new baza(getActivity());
		View c=inflater.inflate(layout.optionscreen, container,false);


        final LinearLayout layoleft=(LinearLayout)c.findViewById(R.id.LinearLayoutLeftTag);
        final LinearLayout layoright=(LinearLayout)c.findViewById(R.id.LinearLayoutRightTag);
        final LinearLayout hidden= (LinearLayout) c.findViewById(R.id.filterLinearLayout);
        final LinearLayout shown= (LinearLayout) c.findViewById(R.id.searchLinearLayout);


        podrucje=(Spinner)c.findViewById(R.id.spinnerpodrucje);
        lokacija=(Spinner)c.findViewById(R.id.spinnerlokacija);


        final Button searchButton= (Button) c.findViewById(R.id.buttonSearchButton);
        searchButton.setBackgroundColor(getResources().getColor(R.color.dark_green_900_material));
        searchButton.setTextColor(getResources().getColor(R.color.white_material));

        final Button createFilter= (Button) c.findViewById(R.id.buttonCreateFilter);
        createFilter.setBackgroundColor(getResources().getColor(R.color.green_A700_material));
        createFilter.setTextColor(getResources().getColor(R.color.white_material));

        final Button cancel= (Button) c.findViewById(R.id.buttonCancelFilter);
        cancel.setBackgroundColor(getResources().getColor(R.color.green_A700_material));
        cancel.setTextColor(getResources().getColor(R.color.white_material));

        final Button add= (Button) c.findViewById(R.id.buttonAddFilter);
        add.setBackgroundColor(getResources().getColor(R.color.dark_green_900_material));
        add.setTextColor(getResources().getColor(R.color.white_material));

        final EditText edt= (EditText) c.findViewById(R.id.editTextCreate);
        final EditText adresa= (EditText) c.findViewById(R.id.address);

        podrucje.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<TAG> tmp=null;
                tmp=db.getAll(position+1);

                sendingInt=position;

                toggleButtonArray=new ArrayList<ToggleButton>();
                layoleft.removeAllViews();
                layoright.removeAllViews();

                for (int i = 1; i <tmp.size()+1 ; i++) {

                    ToggleButton but;
                    Drawable d = getResources().getDrawable(R.drawable.toggle_selector);

                    but = new ToggleButton(getActivity());

                    but.setTextOff(tmp.get(i - 1).ime);
                    but.setTextOn(tmp.get(i - 1).ime);
                    but.setText(tmp.get(i - 1).ime);

                    but.setTextColor(getResources().getColor(R.color.white_material));
                    but.setBackgroundDrawable(d);

                    LinearLayout.LayoutParams mlp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    mlp.setMargins(5,5,5,5);
                    but.setLayoutParams(mlp);


                    but.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(toggleButtonArray!=null && !toggleButtonArray.isEmpty()) {
                                int abc = 0;
                                for (int i = 0; i < toggleButtonArray.size(); i++) {
                                    if (toggleButtonArray.get(i).isChecked()) {
                                        abc = 1;
                                        break;
                                    }
                                }
                                    if (abc == 1) {
                                        createFilter.setVisibility(View.VISIBLE);

                                    } else {
                                        createFilter.setVisibility(View.GONE);
                                    }

                            }
                        }
                    });

                    toggleButtonArray.add(but);


                }
                    if(toggleButtonArray!=null && !toggleButtonArray.isEmpty()) {
                        for (int i = 1; i < toggleButtonArray.size() + 1; i++) {
                            if (i % 2 == 0) {
                                layoright.addView(toggleButtonArray.get(i - 1));
                            } else {
                                layoleft.addView(toggleButtonArray.get(i - 1));
                            }
                        }
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Main m=(Main)getActivity();
                StringBuilder selected=new StringBuilder();

                for(int i=0;i<toggleButtonArray.size();i++)
                {

                    if(toggleButtonArray.get(i).isChecked())
                    {
                        if(selected.length()==0){
                        selected.append(toggleButtonArray.get(i).getTextOn());
                        }
                        else
                        {
                            selected.append(","+toggleButtonArray.get(i).getTextOn());
                        }

                    }
                }
                m.comeWithMeIfYouWantToLive(podrucje.getSelectedItem().toString(),lokacija.getSelectedItem().toString(),selected.toString(),adresa.getText().toString());
                m.fff(selected.toString(),Integer.toString(podrucje.getSelectedItemPosition()+1),Integer.toString(lokacija.getSelectedItemPosition()+1));

            }
        });

        createFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shown.setVisibility(View.GONE);
                hidden.setVisibility(View.VISIBLE);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               StringBuilder temp=new StringBuilder();

                for(int i=0;i<toggleButtonArray.size();i++)
                {
                    if(toggleButtonArray.get(i).isChecked())
                    {
                        if(temp.length()==0)
                        {
                            temp.append(toggleButtonArray.get(i).getTextOn());
                        }
                        else
                        {
                            temp.append(","+toggleButtonArray.get(i).getTextOn());
                        }
                    }
                }

                db.newFilter(new Filter(edt.getText().toString(),temp.toString()));

                shown.setVisibility(View.VISIBLE);
                hidden.setVisibility(View.GONE);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText("");
                shown.setVisibility(View.VISIBLE);
                hidden.setVisibility(View.GONE);
            }
        });

		return c;
	}

    public interface chaplin
    {
        public void comeWithMeIfYouWantToLive(String podrucje,String lokacija,String tagovi,String adresa);
    }
	
	public static ArrayList<ToggleButton> getTagList()
    {
        return toggleButtonArray;
    }
    public static int getPosition()
    {
        return sendingInt;

    }
}
