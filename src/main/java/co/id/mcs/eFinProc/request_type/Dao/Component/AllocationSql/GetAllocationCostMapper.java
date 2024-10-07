package co.id.mcs.eFinProc.request_type.Dao.Component.AllocationSql;

import co.id.mcs.eFinProc.request_type.Constant.AllocationCostSql;
import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.AllocationCostDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
@Component
public class GetAllocationCostMapper implements RowMapper<AllocationCostDto> {

    @Override
    public AllocationCostDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        log.info("Mapping allocation cost with ID: " + rs.getInt(AllocationCostSql.ID.getCommand()));

        AllocationCostDto data = new AllocationCostDto();
        data.setId(rs.getInt(AllocationCostSql.ID.getCommand()));
        data.setId_product(rs.getString(AllocationCostSql.ID_PRODUCT.getCommand()));
        data.setId_department(rs.getString(AllocationCostSql.ID_DEPARTMENT.getCommand())); // Pastikan ini sesuai
        data.setName_departement(rs.getString(AllocationCostSql.NAME_DEPARTMENT.getCommand())); // Sesuaikan dengan DTO
        data.setType(rs.getInt(AllocationCostSql.TYPE.getCommand()));
        data.setStatus(rs.getInt(AllocationCostSql.STATUS.getCommand()));
        data.setAllocation_id_project(rs.getString(AllocationCostSql.ALLOCATION_ID_PROJECT.getCommand()));
        data.setPercentase(rs.getInt(AllocationCostSql.PERCENTASE.getCommand())); // Sesuaikan dengan perintah
        data.setDescription(rs.getString(AllocationCostSql.DESCRIPTION.getCommand()));
        data.setCreated_at(rs.getTimestamp(AllocationCostSql.CREATED_AT.getCommand()));
        data.setUpdated_at(rs.getTimestamp(AllocationCostSql.UPDATED_AT.getCommand()));

        return data;
    }
}
