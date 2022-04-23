package com.poa.POAvanzados.Model.ItemModel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public abstract class ItemState {

    private int state;
    private String name;
}
