package com.poa.POAvanzados.Model.WorkplaceModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class Workplace {

    @Id
    private int idWorkplace;
    private boolean warehouse;
    private int idManager;
    private String address;

    public Workplace(int idWorkplace, boolean warehouse, int idManager, String address) {
        this.idWorkplace = idWorkplace;
        this.warehouse = warehouse;
        this.idManager = idManager;
        this.address = address;
    }

}
