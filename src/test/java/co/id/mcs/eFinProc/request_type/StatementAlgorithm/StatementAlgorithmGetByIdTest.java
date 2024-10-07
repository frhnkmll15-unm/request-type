package co.id.mcs.eFinProc.request_type.StatementAlgorithm;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 17/09/2024 08:38
@Last Modified 17/09/2024 08:38
Version 1.0
*/




import co.id.mcs.eFinProc.request_type.Dao.StatementAlgorithmDao;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.AllocationCostDto;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.RequestTypeDto;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.StatementAlgorithmDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StatementAlgorithmGetByIdTest {

    @Autowired
    StatementAlgorithmDao statementAlgorithmDao;

    @Test
    void getByIdTest() throws Exception {

        int id = 2;


        StatementAlgorithmDto statementAlgorithm = statementAlgorithmDao.getById(id);


        if (statementAlgorithm == null) {
            throw new Exception("Statement tidak ditemukan untuk ID: " + id);
        }


        System.out.println(statementAlgorithm.toString());

    }
}
