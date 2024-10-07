package co.id.mcs.eFinProc.request_type.AllocationCost;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 17/09/2024 10:15
@Last Modified 17/09/2024 10:15
Version 1.0
*/


import co.id.mcs.eFinProc.request_type.Dao.AllocationCostDao;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.UpdateAllocationCostById;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.AllocationCostDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class AllocationCostUpdateTest {

    @Autowired
    AllocationCostDao allocationCostDao;

    @Test
    void updateAllocationCostByIdTest() throws Exception {

        AllocationCostDto data = allocationCostDao.getById(3);
        if (data == null) {
            throw new Exception("Allocation tidak ditemukan");
        }

        UpdateAllocationCostById change = new UpdateAllocationCostById();
        change.setId(data.getId());
        change.setId_type_request_eq(1); // Set an appropriate value based on your logic
        change.setId_product("NewProd1");
        change.setId_departement("dept1");
        change.setName_departement("DeptName1");
        change.setType(data.getType());
        change.setStatus(1);
        change.setAllocation_id_project("proj1");
        change.setPercentase(50);
        change.setDescription("New Description");
        change.setUpdated_at(new Timestamp(System.currentTimeMillis()));

        allocationCostDao.updateAllocationById(change);
        System.out.println("Update sukses");
    }
}
