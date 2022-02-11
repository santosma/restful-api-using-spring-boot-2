package com.demo.springboot2restapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_details")
    private String orderDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
}
