package com.example.broadcastproj;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.text.BreakIterator;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5;
    Button b1;
    
    public static Intent BROADCAST_ACTION;
    private BroadcastReceiver localListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = ( TextView)findViewById(R.id.t1);
        t2 = ( TextView)findViewById(R.id.t2);
        t3 = ( TextView)findViewById(R.id.t3);
        t4 = ( TextView)findViewById(R.id.t4);
        t5 = ( TextView)findViewById(R.id.t5);
        b1 = ( Button)findViewById(R.id.b1);


    }


    @Override
    protected void onStart() {
        super.onStart();

        Receiver localListener = new Receiver();
        IntentFilter intentFilter = new IntentFilter(String.valueOf(BROADCAST_ACTION));
        this.registerReceiver(localListener,intentFilter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(localListener);
    }

    public void onclick(View view) {
        if(view.getId() == R.id.btnStartBroadcast){
           BackgroundServices.StartAction(this.getApplicationContext());
        }
    }
    public class Receiver extends BroadcastReceiver {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onReceive(Context context, Intent intent){
            BreakIterator txtViewMsg = null;
            String currentText = txtViewMsg.getText().toString();
            String message = intent.getStringExtra("value");
            currentText += "\nReceived " +message;
            txtViewMsg.setText(currentText);
        }
    }

}
