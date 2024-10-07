package co.id.mcs.eFinProc.request_type.service;/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 22/09/2024 13:06
@Last Modified 22/09/2024 13:06
Version 1.0
*/

import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.AllocationCostDto;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.DeleteAllocationCostById;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.ParamSearchAllocationCost;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.UpdateAllocationCostById;
import co.id.mcs.eFinProc.request_type.Web.Dto.BaseResponse;

import java.util.List;

public interface AllocationCostService {

    BaseResponse<?> getAllocation(Search search, ParamSearchAllocationCost param);
    BaseResponse<List<AllocationCostDto>> getAllocationCosts(ParamSearchAllocationCost param);
    BaseResponse<AllocationCostDto> getAllocationCostById(int id) throws Exception;
    BaseResponse<AllocationCostDto> createAllocation(AllocationCostDto param);
    BaseResponse<UpdateAllocationCostById> updateAllocationCostById(UpdateAllocationCostById updateAllocationCostById) throws Exception;
    BaseResponse<DeleteAllocationCostById> deleteAllocationCostById(DeleteAllocationCostById deleteAllocationCostById) throws Exception;
}
