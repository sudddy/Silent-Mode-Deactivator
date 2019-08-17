package com.example.sudhar.userinterface;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Database2 extends Fragment implements View.OnClickListener {
    EditText msg11,msg12;
    Button save,load;
    DataHandlerOne handler1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout1 = inflater.inflate(R.layout.activity_database2, container, false);



        msg11=(EditText)layout1.findViewById(R.id.edit);
       // msg12=(EditText)layout1.findViewById(R.id.editText4);
        save=(Button)layout1.findViewById(R.id.button1);
        load=(Button)layout1.findViewById(R.id.button2);

        save.setOnClickListener(new Button.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                String getMsg1 = msg11.getText().toString();
                String getMsg2 = "1234";
                if(msg11.getText().toString().length()==0){
                    msg11.setError("Text is Required");
                    Toast.makeText(getActivity().getBaseContext(),"Please Enter the Text",Toast.LENGTH_LONG).show();
                }
                else
                {
                    final ProgressDialog myPd_ring=ProgressDialog.show(v.getContext(), "Please wait", "Loading please wait..", true);
                    myPd_ring.setCancelable(true);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            try
                            {
                                Thread.sleep(2000);
                            }catch(Exception e){}
                            myPd_ring.dismiss();
                        }
                    }).start();

                    handler1 = new DataHandlerOne(getActivity().getBaseContext());
                    handler1.open();
                    long id=handler1.insertData(getMsg1,getMsg2);

                    Toast.makeText(getActivity().getBaseContext(), "data inserted", Toast.LENGTH_LONG).show();
                    handler1.close();

                }


            }
        });
        load.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v)

            {   String message1,message2;
                message1="";
                message2="";

                handler1 = new DataHandlerOne(getActivity().getBaseContext());
                handler1.open();
                Cursor c1=handler1.returndata();
                if(c1.moveToFirst())
                {
                    do
                    {
                        message1=c1.getString(0);
                        message2=c1.getString(1);


                    }while(c1.moveToNext());
                }
                if(message1!="" && message2!="") {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Message:\t\t\t" + message1)
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
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Please Store the Text.")
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


             //   Toast.makeText(getActivity().getBaseContext(),"Free Text:" +message1,Toast.LENGTH_LONG).show();
                handler1.close();



            }



        });
        return layout1;

    }

    @Override
    public void onClick(View v) {

    }
}
