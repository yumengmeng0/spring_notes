package domain;

import jdk.jfr.DataAmount;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author: ymm
 * @date: 2022/8/5
 * @version: 1.0.0
 * @description:
 */
@Data
public class User {

    // 普通数据类型注入
    private String username;
    private Integer age;

    private List<Object> list;
    private int[] array;

    private Map<String, Object> map;

    private Properties properties;

}
