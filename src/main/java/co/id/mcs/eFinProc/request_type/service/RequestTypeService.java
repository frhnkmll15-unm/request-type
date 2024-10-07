package co.id.mcs.eFinProc.request_type.service;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 19/09/2024 14:12
@Last Modified 19/09/2024 14:12
Version 1.0
*/

import co.id.mcs.eFinProc.PaginationModel;
import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.*;
import co.id.mcs.eFinProc.request_type.Web.Dto.BaseResponse;
import java.util.List;

public interface RequestTypeService {

    BaseResponse<RequestTypeDto> save(RequestTypeDto requestTypeDtos);
    BaseResponse<RequestTypeDto> getRequestTypeById(int status, int id) throws Exception;
    BaseResponse<PaginationModel<List<RequestTypeDto>>> getALl(Search search, ParamSearchRequestType param);
    BaseResponse<UpdateRequestTypeById> updateRequestTypeById(UpdateRequestTypeById updateRequestTypeById) throws Exception;
    BaseResponse<DeleteRequestTypeById> deleteRequestTypeById(DeleteRequestTypeById deleteRequestTypeById) throws Exception;
}
