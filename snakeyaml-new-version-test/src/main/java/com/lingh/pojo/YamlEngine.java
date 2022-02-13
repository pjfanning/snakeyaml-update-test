package com.lingh.pojo;

import com.lingh.ssInterface.YamlConfiguration;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.*;
import java.util.Collection;

/**
 * YAML engine.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class YamlEngine {

    /**
     * Unmarshal YAML.
     *
     * @param yamlFile  YAML file
     * @param classType class type
     * @param <T>       type of class
     * @return object from YAML
     * @throws IOException IO Exception
     */
    public static <T extends YamlConfiguration> T unmarshal(final File yamlFile, final Class<T> classType) throws IOException {
        try (
                FileInputStream fileInputStream = new FileInputStream(yamlFile);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream)
        ) {
            return new Yaml(new ShardingSphereYamlConstructor(classType)).loadAs(inputStreamReader, classType);
        }
    }

    /**
     * Unmarshal YAML.
     *
     * @param yamlBytes YAML bytes
     * @param classType class type
     * @param <T>       type of class
     * @return object from YAML
     * @throws IOException IO Exception
     */
    public static <T extends YamlConfiguration> T unmarshal(final byte[] yamlBytes, final Class<T> classType) throws IOException {
        try (InputStream inputStream = new ByteArrayInputStream(yamlBytes)) {
            return new Yaml(new ShardingSphereYamlConstructor(classType)).loadAs(inputStream, classType);
        }
    }

    /**
     * Unmarshal YAML.
     *
     * @param yamlContent YAML content
     * @param classType   class type
     * @param <T>         type of class
     * @return object from YAML
     */
    public static <T> T unmarshal(final String yamlContent, final Class<T> classType) {
        return new Yaml(new ShardingSphereYamlConstructor(classType)).loadAs(yamlContent, classType);
    }

    /**
     * Unmarshal YAML.
     *
     * @param yamlContent           YAML content
     * @param classType             class type
     * @param skipMissingProperties true if missing properties should be skipped, false otherwise
     * @param <T>                   type of class
     * @return object from YAML
     */
    public static <T> T unmarshal(final String yamlContent, final Class<T> classType, final boolean skipMissingProperties) {
        Representer representer = new Representer();
        representer.getPropertyUtils().setSkipMissingProperties(skipMissingProperties);
        return new Yaml(new ShardingSphereYamlConstructor(classType), representer).loadAs(yamlContent, classType);
    }

    /**
     * Marshal YAML.
     *
     * @param value object to be marshaled
     * @return YAML content
     */
    public static String marshal(final Object value) {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setLineBreak(DumperOptions.LineBreak.getPlatformLineBreak());
        if (value instanceof Collection) {
            return new Yaml(new ShardingSphereYamlRepresenter(), dumperOptions).dumpAs(value, null, DumperOptions.FlowStyle.BLOCK);
        }
        return new Yaml(new ShardingSphereYamlRepresenter(), dumperOptions).dumpAsMap(value);
    }
}
