package co.id.mcs.eFinProc.request_type.StatementAlgorithm;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 15/09/2024 21:54
@Last Modified 15/09/2024 21:54
Version 1.0
*/



import co.id.mcs.eFinProc.request_type.Dao.StatementAlgorithmDao;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.ParamSearchStatementAlgorithm;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.StatementAlgorithmDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StatementAlgorithmGetAllTest {

    @Autowired
    StatementAlgorithmDao statementAlgorithmDao;

    @Test
    void contextLoads() {
        ParamSearchStatementAlgorithm param = new ParamSearchStatementAlgorithm();
        param.setStatus(0);
        List<StatementAlgorithmDto> statementAlgorithms = statementAlgorithmDao.findAll(param);
        System.out.println(statementAlgorithms.toString());


    }
}
