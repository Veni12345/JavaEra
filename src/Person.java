import lombok.Data;

/**
 * @Author : Created by lity25
 * @Date : 2022/3/10 14:19
 * @Description :
 */
@Data
public class Person{
    private String name;
    private int age;

    Person(String name,int age){
        this.name=name;
        this.age=age;
    }
}
