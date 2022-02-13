import com.lingh.ExecuteProcessConstants;
import com.lingh.YamlExecuteProcessContext;
import com.lingh.YamlExecuteProcessUnit;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class JunitTest {
  @Test
  public void snakeYamlTest3() {
    String marshal = "unitStatuses: !!set\n" +
            "  ? status: EXECUTE_STATUS_DONE\n" +
            "    unitID: '159917166'\n" +
            "  : null\n";
    YamlExecuteProcessContext unmarshal = new Yaml(new Constructor(YamlExecuteProcessContext.class)).loadAs(marshal,YamlExecuteProcessContext.class);
    for (YamlExecuteProcessUnit unit : unmarshal.getUnitStatuses()) {
        System.out.println("test problem in class ---------" + unit.getClass().getName());
        if (unit.getStatus() != ExecuteProcessConstants.EXECUTE_STATUS_DONE) {
            return;
        }
    }
  }
}