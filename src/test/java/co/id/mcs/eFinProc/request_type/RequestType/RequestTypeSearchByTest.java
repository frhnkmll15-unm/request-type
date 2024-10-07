package co.id.mcs.eFinProc.request_type.RequestType;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 17/09/2024 20:07
@Last Modified 17/09/2024 20:07
Version 1.0
*/

import co.id.mcs.eFinProc.FilterSearch;
import co.id.mcs.eFinProc.PaginationModel;
import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Dao.RequestTypeDao;
import co.id.mcs.eFinProc.request_type.Dao.StatementAlgorithmDao;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.ParamSearchRequestType;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.RequestTypeDto;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.ParamSearchStatementAlgorithm;
import co.id.mcs.eFinProc.request_type.Pojo.StatementALgorithm.StatementAlgorithmDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class RequestTypeSearchByTest {
    @Autowired
    RequestTypeDao requestTypeDao;

    @Test
    void contextLoads() {
        Set<FilterSearch> filters = new HashSet<>();

        FilterSearch filter1 = new FilterSearch();
        filter1.setField("name");
        filter1.setAliasField(null);
        filters.add(filter1);

        FilterSearch filter2 = new FilterSearch();
        filter2.setField("approval_period_type");
        filter2.setAliasField(null);
        filters.add(filter2);

        Search test = new Search();
        test.setValue("day");
        test.setFilter(filters);

        ParamSearchRequestType param = new ParamSearchRequestType();

        param.setPage(1);
        param.setLimit(10);
        param.setStatus(1);

        PaginationModel<List<RequestTypeDto>> page = requestTypeDao.search(test, param);

        System.out.println(page.toString());
    }
}


