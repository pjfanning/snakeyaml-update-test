package com.lingh.ssInterface;

/**
 * YAML configuration swapper.
 *
 * @param <Y> type of YAML configuration
 * @param <T> type of swapped object
 */
public interface YamlConfigurationSwapper<Y extends YamlConfiguration, T> {

    /**
     * Swap to YAML configuration.
     *
     * @param data data to be swapped
     * @return YAML configuration
     */
    Y swapToYamlConfiguration(T data);

    /**
     * Swap from YAML configuration to object.
     *
     * @param yamlConfig YAML configuration
     * @return swapped object
     */
    T swapToObject(Y yamlConfig);
}
