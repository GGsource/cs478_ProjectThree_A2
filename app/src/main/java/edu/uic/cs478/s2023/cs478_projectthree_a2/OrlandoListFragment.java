package edu.uic.cs478.s2023.cs478_projectthree_a2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class OrlandoListFragment extends Fragment {

    ListView orlandoList;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_orlando_list, container, false);

        orlandoList = v.findViewById(R.id.orlandoList);
        list = new ArrayList<>(Arrays.asList(ListValues.itemTitles));
        adapter = new ArrayAdapter<>(container.getContext(), android.R.layout.simple_list_item_1, list);
        orlandoList.setAdapter(adapter);

        orlandoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tV = (TextView) view.findViewById(view.getId());
                Toast.makeText(view.getContext(), "You clicked: " + tV.getText().toString() + "\n index: " + i , Toast.LENGTH_SHORT).show();
//                Now we know the index of the site to show. Switch the fragment to web view.
//                First Bundle the index for sending
                Bundle data = new Bundle();
                data.putInt("siteIndex",i);
//                Now prepare to switch fragments
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                OrlandoWebFragment newFrag = new OrlandoWebFragment();
                newFrag.setArguments(data);
                transaction.replace(R.id.fragContainer, newFrag, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("webFrag")
                        .commit();
            }
        });

        return v;
    }
}