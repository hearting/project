---
title: 详解Java中的抽象类与接口
date: 2020-08-01 21:38:33
tags: Java
categories: Java
---
<!-- toc -->

<!--more-->

### 首先来说一说这两者的概念
### 一、抽象类与抽象方法的定义



抽象方法和抽象类都必须被abstract关键字修饰。 
抽象——abstract，抽象类的方法不一定是抽象的，但抽象方法出现的类一定是抽象类。抽象类也是可以与普通类那样，可以直接extends，区别在于抽象类不能直接实例化，可以通过实例化其子类，然后重写里面的抽象方法实现，抽象类一定要被继承实现的，否则毫无意义。子类可以不是抽象类，但要实现抽象父类中的所有抽象方法，否则必须定义为abstract类型。
```
public abstract class Door {
    public abstract void opendoor();
    public abstract void closedoor();
}
```
#### 抽象方法
抽象方法和空方法体的方法不是同一个概念。例如，public abstract void test();是一个抽象方法，它根本没方法体，即方法定义后面没有一对花括号；但public void test(){}方法是一个普通方法，它已经定义了方法体，只是方法体为空。
abstract不能用于修饰成员变量（Field），不能用于修饰局部变量，即没有抽象变量、没有抽象Field等说法；abstract也不能用于修饰构造器，没有抽象构造器，抽象类里定义的构造器只能是普通构造器。
#### 作用
抽象类是普通的类与接口之间的一种中庸之道。

抽象方法、抽象类可以使类的抽象性明确起来，告诉用户和编译器怎么使用它们；

同时，抽象类是很好的重构工具，在后期的工作中，可以实现重用性。

体现一种模板的效果，从一群相似的子类提炼出一个抽象类的感觉一样，提供了一种规范，子类可以在其原来的基础上进行扩展。

抽象父类可以只定义需要使用的某些方法，把不能实现的部分抽象成抽象方法，抽象类类似领导，领袖这一类角色，类中可以定义很多的抽象方法，就像是给子类制定了很多应该具备的功能，或指定了很多应该完成的目标。
## 接口
为了实现多继承，引入了接口这个概念

接口通常被理解为一个组件，接口比抽象类更抽象，在接口中所有的方法都是抽象的。就不能像抽象类一样还可以有普通方法。Java中可以implements多个接口，多继承的含义便是接入多个接口实现（继承只能单继承），（abstract省略)。
```
public interface Lock {
    void openLock();
    void closeLock();
}
```
```
public class TheftProofDoor extends Door implements Lock{
    @Override
    public void opendoor() {
        System.out.println("门开了");
    }

    @Override
    public void closedoor() {
        System.out.println("门关上了");
        }

    @Override
    public void openLock() {
        System.out.println("所开了");
    }

    @Override
    public void closeLock() {
        System.out.println("锁上了");
    }
}
```
#### 作用
规范，在分配不同人的任务时，接口就像是总纲一样，告诉大家去实现哪些功能模块等。（命名规范都有限制到）
## 抽象类与接口的异同
相同点：  
（1）都不能被实例化  
（2）接口和抽象类都可以包含抽象方法，只有实现了接口或抽象类中的方法后才能实例化。
不同点：  
1. 抽象类可以有普通方法。Java 8 之前接口中只有抽象方法，而 Java 8 之后接口中也可以声明具体方法，具体方法通过声明默认方法实现。
2. 一个类可以实现多个接口，但一个类只能继承一个抽象类。接口解决了多重继承的问题
3. 接口成员变量默认为public static final，必须赋初值，不能被修改；其所有的成员方法都是public、abstract的。抽象类中成员变量默认default，可在子类中被重新定义，也可被重新赋值；抽象方法被abstract修饰，不能被private、static、synchronized和native等修饰，
4. 接口被用于常用的功能，便于日后维护和添加删除，而抽象类更倾向于充当公共类的角色，不适用于日后重新对立面的代码修改。功能需要累积时用抽象类，不需要累积时用接口。
5. 一个类实现了一个或多个接口之后，这个类必须实现这些接口里所定义的全部抽象方法（也就是重写这些抽象方法）；否则，该类将保留从父接口那里继承到的抽象方法，该类也必须定义成抽象类。
6. 接口定义的是一种规范，因此接口里不能包含构造器和初始化块定义。接口里可以包含Field（只能是常量）、方法（只能是抽象实例方法）、内部类（包括内部接口、枚举）定义。但抽象类与普通类一样，可以有构造器，初始化模块等。

## 使用场景
接口多用于多重继承的时候（功能性强，规范性）

抽象类多用于底层基础功能模块不断改变（模板设计）


