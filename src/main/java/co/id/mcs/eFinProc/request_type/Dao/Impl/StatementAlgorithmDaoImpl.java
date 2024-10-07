package co.id.mcs.eFinProc.request_type.Dao.Impl;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 15/09/2024 09:48
@Last Modified 15/09/2024 09:48
Version 1.0
*/

import co.id.mcs.eFinProc.PaginationModel;
import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Constant.RequestTypeSql;
import co.id.mcs.eFinProc.request_type.Constant.StatementAlgorithmSql;
import co.id.mcs.eFinProc.request_type.Dao.Component.StatementAlgorithm.DeleteStatementAlgorithmMapper;
import co.id.mcs.eFinProc.request_type.Dao.Component.StatementAlgorithm.GetStatementAlgorithmMapper;
import co.id.mcs.eFinProc.request_type.Dao.Component.StatementAlgorithm.InsertStatementAlgorithmMapper;
import co.id.mcs.eFinProc.request_type.Dao.Component.StatementAlgorithm.UpdateStatementAlgorithmMapper;
import co.id.mcs.eFinProc.request_type.Dao.StatementAlgorithmDao;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.DeleteStatementAlgorithmById;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.ParamSearchStatementAlgorithm;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.StatementAlgorithmDto;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.UpdateStatementAlgorithmById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Repository
public class StatementAlgorithmDaoImpl implements StatementAlgorithmDao {

    private JdbcTemplate jdbcTemplate;
    private static final String SCHEMA = StatementAlgorithmSql.SCHEMA.getCommand();
    private static final String TABLE = StatementAlgorithmSql.TABLENAME.getCommand();

    private Function<StatementAlgorithmDto, List<SqlParameterValue>> insertStatementAlgorithmMapper;
    private Function<UpdateStatementAlgorithmById, List<SqlParameterValue>> updateStatementAlgorithmMapper;
    private Function<DeleteStatementAlgorithmById, List<SqlParameterValue>> deleteStatementAlgorithmMapper;
    private RowMapper<StatementAlgorithmDto> getStatementAlgorithmMapper;

    @Autowired
    public void setDataSource(@Qualifier("dataSource") final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertStatementAlgorithmMapper = new InsertStatementAlgorithmMapper();
        this.updateStatementAlgorithmMapper = new UpdateStatementAlgorithmMapper();
        this.deleteStatementAlgorithmMapper = new DeleteStatementAlgorithmMapper();
        this.getStatementAlgorithmMapper = new GetStatementAlgorithmMapper();
    }

    @Override
    public PaginationModel<List<StatementAlgorithmDto>> searchByName(String name, ParamSearchStatementAlgorithm param) {
        List<Object> data = new ArrayList<>();
        data.add(param.getStatus());

        // Wildcard untuk pencarian
        String wildcardValue = "%" + name + "%";
        String prefixValue = name.substring(0, Math.min(3, name.length())) + "%";

        // Query untuk mengambil data
        String query = "SELECT * FROM " + SCHEMA + "." + TABLE +
                " WHERE status = ? AND (" +
                "type LIKE ? OR " +
                "logic LIKE ? OR " +
                "command LIKE ? OR " +
                "created_at::TEXT LIKE ? OR " +
                "updated_at::TEXT LIKE ? " +
                "OR type LIKE ? OR " +  // Mencari berdasarkan 3 huruf depan
                "logic LIKE ? OR " +
                "command LIKE ?" +
                ")" +
                " LIMIT ? OFFSET ?";

        // Menambahkan data untuk pencarian
        data.add(wildcardValue);
        data.add(wildcardValue);
        data.add(wildcardValue);
        data.add(wildcardValue);
        data.add(wildcardValue);
        data.add(prefixValue);
        data.add(prefixValue);
        data.add(prefixValue);

        // Menambahkan limit dan offset
        data.add(param.getLimit());
        data.add((param.getPage() - 1) * param.getLimit());

        List<StatementAlgorithmDto> result = findQuery(query, data.toArray());

        // Hitung total untuk pagination
        String countQuery = "SELECT COUNT(*) FROM " + SCHEMA + "." + TABLE +
                " WHERE status = ? AND (" +
                "type LIKE ? OR " +
                "logic LIKE ? OR " +
                "command LIKE ? OR " +
                "created_at::TEXT LIKE ? OR " +
                "updated_at::TEXT LIKE ? " +
                "OR type LIKE ? OR " +
                "logic LIKE ? OR " +
                "command LIKE ?" +
                ")";

        // Menghitung total hasil
        Integer count = count(countQuery, param.getStatus(), wildcardValue, wildcardValue, wildcardValue, wildcardValue, wildcardValue, prefixValue, prefixValue, prefixValue);

        PaginationModel<List<StatementAlgorithmDto>> paginationModel = new PaginationModel<>();
        paginationModel.setPayload(result);
        paginationModel.setTotal(count);
        paginationModel.setPage(param.getPage());
        paginationModel.setLimit(param.getLimit());
        paginationModel.builderInformation();

        return paginationModel;
    }

