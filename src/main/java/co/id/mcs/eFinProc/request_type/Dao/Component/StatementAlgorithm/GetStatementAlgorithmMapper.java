package co.id.mcs.eFinProc.request_type.Dao.Component.StatementAlgorithm;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 15/09/2024 10:00
@Last Modified 15/09/2024 10:00
Version 1.0
*/

import co.id.mcs.eFinProc.request_type.Constant.StatementAlgorithmSql;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.StatementAlgorithmDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
@Component
public class GetStatementAlgorithmMapper implements RowMapper<StatementAlgorithmDto> {

    @Override
    public StatementAlgorithmDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        log.info("Set up the data mapper to register the statement algorithm id: " + rs.getInt(StatementAlgorithmSql.ID.getCommand()) + " to the object");

        StatementAlgorithmDto data = new StatementAlgorithmDto();
        data.setId(rs.getInt(StatementAlgorithmSql.ID.getCommand()));
        data.setId_type_request_eq(rs.getInt(StatementAlgorithmSql.ID_TYPE_REQUEST_EQ.getCommand())); // Menambahkan id_type_request_eq
        data.setType(rs.getString(StatementAlgorithmSql.TYPE.getCommand()));
        data.setLogic(rs.getString(StatementAlgorithmSql.LOGIC.getCommand()));
        data.setStatus(rs.getInt(StatementAlgorithmSql.STATUS.getCommand()));
        data.setCommand(rs.getString(StatementAlgorithmSql.COMMAND.getCommand()));
        data.setUpdatedAt(rs.getTimestamp(StatementAlgorithmSql.UPDATED_AT.getCommand()));
        data.setCreatedAt(rs.getTimestamp(StatementAlgorithmSql.CREATED_AT.getCommand()));

        return data;
    }
}
