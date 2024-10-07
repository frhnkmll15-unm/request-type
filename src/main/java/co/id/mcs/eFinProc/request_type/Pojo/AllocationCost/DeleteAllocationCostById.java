package co.id.mcs.eFinProc.request_type.Pojo.AllocationCost;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 14:13
@Last Modified 13/09/2024 14:13
Version 1.0
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class DeleteAllocationCostById {
    private int id;
    private int status;
    @JsonProperty("updated_at")
    private Timestamp updated_at;
}