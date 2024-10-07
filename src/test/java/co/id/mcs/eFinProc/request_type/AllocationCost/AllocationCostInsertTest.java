package co.id.mcs.eFinProc.request_type.AllocationCost;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 16:54
@Last Modified 13/09/2024 16:54
Version 1.0
*/


import co.id.mcs.eFinProc.request_type.Dao.AllocationCostDao;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.AllocationCostDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;

@SpringBootTest
public class AllocationCostInsertTest {

    @Autowired
    AllocationCostDao allocationCostDao;

    @Test
    void testInsertAllocationCost() {
        AllocationCostDto dto = new AllocationCostDto(2, "P002", "D002", "HR", 1, "PR002", 30, "Alokasi kedua");
        dto.setId_product("ucen4");
        dto.setId_department("ucen4");
        dto.setName_departement("tes4");
        dto.setType(1);
        dto.setStatus(1);
        dto.setAllocation_id_project("proj-769");
        dto.setPercentase(50);
        dto.setDescription("Description of the allocation cost.");
        dto.setCreated_at(Timestamp.from(Instant.now()));
        dto.setUpdated_at(Timestamp.from(Instant.now()));


        allocationCostDao.save(dto);


    }
}
