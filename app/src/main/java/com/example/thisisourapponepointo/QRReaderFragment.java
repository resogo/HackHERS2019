package com.example.thisisourapponepointo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.vision.barcode.BarcodeDetector;


public class QRReaderFragment extends Fragment {
    private TextView barcodeValue;
    Context context;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.qrcode_reader_layout, container, false);
        super.onCreate(savedInstanceState);
        context = getContext();
        barcodeValue = (TextView)rootView.findViewById(R.id.display_scanned_info);
        //rootView.findViewById(R.id.capture_qrcode).setOnClickListener();
        //BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(context).build();
        return rootView;
    }
}
