package co.id.mcs.eFinProc.request_type.service.Impl;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 20/09/2024 13:57
@Last Modified 20/09/2024 13:57
Version 1.0
*/

import co.id.mcs.eFinProc.PaginationModel;
import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Dao.StatementAlgorithmDao;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.DeleteStatementAlgorithmById;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.ParamSearchStatementAlgorithm;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.StatementAlgorithmDto;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.UpdateStatementAlgorithmById;
import co.id.mcs.eFinProc.request_type.Web.Dto.BaseResponse;
import co.id.mcs.eFinProc.request_type.service.StatementAlgorithmService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;



@Log4j2
@Service
public class StatementAlgorithmServiceImpl implements StatementAlgorithmService {

    @Autowired
    private StatementAlgorithmDao statementAlgorithmDao;


    @Override
    public BaseResponse<?> getStatementAlgorithms(Search search, ParamSearchStatementAlgorithm param) {
        BaseResponse<Object> response = new BaseResponse<>();

        // Validasi param
        if (param == null) {
            response.setPayload(null);
            response.setMessage("Param must not be null");
            response.setStatus("400");
            return response;
        }
        // Menyimpan totalCount untuk menghitung total data
        int totalCount = 0;
        Map<String, Object> payloadData = new HashMap<>();

        // Pencarian berdasarkan nilai
        if (search != null && search.getValue() != null && !search.getValue().isEmpty()) {
            String searchValue = search.getValue().toLowerCase(); // Mengubah menjadi lowercase untuk pencocokan yang lebih baik
            PaginationModel<List<StatementAlgorithmDto>> result = statementAlgorithmDao.searchByName(searchValue, param);

            totalCount = result.getTotal(); // Ambil total dari hasil pencarian

            if (result != null && !result.getPayload().isEmpty()) {
                // Menyusun informasi halaman
                int totalPages = (int) Math.ceil((double) totalCount / param.getLimit());
                Map<String, Object> informationPage = new HashMap<>();
                informationPage.put("firstPage", 1);
                informationPage.put("lastPage", totalPages);
                informationPage.put("previous", param.getPage() > 1 ? param.getPage() - 1 : 1);
                informationPage.put("next", param.getPage() < totalPages ? param.getPage() + 1 : totalPages);
                informationPage.put("currentPage", param.getPage());

                // Menyusun payload
                payloadData.put("informationPage", informationPage);
                payloadData.put("page", param.getPage());
                payloadData.put("limit", param.getLimit());
                payloadData.put("total", totalCount);
                payloadData.put("payload", result.getPayload());

                response.setPayload(payloadData);
                response.setMessage("Success");
                response.setStatus("200");
            } else {
                response.setPayload(Collections.emptyList());
                response.setMessage("No Request Types Found");
                response.setStatus("404");
            }
        } else {
            List<StatementAlgorithmDto> request = statementAlgorithmDao.findAll(param);
            totalCount = statementAlgorithmDao.countAll(param);

            if (!request.isEmpty()) {
                // Menyusun informasi halamann
                int totalPages = (int) Math.ceil((double) totalCount / param.getLimit());
                Map<String, Object> informationPage = new HashMap<>();
                informationPage.put("firstPage", 1);
                informationPage.put("lastPage", totalPages);
                informationPage.put("previous", param.getPage() > 1 ? param.getPage() - 1 : 1);
                informationPage.put("next", param.getPage() < totalPages ? param.getPage() + 1 : totalPages);
                informationPage.put("currentPage", param.getPage());

                // Menyusun payload
                payloadData.put("informationPage", informationPage);
                payloadData.put("page", param.getPage());
                payloadData.put("limit", param.getLimit());
                payloadData.put("total", totalCount);
                payloadData.put("payload", request);

                response.setPayload(payloadData);
                response.setMessage("Success");
                response.setStatus("200");
            } else {
                response.setPayload(Collections.emptyList());
                response.setMessage("No Request Types Found");
                response.setStatus("404");
            }
        }
        return response;
    }
    @Override
    public BaseResponse<StatementAlgorithmDto> createStatementAlgorithm(StatementAlgorithmDto statementAlgorithm) {
        BaseResponse<StatementAlgorithmDto> response = new BaseResponse<>();
        try {
            System.out.println("Preparing to save: " + statementAlgorithm);
            statementAlgorithmDao.save(statementAlgorithm);
            response.setPayload(statementAlgorithm);
            response.setMessage("Pembuatan berhasil");
            response.setStatus("200");
        } catch (IllegalArgumentException e) {
            response.setPayload(null);
            response.setMessage("Permintaan buruk: " + e.getMessage());
            response.setStatus("400");
        } catch (Exception e) {
            response.setPayload(null);
            response.setMessage("Kesalahan: " + e.getMessage());
            response.setStatus("500");
        }
        return response;
    }
    @Override
    public BaseResponse<StatementAlgorithmDto> getStatementAlgorithmById(int status, int id) throws Exception {
        BaseResponse<StatementAlgorithmDto> response = new BaseResponse<>();
       StatementAlgorithmDto statementAlgorithm = statementAlgorithmDao.getById(status, id);
        if (statementAlgorithm != null) {
            response.setPayload(statementAlgorithm);
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
    public BaseResponse<UpdateStatementAlgorithmById> updateStatementAlgorithmById(UpdateStatementAlgorithmById updateStatementAlgorithmById) throws Exception {
        BaseResponse<UpdateStatementAlgorithmById> response = new BaseResponse<>();

        // Retrieve the existing data based on the ID
        StatementAlgorithmDto existingData = statementAlgorithmDao.getById(updateStatementAlgorithmById.getId());

        // Check if the existing data is null
        if (existingData == null) {
            response.setPayload(null);
            response.setMessage("Statement Algorithm Not Found");
            response.setStatus("404");
            return response;
        }

        // Prepare the update object with existing data and new values
        UpdateStatementAlgorithmById change = new UpdateStatementAlgorithmById();
        change.setId(existingData.getId());
        change.setType(updateStatementAlgorithmById.getType());
        change.setLogic(updateStatementAlgorithmById.getLogic());
        change.setStatus(updateStatementAlgorithmById.getStatus());
        change.setCommand(updateStatementAlgorithmById.getCommand());
        change.setUpdated_at(new Timestamp(System.currentTimeMillis()));

        // Include id_type_request_eq in the update if it's part of the UpdateStatementAlgorithmById class
        change.setId_type_request_eq(existingData.getId_type_request_eq());

        // Call DAO to perform the update
        statementAlgorithmDao.updateStatementAlgorithmById(change);

        // Set the response payload and status
        response.setPayload(change);
        response.setMessage("Update Success");
        response.setStatus("200");
        return response;
    }

    //    @Override
//    public BaseResponse<UpdateStatementAlgorithmById> updateStatementAlgorithmById(UpdateStatementAlgorithmById updateStatementAlgorithmById) throws Exception {
//        BaseResponse<UpdateStatementAlgorithmById> response = new BaseResponse<>();
//
//        StatementAlgorithmDto existingData = statementAlgorithmDao.getById(updateStatementAlgorithmById.getId());
//
//        if (existingData == null) {
//            response.setPayload(null);
//            response.setMessage("Statement Algorithm Not Found");
//            response.setStatus("404");
//            return response;
//        }
//
//        UpdateStatementAlgorithmById change = new UpdateStatementAlgorithmById();
//        change.setId(existingData.getId());
//        change.setType(updateStatementAlgorithmById.getType());
//        change.setLogic(updateStatementAlgorithmById.getLogic());
//        change.setStatus(updateStatementAlgorithmById.getStatus());
//        change.setCommand(updateStatementAlgorithmById.getCommand());
//        change.setUpdated_at(new Timestamp(System.currentTimeMillis()));
//
//        statementAlgorithmDao.updateStatementAlgorithmById(change);
//
//        response.setPayload(change);
//        response.setMessage("Update Success");
//        response.setStatus("200");
//        return response;
//    }
    @Override
    public BaseResponse<DeleteStatementAlgorithmById> deleteStatementAlgorithmById(DeleteStatementAlgorithmById deleteStatementAlgorithmById) throws Exception {
        BaseResponse<DeleteStatementAlgorithmById> response = new BaseResponse<>();
        Optional<StatementAlgorithmDto> data = Optional.ofNullable(statementAlgorithmDao.getById(deleteStatementAlgorithmById.getId()));

        if (!data.isPresent()) {
            response.setPayload(null);
            response.setMessage("Request Type Not Found");
            response.setStatus("404");
            return response;
        }

        Timestamp updated_at = new Timestamp(System.currentTimeMillis());
        // Ubah status dari 1 ke 0
        statementAlgorithmDao.updateStatementStatus(deleteStatementAlgorithmById.getId(), 0, updated_at);
        response.setPayload(deleteStatementAlgorithmById);
        response.setMessage("Deleted successfully");
        response.setStatus("200");
        return response;
    }
}
