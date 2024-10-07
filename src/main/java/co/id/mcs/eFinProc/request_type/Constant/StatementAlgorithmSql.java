package co.id.mcs.eFinProc.request_type.Constant;
/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author Farhan a.k.a Muhammad Farhan Kamil
Java Developer
Created On 14/09/2024 20:19
@Last Modified 14/09/2024 20:19
Version 1.0
*/

@SuppressWarnings("ALL")
public enum StatementAlgorithmSql {
    ID("id"),
    ID_TYPE_REQUEST_EQ("id_type_request_eq"),
    TYPE("type"),
    LOGIC("logic"),
    STATUS("status"),
    COMMAND("command"),
    CREATED_AT("created_at"),
    UPDATED_AT("updated_at"),
    ALL("""
        id,
        id_type_request_eq,
        type,
        logic,
        status,
        command,
        created_at,
        updated_at
    """),
    ALL_INSERT("""
        id_type_request_eq,
        type,
        logic,
        status,
        command
    """),
    UPDATE("""
        id_type_request_eq = ?,
        type = ?,
        logic = ?,
        status = ?,
        command = ?,
        updated_at = ?
    """),
    DELETE("""
        status = ?,
        updated_at = ?
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
        (%s) VALUES (?, ?, ?, ?, ?)
    """),
    UPDATE_BY_ID("""
        UPDATE %s SET %s WHERE id = ?
    """),
    DELETE_BY_ID("""
        UPDATE %s SET status = ?, updated_at = ?
        WHERE id = ?
    """),
    COUNT("COUNT(*)"),
    ID_FIND("id = ?"),
    TABLENAME("statement_algorithm"),
    SCHEMA("request_type");

    private final String command;

    StatementAlgorithmSql(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return command;
    }
}
