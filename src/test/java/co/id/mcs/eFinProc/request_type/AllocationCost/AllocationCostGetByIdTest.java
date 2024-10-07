package co.id.mcs.eFinProc.request_type.AllocationCost;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 17/09/2024 10:08
@Last Modified 17/09/2024 10:08
Version 1.0
*/



import co.id.mcs.eFinProc.request_type.Dao.AllocationCostDao;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.AllocationCostDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AllocationCostGetByIdTest {

    @Autowired
    AllocationCostDao allocationCostDao;

    @Test
    void getByIdTest() throws Exception {
        int id = 4;

        AllocationCostDto allocationCost = allocationCostDao.getById(id);

        if (allocationCost == null) {
            throw new Exception("AllocationCost tidak ditemukan untuk ID: " + id);
        }

        System.out.println(allocationCost.toString());
    }
}
