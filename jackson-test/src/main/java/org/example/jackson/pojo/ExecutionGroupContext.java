package org.example.jackson.pojo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Execution group context.
 *
 * @param <T> type of execution input value
 */
@RequiredArgsConstructor
@Getter
@Setter
public final class ExecutionGroupContext<T> {

    private final Collection<ExecutionGroup<T>> inputGroups;

    private final String executionID = new UUID(ThreadLocalRandom.current().nextLong(), ThreadLocalRandom.current().nextLong()).toString();

    private volatile String schemaName;

    private volatile Grantee grantee;
}
