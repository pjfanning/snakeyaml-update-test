package com.lingh.pojo;

import com.lingh.ssInterface.ShardingSphereYamlConstruct;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * ShardingSphere YAML constructor.
 */
public class ShardingSphereYamlConstructor extends Constructor {

    static {
        ShardingSphereServiceLoader.register(ShardingSphereYamlConstruct.class);
    }

    private final Map<Class<?>, Construct> typeConstructs = new HashMap<>();

    private final Class<?> rootClass;

    public ShardingSphereYamlConstructor(final Class<?> rootClass) {
        super(rootClass);
        ShardingSphereServiceLoader.getSingletonServiceInstances(ShardingSphereYamlConstruct.class).forEach(each -> typeConstructs.put(each.getType(), each));
        YamlRuleConfigurationSwapperEngine.getYamlShortcuts().forEach((key, value) -> addTypeDescription(new TypeDescription(value, key)));
        this.rootClass = rootClass;
    }

    @Override
    protected final Construct getConstructor(final Node node) {
        return typeConstructs.getOrDefault(node.getType(), super.getConstructor(node));
    }

    @Override
    protected Class<?> getClassForName(final String className) throws ClassNotFoundException {
        if (className.equals(rootClass.getName())) {
            return super.getClassForName(className);
        }
        throw new IllegalArgumentException(String.format("Class is not accepted: %s", className));
    }
}