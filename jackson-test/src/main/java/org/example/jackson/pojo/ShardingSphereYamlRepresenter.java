package org.example.jackson.pojo;

import org.example.jackson.ssInterface.ShardingSphereYamlTupleProcessor;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ShardingSphere YAML representer.
 */
public final class ShardingSphereYamlRepresenter extends Representer {

    static {
        ShardingSphereServiceLoader.register(ShardingSphereYamlTupleProcessor.class);
    }

    public ShardingSphereYamlRepresenter() {
        YamlRuleConfigurationSwapperEngine.getYamlShortcuts().forEach((key, value) -> addClassTag(value, new Tag(key)));
    }

    @Override
    protected NodeTuple representJavaBeanProperty(final Object javaBean, final Property property, final Object propertyValue, final Tag customTag) {
        NodeTuple nodeTuple = super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
        for (ShardingSphereYamlTupleProcessor each : ShardingSphereServiceLoader.getSingletonServiceInstances(ShardingSphereYamlTupleProcessor.class)) {
            if (property.getName().equals(each.getTupleName())) {
                return each.process(nodeTuple);
            }
        }
        return new DefaultYamlTupleProcessor().process(nodeTuple);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected Node representMapping(final Tag tag, final Map<?, ?> mapping, final DumperOptions.FlowStyle flowStyle) {
        Map skippedEmptyValuesMapping = new LinkedHashMap<>(mapping.size(), 1);
        for (Entry<?, ?> entry : mapping.entrySet()) {
            if (entry.getValue() instanceof Collection && ((Collection) entry.getValue()).isEmpty()) {
                continue;
            }
            if (entry.getValue() instanceof Map && ((Map) entry.getValue()).isEmpty()) {
                continue;
            }
            skippedEmptyValuesMapping.put(entry.getKey(), entry.getValue());
        }
        return super.representMapping(tag, skippedEmptyValuesMapping, flowStyle);
    }
}
