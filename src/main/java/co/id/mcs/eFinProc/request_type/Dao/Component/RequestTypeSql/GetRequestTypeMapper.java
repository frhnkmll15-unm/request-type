package co.id.mcs.eFinProc.request_type.Dao.Component.RequestTypeSql;/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 13:44
@Last Modified 13/09/2024 13:44
Version 1.0
*/



import co.id.mcs.eFinProc.request_type.Constant.RequestTypeSql;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.RequestTypeDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
@Component
public class GetRequestTypeMapper implements RowMapper<RequestTypeDto> {

    @Override
    public RequestTypeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        log.info("Set up the data mapper to register the request type id : " + rs.getString(RequestTypeSql.ID.getCommand()) + " to the object");

        RequestTypeDto data = new RequestTypeDto();
        data.setId(rs.getInt(RequestTypeSql.ID.getCommand()));
        data.setName(rs.getString(RequestTypeSql.NAME.getCommand()));
        data.setInquiryPeriodMin(rs.getInt(RequestTypeSql.INQUIRY_PERIOD_MIN.getCommand()));
        data.setInquiryPeriodMax(rs.getInt(RequestTypeSql.INQUIRY_PERIOD_MAX.getCommand()));
        data.setAmountMin(rs.getInt(RequestTypeSql.AMOUNT_MIN.getCommand()));
        data.setAmountMax(rs.getInt(RequestTypeSql.AMOUNT_MAX.getCommand()));
        data.setType(rs.getString(RequestTypeSql.TYPE.getCommand()));
        data.setStatus(rs.getInt(RequestTypeSql.STATUS.getCommand()));
        data.setDescription(rs.getString(RequestTypeSql.DESCRIPTION.getCommand()));
        data.setOutstanding(rs.getInt(RequestTypeSql.OUTSTANDING.getCommand()));
        data.setAttachment(rs.getBoolean(RequestTypeSql.ATTACHMENT.getCommand()));
        data.setApprovalPeriodType(rs.getString(RequestTypeSql.APPROVAL_PERIOD_TYPE.getCommand()));
        data.setAllocationCost(rs.getBoolean(RequestTypeSql.ALLOCATION_COST.getCommand()));
        data.setBudgeting(rs.getBoolean(RequestTypeSql.BUDGETING.getCommand()));
        data.setCreatedAt(rs.getTimestamp(RequestTypeSql.CREATED_AT.getCommand()));
        data.setUpdatedAt(rs.getTimestamp(RequestTypeSql.UPDATED_AT.getCommand()));

        return data;
    }
}
