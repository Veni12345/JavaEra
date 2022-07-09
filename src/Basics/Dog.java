package Basics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Veni
 * @date: 2022/06/28 六月 星期二 10:41
 * @description: 重写 equal和 hashcode
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dog implements Cloneable, Comparable<Dog> {

    private String name;

    private int age;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    @Override
    public int compareTo(Dog o) {
        if (this.age > o.getAge()) {
            return 1;
        } else if (this.age < o.getAge()) {
            return -1;
        } else
            return 0;
    }

//    @Override
//    public boolean equals(Object object) {
//        if (this == object) return true;
//        if (object == null || getClass() != object.getClass())
//            return false;
//        Concern concern = (Concern) object;
//        return concernFromId.equalsIgnoreCase(concern.concernFromId)
//                && concernToId.equalsIgnoreCase(concern.concernToId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(concernFromId.toUpperCase(),concernToId.toUpperCase());
//    }
}
