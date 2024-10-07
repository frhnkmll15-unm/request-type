package co.id.mcs.eFinProc.request_type.Dao;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 14:08
@Last Modified 13/09/2024 14:08
Version 1.0
*/


import co.id.mcs.eFinProc.PaginationModel;
import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.AllocationCostDto;

import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.DeleteAllocationCostById;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.ParamSearchAllocationCost;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.UpdateAllocationCostById;


import java.sql.Timestamp;
import java.util.List;

public interface AllocationCostDao {

    PaginationModel<List<AllocationCostDto>> searchByName(String name, ParamSearchAllocationCost param);
    void save(AllocationCostDto allocationCostDto);
    PaginationModel<List<AllocationCostDto>> search(Search search, ParamSearchAllocationCost param);
    Integer countAll(ParamSearchAllocationCost param);
    List<AllocationCostDto> findAll(ParamSearchAllocationCost param);
    void updateAllocationStatus(int id, int status, Timestamp updated_at);
    void deleteAlocationCostById(DeleteAllocationCostById deleteAllocationCostById, Timestamp updated_at);
    AllocationCostDto getById(int id) throws Exception;
    AllocationCostDto getById(int status, int id) throws Exception;
    void updateAllocationById(UpdateAllocationCostById updateAllocationCostById);
    void deleteAllocationById(int id);
}



