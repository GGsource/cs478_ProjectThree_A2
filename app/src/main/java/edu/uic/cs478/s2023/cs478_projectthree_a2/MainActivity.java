package edu.uic.cs478.s2023.cs478_projectthree_a2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    final String PROJECT_PERM = "edu.uic.cs478.spring23.mp3";
    BroadcastReceiver pReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter receiverFilter = new IntentFilter(PROJECT_PERM);
        pReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String chosenCity = intent.getStringExtra("sentCity");
                Toast.makeText(context, "Received Broadcast: " + chosenCity, Toast.LENGTH_SHORT).show();
                if (Objects.equals(chosenCity, "Orlando")) {
//                    TODO: Take us to Orlando Activity!
                    Toast.makeText(context, "Launching Orl Activity!", Toast.LENGTH_SHORT).show();
                } else { //Only other possibility is NY
//                    TODO: Take us to New York Activity!
                    Toast.makeText(context, "Launching NY activity!", Toast.LENGTH_SHORT).show();
                }
            }
        };
        registerReceiver(pReceiver, receiverFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(pReceiver);
    }
}