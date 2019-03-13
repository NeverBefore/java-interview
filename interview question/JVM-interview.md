##### 1.JVM运行内存的分类
        程序计数器(Program Counter Register)
            也被称作PC寄存器，当前线程所执行的字节码的行号指示器，也可以说保存下一条指令所在的存储单元的地址
        Java栈
            也被称作虚拟机栈(Java Vitual Machine Stack),存储方法中的局部变量，包括在方法中声明的非静态变量和函数形参
            存放基本数据类型，对象的引用和方法出口
        本地方法栈
            和虚拟栈相似，只不过它服务于本地方法 (native method)
        堆
            是内存中最大的一块，所有对象实例、数组都存放在堆里
            堆是Java垃圾收集器管理(GC)的主要管理区域
            对是被所有线程共享的，在jvm中只有一个堆
        方法区
            存储每个类的信息，包括类的名称、方法信息、字段信息，静态常量，常量，以及编译器编译后的代码
            在方法区中有一个非常重要的部分就是运行时常量池，它是每一个类或接口的常量池的运行时表示形式，
            在类和接口被加载到JVM后，对应的运行时常量池就被创建出来。当然并非Class文件常量池中的内容才能进入运行时常量池，
            在运行期间也可将新的常量放入运行时常量池中，比如String的intern方法。
            
##### 2.堆和栈的区别
        最通俗的一点就是：堆是用来存放对象的，栈是用来存放执行程序的
        应用程序所有的部分都使用堆内存，然后栈内存通过一个线程运行来使用。
        不论对象什么时候创建，他都会存储在堆内存中，栈内存包含它的引用。栈内存只包含本地原始变量和堆中对象变量的引用。
        存储在堆中的对象是全局可以被访问的，然而栈内存不能被其他线程所访问。
        栈内存是生命周期很短的，然而堆内存的生命周期从程序的运行开始到运行结束。
        和堆内存比，栈内存要小的多，因为明确使用了内存分配规则（LIFO），和堆内存相比栈内存非常快。
##### 3.GC回收机制
        GC：Garbage Collection 垃圾收集
            所谓的垃圾是指在系统运行过程中产生的一些无用的对象，这些对象占据着一定的内存空间，如果长期不释放，可能会OOM(Out Of Memory)
        GC需要完成的三件事情：
            1.哪些内存需要回收
                在堆中没有引用，不会再次被使用的对象是需要回收的，那如何判断对象是否失效呢？
                    1.1 引用计数法
                        原理：给对象添加一个引用计数器，这个对象被使用的时候计数器+1，失效的时候计数器-1。当该对象引用为0的时候，
                        判定对象失效，可以被GC回收了
                            优点：实现简单，判定效率高
                            缺点：A->B,B->A,那么 AB 将永远不会被回收了。很难解决对象之间循环引用的问题
                    1.2 可达性分析法(根搜索算法)
                        原理：通过一个叫GC Roots的对象作为起点，从这些节点开始向下搜索，搜索走过的路径被称为引用链，当一个对象
                        没有与任何的引用链相连，那么这个对象就可以被GC回收了
                        Java中可作为GC Roots的对象
                            虚拟机栈（栈帧中的本地变量表）中引用的对象；
                            方法区中类静态属性引用的对象；
                            方法区中常量引用的对象；
                            本地方法栈中JNI（即一般说的Native方法）引用的对象。
            2.什么时候回收
                当jvm分析出失效的对象的时候，并不是马上清除，而是进行标记并判断是否回收
                    2.1 判断对象是否覆盖了finalize()方法
                        如果覆盖，那么将finalize()方法放到F-Queue队列中
                        如果没有覆盖，直接回收
                    2.2 执行F-Queue队列中的finalize()方法
                        
            3.如何回收      
##### 4.GC回收算法
        标记-清除法：标记出没有用的对象，一个一个回收掉
            标记-清楚法分为两个阶段：
                1.标记阶段：