package DesignPatterns.Proxy.MatchService;

/**
 * @author: Veni
 * @date: 2022/07/20 七月 星期三 16:00
 * @description: 配对服务，set、get人的姓名、性别、兴趣、Hot|Not
 */
public interface PersonBean {
    String getName();
    String getGender();
    String getInterests();
    int getHotNotRating(); //等级，从1-10

    void setName(String name);
    void setGender(String gender);
    void setInterests(String interests);
    void setHotNotRating(int rating);

}
