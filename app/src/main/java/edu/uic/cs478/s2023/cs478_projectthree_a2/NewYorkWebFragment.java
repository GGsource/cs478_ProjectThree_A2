package edu.uic.cs478.s2023.cs478_projectthree_a2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewYorkWebFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_york_web, container, false);

        int index = -1;
        Bundle data = getArguments();
        if (data != null) {
            index = data.getInt("siteIndex", -1);
        }
        WebView webber = v.findViewById(R.id.webFragNY);
        webber.setWebViewClient(new WebViewClient());
        WebSettings settings = webber.getSettings();
        settings.setJavaScriptEnabled(true);
        if (index != -1) {
            webber.loadUrl(ListValues.itemSitesNY[index]);
        } else {
            webber.loadUrl("https://www.google.com");
        }

        return v;
    }
}