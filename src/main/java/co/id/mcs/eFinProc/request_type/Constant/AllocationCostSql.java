package co.id.mcs.eFinProc.request_type.Constant;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 13/09/2024 13:37
@Last Modified 13/09/2024 13:37
Version 1.0
*/

@SuppressWarnings("ALL")
public enum AllocationCostSql {
    ID("id"),
    ID_PRODUCT("id_product"),
    ID_DEPARTMENT("id_departement"),
    NAME_DEPARTMENT("name_departement"),
    TYPE("type"),
    STATUS("status"),
    ALLOCATION_ID_PROJECT("allocation_id_proyek"),
    PERCENTASE("persentase"),
    DESCRIPTION("description"),
    CREATED_AT("created_at"),
    UPDATED_AT("updated_at"),

    ALL("""
        id,
        id_product,
        id_departement,
        name_departement,
        type,
        status,
        allocation_id_proyek,
        persentase,
        description,
        created_at,
        updated_at
    """),

    GET_ALL("""
        SELECT %s
        FROM %s WHERE status = ?
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
        (%s) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """),

    UPDATE("""
        id_product = ?,
        id_departement = ?,
        name_departement = ?,
        type = ?,
        status = ?,
        allocation_id_proyek = ?,
        persentase = ?,
        description = ?,
        updated_at = ?
    """),

    UPDATE_BY_ID("""
        UPDATE %s SET %s WHERE %s
    """),

    DELETE_BY_ID("""
        UPDATE %s SET %s WHERE %s
    """),

    COUNT("COUNT(*)"),
    ID_FIND("id = ?"),
    TABLENAME("allocation_cost"),
    FUNCTION_INSERT("fn_allocation_cost_insert"),
    FUNCTION_UPDATE("fn_allocation_cost_update"),
    FUNCTION_DELETE("fn_allocation_cost_delete"),
    SCHEMA("request_type");

    private final String command;

    // Constructor
    AllocationCostSql(String command) {
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
