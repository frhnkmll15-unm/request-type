package co.id.mcs.eFinProc.request_type.RequestType;

import co.id.mcs.eFinProc.request_type.Dao.RequestTypeDao;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.UpdateRequestTypeById;
import co.id.mcs.eFinProc.request_type.Pojo.RequestType.RequestTypeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class RequestTypeUpdateTest {

    @Autowired
    RequestTypeDao requestTypeDao;

    @Test
    void updateRequestTypeByIdTest() throws Exception {
        RequestTypeDto data = requestTypeDao.getById(4);
        if (data == null) {
            throw new Exception("RequestType tidak ditemukan");
        }

        UpdateRequestTypeById change = new UpdateRequestTypeById();
        change.setId(data.getId());
        change.setName("ahayu");
        change.setInquiry_period_min(data.getInquiry_period_min());
        change.setInquiry_period_max(data.getInquiry_period_max());
        change.setAmount_min(data.getAmount_min());
        change.setAmount_max(data.getAmount_max());
        change.setType(data.getType());
        change.setStatus(data.getStatus());
        change.setDescription(data.getDescription());
        change.setOutstanding(data.getOutstanding());
        change.setAttachment(data.getAttachment());
        change.setApproval_period_type(data.getApproval_period_type());
        change.setAllocation_cost(data.getAllocation_cost());
        change.setBudgeting(data.getBudgeting());
        change.setUpdated_at(new Timestamp(System.currentTimeMillis()));

        requestTypeDao.updateRequestTypeById((RequestTypeDto) change);
        System.out.println("Update berhasil");
    }
}
