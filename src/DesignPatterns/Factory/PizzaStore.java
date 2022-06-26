package DesignPatterns.Factory;

/**
 * 工厂方法
 */
public abstract class PizzaStore {
    public Pizza orderPizza(String type){
        Pizza pizza;
        pizza=this.createPizza(type);

        return pizza;
    }
    protected abstract Pizza createPizza(String type);
}
