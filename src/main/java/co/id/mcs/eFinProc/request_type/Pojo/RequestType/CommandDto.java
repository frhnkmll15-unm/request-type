package co.id.mcs.eFinProc.request_type.Pojo.RequestType;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 30/09/2024 14:37
@Last Modified 30/09/2024 14:37
Version 1.0
*/

import lombok.Data;
@Data
public class CommandDto {
    private String type;
    private String logic;
    private int status;
    private String command;

}
