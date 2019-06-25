# Gang of Four Design Patterns

设计模式：是指在软件开发过程中，经过验证的，用于解决在特定环境下、重复出现的、特定问题的解决方案。

__创建型：抽象了对象实例化过程，用来帮助创建对象的实例。__
* 简单工厂模式
* Singleton(单例模式)
* Factory Method(工厂方法模式)
* Abstract Factory(抽象工厂模式)
* Builder(生成器模式)
* Prototype(原型模式)

__结构型：描述如何组合类和对象以获得更大的结构。__
* Facade(外观模式)
* Adapter(适配器模式)
* Proxy(代理模式)
* Composite(组合模式)
* Flyweight(享元模式)
* Decorator(装饰模式)
* Bridge(桥接模式)

__行为型：描述算法和对象间职责的分配。__
* Mediator(中介者模式)
* Observer(观察者模式)
* Command(命令模式)
* Iterator(迭代器模式)
* Template Method(模板方法模式)
* Strategy(策略模式)
* State(状态模式)
* Memento(备忘录模式)
* Interpreter(解释器模式)
* Chain of Responsibility(职责链模式)
* Visitor(访问者模式)
&nbsp;&nbsp;
&nbsp;&nbsp;

### Facade(外观模式)
&emsp;&emsp;为子系统中的一组接口提供一个一致的界面，Facade模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。  
&emsp;&emsp;目的不是给子系统添加新的功能接口，而是为了让外部减少与子系统内多个模块的交互，松散耦合，从而让外部能够更简单地使用子系统。

* 外观模式与中介者模式  
中介都模式主要用来封闭多个对象之间相互的交互，多用在系统内部的多个模块之间；外观模式封闭的是单向的交互，是从客户端访问系统的调用，没有从系统来访问客户端的调用。  
在中介者模式的实现里面，是需要实现具体的交互功能的；而外观模式的实现里面，一般是组合调用或是转调内部实现的功能，通常外观模式并不实现这些功能。  
中介者模式的目的主要是松散多个模块的耦合，把这些耦合关系全部放到中介者中去实现；而外观模式的目的是简化客户端的调用。  

* 外观模式和单例模式  
通常一个子系统只要一个外观实例，所以外观模式可以和单例模式组合使用。

* 外观模式和抽象工厂模式  
&nbsp;&nbsp;
&nbsp;&nbsp;

### Adapter(适配器模式)
&emsp;&emsp;将一个类的接口转换成客户希望的另外一个接口。适配器模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。  
&emsp;&emsp;适配器模式的主要功能是进行匹配转换，目的是复用已有的功能，而不是来实现新的接口。

* 优点  
    更好的复用性  
    更好的可扩展性  
* 缺点  
    过多地使用适配器，会让系统非常零乱，不容易整体进行把握。  
&nbsp;&nbsp;

* 适配器模式与桥接模式  
    除了结构略为相似外，功能上完全不同。  
    适配器模式是把两个或者多个接口的功能进行转换匹配；而桥接模式是让接口和实现部分相分离，以便他们可以相对独立变化。  

* 适配器模式与装饰模式  
    从某种意义上讲，适配器模式能模拟实现简单的装饰模式的功能，也就是为已有功能增添功能。  
    两个模式有一个很大的不同：一般适配器适配过后是需要改变接口的，如果不改接口就没有必要适配了；而装饰模式是不改变接口的，无论多少层装饰都是一个接口。因此装饰模式可以很容易地支持递归组合，而适配器就做不到，每次的接口不同，无法递归。  

* 适配器模式和代理模式  
    适配器模式可以和代理模式组合使用。在实现适配器的时候，可以通过代理来调用Adapter，这样可以获得更大的灵活性。  

* 适配器模式和抽象工厂模式  
    在适配器实现的时候，通常需要得到被适配的对象。如果被适配的是一个接口，那么就可以结合一些可以创造对象实例的设计模式，来得到被适配的对象示例，比如抽象工厂模式、单例模式、工厂方法模式等。  
&nbsp;&nbsp;
&nbsp;&nbsp;

### Singleton(单例模式)
&emsp;&emsp;单例模式是用来保证这个类在运行期间只会被创建一个类实例，另外，单例模式还提供了一个全局别唯一访问这个类实例的访问点，就是getInstance方法。  
&emsp;&emsp;对于单例模式而言，不管采用何种实现方式，它都是只关心类实例的创建问题，并不关心具体的业务功能。  

  **双重检查加锁**  
