package co.id.mcs.eFinProc.request_type.Web.Controller;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 22/09/2024 13:22
@Last Modified 22/09/2024 13:22
Version 1.0
*/

import co.id.mcs.eFinProc.FilterSearch;
import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.AllocationCostDto;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.DeleteAllocationCostById;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.ParamSearchAllocationCost;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.UpdateAllocationCostById;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.ParamSearchRequestType;
import co.id.mcs.eFinProc.request_type.Web.Dto.BaseResponse;
import co.id.mcs.eFinProc.request_type.service.AllocationCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/allocation-costs") // Adjust the base path as needed
public class AllocationController {

    @Autowired
    private AllocationCostService allocationCostService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<AllocationCostDto>> createAllocation(@RequestBody AllocationCostDto allocationCost) {
        System.out.println("Received allocationCost: " + allocationCost);
        BaseResponse<AllocationCostDto> response = allocationCostService.createAllocation(allocationCost);
        if ("400".equals(response.getStatus())) {
            return ResponseEntity.badRequest().body(response);
        } else if ("500".equals(response.getStatus())) {
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<?>> getAllocationCost(
            @RequestParam(required = false) String searchValue,
            @RequestParam(required = false) List<String> fields,
            @RequestParam int page,
            @RequestParam int limit,
            @RequestParam(required = false) Integer status) {

        Search search = new Search();
        search.setValue(searchValue);
        if (fields != null && !fields.isEmpty()) {
            Set<FilterSearch> filters = new HashSet<>();
            for (String field : fields) {
                FilterSearch filter = new FilterSearch();
                filter.setField(field);
                filter.setAliasField(null);
                filters.add(filter);
            }
            search.setFilter(filters);
        }

        ParamSearchAllocationCost param = new ParamSearchAllocationCost();
        param.setPage(page);
        param.setLimit(limit);
        if (status != null) {
            param.setStatus(status);
        }

        BaseResponse<?> response = allocationCostService.getAllocation(search, param);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse<UpdateAllocationCostById>> updateAllocationCostById(@PathVariable int id, @RequestBody UpdateAllocationCostById updateAllocationCostById) throws Exception{
        updateAllocationCostById.setId(id);
        BaseResponse<UpdateAllocationCostById> response = allocationCostService.updateAllocationCostById(updateAllocationCostById);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<BaseResponse<AllocationCostDto>> getAllocationCostById(@PathVariable int id) throws Exception {
        System.out.println("Fetching allocation cost with ID: " + id);
        BaseResponse<AllocationCostDto> response = allocationCostService.getAllocationCostById(id);

        if ("404".equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<DeleteAllocationCostById>> deleteAllocationCostById(@PathVariable int id) throws Exception {
        DeleteAllocationCostById deleteRequest = new DeleteAllocationCostById();
        deleteRequest.setId(id);

        BaseResponse<DeleteAllocationCostById> response = allocationCostService.deleteAllocationCostById(deleteRequest);

        if (response == null || "404".equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
