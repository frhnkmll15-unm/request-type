package co.id.mcs.eFinProc.request_type.web.controller;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 19/09/2024 14:10
@Last Modified 19/09/2024 14:10
Version 1.0
*/

import co.id.mcs.eFinProc.FilterSearch;
import co.id.mcs.eFinProc.PaginationModel;
import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.ParamSearchRequestType;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.RequestTypeDto;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.UpdateRequestTypeById;
import co.id.mcs.eFinProc.request_type.Web.Dto.BaseResponse;
import co.id.mcs.eFinProc.request_type.service.RequestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/request-types")
public class RequestTypeController {

    @Autowired
    private RequestTypeService requestTypeService;
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<PaginationModel<List<RequestTypeDto>>>> getRequestTypes(
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

        ParamSearchRequestType param = new ParamSearchRequestType();
        param.setPage(page);
        param.setLimit(limit);
        if (status != null) {
            param.setStatus(status);
        }

        BaseResponse<PaginationModel<List<RequestTypeDto>>> response = requestTypeService.getAll(search, param);
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<RequestTypeDto>> createRequestType(@RequestBody RequestTypeDto requestTypeDto) {
        BaseResponse<RequestTypeDto> response = requestTypeService.save(requestTypeDto);

        switch (response.getStatus()) {
            case "201": // Created
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            case "409": // Conflict
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            case "404": // Not Found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            default: // Bad Request atau masalah lain
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<BaseResponse<RequestTypeDto>> getRequestTypeById(@PathVariable int id) throws Exception {
        BaseResponse<RequestTypeDto> response = requestTypeService.getRequestTypeById(id);
        if ("404".equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse<RequestTypeDto>> updateRequestTypeById(
            @PathVariable int id,
            @RequestBody UpdateRequestTypeById updateRequestTypeById) throws Exception {
        updateRequestTypeById.setId(id);
        BaseResponse<RequestTypeDto> response = requestTypeService.updateRequestTypeById(updateRequestTypeById);
        if ("404".equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}/details")
    public ResponseEntity<BaseResponse<RequestTypeDto>> getRequestTypeDetails(@PathVariable int id) {
        BaseResponse<RequestTypeDto> response = requestTypeService.getRequestTypeDetails(id);
        if ("404".equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
