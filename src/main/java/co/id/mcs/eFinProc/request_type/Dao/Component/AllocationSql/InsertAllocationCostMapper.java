package co.id.mcs.eFinProc.request_type.Dao.Component.AllocationSql;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 13:45
@Last Modified 13/09/2024 13:45
Version 1.0
*/



import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.AllocationCostDto;
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
public class InsertAllocationCostMapper implements Function<AllocationCostDto, List<SqlParameterValue>> {

    @SneakyThrows
    @Override
    public List<SqlParameterValue> apply(AllocationCostDto allocationCostDto) {
        log.info("Prepare mapper data insert by id : " + allocationCostDto.getId());
        return Arrays.asList(
                new SqlParameterValue(Types.VARCHAR, allocationCostDto.getId_product()),
                new SqlParameterValue(Types.VARCHAR, allocationCostDto.getId_department()),
                new SqlParameterValue(Types.VARCHAR, allocationCostDto.getName_departement()),
                new SqlParameterValue(Types.INTEGER, allocationCostDto.getType()),
                new SqlParameterValue(Types.INTEGER, allocationCostDto.getStatus()),
                new SqlParameterValue(Types.VARCHAR, allocationCostDto.getAllocation_id_project()),
                new SqlParameterValue(Types.INTEGER, allocationCostDto.getPercentase()),
                new SqlParameterValue(Types.VARCHAR, allocationCostDto.getDescription())
        );
    }
}
