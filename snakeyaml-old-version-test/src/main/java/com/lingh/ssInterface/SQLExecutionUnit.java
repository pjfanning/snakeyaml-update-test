package com.lingh.ssInterface;

import com.lingh.pojo.ExecutionUnit;
import com.lingh.ssEnum.ConnectionMode;

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
