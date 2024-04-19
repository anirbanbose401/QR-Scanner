package com.example.e_summit_scanner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private Button scanButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        scanButton = findViewById(R.id.scanner);
        resultTextView = findViewById(R.id.text);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQRScanner();
            }
        });
    }

    private void startQRScanner() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setPrompt("Scan a QR Code");
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && result.getContents() != null) {
            resultTextView.setText(result.getContents());
        }
    }
}
