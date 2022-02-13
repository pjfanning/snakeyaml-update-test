package com.lingh.pojo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Execution group.
 *
 * @param <T> type of execution input value
 */
@RequiredArgsConstructor
@Getter
public final class ExecutionGroup<T> {
    
    private final List<T> inputs;
}