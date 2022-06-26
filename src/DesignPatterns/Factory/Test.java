package DesignPatterns.Factory;

import DesignPatterns.Const;

public class Test {
    public static void main(String[] args) {
        PizzaStore nyStore=new NYPizzaStore();
        PizzaStore chicagoStore=new ChicagoPizzaStore();

        //点一个纽约芝士披萨
        Pizza pizza=nyStore.orderPizza(Const.FACTORY.CHEESE);

        //点一个芝加哥 素食披萨
        pizza=chicagoStore.orderPizza(Const.FACTORY.VEGGIE);
    }
}