    @Override
    public void updateStatementStatus(int id, int status, Timestamp updated_at) {
        String sql = "UPDATE " + SCHEMA + "." + TABLE + " SET status = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, status, updated_at, id);
    }

    @Override
    public void deleteStatementAlgorithmById(DeleteStatementAlgorithmById deleteStatementAlgorithmById, Timestamp updated_at) {
        updateStatementStatus(deleteStatementAlgorithmById.getId(), 0, updated_at); // Set status menjadi 0
    }
    @Override
    public StatementAlgorithmDto getById(int id) throws Exception {
        return getById(1, id);
    }

    @Override
    public StatementAlgorithmDto getById(int status, int id) throws Exception {
        if (id == 0) {
            throw new Exception("Id cannot be null");
        }
        String query = "SELECT * FROM " + SCHEMA + "." + TABLE + " WHERE status = ? AND id = ?";
        return firstQuery(query, status, id);
    }

    @Override
    public Integer countAll(ParamSearchStatementAlgorithm param) {
        List<Object> data = new ArrayList<>();
        data.add(param.getStatus());

        String query = "SELECT COUNT(*) FROM " + SCHEMA + "." + TABLE + " WHERE status = ?";

        // Tambahkan kondisi lain jika perlu berdasarkan parameter lain di ParamSearchRequestType

        return count(query, data.toArray());
    }


    @Override
    public void save(StatementAlgorithmDto statementAlgorithmDto) {
        List<SqlParameterValue> parameters = this.insertStatementAlgorithmMapper.apply(statementAlgorithmDto);
        SqlParameterValue[] parameterArray = parameters.toArray(new SqlParameterValue[0]);
        PreparedStatementSetter pss = new ArgumentPreparedStatementSetter(parameterArray);
        this.jdbcTemplate.update(
                StatementAlgorithmSql.INSERT_INTO.getCommand().formatted(
                        new StringBuilder()
                                .append(SCHEMA)
                                .append(".")
                                .append(TABLE)
                                .toString(),
                        StatementAlgorithmSql.ALL_INSERT.getCommand()
                ),
                pss
        );
    }
    @Override
    public void updateStatementAlgorithmById(UpdateStatementAlgorithmById updateStatementAlgorithmById) {
        List<SqlParameterValue> parameters = new ArrayList<>(this.updateStatementAlgorithmMapper.apply(updateStatementAlgorithmById));
        SqlParameterValue id =parameters.remove(0);
        parameters.add(id);
        PreparedStatementSetter pss = new ArgumentPreparedStatementSetter(parameters.toArray());
        String query = StatementAlgorithmSql.UPDATE_BY_ID.getCommand().formatted(
                new StringBuilder()
                        .append(SCHEMA)
                        .append(".")
                        .append(TABLE)
                        .toString(),
                StatementAlgorithmSql.UPDATE.getCommand(),
                StatementAlgorithmSql.ID_FIND.getCommand()
        );
        this.jdbcTemplate.update(
                query,
                pss
        );
    }




