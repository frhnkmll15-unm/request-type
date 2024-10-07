package co.id.mcs.eFinProc.request_type.service.Impl;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 01:00
@Last Modified 13/09/2024 01:00
Version 1.0
*/

import co.id.mcs.eFinProc.PaginationModel;
import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Dao.RequestTypeDao;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.*;
import co.id.mcs.eFinProc.request_type.service.RequestTypeService;
import co.id.mcs.eFinProc.request_type.Web.Dto.BaseResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class RequestTypeServiceImpl implements RequestTypeService {

    @Autowired
    private RequestTypeDao requestTypeDao;
    @Autowired
    private RestTemplate restTemplate;

    /// DATA BENAR \\\
//    @Override
//    public BaseResponse<RequestTypeDto> save(RequestTypeDto requestTypeDto) {
//        BaseResponse<RequestTypeDto> response = new BaseResponse<>();
//        try {
//            // Panggil metode save pada DAO untuk menyimpan data
//            requestTypeDao.save(requestTypeDto);
//
//            // Set payload dengan objek yang disimpan
//            response.setPayload(requestTypeDto);
//            response.setMessage("Created successfully");
//            response.setStatus("201");
//        } catch (Exception e) {
//            // Tangani kesalahan dan set respons yang sesuai
//            response.setPayload(null);
//            response.setMessage("Bad Request: " + e.getMessage());
//            response.setStatus("400");
//        }
//        return response;
//    }

    @Override
    public BaseResponse<RequestTypeDto> save(RequestTypeDto requestTypeDto) {
        BaseResponse<RequestTypeDto> response = new BaseResponse<>();
        try {
            requestTypeDao.save(requestTypeDto);
            requestTypeDto.setId(21);
            requestTypeDto.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
            response.setPayload(requestTypeDto);
            response.setMessage("Created successfully");
            response.setStatus("201");
        } catch (Exception e) {
            response.setPayload(null);
            response.setMessage("Bad Request: " + e.getMessage());
            response.setStatus("400");
        }
        return response;
    }

    @Override
    public BaseResponse<RequestTypeDto> getRequestTypeById(int status, int id) throws Exception {
        BaseResponse<RequestTypeDto> response = new BaseResponse<>();

        // Ambil request type berdasarkan ID
        Optional<RequestTypeDto> requestOpt = requestTypeDao.getById(id);

        if (requestOpt.isPresent()) {
            response.setPayload(requestOpt.get());
            response.setMessage("Success");
            response.setStatus("200");
        } else {
            response.setPayload(null);
            response.setMessage("Request Type Not Found");
            response.setStatus("404");
        }

        return response;
    }
    @Override
    public BaseResponse<PaginationModel<List<RequestTypeDto>>> getALl(Search search, ParamSearchRequestType param) {
        BaseResponse<PaginationModel<List<RequestTypeDto>>> response = new BaseResponse<>();
        PaginationModel<List<RequestTypeDto>> result = requestTypeDao.getAll(search, param);
        if (result != null) {
            response.setPayload(result);
            response.setMessage("Success");
            response.setStatus("200");
            return response;
        } else {
            response.setPayload(null);
            response.setMessage("No Users Found");
            response.setStatus("404");
            return response;
        }
    }
    @Override
    public BaseResponse<UpdateRequestTypeById> updateRequestTypeById(UpdateRequestTypeById updateRequestTypeById) throws Exception {
        BaseResponse<UpdateRequestTypeById> response = new BaseResponse<>();

        // Cek apakah data yang akan diperbarui ada
        Optional<RequestTypeDto> existingData = requestTypeDao.getById(updateRequestTypeById.getId());
        if (!existingData.isPresent()) {
            response.setPayload(null);
            response.setMessage("Request Type Not Found");
            response.setStatus("404");
            return response;
        }
        try {
            // Melakukan pembaruan data
            requestTypeDao.updateRequestTypeById(updateRequestTypeById);
            response.setPayload(updateRequestTypeById);
            response.setMessage("Update successful");
            response.setStatus("200");
        } catch (DataAccessException e) {
            response.setPayload(null);
            response.setMessage("Update failed: " + e.getMessage());
            response.setStatus("500");
        } catch (Exception e) {
            response.setPayload(null);
            response.setMessage("An unexpected error occurred: " + e.getMessage());
            response.setStatus("500");
        }

        return response;
    }
    @Override
    public BaseResponse<DeleteRequestTypeById> deleteRequestTypeById(DeleteRequestTypeById deleteRequestTypeById) throws Exception {
        BaseResponse<DeleteRequestTypeById> response = new BaseResponse<>();
        Optional<RequestTypeDto> data = requestTypeDao.getById(deleteRequestTypeById.getId());
        if (!data.isPresent()) {
            response.setPayload((null));
            response.setMessage("User Not Found");
            response.setStatus("404");
            return response;
        }
        DeleteRequestTypeById delete = new DeleteRequestTypeById();
        delete.setId(deleteRequestTypeById.getId());
        requestTypeDao.deleteRequestTypeById(delete);

        response.setPayload(deleteRequestTypeById);
        response.setMessage("Delete successfully");
        response.setStatus("200");

        return response;
    }
}


