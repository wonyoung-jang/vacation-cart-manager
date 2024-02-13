package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vacations")
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_id", nullable = false)
    private Long id;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String image_URL;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @Column(name = "travel_fare_price")
    private BigDecimal travel_price;

    @Column(name = "vacation_title")
    private String vacation_title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vacation", fetch = FetchType.LAZY)
    private Set<Excursion> excursions;
}
