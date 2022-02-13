package org.example.jackson.pojo;

import org.example.jackson.ssInterface.RuleConfiguration;
import org.example.jackson.ssInterface.YamlRuleConfiguration;
import org.example.jackson.ssInterface.YamlRuleConfigurationSwapper;
import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * YAML rule configuration swapper engine.
 */
public final class YamlRuleConfigurationSwapperEngine {

    static {
        ShardingSphereServiceLoader.register(YamlRuleConfigurationSwapper.class);
    }

    /**
     * Swap to YAML rule configurations.
     *
     * @param ruleConfigs rule configurations
     * @return YAML rule configurations
     */
    @SuppressWarnings("unchecked")
    public Collection<YamlRuleConfiguration> swapToYamlRuleConfigurations(final Collection<RuleConfiguration> ruleConfigs) {
        return OrderedSPIRegistry.getRegisteredServices(YamlRuleConfigurationSwapper.class, ruleConfigs).entrySet().stream().map(
                entry -> (YamlRuleConfiguration) entry.getValue().swapToYamlConfiguration(entry.getKey())).collect(Collectors.toList());
    }

    /**
     * Swap from YAML rule configurations to rule configurations.
     *
     * @param yamlRuleConfigs YAML rule configurations
     * @return rule configurations
     */
    @SuppressWarnings("rawtypes")
    public Collection<RuleConfiguration> swapToRuleConfigurations(final Collection<YamlRuleConfiguration> yamlRuleConfigs) {
        Collection<RuleConfiguration> result = new LinkedList<>();
        Collection<Class<?>> ruleConfigTypes = yamlRuleConfigs.stream().map(YamlRuleConfiguration::getRuleConfigurationType).collect(Collectors.toList());
        for (Entry<Class<?>, YamlRuleConfigurationSwapper> entry : OrderedSPIRegistry.getRegisteredServicesByClass(YamlRuleConfigurationSwapper.class, ruleConfigTypes).entrySet()) {
            result.addAll(swapToRuleConfigurations(yamlRuleConfigs, entry.getKey(), entry.getValue()));
        }
        return result;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Collection<RuleConfiguration> swapToRuleConfigurations(final Collection<YamlRuleConfiguration> yamlRuleConfigs,
                                                                   final Class<?> ruleConfigType, final YamlRuleConfigurationSwapper swapper) {
        return yamlRuleConfigs.stream().filter(
                each -> each.getRuleConfigurationType().equals(ruleConfigType)).map(each -> (RuleConfiguration) swapper.swapToObject(each)).collect(Collectors.toList());
    }

    /**
     * Get YAML shortcuts.
     *
     * @return YAML shortcuts
     */
    @SuppressWarnings("rawtypes")
    @SneakyThrows(ReflectiveOperationException.class)
    public static Map<String, Class<?>> getYamlShortcuts() {
        Collection<YamlRuleConfigurationSwapper> swappers = ShardingSphereServiceLoader.getSingletonServiceInstances(YamlRuleConfigurationSwapper.class);
        Map<String, Class<?>> result = new HashMap<>(swappers.size(), 1);
        for (YamlRuleConfigurationSwapper each : swappers) {
            Class<?> yamlRuleConfigurationClass = Class.forName(((ParameterizedType) each.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0].getTypeName());
            result.put(String.format("!%s", each.getRuleTagName()), yamlRuleConfigurationClass);
        }
        return result;
    }

    /**
     * Swap from YAML rule configuration to rule configuration.
     *
     * @param yamlRuleConfig YAML rule configuration
     * @return rule configuration
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public RuleConfiguration swapToRuleConfiguration(final YamlRuleConfiguration yamlRuleConfig) {
        Collection<Class<?>> types = Collections.singletonList(yamlRuleConfig.getRuleConfigurationType());
        Map<Class<?>, YamlRuleConfigurationSwapper> typeSwapperMap = OrderedSPIRegistry.getRegisteredServicesByClass(YamlRuleConfigurationSwapper.class, types);
        YamlRuleConfigurationSwapper swapper = typeSwapperMap.get(yamlRuleConfig.getRuleConfigurationType());
        return (RuleConfiguration) swapper.swapToObject(yamlRuleConfig);
    }
}
