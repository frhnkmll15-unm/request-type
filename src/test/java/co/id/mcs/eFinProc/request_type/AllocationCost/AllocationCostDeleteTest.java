package co.id.mcs.eFinProc.request_type.AllocationCost;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 17/09/2024 09:56
@Last Modified 17/09/2024 09:56
Version 1.0
*/


import co.id.mcs.eFinProc.request_type.Dao.AllocationCostDao;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.DeleteAllocationCostById;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.AllocationCostDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class AllocationCostDeleteTest {

    @Autowired
    AllocationCostDao allocationCostDao;

    @Test
    void deleteAllocationById() throws Exception {

        Timestamp updated_at = new Timestamp(System.currentTimeMillis());
        allocationCostDao.deleteAllocationById(-1, updated_at, 1);
        System.out.println("success");
    }
}

