package edu.uic.cs478.s2023.cs478_projectthree_a2;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class OrlandoActivity extends FragmentActivity {

    final OrlandoWebFragment webFragment = new OrlandoWebFragment();
    FragmentManager fragManager;
    FragmentContainerView listContainer, webContainer;

    OrlandoViewModel viewModel;
    Boolean isWebOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orlando);

        listContainer = (FragmentContainerView) findViewById(R.id.fragListContainer);
        webContainer = (FragmentContainerView) findViewById(R.id.fragWebContainer);
        fragManager =  getSupportFragmentManager();

        final FragmentTransaction fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.fragListContainer, new OrlandoListFragment());
        fragTransaction.commit();
//        Reset the layout every time something changes on backstack
//        fragManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                changeLayout();
//            }
//        });

        viewModel = new ViewModelProvider(this).get(OrlandoViewModel.class);
        viewModel.getSelectedItem().observe(this, item ->{
            if (!webFragment.isAdded()) {
                Log.d("WEBFRAG", "Web fragment not currently added, going to add.");
                FragmentTransaction secondTransaction = fragManager.beginTransaction();
                secondTransaction.add(R.id.fragWebContainer, webFragment);
                secondTransaction.addToBackStack(null);
                secondTransaction.commit();
                fragManager.executePendingTransactions();
            }
        });
//        changeLayout();
    }

//    public void changeLayout() {
//        if (!webFragment.isAdded()) {
////            Web Fragment is not currently on screen. Make List take up whole screen.
//            Toast.makeText(this, "Web fragment not added. Not resizing.", Toast.LENGTH_SHORT).show();
//            listContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            webContainer.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT)); //Web now has 0 width, not visible.
//        } else {
////            Web Fragment has been added! Make it take up 2/3rds.
//            listContainer.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
//            webContainer.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2f));
//        }
//    }
    public void landscapeLayoutOpen() {
        listContainer.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
        webContainer.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2f));
        isWebOpen = true;
    }
    public void landscapeLayoutClose() {
        listContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        webContainer.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT)); //Web now has 0 width, not visible.
        isWebOpen = false;
    }

    @Override
    public void onBackPressed() {
        if (isWebOpen) {
            landscapeLayoutClose();
        }
        super.onBackPressed();
    }
}