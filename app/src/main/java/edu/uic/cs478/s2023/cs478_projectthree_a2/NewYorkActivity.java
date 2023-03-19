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

public class NewYorkActivity extends FragmentActivity {

    final NewYorkWebFragment webFragment = new NewYorkWebFragment();
    FragmentManager fragManager;
    FragmentContainerView listContainerNY, webContainerNY;

    SharedViewModel viewModel;
    Boolean isWebOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_york);

        listContainerNY = (FragmentContainerView) findViewById(R.id.fragListContainerNY);
        webContainerNY = (FragmentContainerView) findViewById(R.id.fragWebContainerNY);
        fragManager =  getSupportFragmentManager();

        final FragmentTransaction fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.fragListContainerNY, new NewYorkListFragment());
        fragTransaction.commit();

        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        viewModel.getSelectedItem().observe(this, item ->{
            if (!webFragment.isAdded()) {
                FragmentTransaction secondTransaction = fragManager.beginTransaction();
                secondTransaction.add(R.id.fragWebContainerNY, webFragment);
                secondTransaction.addToBackStack(null);
                secondTransaction.commit();
                fragManager.executePendingTransactions();
            }
        });
    }
    public void landscapeLayoutOpen() {
        listContainerNY.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
        webContainerNY.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2f));
        isWebOpen = true;
    }
    public void landscapeLayoutClose() {
        listContainerNY.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        webContainerNY.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT)); //Web now has 0 width, not visible.
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