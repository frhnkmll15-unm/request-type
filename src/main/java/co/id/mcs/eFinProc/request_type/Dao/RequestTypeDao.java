package co.id.mcs.eFinProc.request_type.Dao;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 13:49
@Last Modified 13/09/2024 13:49
Version 1.0
*/

import co.id.mcs.eFinProc.PaginationModel;
import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.*;
import co.id.mcs.eFinProc.request_type.Web.Dto.BaseResponse;

import java.util.List;
import java.util.Optional;

public interface RequestTypeDao {

    Optional<RequestTypeDto> getById(int id) throws Exception;
    String save(RequestTypeDto requestTypeDto);
    PaginationModel<List<RequestTypeDto>> getAll(Search search, ParamSearchRequestType param);
//    RequestTypeDto getById(int status, int id) throws Exception;
    BaseResponse<UpdateRequestTypeById> updateRequestTypeById(UpdateRequestTypeById updateRequestTypeById);
    void deleteRequestTypeById(DeleteRequestTypeById deleteRequestTypeById);

}
