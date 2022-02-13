package com.lingh.pojo;

import com.lingh.ssEnum.ExecuteProcessConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Execute process unit for YAML.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class YamlExecuteProcessUnit {

    private String unitID;

    private ExecuteProcessConstants status;

    public YamlExecuteProcessUnit(final ExecuteProcessUnit executeProcessUnit) {
        unitID = executeProcessUnit.getUnitID();
        status = executeProcessUnit.getStatus();
    }
}