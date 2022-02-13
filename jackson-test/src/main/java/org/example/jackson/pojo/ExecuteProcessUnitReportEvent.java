package org.example.jackson.pojo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Execute process unit report event.
 */
@RequiredArgsConstructor
@Getter
public final class ExecuteProcessUnitReportEvent {

    private final String executionID;

    private final ExecuteProcessUnit executeProcessUnit;
}