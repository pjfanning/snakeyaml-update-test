package org.example.jackson.pojo;

import org.example.jackson.ssEnum.ExecuteProcessConstants;
import lombok.Getter;

/**
 * Execute process unit.
 */
@Getter
public final class ExecuteProcessUnit {

    private final String unitID;

    private final ExecuteProcessConstants status;

    public ExecuteProcessUnit(final ExecutionUnit executionUnit, final ExecuteProcessConstants status) {
        this.unitID = String.valueOf(executionUnit.hashCode());
        this.status = status;
    }
}