package com.demo.springboot2restapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.transaction.support.ResourceHolderSupport;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "orders")
public class Order extends RepresentationModel {
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
