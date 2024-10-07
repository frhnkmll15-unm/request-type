package co.id.mcs.eFinProc.request_type.Dao.Impl;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 14:09
@Last Modified 13/09/2024 14:09
Version 1.0
*/

import co.id.mcs.eFinProc.PaginationModel;
import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Constant.AllocationCostSql;
import co.id.mcs.eFinProc.request_type.Dao.AllocationCostDao;
import co.id.mcs.eFinProc.request_type.Dao.Component.AllocationSql.DeleteAllocationCostMapper;
import co.id.mcs.eFinProc.request_type.Dao.Component.AllocationSql.GetAllocationCostMapper;
import co.id.mcs.eFinProc.request_type.Dao.Component.AllocationSql.InsertAllocationCostMapper;
import co.id.mcs.eFinProc.request_type.Dao.Component.AllocationSql.UpdateAllocationCostMapper;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.AllocationCostDto;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.DeleteAllocationCostById;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.ParamSearchAllocationCost;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.UpdateAllocationCostById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;



@Repository
@SuppressWarnings("ALL")
public class AllocationCostDaoImpl implements AllocationCostDao {

    private JdbcTemplate jdbcTemplate;
    private static final String SCHEMA = AllocationCostSql.SCHEMA.getCommand();
    private static final String TABLE = AllocationCostSql.TABLENAME.getCommand();
    private Function<AllocationCostDto, List<SqlParameterValue>> insertAllocationCostMapper;
    private Function<UpdateAllocationCostById, List<SqlParameterValue>> updateAllocationCostMapper;
    private DeleteAllocationCostMapper deleteAllocationCostMapper;
    private GetAllocationCostMapper getAllocationCostMapper;
    @Autowired
    public void setDataSource(@Qualifier("dataSource") final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertAllocationCostMapper = new InsertAllocationCostMapper();
        this.updateAllocationCostMapper = new UpdateAllocationCostMapper();
        this.getAllocationCostMapper = new GetAllocationCostMapper();
        this.deleteAllocationCostMapper = new DeleteAllocationCostMapper();
    }

    @Override
    public PaginationModel<List<AllocationCostDto>> searchByName(String name, ParamSearchAllocationCost param) {
        List<Object> data = new ArrayList<>();
        data.add(param.getStatus());

        // Wildcard untuk pencarian substring
        String wildcardValue = "%" + name + "%";

        // Kueri untuk mengambil data
        String query = "SELECT * FROM " + SCHEMA + "." + TABLE +
                " WHERE status = ? AND (" +
                "description LIKE ? OR " +
                "id_product LIKE ? OR " +
                "id_departement LIKE ? OR " +
                "name_departement LIKE ? OR " +
                "allocation_id_proyek LIKE ? " +
                ")" +
                " LIMIT ? OFFSET ?";

        // Menambahkan data untuk wildcard pencarian di berbagai field
        data.add(wildcardValue); // description
        data.add(wildcardValue); // id_product
        data.add(wildcardValue); // id_departement
        data.add(wildcardValue);
        data.add(wildcardValue);

        // Menambahkan limit dan offset
        data.add(param.getLimit());
        data.add((param.getPage() - 1) * param.getLimit());

        List<AllocationCostDto> result = findQuery(query, data.toArray());

        // Kueri hitung untuk pagination
        String countQuery = "SELECT COUNT(*) FROM " + SCHEMA + "." + TABLE +
                " WHERE status = ? AND (" +
                "description LIKE ? OR " +
                "id_product LIKE ? OR " +
                "id_departement LIKE ? OR " +
                "name_departement LIKE ? OR " +
                "allocation_id_proyek LIKE ? " +
                ")";

        // Menghitung total hasil
        Integer count = count(countQuery,
                param.getStatus(), // parameter status
                wildcardValue,     // description
                wildcardValue,     // id_product
                wildcardValue,     // id_departement
                wildcardValue,     // name_departement
                wildcardValue      // allocation_id_proyek
        );

        PaginationModel<List<AllocationCostDto>> paginationModel = new PaginationModel<>();
        paginationModel.setPayload(result);
        paginationModel.setTotal(count);
        paginationModel.setPage(param.getPage());
        paginationModel.setLimit(param.getLimit());
        paginationModel.builderInformation();

        return paginationModel;
    }

    // Metode untuk memperbarui status RequestType
    @Override
    public void updateAllocationStatus(int id, int status, Timestamp updated_at) {
        String sql = "UPDATE " + SCHEMA + "." + TABLE + " SET status = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, status, updated_at, id);
    }
    @Override
    public void deleteAlocationCostById(DeleteAllocationCostById deleteAllocationCostById, Timestamp updated_at) {
        updateAllocationStatus(deleteAllocationCostById.getId(), 0, updated_at); // Set status menjadi 0
    }
    @Override
    public AllocationCostDto getById(int id) throws Exception {
        return getById(1, id);
    }
    @Override
    public AllocationCostDto getById(int status, int id) throws Exception {
        if (id == 0) {
            throw new Exception("Id cannot be null");
        }
        String query = "SELECT * FROM " + SCHEMA + "." + TABLE + " WHERE status = ? AND id = ?";
        return firstQuery(query, status, id);
    }

