package DesignPatterns.Factory;

import DesignPatterns.Const;

/**
 * 纽约的披萨生产工厂
 */
public class NYPizzaStore extends PizzaStore{
    @Override
    protected Pizza createPizza(String type) {
        if(type.equals(Const.FACTORY.CHEESE)){
            return new NYStyleCheesePizza();
        }else if(type.equals(Const.FACTORY.VEGGIE)){
            return new NYStyleVeggiePizza();
        }

        return null;
    }
}
