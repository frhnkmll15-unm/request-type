package co.id.mcs.eFinProc.request_type.Pojo.AllocationCost;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UpdateAllocationCostById {

    private int id;
    private int id_type_request_eq;
    private int type;
    private int status;
    private int percentase;
    private String description;

    @JsonProperty("id_product")
    private String id_product;

    @JsonProperty("id_departement")
    private String id_departement;

    @JsonProperty("name_departement")
    private String name_departement;

    @JsonProperty("allocation_id_proyek")
    private String allocation_id_project;

    @JsonProperty("updated_at")
    private Timestamp updated_at;

    @JsonProperty("created_at")
    private Timestamp created_at;
}
