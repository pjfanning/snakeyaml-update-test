package org.example.jackson.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Route mapper.
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public final class RouteMapper {
    
    private final String logicName;
    
    private final String actualName;
}