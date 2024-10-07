package co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 15/09/2024 10:07
@Last Modified 15/09/2024 10:07
Version 1.0
*/



import lombok.Data;


@Data
public class ParamSearchStatementAlgorithm  {
    private int status = 1;
    private Boolean isActive = true;
    private  int page = 1;
    private int limit  = 20;


}
