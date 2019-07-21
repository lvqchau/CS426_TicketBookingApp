package com.example.a1751054_h01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends Activity {
    //for transparent status bar
//    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
//
//        Window win = activity.getWindow();
//        WindowManager.LayoutParams winParams = win.getAttributes();
//        if (on) {
//            winParams.flags |= bits;
//        } else {
//            winParams.flags &= ~bits;
//        }
//        win.setAttributes(winParams);
//    }

    TextView dayView2;
    TextView timeView2;
    TextView cinemaView2;
    TextView avail;
    TextView booked;
    TextView selected;
    TextView ticket;

    Button submit, button;
    List<List<Button>> mButton1 = new ArrayList<>();

    String mName[] = {"A", "B","C","D","E","F","G","H","I","J","K"};
    private StyleableToast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        //change color of status bar to transparent color/translucent
//        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
//            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
//        }
//        if (Build.VERSION.SDK_INT >= 19) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        }
//        //make fully Android Transparent Status bar
//        if (Build.VERSION.SDK_INT >= 21) {
//            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
//            getWindow().setStatusBarColor(Color.parseColor("#80002281"));
//        }

        //remove status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);


        submit = findViewById(R.id.left_arrow);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                submit.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_left_arrow_purple, 0, 0);
                Intent myIntent = new Intent(MainActivity2.this, MainActivity.class);
                //myIntent.putExtra("key", value);
                MainActivity2.this.startActivity(myIntent);
            }
        });

        button = findViewById(R.id.submit_button2);

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                int co=0;
                StringBuffer msgBuf = new StringBuffer();
                for (int k=0; k<11; k++) {
                    for (int z = 0; z < 10; z++) {
                        if (mButton1.get(k).get(z)!=null) {
                            if (mButton1.get(k).get(z).getBackground().getConstantState()==getResources().getDrawable(R.drawable.purpleseat).getConstantState()) {
                                co++;
                            }
                        }
                    }
                }
                if (arg1.getAction()==MotionEvent.ACTION_DOWN) {
                    button.setBackgroundResource(R.drawable.round_button_light);
                    if (co==0) {
                        msgBuf.append("Please choose at least one seat!");
                    }
                    else if (co==1) {
                        msgBuf.append("Successfully reserved " + co + " seat");
                    }
                    else {
                        msgBuf.append("Successfully reserved " + co + " seats");
                    }
                    showToast(msgBuf.toString());
                }
                else if (arg1.getAction()==MotionEvent.ACTION_UP) {

                    button.setBackgroundResource(R.drawable.round_button);
                }
                return false;
            }
        });

        initSeat2D();
        checkSeat2D();
        setViewforAll();
    }


    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void showToast(String message) {
        if (toast != null) {
            toast.cancel();
        }
        toast = StyleableToast.makeText(getApplicationContext(), message, R.style.toastStyle);
        toast.show();
    }

    public void initSeat2D() {
        for ( int i=0; i<11; i++) {
            mButton1.add(new ArrayList<Button>());
            for (int j=0; j<10; j++) {
                mButton1.get(i).add(null);
            }
        }


        for (int i=0; i<11; i++) {
            for (int j=0; j<10; j++) {
                String seatname  = "row" + mName[i] + (j+1); //init = row A 1
                System.out.println(seatname);
                int resID = getResources().getIdentifier(seatname, "id",  getPackageName());
                mButton1.get(i).set(j, (Button) findViewById(resID)); //init = get(0).set(0)
            }
        }
    }

    public void checkSeat2D() {
        for (int i=0; i<11; i++) {
            for (int j = 0; j < 10; j++) {
                if (mButton1.get(i).get(j)!=null) {
                    checkOnClick(i, j);
                }
            }
        }
    }
    public void checkOnClick(final int i,final int j) {
        mButton1.get(i).get(j).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeColorOfSeat2D(i,j);
            }
        });
    }

    public void changeColorOfSeat2D(final int i,final int j) {
        Button button = mButton1.get(i).get(j);
        TextView price = findViewById(R.id.price_total);
        TextView ticketco = findViewById(R.id.ticket_count);
        StringBuffer msgBuf = new StringBuffer();
        //check if seat chosen is over 7 seats
        int co = 1;
        int total=10;
        //your selection
        if (button.getBackground().getConstantState()==getResources().getDrawable(R.drawable.purpleseat).getConstantState()) {
            button.setBackgroundResource(R.drawable.grayseat);
            for (int k=0; k<11; k++) {
                for (int z = 0; z < 10; z++) {
                    if (mButton1.get(k).get(z)!=null) {
                        if (mButton1.get(k).get(z).getBackground().getConstantState()==getResources().getDrawable(R.drawable.purpleseat).getConstantState()) {
                            co++;
                        }
                    }
                }
            }
            co--;
            total = co*10;
            price.setText("$" + Integer.toString(total));
            ticketco.setText("x"+Integer.toString(co));
        }
        //booked
        else if (button.getBackground().getConstantState()==getResources().getDrawable(R.drawable.blackseat).getConstantState()) {
            button.setBackgroundResource(R.drawable.blackseat);
            msgBuf.append("Seat " + mName[i] + (j+1) + " is unavailable!");
            showToast(msgBuf.toString());
        }
        //available
        else if (button.getBackground().getConstantState()==getResources().getDrawable(R.drawable.grayseat).getConstantState()) {
            for (int k=0; k<11; k++) {
                for (int z = 0; z < 10; z++) {
                    if (mButton1.get(k).get(z)!=null) {
                        if (mButton1.get(k).get(z).getBackground().getConstantState()==getResources().getDrawable(R.drawable.purpleseat).getConstantState()) {
                            co++;
                        }
                    }
                }
            }
            if (co<11) {
                button.setBackgroundResource(R.drawable.purpleseat);
                msgBuf.append("Seat " + mName[i] + (j + 1) + " chosen");
                showToast(msgBuf.toString());
                total = co*10;
                price.setText("$" + Integer.toString(total));
                ticketco.setText("x"+Integer.toString(co));
            }
            else {
                msgBuf.append("Reserving limits to 10 seats only");
                showToast(msgBuf.toString());
            }
        }
    }



    public void setViewforAll() {
        dayView2 = (TextView) findViewById(R.id.day2);
        dayView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down2, 0);
        timeView2 = (TextView) findViewById(R.id.time2);
        timeView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down2, 0);
        cinemaView2 = (TextView) findViewById(R.id.cinema2);
        cinemaView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down2, 0);

        avail = (TextView) findViewById(R.id.available);
        avail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.graycircle, 0, 0, 0);
        booked = (TextView) findViewById(R.id.booked);
        booked.setCompoundDrawablesWithIntrinsicBounds(R.drawable.blackcircle, 0, 0, 0);
        selected = (TextView) findViewById(R.id.select);
        selected.setCompoundDrawablesWithIntrinsicBounds(R.drawable.purplecircle, 0, 0, 0);

        ticket = (TextView) findViewById(R.id.ticket_count);
        ticket.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_ticket, 0, 0, 0);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        outState.putBoolean("buttonClicked", true);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        if (savedInstanceState != null)
        {
            if (savedInstanceState.containsKey("buttonClicked"))
            {
                if (savedInstanceState.getBoolean("buttonClicked")) {
                    for (int k=0; k<11; k++) {
                        for (int z = 0; z < 10; z++) {
                            if (mButton1.get(k).get(z)!=null) {
                                if (mButton1.get(k).get(z).getBackground().getConstantState()==getResources().getDrawable(R.drawable.purpleseat).getConstantState()) {
                                    mButton1.get(k).get(z).setBackgroundResource(R.drawable.purpleseat);
                                }
                                else if (mButton1.get(k).get(z).getBackground().getConstantState()==getResources().getDrawable(R.drawable.grayseat).getConstantState()) {
                                    mButton1.get(k).get(z).setBackgroundResource(R.drawable.grayseat);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
