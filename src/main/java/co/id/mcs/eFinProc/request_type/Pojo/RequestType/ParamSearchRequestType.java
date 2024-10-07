package co.id.mcs.eFinProc.request_type.Pojo.RequestType;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 13:52
@Last Modified 13/09/2024 13:52
Version 1.0
*/
import lombok.Data;
import java.util.List;

@Data
public class ParamSearchRequestType {
    private int status = 1;
    private Boolean isActive = true;
    private int page = 1;
    private int limit = 20;
    private List<String> fields;

}
