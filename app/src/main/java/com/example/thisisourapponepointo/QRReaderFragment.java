package com.example.thisisourapponepointo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import com.google.android.gms.vision.barcode.BarcodeDetector;


public class QRReaderFragment extends Fragment {
    private TextView barcodeValue;
    Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        setContentView(R.layout.qrcode_reader_layout);
        barcodeValue = (TextView)findViewById(R.id.display_scanned_info);
        findViewById(R.id.capture_qrcode).setOnClickListener(context);
        //BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(context).build();

    }
}
