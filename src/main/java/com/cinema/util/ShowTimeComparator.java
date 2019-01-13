package com.cinema.util;

import com.cinema.model.entity.ShowTime;

import java.util.Comparator;

/**
 * ShowTime comparator class
 *
 * @author Anton Spasskikh
 */
public class ShowTimeComparator implements Comparator<ShowTime> {

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(ShowTime s1, ShowTime s2) {
        if (s1.getDate().isEqual(s2.getDate()))
            return s1.getTimeSlot().getFrom().compareTo(s2.getTimeSlot().getFrom());
        return s1.getDate().compareTo(s2.getDate());
    }
}
