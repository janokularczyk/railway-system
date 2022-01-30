package com.railwayproject.connection;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "connections")
public class Connection {

    @SequenceGenerator(
            name = "connection_sequence",
            sequenceName = "connection_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "connection_sequence"
    )
    private Long id;
    @Column(name = "station_from")
    private String from;
    private LocalDateTime departure;
    private Integer platform;
    @Column(name = "station_to")
    private String to;
    private LocalDateTime arrival;
    private String company;
    private Double price;

    public Connection() {
    }

    public Connection(String from,
                      LocalDateTime departure,
                      Integer platform,
                      String to,
                      LocalDateTime arrival,
                      String company,
                      Double price) {
        this.from = from;
        this.departure = departure;
        this.platform = platform;
        this.to = to;
        this.arrival = arrival;
        this.company = company;
        this.price = price;
    }

    public Connection(Long id,
                      String from,
                      LocalDateTime departure,
                      Integer platform,
                      String to,
                      LocalDateTime arrival,
                      String company,
                      Double price) {
        this.id = id;
        this.from = from;
        this.departure = departure;
        this.platform = platform;
        this.to = to;
        this.arrival = arrival;
        this.company = company;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", departure=" + departure +
                ", platform=" + platform +
                ", to='" + to + '\'' +
                ", arrival=" + arrival +
                ", company='" + company + '\'' +
                ", price=" + price +
                '}';
    }
}
