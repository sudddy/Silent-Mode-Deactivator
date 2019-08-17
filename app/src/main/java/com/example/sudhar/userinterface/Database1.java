package com.example.sudhar.userinterface;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;

import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class Database1 extends Fragment implements View.OnClickListener {


    Button save, load;
    ImageButton ph1,ph2;
    EditText phno1, phno2, msg;
    DataHandler handler;
    ProgressDialog progressBar;
    Integer min=10,max=14;

    private static final int RESULT_PICK_CONTACT = 85500;
    private static final int RESULT_PICK_CONTACT1 = 85501;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout1 = inflater.inflate(R.layout.activity_database1, container, false);
        save = (Button) layout1.findViewById(R.id.button1);
        load=(Button)layout1.findViewById(R.id.button2);


        ph1 = (ImageButton) layout1.findViewById(R.id.button3);
        ph2 = (ImageButton) layout1.findViewById(R.id.button4);
        phno1 = (EditText) layout1.findViewById(R.id.editText1);
        phno2 = (EditText) layout1.findViewById(R.id.editText2);
        msg = (EditText) layout1.findViewById(R.id.editText3);


        save.setOnClickListener(new Button.OnClickListener()

        {
            @Override
            public void onClick(final View v) {
                String getNum1 = phno1.getText().toString();
                String getNum2 = phno2.getText().toString();
                String usermsg = msg.getText().toString();

                if(phno1.getText().toString().length()==0 ||((phno1.getText().toString().length()!=10) && (phno1.getText().toString().length()!=13 )) ) {
                    phno1.setError("Enter Proper Phone Number");
                    Toast.makeText(getActivity().getBaseContext(), "Enter Proper Phone Number", Toast.LENGTH_LONG).show();
                }


                else if( phno2.getText().toString().length()==0 ||((phno2.getText().toString().length()!=10) && (phno2.getText().toString().length()!=13 )))
                {
                    phno2.setError("Enter Proper Phone Number");
                    Toast.makeText(getActivity().getBaseContext(), "Enter Proper Phone Number", Toast.LENGTH_LONG).show();
                }

                else if(msg.getText().toString().length()==0){
                    msg.setError("Text is Required");
                    Toast.makeText(getActivity().getBaseContext(),"Please Enter the Text",Toast.LENGTH_LONG).show();
                }



                else {

                    final ProgressDialog myPd_ring=ProgressDialog.show(v.getContext(), "Please wait", "Loading please wait..", true);
                    myPd_ring.setCancelable(true);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try
                            {
                                Thread.sleep(2000);
                            }catch(Exception e){}
                            myPd_ring.dismiss();
                        }
                    }).start();


                    handler = new DataHandler(getActivity().getBaseContext());
                    handler.open();
                    if(!getNum1.startsWith("+91")) {
                        getNum1="+91"+getNum1;
                    }
                    if(!getNum2.startsWith("+91")) {
                        getNum2="+91"+getNum2;
                    }
                    long id = handler.insertData(getNum1, getNum2, usermsg);

                    Toast.makeText(getActivity().getBaseContext(), "data inserted", Toast.LENGTH_LONG).show();
                    handler.close();


                }

            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)

            {
                String getnum1, getnum2, msgnum;
                getnum1 = "";
                getnum2 = "";
                msgnum = "";

                handler = new DataHandler(getActivity().getBaseContext());
                handler.open();
                Cursor c = handler.returndata();
                if (c.moveToFirst()) {
                    do {
                        getnum1 = c.getString(0);
                        getnum2 = c.getString(1);
                        msgnum = c.getString(2);

                    } while (c.moveToNext());
                }
                if(getnum1!="" && getnum2!="" && msgnum!="")
                {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Number 1:\t\t\t" + getnum1 + "\n\nNumber 2:\t\t\t" + getnum2 + "\n\nMessage:\t\t\t\t" + msgnum)
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("View Stored data");
                alert.show();
            }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Please Store the PhoneNumber and Text.")
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Stored data");
                    alert.show();


                }

                // Toast.makeText(getActivity().getBaseContext(), "Number 1:" + getnum1 + " Number 2:" + getnum2 + "MSG:" + msgnum, Toast.LENGTH_LONG).show();
                handler.close();


            }


        });
        ph1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v)

            {
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(i,0);



            }


        });

        ph2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v)

            {

                Intent i1 = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(i1,1);

            }


        });

        return layout1;
    }

    private void load() {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == getActivity().RESULT_OK) {
            // Check for the request code, we might be usign multiple startActivityForReslut
            switch (requestCode) {
                case 0:
                    contactPicked(data);
                    break;
                case 1:
                    contactPicked1(data);
                    break;
            }
        }
        else {
            Log.e("MainActivity", "Failed to pick contact");
        }

    }


    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null;

            String a, finalno;
            // getData() method will have the Content Uri of the selected contact
            Uri uri = data.getData();
            //Query the content uri
            cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            // column index of the phone number
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            // column index of the contact name

            phoneNo = cursor.getString(phoneIndex);
            finalno = phoneNo.toString().replace("-", "");

            if (finalno.startsWith("+91")) {
                phno1.setText(finalno);
            } else if (finalno.startsWith("0")) {
                a = finalno.substring(1);
                a = "+91".concat(a);
                phno1.setText(a);
            } else {
                a = "+91".concat(finalno);
                phno1.setText(a);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void contactPicked1(Intent data) {
        Cursor cursor1 = null;
        try {
            String phoneNo1 = null;

            String a1, finalno1;
            // getData() method will have the Content Uri of the selected contact
            Uri uri1 = data.getData();
            //Query the content uri
            cursor1 = getActivity().getContentResolver().query(uri1, null, null, null, null);
            cursor1.moveToFirst();
            // column index of the phone number
            int phoneIndex1 = cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            // column index of the contact name

            phoneNo1 = cursor1.getString(phoneIndex1);
            finalno1 = phoneNo1.toString().replace("-", "");
            if (finalno1.startsWith("+91")) {
                phno2.setText(finalno1);
            } else if (finalno1.startsWith("0")) {
                a1 = finalno1.substring(1);
                a1 = "+91".concat(a1);
                phno2.setText(a1);
            } else {
                a1 = "+91".concat(finalno1);
                phno2.setText(a1);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }









    @Override
    public void onClick(View v) {



    }
}
