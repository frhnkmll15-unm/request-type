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
import co.id.mcs.eFinProc.request_type.Dao.AllocationCostDao;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.AllocationCostDto;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.DeleteAllocationCostById;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.ParamSearchAllocationCost;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.UpdateAllocationCostById;

import co.id.mcs.eFinProc.request_type.Web.Dto.BaseResponse;
import co.id.mcs.eFinProc.request_type.service.AllocationCostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 22/09/2024 13:05
@Last Modified 22/09/2024 13:05
Version 1.0
*/
@Log4j2
@Service
public class AllocationCostServiceImpl implements AllocationCostService {

    @Autowired
    private AllocationCostDao allocationCostDao;

    @Override
    public BaseResponse<?> getAllocation(Search search, ParamSearchAllocationCost param) {
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
            PaginationModel<List<AllocationCostDto>> result = allocationCostDao.searchByName(searchValue, param);

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
            List<AllocationCostDto> request = allocationCostDao.findAll(param);
            totalCount = allocationCostDao.countAll(param);

            if (!request.isEmpty()) {
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
    public BaseResponse<List<AllocationCostDto>> getAllocationCosts(ParamSearchAllocationCost param) {
        BaseResponse<List<AllocationCostDto>> response = new BaseResponse<>();
        List<AllocationCostDto> allocationCosts = allocationCostDao.findAll(param);

        if (!allocationCosts.isEmpty()) {
            response.setPayload(allocationCosts);
            response.setMessage("Success");
            response.setStatus("200");
        } else {
            response.setPayload(null);
            response.setMessage("No Allocation Costs Found");
            response.setStatus("404");
        }
        return response;
    }
    @Override
    public BaseResponse<AllocationCostDto> getAllocationCostById(int id) throws Exception {
        BaseResponse<AllocationCostDto> response = new BaseResponse<>();

        // Fetch the allocation cost from the DAO
        AllocationCostDto allocationCost = allocationCostDao.getById(id);

        if (allocationCost != null) {
            response.setPayload(allocationCost);
            response.setMessage("Success");
            response.setStatus("200");
        } else {
            response.setPayload(null);
            response.setMessage("Allocation Cost Not Found");
            response.setStatus("404");
        }

        return response;
    }
    @Override
    public BaseResponse<AllocationCostDto> createAllocation(AllocationCostDto param) {
        BaseResponse<AllocationCostDto> response = new BaseResponse<>();
        try {
            System.out.println("Preparing to save allocation: " + param);
            allocationCostDao.save(param); // Assuming you have an allocationCostDao
            response.setPayload(param);
            response.setMessage("Allocation creation successful");
            response.setStatus("200");
        } catch (IllegalArgumentException e) {
            response.setPayload(null);
            response.setMessage("Bad request: " + e.getMessage());
            response.setStatus("400");
        } catch (Exception e) {
            response.setPayload(null);
            response.setMessage("Error: " + e.getMessage());
            response.setStatus("500");
        }
        return response;
    }

    @Override
    public BaseResponse<UpdateAllocationCostById> updateAllocationCostById(UpdateAllocationCostById updateAllocationCostById) throws Exception {
        BaseResponse<UpdateAllocationCostById> response = new BaseResponse<>();
        Optional<AllocationCostDto> data = Optional.ofNullable(allocationCostDao.getById(updateAllocationCostById.getId()));

        if (!data.isPresent()) {
            response.setPayload(null);
            response.setMessage("Allocation Cost Not Found");
            response.setStatus("404");
            return response;
        }

        UpdateAllocationCostById change = new UpdateAllocationCostById();
        change.setId(updateAllocationCostById.getId());
        change.setId_product(updateAllocationCostById.getId_product());
        change.setId_departement(updateAllocationCostById.getId_departement());
        change.setName_departement(updateAllocationCostById.getName_departement());
        change.setId_type_request_eq(data.get().getId_type_request_eq());
        change.setType(data.get().getType());
        change.setStatus(updateAllocationCostById.getStatus());
        change.setAllocation_id_project(updateAllocationCostById.getAllocation_id_project());
        change.setPercentase(updateAllocationCostById.getPercentase());
        change.setDescription(updateAllocationCostById.getDescription());
        change.setUpdated_at(new Timestamp(System.currentTimeMillis()));

        allocationCostDao.updateAllocationById(change);
        response.setPayload(change);
        response.setMessage("Update Success");
        response.setStatus("200");
        return response;
    }


    //    @Override
//    public BaseResponse<UpdateAllocationCostById> updateAllocationCostById(UpdateAllocationCostById updateAllocationCostById) throws Exception {
//        BaseResponse<UpdateAllocationCostById> response = new BaseResponse<>();
//        Optional<AllocationCostDto> data = Optional.ofNullable(allocationCostDao.getById(updateAllocationCostById.getId()));
//        if (!data.isPresent()) {
//            response.setPayload(null);
//            response.setMessage("Allocation Cost Not Found");
//            response.setStatus("404");
//            return response;
//        }
//        UpdateAllocationCostById change = new UpdateAllocationCostById();
//        change.setId(updateAllocationCostById.getId());
//        change.setId_product(updateAllocationCostById.getId_product());
//        change.setId_departement(updateAllocationCostById.getId_departement());
//        change.setName_departement(updateAllocationCostById.getName_departement());
//        change.setType(data.get().getType());
//        change.setStatus(updateAllocationCostById.getStatus());
//        change.setAllocation_id_project(updateAllocationCostById.getAllocation_id_project());
//        change.setPercentase(updateAllocationCostById.getPercentase());
//        change.setDescription(updateAllocationCostById.getDescription());
//        change.setUpdated_at(new Timestamp(System.currentTimeMillis()));
//
//        allocationCostDao.updateAllocationById(change);
//        response.setPayload(change);
//        response.setMessage("Update Success");
//        response.setStatus("200");
//        return response;
//    }
    @Override
    public BaseResponse<DeleteAllocationCostById> deleteAllocationCostById(DeleteAllocationCostById deleteAllocationCostById) throws Exception {
        BaseResponse<DeleteAllocationCostById> response = new BaseResponse<>();

        // Ambil data berdasarkan ID
        Optional<AllocationCostDto> data = Optional.ofNullable(allocationCostDao.getById(deleteAllocationCostById.getId()));

        // Periksa apakah data ada
        if (!data.isPresent()) {
            response.setPayload(null);
            response.setMessage("Request Type Not Found");
            response.setStatus("404");
            return response;
        }

        Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
        // Log sebelum memperbarui status
        System.out.println("Updating status to 0 for ID: " + deleteAllocationCostById.getId());

        // Ubah status dari 1 ke 0
        allocationCostDao.updateAllocationStatus(deleteAllocationCostById.getId(), 0, updatedAt);

        // Log setelah pembaruan
        System.out.println("Status updated successfully for ID: " + deleteAllocationCostById.getId());

        response.setPayload(deleteAllocationCostById);
        response.setMessage("Deleted successfully");
        response.setStatus("200");
        return response;
    }
}
