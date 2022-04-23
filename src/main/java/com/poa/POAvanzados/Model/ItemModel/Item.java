package com.poa.POAvanzados.Model.ItemModel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Item {
    // Product Attributes
    private int idItem;
    private String name;
    private boolean critical;
}
