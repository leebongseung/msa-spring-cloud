package com.example.catalogservice.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.util.Date;

@Data
public class CatalogDto implements Serializable { // 직렬화 하는 이유 : 객체를 다른 서버로 전송하기 위해서
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;
}
