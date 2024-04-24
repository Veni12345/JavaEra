package Collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : Created by lity25
 * @Date : 2022/3/24 16:36
 * @Description :
 */
public class MapDemo {
    public static void main(String[] args) {
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "Rob");
        map1.put(2, "John");
        map1.put(3, "Ary");

        map1.get(2);
        System.out.println(map1.get(1));

    }
}
