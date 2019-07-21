package com.example.a1751054_h01.data;

import java.util.Date;
import java.util.List;

import com.example.a1751054_h01.data.model.MovieInfo;
import com.example.a1751054_h01.data.model.Showtime;

public class CinemaShowtimeRepository {
    private static CinemaShowtimeRepository instance;
    private DummyCinemaShowtimeDataSource dataSource;

    public CinemaShowtimeRepository(DummyCinemaShowtimeDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static CinemaShowtimeRepository getInstance(DummyCinemaShowtimeDataSource dataSource) {
        if (instance == null) {
            instance = new CinemaShowtimeRepository(dataSource);
        }
        return instance;
    }

    public List<Date> getListDates(Date startDate) {
        return this.dataSource.getListDates(startDate, 10);
    }

    public List<Showtime> getListShowtimes(Date startTime) {
        return this.dataSource.getListShowtimes(startTime, 8);
    }

    public MovieInfo getMovieInfo() {
        return this.dataSource.getMovieInfor();
    }
}
