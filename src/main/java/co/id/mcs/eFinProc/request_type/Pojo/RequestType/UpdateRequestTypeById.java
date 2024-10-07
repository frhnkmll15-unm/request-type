package co.id.mcs.eFinProc.request_type.Pojo.RequestType;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 13:53
@Last Modified 13/09/2024 13:53
Version 1.0
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UpdateRequestTypeById {
    private int id; // ID dari request type
    private String name; // Nama request type

    @JsonProperty("inquiry_period_min")
    private int inquiryPeriodMin; // Periode min untuk inquiry

    @JsonProperty("inquiry_period_max")
    private int inquiryPeriodMax; // Periode max untuk inquiry

    @JsonProperty("amount_min")
    private long amountMin; // Jumlah minimum (gunakan long untuk big integer)

    @JsonProperty("amount_max")
    private long amountMax; // Jumlah maksimum (gunakan long untuk big integer)

    private String type; // Tipe request

    private String description; // Deskripsi dari request type

    private int outstanding; // Outstanding

    private Boolean attachment; // Attachment (boolean)

    @JsonProperty("approval_period_type")
    private String approvalPeriodType; // Tipe periode approval

    @JsonProperty("allocation_cost")
    private Boolean allocationCost; // Biaya alokasi (boolean)

    private Boolean budgeting; // Anggaran (boolean)

    @JsonProperty("updated_at")
    private Timestamp updatedAt; // Waktu update

    @JsonProperty("status")
    private int status; // Status

    public boolean isAllocationCost() {
        return allocationCost;
    }

    public boolean isAttachment() {
        return attachment;
    }

    public boolean isBudgeting() {
        return budgeting;
    }
}
