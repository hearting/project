---
title: gitnote
date: 2020-04-08 15:02:41
tags: Linux
categories: Linux
---

## **git笔记**  
**1.新建版本库**  
cd 盘名:\文件夹名  //进入D盘文件夹  
mkdir 文件夹名称  //创建一个文件夹  
git config --global user.name "username" //创建用户名和密码  
git config --global user.email "email"  
git config --list //查看git配置  

<!--more-->

cat 文件 //打开文件  
git init //创建库  
touch readme.txt //新建readme记事本  
cd 文件名 //进入文件夹  
cd .. 回退到上一个目录  
git add 文件名 //把文件加入git仓库  
git add .添加同目录所有文件夹  
git add -f file //强行添加  
git commit -m"说明" //把文件提交到仓库  
git status //查看仓库修改状态  
git diff //查看做了什么修改  
git config --global alias.别名 原名 //配置别名  
**版本回退与修改**  
git log //查看修改日志按q退出  
git log --pretty=oneline 简化输出  
git reset --hard HEAD^ //回退到前^个版本  
当窗口没有关闭是可以重新回来，git reset --hard commitID 
git reflog //用来记录你的每一次命令
![](image/gitnote1.jpg)
git diff HEAD -- 文件名 //查看工作区和版本库里面最新版本的区别  
场景一：当你改乱了工作区某个文件的内容，想直接丢弃工作区的修改时，git checkout -- file  
场景二：当你不但改乱了工作区某个文件的内容，还添加到了暂存区时，想丢弃修改，分两步，第一步用命令git reset HEAD file，就回到了场景1，第二步按场景1操作。  
git rm file 或rm film//删除文件，并且git commit   
**连接远程库**  
ssh-keygen -t rsa -b 4096 -C "your_email@example.com"//生成ssh  
ssh -T git@github.com # 注意邮箱地址不用改
git remote add origin +git地址 //添加远程库，远程库的名字就是origin  
git remote show origin //显示所有分支  
git remote rm origin //删除远程连接  
git pull --rebase origin name //在第一次上传项目之前最好更新项目，确保没有与远程仓库代码冲突  
ls -al ~/.ssh //检查本地计算机SSH key  
**管理分支**  
git checkout -b dev //表示创建并切换，相当于以下两条命令：  
$ git branch dev  
$ git checkout dev  
git branch命令会列出所有分支，当前分支前面会标一个*号。  
git checkout 分支 //切换分支  
git merge dev //合并dev分支到当前分支。  
git branch -d dev //删除dev分支  
git branch -D dev //强制删除  
git log --graph --pretty=oneline --abbrev-commit //查看分支合并图  
git merge --no-ff -m "merge with no-ff" dev //--no-ff参数，表示禁用Fast forward，加-m会把commit写进去  
git stash //把当前工作现场“储藏”起来，等以后恢复现场后继续工作  
git stash list //查看工作现场存在哪  
恢复：
一是用git stash apply恢复，但是恢复后，stash内容并不删除，你需要用git stash drop来删除；
另一种方式是用git stash pop，恢复的同时把stash内容也删了：  
git remote -v//查看远程库信息  
**多人协作**  
git push origin branchname//推送自己的修改  
git push -u origin branchname //初次添加到远程分支  
如果推送失败，则因为远程分支比你的本地更新，需要先用git pull抓下来  
如果合并有冲突，则解决冲突，并在本地提交；  
没有冲突或者解决掉冲突后，再用git push origin branchname推送就能成功！
如果git pull提示no tracking information，则说明本地分支和远程分支的链接关系没有创建，用命令git branch --set-upstream-to <branch-name> origin/<branch-name>。  
git rebase操作可以把本地未push的分叉提交历史整理成直线；  
git pull origin branch-name//拉取远程仓库  
git fetch是将远程主机的最新内容拉到本地，用户在检查了以后决定是否合并到工作本机分支中。

而git pull 则是将远程主机的最新内容拉下来后直接合并，即：git pull = git fetch + git merge，这样可能会产生冲突，需要手动解决。

git clone 地址//这是一种较为简单的初始化方式，当你已经有一个远程的Git版本库，只需要在本地克隆一份  
**打标签**  
git tag tagname//在当前分支打一个新标签，默认标签是打在最新提交的commit上的  
git tag//查看所有标签  
git tag tagname commitID//打历史标签  
git show tagname//查看标签信息  
git tag -a v0.1 -m "version 0.1 released"//创建带有说明的标签，用-a指定标签名，-m指定说明文字  
git tag -d tagname //删除本地标签  
git push origin --delete tagname //删除远程标签  
git push origin tagname或git push origin :refs/tags/tagname //推送远程标签  
git push origin --tags //一次性推送远程标签  

## 垃圾回收
>git gc

gc意味着垃圾回收(garbage collect)，
使用git gc指令，可以将松散的文件压缩。具体流程如文档所说：“Git 会不定时地自动运行称为 “auto gc” 的命令。大部分情况下该命令什么都不处理。不过要是存在太多松散对象 (loose object, 不在 packfile 中的对象) 或 packfile，Git 会进行调用 git gc 命令。gc 指垃圾收集 (garbage collect)，此命令会做很多工作：收集所有松散对象并将它们存入 packfile，合并这些 packfile 进一个大的 packfile，然后将不被任何 commit 引用并且已存在一段时间 (数月) 的对象删除。”

>git gc --aggressive

对本地git库进行更彻底清理和优化，这个指令花费的时间也会更长。 

清空git缓存 git rm -r --cached . 

## 升级
>git update-git-for-windows