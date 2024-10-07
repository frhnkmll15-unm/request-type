package co.id.mcs.eFinProc.request_type.Pojo.AllocationCost;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 14:16
@Last Modified 13/09/2024 14:16
Version 1.0
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class AllocationCostDto {

    private int id;

    @JsonProperty("id_type_request_eq")
    private int id_type_request_eq;

    @JsonProperty("id_product")
    private String id_product;

    @JsonProperty("id_departement")
    private String id_department;

    @JsonProperty("name_departement")
    private String name_departement;

    private int type;
    private int status;

    @JsonProperty("allocation_id_proyek")
    private String allocation_id_project;

    @JsonProperty("persentase")
    private int percentase;
    @JsonProperty("description")
    private String description;

    @JsonProperty("created_at")
    private Timestamp created_at;

    @JsonProperty("updated_at")
    private Timestamp updated_at;

}
