package co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 15/09/2024 09:40
@Last Modified 15/09/2024 09:40
Version 1.0
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class StatementAlgorithmDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("id_type_request_eq")
    private int id_type_request_eq;

    @JsonProperty("type")
    private String type;

    @JsonProperty("logic")
    private String logic;

    @JsonProperty("status")
    private int status;

    @JsonProperty("command")
    private String command;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("updated_at")
    private Timestamp updatedAt;
}
