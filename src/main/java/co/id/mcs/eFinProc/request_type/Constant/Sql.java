package co.id.mcs.eFinProc.request_type.Constant;

public enum Sql {
    GET_SEARCH( """
            SELECT %s
            FROM %s WHERE is_active = ? AND
             %s
            """ ),
    UPDATE("UPDATE" ),
    INSERT("INSERT" ),
    ALL("*" ),
    JOIN("JOIN" ),
    ON("ON" ),
    LEFTJOIN("LEFT JOIN" )
    ;


    private final String command;

    // Constructor
    Sql(String command) {
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
