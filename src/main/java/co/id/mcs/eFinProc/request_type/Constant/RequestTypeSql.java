package co.id.mcs.eFinProc.request_type.Constant;

/**
 * Enum representing SQL commands for managing request types.
 * Created By: Farhan a.k.a Muhammad Farhan Kamil
 * Created On: 13/09/2024
 * Version: 1.1
 */
@SuppressWarnings("ALL")
public enum RequestTypeSql {
    ID("id"),
    NAME("name"),
    INQUIRY_PERIOD_MIN("inquiry_period_min"),
    INQUIRY_PERIOD_MAX("inquiry_period_max"),
    AMOUNT_MIN("amount_min"),
    AMOUNT_MAX("amount_max"),
    TYPE("type"),
    STATUS("status"),
    DESCRIPTION("description"),
    OUTSTANDING("outstanding"),
    ATTACHMENT("attachment"),
    APPROVAL_PERIOD_TYPE("approval_period_type"),
    ALLOCATION_COST("allocation_cost"),
    BUDGETING("budgeting"),
    CREATED_AT("created_at"),
    UPDATED_AT("updated_at"),
    ALL("""
        id,
        name,
        inquiry_period_min,
        inquiry_period_max,
        amount_min,
        amount_max,
        type,
        status,
        description,
        outstanding,
        attachment,
        approval_period_type,
        allocation_cost,
        budgeting,
        created_at,
        updated_at
    """),
    ALL_INSERT("""
        name,
        inquiry_period_min,
        inquiry_period_max,
        amount_min,
        amount_max,
        type,
        status,
        description,
        outstanding,
        attachment,
        approval_period_type,
        allocation_cost,
        budgeting
    """),
    UPDATE_REQUEST_TYPE("""
        name = ?,
        inquiry_period_min = ?,
        inquiry_period_max = ?,
        amount_min = ?,
        amount_max = ?,
        type = ?,
        status = ?,
        description = ?,
        outstanding = ?,
        attachment = ?,
        approval_period_type = ?,
        allocation_cost = ?,
        budgeting = ?,
        updated_at = ?
    """),
    DELETE_REQUEST_TYPE("""
        status = ?,
        updated_at = ?
    """),
    GET_ALL("""
        SELECT %s FROM %s WHERE status = ?
        %s
    """),
    FIND_BY("""
        SELECT %s
        FROM %s WHERE status = ? AND
        %s
    """),
    LIMIT_SKIP("""
        LIMIT ? OFFSET ?
    """),
    INSERT_INTO("""
        INSERT INTO %s
        (%s) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """),
    UPDATE_BY_ID("""
        UPDATE %s SET %s WHERE id = ?
    """),
    DELETE_BY_ID("""
        UPDATE %s SET %s
        WHERE id = ?
    """),
    COUNT("COUNT(*)"),
    ID_FIND("id = ?"),
    TABLENAME("type_request_eq"),
    VIEW_GET_ALL("vw_type_request_eq_getall"),
    SELECT_ALL_VW("""
        SELECT * FROM request_type.vw_type_request_eq_getall
    """),
    SCHEMA("request_type");

    private final String command;

    // Constructor
    RequestTypeSql(String command) {
        this.command = command;
    }

    // Getter
    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return command;
    }
}
