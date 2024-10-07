package co.id.mcs.eFinProc.request_type.StatementAlgorithm;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 17/09/2024 09:45
@Last Modified 17/09/2024 09:45
Version 1.0
*/



import co.id.mcs.eFinProc.request_type.Dao.StatementAlgorithmDao;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.DeleteStatementAlgorithmById;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.StatementAlgorithmDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class StatementAlgorithmDeleteTest {

    @Autowired
    StatementAlgorithmDao statementAlgorithmDao;

    @Test
    void deleteStatementAlgorithmByIdTest() throws Exception {

        Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
        statementAlgorithmDao.deleteStatementAlgorithmById(-1,updatedAt ,1);
        System.out.println("success");
    }
}
