package co.id.mcs.eFinProc.request_type.Dao.Component.StatementAlgorithm;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 15/09/2024 09:55
@Last Modified 15/09/2024 09:55
Version 1.0
*/

import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.DeleteStatementAlgorithmById;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Log4j2
@Component
public class DeleteStatementAlgorithmMapper implements Function<DeleteStatementAlgorithmById, List<SqlParameterValue>> {


    @Override
    public List<SqlParameterValue> apply(DeleteStatementAlgorithmById deleteStatementAlgorithmById) {

        Timestamp updated_at = new Timestamp(System.currentTimeMillis());

        return Arrays.asList(
                new SqlParameterValue(Types.INTEGER, deleteStatementAlgorithmById.getStatus()), // Status
                new SqlParameterValue(Types.INTEGER, deleteStatementAlgorithmById.getId()),
                new SqlParameterValue(Types.TIMESTAMP, updated_at)
        );
    }
}
