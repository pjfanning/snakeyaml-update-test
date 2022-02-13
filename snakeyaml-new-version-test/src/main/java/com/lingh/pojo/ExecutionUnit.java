package com.lingh.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Execution unit.
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public final class ExecutionUnit {
    
    private final String dataSourceName;
    
    private final SQLUnit sqlUnit;
}
