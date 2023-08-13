package Collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Veni
 * @date: 2023/07/23 七月 星期日 8:28
 * @description:
 */
public class MapTest {
    public static void main(String[] args) {
        Map<Integer,String> map=new HashMap<>(){
            {
                put(1,"veni");
                put(2,"vedi");
            }
        };

        System.out.println(map);
    }
}
