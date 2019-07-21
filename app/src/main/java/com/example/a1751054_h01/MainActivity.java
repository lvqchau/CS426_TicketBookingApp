package com.example.a1751054_h01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;
import java.util.List;

import com.example.a1751054_h01.data.CinemaShowtimeRepository;
import com.example.a1751054_h01.data.DummyCinemaShowtimeDataSource;
import com.example.a1751054_h01.data.model.CinemaInfo;
import com.example.a1751054_h01.data.model.MovieInfo;
import com.example.a1751054_h01.data.model.Showtime;


public class MainActivity extends Activity {
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


    int date=0;
    Button button;
    Button showSypno;
    CardView cardInfo;
    TextView sypno;
    TextView movieName;
    TextView movieRating;
    TextView movieTime;
    TextView movieReso;
    TextView movieSypno;
    Button movieGenre1;
    Button movieGenre2;
    Button movieGenre3;

    ImageView imgCover;
    TextView starView;
    TextView clockView;
    TextView filmView;
    RecyclerView rDate;
    int mColor, mPosition;


    Calendar cal = Calendar.getInstance();
    int mDay;
    int mDow;
    String dow;
    //vars
    private ArrayList<String> dayText = new ArrayList<>();
    private ArrayList<String> day = new ArrayList<>();
    private ArrayList<CinemaInfo> cinema = new ArrayList<>();
    private ArrayList<Showtime> showTime = new ArrayList<>();
    private List<String> Genre = new ArrayList<>();


    public static final String[] cinemaArr = {
            "Galaxy Nguyen Du"
    };
    DummyCinemaShowtimeDataSource dataSource;
    CinemaShowtimeRepository dataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

//
//


        //for transparent status bar
//        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
//            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
//        }
//        if (Build.VERSION.SDK_INT >= 19) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        }
//        //make fully Android Transparent Status bar
//        if (Build.VERSION.SDK_INT >= 21) {
//            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
//            getWindow().setStatusBarColor(Color.parseColor("#000000"));
//        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        dataSource = DummyCinemaShowtimeDataSource.getInstance();
        dataRepository = CinemaShowtimeRepository.getInstance(dataSource);

        MovieInfo movie = dataRepository.getMovieInfo();
        Date startTime = new Date(0, 0, 0, 8, 30);
        List<Showtime> listTimes = dataRepository.getListShowtimes(startTime);


        button = findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button.setBackgroundResource(R.drawable.round_button_light);
                Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
                //myIntent.putExtra("key", value);
                MainActivity.this.startActivity(myIntent);
            }
        });

        showSypno = findViewById(R.id.button_sypno);
        cardInfo = findViewById(R.id.info_card);
        sypno = findViewById(R.id.sypnosis);


        setMovieInfo();
        setCinemaAndTimeInfo();
        setImg();
        getdataOfDay();


    }


    private void setMovieInfo() {
        MovieInfo movie = dataRepository.getMovieInfo();

        movieName = findViewById(R.id.nameOfFilm);
        movieRating = findViewById(R.id.stars);
        movieTime = findViewById(R.id.watch_time);
        movieReso = findViewById(R.id.reso);
        movieSypno = findViewById(R.id.sypnosis);
        movieGenre1 = findViewById(R.id.genre1);
        movieGenre2 = findViewById(R.id.genre2);
        movieGenre3 = findViewById(R.id.genre3);

        Genre = movie.getListGenres();
        movieGenre1.setText(Genre.get(0));
        movieGenre2.setText(Genre.get(1));
        movieGenre3.setText(Genre.get(2));

        movieName.setText(movie.getMovieTitle());
        movieRating.setText(Double.toString((movie.getVoteScore() / 10.0)));
        movieTime.setText(
                String.format(Locale.US,
                        "%s min",
                        Integer.toString(movie.getRuntime())
                ));
        movieReso.setText(movie.getMovieFormat());
        movieSypno.setText(movie.getSynopsis());
    }

    private void setImg() {
        imgCover = findViewById(R.id.imageView1);
        imgCover.setImageResource(R.drawable.cover);

        starView = findViewById(R.id.stars);
        starView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_star, 0, 0, 0);
        clockView = findViewById(R.id.watch_time);
        clockView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_clock, 0, 0, 0);
        filmView = findViewById(R.id.reso);
        filmView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_video, 0, 0, 0);
    }

    private void getdataOfDay() {

        for (int i = 0; i < 9; i++) {
            mDow = cal.get(Calendar.DAY_OF_WEEK);
            mDay = cal.get(Calendar.DAY_OF_MONTH);
            switch (mDow) {
                case 2:
                    dow = "MON";
                    break;
                case 3:
                    dow = "TUE";
                    break;
                case 4:
                    dow = "WED";
                    break;
                case 5:
                    dow = "THURS";
                    break;
                case 6:
                    dow = "FRI";
                    break;
                case 7:
                    dow = "SAT";
                    break;
                case 1:
                    dow = "SUN";
                    break;
            }
            String dayString = Integer.toString(mDay);
            dayText.add(dow);
            day.add(dayString);
            cal.add(Calendar.DAY_OF_WEEK, 1);
        }

        initRecyclerView();

    }
    private void setCinemaAndTimeInfo() {
        int x = 7, z = 15, w = 30;
        boolean available = false;
        String[] cinemaArr = {
                "CGV Hung Vuong",
                "CGV Su Van Hanh",
                "Lotte NowZone",
                "MegaGS Cao Thang",
                "Galaxy Nguyen Du",
                "Cinestar Nguyen Trai",
                "CGV Su Van Hanh",
                "Lotte NowZone"
        };
        Calendar calendar = Calendar.getInstance();
        Date startTime = new Date(0, 0, 0, 10, 15);
        for (int i = 0; i < cinemaArr.length; i++) {
            if (i % 2 == 0) {
                startTime = new Date(0, 0, 0, x, z);
            } else {
                startTime = new Date(0, 0, 0, x, w);
            }
            calendar.setTime(startTime);
            List<Showtime> listTimes = dataRepository.getListShowtimes(startTime);

            if (startTime.getMinutes() <= 15) {
                available = false;
            } else {
                available = true;
            }

            cinema.add(new CinemaInfo(cinemaArr[i], listTimes));
            showTime.add(new Showtime(calendar.getTime(), available));

            if (i % 2 == 0) {
                x = 6;
            }
            x++;
            z += 15;
            w += 30;
        }
        initRecyclerViewCinema();
    }


    private void initRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        Adapter adapter = new Adapter(this, dayText, day);
        recyclerView.setAdapter(adapter);
    }

    private void initRecyclerViewCinema() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewCinema);
        recyclerView.setLayoutManager(layoutManager);
        AdapterCinema adapter = new AdapterCinema(cinema, this);
        recyclerView.setAdapter(adapter);
    }


    public void openSypno(View view) {

        RelativeLayout.LayoutParams cardLayout = (RelativeLayout.LayoutParams) cardInfo.getLayoutParams();
        ViewGroup.LayoutParams sypnoLayout = sypno.getLayoutParams();
        if (showSypno.getCompoundDrawables()[1].getConstantState() == getResources().getDrawable(R.drawable.ic_down).getConstantState()) {
            cardLayout.height = 975;
            sypnoLayout.height = 415;
            showSypno.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_up, 0, 0);
        } else {
            cardLayout.height = 800;
            sypnoLayout.height = 240;
            showSypno.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_down, 0, 0);
        }
    }
}

