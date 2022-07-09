package Reflect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Veni
 * @date: 2022/07/05 七月 星期二 21:48
 * @description:
 */
@Data
//@NoArgsConstructor
@AllArgsConstructor
public class Cat extends Animal implements ClimbBehavior{

    private String catName;

    private Integer catAge;

    public Cat(){}


    @Override
    Boolean likeHuman() {
        return Boolean.FALSE;  //OR Neutral
    }
}
