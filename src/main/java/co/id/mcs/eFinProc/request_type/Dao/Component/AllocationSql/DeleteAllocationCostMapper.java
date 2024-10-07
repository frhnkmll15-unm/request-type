package co.id.mcs.eFinProc.request_type.Dao.Component.AllocationSql;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 01:00
@Last Modified 13/09/2024 01:00
Version 1.0
*/





import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.DeleteAllocationCostById;
import lombok.extern.log4j.Log4j2;
import org.springframework.cglib.core.internal.Function;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;


@Log4j2
@Component
public class DeleteAllocationCostMapper implements Function<DeleteAllocationCostById, List<SqlParameterValue>> {


    @Override
    public List<SqlParameterValue> apply(DeleteAllocationCostById allocationCostById) {
        Timestamp updated_at = new Timestamp(System.currentTimeMillis());

        return Arrays.asList(
                new SqlParameterValue(Types.INTEGER, allocationCostById.getStatus()), // Status
                new SqlParameterValue(Types.INTEGER, allocationCostById.getId()),
                new SqlParameterValue(Types.TIMESTAMP, updated_at)
        );
    }
}


