package co.id.mcs.eFinProc.request_type.RequestType;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 13:59
@Last Modified 13/09/2024 13:59
Version 1.0
*/


import co.id.mcs.eFinProc.request_type.Dao.RequestTypeDao;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.AllocationCostDto;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.RequestTypeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RequestTypeGetByIdTest {

    @Autowired
    RequestTypeDao requestTypeDao;

    @Test
    void getByIdTest() throws Exception {

        int id = 5;


        RequestTypeDto requestType = requestTypeDao.getById(id);

        if (requestType == null) {
            throw new Exception("Request- Type tidak ditemukan untuk ID: " + id);
        }

        System.out.println(requestType.toString());

    }
}
