package domain;


import lombok.Data;

/**
 * @author: ymm
 * @date: 2022/8/6
 * @version: 1.0.0
 * @description:
 */
@Data
public class Account {
    private Integer id;
    private String name;
    private Double money;
}
