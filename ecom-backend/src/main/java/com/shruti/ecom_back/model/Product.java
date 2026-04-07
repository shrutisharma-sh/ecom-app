package com.shruti.ecom_back.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false , precision = 10 ,scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean inStock=true;

    @Column(nullable = false)
    private String imageUrl="";

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

}
