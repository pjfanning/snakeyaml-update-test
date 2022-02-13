package org.example.jackson.ssInterface;

/**
 * YAML rule configuration swapper.
 *
 * @param <Y> type of YAML rule configuration
 * @param <T> type of rule configuration
 */
public interface YamlRuleConfigurationSwapper<Y extends YamlRuleConfiguration, T extends RuleConfiguration> extends YamlConfigurationSwapper<Y, T>, OrderedSPI<T> {

    /**
     * Get YAML rule tag name.
     *
     * @return YAML rule tag name
     */
    String getRuleTagName();
}