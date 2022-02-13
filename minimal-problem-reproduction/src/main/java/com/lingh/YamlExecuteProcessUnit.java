package com.lingh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class YamlExecuteProcessUnit {
    private String unitID;
    private ExecuteProcessConstants status;
}