package com.example.thisisourapponepointo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRReaderFragment extends android.app.Fragment {
    private TextView barcodeValue;
    Context context;
    private String toast;
    private String info;
    private String userEmail;
    private String userName;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.qrcode_reader_layout, container, false);
        super.onCreate(savedInstanceState);
        context = rootView.getContext();
        barcodeValue = (TextView) rootView.findViewById(R.id.display_scanned_info);
        userEmail = "";
        userName = "";
        Button scan = (Button) rootView.findViewById(R.id.capture_qrcode);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanFromFragment();
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        displayToast();
    }

    public void scanFromFragment() {
        IntentIntegrator.forFragment(this).initiateScan();
    }

    private void displayToast() {
        if(getActivity() != null && toast != null) {
            Toast.makeText(getActivity(), toast, Toast.LENGTH_LONG).show();
            barcodeValue.setText(userEmail);
            toast = null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                toast = "Cancelled from fragment";
            } else {
                toast = "Scanned from fragment";
                info = result.getContents();
                parseBarcodeData();
                String success = "Successfully scanned "+userName+". Email: " + userEmail;
                barcodeValue.setText(success);
            }

            // At this point we may or may not have a reference to the activity
            displayToast();
        }
    }
    public void parseBarcodeData(){
        userEmail = info.substring(info.indexOf("EMAIL: ")+7);
        userName = info.substring(info.indexOf("NAME: ")+6, info.indexOf("EMAIL: "));
    }
}
