package com.lingh.ssInterface;

import org.yaml.snakeyaml.constructor.Construct;

/**
 * ShardingSphere YAML construct.
 */
public interface ShardingSphereYamlConstruct extends Construct {
    
    /**
     * Get type.
     * 
     * @return type
     */
    Class<?> getType();
}
