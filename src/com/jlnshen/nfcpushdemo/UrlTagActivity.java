package com.jlnshen.nfcpushdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

public class UrlTagActivity extends Activity {
	
	String url;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.main);
        
        TextView text = (TextView) findViewById(R.id.mytext);
        
        Intent intent = getIntent();
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        
        if(rawMsgs!=null) {
        	for(Parcelable p:rawMsgs) {
        		NdefMessage msg = (NdefMessage)p;
        		NdefRecord recs[] = msg.getRecords();
        		
        		if(recs!=null && recs.length>0) {
        			url = new String(msg.getRecords()[0].getPayload());
        			text.setText(url);
        			break;
        		}
        	}
        }
        
        if(url!=null) {
	        Intent viewIntent = new Intent();
	        viewIntent.setAction(Intent.ACTION_VIEW);
	        viewIntent.setData(Uri.parse(url));
	        startActivity(viewIntent);
        }
	}

}
