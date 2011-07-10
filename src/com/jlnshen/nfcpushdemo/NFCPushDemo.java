package com.jlnshen.nfcpushdemo;


import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.widget.TextView;

public class NFCPushDemo extends Activity {
	String share = "";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TextView text = (TextView) findViewById(R.id.mytext);
        
        Intent intent = getIntent();
        share = intent.getExtras().getString(Intent.EXTRA_TEXT);
        text.setText(share);
        
        
    }

	@Override
	protected void onPause() {
		super.onPause();
		
		NfcManager manager = (NfcManager) getSystemService(NFC_SERVICE);
        manager.getDefaultAdapter().disableForegroundNdefPush(this);
	}

	@Override
	protected void onResume()  {
		super.onResume();
		
		NfcManager manager = (NfcManager) getSystemService(NFC_SERVICE);
        NdefRecord rec = new NdefRecord(NdefRecord.TNF_ABSOLUTE_URI, NdefRecord.RTD_URI, new byte[0],share.getBytes());
		manager.getDefaultAdapter().enableForegroundNdefPush(this, new NdefMessage(new NdefRecord[]{rec}));
	}
}