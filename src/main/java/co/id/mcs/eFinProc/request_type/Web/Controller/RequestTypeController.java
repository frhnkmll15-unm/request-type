package co.id.mcs.eFinProc.request_type.Web.Controller;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 01/10/2024 15:55
@Last Modified 01/10/2024 15:55
Version 1.0
*/


import co.id.mcs.eFinProc.FilterSearch;
import co.id.mcs.eFinProc.PaginationModel;
import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.*;
import co.id.mcs.eFinProc.request_type.Web.Dto.BaseResponse;
import co.id.mcs.eFinProc.request_type.service.RequestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("/api/request-types")
public class RequestTypeController {

    @Autowired
    private RequestTypeService requestTypeService;
    @PostMapping("/create")
    public ResponseEntity<String> createRequestType(@RequestBody RequestTypeDto requestTypeDto) {
        System.out.println("Received DTO: " + requestTypeDto);
        String result = String.valueOf(requestTypeService.save(requestTypeDto));
        return ResponseEntity.ok(result);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse<UpdateRequestTypeById>> updateRequestType(
            @PathVariable int id,
            @RequestBody UpdateRequestTypeById updateRequestTypeById) throws Exception {
        try {
            updateRequestTypeById.setId(id);
            BaseResponse<UpdateRequestTypeById> response = requestTypeService.updateRequestTypeById(updateRequestTypeById);
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponse<>());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse<>());
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<DeleteRequestTypeById>> deleteRequestTypeById(@PathVariable int id, DeleteRequestTypeById deleteRequestTypeById) throws Exception{
        deleteRequestTypeById.setId(id);
        BaseResponse<DeleteRequestTypeById> response = requestTypeService.deleteRequestTypeById(deleteRequestTypeById);
        if ("404".equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<BaseResponse<RequestTypeDto>> getRequestTypeById (@PathVariable int id) throws Exception{
        BaseResponse<RequestTypeDto> response = requestTypeService.getRequestTypeById(1,id);
        if ("404".equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<PaginationModel<List<RequestTypeDto>>>> getAll(
            @RequestParam(required = false) String searchValue,
            @RequestParam(required = false) List<String> fields,
            @RequestParam int page,
            @RequestParam int limit) { // Hapus status dari sini

        ParamSearchRequestType param = new ParamSearchRequestType();
        param.setPage(page);
        param.setLimit(limit);

        Set<FilterSearch> filters = new HashSet<>();
        if (fields != null && !fields.isEmpty()) {
            for (String field : fields) {
                FilterSearch filter = new FilterSearch();
                filter.setField(field);
                filters.add(filter);
            }
        }

        // Membangun objek pencarian
        Search search = new Search();
        search.setValue(searchValue);
        search.setFilter(filters);

        // Memanggil service untuk mendapatkan data
        BaseResponse<PaginationModel<List<RequestTypeDto>>> response = requestTypeService.getALl(searchValue != null ? search : null, param);

        // Mengembalikan respons berdasarkan status
        if ("404".equals(response.getStatus())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            return ResponseEntity.ok(response);
        }
    }

//    @GetMapping("/list")
//    public ResponseEntity<BaseResponse<PaginationModel<List<RequestTypeDto>>>> getAll(
//            @RequestParam(required = false) String searchValue,
//            @RequestParam(required = false) List<String> fields,
//            @RequestParam int page,
//            @RequestParam int limit,
//            @RequestParam int status) {
//
//        ParamSearchRequestType param = new ParamSearchRequestType();
//        param.setPage(page);
//        param.setLimit(limit);
//        param.setStatus(status);
//
//        Set<FilterSearch> filters = new HashSet<>();
//        if (fields != null && !fields.isEmpty()) {
//            for (String field : fields) {
//                FilterSearch filter = new FilterSearch();
//                filter.setField(field);
//                filters.add(filter);
//            }
//        }
//
//        Search search = new Search();
//        search.setValue(searchValue);
//        search.setFilter(filters);
//
//        BaseResponse<PaginationModel<List<RequestTypeDto>>> response = requestTypeService.getALl(searchValue != null ? search : null, param);
//
//        if ("404".equals(response.getStatus())) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        } else {
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        }
//    }
}
