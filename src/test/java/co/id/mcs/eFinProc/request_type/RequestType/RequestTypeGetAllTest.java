package co.id.mcs.eFinProc.request_type.RequestType;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 13:58
@Last Modified 13/09/2024 13:58
Version 1.0
*/

import co.id.mcs.eFinProc.request_type.Dao.RequestTypeDao;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.ParamSearchRequestType;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.RequestTypeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RequestTypeGetAllTest {

    @Autowired
    RequestTypeDao requestTypeDao;

    @Test
    void contextLoads() {

        ParamSearchRequestType param = new ParamSearchRequestType();
        param.setStatus(1);
        List<RequestTypeDto> requestTypes = requestTypeDao.findAll(param);
        System.out.println(requestTypes.toString());
    }
}