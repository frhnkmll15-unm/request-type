package co.id.mcs.eFinProc.request_type.Dao.Component.AllocationSql;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 13:46
@Last Modified 13/09/2024 13:46
Version 1.0
*/



import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.UpdateAllocationCostById;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

;

@Log4j2
@Component
public class UpdateAllocationCostMapper implements Function<UpdateAllocationCostById, List<SqlParameterValue>> {

    @Override
    public List<SqlParameterValue> apply(UpdateAllocationCostById updateAllocationCostById) {
        log.info("Setting up data mapper to update allocation cost with ID: " + updateAllocationCostById.getId());

        // Sesuaikan parameter untuk update
        List<SqlParameterValue> parameters = new ArrayList<>();
        parameters.add(new SqlParameterValue(Types.INTEGER, Integer.parseInt(String.valueOf(updateAllocationCostById.getId()))));
        parameters.add(new SqlParameterValue(Types.VARCHAR, updateAllocationCostById.getId_product()));
        parameters.add(new SqlParameterValue(Types.VARCHAR, updateAllocationCostById.getName_departement()));
        parameters.add(new SqlParameterValue(Types.VARCHAR, updateAllocationCostById.getName_departement()));
        parameters.add(new SqlParameterValue(Types.INTEGER, updateAllocationCostById.getType()));
        parameters.add(new SqlParameterValue(Types.INTEGER, updateAllocationCostById.getStatus()));
        parameters.add(new SqlParameterValue(Types.VARCHAR, updateAllocationCostById.getAllocation_id_project()));
        parameters.add(new SqlParameterValue(Types.INTEGER, updateAllocationCostById.getPercentase()));
        parameters.add(new SqlParameterValue(Types.VARCHAR, updateAllocationCostById.getDescription()));
        parameters.add(new SqlParameterValue(Types.TIMESTAMP, updateAllocationCostById.getUpdated_at()));
        return parameters;
    }

}