&emsp;&emsp;需要使用关键字volatile,被volatile修饰的变量的值，将不会被本地线程缓存，所有对该变量的读写都是直接操作共享内存，从而确保多个线程能正确的处理该变量。
```java
      public class Singleton {
          private volatile static Singleton instantce = null;

          private Singleton() {
          }

          public static Singleton getInstance() {

              if (instance == null) {
                  synchronized(Singleton.class) {
                      if (instance == null) {
                          instance = new Singleton();
                      }
                  }
              }
          }
      }
```

&emsp;&emsp;由于volatile关键字可能会屏蔽掉虚拟机中一些必要的代码优化，所以运行效率并不是很高。一般情况下，没有特别的需要，不要使用。

&emsp;&emsp;Lazy initialization holder class模式，综合使用了java的类级内部类和多线程缺省同步锁，很巧妙地同时实现了延迟加载和线程安全。  

**多线程缺省同步锁：**  
在某些情况中，JVM已经隐含地为你执行了同步，这些情况下就不用自己再来同步控制了。  
* 由静态初始化器(在静态字段上或static{}块中的初始化器)初始化数据时  
* 访问final字段时  
* 在创建线程之前创建对象时  
* 线程可以看见它将要处理的对象时  
```java
      public class Singleton {
          
          /** 类线内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例没有绑定关系，而且只
           * 有被调用到时才会装载，从而实现了延迟加载
           */
          private static class SingletonHolder {

              /**
               * 静态初始化器，由JVM来保证线程安全
               */
              private static Singleton instance = new Sinleton();
          }

          /**
           * 私有化构造方法
           */
          private Singleton() {
          }

          public static Singleton getInstance() {
              return SingletonHolder.instance;
          }
      }
```
&nbsp;&nbsp;
&nbsp;&nbsp;

### Factory Method(工厂方法模式)
&emsp;&emsp;定义一个用于创建对象的接口，让子类决定实例化哪一个类，Factory Method使得一个类的实现化延迟到其子类。  
&emsp;&emsp;主要功能是让父类在不知道具体实现的情况下，完成自身的功能调用；而具体的实现延迟到子类来实现。  
&emsp;&emsp;这样在设计的时候，不用去考虑具体的实现，需要某个对象，把它通过工厂方法返回就好了，在使用这些对象实现功能的时候还是通过接口来操作。

* 优点  
    。可以在不知具体实现的情况下编程  
    。更容易扩展对象的新版本  
    。连接平等的类层次  
* 缺点  
    。具体产品对象和工厂方法的耦合性  
    在工厂方法模式中，工厂方法是需要创建产品对象的，也就是需要选择具体的产品对象，并创建它们的实例，因此具体产品对象和工厂方法是耦合的。  
    。工厂方法模式和抽象工厂模式  
    。工厂方法模式和模板方法模式  
      这两个模式外观类似，都有一个抽象类，然后由子类来提供一些实现，但是工厂方法模式的子类专注的是创建产品对象，而模板方法模式的子类专注的是为固定的算法骨架提供某些步骤的实现。  
      这两个模式可以组合使用，通常在模板方法模式里面，使用工厂方法来创建模板方法需要的对象。  
&nbsp;&nbsp;
&nbsp;&nbsp;

### Abstract Factory(抽象工厂模式)
&emsp;&emsp;提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类。  
&emsp;&emsp;功能是为一系列相关对象或相互依赖的对象创建一个接口。  
&emsp;&emsp;一定要注意，这个接口内的方法不是任意堆砌的，而是一系列相关或相互依赖的方法。

* 优点  
    分离接口和实现  
    使用切换产品簇变得容易  

* 缺点  
    不太容易扩展新的产品  
    容易造成类层次复杂  

* 抽象工厂模式和工厂方法模式  
    工厂方法模式一般是针对单独产品对象的创建，而抽象工厂模式注重产品簇对象的的创建。  

* 抽象工厂模式和单例模式  
    可以组合使用  
&nbsp;&nbsp;
&nbsp;&nbsp;

### Builder(生成器模式)
&emsp;&emsp;将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。  
&emsp;&emsp;生成器模式的主要功能是构建复杂的产品，而且是细化的、分步骤的构建产品，也就是生成器模式重在一步一步解决构造复杂对象的问题。更为重要的是，这个构建的过程是统一的、固定不变的，变化的部分放到生成器部分了，只要配置不同的生成器，那么同样的构建过程，就能构建出不同的产品来。  

