package org.example.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.example.jackson.pojo.YamlEngine;
import org.example.jackson.pojo.YamlExecuteProcessContext;
import org.example.jackson.pojo.YamlExecuteProcessUnit;
import org.example.jackson.ssEnum.ExecuteProcessConstants;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

public class JacksonTest {
    @Test
    public void jacksonTest1() throws JsonProcessingException {
        // Simple org.apache.shardingsphere.mode.manager.cluster.coordinator.registry.process.subscriber.ProcessRegistrySubscriberTest#assertReportExecuteProcessUnit()
        YamlExecuteProcessUnit yamlExecuteProcessUnit = new YamlExecuteProcessUnit();
        yamlExecuteProcessUnit.setUnitID("159917166");
        yamlExecuteProcessUnit.setStatus(ExecuteProcessConstants.EXECUTE_STATUS_DONE);
        Collection<YamlExecuteProcessUnit> unitStatuses = Collections.singleton(yamlExecuteProcessUnit);
        YamlExecuteProcessContext yamlExecuteProcessContext = new YamlExecuteProcessContext();
        yamlExecuteProcessContext.setUnitStatuses(unitStatuses);
        YAMLMapper mapper = new YAMLMapper();
        String marshal = mapper.writeValueAsString(yamlExecuteProcessContext);
        System.out.println("print start-------------------------");
        System.out.println(marshal);
        System.out.println("print end-------------------------");
        YamlExecuteProcessContext unmarshal = mapper.readValue(marshal, YamlExecuteProcessContext.class);
        System.out.println("test problem out class ---------" + unmarshal.getUnitStatuses().getClass().getName());
        for (YamlExecuteProcessUnit unit : unmarshal.getUnitStatuses()) {
            System.out.println("test problem in class ---------" + unit.getClass().getName());
            if (unit.getStatus() != ExecuteProcessConstants.EXECUTE_STATUS_DONE) {
                return;
            }
        }
    }
}
