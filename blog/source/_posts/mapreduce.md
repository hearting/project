---
title: mapreduce基础知识
date: 2020-04-08 16:49:12
tags: BigData
categories: BigData
---

<!-- toc -->

# mapreduce基础知识

Mapreduce 是一个分布式运算程序的编程框架，是用户开发“基于 hadoop 的数据分析 应用”的核心框架

Mapreduce 核心功能是将用户编写的业务逻辑代码和自带默认组件整合成一个完整的 分布式运算程序，并发运行在一个 hadoop 集群上

<!--more-->

## MapReduce编程模型

MapReduce 由 两 个 阶 段 组 成 ：Map 和 Reduce。

map() 函数以 key/value 对作为输入，产生另外一系列 key/value 对作为中间输出写入本地 磁盘。MapReduce 框架会自动将这些中间数据按照 key 值进行聚集，且 key 值相同（用户可 设定聚集策略，默认情况下是对 key 值进行哈希取模）的数据被统一交给 reduce() 函数处理。
reduce() 函数以 key 及对应的 value 列表作为输入，经合并 key 相同的 value 值后，产 生另外一系列 key/value 对作为最终输出写入 HDFS。
指定三个组件分别是 InputFormat、Partitioner 和 OutputFormat， 它们均需要用户根据自己的应用需求配置①指定输入 文件格式。将输入数据切分成若干个 split，且将每个 split 中的数据解析成一个个 map() 函数 要求的 key/value 对。②确定 map() 函数产生的每个 key/value 对发给哪个 Reduce Task 函数处 理。③指定输出文件格式，即每个 key/value 对以何种形式保存到输出文件中。


