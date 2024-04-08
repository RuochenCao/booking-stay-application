package edu.viterbi.staybooking.model;

import java.time.LocalDate;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


// this class is used as the primary key of the StayReservedDate table
@Embeddable // Embeddable classes are classes that do not have their own table in the database but are instead embedded in other entity classes.
public class StayReservedDateKey implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long stay_id;
    private LocalDate date;

    public StayReservedDateKey() {}

    public StayReservedDateKey(Long stay_id, LocalDate date) {
        this.stay_id = stay_id;
        this.date = date;
    }

    public Long getStay_id() {
        return stay_id;
    }

    public StayReservedDateKey setStay_id(Long stay_id) {
        this.stay_id = stay_id;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public StayReservedDateKey setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StayReservedDateKey that = (StayReservedDateKey) o;
        return stay_id.equals(that.stay_id) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stay_id, date);
    }


}