* 生成器模式分成两个很重要的部分  
    。一个是Bulider接口，这里是定义了如何构建各个部件，也就是知道每个部件功能如何实现，以及如何装配这些部件到产品中去；  
    。另一个是Director，Director是知道如何组合来构建产品，也就是说Director负责整体的构建算法，而且通常是分步骤地来执行。  

* 生成器模式和工厂方法模式  
* 生成器模式和抽象工厂模式  
* 生成器模式和模板方法模式  
* 生成器模式和组合模式  
&nbsp;&nbsp;
&nbsp;&nbsp;

### Prototype(原型模式)
&emsp;&emsp;用原型实例指定创建对象的种类，并通过自拷贝这些原型创建新的对象。

* 原型模式的功能  
    包含两个方面：  
    。一个是通过克隆来创建新的对象实例；  
    。另一个是为克隆出来的新的对象实例复制原型实例属性的值。  

* 何时选用原型模式  
    。如果一个系统想要独立于它想要使用的对象时，可以使用原型模式，让系统只面向接口编程，在系统需要新的对象的时候，可以通过克隆原型来得到。  
    。如果需要实例化的类是在运行时刻动态指定时，可以使用原型模式，通过克隆原型来得到需要的实例。  

* 原型模式和抽象工厂模式  
* 原型模式和生成器模式  
&nbsp;&nbsp;
&nbsp;&nbsp;

### Mediator(中介者模式)
&emsp;&emsp;用一个中介对象来封装一系列的对象交互。中介者使得各个对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。  

* 中介者模式和外观模式  
* 中介者模式和观察者模式  
&nbsp;&nbsp;
&nbsp;&nbsp;

### Proxy(代理模式)
&emsp;&emsp;为其它对象提供一种代理以控制对这个对象的访问。

* 代理模式的功能  
    代理模式是通过创建一个代理对象，用这个代理对象去代表真实的对象，客户端得到这个代理对象后，对客户端并没有什么影响，就跟得到了真实对象一样来使用。  

* 代理的分类  
    虚代理  
    远程代理  
    copy-on-write代理  
    保护代理  
    Cache代理  
    防火墙代理  
    同步代理  
    智能指引  

代理模式的本质：控制对象访问。  

* 代理模式和适配器模式  
* 代理模式和装饰模式  
&nbsp;&nbsp;
&nbsp;&nbsp;

### Observer(观察者模式)
&emsp;&emsp;定义对象之间的一种一对多的依赖关系。当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被被自动更新。

* 推模型
  目标对象主动向观察推送目标的详细信息，不管观察者是否需要，推送的信息通常是目标对象的全部或部分数据，相当于在广播通信。  

* 拉模型  
    目标对象在通知观察者的时候，只传递少量信息。如果观察者需要更具体的信息，由观察者主动到目标对象中获取，相当于是观察者从目标对象中拉数据。一般这种模型的实现中，会把目标对象自身通过update方法传递给观察者，这样在观察者需要获取数据的时候，就可以通过这个引用来获取了。  

* java中的观察者模式  
    在java.util包里面有一个类Observable,它实现了大部分我们需要的目标的功能：还有一个接口Observer，其中定义了update的方法，就是观察者的接口。  

* 观察者模式的优缺点  
    优点：  
    。观察者模式实现了观察者和目标之间的抽象耦合  
    。观察者模式实现了动态联动  
    。观察者模式支持广播通信  

     缺点：  
    。可能会引起无谓的操作  

    观察者模式的本质：触发联动  

* 观察者模式和状态模式  
* 观察者模式和中介者模式  
&nbsp;&nbsp;
&nbsp;&nbsp;

### Command(命令模式)




### Strategy(策略模式)
&emsp;&emsp;定义一系列的算法，把它们一个个封装起来，并且使它们可相互替换。  
&emsp;&emsp;本模式使得算法可独立于使用它的客户而变化。  
&emsp;&emsp;策略模式的功能是把具体的算法实现从具体的业务处理中独立出来，把它们实现成为单独的算法类，从而形成一系列的算法，并让这些算法可以相互替换。  
&emsp;&emsp;策略模式的重心不是如何来实现算法，而是如何组织、调用这些算法，从而让程序结构更灵活，具有更好的性和扩展性。




