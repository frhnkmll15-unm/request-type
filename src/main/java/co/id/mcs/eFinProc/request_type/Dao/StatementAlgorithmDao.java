package co.id.mcs.eFinProc.request_type.Dao;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 15/09/2024 09:51
@Last Modified 15/09/2024 09:51
Version 1.0
*/

import co.id.mcs.eFinProc.PaginationModel;
import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.ParamSearchAllocationCost;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.DeleteRequestTypeById;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.ParamSearchRequestType;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.RequestTypeDto;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.DeleteStatementAlgorithmById;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.ParamSearchStatementAlgorithm;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.StatementAlgorithmDto;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.UpdateStatementAlgorithmById;

import java.sql.Timestamp;
import java.util.List;

public interface StatementAlgorithmDao {

    PaginationModel<List<StatementAlgorithmDto>> searchByName(String name, ParamSearchStatementAlgorithm param);


    Integer countAll(ParamSearchStatementAlgorithm param);

    void save(StatementAlgorithmDto statementAlgorithmDto);


    
    void deleteStatementAlgorithmById(Object ... value);



    StatementAlgorithmDto getById(int id, int i) throws Exception;

    // Metode untuk memperbarui status RequestType
    void updateStatementStatus(int id, int status, Timestamp updatedAt);

    void deleteStatementAlgorithmById(DeleteStatementAlgorithmById deleteStatementAlgorithmById, Timestamp updated_at);

    StatementAlgorithmDto getById(int id) throws Exception;

    List<StatementAlgorithmDto> findAll(ParamSearchStatementAlgorithm param);

    PaginationModel<List<StatementAlgorithmDto>> search(Search search, ParamSearchStatementAlgorithm param);

    void updateStatementAlgorithmById(UpdateStatementAlgorithmById updateStatementAlgorithmById);
}
