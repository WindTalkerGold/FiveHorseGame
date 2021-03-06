﻿# 基本概念
## 基本变量类型，变量声明与基本操作
包括int，long，double，boolean等这几种变量类型（Primitive Types）；了解它们与String为代表的Reference Type变量的区别。
## 类与方法的定义
了解如何定义一个类，包括定义其成员变量和成员方法，定义类的构造函数，了解访问控制层次，public，protected，private以及默认的区别。

了解面向对象编程基础，包括类与对象的关系，类之间的继承关系等。
## 几种基本的Java类库
包括String，数组类型，ArrayList等
## 练习
在了解以上概念后，请编写以下的类
1. class Person，这个类的每一个对象代表一个人，你可以对这个类进行至少以下的操作
- 设置姓名
- 设置出生年份
- 获取姓名
- 获取出生年份
- 获取年龄
2. class Student，代表一个学生，这个类继承于Person，因为学生是一个人。对学生而言，你还可以进行以下操作：
- 安排这名学生去上某个老师的课程
- 获取这个学生所有任何老师的列表
3. class Teacher，代表一个老师，同样继承与Person，每个老师都有它所任教的学科，请添加方法。

在完成以上类的编码工作之后，请编写两个方法，输入都是一个学生，输出时这个学生所有任课老师中年龄最大的那一个所教的学科，以及这名学生所有老师的平均年龄。

这个练习的主要目的时复习上面的几个基本概念，你可以[在这里找到答案](https://github.com/WindTalkerGold/FiveHorseGame/tree/master/src/main/java/fundmental)。
