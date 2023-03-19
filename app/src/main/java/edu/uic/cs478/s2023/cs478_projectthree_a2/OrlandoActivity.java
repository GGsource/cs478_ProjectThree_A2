package edu.uic.cs478.s2023.cs478_projectthree_a2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class OrlandoActivity extends AppCompatActivity {

    final OrlandoWebFragment webFragment = new OrlandoWebFragment();
    FragmentManager fragManager;
    FragmentContainerView listContainerOrlando, webContainerOrlando;

    SharedViewModel viewModel;
    Boolean isWebOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orlando);

        listContainerOrlando = (FragmentContainerView) findViewById(R.id.fragListContainerOrlando);
        webContainerOrlando = (FragmentContainerView) findViewById(R.id.fragWebContainerOrlando);
        fragManager =  getSupportFragmentManager();

        final FragmentTransaction fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.fragListContainerOrlando, new OrlandoListFragment());
        fragTransaction.commit();
//        Reset the layout every time something changes on backstack
//        fragManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                changeLayout();
//            }
//        });

        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        viewModel.getSelectedItem().observe(this, item ->{
            if (!webFragment.isAdded()) {
                Log.d("WEBFRAG", "Web fragment not currently added, going to add.");
                FragmentTransaction secondTransaction = fragManager.beginTransaction();
                secondTransaction.add(R.id.fragWebContainerOrlando, webFragment);
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
        listContainerOrlando.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
        webContainerOrlando.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2f));
        isWebOpen = true;
    }
    public void landscapeLayoutClose() {
        listContainerOrlando.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        webContainerOrlando.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT)); //Web now has 0 width, not visible.
        isWebOpen = false;
    }

    @Override
    public void onBackPressed() {
        if (isWebOpen) {
            landscapeLayoutClose();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_a2, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemNY:
                Intent i = new Intent(this,NewYorkActivity.class);
                this.startActivity(i);
                return true;
            case R.id.itemOrlando:
                Intent i2 = new Intent(this,OrlandoActivity.class);
                this.startActivity(i2);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}