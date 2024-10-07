package co.id.mcs.eFinProc.request_type.AllocationCost;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 17/09/2024 14:54
@Last Modified 17/09/2024 14:54
Version 1.0
*/

import co.id.mcs.eFinProc.FilterSearch;
import co.id.mcs.eFinProc.PaginationModel;
import co.id.mcs.eFinProc.Search;
import co.id.mcs.eFinProc.request_type.Dao.AllocationCostDao;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.AllocationCostDto;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.ParamSearchAllocationCost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class AllocationCostSearchTest {

    @Autowired
    AllocationCostDao allocationCostDao;

    @Test
    void testSearchAllocationCost() {
        Set<FilterSearch> filters = new HashSet<>();

        FilterSearch filter1 = new FilterSearch();
        filter1.setField("id_product");
        filter1.setAliasField(null);
        filters.add(filter1);

        FilterSearch filter2 = new FilterSearch();
        filter2.setField("name_departement");
        filter2.setAliasField(null);
        filters.add(filter2);

        Search test = new Search();
        test.setValue("ucen2");
        test.setFilter(filters);

        ParamSearchAllocationCost param = new ParamSearchAllocationCost();
        param.setPage(1);
        param.setLimit(10);
        param.setStatus(1);


        PaginationModel<List<AllocationCostDto>> page = allocationCostDao.search(test, param);
        System.out.println(page.toString());
    }
}
