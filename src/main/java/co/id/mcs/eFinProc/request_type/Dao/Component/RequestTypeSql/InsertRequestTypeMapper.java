package co.id.mcs.eFinProc.request_type.Dao.Component.RequestTypeSql;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 13:41
@Last Modified 13/09/2024 13:41
Version 1.0
*/



import co.id.mcs.eFinProc.request_type.Pojo.RequestType.RequestTypeDto;
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
public class InsertRequestTypeMapper implements Function<RequestTypeDto, List<SqlParameterValue>> {

    @SneakyThrows
    @Override
    public List<SqlParameterValue> apply(RequestTypeDto requestTypeDto) {
        log.info("Prepare mapper data insert by id : " + requestTypeDto.getId());
        return Arrays.asList(
                new SqlParameterValue(Types.VARCHAR, requestTypeDto.getName()),
                new SqlParameterValue(Types.INTEGER, requestTypeDto.getInquiryPeriodMin()),
                new SqlParameterValue(Types.INTEGER, requestTypeDto.getInquiryPeriodMax()),
                new SqlParameterValue(Types.BIGINT, requestTypeDto.getAmountMin()),
                new SqlParameterValue(Types.BIGINT, requestTypeDto.getAmountMax()),
                new SqlParameterValue(Types.VARCHAR, requestTypeDto.getType()),
                new SqlParameterValue(Types.INTEGER, requestTypeDto.getStatus()),
                new SqlParameterValue(Types.VARCHAR, requestTypeDto.getDescription()),
                new SqlParameterValue(Types.INTEGER, requestTypeDto.getOutstanding()),
                new SqlParameterValue(Types.BOOLEAN, requestTypeDto.getAttachment()),
                new SqlParameterValue(Types.VARCHAR, requestTypeDto.getApprovalPeriodType()),
                new SqlParameterValue(Types.BOOLEAN, requestTypeDto.getAllocationCost()),
                new SqlParameterValue(Types.BOOLEAN, requestTypeDto.getBudgeting()));

    }
}

