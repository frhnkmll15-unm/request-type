package co.id.mcs.eFinProc.request_type.Pojo.RequestType;

import co.id.mcs.eFinProc.request_type.Pojo.AllocationCost.AllocationCostDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class RequestTypeDto {
    private int id;
    private String name;

    @JsonProperty("inquiry_period_min")
    private int inquiryPeriodMin;

    @JsonProperty("inquiry_period_max")
    private int inquiryPeriodMax;

    @JsonProperty("amount_min")
    private int amountMin;

    @JsonProperty("amount_max")
    private int amountMax;

    private String type;
    private int status;
    private String description;
    private int outstanding;
    private Boolean attachment;

    @JsonProperty("approval_period_type")
    private String approvalPeriodType;

    @JsonProperty("allocation_cost")
    private Boolean allocationCost;

    @JsonProperty("budgeting")
    private Boolean budgeting;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @JsonProperty("statement_algorithm")
    private List<String> statementAlgorithm;

    @JsonProperty("allocation_costs")
    private List<AllocationCostDto> allocationCosts;
    private String command;
    private String logic;

    // Getter untuk list biaya alokasi
    public List<AllocationCostDto> getAllocationCosts() {
        return allocationCosts;
    }

    public String getLogic() {
        return logic;
    }

    public String getCommand() {
        return command;
    }

}
