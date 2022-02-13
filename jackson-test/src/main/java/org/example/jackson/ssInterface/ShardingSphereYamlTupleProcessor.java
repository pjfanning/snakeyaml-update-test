package org.example.jackson.ssInterface;

import org.yaml.snakeyaml.nodes.NodeTuple;

/**
 * ShardingSphere YAML tuple processor.
 */
public interface ShardingSphereYamlTupleProcessor {
    
    /**
     * Get tuple name.
     *
     * @return tuple name
     */
    String getTupleName();
    
    /**
     * Process YAML tuple for representer.
     *
     * @param nodeTuple YAML node tuple
     * @return YAML node tuple after process
     */
    NodeTuple process(NodeTuple nodeTuple);
}