    private String queryGetBySearch(String select, String search) {
        return RequestTypeSql.GET_ALL.getCommand().formatted(
                select,
                new StringBuilder()
                        .append(SCHEMA)
                        .append(".")
                        .append(TABLE)
                        .toString(),
                search
        );
    }

    private String queryFindBy(String select, String search) {
        return StatementAlgorithmSql.FIND_BY.getCommand().formatted(
                select,
                new StringBuilder()
                        .append(SCHEMA)
                        .append(".")
                        .append(TABLE)
                        .toString(),
                search
        );
    }

    private List<StatementAlgorithmDto> findQuery(String query, Object... args) {
        return this.jdbcTemplate.query(
                query,
                this.getStatementAlgorithmMapper, args
        );
    }

    private StatementAlgorithmDto firstQuery(String query, Object... args) {
        List<StatementAlgorithmDto> result = this.jdbcTemplate.query(
                query,
                this.getStatementAlgorithmMapper, args
        );
        return !result.isEmpty() ? result.get(0) : null;
    }

    private Integer count(String query, Object... args) {
        return this.jdbcTemplate.queryForObject(
                query,
                Integer.class, // Tipe hasil yang diharapkan
                args // Argumen untuk query
        );
    }


    @Override
    public void deleteStatementAlgorithmById(Object ... value) {

        String query = StatementAlgorithmSql.DELETE_BY_ID.getCommand().formatted(
                new StringBuilder()
                        .append(SCHEMA)
                        .append(".")
                        .append(TABLE)
                        .toString(),
                StatementAlgorithmSql.DELETE.getCommand(),
                StatementAlgorithmSql.ID_FIND.getCommand()
        );
        this.jdbcTemplate.update(
                query,
                value
        );
    }
    @Override
    public List<StatementAlgorithmDto> findAll(ParamSearchStatementAlgorithm param) {
        List<Object> data = new ArrayList<>();
        String query = "SELECT * FROM " + SCHEMA + "." + TABLE + " WHERE status = ? LIMIT ? OFFSET ?";

        data.add(param.getStatus());
        data.add(param.getLimit()); // Menambahkan limit
        data.add((param.getPage() - 1) * param.getLimit()); // Menghitung offset

        return findQuery(query, data.toArray());
    }

    @Override
    public PaginationModel<List<StatementAlgorithmDto>> search(Search search, ParamSearchStatementAlgorithm param) {
        List<Object> data = new ArrayList<>();
        data.add(param.getStatus());

        String searchQuery = search.buildSearch();
        searchQuery = !searchQuery.isEmpty() ? "AND " + searchQuery : "";
        String query = StatementAlgorithmSql.GET_ALL.getCommand().formatted(
                StatementAlgorithmSql.ALL.getCommand(),
                SCHEMA + "." + TABLE,
                searchQuery
        );
        String queryCount = StatementAlgorithmSql.GET_ALL.getCommand().formatted(
                StatementAlgorithmSql.COUNT.getCommand(),
                SCHEMA + "." + TABLE,
                searchQuery
        );

        String limitSkip = StatementAlgorithmSql.LIMIT_SKIP.getCommand();
        data.add(param.getLimit());
        data.add((param.getPage() - 1) * param.getLimit());

        List<StatementAlgorithmDto> result = this.jdbcTemplate.query(query + " " + limitSkip,
                data.toArray(),
                this.getStatementAlgorithmMapper
        );

        List<Object> dataCount = new ArrayList<>(data);
        dataCount.remove(dataCount.size() - 1); // Remove limit
        dataCount.remove(dataCount.size() - 1); // Remove offset
        Integer count = this.jdbcTemplate.queryForObject(queryCount,
                Integer.class,
                dataCount.toArray()
        );

        PaginationModel<List<StatementAlgorithmDto>> paginationModel = new PaginationModel<>();
        paginationModel.setPayload(result);
        paginationModel.setTotal(count);
        paginationModel.setPage(param.getPage());
        paginationModel.setLimit(param.getLimit());
        paginationModel.builderInformation();

        return paginationModel;
    }


}

