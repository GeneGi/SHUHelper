# SHUHelper
##项目简介
本项目为上海大学2015-2016年冬季学期,Java程序设计课程的课程项目.
主要实现了一些日常实用的小功能:
- 在线多人聊天室 
- 网络爬虫 
- 信息加密 
- 文件P2P共享
- 点菜系统
- 网站屏蔽

##使用方法
该目录下包含两个文件夹,/src和/lib,在/src中包含项目所有源代码,在/lib中包含了项目中用到的外部jar包
在IDEA中选择New Project From Existing Sources即可,在Project Structure->Libraries中将lib文件夹加入即可
在Eclipse中同理

##注意事项
- 网络聊天室需要先运行ChatRoomServer,若要运行在服务器上,要先修改ChatRoomClient中的ServerAddress为服务器地址
- 运行网站屏蔽器需要管理员权限,或者使用sudo运行,否则将无法实现屏蔽和取消
- 网络爬虫会默认将内容爬取到项目目录下,需要进一步解析处理
