package org.example.jackson.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

/**
 * SQL unit.
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(of = "sql")
@ToString
public final class SQLUnit {

    private final String sql;

    private final List<Object> parameters;

    private final List<RouteMapper> tableRouteMappers;

    public SQLUnit(final String sql, final List<Object> parameters) {
        this(sql, parameters, Collections.emptyList());
    }
}