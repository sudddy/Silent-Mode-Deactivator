package com.example.sudhar.userinterface;

/**
 * Created by Sudhar on 14-01-2016.
 */



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceive extends BroadcastReceiver {
    String fnum="",lnum="",msg="";
    String senderNum,ft;
    DataHandler handler;
    DataHandlerOne h1;
    final SmsManager sms;
    public SMSReceive() {
        this.sms = SmsManager.getDefault();
    }

    public void onReceive(Context context, Intent intent) {

        AudioManager am = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            try {
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (Object obj : pdusObj) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) obj);
                    String senderNum = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();
                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);
                    handler = new DataHandler(context);
                    handler.open();
                    Cursor c=handler.returndata();
                    if(c.moveToFirst())
                    {
                        do
                        {
                            fnum=c.getString(0);
                            lnum=c.getString(1);
                            msg=c.getString(2);

                        }while(c.moveToNext());
                    }
                    //handler.close();
                    h1 = new DataHandlerOne(context);
                    h1.open();
                    Cursor c1=h1.returndata();
                    if(c1.moveToFirst())
                    {
                        do
                        {
                            ft=c1.getString(0);


                        }while(c1.moveToNext());
                    }
                    //h1.close();

                    if ((message.endsWith(msg)&&(senderNum.equals(fnum)||senderNum.equals(lnum)))||(message.endsWith(ft)))
                    {
                        am.setRingerMode(2);
                    }
                    else {
                        am.setRingerMode(0);
                    }

                }
            } catch (Exception e) {
                Log.e("SmsReceiver", "Exception smsReceiver" + e);
            }
        }
    }
}
