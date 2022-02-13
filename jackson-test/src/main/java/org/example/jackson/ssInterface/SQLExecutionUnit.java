package org.example.jackson.ssInterface;

import org.example.jackson.ssEnum.ConnectionMode;
import org.example.jackson.pojo.ExecutionUnit;

/**
 * SQL execution unit.
 */
public interface SQLExecutionUnit {

    /**
     * Get execution unit.
     *
     * @return execution unit
     */
    ExecutionUnit getExecutionUnit();

    /**
     * Get connection mode.
     *
     * @return connection mode
     */
    ConnectionMode getConnectionMode();
}
