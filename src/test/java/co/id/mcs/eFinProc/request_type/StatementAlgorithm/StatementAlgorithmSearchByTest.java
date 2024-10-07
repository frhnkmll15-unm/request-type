package co.id.mcs.eFinProc.request_type.StatementAlgorithm;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 17/09/2024 08:53
@Last Modified 17/09/2024 08:53
Version 1.0
*/

import co.id.mcs.eFinProc.FilterSearch;
import co.id.mcs.eFinProc.PaginationModel;
import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Dao.StatementAlgorithmDao;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.ParamSearchStatementAlgorithm;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.StatementAlgorithmDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class StatementAlgorithmSearchByTest {

    @Autowired
    StatementAlgorithmDao statementAlgorithmDao;

    @Test
    void contextLoads() {


        Set<FilterSearch> filters = new HashSet<>();
        FilterSearch filter1 = new FilterSearch();
        filter1.setField("logic");
        filter1.setAliasField(null);
        filters.add(filter1);

        FilterSearch filter2 = new FilterSearch();
        filter2.setField("type");
        filter2.setAliasField(null);
        filters.add(filter2);


        Search search = new Search();
        search.setValue("exampleValue"); // Value to search for
        search.setFilter(filters);


        ParamSearchStatementAlgorithm param = new ParamSearchStatementAlgorithm();
        param.setPage(1);
        param.setLimit(10);
        param.setStatus(1);


        PaginationModel<List<StatementAlgorithmDto>> page = statementAlgorithmDao.search(search, param);

        System.out.println(page.toString());
    }
}