    // data benar \\
//    @Override
//    public void save(AllocationCostDto allocationCostDto) {
//        List<SqlParameterValue> parameters = this.insertAllocationCostMapper.apply(allocationCostDto);
//        SqlParameterValue[] parameterArray = parameters.toArray(new SqlParameterValue[0]);
//        PreparedStatementSetter pss = new ArgumentPreparedStatementSetter(parameterArray);
//        this.jdbcTemplate.update(
//                AllocationCostSql.INSERT_INTO.getCommand().formatted(
//                        new StringBuilder()
//                                .append(SCHEMA)
//                                .append(".")
//                                .append(TABLE)
//                                .toString(),
//                        AllocationCostSql.ALL_INSERT.getCommand()
//                ),
//                pss
//        );
//    }

    @Override
    public void save(AllocationCostDto allocationCostDto) {
    List<SqlParameterValue> parameters = this.insertAllocationCostMapper.apply(allocationCostDto);
    SqlParameterValue[] parameterArray = parameters.toArray(new SqlParameterValue[0]);
    PreparedStatementSetter pss = new ArgumentPreparedStatementSetter(parameterArray);

    String query = AllocationCostSql.INSERT_INTO.getCommand().formatted(
            new StringBuilder()
                    .append(SCHEMA)
                    .append(".")
                    .append(TABLE)
                    .toString(),
            AllocationCostSql.FUNCTION_INSERT.getCommand()
         );
        this.jdbcTemplate.update(query, pss);
    }

    private String queryGetBySearch(String select, String search) {
        return String.format(
                AllocationCostSql.GET_ALL.getCommand(),
                select,
                SCHEMA + "." + TABLE,
                search
        );
    }
    private String queryFindBy(String select, String search) {
        return String.format(
                AllocationCostSql.FIND_BY.getCommand(),
                select,
                SCHEMA + "." + TABLE,
                search
        );
    }
    private List<AllocationCostDto> findQuery(String query, Object... args) {
        return this.jdbcTemplate.query(
                query,
                args,
                this.getAllocationCostMapper
        );
    }
    private AllocationCostDto firstQuery(String query, Object... args) {
        List<AllocationCostDto> result = this.jdbcTemplate.query(
                query,
                this.getAllocationCostMapper, args
        );
        return !result.isEmpty() ? result.get(0) : null;
    }
    private Integer count(String query, Object... args) {
        return this.jdbcTemplate.queryForObject(
                query,
                Integer.class,
                args
        );
    }

    @Override
    public PaginationModel<List<AllocationCostDto>> search(Search search, ParamSearchAllocationCost param) {
        List<Object> data = new ArrayList<>();
        data.add(param.getStatus());

        String searchQuery = search.buildSearch();
        searchQuery = !searchQuery.isEmpty() ? "AND " + searchQuery : "";
        String query = queryGetBySearch(AllocationCostSql.ALL.getCommand(), searchQuery);
        String queryCount = queryGetBySearch(AllocationCostSql.COUNT.getCommand(), searchQuery);

        String limitSkip = AllocationCostSql.LIMIT_SKIP.getCommand();
        data.add(param.getLimit());
        data.add((param.getPage() - 1) * param.getLimit());

        List<AllocationCostDto> result = findQuery(query + " " + limitSkip, data.toArray());

        List<Object> dataCount = new ArrayList<>(data);
        dataCount.remove(dataCount.size() - 1);
        dataCount.remove(dataCount.size() - 1);
        Integer count = count(queryCount, dataCount.toArray());

        PaginationModel<List<AllocationCostDto>> paginationModel = new PaginationModel<>();
        paginationModel.setPayload(result);
        paginationModel.setTotal(count);
        paginationModel.setPage(param.getPage());
        paginationModel.setLimit(param.getLimit());
        paginationModel.builderInformation();

        return paginationModel;
    }
    @Override
    public Integer countAll(ParamSearchAllocationCost param) {
        List<Object> data = new ArrayList<>();
        data.add(param.getStatus());

        String query = "SELECT COUNT(*) FROM " + SCHEMA + "." + TABLE + " WHERE status = ?";


        return count(query, data.toArray());
    }
    @Override
    public List<AllocationCostDto> findAll(ParamSearchAllocationCost param) {
        List<Object> data = new ArrayList<>();
        String query = "SELECT * FROM " + SCHEMA + "." + TABLE + " WHERE status = ? LIMIT ? OFFSET ?";

        data.add(param.getStatus());
        data.add(param.getLimit());
        data.add((param.getPage() - 1) * param.getLimit());

        return findQuery(query, data.toArray());
    }

    @Override
    public void updateAllocationById(UpdateAllocationCostById updateAllocationCostById) {
        List<SqlParameterValue> parameters = new ArrayList<>(this.updateAllocationCostMapper.apply(updateAllocationCostById));
        SqlParameterValue idParam = parameters.remove(0);
        parameters.add(idParam);

        PreparedStatementSetter pss = new ArgumentPreparedStatementSetter(parameters.toArray());
        String query = String.format(
                AllocationCostSql.UPDATE_BY_ID.getCommand(),
                SCHEMA + "." + TABLE,
                AllocationCostSql.FUNCTION_UPDATE.getCommand(),
                AllocationCostSql.ID_FIND.getCommand()
        );
        this.jdbcTemplate.update(query, pss);
    }

    @Override
    public void deleteAllocationById(int id) {
        String query = AllocationCostSql.DELETE_BY_ID.getCommand().formatted(
                new StringBuilder()
                        .append(SCHEMA)
                        .append(".")
                        .append(TABLE)
                        .toString(),
                AllocationCostSql.FUNCTION_DELETE.getCommand(),
                AllocationCostSql.ID_FIND.getCommand()
        );
        this.jdbcTemplate.update(query, id);
    }
}