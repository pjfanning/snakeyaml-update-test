## Investigation of #15260
- Investigation of YAML deserialization issue at https://github.com/apache/shardingsphere/pull/15260 . 
- The problem of main investigation is `org.apache.shardingsphere.mode.manager.cluster.coordinator.registry.process.subscriber.ProcessRegistrySubscriberTest` test failed.
- The `src/main/java/com/lingh` of `snakeyaml-old-version-test` contains the classes required by the Apache ShardingSphere master branch at `2022-02-10 13:20` for `assertReportExecuteProcess()`. 
- Both the and `master` branches are used Same component version .
- Junit test file is `src/test/java/com/lingh/JunitTest.java`

### snakeyaml-new-version-test
`snakeyaml.version`=`1.30`

### snakeyaml-old-version-test
`snakeyaml.version`=`1.16`, consistent with the master branch of Apache ShardingSphere.
