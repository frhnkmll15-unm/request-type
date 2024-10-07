package co.id.mcs.eFinProc.request_type.StatementAlgorithm;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 17/09/2024 09:30
@Last Modified 17/09/2024 09:30
Version 1.0
*/

import co.id.mcs.eFinProc.request_type.Dao.StatementAlgorithmDao;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.UpdateStatementAlgorithmById;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.StatementAlgorithmDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class StatementAlgorithmUpdateTest {

    @Autowired
    StatementAlgorithmDao statementAlgorithmDao;

    @Test
    void updateStatementAlgorithmByIdTest() throws Exception {

        StatementAlgorithmDto data = statementAlgorithmDao.getById(2);
        if (data == null) {
            throw new Exception("User tidak ditemukan");
        }

        UpdateStatementAlgorithmById change = new UpdateStatementAlgorithmById();
        change.setId(data.getId());
        change.setType("updatedType");
        change.setLogic("updatedLogic");
        change.setStatus(1);
        change.setCommand("updatedCommand");

        statementAlgorithmDao.updateStatementAlgorithmById(change);
        System.out.println("Update berhasil");
    }
}
