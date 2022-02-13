package org.example.jackson.pojo;

import org.example.jackson.ssEnum.ExecuteProcessConstants;
import org.example.jackson.ssInterface.SQLExecutionUnit;
import lombok.Getter;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Execute process context.
 */
@Getter
public final class ExecuteProcessContext {

    private final String executionID;

    private final String schemaName;

    private final String username;

    private final String hostname;

    private final String sql;

    private final Collection<ExecuteProcessUnit> unitStatuses;

    private final long startTimeMillis = System.currentTimeMillis();

    public ExecuteProcessContext(final String sql, final ExecutionGroupContext<? extends SQLExecutionUnit> executionGroupContext, final ExecuteProcessConstants constants) {
        this.executionID = executionGroupContext.getExecutionID();
        this.sql = sql;
        this.schemaName = executionGroupContext.getSchemaName();
        Grantee grantee = executionGroupContext.getGrantee();
        this.username = null != grantee ? grantee.getUsername() : null;
        this.hostname = null != grantee ? grantee.getHostname() : null;
        unitStatuses = createExecutionUnitStatuses(executionGroupContext, constants);
    }

    private Collection<ExecuteProcessUnit> createExecutionUnitStatuses(final ExecutionGroupContext<? extends SQLExecutionUnit> executionGroupContext, final ExecuteProcessConstants constants) {
        Collection<ExecuteProcessUnit> result = new LinkedList<>();
        for (ExecutionGroup<? extends SQLExecutionUnit> group : executionGroupContext.getInputGroups()) {
            for (SQLExecutionUnit each : group.getInputs()) {
                result.add(new ExecuteProcessUnit(each.getExecutionUnit(), constants));
            }
        }
        return result;
    }
}
