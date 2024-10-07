package co.id.mcs.eFinProc.request_type.Dao.Component.StatementAlgorithm;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 15/09/2024 09:39
@Last Modified 15/09/2024 09:39
Version 1.0
*/

import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.StatementAlgorithmDto;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Log4j2
@Component
public class InsertStatementAlgorithmMapper implements Function<StatementAlgorithmDto, List<SqlParameterValue>> {

    @SneakyThrows
    @Override
    public List<SqlParameterValue> apply(StatementAlgorithmDto statementAlgorithm) {
        log.info("Preparing mapper data for inserting statement algorithm: " + statementAlgorithm.getType());
        return Arrays.asList(
                new SqlParameterValue(Types.INTEGER, statementAlgorithm.getId_type_request_eq()), // Menambahkan id_type_request_eq
                new SqlParameterValue(Types.VARCHAR, statementAlgorithm.getType()),
                new SqlParameterValue(Types.VARCHAR, statementAlgorithm.getLogic()),
                new SqlParameterValue(Types.INTEGER, statementAlgorithm.getStatus()),
                new SqlParameterValue(Types.VARCHAR, statementAlgorithm.getCommand())
        );
    }
}
