package co.id.mcs.eFinProc.request_type.Dao.Component.RequestTypeSql;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 13:42
@Last Modified 13/09/2024 13:42
Version 1.0
*/


import co.id.mcs.eFinProc.request_type.Pojo.RequestType.UpdateRequestTypeById;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Log4j2
@Component
public class UpdateRequestTypeMapper implements Function<UpdateRequestTypeById, List<SqlParameterValue>> {

    @SneakyThrows
    @Override
    public List<SqlParameterValue> apply(UpdateRequestTypeById updateRequestTypeById) {
        List<SqlParameterValue> parameters = new ArrayList<>();

        parameters.add(new SqlParameterValue(Types.INTEGER, updateRequestTypeById.getId()));
        parameters.add(new SqlParameterValue(Types.VARCHAR, updateRequestTypeById.getName()));
        parameters.add(new SqlParameterValue(Types.INTEGER, updateRequestTypeById.getInquiryPeriodMin()));
        parameters.add(new SqlParameterValue(Types.INTEGER, updateRequestTypeById.getInquiryPeriodMax()));
        parameters.add(new SqlParameterValue(Types.BIGINT, updateRequestTypeById.getAmountMin()));
        parameters.add(new SqlParameterValue(Types.BIGINT, updateRequestTypeById.getAmountMax()));
        parameters.add(new SqlParameterValue(Types.VARCHAR, updateRequestTypeById.getType()));
        parameters.add(new SqlParameterValue(Types.INTEGER, updateRequestTypeById.getStatus())); // Pastikan ada metode getStatus()
        parameters.add(new SqlParameterValue(Types.VARCHAR, updateRequestTypeById.getDescription()));
        parameters.add(new SqlParameterValue(Types.INTEGER, updateRequestTypeById.getOutstanding()));
        parameters.add(new SqlParameterValue(Types.BOOLEAN, updateRequestTypeById.isAttachment())); // Pastikan metode ini ada
        parameters.add(new SqlParameterValue(Types.VARCHAR, updateRequestTypeById.getApprovalPeriodType()));
        parameters.add(new SqlParameterValue(Types.BOOLEAN, updateRequestTypeById.isAllocationCost())); // Pastikan metode ini ada
        parameters.add(new SqlParameterValue(Types.BOOLEAN, updateRequestTypeById.isBudgeting())); // Pastikan metode ini ada
        parameters.add(new SqlParameterValue(Types.TIMESTAMP, updateRequestTypeById.getUpdatedAt())); // Pastikan ada metode getUpdatedAt()

        return parameters;
    }
}
