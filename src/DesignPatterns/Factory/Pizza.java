package DesignPatterns.Factory;

import lombok.Data;

import java.util.ArrayList;

/**
 * 工厂模式--披萨类
 */
@Data
public abstract class Pizza{
    String name;
    String dough;  //面团类型
    String sauce;  //酱料类型
    ArrayList toppings=new ArrayList();  //一套佐料


}