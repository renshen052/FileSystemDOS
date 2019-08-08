# FileSystemDOS
**这是一个基于java的简单的仿DOS文件管理系统**

目的是学习java中的file操作

## 实现功能
* 系统初始化,到路径执行命令，如：

>```shell
C:\Users\Administrator>
```
>根据配置文件初始化路径 


* 实现 `cd` 命令  ,支持参数选项

>```shell
C:\Users\Administrator>cd /d D:
D:\>
```



* 实现 `mkdir` 命令,支持参数选项 

> ```shell
C:\Users\Administrator\Desktop>mkdir /R a\b\c
```
>创建了目录 a与其子目录b和与其子目录c(r递归的)


* 实现 `copy` 复制命令，支持参数选项

>```shell
C:\Users\Administrator\Desktop\a>copy b.txt b/c.txt
```
>复制b.txt 到 b目录下并重命名为c.txt


* 实现 `del`  删除命令，支持参数选项

>```shell
C:\Users\Administrator\Desktop\a>del b.txt
```
>删除a目录下的文件b.txt



* 实现 `dir`  列出当前路径的子目录和文件，支持参数选项

>```shell
dir C:
```
>列出C盘下的子目录和文件




* 实现 `type` 查看文件

>```shell
type E:\fileJavaSystem\a.txt
```
>查看a.txt文件的内容


---

**编码实现的流程**
1. 确定命令接口Command
2. 编写帮助Help
3. 写FileController控制流程
4. 对着流程一个一个实现命令

> * 先编写每个命令的help方法

> * 然后在编写excute方法

> * 初步测试



5.最后进行简单测试


