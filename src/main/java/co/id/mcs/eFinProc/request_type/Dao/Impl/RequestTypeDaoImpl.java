package co.id.mcs.eFinProc.request_type.Dao.Impl;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 13:47
@Last Modified 13/09/2024 13:47
Version 1.0
*/
import co.id.mcs.eFinProc.PaginationModel;
import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Constant.RequestTypeSql;
import co.id.mcs.eFinProc.request_type.Dao.Component.RequestTypeSql.DeleteRequestTypeMapper;
import co.id.mcs.eFinProc.request_type.Dao.Component.RequestTypeSql.GetRequestTypeMapper;
import co.id.mcs.eFinProc.request_type.Dao.Component.RequestTypeSql.InsertRequestTypeMapper;
import co.id.mcs.eFinProc.request_type.Dao.Component.RequestTypeSql.UpdateRequestTypeMapper;
import co.id.mcs.eFinProc.request_type.Dao.RequestTypeDao;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.AllocationCostDto;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.*;
import co.id.mcs.eFinProc.request_type.Web.Dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class RequestTypeDaoImpl implements RequestTypeDao {

    /**
     * @param jdbcTemplate Calling a Stored Procedure
     * @param jdbcTemplatee Calling a Stored Procedure
     * @param schemaDatabase from application.properties
     * @param objectMapper to convert objects
     */

    private JdbcTemplate jdbcTemplate;
    private static final String SCHEMA = RequestTypeSql.SCHEMA.getCommand();
    private static final String TABLE = RequestTypeSql.TABLENAME.getCommand();
    private Function<RequestTypeDto, List<SqlParameterValue>> insertRequestTypeMapper;
    private Function<UpdateRequestTypeById, List<SqlParameterValue>> updateRequestTypeMapper;
    private Function<DeleteRequestTypeById, List<SqlParameterValue>> deleteRequestTypeMapper;
    private RowMapper<RequestTypeDto> getRequestTypeMapper;
    private AllocationCostDto[] allocations;


    @Autowired
    public void setDataSource(@Qualifier("dataSource") final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertRequestTypeMapper = new InsertRequestTypeMapper();
        this.updateRequestTypeMapper = new UpdateRequestTypeMapper();
        this.getRequestTypeMapper = new GetRequestTypeMapper();
        this.deleteRequestTypeMapper = new DeleteRequestTypeMapper();
    }
    /// Data Benar \\\
    @Override
    public String save(RequestTypeDto requestTypeDto) {
        if (requestTypeDto.getAllocationCost() == null) {
            requestTypeDto.setAllocationCost(false);
        }
        if (requestTypeDto.getName() == null || requestTypeDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh null atau kosong");
        }
        String sqlRequestType = "SELECT request_type.fn_type_request_eq_insert(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println("Menjalankan SQL untuk Tipe Permintaan: " + sqlRequestType);
        Object[] requestTypeParameters = new Object[]{
                requestTypeDto.getName(),
                requestTypeDto.getInquiryPeriodMin(),
                requestTypeDto.getInquiryPeriodMax(),
                requestTypeDto.getAmountMin(),
                requestTypeDto.getAmountMax(),
                requestTypeDto.getType(),
                requestTypeDto.getDescription(),
                requestTypeDto.getOutstanding(),
                requestTypeDto.getAttachment(),
                requestTypeDto.getApprovalPeriodType(),
                requestTypeDto.getAllocationCost(),
                requestTypeDto.getBudgeting()
        };
        try {
            String requestTypeResult = this.jdbcTemplate.queryForObject(
                    sqlRequestType,
                    requestTypeParameters,
                    String.class
            );
            System.out.println("Hasil Simpan Tipe Permintaan: " + requestTypeResult);
            int idTypeRequestEq = 1;
            String idProduct = "P001";
            String idDepartement = "D001";
            String nameDepartement = "Finance";
            int type = 2;
            String allocationIdProyek = "PR001";
            int persentase = 50;
            String allocationDescription = "Alokasi awal";
            String sqlAllocationCost = "SELECT request_type.fn_allocation_cost_insert(?, ?, ?, ?, ?, ?, ?, ?)";
            Object[] allocationCostParameters = new Object[]{
                    idTypeRequestEq,
                    idProduct,
                    idDepartement,
                    nameDepartement,
                    type,
                    allocationIdProyek,
                    persentase,
                    allocationDescription
            };
            String allocationCostResult = this.jdbcTemplate.queryForObject(
                    sqlAllocationCost,
                    allocationCostParameters,
                    String.class
            );
            System.out.println("Hasil Simpan Alokasi Biaya: " + allocationCostResult);
            String statementType = "ExampleType";
            String logic = "ExampleLogic";
            String command = "SELECT * FROM example_table;";
            String sqlStatementAlgorithm = "SELECT request_type.fn_statement_algorithm_insert(?, ?, ?, ?)";

            Object[] statementParameters = new Object[]{
                    idTypeRequestEq,
                    statementType,
                    logic,
                    command
            };
            String statementResult = this.jdbcTemplate.queryForObject(
                    sqlStatementAlgorithm,
                    statementParameters,
                    String.class
            );
            System.out.println("Hasil Simpan Algoritma Pernyataan: " + statementResult);
            return "Tipe Permintaan, Alokasi Biaya, dan Algoritma Pernyataan berhasil disimpan.";
        } catch (DataAccessException e) {
            System.err.println("Error saat menjalankan SQL: " + e.getMessage());
            throw new RuntimeException("Kesalahan database: " + e.getMessage());
        }
    }
    private String queryGetBySearch(String select, String search) {
        return String.valueOf(Integer.valueOf(RequestTypeSql.GET_ALL.getCommand().formatted(
                select,
                new StringBuilder()
                        .append(SCHEMA)
                        .append(".")
                        .append(RequestTypeSql.VIEW_GET_ALL.getCommand())
                        .toString(),
                search
        )));
    }
    private String queryFindBy(String select, String search) {
        return RequestTypeSql.FIND_BY.getCommand().formatted(
                select,
                new StringBuilder()
                        .append(SCHEMA)
                        .append(".")
                        .append(RequestTypeSql.VIEW_GET_ALL.getCommand())
                        .toString(),
                search
        );
    }
    private List<RequestTypeDto> findQuery(String query, Object... args) {
        return this.jdbcTemplate.query(
                query,
                this.getRequestTypeMapper, args
        );
    }
    private RequestTypeDto firstQuery(String query, Object... args) {
        List<RequestTypeDto> result = this.jdbcTemplate.query(
                query,
                this.getRequestTypeMapper, args
        );
        return !result.isEmpty() ? result.get(0) : null;
    }
    private List<RequestTypeDto> queryList(String query, int id) throws Exception {
        try {
            return jdbcTemplate.query(query, new Object[]{id}, this.getRequestTypeMapper);
        } catch (DataAccessException e) {
            throw new Exception("Error executing query", e);
        }
    }
    private Integer count(String query, Object... args) {
        return this.jdbcTemplate.queryForObject(
                query,
                Integer.class, args
        );
    }
    @Override
    public PaginationModel<List<RequestTypeDto>> getAll(Search search, ParamSearchRequestType param) {
        List<Object> data = new ArrayList<>();
        StringBuilder searchQuery = new StringBuilder();
        if (search != null && search.getValue() != null && !search.getValue().isEmpty() && param.getFields() != null && !param.getFields().isEmpty()) {
            for (String field : param.getFields()) {
                if (searchQuery.length() > 0) {
                    searchQuery.append(" OR ");
                } else {
                    searchQuery.append("WHERE ");
                }
                searchQuery.append(field).append(" ILIKE ?");
                data.add("%" + search.getValue() + "%");
            }
        }
        String query = "SELECT * FROM request_type.vw_type_request_eq_getall " + searchQuery.toString();
        String limitSkip = "LIMIT ? OFFSET ?";
        String finalQuery = query + " " + limitSkip;
        data.add(param.getLimit());
        data.add((param.getPage() - 1) * param.getLimit());
        List<RequestTypeDto> result = findQuery(finalQuery, data.toArray());
        String queryCount = "SELECT COUNT(*) FROM request_type.vw_type_request_eq_getall " + searchQuery.toString();
        Integer count = count(queryCount, data.subList(0, data.size() - 2).toArray());
        PaginationModel<List<RequestTypeDto>> paginationModel = new PaginationModel<>();
        paginationModel.setPayload(result);
        paginationModel.setTotal(count);
        paginationModel.setPage(param.getPage());
        paginationModel.setLimit(param.getLimit());
        paginationModel.builderInformation();

        return paginationModel;
    }
    @Override
    public Optional<RequestTypeDto> getById(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be greater than zero");
        }
        String query = "SELECT * FROM request_type.vw_type_request_eq_getall WHERE id = ?";
        RequestTypeDto result = firstQuery(query, id);
        return Optional.ofNullable(result);
    }
    @Override
    public BaseResponse<UpdateRequestTypeById> updateRequestTypeById(UpdateRequestTypeById updateRequestTypeById) {
        BaseResponse<UpdateRequestTypeById> response = new BaseResponse<>();
        if (updateRequestTypeById.getId() <= 0) {
            throw new IllegalArgumentException("ID tidak valid");
        }
        if (updateRequestTypeById.getName() == null || updateRequestTypeById.getName().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh null atau kosong");
        }
        String checkQuery = "SELECT * FROM request_type.vw_type_request_eq_getall WHERE id = ?";
        RequestTypeDto existingRequestType;
        try {
            existingRequestType = jdbcTemplate.queryForObject(checkQuery, new Object[]{updateRequestTypeById.getId()}, getRequestTypeMapper);
        } catch (EmptyResultDataAccessException e) {
            response.setPayload(null);
            response.setMessage("Tipe Permintaan dengan ID " + updateRequestTypeById.getId() + " tidak ditemukan.");
            response.setStatus("404");
            return response;
        }
        String sqlUpdate = "SELECT request_type.fn_type_request_eq_update(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] updateParameters = new Object[]{
                updateRequestTypeById.getId(),
                updateRequestTypeById.getName(),
                updateRequestTypeById.getInquiryPeriodMin(),
                updateRequestTypeById.getInquiryPeriodMax(),
                updateRequestTypeById.getAmountMin(),
                updateRequestTypeById.getAmountMax(),
                updateRequestTypeById.getType(),
                updateRequestTypeById.getDescription(),
                updateRequestTypeById.getOutstanding(),
                updateRequestTypeById.isAttachment(),
                updateRequestTypeById.getApprovalPeriodType(),
                updateRequestTypeById.isAllocationCost(),
                updateRequestTypeById.isBudgeting()
        };
        try {
            String updateResult = this.jdbcTemplate.queryForObject(sqlUpdate, updateParameters, String.class);
            String sqlAllocationUpdate = "SELECT request_type.fn_allocation_cost_update(?, ?, ?, ?, ?, ?, ?, ?)";
            Object[] allocationParameters = new Object[]{
                    1,
                    updateRequestTypeById.getId(),
                    "P002",
                    "D002",
                    "Marketing",
                    1,
                    "PR002",
                    75,
                    "Updated allocation"
            };
            String allocationResult = this.jdbcTemplate.queryForObject(sqlAllocationUpdate, allocationParameters, String.class);
            String sqlStatementUpdate = "SELECT request_type.fn_statement_algorithm_update(?, ?, ?, ?, ?)";
            Object[] statementParameters = new Object[]{
                    1,
                    updateRequestTypeById.getId(),
                    "input type",
                    "logic b",
                    "SELECT * FROM example_table;"
            };
            String statementResult = this.jdbcTemplate.queryForObject(sqlStatementUpdate, statementParameters, String.class);
            response.setMessage("Update berhasil: " + updateResult + ", Update alokasi: " + allocationResult + ", Update pernyataan: " + statementResult);
            response.setPayload(updateRequestTypeById);
            response.setStatus("200");
        } catch (DataAccessException e) {
            response.setPayload(null);
            response.setMessage("Update gagal: " + e.getMessage());
            response.setStatus("500");
        } catch (Exception e) {
            response.setPayload(null);
            response.setMessage("Terjadi kesalahan yang tidak terduga: " + e.getMessage());
            response.setStatus("500");
        }
        return response;
    }
    @Override
    public void deleteRequestTypeById(DeleteRequestTypeById deleteRequestTypeById) {
        if (deleteRequestTypeById.getId() <= 0) {
            throw new IllegalArgumentException("ID tidak valid");
        }
        String sqlDeleteRequestType = "SELECT request_type.fn_type_request_eq_delete(?)";
        String sqlDeleteAllocationCost = "SELECT request_type.fn_allocation_cost_delete(?)";
        String sqlDeleteStatementAlgorithm = "SELECT request_type.fn_statement_algoritm_delete(?)";
        try {
            String deleteRequestResult = this.jdbcTemplate.queryForObject(
                    sqlDeleteRequestType,
                    new Object[]{deleteRequestTypeById.getId()},
                    String.class
            );
            System.out.println("Hasil penghapusan tipe permintaan: " + deleteRequestResult);
            String deleteAllocationResult = this.jdbcTemplate.queryForObject(
                    sqlDeleteAllocationCost,
                    new Object[]{deleteRequestTypeById.getId()},
                    String.class
            );
            System.out.println("Hasil penghapusan biaya alokasi: " + deleteAllocationResult);
            String deleteStatementAlgorithmResult = this.jdbcTemplate.queryForObject(
                    sqlDeleteStatementAlgorithm,
                    new Object[]{deleteRequestTypeById.getId()},
                    String.class
            );
            System.out.println("Hasil penghapusan algoritma pernyataan: " + deleteStatementAlgorithmResult);
        } catch (DataAccessException e) {
            System.err.println("Error saat menghapus tipe permintaan, biaya alokasi, atau algoritma pernyataan: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
