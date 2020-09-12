package com.assignment03.myapplication.ui.main;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.assignment03.myapplication.R;

public class BildeListeFragment extends ListFragment {
    private String[] bildeTitel;


    private MainViewModel mViewModel;

    public static BildeListeFragment newInstance() {
        return new BildeListeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Henter resurser fra strings.xml
        Resources res=getResources();
        bildeTitel=res.getStringArray(R.array.bildeNavn);
        //lager array med data hentet fra string.xml
        setListAdapter(new ArrayAdapter<String>(getActivity(),R.layout.main_activity,R.id.main_fragment_bilde_Liste,bildeTitel));
        Log.i("bildeListeFragment", "onCreate: ");

        //todo flytt til onCreateView
        /*
        //Legger til navnvene på biledne og gjør de kikkbare
        View v=res.(R.layout.main_fragment,container,false);
        LinearLayout layout=(LinearLayout)v.findViewById(R.id.fragment_LinearLayout);
        LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);



        for (int i = 0; i <bildeTitel.length ; i++) {
            TextView textView=(TextView)v.findViewById(R.id.fragment_message);
            //textView.setLayoutParams(params);
            textView.setText(bildeTitel[i]);
            final int nr=i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("mainFragment", "onClick:bilde nr "+nr);
                }
            });
        }
        container.addView(v);

         */
    }


    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.i(this.getClass().getSimpleName(), "onListItemClick "+id);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(this.getClass().getSimpleName(), "onActivityCreated ");
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }


}