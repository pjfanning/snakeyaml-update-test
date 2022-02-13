package com.lingh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class YamlExecuteProcessContext {
    private String executionID;
    private String schemaName;
    private String username;
    private String hostname;
    private String sql;
    private Collection<YamlExecuteProcessUnit> unitStatuses;
    private Long startTimeMillis;
}