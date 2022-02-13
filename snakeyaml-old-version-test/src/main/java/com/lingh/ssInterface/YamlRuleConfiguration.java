package com.lingh.ssInterface;

/**
 * YAML rule configuration.
 */
public interface YamlRuleConfiguration extends YamlConfiguration {

    /**
     * Get rule configuration type.
     *
     * @return rule configuration type
     */
    Class<? extends RuleConfiguration> getRuleConfigurationType();
}