## Google MapReduce并行处理的基本过程
![](https://img-blog.csdnimg.cn/20200403011440528.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)

### 1.带宽优化：

大量的键值对数据在传送给Reduce节点时会引起较大的通信带开销.·解决方案

每个Map节点处理完成的中间键值对将由combiner做一个合并庄缩，即把那些键名相同的鍵值对归井 为一个鍵名下的一组数值.

### 2.用数据分区解决数据相关性问题：

一个Reduce节点上的计算数据可能会来自多个Map节点，因此，为了在进入Reduce节点计算之前，需要把属于一个Reduce节点的数据归并到一起.

在Map阶段进行了Corbining以后，可以根据一定的策路对Ma输出的中间结果进行分区（partitioning），这样即可解决以上数据相关性问题避免Reduceit算过程中的数据通信.

## MapReduce框架组成
![](https://img-blog.csdnimg.cn/20200403011345581.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)

当一个作被提交给Hadoop时，这个作业输入数剧划分成很多个等长的快，每个数据块都会对应一个a任务。这些map任务时执行，并行化地处理数据。

map任务的输出排序，然后分发给Redace任务以做进一步处理。

作业（Job）和任务（Task）是Hadoop MapRedace并行计算框架中常重要的两个概念。

任务（Task）是Hadoop mapRetoce架进行并行计算的基本单位，是一个逻辑上的概念。

## 数据存储与计算节点构架
![](https://img-blog.csdnimg.cn/20200403011235621.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)

为了实现Hadoop系统设计中本地化计算的原则，数据存储节点DataNode与计算节点TaskTracker将合并设置，让每个从节点同时运行作为DataNode和TaskTracker，以此让每个TaskTracker尽可能处理存储在本地DataNode上的数据.

而数据主控节点NameNode与作业执行主控节点jobTracker既可以设置在同一个主控节点上，在集群规模较大或者这两个主控节点负载都很高以致于会相互影响时，也可以分开设置在两个不同的节点上.

## 体系结构
![](https://img-blog.csdnimg.cn/20200403011212908.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)

**Client（客户端）**

1.通过client可以提交用户编写的应用程序用户通过它将应

用程序交到JobTracker端

2.通过这些Client用户也可以通过它提供的一些接口去查

看当前提交作业的运行状态

**JobTracker（作业跟踪器）**

>负责资源的监控和作业的调度

>监控底层的其它的TaskTracker以及当前运行的Job的健康状况

>一旦探测到失败的情况就把这个任务转移到其它节点继续执行跟踪任务执行进度和资源使用量

**TaskTracker（任务调度器）**

执行具体的相关任务一般接收JobTracker发送过来的命令

把一些自己的资源使用情况，以及任务的运行进度通过心跳的方式，也就是heartbeat发送给JobTracker

以slot为资源调度单位
![](https://img-blog.csdnimg.cn/20200403011107433.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)

同一台机器上可以同时执行map任务和reduce任务。

Hadoop MapReduce执行框架的组件和执行流程
![](https://img-blog.csdnimg.cn/20200403011011708.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMTEyNDQ4,size_16,color_FFFFFF,t_70)

### 大概流程：

每个TaskTracker节点将从HDFS分布式文件中读取所有处理的数据。Hadoop MapReduce框架提供了一个InputFormat对象负责具体以什么样的输入格式读取数据。然后数据会被分为很多个分片（Split），每个分片将交由一个Map对象处理。再进入Map之前，需要通过RecorderReader对象逐个从数据分片中读出数据记录、并转换为Key-value键值对，逐个输入到Map中处理。Map输出中间结果前，需要经过一个Combiner对象将该Map输出的相同主键下的所有键值对合并成一个键值对；map所输出的中间结果在进入Reduce之前，先通过中间的Partitioner对象进行数据分区，将数据发送到合适的Reduce节点上，避免不同Reduce节点上数据相关性，保证每个reduce节点可独立完成本地计算；在传入Reduce节点之前还会将所有键值对按照主键值进行排序。Reduce节点完成计算后，经过OutputFormat对指定输出数据的具体格式，最终将数据输出并写回到HDFS中。

### 文件输入格式InputFormat

InputFormat类是Hadoop MapReduce框架中的基础类之一

定义了数据文件如何分割和读取

InputFormat提供了以下一些功能

1.选择文件或者其它对象，用来作为输入

2.定义InputSplits，将一个文件分为不同任务，每一个IputSplits将单独作为一个Map的输入

3.提供一个RecordReader，将InputSplit处理转换为若干输入记录

4.有一个抽象的类FilelnputFormat，所有的输入格式类都从这个类继承其功能以及特性当启动一个Hadoop任务的时候，一个输入文件所在的目录被输入到FilelnputFormat对象中。Filelnputformat从这个目录中读取所有文件。然后FilelnputFormat将这些文件分割为多个InputSplits。

5.通过在JobConf对象上设置JobConf.sethnputFormat设置文件输入的格式

### 输入数据分块InputSplits

输入数据分块InputSplits inputsplit定了输入到单个Map任务的输入数据

一个MapReduce程序被统称为一个Job，可能有上百个任务构成

InputSpilt将文件分为64MB的大小

·配置文件hadoop-site xml中的mepred min.split.size控制这个大小

mapred.tasktracker.map.taks.maximm用来控制某一个节点上所有map任务的最大数目

### 数据记录读入RecordReader

数据记录读入RecordReader inputsplit定义了一个数据分块，但是没有定义如何读取数据记录

RecordReader实际上定义了如何将数据记录转化为一个key value对的详细方法，并将数据记录传给Mapper类

TexthnputFormat提供了LineRecordReader读入一个文本行数据记录

### Mapper

每一个Mapper类的实例生成了一个Java进程，负责处理某一个InputSplit上的数据有两个额外的参数OutputCollector以及Reporter，前者用来收集中间结果，后者用来获得环境参数以及设置当前执行的状态。

现在的版本用MapperContext提供给每一个Mapper函数，用来提供上面两个对象的功能

### shuffle阶段：

将map的输出作为reduce的输入的过程就是shuffle了，这个是mapreduce优化的重点地方。Shuffle一开始就是map阶段做输出操作，一般mapreduce计算的都是海量数据，map输出时候不可能把所有文件都放到内存操作，因此map写入磁盘的过程十分的复杂，更何况map输出时候要对结果进行排序，内存开销是很大的，map在做输出时候会在内存里开启一个环形内存缓冲区，这个缓冲区专门用来输出的，默认大小是100mb，并且在配置文件里为这个缓冲区设定了一个阀值，默认是0.80（这个大小和阀值都是可以在配置文件里进行配置的），同时map还会为输出操作启动一个守护线程，如果缓冲区的内存达到了阀值的80%时候，这个守护线程就会把内容写到磁盘上，这个过程叫spill，另外的20%内存可以继续写入要写进磁盘的数据，写入磁盘和写入内存操作是互不干扰的，如果缓存区被撑满了，那么map就会阻塞写入内存的操作，让写入磁盘操作完成后再继续执行写入内存操作，前面我讲到写入磁盘前会有个排序操作，这个是在写入磁盘操作时候进行，不是在写入内存时候进行的，如果我们定义了combiner函数，那么排序前还会执行combiner操作。每次spill操作也就是写入磁盘操作时候就会写一个溢出文件，也就是说在做map输出有几次spill就会产生多少个溢出文件，等map输出全部做完后，map会合并这些输出文件。这个过程里还会有一个Partitioner操作，对于这个操作很多人都很迷糊，其实Partitioner操作和map阶段的输入分片（Input split）很像，一个Partitioner对应一个reduce作业，如果我们mapreduce操作只有一个reduce操作，那么Partitioner就只有一个，如果我们有多个reduce操作，那么Partitioner对应的就会有多个，Partitioner因此就是reduce的输入分片，这个程序员可以编程控制，主要是根据实际key和value的值，根据实际业务类型或者为了更好的reduce负载均衡要求进行，这是提高reduce效率的一个关键所在。到了reduce阶段就是合并map输出文件了，Partitioner会找到对应的map输出文件，然后进行复制操作，复制操作时reduce会开启几个复制线程，这些线程默认个数是5个，程序员也可以在配置文件更改复制线程的个数，这个复制过程和map写入磁盘过程类似，也有阀值和内存大小，阀值一样可以在配置文件里配置，而内存大小是直接使用reduce的tasktracker的内存大小，复制时候reduce还会进行排序操作和合并文件操作，这些操作完了就会进行reduce计算了。

### Reducer

做用户定义的Reduce操作

接收到一个OutputCollector的

类作为输出

新版本的编程接口是Reducer.Context

### 文件输出格式OutputFormat

写入到HDFS的所有OutputFormat都继承自FileOutputFormat每一个Reducer都写一个文件到一个共同的输出目录，文件名是

part-nnnnn，其中nnnn是与每一个reducer相关的一个号（partition id）

FileOutputFormat.setOutputPath（）

JobConf.setOutputFormat（）

## 关于HDFS block和MapReduce split

Block：HDFS中最小的数据存储单位，默认是164M；Split：MapReduce中最小的计算单元，默认与Block一一对应。两者的对应关系是任意的，用户可控制。



