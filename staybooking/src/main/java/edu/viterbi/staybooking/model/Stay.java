package edu.viterbi.staybooking.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stay")
@JsonDeserialize(builder = Stay.Builder.class) // @JsonDeserialize makes sure the Jackson library will use the Builder class to convert JSON format data to the Stay object
public class Stay implements Serializable { // implements Serializable: indicates that the Stay class can be serialized using Java's standard serialization mechanism. It requires the class to define a serialVersionUID.
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String address;
    @JsonProperty("guest_number") // makes sure to map guestNumber field to the guest_number key in JSON format data
    private int guestNumber;
    @ManyToOne
    @JoinColumn(name = "user_id") //  specifies the column in the stay table that is a foreign key to the user table. The column name in the table is "user_id"
    private User host;
    @OneToMany(mappedBy = "stay", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<StayImage> images;

    /*
    Defines a one-to-many relationship from Stay to StayReservedDate.
    The mappedBy attribute indicates the property in the StayReservedDate class that owns the relationship.
    cascade = CascadeType.ALL means that persistence actions such as persist, remove, refresh, merge, etc., should be cascaded from Stay to StayReservedDate.
    fetch = FetchType.LAZY indicates that StayReservedDate should be loaded on-demand rather than eagerly when the Stay entity is loaded
     */
    @JsonIgnore // makes sure don’t return reserved date information when returning the stay information in JSON format because
    @OneToMany(mappedBy = "stay", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<StayReservedDate> reservedDates;

    public Stay() {}

    private Stay(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.address = builder.address;
        this.guestNumber = builder.guestNumber;
        this.host = builder.host;
        this.reservedDates = builder.reservedDates;
        this.images = builder.images;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public User getHost() {
        return host;
    }

    public List<StayReservedDate> getReservedDates() {
        return reservedDates;
    }

    public List<StayImage> getImages() {
        return images;
    }

    public Stay setImages(List<StayImage> images) {
        this.images = images;
        return this;
    }

    public static class Builder {

        @JsonProperty("id")
        private Long id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("description")
        private String description;

        @JsonProperty("address")
        private String address;

        @JsonProperty("guest_number")
        private int guestNumber;

        @JsonProperty("host")
        private User host;

        @JsonProperty("images")
        private List<StayImage> images;

        @JsonProperty("dates")
        private List<StayReservedDate> reservedDates;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setGuestNumber(int guestNumber) {
            this.guestNumber = guestNumber;
            return this;
        }

        public Builder setHost(User host) {
            this.host = host;
            return this;
        }

        public Builder setReservedDates(List<StayReservedDate> reservedDates) {
            this.reservedDates = reservedDates;
            return this;
        }
        public Builder setImages(List<StayImage> images) {
            this.images = images;
            return this;
        }
        public Stay build() {
            return new Stay(this);
        }
    }
}
