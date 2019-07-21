package com.example.a1751054_h01.data.model;

import java.util.List;

public class CinemaInfo {
    private String cineName;
    private List<Showtime> showtimes;

    public CinemaInfo (
            String cineName,
            List<Showtime> showtimes) {
        this.cineName = cineName;
        this.showtimes = showtimes;
    }

    public String getCinema() {
        return cineName;
    }

    public List<Showtime> getShowTime() {
        return showtimes;
    }
}
