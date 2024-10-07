package co.id.mcs.eFinProc.request_type.service;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 20/09/2024 14:00
@Last Modified 20/09/2024 14:00
Version 1.0
*/


import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.DeleteStatementAlgorithmById;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.ParamSearchStatementAlgorithm;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.StatementAlgorithmDto;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.UpdateStatementAlgorithmById;
import co.id.mcs.eFinProc.request_type.Web.Dto.BaseResponse;


public interface StatementAlgorithmService {

    BaseResponse<?> getStatementAlgorithms(Search search, ParamSearchStatementAlgorithm param);
    BaseResponse<StatementAlgorithmDto> createStatementAlgorithm(StatementAlgorithmDto statementAlgorithm);
    BaseResponse<StatementAlgorithmDto> getStatementAlgorithmById(int status, int id) throws Exception;
    BaseResponse<UpdateStatementAlgorithmById> updateStatementAlgorithmById(UpdateStatementAlgorithmById updateStatementAlgorithmById) throws Exception;
    BaseResponse<DeleteStatementAlgorithmById> deleteStatementAlgorithmById(DeleteStatementAlgorithmById deleteStatementAlgorithmById) throws Exception;

}
