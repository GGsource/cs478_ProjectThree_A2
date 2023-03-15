package edu.uic.cs478.s2023.cs478_projectthree_a2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    BroadcastReceiver receiver;
    final String PROJECT_PERM = "edu.uic.cs478.spring23.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        IntentFilter receiverFilter = new IntentFilter();
        receiverFilter.addAction(PROJECT_PERM);

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
//                TODO: Check if who we received has the right permission
                String chosenCity = intent.getStringExtra("sentCity");
                Toast.makeText(context, "Received Broadcast: " + chosenCity, Toast.LENGTH_SHORT).show();
            }
        };

        registerReceiver(receiver, receiverFilter);

    }

    @Override
    protected void onDestroy() {
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
        super.onDestroy();
    }
}