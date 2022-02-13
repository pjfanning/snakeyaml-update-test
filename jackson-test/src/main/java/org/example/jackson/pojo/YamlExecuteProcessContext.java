package org.example.jackson.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Execute process context for YAML.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class YamlExecuteProcessContext {

    private String executionID;

    private String schemaName;

    private String username;

    private String hostname;

    private String sql;

    private Collection<YamlExecuteProcessUnit> unitStatuses;

    private Long startTimeMillis;

    public YamlExecuteProcessContext(final ExecuteProcessContext executeProcessContext) {
        executionID = executeProcessContext.getExecutionID();
        schemaName = executeProcessContext.getSchemaName();
        username = executeProcessContext.getUsername();
        hostname = executeProcessContext.getHostname();
        sql = executeProcessContext.getSql();
        unitStatuses = executeProcessContext.getUnitStatuses().stream().map(YamlExecuteProcessUnit::new).collect(Collectors.toList());
        startTimeMillis = executeProcessContext.getStartTimeMillis();
    }
}
