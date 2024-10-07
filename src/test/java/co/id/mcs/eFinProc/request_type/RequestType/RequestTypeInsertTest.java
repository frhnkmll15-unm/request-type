package co.id.mcs.eFinProc.request_type.RequestType;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 13:56
@Last Modified 13/09/2024 13:56
Version 1.0
*/


import co.id.mcs.eFinProc.request_type.Dao.RequestTypeDao;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.RequestTypeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RequestTypeInsertTest {

    @Autowired
    RequestTypeDao requestTypeDao;

    @Test
    void contextLoads() {

        RequestTypeDto Dto = new RequestTypeDto();
        Dto.setName("uhukky");
        Dto.setInquiry_period_min(5);
        Dto.setInquiry_period_max(2);
        Dto.setAmount_min(2000);
        Dto.setAmount_max(70000);
        Dto.setType("Type e");
        Dto.setStatus(1);
        Dto.setDescription("Description of the request type1.");
        Dto.setOutstanding(5);
        Dto.setAttachment(true);
        Dto.setApproval_period_type("Monthly");
        Dto.setAllocation_cost(true);
        Dto.setBudgeting(true);
        requestTypeDao.save(Dto);
    }
}
