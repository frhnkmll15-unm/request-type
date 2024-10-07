package co.id.mcs.eFinProc.request_type.AllocationCost;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 21:38
@Last Modified 13/09/2024 21:38
Version 1.0
*/




import co.id.mcs.eFinProc.request_type.Dao.AllocationCostDao;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.AllocationCostDto;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.ParamSearchAllocationCost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AllocationCostGetAllTest {

    @Autowired
    AllocationCostDao allocationCostDao;

    @Test
    void contextLoads() {

        ParamSearchAllocationCost param = new ParamSearchAllocationCost();
        param.setStatus(0);
        List<AllocationCostDto> allocationCosts = allocationCostDao.findAll(param);

        System.out.println(allocationCosts.toString());
    }
}
