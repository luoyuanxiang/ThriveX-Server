/*
 Navicat Premium Dump SQL

 Source Server         : 阿里云数据库
 Source Server Type    : MySQL
 Source Server Version : 90300 (9.3.0)
 Source Host           : 47.109.138.9:13000
 Source Schema         : thrivex_blog

 Target Server Type    : MySQL
 Target Server Version : 90300 (9.3.0)
 File Encoding         : 65001

 Date: 09/05/2025 13:39:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for album_cate
-- ----------------------------
DROP TABLE IF EXISTS `album_cate`;
CREATE TABLE `album_cate`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '相册名称',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文章封面',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '相册信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of album_cate
-- ----------------------------
INSERT INTO `album_cate` VALUES (1, '旅行', 'https://oss.luoyuanxiang.top/thrivex/swiper/680ee2f3e4b08588b0e0cf7e.png', NULL);

-- ----------------------------
-- Table structure for album_image
-- ----------------------------
DROP TABLE IF EXISTS `album_image`;
CREATE TABLE `album_image`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '相册名称',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '相册介绍',
  `image` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '相册地址',
  `cate_id` int NULL DEFAULT NULL COMMENT '相册ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '相册' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of album_image
-- ----------------------------
INSERT INTO `album_image` VALUES (1, '日本富士山', '日本标志性的富士山风景,白雪皑皑的山顶与蓝天相映成趣', 'https://images.unsplash.com/photo-1480796927426-f609979314bd?ixlib=rb-1.2.1&auto=format&fit=crop&w=3840&q=100', 1, NULL);
INSERT INTO `album_image` VALUES (2, '日落时分', '夕阳西下的美丽景色,金色的阳光洒在大地上', 'https://images.unsplash.com/photo-1470071459604-3b5ec3a7fe05?ixlib=rb-1.2.1&auto=format&fit=crop&w=3840&q=100', 1, NULL);
INSERT INTO `album_image` VALUES (3, '山川湖泊', '壮丽的山川与平静的湖泊交相辉映,展现大自然的鬼斧神工', 'https://images.unsplash.com/photo-1501785888041-af3ef285b470?ixlib=rb-1.2.1&auto=format&fit=crop&w=3840&q=100', 1, NULL);
INSERT INTO `album_image` VALUES (4, '星空璀璨', '繁星点点的夜空,银河横跨天际,令人沉醉的夜景', 'https://images.unsplash.com/photo-1441716844725-09cedc13a4e7?ixlib=rb-1.2.1&auto=format&fit=crop&w=3840&q=100', 1, NULL);
INSERT INTO `album_image` VALUES (5, '绿色山谷', '郁郁葱葱的山谷,清新的空气与翠绿的植被构成和谐画面', 'https://images.unsplash.com/photo-1472214103451-9374bd1c798e?ixlib=rb-1.2.1&auto=format&fit=crop&w=3840&q=100', 1, NULL);
INSERT INTO `album_image` VALUES (6, '京都古寺', '日本京都的传统寺庙,展现着东方古典建筑的独特魅力', 'https://images.unsplash.com/photo-1542051841857-5f90071e7989?ixlib=rb-1.2.1&auto=format&fit=crop&w=3840&q=100', 1, NULL);
INSERT INTO `album_image` VALUES (7, '城市夜景', '灯火通明的现代都市,霓虹闪烁的夜晚风景', 'https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?ixlib=rb-1.2.1&auto=format&fit=crop&w=3840&q=100', 1, NULL);
INSERT INTO `album_image` VALUES (8, '海边日落', '金色的夕阳映照在海面上,浪花轻轻拍打着沙滩', 'https://images.unsplash.com/photo-1504198322253-cfa87a0ff25f?ixlib=rb-1.2.1&auto=format&fit=crop&w=3840&q=100', 1, NULL);
INSERT INTO `album_image` VALUES (9, '樱花季节', '粉色的樱花绽放,营造出浪漫唯美的春日氛围', 'https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?ixlib=rb-1.2.1&auto=format&fit=crop&w=3840&q=100', 1, NULL);
INSERT INTO `album_image` VALUES (10, '繁华都市', '现代化的城市景观,高楼大厦鳞次栉比', 'https://images.unsplash.com/photo-1503614472-8c93d56e92ce?ixlib=rb-1.2.1&auto=format&fit=crop&w=3840&q=100', 1, NULL);
INSERT INTO `album_image` VALUES (11, '雪山之巅', '巍峨的雪山山峰,白雪皑皑,云雾缭绕', 'https://images.unsplash.com/photo-1444464666168-49d633b86797?ixlib=rb-1.2.1&auto=format&fit=crop&w=3840&q=100', 1, NULL);
INSERT INTO `album_image` VALUES (13, '街头巷尾', '充满生活气息的街道,记录着城市的日常点滴', 'https://images.unsplash.com/photo-1492571350019-22de08371fd3?ixlib=rb-1.2.1&auto=format&fit=crop&w=3840&q=100', 1, NULL);
INSERT INTO `album_image` VALUES (14, '晨光熹微', '清晨的第一缕阳光,唤醒沉睡的大地', 'https://images.unsplash.com/photo-1494548162494-384bba4ab999?ixlib=rb-1.2.1&auto=format&fit=crop&w=3840&q=100', 1, NULL);
INSERT INTO `album_image` VALUES (15, '极光之夜', '绚丽的北极光在夜空中舞动,创造出梦幻般的景象', 'https://images.unsplash.com/photo-1504714146340-959ca07e1f38?ixlib=rb-1.2.1&auto=format&fit=crop&w=3840&q=100', 1, NULL);
INSERT INTO `album_image` VALUES (16, '山水如画', '如诗如画的山水风景,展现大自然的壮美与和谐', 'https://images.unsplash.com/photo-1501785888041-af3ef285b470?ixlib=rb-1.2.1&auto=format&fit=crop&w=3840&q=100', 1, NULL);

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章标题',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文章介绍',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章主要内容',
  `cover` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文章封面',
  `view` int NULL DEFAULT 0 COMMENT '文章浏览量',
  `comment` int NULL DEFAULT 0 COMMENT '评论数量',
  `create_time` datetime NOT NULL COMMENT '文章创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (2, '🎉 ThriveX 现代化博客管理系统', 'Thrive 是一个简而不简单的现代化博客管理系统，专注于分享技术文章和知识，为技术爱好者和从业者提供一个分享、交流和学习的平台。用户可以在平台上发表自己的技术文章，或浏览其他用户分享的文章，并与他们进行讨论和互动。', '# ThriveX 现代化博客管理系统 🎉\n\n**🔥 首先最重要的事情放第一**\n\n**开源不易，麻烦占用** `10` 秒钟的时间帮忙点个免费的 `Star`，再此万分感谢！\n\n**下面开始进入主题↓↓↓**\n\n**🌈 项目介绍：** Thrive 是一个简而不简单的现代化博客管理系统，专注于分享技术文章和知识，为技术爱好者和从业者提供一个分享、交流和学习的平台。用户可以在平台上发表自己的技术文章，或浏览其他用户分享的文章，并与他们进行讨论和互动。\n\n**🗂️ 项目预览：** [https://liuyuyang.net/](https://liuyuyang.net/)\n\n**🛠️ 技术架构：**\n\n**前端：** React 、**Nextjs**、TypeScript、Zustand、**TailwindCSS**、Antd、Scss、Echarts\n\n**后端：** **Spring Boot**、Mybatis Plus、MySQL、Qiniu、Socket.io、Swagger\n\n**❤️ 项目初衷：**\n\n一直对网站开发领域很感兴趣，从小就希望有一个属于自己的网站，因此踏上了 `Web` 全栈开发的旅途，立志有朝一日也能开发一款属于自己的网站。如今历时1年有余，一个人从0到1独立完成前端、控制端、后端、数据库。也算是完成了从小的一个心愿吧\n\n## 项目演示\n\n### 前端\n\n**只演示部分，具体查看：** [https://liuyuyang.net](https://liuyuyang.net)\n\n![首页](https://bu.dusays.com/2024/10/17/6710eae3b6453.png)\n\n![足迹](https://bu.dusays.com/2024/09/17/66e97036dddcb.png)\n\n### 控制端\n\n![数据分析](https://bu.dusays.com/2024/09/17/66e97035726ae.png)\n\n![文件系统](https://bu.dusays.com/2024/09/17/66e97031cd456.png)\n\n## 项目运行\n\n**前端 or 控制端**\n\n**环境：** Nodejs18及以上\n\n```bash\nnpm i\nnpm run dev\n```\n\n**后端**\n\n**Java8**\n\n**直接安装maven依赖，配置相关的秘钥，最后运行**\n\n## 开源地址\n\n### 最新版（Nextjs + Spring Boot）\n\n**前端：**[LiuYuYang01/ThriveX-Blog (github.com)](https://github.com/LiuYuYang01/ThriveX-Blog)\n\n**控制端：**[LiuYuYang01/ThriveX-Admin (github.com)](https://github.com/LiuYuYang01/ThriveX-Admin)\n\n**后端：**[LiuYuYang01/ThriveX-Server (github.com)](https://github.com/LiuYuYang01/ThriveX-Server)\n\n### 旧版（Vue3 + Python Flask）\n\n**前端：**[LiuYuYang01/Thrive\\_Blog (github.com)](https://github.com/LiuYuYang01/Thrive_Blog)\n\n**控制端：**[LiuYuYang01/Thrive\\_Admin (github.com)](https://github.com/LiuYuYang01/Thrive_Admin)\n\n**后端：**[LiuYuYang01/Thrive\\_Server (github.com)](https://github.com/LiuYuYang01/Thrive_Server)\n\n![后台](https://bu.dusays.com/2024/09/17/66e96ca781d49.png)\n\n## 项目部署\n\n**等有时间单独教大家如何部署**\n\n## 技术支持\n\n**如果大家在部署过程中有任何疑问，可以选择付费（不提倡）或者选择给本项目拉人点** `10` 个 `star`\n\n**联系方式：**\n\n**微信：** liuyuyang2023\n\n**邮箱：**[liuyuyang1024@yeah.net](mailto:liuyuyang1024@yeah.net)\n\n## 最后\n\n**这个项目从前端到后端都是我从** `0` 到 `1` 敲出来的，所以刚开始一定会有很多隐藏的 `BUG`，希望大家能够及时在 `GitHub` 反馈，这样我也好加以改正，不断改善，成为最佳！\n\n**当然如果大家能够提交** `PR` 那再好不过了\n', 'https://bu.dusays.com/2024/09/17/66e97036dddcb.png', 49, 0, '2024-11-17 16:56:19');
INSERT INTO `article` VALUES (5, 'Markdown 语法全指南', '本文详细介绍了 Markdown 的各种语法，包括字符效果、列表、链接、引用、脚注、代码、数学公式、特殊符号、Emoji 表情、提示信息以及视频嵌入等，帮助用户快速掌握 Markdown 的使用技巧。', '# Markdown 样式\n\n## 一、字符效果\n\n| 类型 | 使用方法 | 效果 |  \n| :--: | :--: | :--: |\n| 删除线 | \\~\\~文本\\~\\~ | ~~文本效果~~ |\n| 斜体字 | \\_文本\\_ | _文本效果_ |\n| 粗体字 | \\*\\*文本\\*\\* | **文本效果** |\n| 上标 | \\~文本\\~ | ~文本效果~ |\n| 下标 | \\^文本\\^ | ^文本效果^ |\n| 标记 | \\=\\=文本\\=\\= | ==文本效果== |\n\n## 二、列表\n\n### 1、无序列表\n\n- 福建\n  - 厦门\n  - 福州\n- 浙江\n- 江苏\n\n### 2、有序列表\n\n1. 动物\n   1. 人类\n   2. 犬类\n2. 植物\n3. 微生物\n\n### 3、任务列表\n\n- [x] 预习计算机网络\n- [ ] 复习现代控制理论\n- [ ] 刷现代控制理论历年卷\n  - [ ] 2019 年期末试卷\n  - [ ] 2020 年期末试卷\n\n# 三、链接\n\n## 1、超链接\n\n1. 使用方法：\\[普通链接\\]\\(链接地址)\n2. 效果展示：[ThriveX 官网](https://thrivex.liuyuyang.net/)\n3. 在新窗口打开（待完善）：<a href=\"https://docs.liuyuyang.net/\" target=\"_blank\">ThriveX 文档</a>\n\n## 2、图片链接\n\n1. 使用方法：\\[图片名称\\]\\(图片地址)\n2. 效果展示：![星空宇航员](https://bu.dusays.com/2024/04/24/6628990012b51.jpg)\n\n## 四、引用\n\n1. 使用方法：\\> 这里写引用的内容\n2. 效果展示：\n> 这里写引用的内容\n\n## 五、脚注\n1. 使用方法：\\[^1\\]\n2. 效果展示：\n这是一个简单的脚注 [^1] 而这是一个更长的脚注 [^bignote].\n\n[^1]: 这是第一个脚注.\n[^bignote]: 这是一个非常长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长的脚注.\n\n## 六、代码\n\n### 1、行内代码\n\n1. 使用方法：\\` 代码 \\`\n2. 效果展示：`npm install marked`\n\n### 2、代码片段\n\n1. 使用方法：\n    1. 以\\`\\`\\` 开头  以\\`\\`\\` 结尾\n    2. \n2. 效果展示：\n```html\n<!DOCTYPE html>\n<html>\n    <head>\n        <mate charest=\"utf-8\" />\n        <title>Hello world!</title>\n    </head>\n    <body>\n        <h1>Hello world!</h1>\n    </body>\n</html>\n```\n\n## 七、数学公式\n\n### 1、行间公式：\n$\n\\sin(\\alpha)^{\\theta}=\\sum_{i=0}^{n}(x^i + \\cos(f))\n$\n\n### 2、行内公式\n$E=mc^2$\n\n## 八、特殊符号\n\n&copy; & &uml; &trade; &iexcl; &pound;\n&amp; &lt; &gt; &yen; &euro; &reg; &plusmn; &para; &sect; &brvbar; &macr; &laquo; &middot;\n\nX&sup2; Y&sup3; &frac34; &frac14; &times; &divide; &raquo;\n\n18&ordm;C &quot; &apos;\n\n## 九、Emoji 表情 🎉\n\n- 马：🐎\n- 星星：✨\n- 笑脸：😀\n- 跑步：🏃‍\n\n## 十、提示信息\n- 使用方法: \n    -  \\> \\[!类型\\] 标题 开头\n    -  \\> 正文\n\n> [!note] Note\n> 用于强调即使用户在快速浏览时也应考虑的重点信息。\n\n\n> [!Tip] Tip\n> 用于帮助用户更成功的可选信息。\n\n\n> [!Check] Check\n> xxxxxxxx\n\n\n> [!warning] Warning\n> 由于存在潜在风险，需要用户立即关注的关键内容。\n\n\n> [!Danger] Danger\n> 一个行为的潜在负面后果。# 数学公式\n\n\n## 视频\n\n### 自定义视频\n\n<h3>单视频</h3>\n<video width=\"640\" height=\"360\" controls>\n    <source src=\"http://vjs.zencdn.net/v/oceans.mp4\" type=\"video/mp4\">\n    您的浏览器不支持 HTML5 视频标签。\n</video>\n\n<h3>视频尺寸</h3>\n<video width=\"800\" controls>\n    <source src=\"http://vjs.zencdn.net/v/oceans.mp4\" type=\"video/mp4\">\n    您的浏览器不支持 HTML5 视频标签。\n</video>\n\n<h3>视频加封面</h3>\n<video width=\"640\" height=\"360\" controls poster=\"https://bu.dusays.com/2024/09/17/66e9704b2b809.png\">\n    <source src=\"http://vjs.zencdn.net/v/oceans.mp4\" type=\"video/mp4\">\n    您的浏览器不支持 HTML5 视频标签。\n</video>\n\n<h3>视频加封面加尺寸</h3>\n<video width=\"100%\" controls poster=\"https://bu.dusays.com/2024/09/17/66e9704b2b809.png\">\n    <source src=\"http://vjs.zencdn.net/v/oceans.mp4\" type=\"video/mp4\">\n    您的浏览器不支持 HTML5 视频标签。\n</video>\n\n\n### 哔哩哔哩视频\n\n<h3>默认宽度</h3>\n<iframe src=\"//player.bilibili.com/player.html?isOutside=true&aid=113651931481594&bvid=BV1yaB7YbEy6&cid=27343916591&p=1\" scrolling=\"no\" border=\"0\" frameborder=\"no\" framespacing=\"0\" allowfullscreen></iframe>\n\n<h3>自定义尺寸</h3>\n<iframe src=\"//player.bilibili.com/player.html?isOutside=true&aid=113651931481594&bvid=BV1yaB7YbEy6&cid=27343916591&p=1\" scrolling=\"no\" border=\"0\" frameborder=\"no\" framespacing=\"0\" allowfullscreen style=\"width:100%;height:500px\"></iframe>\n\n\n## 其他\n\n### 折叠\n<details>\n<summary>点击展开</summary>\n\n这里是折叠内容。\n\n</details>\n\n\n### 分割线\n___\n\n***\n\n---\n\n\n### 解析 HTML 标签\n<div style=\"color: red; font-size:30px\">ThriveX 现代化博客管理系统</div>', NULL, 15, 0, '2025-04-18 20:46:34');
INSERT INTO `article` VALUES (14, 'Docker常用命令与操作指南', '本文详细介绍了Docker的常用命令和操作，包括容器和镜像的管理、Dockerfile语法以及数据卷和网络配置等实用技巧，适合开发者快速掌握Docker的基本使用。', '```shell\ndocker run -it -v G:/docker/mysql/data:/var/lib/mysql -v G:/docker/mysql/config/my.cnf:/etc/mysql/my.cnf --restart=always --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql\ndocker run -it -v /var/lib/mysql:/var/lib/mysql -v /etc/mysql/my.cnf:/etc/mysql/my.cnf --restart=always --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:5.7\ndocker run -it -d -p 6379:6379 -v G:/docker/redis/config/redis.conf:/usr/local/etc/redis/redis.conf -v G:/docker/redis/data:/data --name docker-redis docker.io/redis redis-server /usr/local/etc/redis/redis.conf --appendonly yes\n命令解释\n-p 6379:6379 : 将容器的6379端口映射到主机的6379端口\n-v $PWD/docker/redis/conf:/etc/redis/redis.conf： 将主机中当前目录下的redis.conf映射成redis的启动配置文件\n-v $PWD /docker/redis/data:/data： 将主机中当前目录下 /docker/reids/data挂载到容器的/data\nredis-server /etc/redis/redis.conf：指定配置文件启动redis-server进程\n--requirepass \"123456\" :指定链接redis-server的密码\n--appendonly yes：开启数据持久化\nUsage: docker run [OPTIONS] IMAGE [COMMAND] [ARG...]\n-d, --detach=false         指定容器运行于前台还是后台，默认为false   \n-i, --interactive=false   打开STDIN，用于控制台交互  \n-t, --tty=false            分配tty设备，该可以支持终端登录，默认为false  \n-u, --user=\"\"              指定容器的用户  \n-a, --attach=[]            标准输入输出流和错误信息（必须是以非docker run -d启动的容器）\n-w, --workdir=\"\"           指定容器的工作目录\n-c, --cpu-shares=0        设置容器CPU权重，在CPU共享场景使用  \n-e, --env=[]               指定环境变量，容器中可以使用该环境变量  \n-m, --memory=\"\"            指定容器的内存上限  \n-P, --publish-all=false    指定容器暴露的端口  \n-p, --publish=[]           指定容器暴露的端口\n-h, --hostname=\"\"          指定容器的主机名  \n-v, --volume=[]            给容器挂载存储卷，挂载到容器的某个目录  \n--volumes-from=[]          给容器挂载其他容器上的卷，挂载到容器的某个目录\n--cap-add=[]               添加权限，权限清单详见：http://linux.die.net/man/7/capabilities  \n--cap-drop=[]              删除权限，权限清单详见：http://linux.die.net/man/7/capabilities  \n--cidfile=\"\"               运行容器后，在指定文件中写入容器PID值，一种典型的监控系统用法  \n--cpuset=\"\"                设置容器可以使用哪些CPU，此参数可以用来容器独占CPU  \n--device=[]                添加主机设备给容器，相当于设备直通  \n--dns=[]                   指定容器的dns服务器  \n--dns-search=[]            指定容器的dns搜索域名，写入到容器的/etc/resolv.conf文件  \n--entrypoint=\"\"            覆盖image的入口点  \n--env-file=[]              指定环境变量文件，文件格式为每行一个环境变量  \n--expose=[]                指定容器暴露的端口，即修改镜像的暴露端口  \n--link=[]                  指定容器间的关联，使用其他容器的IP、env等信息  \n--lxc-conf=[]              指定容器的配置文件，只有在指定--exec-driver=lxc时使用  \n--name=\"\"                  指定容器名字，后续可以通过名字进行容器管理，links特性需要使用名字  \n--net=\"bridge\"             容器网络设置:\nbridge 使用docker daemon指定的网桥     \nhost     //容器使用主机的网络  \ncontainer:NAME_or_ID  >//使用其他容器的网路，共享IP和PORT等网络资源  \nnone 容器使用自己的网络（类似--net=bridge），但是不进行配置\n--privileged=false         指定容器是否为特权容器，特权容器拥有所有的capabilities  \n--restart=\"no\"             指定容器停止后的重启策略:\nno：容器退出时不重启  \non-failure：容器故障退出（返回值非零）时重启\nalways：容器退出时总是重启  \n--rm=false                 指定容器停止后自动删除容器(不支持以docker run -d启动的容器)  \n--sig-proxy=true           设置由代理接受并处理信号，但是SIGCHLD、SIGSTOP和SIGKILL不能被代理\n```\ndocker补充命令\n* `docker start 容器名（容器ID也可以）`: 启动容器\n* `docker stop 容器名（容器ID也可以）`: 停止容器\n* `docker run 命令加 -d 参数`，docker 会将容器放到后台运行\n* `docker ps` 正在运行的容器\n* `docker logs --tail 10 -tf 容器名` 查看容器的日志文件,加-t是加上时间戳，f是跟踪某个容器的最新日志而不必读整个日志文件\n* `docker top` 容器名 查看容器内部运行的进程\n* `docker exec -d 容器名 touch /etc/new_config_file` 通过后台命令创建一个空文件\n* `docker run --restart=always --name 容器名 -d ubuntu /bin/sh -c \"while true;do echo hello world; sleep 1; done\" `无论退出代码是什么，docker都会自动重启容器，可以设置 --restart=on-failure:5 自动重启的次数\n* `docker inspect` 容器名 对容器进行详细的检查，可以加 --format=\'{(.State.Running)}\' 来获取指定的信息\n* `docker rm` 容器ID 删除容器，注，运行中的容器无法删除\n* `docker rm $(docker ps -aq)` 删除所有容器\n* `docker rmi $(docker images -aq)` 删除所有镜像\n* `docker images` 列出镜像\n* `docker pull` 镜像名:标签 拉镜像\n* `docker search` 查找docker Hub 上公共的可用镜像\n* `docker build -t=\'AT/web_server:v1\'` 命令后面可以直接加上github仓库的要目录下存在的Dockerfile文件。 命令是编写Dockerfile 之后使用的。-t选项为新镜像设置了仓库和名称:标签\n* `docker login` 登陆到Docker Hub，个人认证信息将会保存到$HOME/.dockercfg,\n* `docker commit -m=\"comment \" --author=\"AT\"` 容器ID 镜像的用户名/仓库名:标签 不推荐这种方法，推荐dockerfile\n* `docker history` 镜像ID 深入探求镜像是如何构建出来的\n* `docker port` 镜像ID 端口 查看映射情况的容器的ID和容器的端口号，假设查询80端口对应的映射的端口\n* `run` 运行一个容器， -p 8080:80 将容器内的80端口映射到docker宿主机的某一特定端口，将容器的80端口绑定到宿主机的8080端口，另 127.0.0.1:80:80 是将容器的80端口绑定到宿主机这个IP的80端口上，-P 是将容器内的80端口对本地的宿主机公开\n* `docker push` 镜像名 将镜像推送到 Docker Hub\n* `docker rmi` 镜像名 删除镜像\n* `docker attach` 容器ID 进入容器\n* `docker network create --subnet=172.171.0.0/16` docker-at 选取172.172.0.0网段\n* `docker build` 就可以加 -ip指定容器ip 172.171.0.10 了\ndocker 镜像操作\n```shell\n docker images：列出images\n docker images -a：列出所有的images（包含历史）\n docker images --tree ：显示镜像的所有层(layer)\n docker rmi  <image ID>：删除一个或多个image\n```\ndocker 容器操作\n```shell\n#启动容器\ndocker start <ContainerId(或者name)>\n#停止容器\ndocker stop <ContainerId(或者name)>\ndocker stop -t=60 容器ID或容器名\n参数 -t：关闭容器的限时，如果超时未能关闭则用kill强制关闭，默认值10s，这个时间用于容器的自己保存状态\n#重启容器\ndocker restart <ContainerId(或者name)>\n#删除容器\ndocker rm <ContainerId(或者name)>\ndocker rm -f 容器ID或容器名 ：直接删除正在运行的容器\n#删除所有容器\ndocker rm $(docker ps -a -q)\n#进入容器\ndocker exec -it containerID /bin/bash\n# 查看容器日志\ndocker logs -f -t --tail 行数 容器名\n```\nDockerfile语法\n* `MAINTAINER` 标识镜像的作者和联系方式\n* `EXPOSE` 可以指定多个EXPOSE向外部公开多个端口，可以帮助多个容器链接\n* `FROM` 指令指定一个已经存在的镜像\n* `RUN` 运行命令,会在shell 里使用命令包装器 /bin/sh -c 来执行。如果是在一个不支持shell 的平台上运行或者不希望在shell 中运行，也可以 使用exec 格式 的RUN指令\n* `ENV REFRESHED_AT` 环境变量 这个环境亦是用来表明镜像模板最后的更新时间\n* `VOLUME` 容器添加卷。一个卷是可以 存在于一个或多个容器内的特定的目录，对卷的修改是立刻生效的，对卷的修改不会对更新镜像产品影响，例:VOLUME[\"/opt/project\",\"/data\"]\n* `ADD` 将构建环境 下的文件 和目录复制到镜像 中。例 ADD nginx.conf /conf/nginx.conf 也可以是取url 的地址文件，如果是压缩包，ADD命令会自动解压、\n* `USER` 指定镜像用那个USER 去运行\n* `COPY` 是复制本地文件，而不会去做文件提取（解压包不会自动解压） 例：COPY conf.d/ /etc/apache2/ 将本地conf.d目录中的文件复制到/etc/apache2/目录中\n* `CMD` 类似于RUN指令，CMD指令也可用于运行任何命令或应用程序，不过，二者的运行时间点不同\n    * `RUN` 指令运行于映像文件构建过程中，而CMD指令运行于基于Dockerfile构建出的新镜像文件启动一个容器时。\n    * `CMD`指令的首要目的在于为启动的容器指定默认要运行的程序，且其运行结束后，容器也将终止;不过，CMD指定的命令其可以被docker run的命令行选项所覆盖\n    * 在Dockerfile中可以存在多个CMD指令，但仅最后一个生效\n> 你要尽全力保护你的梦想。那些嘲笑你梦想的人，因为他们必定会失败，他们想把你变成和他们一样的人。 ---《当幸福来敲门》', NULL, 28, 0, '2025-04-27 16:22:01');
INSERT INTO `article` VALUES (16, '线程池参数设置最佳实践', '文章详细介绍了如何根据CPU核数和任务类型（IO密集型或CPU密集型）合理设置线程池的核心线程数、最大线程数、队列长度等关键参数，并提供了具体的计算公式和实践建议，帮助开发者优化线程池性能。', '<h2 id=\"f2uP7\">线程运行规则</h2>\n\n\n![图片](https://oss.luoyuanxiang.top/thrivex/article/680eeb66e4b08588b0e0cf7f.jpeg)\n\n<h2 id=\"articleContentId\"><font style=\"color:rgb(79, 79, 79);\">手动设置线程池，就要合理设置最大线程数和核心线程数，按照网上大多数的说法，都是跟服务器的CPU有关</font></h2>\n1. <font style=\"color:rgb(77, 77, 77);\">先看下机器的CPU核数，然后在设定具体参数：</font>\n\n```java\nSystem.out.println(Runtime.getRuntime().availableProcessors());\n```\n\n<font style=\"color:rgb(77, 77, 77);\">即 CPU核数 = Runtime.getRuntime().availableProcessors()</font>\n\n2. <font style=\"color:rgb(77, 77, 77);\">分析下线程池处理的程序是CPU密集型，还是IO密集型</font>\n    1. <font style=\"color:rgb(77, 77, 77);\">IO密集型：大量网络，文件操作</font>\n    2. <font style=\"color:rgb(77, 77, 77);\">IO密集型：核心线程数 = CPU核数 * 2</font>\n    3. <font style=\"color:rgb(77, 77, 77);\">CPU 密集型：大量计算，cpu 占用越接近 100%, 耗费多个核或多台机器</font>\n    4. <font style=\"color:rgb(77, 77, 77);\">CPU密集型：核心线程数 = CPU核数 + 1</font>\n    5. <font style=\"color:rgb(77, 77, 77);\">注：IO密集型（某大厂实践经验）</font>\n    6. <font style=\"color:rgb(77, 77, 77);\">核心线程数 = CPU核数 / （1-阻塞系数） 例如阻塞系数 0.8，CPU核数为4，则核心线程数为20</font>\n\n\n![图片](https://oss.luoyuanxiang.top/thrivex/article/680eeb99e4b08588b0e0cf80.jpeg)\n\n<h2 id=\"AshX5\"><font style=\"color:rgb(77, 77, 77);\">maxPoolSize</font></h2>\n当系统负载达到最大值时，核心线程数已无法按时处理完所有任务，这时就需要增加线程。每秒200个任务需要20个线程，那么当每秒达到1000个任务时，则需要(1000-queueCapacity)*(20/200)，即60个线程，可将maxPoolSize设置为60。还有说法就是 cpuNUM*2 或者是cpuNUM*4\n\n<h2 id=\"TJ2to\">keepAliveTime</h2>\n线程数量只增加不减少也不行。当负载降低时，可减少线程数量，如果一个线程空闲时间达到keepAliveTiime，该线程就退出。默认情况下线程池最少会保持corePoolSize个线程。\n\n<h2 id=\"DFp3U\">allowCoreThreadTimeout</h2>\n默认情况下核心线程不会退出，可通过将该参数设置为true，让核心线程也退出。\n\n<h2 id=\"UawPT\">queueCapacity</h2>\n任务队列的长度要根据核心线程数，以及系统对任务响应时间的要求有关。队列长度可以设置为(corePoolSize/tasktime)*responsetime： (20/0.1)*2=400，即队列长度可设置为400。\n\n队列长度设置过大，会导致任务响应时间过长，切忌以下写法：\n\nLinkedBlockingQueue queue = new LinkedBlockingQueue();\n\n这实际上是将队列长度设置为Integer.MAX_VALUE，将会导致线程数量永远为corePoolSize，再也不会增加，当任务数量陡增时，任务响应时间也将随之陡增。\n\n\n\n', NULL, 22, 0, '2025-04-27 16:21:01');
INSERT INTO `article` VALUES (17, 'SQL查询性能优化指南', '本文总结了29条SQL查询性能优化的实用技巧，包括避免NULL值判断、慎用OR和IN操作、合理使用索引、减少全表扫描等，帮助开发者编写高效的数据库查询语句。', '2. 应尽量避免在where子句中对字段进行null值判断，否则将导致引擎放弃使用索引而进行全表扫描，如：\n```sql\nselect id from t where num is null\n```\n最好不要给数据库留`NULL`，尽可能的使用`NOT NULL`填充数据库.\n备注、描述、评论之类的可以设置为`NULL`，其他的，最好不要使用`NULL`。\n不要以为`NULL`不需要空间，比如：`char(100)`型，在字段建立时，空间就固定了， 不管是否插入值（`NULL`也包含在内），都是占用 100个字符的空间的，如果是`varchar`这样的变长字段，`null`不占用空间。\n可以在`num`上设置默认值0，确保表中num列没有null值，然后这样查询：\n```sql\nselect id from t where num = 0\n```\n3. 应尽量避免在`where`子句中使用`!=`或`<>`操作符，否则将引擎放弃使用索引而进行全表扫描。\n4. 应尽量避免在`where`子句中使用`or`来连接条件，如果一个字段有索引，一个字段没有索引，将导致引擎放弃使用索引而进行全表扫描，如：\n```sql\nselect id from t where num=10 or Name = \'admin\'\n```\n可以这样查询：\n```sql\nselect id from t where num = 10\nunion all\nselect id from t where Name = \'admin\'\n```\n5. `in`和`not in`也要慎用，否则会导致全表扫描，如：\n```sql\nselect id from t where num in(1,2,3)\n```\n对于连续的数值，能用 `between` 就不要用`in`了：\n```sql\nselect id from t where num between 1 and 3\n```\n很多时候用`exists`代替`in`是一个好的选择：\n```sql\nselect num from a where num in(select num from b)\n```\n用下面的语句替换：\n```sql\nselect num from a where exists(select 1 from b where num=a.num)\n```\n6. 下面的查询也将导致全表扫描：\n```sql\nselect id from t where name like ‘%abc%’\n```\n若要提高效率，可以考虑全文检索。\n7. 如果在`where`子句中使用参数，也会导致全表扫描。因为`SQL`只有在运行时才会解析局部变量，但优化程序不能将访问计划的选择推迟到运行时；它必须在编译时进行选择。然 而，如果在编译时建立访问计划，变量的值还是未知的，因而无法作为索引选择的输入项。如下面语句将进行全表扫描：\n```sql\nselect id from t where num = @num\n```\n可以改为强制查询使用索引：\n```sql\nselect id from t with(index(索引名)) where num = @num\n```\n应尽量避免在 `where` 子句中对字段进行表达式操作，这将导致引擎放弃使用索引而进行全表扫描。如：\n```sql\nselect id from t where num/2 = 100\n```\n应改为:\n```sql\nselect id from t where num = 100*2\n```\n8. 应尽量避免在where子句中对字段进行函数操作，这将导致引擎放弃使用索引而进行全表扫描。如：\n```sql\nselect id from t where substring(name,1,3) = ’abc’       -–name以abc开头的id\nselect id from t where datediff(day,createdate,’2005-11-30′) = 0    -–‘2005-11-30’    --生成的id\n```\n应改为:\n```sql\nselect id from t where name like \'abc%\'\nselect id from t where createdate >= \'2005-11-30\' and createdate < \'2005-12-1\'\n```\n9. 不要在`where`子句中的=左边进行函数、算术运算或其他表达式运算，否则系统将可能无法正确使用索引。\n10. 在使用索引字段作为条件时，如果该索引是复合索引，那么必须使用到该索引中的第一个字段作为条件时才能保证系统使用该索引，否则该索引将不会被使用，并且应尽可能的让字段顺序与索引顺序相一致。\n11. 不要写一些没有意义的查询，如需要生成一个空表结构：\n```sql\nselect col1,col2 into #t from t where 1=0\n```\n这类代码不会返回任何结果集，但是会消耗系统资源的，应改成这样：\n```sql\ncreate table #t(…)\n```\n12. `Update`语句，如果只更改1、2个字段，不要`Update`全部字段，否则频繁调用会引起明显的性能消耗，同时带来大量日志。\n13. 对于多张大数据量（这里几百条就算大了）的表`JOIN`，要先分页再`JOIN`，否则逻辑读会很高，性能很差。\n14. `select count(*) from table；`这样不带任何条件的`count`会引起全表扫描，并且没有任何业务意义，是一定要杜绝的。\n15. 索引并不是越多越好，索引固然可以提高相应的`select`的效率，但同时也降低了`insert`及`update`的效率，因为`insert`或`update`时有可能会重建索引，所以怎样建索引需要慎重考虑，视具体情况而定。一个表的索引数最好不要超过6个，若太多则应考虑一些不常使用到的列上建的索引是否有 必要。\n16. 应尽可能的避免更新`clustered`索引数据列，因为`clustered`索引数据列的顺序就是表记录的物理存储顺序，一旦该列值改变将导致整个表记录的顺序的调整，会耗费相当大的资源。若应用系统需要频繁更新`clustered`索引数据列，那么需要考虑是否应将该索引建为`clustered`索引。\n17. 尽量使用数字型字段，若只含数值信息的字段尽量不要设计为字符型，这会降低查询和连接的性能，并会增加存储开销。这是因为引擎在处理查询和连 接时会逐个比较字符串中每一个字符，而对于数字型而言只需要比较一次就够了。\n18. 尽可能的使用`varchar/nvarchar`代替`char/nchar`，因为首先变长字段存储空间小，可以节省存储空间，其次对于查询来说，在一个相对较小的字段内搜索效率显然要高些。\n19. 任何地方都不要使用`select * from t`用具体的字段列表代替*，不要返回用不到的任何字段。\n20. 尽量使用表变量来代替临时表。如果表变量包含大量数据，请注意索引非常有限（只有主键索引）。\n21. 避免频繁创建和删除临时表，以减少系统表资源的消耗。临时表并不是不可使用，适当地使用它们可以使某些例程更有效，例如，当需要重复引用大型表或常用表中的某个数据集时。但是，对于一次性事件， 最好使用导出表。\n22. 在新建临时表时，如果一次性插入数据量很大，那么可以使用`select into`代替`create table`，避免造成大量 `log` ，以提高速度；如果数据量不大，为了缓和系统表的资源，应先`create table`，然后`insert`。\n23. 如果使用到了临时表，在存储过程的最后务必将所有的临时表显式删除，先`truncate table`，然后`drop table`，这样可以避免系统表的较长时间锁定。\n24. 尽量避免使用游标，因为游标的效率较差，如果游标操作的数据超过1万行，那么就应该考虑改写。\n25. 使用基于游标的方法或临时表方法之前，应先寻找基于集的解决方案来解决问题，基于集的方法通常更有效。\n26. 与临时表一样，游标并不是不可使用。对小型数据集使用`FAST_FORWARD`游标通常要优于其他逐行处理方法，尤其是在必须引用几个表才能获得所需的数据时。在结果集中包括“合计”的例程通常要比使用游标执行的速度快。如果开发时 间允许，基于游标的方法和基于集的方法都可以尝试一下，看哪一种方法的效果更好。\n27. 在所有的存储过程和触发器的开始处设置`SET NOCOUNT ON`，在结束时设置`SET NOCOUNT OFF`。无需在执行存储过程和触发器的每个语句后向客户端发送`DONE_IN_PROC`消息。\n28. 尽量避免大事务操作，提高系统并发能力。\n29. 尽量避免向客户端返回大数据量，若数据量过大，应该考虑相应需求是否合理。\n> 人所缺乏的不是才干而是志向，不是成功的能力而是勤劳的意志。 —— 部尔卫', NULL, 20, 0, '2025-04-27 16:20:30');
INSERT INTO `article` VALUES (18, '单例模式详解与实现方式', '本文介绍了单例模式的核心思想、目的及多种实现方式，包括饿汉式、懒汉式、双重检查、静态内部类和枚举等，帮助开发者理解如何确保类只有一个实例并提供全局访问点。', '## 核心思想\n1) 确保类只有一个实例。\n2) 提供一个全局访问点来获取这个实例。\n## 目的\n1) 控制资源的使用，通过共享唯一的实例来优化系统性能。\n2) 避免频繁创建和销毁对象的开销。\n## 实现方式\n1) 私有构造方法，防止外部实例化。\n2) 静态私有成员变量，存储唯一实例。\n3) 静态公共方法，提供全局访问点。\n## 常见实现方式\n1) [饿汉式（静态常量）](#饿汉式静态常量)\n2) [饿汉式（静态代码块）](#饿汉式静态代码块)\n3) [懒汉式（线程不安全）](#懒汉式线程不安全)\n4) [懒汉式（线程安全，同步方法）](#懒汉式线程安全同步方法)\n5) [懒汉式（线程安全，同步代码块）](#懒汉式线程安全同步代码块)\n6) [双重检查](#双重检查)\n7) [静态内部类](#静态内部类)\n8) [枚举](#枚举)\n## 示例\n### 饿汉式（静态常量）\n```java\n   class Singleton {\n       // 直接创建对象实例，并且通过private static final修饰\n       private static final Singleton instance = new Singleton();\n       // 构造方法私有化，防止外部通过new创建实例\n       private Singleton() {}\n       // 提供一个公共的静态方法返回实例\n       public static Singleton getInstance() {\n           return instance;\n       }\n   }\n```\n这种方式在类加载的时候就创建了单例对象。因为`static`变量在类加载阶段初始化，并且`final`关键字保证了这个引用不可变。由于类加载过程是由类加载器来保证同步的，所以在多线程环境下是安全的。它的优点是实现简单，并且在多线程环境下安全可靠。缺点是可能会造成资源的浪费，因为如果这个单例对象一直没有被使用，但是在类加载的时候就已经创建了。\n### 饿汉式（静态代码块）\n```java\n   class Singleton {\n       private static Singleton instance;\n       static {\n           // 在静态代码块中创建实例\n           instance = new Singleton();\n       }\n       private Singleton() {}\n       public static Singleton getInstance() {\n           return instance;\n       }\n   }\n```\n与饿汉式（静态常量）类似，在类加载时执行静态代码块，创建单例对象。在多线程环境下也是安全的，因为类加载过程是线程安全的。不过同样存在资源可能被浪费的问题，即如果单例对象一直未被使用，却在类加载时就已经创建。\n### 懒汉式（线程不安全）\n```java\n   class Singleton {\n       private static Singleton instance;\n       private Singleton() {}\n       public static Singleton getInstance() {\n           if (instance == null) {\n               // 如果实例不存在，则创建实例\n               instance = new Singleton();\n           }\n           return instance;\n       }\n   }\n```\n这种方式是在第一次调用`getInstance`方法时才创建实例。但是在多线程环境下是不安全的。例如，当两个线程同时调用`getInstance`方法，并且都判断`instance`为`null`时，会创建两个不同的实例，这就违背了单例模式的初衷。不过这种方式在单线程环境下或者对实例创建时机要求比较灵活的情况下可以使用，因为它实现了延迟加载，只有在真正需要实例的时候才会创建。\n### 懒汉式（线程安全，同步方法）\n```java\n   class Singleton {\n       private static Singleton instance;\n       private Singleton() {}\n       public static synchronized Singleton getInstance() {\n           if (instance == null) {\n               instance = new Singleton();\n           }\n           return instance;\n       }\n   }\n```\n在`getInstance`方法上添加了`synchronized`关键字，这使得该方法在多线程环境下是线程安全的。当多个线程访问`getInstance`方法时，会排队依次执行。不过这种方式的性能较差，因为每次调用`getInstance`方法都需要获取锁，即使实例已经创建，也需要等待锁的释放，会增加额外的开销。\n### 懒汉式（线程安全，同步代码块）\n```java\n   class Singleton {\n       private static Singleton instance;\n       private Singleton() {}\n       public static Singleton getInstance() {\n           if (instance == null) {\n               synchronized (Singleton.class) {\n                   if (instance == null) {\n                       instance = new Singleton();\n                   }\n               }\n           }\n           return instance;\n       }\n   }\n```\n这种方式是对懒汉式进行了优化。首先判断`instance`是否为`null`，如果为`null`，再进入同步代码块。在同步代码块中再次判断`instance`是否为`null`，这样可以避免在实例已经创建后，每次获取实例都需要获取锁的开销。不过这种方式的实现相对复杂一些，而且如果在多线程环境下，第一次创建实例时，多个线程同时判断`instance`为`null`，还是会有多个线程竞争锁的情况。\n### 双重检查\n```java\n   class Singleton {\n       private static volatile Singleton instance;\n       private Singleton() {}\n       public static Singleton getInstance() {\n           if (instance == null) {\n               synchronized (Singleton.class) {\n                   if (instance == null) {\n                       instance = new Singleton();\n                   }\n               }\n           }\n           return instance;\n       }\n   }\n```\n与懒汉式（线程安全，同步代码块）类似，但是这里添加了`volatile`关键字来修饰`instance`变量。这是因为在`instance = new Singleton();`这一操作不是原子操作，它包含了分配内存空间、初始化对象、将对象引用赋值给`instance`三个步骤。在没有`volatile`关键字的情况下，可能会出现一个线程已经分配了内存空间并初始化了对象，但是还没有将对象引用赋值给`instance`，另一个线程就判断`instance`为`null`，从而又创建一个新的对象。`volatile`关键字保证了变量的可见性，使得其他线程能够及时看到`instance`的最新状态。\n### 静态内部类\n```java\n   class Singleton {\n       private Singleton() {}\n       private static class SingletonHolder {\n           private static final Singleton INSTANCE = new Singleton();\n       }\n       public static Singleton getInstance() {\n           return SingletonHolder.INSTANCE;\n       }\n   }\n```\n当外部类`Singleton`被加载时，内部类`SingletonHolder`不会被加载。只有当调用`getInstance`方法时，才会加载`SingletonHolder`类，从而创建单例对象`INSTANCE`。这种方式既保证了线程安全（因为类加载是线程安全的），又实现了懒加载，避免了资源的浪费。它结合了懒汉式和饿汉式的优点，是一种比较推荐的单例模式实现方式。\n### 枚举\n```java\n   enum Singleton {\n       INSTANCE;\n       public void doSomething() {\n           // 可以在这里定义单例对象的方法\n       }\n   }\n```\n利用枚举类型的特性来实现单例模式。在 Java 中，枚举类型的实例是唯一的，并且枚举的构造方法默认是私有的，这就保证了只能有一个实例存在。枚举类型还具有线程安全、防止反序列化重新创建对象等优点。使用枚举实现单例模式非常简洁，而且在功能上更加健壮，是一种比较优雅的实现方式。', NULL, 22, 0, '2025-04-27 16:19:49');
INSERT INTO `article` VALUES (19, 'Docker Compose配置文件详解', '本文详细介绍了Docker Compose配置文件的各个部分，包括version、services、networks和volumes的配置方法，以及服务配置中的build、cap_add、command、depends_on、deploy等关键配置项的使用说明。', '```yaml\nversion: \"3.8\"\nservices:\n  redis:\n    image: redis:alpine\n    ports:\n      - \"6379\"\n    networks:\n      - frontend\n    deploy:\n      replicas: 2\n      update_config:\n        parallelism: 2\n        delay: 10s\n      restart_policy:\n        condition: on-failure\n  db:\n    image: postgres:9.4\n    volumes:\n      - db-data:/var/lib/postgresql/data\n    networks:\n      - backend\n    deploy:\n      placement:\n        constraints:\n          - \"node.role==manager\"\n  vote:\n    image: dockersamples/examplevotingapp_vote:before\n    ports:\n      - \"5000:80\"\n    networks:\n      - frontend\n    depends_on:\n      - redis\n    deploy:\n      replicas: 2\n      update_config:\n        parallelism: 2\n      restart_policy:\n        condition: on-failure\n  result:\n    image: dockersamples/examplevotingapp_result:before\n    ports:\n      - \"5001:80\"\n    networks:\n      - backend\n    depends_on:\n      - db\n    deploy:\n      replicas: 1\n      update_config:\n        parallelism: 2\n        delay: 10s\n      restart_policy:\n        condition: on-failure\n  worker:\n    image: dockersamples/examplevotingapp_worker\n    networks:\n      - frontend\n      - backend\n    deploy:\n      mode: replicated\n      replicas: 1\n      labels: [APP=VOTING]\n      restart_policy:\n        condition: on-failure\n        delay: 10s\n        max_attempts: 3\n        window: 120s\n      placement:\n        constraints:\n          - \"node.role==manager\"\n  visualizer:\n    image: dockersamples/visualizer:stable\n    ports:\n      - \"8080:8080\"\n    stop_grace_period: 1m30s\n    volumes:\n      - \"/var/run/docker.sock:/var/run/docker.sock\"\n    deploy:\n      placement:\n        constraints:\n          - \"node.role==manager\"\nnetworks:\n  frontend:\n  backend:\nvolumes:\n  db-data:\n```\n顶层的`version`、`services`、`networks`和`volumes`将`Compose`配置文件分为四个部分，其中`version`指定`Compose`配置文件的版本，`services`定义服务，`networks`定义网络，`volumes`定义数据卷。\n### 服务配置\n服务定义了该服务启动的每个容器的配置，就像将命令行参数传递给docker run一样。比如以下配置：\n```yaml\nservices:\n  redis:\n    image: redis\n```\nservices下的redis是用户自定义的服务名称，redis下的image只是众多服务配置项中的其中一个，意思是指定镜像名称或id。下面就对服务的相关配置项进行一个总结。\n#### 1. build\n在构建时应用的配置项。一般直接指定Dockerfile所在文件夹路径，可以是绝对路径，或者相对于Compose配置文件的路径。可以指定为包含构建上下文（context）路径的字符串。例如：\n```yaml\nversion: \"3.8\"\nservices:\n  webapp:\n    build: ./dir\n```\n也可以使用context指定上下文路径，使用dockerfile基于上下文路径指定Dockerfile文件，使用args指定构建参数。例如：\n```yaml\nversion: \"3.8\"\nservices:\n  webapp:\n    build:\n      context: ./dir\n      dockerfile: Dockerfile-alternate\n      args:\n        buildno: 1\n```\n如果同时指定了build和image。例如：\n```yaml\nbuild: ./dir\nimage: webapp:tag\n```\nCompose会在./dir目录下构建一个名为webapp，标签为tag的镜像。\n{% note warning simple %}\n使用docker stack deploy时的注意事项：在swarm mode下部署堆栈时，build配置项被忽略。因为docker stack命令不会在部署之前构建镜像。\n{% endnote %}\n##### (1)context\n指定包含Dockerfile的目录路径或git仓库url。该目录是发送给Docker守护进程（Daemon）的构建上下文（context）。当配置的值是相对路径时，它将被解释为相对于Compose配置文件的路径。例如：\n```yaml\nbuild:\n  context: ./dir\n```\n指定上下文为Compose配置文件目录下的dir目录。也可以这样写：\n```yaml\nbuild: dir\n```\n##### (2)dockerfile\n指定Dockerfile文件。Compose会使用指定的Dockerfile文件构建镜像，但必须要指定构建上下文路径。例如：\n```yaml\nbuild:\n  context: .\n  dockerfile: Dockerfile-alternate\n```\nCompose会使用Compose配置文件所在目录下名为Dockerfile-alternate的Dockerfile文件构建镜像。\n##### (3)args\n添加构建参数，这些只能在构建过程中访问的环境变量。首先在Dockerfile文件中指定参数：\n```dockerfile\nARG buildno\nARG gitcommithash\nRUN echo \"Build number: $buildno\"\nRUN echo \"Based on commit: $gitcommithash\"\n```\n然后build中指定参数，以下两种写法都可以：\n```yaml\nbuild:\n  context: .\n  args:\n    buildno: 1\n    gitcommithash: cdc3b19\n```\n```yaml\nbuild:\n  context: .\n  args:\n    - buildno=1\n    - gitcommithash=cdc3b19\n```\n这时构建过程中使用的参数的值为args指定的值。在指定构建参数时也可以不指定值，在这种情况下，构建过程中使用的参数的值为运行Compose的环境中的值。例如：\n```yaml\nargs:\n  - buildno\n  - gitcommithash\n```\n{% note warning simple %}\n使用布尔值时的注意事项：YMAL中布尔类型的值（\"true\"、\"false\"、\"yes\"、\"no\"、\"on\"、\"off\"）必须用引号引起来，以便解析器将它们解释为字符串。\n{% endnote %}\n##### (4)cache_from\n{% note warning simple %}\n在3.2版的配置文件格式中加入\n{% endnote %}\n指定缓存解析镜像列表。例如：\n```yaml\nbuild:\n    context: .\n    cache_from:\n        - alpine:latest\n        - corp/web_app:3.14\n```\n##### (5)labels\n{% note warning simple %}\n在3.3版的配置文件格式中加入\n{% endnote %}\n将元数据以标签的形式添加到生成的镜像中。可以使用数组或字典两种格式。推荐使用反向DNS写法以避免和其他应用的标签冲突。例如：\n```yaml\nbuild:\n  context: .\n  labels:\n    com.example.description: \"Accounting webapp\"\n    com.example.department: \"Finance\"\n    com.example.label-with-empty-value: \"\"\n```\n```yaml\nbuild:\n  context: .\n  labels:\n    - \"com.example.description=Accounting webapp\"\n    - \"com.example.department=Finance\"\n    - \"com.example.label-with-empty-value\"\n```\n##### (6)network\n{% note warning simple %}\n在3.4版的配置文件格式中加入\n{% endnote %}\n设置容器网络连接以获取构建过程中的RUN指令。例如：\n```yaml\nbuild:\n  context: .\n  network: custom_network_1\n```\n设置为none可以在构建期间禁用网络连接。例如：\n```yaml\nbuild:\n  context: .\n  network: none\n```\n(7)shm_size\n{% note warning simple %}\n在3.5版的配置文件格式中加入\n{% endnote %}\n指定容器的/dev/shm分区大小。指定的值为表示字节数的整数值或表示字节值的字符串。例如：\n```yaml\nbuild:\n  context: .\n  shm_size: 10000000\n```\n```yaml\nbuild:\n  context: .\n  shm_size: \'2gb\'\n```\n##### (8)target\n{% note warning simple %}\n在3.4版的配置文件格式中加入\n{% endnote %}\n指定在Dockerfile中定义的构建阶段，即镜像只构建到指定阶段就停止构建。例如：\n```yaml\nbuild:\n  context: .\n  target: prod\n```\n指定构建阶段为prod，即镜像只构建到prod阶段，prod阶段之后的内容不会被构建。\n#### 2.cap_add、cap_drop\n添加或删除容器内核能力（capability）。完整的capability列表可查看man 7 capabilities。例如，让容器拥有所有内核能力：\n```yaml\ncap_add:\n  - ALL\n```\n例如，删除NET_ADMIN和SYS_ADMIN能力：\n```yaml\ncap_drop:\n  - NET_ADMIN\n  - SYS_ADMIN\n```\n使用docker stack deploy时的注意事项：在swarm mode下部署堆栈时，cap_add和cap_drop配置项将被忽略。\n#### 3.cgroup_parent\n为容器指定一个可选的父控制组。例如：\n```yaml\ncgroup_parent: m-executor-abcd\n```\n使用docker stack deploy时的注意事项：在swarm mode下部署堆栈时，cgroup_parent配置项将被忽略。\n#### 4.command\n覆盖容器启动后默认执行的命令。可以写成字符串形式。例如：\n```yaml\ncommand: bundle exec thin -p 3000\n```\n也可以写成JSON数组形式。例如：\n```yaml\ncommand: [\"bundle\", \"exec\", \"thin\", \"-p\", \"3000\"]\n```\n#### 5.configs\n{% note warning simple %}\n在3.3版的配置文件格式中加入\n{% endnote %}\n为每个服务授予对配置（configs）的访问权限。支持short和long两种格式的语法。更多configs信息，参考configs。\n注意：该配置（config）必须已存在或者在堆栈文件顶层configs配置项中定义，否则堆栈部署将失败。\nshort语法仅指定config名称来授予容器访问config的权限并将其挂载到容器的/<config_name>上。source名称和目标挂载点都设置为config名称。例如以下示例，授予了redis服务对configs的my_config和my_other_config的访问权限，其中my_config的值设置到文件./my_config.txt的内容中，my_other_config定义为外部资源，这意味着它已经在Docker中通过运行docker config create命令或其他堆栈部署进行定义，如果外部config不存在，堆栈部署将会失败并显示config not found错误：\n````yaml\nversion: \"3.8\"\nservices:\n  redis:\n    image: redis:latest\n    deploy:\n      replicas: 1\n    configs:\n      - my_config\n      - my_other_config\nconfigs:\n  my_config:\n    file: ./my_config.txt\n  my_other_config:\n    external: true\n````\nlong语法提供了在服务的任务容器内如何创建config的更多粒度：\n* source：Docker中存在的config名称。\n* target：指定要挂载到服务的任务容器的文件的路径加名称。如果未指定，默认为/。\n* uid和gid：指定服务的任务容器所拥有的该文件的UID或GID。如果在LInux中未指定，两者都默认为0。不支持Windows。\n* mode：以八进制表示法指定要挂载到服务的任务容器的文件权限。例如，0444代表可读。默认值就为0444。config内容已挂载到临时文件系统中，所以不可写，如果设置了可写位将被忽略。可以设置可执行位。如果不熟悉UNIX文件权限模式，可以使用权限计算器 。\n  例如以下示例，指定config名称为my_config，授予redis服务对my_config的访问权限，指定要挂载到redis服务的任务容器的路径加文件名称为/redis_config，指定UID和GID均为103，指定要挂载到服务的任务容器的文件权限为0440（group-readable），但该redis服务没有访问my_other_config的权限：\n```yaml\nversion: \"3.8\"\nservices:\n  redis:\n    image: redis:latest\n    deploy:\n      replicas: 1\n    configs:\n      - source: my_config\n        target: /redis_config\n        uid: \'103\'\n        gid: \'103\'\n        mode: 0440\nconfigs:\n  my_config:\n    file: ./my_config.txt\n  my_other_config:\n    external: true\n```\n可以授予服务访问多个config的权限，并且可以混合long和short语法。定义config并不意味着授予服务对其的访问权限。\n#### 6.container_name\n指定自定义容器的名称，而不是使用默认名称。例如：\n```yaml\ncontainer_name: my-web-container\n```\n因为Docker容器的名称必须唯一，所以为一个服务指定了自定义容器名称后，该服务不能进行扩展。如果尝试为该服务扩容将会导致错误。\n使用docker stack deploy时的注意事项：在swarm mode下部署堆栈时，container_name配置项将被忽略。\n#### 7.credential_spec\n{% note warning simple %}\n在3.3版的配置文件格式中加入\n在3.8或更高版本文件格式中支持将组托管服务帐户（GMSA）配置与Compose配置文件一起使用。\n{% endnote %}\n配置托管服务帐户的凭据规格（credential spec）。此选项仅用于使用Windows容器的服务。credential_spec配置必须采用file://或registry://格式。使用file:时，引用的文件必须存在于Docker数据目录的CredentialSpecs子目录中，在Windows上，Docker数据目录默认为C:\\ProgramData\\Docker\\。以下示例从名为C:\\ProgramData\\Docker\\CredentialSpecs\\my-credential-spec.json的文件加载凭证规格：\n```yaml\ncredential_spec:\n  file: my-credential-spec.json\n```\n使用registry:时，将从守护进程主机上的Windows注册表中读取凭据规格。其注册表值必须位于HKLM\\SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Virtualization\\Containers\\CredentialSpecs。以下示例从注册表中名为my-credential-spec的值加载凭证规格：\n```yaml\ncredential_spec:\n  registry: my-credential-spec\n```\n为服务配置GMSA凭据规格时，只需用config指定凭据规格。例如：\n```yaml\nversion: \"3.8\"\nservices:\n  myservice:\n    image: myimage:latest\n    credential_spec:\n      config: my_credential_spec\nconfigs:\n  my_credentials_spec:\n    file: ./my-credential-spec.json\n```\n#### 8.depends_on\n指定服务之间的依赖关系，解决服务启动先后顺序问题。指定服务之间的依赖关系，将会导致以下行为：\n* docker-compose up以依赖顺序启动服务。\n* docker-compose up SERVICE会自动包含SERVICE的依赖项。\n* docker-compose stop以依赖顺序停止服务。\n  例如以下示例：\n```yaml\nversion: \"3.8\"\nservices:\n  web:\n    build: ../../_posts/随笔\n    depends_on:\n      - db\n      - redis\n  redis:\n    image: redis\n  db:\n    image: postgres\n```\n启动时会先启动db和redis，最后才启动web。在使用docker-compose up web启动web时，也会启动db和redis，因为在web服务中指定了依赖关系。在停止时也在web之前先停止db和redis。\n{% note warning simple %}\n使用depends_on时的注意事项：\n* 服务不会等待该服务所依赖的服务完全启动之后才启动。例如上例，web不会等到db和redis完全启动之后才启动。\n* V3版不再支持的condition形式的depends_on。\n* V3版中，在swarm mode下部署堆栈时，depends_on配置项将被忽略。\n  {% endnote %}\n#### 9.deploy\n{% note warning simple %}\n在3版的配置文件格式中加入\n{% endnote %}\n指定部署和运行服务的相关配置。该配置仅在swarm mode下生效，并只能通过docker stack deploy命令部署，docker-compose up和docker-compose run命令将被忽略。例如：\n```yaml\nversion: \"3.8\"\nservices:\n  redis:\n    image: redis:alpine\n    deploy:\n      replicas: 6\n      placement:\n        max_replicas_per_node: 1\n      update_config:\n        parallelism: 2\n        delay: 10s\n      restart_policy:\n        condition: on-failure\n```\ndeploy配置项中包含endpoint_mode、labels、mode、placement、replicas、resources、restart_policy、update_config等子配置项。\n##### (1)endpoint_mode\n{% note warning simple %}\n在3.2版的配置文件格式中加入\n{% endnote %}\n为外部客户端连接到swarm指定服务发现方式：\n* endpoint_mode: vip：Docker为服务分配了一个前端的虚拟IP，客户端通过该虚拟IP访问网络上的服务。Docker在客户端和服务的可用工作节点之间进行路由请求，而无须关系有多少节点正在参与该服务或这些节点的IP地址或者端口。这是默认设置。\n* endpoint_mode: dnsrr：DNS轮询（DNSRR），Docker设置服务的DNS条目，以便对服务名称的DNS查询返回IP地址列表，并且客户端通过轮询的方式直接连接到其中之一。\n  例如：\n```yaml\nversion: \"3.8\"\nservices:\n  wordpress:\n    image: wordpress\n    ports:\n      - \"8080:80\"\n    deploy:\n      mode: replicated\n      replicas: 2\n      endpoint_mode: vip\n```', NULL, 18, 0, '2025-04-27 16:18:56');
INSERT INTO `article` VALUES (20, 'Shiro与JWT实现分布式认证方案', '文章详细介绍了在分布式系统中使用Shiro进行权限控制，结合JWT替代传统Session解决跨域和集群认证问题。包含完整的Spring Boot集成代码示例，涵盖Token生成、自定义过滤器、Shiro配置等核心实现。', '`shiro` 用来认证用户及权限控制，`jwt`用来生成一个`token`，暂存用户信息。\n为什么不使用`session`而使用`jwt`？传统情况下是只有一个服务器，用户登陆后将一些信息以session的形式存储服务器上，\n然后将`sessionid`存储在本地`cookie`中，当用户下次请求时将会将`sessionid`传递给服务器，用于确认身份。\n但如果是分布式的情况下会出现问题，在服务器集群中，需要一个`session`数据库来存储每一个session，提供给集群中所有服务使用，且无法跨域(多个Ip)使用。\n而`jwt`是生成一个`token`存储在客户端，每次请求将其存储在`header`中，解决了跨域，且可以通过自定义的方法进行验证，解决了分布式验证的问题。\n缺点：无法在服务器注销、比`sessionid`大占带宽、一次性（想修改里面的内容，就必须签发一个新的`jwt`）\n二、废话不多说上代码\n`pom.xml`\n```xml\n<dependency>\n            <groupId>org.projectlombok</groupId>\n            <artifactId>lombok</artifactId>\n            <optional>true</optional>\n        </dependency>\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-web</artifactId>\n        </dependency>\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-test</artifactId>\n            <scope>test</scope>\n            <exclusions>\n                <exclusion>\n                    <groupId>org.junit.vintage</groupId>\n                    <artifactId>junit-vintage-engine</artifactId>\n                </exclusion>\n            </exclusions>\n        </dependency>\n        <dependency>\n            <groupId>org.mybatis.spring.boot</groupId>\n            <artifactId>mybatis-spring-boot-starter</artifactId>\n            <version>2.1.2</version>\n            <exclusions>\n                <exclusion>\n                    <groupId>org.mybatis</groupId>\n                    <artifactId>mybatis-spring</artifactId>\n                </exclusion>\n            </exclusions>\n        </dependency>\n        <dependency>\n            <groupId>mysql</groupId>\n            <artifactId>mysql-connector-java</artifactId>\n            <scope>runtime</scope>\n        </dependency>\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-aop</artifactId>\n        </dependency>\n        <!-- 工具 -->\n        <dependency>\n            <groupId>cn.hutool</groupId>\n            <artifactId>hutool-all</artifactId>\n            <version>5.2.3</version>\n        </dependency>\n        <!-- 密码加密 -->\n        <dependency>\n            <groupId>com.github.ulisesbocchio</groupId>\n            <artifactId>jasypt-spring-boot-starter</artifactId>\n            <version>2.1.0</version>\n        </dependency>\n        <dependency>\n            <groupId>com.alibaba</groupId>\n            <artifactId>fastjson</artifactId>\n            <version>1.2.62</version>\n        </dependency>\n        <!-- mybatis-plus -->\n        <dependency>\n            <groupId>com.baomidou</groupId>\n            <artifactId>mybatis-plus-boot-starter</artifactId>\n            <version>3.3.1</version>\n            <exclusions>\n                <exclusion>\n                    <groupId>org.mybatis</groupId>\n                    <artifactId>mybatis</artifactId>\n                </exclusion>\n            </exclusions>\n        </dependency>\n        <!-- 数据源 -->\n        <dependency>\n            <groupId>com.alibaba</groupId>\n            <artifactId>druid-spring-boot-starter</artifactId>\n            <version>1.1.21</version>\n        </dependency>\n        <!-- xss过滤组件 -->\n        <dependency>\n            <groupId>org.jsoup</groupId>\n            <artifactId>jsoup</artifactId>\n            <version>1.9.2</version>\n        </dependency>\n        <!-- restful api 文档 swagger2 -->\n        <dependency>\n            <groupId>io.springfox</groupId>\n            <artifactId>springfox-swagger2</artifactId>\n            <version>2.9.2</version>\n        </dependency>\n        <dependency>\n            <groupId>com.github.xiaoymin</groupId>\n            <artifactId>swagger-bootstrap-ui</artifactId>\n            <version>1.9.3</version>\n        </dependency>\n```\n重构`token`生成继承 `AuthenticationToken` 类\n```java\npackage com.luoyx.vjsb.authority.token;\nimport lombok.Data;\nimport org.apache.shiro.authc.AuthenticationToken;\n/**\n* <p>\n* 自定义token\n* </p>\n*\n* @author luoyuanxiang <p>luoyuanxiang.github.io</p>\n* @since 2020/3/19 17:06\n  */\n  @Data\n  public class Oauth2Token implements AuthenticationToken {\n  private static final long serialVersionUID = 8585428037102822625L;\n  /**\n    * json web token值\n      */\n      private String jwt;\n  public Oauth2Token(String jwt) {\n  this.jwt = jwt;\n  }\n  /**\n    * jwt\n    *\n    * @return jwt\n      */\n      @Override\n      public Object getPrincipal() {\n      return this.jwt;\n      }\n  /**\n    * 返回jwt\n    *\n    * @return jwt\n      */\n      @Override\n      public Object getCredentials() {\n      return this.jwt;\n      }\n  }\n```\n自定义过滤器，继承 `AuthenticatingFilter` 类\n```java\npackage com.luoyx.vjsb.authority.shiro.filter;\nimport cn.hutool.core.util.StrUtil;\nimport cn.hutool.json.JSONUtil;\nimport com.alibaba.fastjson.JSON;\nimport com.luoyx.vjsb.authority.token.Oauth2Token;\nimport com.luoyx.vjsb.authority.util.JsonWebTokenUtil;\nimport com.luoyx.vjsb.authority.vo.JwtAccount;\nimport com.luoyx.vjsb.common.properties.VjsbProperties;\nimport com.luoyx.vjsb.common.util.AjaxResult;\nimport com.luoyx.vjsb.common.util.IpUtil;\nimport io.jsonwebtoken.SignatureAlgorithm;\nimport lombok.Setter;\nimport lombok.extern.slf4j.Slf4j;\nimport org.apache.shiro.authc.AuthenticationException;\nimport org.apache.shiro.authc.AuthenticationToken;\nimport org.apache.shiro.web.filter.authc.AuthenticatingFilter;\nimport org.apache.shiro.web.util.WebUtils;\nimport org.springframework.data.redis.core.StringRedisTemplate;\nimport org.springframework.http.HttpStatus;\nimport org.springframework.web.bind.annotation.RequestMethod;\nimport javax.servlet.ServletRequest;\nimport javax.servlet.ServletResponse;\nimport javax.servlet.http.HttpServletRequest;\nimport javax.servlet.http.HttpServletResponse;\nimport java.io.IOException;\nimport java.io.PrintWriter;\nimport java.util.UUID;\nimport java.util.concurrent.TimeUnit;\n/**\n* <p>\n* 自定义过滤器配置\n* </p>\n*\n* @author luoyuanxiang <p>luoyuanxiang.github.io</p>\n* @since 2020/3/19 17:34\n  */\n  @Slf4j\n  @Setter\n  public class Oauth2Filter extends AuthenticatingFilter {\n  private final String expiredJwt = \"expiredJwt\";\n  private StringRedisTemplate redisTemplate;\n  private VjsbProperties properties;\n  /**\n    * 对跨域提供支持\n      */\n      @Override\n      protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {\n      HttpServletRequest httpServletRequest = (HttpServletRequest) request;\n      HttpServletResponse httpServletResponse = (HttpServletResponse) response;\n      httpServletResponse.setHeader(\"Access-control-Allow-Origin\", httpServletRequest.getHeader(\"Origin\"));\n      httpServletResponse.setHeader(\"Access-Control-Allow-Methods\", \"GET,POST,OPTIONS,PUT,DELETE\");\n      httpServletResponse.setHeader(\"Access-Control-Allow-Headers\", httpServletRequest.getHeader(\"Access-Control-Request-Headers\"));\n      // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态\n      if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {\n      httpServletResponse.setStatus(HttpStatus.OK.value());\n      return false;\n      }\n      return super.preHandle(request, response);\n      }\n  /**\n    * 先执行：isAccessAllowed 再执行onAccessDenied\n    * 如果返回true的话，就直接返回交给下一个filter进行处理。\n    * 如果返回false的话，回往下执行onAccessDenied\n    *\n    * @param request     the incoming <code>ServletRequest</code>\n    * @param response    the outgoing <code>ServletResponse</code>\n    * @param mappedValue the filter-specific config value mapped to this filter in the URL rules mappings.\n    * @return <code>true</code> if the request should proceed through the filter normally, <code>false</code> if the\n    * request should be processed by this filter\'s\n    * {@link #onAccessDenied(ServletRequest, ServletResponse, Object)} method instead.\n      */\n      @Override\n      protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {\n      return ((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name());\n      }\n  /**\n    * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；\n    * 如果返回false表示该拦截器实例已经处理了，将直接返回即可。\n    *\n    * @param request  the incoming <code>ServletRequest</code>\n    * @param response the outgoing <code>ServletResponse</code>\n    * @return <code>true</code> if the request should continue to be processed; false if the subclass will\n    * handle/render the response directly.\n    * @throws Exception if there is an error processing the request.\n      */\n      @Override\n      protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {\n      String token = getRequestToken((HttpServletRequest) request);\n      if (StrUtil.isBlank(token)) {\n      AjaxResult.responseWrite(JSON.toJSONString(AjaxResult.success(\"无权限访问\", 1007, null)), response);\n      return false;\n      }\n      return executeLogin(request, response);\n      }\n  @Override\n  protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {\n  // 这个创建token是在登录完成之后，去调用控制层时调用的，也就是要有token的时候，才会调用这个方法\n  return new Oauth2Token(getRequestToken(WebUtils.toHttp(request)));\n  }\n    /**\n     * 获取请求的token\n     */\n    private String getRequestToken(HttpServletRequest httpRequest) {\n        //从header中获取token\n        String token = httpRequest.getHeader(\"Authorization\");\n        //如果header中不存在token，则从参数中获取token\n        if (StrUtil.isBlank(token)) {\n            token = httpRequest.getParameter(\"Authorization\");\n        }\n        return token;\n    }\n    /**\n     * 登录失败处理\n     *\n     * @param token token\n     * @param e 异常\n     * @param request 1\n     * @param response 2\n     * @return boolean\n     */\n    @Override\n    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {\n        //处理登录失败的异常\n        Throwable throwable = e.getCause() == null ? e : e.getCause();\n        PrintWriter writer = null;\n        try {\n            writer = WebUtils.toHttp(response).getWriter();\n        } catch (IOException ignored) {\n        }\n        assert writer !=null;\n        // 这里做token验证处理，在验证器中验证token\n        String message = e.getMessage();\n        // 令牌过期\n        if (expiredJwt.equals(message)) {\n            String jwt = JsonWebTokenUtil.parseJwtPayload(token.getCredentials().toString());\n            JwtAccount jwtAccount = JSONUtil.toBean(jwt, JwtAccount.class);\n            String s = redisTemplate.opsForValue().get(\"JWT-SESSION-\" + IpUtil.getIpFromRequest((HttpServletRequest) request).toUpperCase() + jwtAccount.getSub());\n            if (s != null) {\n                // 重新申请新的JWT\n                String newJwt = JsonWebTokenUtil.createToken(UUID.randomUUID().toString(), jwtAccount.getSub(),\n                        \"token-server\", jwtAccount.getPassword(), properties.getExpire(), SignatureAlgorithm.HS512);\n                // 将签发的JWT存储到Redis： {JWT-SESSION-{appID} , jwt}\n                redisTemplate.opsForValue().set(\"JWT-SESSION-\" + IpUtil.getIpFromRequest((HttpServletRequest) request) + \"_\" + jwtAccount.getSub(), newJwt, properties.getExpire() << 1, TimeUnit.SECONDS);\n                writer.print(JSON.toJSONString(AjaxResult.success(\"刷新令牌\", 1006, newJwt)));\n            } else {\n                writer.print(JSON.toJSONString(AjaxResult.success(\"令牌无效！\", 1008, null)));\n            }\n            writer.flush();\n            return false;\n        }\n        writer.print(JSON.toJSONString(AjaxResult.success(message, 1008, null)));\n        writer.flush();\n        return false;\n    }\n}\n```\n配置`config`\n`shiroFilter`管理\n```java\npackage com.luoyx.vjsb.authority.shiro.filter;\nimport com.luoyx.vjsb.common.holder.SpringContextHolder;\nimport com.luoyx.vjsb.common.properties.VjsbProperties;\nimport lombok.extern.slf4j.Slf4j;\nimport org.apache.shiro.spring.web.ShiroFilterFactoryBean;\nimport org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;\nimport org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;\nimport org.apache.shiro.web.servlet.AbstractShiroFilter;\nimport org.springframework.data.redis.core.StringRedisTemplate;\nimport org.springframework.stereotype.Component;\nimport javax.annotation.Resource;\nimport javax.servlet.Filter;\nimport java.util.*;\n/**\n* <p>\n* Shiro Filter 管理器\n* </p>\n*\n* @author luoyuanxiang <p>luoyuanxiang.github.io</p>\n* @since 2020/3/20 11:00\n  */\n  @Slf4j\n  @Component\n  public class ShiroFilterChainManager {\n  @Resource\n  private StringRedisTemplate stringRedisTemplate;\n  @Resource\n  private VjsbProperties vjsbProperties;\n    /**\n     * 初始化获取过滤链\n     *\n     * @return java.util.Map<java.lang.String, javax.servlet.Filter>\n     */\n    public Map<String, Filter> initGetFilters() {\n        Map<String, Filter> filters = new LinkedHashMap<>();\n        Oauth2Filter jwtFilter = new Oauth2Filter();\n        jwtFilter.setRedisTemplate(stringRedisTemplate);\n        jwtFilter.setProperties(vjsbProperties);\n        filters.put(\"oauth2\", jwtFilter);\n        return filters;\n    }\n    /**\n     * 初始化获取过滤链规则\n     *\n     * @return java.util.Map<java.lang.String, java.lang.String>\n     */\n    public Map<String, String> initGetFilterChain() {\n        Map<String, String> filterChain = new LinkedHashMap<>();\n        // -------------anon 默认过滤器忽略的URL\n        List<String> defaultAnon = Arrays.asList(\"/css/**\", \"/js/**\", \"/login\");\n        defaultAnon.forEach(ignored -> filterChain.put(ignored, \"anon\"));\n        // -------------auth 默认需要认证过滤器的URL 走auth--PasswordFilter\n        List<String> defaultAuth = Collections.singletonList(\"/**\");\n        defaultAuth.forEach(auth -> filterChain.put(auth, \"oauth2\"));\n        return filterChain;\n    }\n    /**\n     * 动态重新加载过滤链规则\n     */\n    public void reloadFilterChain() {\n        ShiroFilterFactoryBean shiroFilterFactoryBean = SpringContextHolder.getBean(ShiroFilterFactoryBean.class);\n        AbstractShiroFilter abstractShiroFilter = null;\n        try {\n            abstractShiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();\n            assert abstractShiroFilter != null;\n            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) abstractShiroFilter.getFilterChainResolver();\n            DefaultFilterChainManager filterChainManager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();\n            filterChainManager.getFilterChains().clear();\n            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();\n            shiroFilterFactoryBean.setFilterChainDefinitionMap(this.initGetFilterChain());\n            shiroFilterFactoryBean.getFilterChainDefinitionMap().forEach(filterChainManager::createChain);\n        } catch (Exception e) {\n            log.error(e.getMessage(), e);\n        }\n    }\n}\n```\n`shiro`配置类\n```java\npackage com.luoyx.vjsb.authority.shiro.config;\nimport com.luoyx.vjsb.authority.shiro.filter.ShiroFilterChainManager;\nimport com.luoyx.vjsb.authority.shiro.realm.Oauth2Realm;\nimport lombok.extern.slf4j.Slf4j;\nimport org.apache.shiro.SecurityUtils;\nimport org.apache.shiro.mgt.DefaultSessionStorageEvaluator;\nimport org.apache.shiro.mgt.DefaultSubjectDAO;\nimport org.apache.shiro.mgt.SecurityManager;\nimport org.apache.shiro.session.mgt.DefaultSessionManager;\nimport org.apache.shiro.spring.web.ShiroFilterFactoryBean;\nimport org.apache.shiro.web.mgt.DefaultWebSecurityManager;\nimport org.springframework.context.annotation.Bean;\nimport org.springframework.context.annotation.Configuration;\n/**\n* <p>\n* shiro 权限配置\n* </p>\n*\n* @author luoyuanxiang <p>luoyuanxiang.github.io</p>\n* @since 2020/3/19 15:17\n  */\n  @Slf4j\n  @Configuration\n  public class ShiroConfiguration {\n  @Bean\n  public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, ShiroFilterChainManager filterChainManager) {\n  ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();\n  shiroFilterFactoryBean.setSecurityManager(securityManager);\n  shiroFilterFactoryBean.setFilters(filterChainManager.initGetFilters());\n  shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainManager.initGetFilterChain());\n  return shiroFilterFactoryBean;\n  }\n  @Bean\n  public SecurityManager securityManager() {\n  DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();\n  securityManager.setRealm(jwtRealm());\n       log.info(\"设置sessionManager禁用掉会话调度器\");\n       securityManager.setSessionManager(sessionManager());\n       // 无状态subjectFactory设置\n       DefaultSessionStorageEvaluator evaluator = (DefaultSessionStorageEvaluator) ((DefaultSubjectDAO) securityManager.getSubjectDAO()).getSessionStorageEvaluator();\n       evaluator.setSessionStorageEnabled(Boolean.FALSE);\n       StatelessDefaultSubjectFactory subjectFactory = new StatelessDefaultSubjectFactory();\n       securityManager.setSubjectFactory(subjectFactory);\n       SecurityUtils.setSecurityManager(securityManager);\n       return securityManager;\n  }\n  @Bean\n  public Oauth2Realm jwtRealm() {\n  return new Oauth2Realm();\n  }\n  /**\n    * session管理器\n    * sessionManager通过sessionValidationSchedulerEnabled禁用掉会话调度器，\n    * 因为我们禁用掉了会话，所以没必要再定期过期会话了。\n    *\n    * @return 1\n      */\n      @Bean\n      public DefaultSessionManager sessionManager() {\n      DefaultSessionManager sessionManager = new DefaultSessionManager();\n      sessionManager.setSessionValidationSchedulerEnabled(Boolean.FALSE);\n      return sessionManager;\n      }\n      }\nStatelessDefaultSubjectFactory\npackage com.luoyx.vjsb.authority.shiro.config;\nimport org.apache.shiro.subject.Subject;\nimport org.apache.shiro.subject.SubjectContext;\nimport org.apache.shiro.web.mgt.DefaultWebSubjectFactory;\n/**\n* <p>\n*\n* </p>\n*\n* @author luoyuanxiang <p>luoyuanxiang.github.io</p>\n* @since 2020/3/20 16:24\n  */\n  public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {\n  @Override\n  public Subject createSubject(SubjectContext context) {\n  //不创建session\n  context.setSessionCreationEnabled(false);\n  return super.createSubject(context);\n  }\n  }\n```\n`token`工具生成类\n```java\npackage com.luoyx.vjsb.authority.util;\nimport com.fasterxml.jackson.databind.ObjectMapper;\nimport com.luoyx.vjsb.authority.vo.JwtAccount;\nimport io.jsonwebtoken.*;\nimport io.jsonwebtoken.impl.DefaultHeader;\nimport io.jsonwebtoken.impl.DefaultJwsHeader;\nimport io.jsonwebtoken.impl.TextCodec;\nimport io.jsonwebtoken.impl.compression.DefaultCompressionCodecResolver;\nimport io.jsonwebtoken.lang.Assert;\nimport org.springframework.util.CollectionUtils;\nimport org.springframework.util.StringUtils;\nimport javax.xml.bind.DatatypeConverter;\nimport java.io.IOException;\nimport java.util.*;\n/**\n* <p>\n* token生成器\n* </p>\n*\n* @author luoyuanxiang <p>luoyuanxiang.github.io</p>\n* @since 2020/3/19 15:37\n  */\n  public class JsonWebTokenUtil {\n    public static final String SECRET_KEY = \"?::4343fdf4fdf6cvf):\";\n    private static final ObjectMapper MAPPER = new ObjectMapper();\n    private static final int COUNT_2 = 2;\n    private static CompressionCodecResolver codecResolver = new DefaultCompressionCodecResolver();\n    private JsonWebTokenUtil() {\n    }\n    /**\n     * json web token 签发\n     *\n     * @param id          令牌ID\n     * @param subject     用户ID\n     * @param issuer      签发人\n     * @param period      有效时间(秒)\n     * @param password    用户密码\n     * @param algorithm   加密算法\n     * @return java.lang.String\n     */\n    public static String createToken(String id, String subject, String issuer, String password, Long period, SignatureAlgorithm algorithm) {\n        // 当前时间戳\n        long currentTimeMillis = System.currentTimeMillis();\n        // 秘钥\n        byte[] secreKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);\n        JwtBuilder jwtBuilder = Jwts.builder();\n        Optional.ofNullable(id)\n                .ifPresent(i-> {\n                    jwtBuilder.setId(id);\n                });\n        if (!StringUtils.isEmpty(id)) {\n            jwtBuilder.setId(id);\n        }\n        if (!StringUtils.isEmpty(subject)) {\n            jwtBuilder.setSubject(subject);\n        }\n        if (!StringUtils.isEmpty(issuer)) {\n            jwtBuilder.setIssuer(issuer);\n        }\n        if (!StringUtils.isEmpty(password)) {\n            jwtBuilder.claim(\"password\", password);\n        }\n        // 设置签发时间\n        jwtBuilder.setIssuedAt(new Date(currentTimeMillis));\n        // 设置到期时间\n        if (null != period) {\n            jwtBuilder.setExpiration(new Date(currentTimeMillis + period * 1000));\n        }\n        // 压缩，可选GZIP\n        jwtBuilder.compressWith(CompressionCodecs.DEFLATE);\n        // 加密设置\n        jwtBuilder.signWith(algorithm, secreKeyBytes);\n        return jwtBuilder.compact();\n    }\n    /**\n     * 解析JWT的Payload\n     */\n    public static String parseJwtPayload(String jwt) {\n        Assert.hasText(jwt, \"JWT String argument cannot be null or empty.\");\n        String base64UrlEncodedHeader = null;\n        String base64UrlEncodedPayload = null;\n        String base64UrlEncodedDigest = null;\n        int delimiterCount = 0;\n        StringBuilder sb = new StringBuilder(128);\n        for (char c : jwt.toCharArray()) {\n            if (c == \'.\') {\n                CharSequence tokenSeq = io.jsonwebtoken.lang.Strings.clean(sb);\n                String token = tokenSeq != null ? tokenSeq.toString() : null;\n                if (delimiterCount == 0) {\n                    base64UrlEncodedHeader = token;\n                } else if (delimiterCount == 1) {\n                    base64UrlEncodedPayload = token;\n                }\n                delimiterCount++;\n                sb.setLength(0);\n            } else {\n                sb.append(c);\n            }\n        }\n        if (delimiterCount != COUNT_2) {\n            String msg = \"JWT strings must contain exactly 2 period characters. Found: \" + delimiterCount;\n            throw new MalformedJwtException(msg);\n        }\n        if (sb.length() > 0) {\n            base64UrlEncodedDigest = sb.toString();\n        }\n        if (base64UrlEncodedPayload == null) {\n            throw new MalformedJwtException(\"JWT string \'\" + jwt + \"\' is missing a body/payload.\");\n        }\n        // =============== Header =================\n        Header header = null;\n        CompressionCodec compressionCodec = null;\n        if (base64UrlEncodedHeader != null) {\n            String origValue = TextCodec.BASE64URL.decodeToString(base64UrlEncodedHeader);\n            Map<String, Object> m = readValue(origValue);\n            if (base64UrlEncodedDigest != null) {\n                header = new DefaultJwsHeader(m);\n            } else {\n                header = new DefaultHeader(m);\n            }\n            compressionCodec = codecResolver.resolveCompressionCodec(header);\n        }\n        // =============== Body =================\n        String payload;\n        if (compressionCodec != null) {\n            byte[] decompressed = compressionCodec.decompress(TextCodec.BASE64URL.decode(base64UrlEncodedPayload));\n            payload = new String(decompressed, io.jsonwebtoken.lang.Strings.UTF_8);\n        } else {\n            payload = TextCodec.BASE64URL.decodeToString(base64UrlEncodedPayload);\n        }\n        return payload;\n    }\n    /**\n     * 验签JWT\n     *\n     * @param jwt    json web token\n     * @param appKey key\n     * @return JwtAccount\n     * @throws ExpiredJwtException      异常\n     * @throws UnsupportedJwtException  异常\n     * @throws MalformedJwtException    异常\n     * @throws SignatureException       异常\n     * @throws IllegalArgumentException 异常\n     */\n    public static JwtAccount parseJwt(String jwt, String appKey) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {\n        Claims claims = Jwts.parser()\n                .setSigningKey(DatatypeConverter.parseBase64Binary(appKey))\n                .parseClaimsJws(jwt)\n                .getBody();\n        JwtAccount jwtAccount = new JwtAccount();\n        // 令牌ID\n        jwtAccount.setJti(claims.getId())\n                // 客户标识\n                .setSub(claims.getSubject())\n                // 签发者\n                .setIss(claims.getIssuer())\n                // 签发时间\n                .setIat(claims.getIssuedAt().getTime())\n                .setExp(claims.getExpiration().getTime())\n                // 密码\n                .setPassword(claims.get(\"password\", String.class));\n        return jwtAccount;\n    }\n    /**\n     * description 从json数据中读取格式化map\n     *\n     * @param val 1\n     * @return java.util.Map<java.lang.String, java.lang.Object>\n     */\n    @SuppressWarnings(\"unchecked\")\n    public static Map<String, Object> readValue(String val) {\n        try {\n            return MAPPER.readValue(val, Map.class);\n        } catch (IOException e) {\n            throw new MalformedJwtException(\"Unable to read JSON value: \" + val, e);\n        }\n    }\n    /**\n     * 分割字符串进SET\n     */\n    @SuppressWarnings(\"unchecked\")\n    public static Set<String> split(String str) {\n        Set<String> set = new HashSet<>();\n        if (StringUtils.isEmpty(str)) {\n            return set;\n        }\n        set.addAll(CollectionUtils.arrayToList(str.split(\",\")));\n        return set;\n    }\n}\n```\n到此项目差不多搞定\n项目源码：[源码](https://gitee.com/luoyuanxiang/vjsb)', NULL, 16, 0, '2025-04-27 16:17:51');
INSERT INTO `article` VALUES (22, 'Java代码优化35条实用技巧', '本文详细介绍了35条Java代码优化的实用技巧，涵盖减小代码体积、提高运行效率等多个方面，包括使用final修饰符、重用对象、局部变量、及时关闭流等具体优化方法，帮助开发者编写更高效的Java程序。', '代码优化的目标是：\n\n1、减小代码的体积\n\n2、提高代码运行的效率\n\n# 代码优化细节\n1、尽量指定类、方法的 `final` 修饰符\n带有 `final` 修饰符的类是不可派生的。在 `Java` 核心 `API` 中，有许多应用 `final` 的例子，例如 `java.lang.String` ，整个类都是 `final` 的。为类指定 `final` 修饰符可以让类不可以被继承，为方法指定 `final` 修饰符可以让方法不可以被重写。如果指定了一个类为 `final` ，则该类所有的方法都是 `final` 的。 `Java` 编译器会寻找机会内联所有的 `final` 方法，内联对于提升 `Java` 运行效率作用重大，具体参见 `Java` 运行期优化。此举能够使性能平均提高 50%。\n2、尽量重用对象\n特别是 `String` 对象的使用，出现字符串连接时应该使用 `StringBuilder/StringBuffer` 代替。由于 `Java` 虚拟机不仅要花时间生成对象，以后可能还需要花时间对这些对象进行垃圾回收和处理，因此，生成过多的对象将会给程序的性能带来很大的影响。\n3、尽可能使用局部变量\n调用方法时传递的参数以及在调用中创建的临时变量都保存在栈中速度较快，其他变量，如静态变量、实例变量等，都在堆中创建，速度较慢。另外，栈中创建的变量，随着方法的运行结束，这些内容就没了，不需要额外的垃圾回收。\n4、及时关闭流\n`Java` 编程过程中，进行数据库连接、I/O 流操作时务必小心，在使用完毕后，及时关闭以释放资源。因为对这些大对象的操作会造成系统大的开销，稍有不慎，将会导致严重的后果。\n5、尽量减少对变量的重复计算\n明确一个概念，对方法的调用，即使方法中只有一句语句，也是有消耗的，包括创建栈帧、调用方法时保护现场、调用方法完毕时恢复现场等。所以例如下面的操作：\n```java\nfor (int i = 0; i < list.size(); i++) {\n    ...\n}\n```\n建议替换为：\n```java\nfor (int i = 0, int length = list.size(); i < length; i++) {\n    ...\n}\n```\n这样，在 `list.size()` 很大的时候，就减少了很多的消耗\n6、尽量采用懒加载的策略，即在需要的时候才创建\n例如：\n```java\nString str = \"aaa\";\nif (i == 1) {\n    list.add(str);\n}\n```\n建议替换为：\n```java\nif (i == 1) {\n    String str = \"aaa\";\n    list.add(str);\n}\n```\n7、慎用异常\n异常对性能不利。抛出异常首先要创建一个新的对象， `Throwable` 接口的构造函数调用名为 `fillInStackTrace()` 的本地同步方法， `fillInStackTrace()` 方法检查堆栈，收集调用跟踪信息。只要有异常被抛出，`Java` 虚拟机就必须调整调用堆栈，因为在处理过程中创建了一个新的对象。异常只能用于错误处理，不应该用来控制程序流程。\n8、不要在循环中使用 `try…catch…` ，应该把其放在最外层\n除非不得已。如果毫无理由地这么写了，只要你的领导资深一点、有强迫症一点，八成就要骂你为什么写出这种垃圾代码来了\n9、如果能估计到待添加的内容长度，为底层以数组方式实现的集合、工具类指定初始长度\n比如 `ArrayList`、`LinkedLlist`、`StringBuilder`、`StringBuffer`、`HashMap`、`HashSet` 等等，以 `StringBuilder` 为例：\n（1） `StringBuilder()` 　　　　　　// 默认分配 16 个字符的空间\n（2） `StringBuilder(int size)` 　　// 默认分配 size 个字符的空间\n（3） `StringBuilder(String str)` 　// 默认分配 16 个字符 + `str.length()` 个字符空间\n可以通过类（这里指的不仅仅是上面的 `StringBuilder` ）的来设定它的初始化容量，这样可以明显地提升性能。比如 `StringBuilder` 吧， `length` 表示当前的 `StringBuilder` 能保持的字符数量。因为当 `StringBuilder` 达到最大容量的时候，它会将自身容量增加到当前的 2 倍再加 2，无论何时只要 StringBuilder 达到它的最大容量，它就不得不创建一个新的字符数组然后将旧的字符数组内容拷贝到新字符数组中 —- 这是十分耗费性能的一个操作。试想，如果能预估到字符数组中大概要存放 5000 个字符而不指定长度，最接近 5000 的 2 次幂是 4096，每次扩容加的 2 不管，那么：\n（1）在 4096 的基础上，再申请 8194 个大小的字符数组，加起来相当于一次申请了 12290 个大小的字符数组，如果一开始能指定 5000 个大小的字符数组，就节省了一倍以上的空间\n（2）把原来的 4096 个字符拷贝到新的的字符数组中去\n这样，既浪费内存空间又降低代码运行效率。所以，给底层以数组实现的集合、工具类设置一个合理的初始化容量是错不了的，这会带来立竿见影的效果。但是，注意，像 HashMap 这种是以数组 + 链表实现的集合，别把初始大小和你估计的大小设置得一样，因为一个 table 上只连接一个对象的可能性几乎为 0。初始大小建议设置为 2 的 N 次幂，如果能估计到有 2000 个元素，设置成 new HashMap(128)、new HashMap(256) 都可以。\n10、当复制大量数据时，使用 `System.arraycopy()` 命令\n11、乘法和除法使用移位操作\n例如：\n```java\nfor (val = 0; val < 100000; val += 5) {\n    a = val * 8;\n    b = val / 2;\n}\n```\n用移位操作可以极大地提高性能，因为在计算机底层，对位的操作是最方便、最快的，因此建议修改为：\n```java\nfor (val = 0; val < 100000; val += 5) {\n    a = val << 3;\n    b = val >> 1;\n}\n```\n移位操作虽然快，但是可能会使代码不太好理解，因此最好加上相应的注释。\n12、循环内不要不断创建对象引用\n例如：\n```java\nfor (int i = 1; i <= count; i++) {\n    Object obj = new Object();\n}\n```\n这种做法会导致内存中有 `count` 份 `Object` 对象引用存在，`count` 很大的话，就耗费内存了，建议为改为：\n```java\nObject obj = null;\nfor (int i = 0; i <= count; i++) { \n    obj = new Object(); \n}\n```\n13、基于效率和类型检查的考虑，应该尽可能使用 `array` ，无法确定数组大小时才使用 `ArrayList`\n14、尽量使用 `HashMap`、`ArrayList`、`StringBuilder` ，除非线程安全需要，否则不推荐使用 `Hashtable`、`Vector`、`StringBuffer` ，后三者由于使用同步机制而导致了性能开销\n15、不要将数组声明为 `public static final`\n因为这毫无意义，这样只是定义了引用为 `static final` ，数组的内容还是可以随意改变的，将数组声明为 `public` 更是一个安全漏洞，这意味着这个数组可以被外部类所改变\n16、尽量在合适的场合使用单例\n使用单例可以减轻加载的负担、缩短加载的时间、提高加载的效率，但并不是所有地方都适用于单例，简单来说，单例主要适用于以下三个方面：\n（1）控制资源的使用，通过线程同步来控制资源的并发访问\n（2）控制实例的产生，以达到节约资源的目的\n（3）控制数据的共享，在不建立直接关联的条件下，让多个不相关的进程或线程之间实现通信\n17、尽量避免随意使用静态变量\n要知道，当某个对象被定义为 `static` 的变量所引用，那么 gc 通常是不会回收这个对象所占有的堆内存的，如：\n```java\npublic class A {\n    private static B b = new B();\n}\n```\n此时静态变量 b 的生命周期与 A 类相同，如果 A 类不被卸载，那么引用 B 指向的 B 对象会常驻内存，直到程序终止\n18、及时清除不再需要的会话\n为了清除不再活动的会话，许多应用服务器都有默认的会话超时时间，一般为 30 分钟。当应用服务器需要保存更多的会话时，如果内存不足，那么操作系统会把部分数据转移到磁盘，应用服务器也可能根据 MRU（最近最频繁使用）算法把部分不活跃的会话转储到磁盘，甚至可能抛出内存不足的异常。如果会话要被转储到磁盘，那么必须要先被序列化，在大规模集群中，对对象进行序列化的代价是很昂贵的。因此，当会话不再需要时，应当及时调用 HttpSession 的 invalidate() 方法清除会话。\n19、实现 `RandomAccess` 接口的集合比如 `ArrayList` ，应当使用最普通的 `for` 循环而不是 `foreach` 循环来遍历\n这是 `JDK` 推荐给用户的。`JDK API` 对于 `RandomAccess` 接口的解释是：实现 `RandomAccess` 接口用来表明其支持快速随机访问，此接口的主要目的是允许一般的算法更改其行为，从而将其应用到随机或连续访问列表时能提供良好的性能。实际经验表明，实现 `RandomAccess` 接口的类实例，假如是随机访问的，使用普通 `for` 循环效率将高于使用 `foreach` 循环；反过来，如果是顺序访问的，则使用 `Iterator` 会效率更高。可以使用类似如下的代码作判断：\n```java\nif (list instanceof RandomAccess) { \n    for (int i = 0; i < list.size(); i++){}\n} else {\n    Iterator<?> iterator = list.iterable(); \n    while (iterator.hasNext()) {\n        iterator.next()\n    }\n}\n```\n`foreach` 循环的底层实现原理就是迭代器 `Iterator` ，参见 `Java` 语法糖 1：可变长度参数以及 `foreach` 循环原理。所以后半句” 反过来，如果是顺序访问的，则使用 `Iterator` 会效率更高” 的意思就是顺序访问的那些类实例，使用 `foreach` 循环去遍历。\n20、使用同步代码块替代同步方法\n这点在多线程模块中的 `synchronized` 锁方法块一文中已经讲得很清楚了，除非能确定一整个方法都是需要进行同步的，否则尽量使用同步代码块，避免对那些不需要进行同步的代码也进行了同步，影响了代码执行效率。\n21、将常量声明为 `static final` ，并以大写命名\n这样在编译期间就可以把这些内容放入常量池中，避免运行期间计算生成常量的值。另外，将常量的名字以大写命名也可以方便区分出常量与变量\n22、不要创建一些不使用的对象，不要导入一些不使用的类\n这毫无意义，如果代码中出现”The value of the local variable i is not used”、”The import java.util is never used”，那么请删除这些无用的内容\n23、程序运行过程中避免使用反射\n关于，请参见反射。反射是 `Java` 提供给用户一个很强大的功能，功能强大往往意味着效率不高。不建议在程序运行过程中使用尤其是频繁使用反射机制，特别是 `Method` 的 `invoke` 方法，如果确实有必要，一种建议性的做法是将那些需要通过反射加载的类在项目启动的时候通过反射实例化出一个对象并放入内存 —- 用户只关心和对端交互的时候获取最快的响应速度，并不关心对端的项目启动花多久时间。\n24、使用数据库连接池和线程池\n这两个池都是用于重用对象的，前者可以避免频繁地打开和关闭连接，后者可以避免频繁地创建和销毁线程\n25、使用带缓冲的输入输出流进行 IO 操作\n带缓冲的输入输出流，即 `BufferedReader`、`BufferedWriter`、`BufferedInputStream`、`BufferedOutputStream` ，这可以极大地提升 IO 效率\n26、顺序插入和随机访问比较多的场景使用 `ArrayList` ，元素删除和中间插入比较多的场景使用 `LinkedList`\n参考：https://www.cnblogs.com/wwwcnblogscom/p/8036411.html\n27、不要让 `public` 方法中有太多的形参\n`public` 方法即对外提供的方法，如果给这些方法太多形参的话主要有两点坏处：\n1、违反了面向对象的编程思想，`Java` 讲求一切都是对象，太多的形参，和面向对象的编程思想并不契合\n2、参数太多势必导致方法调用的出错概率增加\n至于这个” 太多” 指的是多少个，3、4 个吧。比如我们用 JDBC 写一个 `insertStudentInfo` 方法，有 10 个学生信息字段要插如 `Student` 表中，可以把这 10 个参数封装在一个实体类中，作为 `insert` 方法的形参\n28、字符串变量和字符串常量 `equals` 的时候将字符串常量写在前面\n这是一个比较常见的小技巧了，如果有以下代码：\n```java\nString str = \"123\";\nif (str.equals(\"123\")) {\n...\n}\n```\n建议修改为：\n```java\nString str = \"123\";\nif (\"123\".equals(str))\n{\n...\n}\n```\n这么做主要是可以避免空指针异常\n29、请知道，在 `java` 中 `if (i == 1)` 和 `if (1 == i)` 是没有区别的，但从阅读习惯上讲，建议使用前者\n平时有人问，`if (i == 1)` 和 `if (1== i)` 有没有区别，这就要从 `C/C++` 讲起。\n在 `C/C++` 中，`if (i == 1)` 判断条件成立，是以 0 与非 0 为基准的，0 表示 `false`，非 0 表示 `true`，如果有这么一段代码：\n```java\nint i = 2;\nif (i == 1) {\n...\n} else {\n...\n}\n```\n`C/C++` 判断 `i==1` 不成立，所以以 0 表示，即 `false`。但是如果：\n```java\nint i = 2;\nif (i = 1) {\n    ... \n} else { \n    ... \n}\n```\n万一程序员一个不小心，把 `if (i == 1)` 写成`if (i = 1)`，这样就有问题了。在 `if` 之内将 `i` 赋值为 1，`if` 判断里面的内容非 0，返回的就是 `true` 了，但是明明 i 为 2，比较的值是 1，应该返回的 `false`。这种情况在 `C/C++` 的开发中是很可能发生的并且会导致一些难以理解的错误产生，所以，为了避免开发者在 `if` 语句中不正确的赋值操作，建议将 `if` 语句写为：\n```java\nint i = 2;\nif (1 == i) { \n    ...\n} else { \n    ... \n}\n```\n这样，即使开发者不小心写成了`1 = i`，`C/C++` 编译器也可以第一时间检查出来，因为我们可以对一个变量赋值 `i` 为 1，但是不能对一个常量赋值 1 为 `i`。\n但是，在 `Java` 中，`C/C++` 这种`if (i = 1)` 的语法是不可能出现的，因为一旦写了这种语法，`Java` 就会编译报错`Type mismatch: cannot convert from int to boolean`。但是，尽管 `Java` 的`if (i == 1)` 和`if (1 == i)` 在语义上没有任何区别，但是从阅读习惯上讲，建议使用前者会更好些。\n30、不要对数组使用 `toString()` 方法\n看一下对数组使用 `toString()` 打印出来的是什么：\n```java\npublic static void main(String[] args) { \n    int[] is = new int[]{1, 2, 3};\n    System.out.println(is.toString());\n}\n```\n结果是：\n`[I@18a992f`\n本意是想打印出数组内容，却有可能因为数组引用 `is` 为空而导致空指针异常。不过虽然对数组 `toString()` 没有意义，但是对集合 `toString()` 是可以打印出集合里面的内容的，因为集合的父类 `AbstractCollections<E>` 重写了 `Object` 的 `toString()` 方法。\n31、不要对超出范围的基本数据类型做向下强制转型\n这绝不会得到想要的结果：\n```java\npublic static void main(String[] args) {\n    long l = 12345678901234L;\n    int i = (int)l;\n    System.out.println(i);\n}\n```\n我们可能期望得到其中的某几位，但是结果却是：\n1942892530\n解释一下。`Java` 中 `long` 是 8 个字节 64 位的，所以 12345678901234 在计算机中的表示应该是：\n0000 0000 0000 0000 0000 1011 0011 1010 0111 0011 1100 1110 0010 1111 1111 0010\n一个 `int` 型数据是 4 个字节 32 位的，从低位取出上面这串二进制数据的前 32 位是：\n0111 0011 1100 1110 0010 1111 1111 0010\n这串二进制表示为十进制 1942892530，所以就是我们上面的控制台上输出的内容。从这个例子上还能顺便得到两个结论：\n1、整型默认的数据类型是 `int`，`long l = 12345678901234L` ，这个数字已经超出了 `int` 的范围了，所以最后有一个 L，表示这是一个 `long` 型数。顺便，浮点型的默认类型是 `double` ，所以定义 `float` 的时候要写成 `float f = 3.5f`\n2、接下来再写一句 `int ii = l + i;` 会报错，因为 `long + int` 是一个 `long` ，不能赋值给 `int`\n32、公用的集合类中不使用的数据一定要及时 `remove` 掉\n如果一个集合类是公用的（也就是说不是方法里面的属性），那么这个集合里面的元素是不会自动释放的，因为始终有引用指向它们。所以，如果公用集合里面的某些数据不使用而不去 remove 掉它们，那么将会造成这个公用集合不断增大，使得系统有内存泄露的隐患。\n33、把一个基本数据类型转为字符串，`基本数据类型.toString ()` 是最快的方式、`String.valueOf(数据)` 次之、数据 +”\" 最慢\n把一个基本数据类型转为一般有三种方式，我有一个 `Integer` 型数据 `i`，可以使用 `i.toString()`、`String.valueOf(i)`、`i+\"\"` 三种方式，三种方式的效率如何，看一个测试：\n```java\npublic static void main(String[] args) {\n    int loopTime = 50000;\n    Integer i = 0; \n    long startTime = System.currentTimeMillis(); \n    for (int j = 0; j < loopTime; j++) {\n        String str = String.valueOf(i);\n    }\n    System.out.println(\"String.valueOf()：\" + (System.currentTimeMillis() - startTime) + \"ms\");\n    startTime = System.currentTimeMillis(); \n    for (int j = 0; j < loopTime; j++) {\n        String str = i.toString();\n    }\n    System.out.println(\"Integer.toString()：\" + (System.currentTimeMillis() - startTime) + \"ms\");\n    startTime = System.currentTimeMillis(); \n    for (int j = 0; j < loopTime; j++) {\n        String str = i + \"\";\n    }\nSystem.out.println(\"i + \\\"\\\"：\" + (System.currentTimeMillis() - startTime) + \"ms\");\n}\n```\n运行结果为：\n`String.valueOf()：11ms Integer.toString()：5ms i + \"\"：25ms`\n所以以后遇到把一个基本数据类型转为 `String` 的时候，优先考虑使用 `toString()` 方法。至于为什么，很简单：\n1、 `String.valueOf()` 方法底层调用了 `Integer.toString()` 方法，但是会在调用前做空判断\n2、 `Integer.toString()` 方法就不说了，直接调用了\n3、`i + “”` 底层使用了 `StringBuilder` 实现，先用 `append` 方法拼接，再用 `toString()` 方法获取字符串\n三者对比下来，明显是 2 最快、1 次之、3 最慢\n34、使用最有效率的方式去遍历 `Map`\n遍历 `Map` 的方式有很多，通常场景下我们需要的是遍历 `Map` 中的 `Key` 和 `Value`，那么推荐使用的、效率最高的方式是：\n```java\npublic static void main(String[] args) {\n    HashMap<String, String> hm = new HashMap<String, String>();\n    hm.put(\"111\", \"222\");\n    Set<Map.Entry<String, String>> entrySet = hm.entrySet();\n    Iterator<Map.Entry<String, String>> iter = entrySet.iterator();\n    while (iter.hasNext()) {\n        Map.Entry<String, String> entry = iter.next();\n        System.out.println(entry.getKey() + \"\\t\" + entry.getValue());\n    }\n}\n```\n如果你只是想遍历一下这个 `Map` 的 `key` 值，那用 `Set<String> keySet = hm.keySet();` 会比较合适一些\n35、对资源的 `close()` 建议分开操作\n意思是，比如我有这么一段代码：\n```java\ntry {\n    XXX.close();\n    YYY.close();\n} catch (Exception e) {\n    ...\n}\n```\n建议修改为：\n```java\ntry { \n    XXX.close(); \n} catch (Exception e) { \n    ...\n}\ntry { \n    YYY.close(); \n} catch (Exception e) { \n    ... \n}\n```\n虽然有些麻烦，却能避免资源泄露。我们想，如果没有修改过的代码，万一 `XXX.close()` 抛异常了，那么就进入了 `cath` 块中了， `YYY.close()` 不会执行，`YYY` 这块资源就不会回收了，一直占用着，这样的代码一多，是可能引起资源句柄泄露的。而改为下面的写法之后，就保证了无论如何 `XXX` 和 `YYY` 都会被 `close` 掉。\n> 只有把抱怨环境的心情，化为上进的力量，才是成功的保证。—— 罗曼・罗兰', NULL, 17, 0, '2025-04-27 16:18:11');
INSERT INTO `article` VALUES (23, '使用Docker Compose部署项目', '本文介绍如何使用Docker Compose部署包含API服务、管理端和Web端的项目，涵盖Dockerfile构建及服务配置。', '# 使用 Docker Compose 部署项目\n\n## API 服务端 Dockerfile\n\n```dockerfile\nFROM maven:3.8.6-eclipse-temurin-8-alpine AS builder\nWORKDIR /app\nCOPY pom.xml .\nCOPY model/pom.xml model/pom.xml\nCOPY blog/pom.xml blog/pom.xml\n\nRUN mvn dependency:go-offline -B\nCOPY . .\nRUN mvn clean package -DskipTests -P pro\n\nFROM eclipse-temurin:8-jre-alpine\nWORKDIR /app\nCOPY --from=builder /app/blog/target/*.jar app.jar\nCMD [\"java\", \"-Xms256m\", \"-Xmx512m\", \"-jar\", \"app.jar\"]\n```\n\n## 管理端 Dockerfile\n\n```dockerfile\nFROM registry.cn-hangzhou.aliyuncs.com/liuyi778/node-20-alpine AS builder\nWORKDIR /thrive\nCOPY package*.json ./\nRUN npm config set registry https://registry.npmmirror.com\nRUN npm install\nCOPY . .\n\nARG VITE_PROJECT_API\nARG VITE_VERSION\nARG VITE_BAIDU_TONGJI_SITE_ID\nARG VITE_BAIDU_TONGJI_SITE_NAME \nARG VITE_AI_APIPASSWORD\nARG VITE_AI_MODEL\nARG VITE_GAODE_WEB_API\n\nENV VITE_PROJECT_API=$VITE_PROJECT_API \\\n    VITE_VERSION=$VITE_VERSION \\\n    VITE_BAIDU_TONGJI_SITE_ID=$VITE_BAIDU_TONGJI_SITE_ID \\\n    VITE_BAIDU_TONGJI_SITE_NAME=$VITE_BAIDU_TONGJI_SITE_NAME \\\n    VITE_AI_APIPASSWORD=$VITE_AI_APIPASSWORD \\\n    VITE_AI_MODEL=$VITE_AI_MODEL \\\n    VITE_GAODE_WEB_API=$VITE_GAODE_WEB_API\n\nRUN npm run build\n\nFROM nginx:alpine\nCOPY --from=builder /thrive/dist /usr/share/nginx/html\nEXPOSE 80\nCMD [\"nginx\", \"-g\", \"daemon off;\"]\n```\n\n## Web 端 Dockerfile\n\n```dockerfile\nFROM node:20-alpine\nWORKDIR /thrive\nRUN npm config set registry https://registry.npmmirror.com\nCOPY package*.json .\nRUN npm install\nCOPY . .\n\nARG NEXT_PUBLIC_PROJECT_API\nARG NEXT_PUBLIC_CACHING_TIME\nARG NEXT_PUBLIC_GAODE_KEY_CODE\nARG NEXT_PUBLIC_GAODE_SECURITYJS_CODE\n\nENV NEXT_PUBLIC_PROJECT_API=$NEXT_PUBLIC_PROJECT_API \\\n    NEXT_PUBLIC_CACHING_TIME=$NEXT_PUBLIC_CACHING_TIME \\\n    NEXT_PUBLIC_GAODE_KEY_CODE=$NEXT_PUBLIC_GAODE_KEY_CODE \\\n    NEXT_PUBLIC_GAODE_SECURITYJS_CODE=$NEXT_PUBLIC_GAODE_SECURITYJS_CODE\n\nRUN npm run build\nEXPOSE 9001\nCMD [\"npm\", \"start\"]\n```\n\n## Docker Compose 配置\n\n```yaml\nversion: \'3.7\'\n\nservices:\n  mysql:\n    image: mysql\n    container_name: db\n    environment:\n      MYSQL_ROOT_PASSWORD: 123456\n      TZ: Asia/Shanghai\n      MYSQL_DATABASE: thrivex_blog\n    volumes:\n      - ./mysql:/var/lib/mysql\n      - ./ThriveX-Server/ThriveX.sql:/docker-entrypoint-initdb.d/init.sql\n    command:\n      --max_connections=1000\n      --character-set-server=utf8mb4\n      --collation-server=utf8mb4_bin\n    ports:\n      - \"3309:3306\"\n    networks:\n      - thrivex_network\n\n  server:\n    build:\n      context: ./ThriveX-Server\n      dockerfile: Dockerfile-maven\n    environment:\n      DB_DATABASE: thrivex_blog\n      DB_HOST: db\n      DB_USERNAME: root\n      DB_PASSWORD: 123456\n      DB_PORT: 3306\n    volumes:\n      - ./upload:/upload\n    ports:\n      - \"9999:9999\"\n    healthcheck:\n      test: [\"CMD\", \"curl\", \"-f\", \"https://localhost:9999\"]\n      interval: 10s\n      timeout: 5s\n      retries: 3\n    depends_on:\n      - mysql\n\n  client:\n    build:\n      context: ./ThriveX-Blog\n      args:\n        NEXT_PUBLIC_PROJECT_API: https://d.luoyuanxiang.top:9999/api\n        NEXT_PUBLIC_CACHING_TIME: 0\n    ports:\n      - \"9001:9001\"\n    depends_on:\n      server:\n        condition: service_healthy\n\n  dashboard:\n    build:\n      context: ./ThriveX-Admin\n      args:\n        VITE_PROJECT_API: https://d.luoyuanxiang.top:9999/api\n        VITE_VERSION: 2.4.5\n    ports:\n      - \"9000:80\"\n    depends_on:\n      server:\n        condition: service_healthy\n\nnetworks:\n  thrivex_network:\n    driver: bridge\n```', NULL, 33, 0, '2025-04-28 09:41:38');
INSERT INTO `article` VALUES (24, 'GitHub Actions 自动化部署 Spring Boot 到阿里云与服务器', '通过 GitHub Actions 构建并推送 Spring Boot 项目到阿里云镜像仓库，并自动部署到远程服务器。', '# GitHub 通过 actions 打包 Sprong Boot 项目到阿里云镜像仓库，并且部署项目到服务器上\n\n## 在项目根目录下新建 `.github/workflows/docker-deploy.yml`，内容如下：\n\n```yml\nname: Build and Deploy Spring Boot Docker\n\non:\n  push:\n    branches: [ \"master\" ] # 触发分支\n\njobs:\n  build-and-deploy:\n    runs-on: ubuntu-latest\n\n    steps:\n      - name: Checkout code\n        uses: actions/checkout@v4\n\n      - name: Set up JDK 1.8\n        uses: actions/setup-java@v3\n        with:\n          java-version: \'8\'\n          distribution: \'temurin\'\n\n      - name: Build with Maven\n        run: mvn clean package -DskipTests -Ppro\n\n      - name: Log in to ACR\n        uses: docker/login-action@v2\n        with:\n          registry: registry.cn-chengdu.aliyuncs.com # 替换为你的区域地址\n          username: ${{ secrets.ACR_USERNAME }}\n          password: ${{ secrets.ACR_PASSWORD }}\n\n      - name: Build and push Docker image\n        run: |\n          docker build -t registry.cn-chengdu.aliyuncs.com/lyx_blog/server:latest .\n\n      - name: Push Docker image\n        run: |\n          docker push registry.cn-chengdu.aliyuncs.com/lyx_blog/server:latest\n\n      # 添加部署到服务器的步骤（示例使用 SSH）\n      - name: Deploy to Server\n        uses: appleboy/ssh-action@v1\n        with:\n          host: ${{ secrets.SERVER_IP }}\n          username: ${{ secrets.SERVER_USER }}\n          key: ${{ secrets.SERVER_SSH_KEY }}\n          script: |\n            cd /root/server\n            docker-compose -f docker-compose.yml down --rmi all || true\n            docker-compose -f docker-compose.yml up -d || true\n```\n\n### 变量解释\n* `ACR_USERNAME`: 阿里云镜像用户名\n* `ACR_PASSWORD`: 阿里云镜像密码\n* `SERVER_IP`: 服务器IP地址\n* `SERVER_USER`: 服务器用户名\n* `SERVER_SSH_KEY`: 服务器SSH key\n\n也可以使用服务器的密码命令\n`password`: xxx\n参考：https://github.com/appleboy/ssh-action\n\n注意：阿里云的镜像地址需要替换为自己的阿里云地址', NULL, 13, 0, '2025-05-06 12:53:38');
INSERT INTO `article` VALUES (25, 'GitHub Actions 实现 Spring Boot 项目自动化部署', '通过 GitHub Actions 将 Spring Boot 项目打包并自动部署到服务器，提升开发效率。', '# GitHub 通过 actions 打包 Sprong Boot 项目到服务器上\n\n## 在项目根目录下新建 `.github/workflows/ci.yml`，内容如下：\n\n```yml\nname: 项目远程部署\n\non:\n  push:\n    branches:\n      - main\n\njobs:\n  build:\n    runs-on: ubuntu-latest\n\n    steps:\n      - name: Checkout code\n        uses: actions/checkout@v4\n\n      - name: Set up JDK 17\n        uses: actions/setup-java@v4\n        with:\n          distribution: \'temurin\'\n          java-version: \'17\'\n      - name: Build with Maven\n        run: mvn clean install -Dmaven.test.skip=true\n\n      - name: Deploy\n        uses: cross-the-world/ssh-scp-ssh-pipelines@latest\n        with:\n          host: ${{ secrets.HOST }}\n          user: ${{ secrets.USERNAME }}\n          pass: ${{ secrets.PASSWORD }}\n          port: 22\n          scp: |\n            ./target/helixia-admin.jar => /root/\n          last_ssh: |\n              cd /root/ && sh start.sh restart\n```\n\n### 变量解释\n\n* `HOST`: 服务器IP地址\n* `USERNAME`: 服务器用户名\n* `PASSWORD`: 服务器SSH key\n\n\n参考：https://github.com/cross-the-world/ssh-scp-ssh-pipelines', NULL, 19, 0, '2025-05-06 13:00:20');

-- ----------------------------
-- Table structure for article_cate
-- ----------------------------
DROP TABLE IF EXISTS `article_cate`;
CREATE TABLE `article_cate`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL COMMENT '文章ID',
  `cate_id` int NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `article_cate_pk_2`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1513 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章和分类的中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_cate
-- ----------------------------
INSERT INTO `article_cate` VALUES (1444, 2, 1);
INSERT INTO `article_cate` VALUES (1497, 20, 1);
INSERT INTO `article_cate` VALUES (1498, 22, 1);
INSERT INTO `article_cate` VALUES (1499, 14, 1);
INSERT INTO `article_cate` VALUES (1501, 16, 1);
INSERT INTO `article_cate` VALUES (1502, 17, 1);
INSERT INTO `article_cate` VALUES (1503, 18, 1);
INSERT INTO `article_cate` VALUES (1504, 19, 1);
INSERT INTO `article_cate` VALUES (1506, 5, 1);
INSERT INTO `article_cate` VALUES (1508, 23, 80);
INSERT INTO `article_cate` VALUES (1511, 24, 80);
INSERT INTO `article_cate` VALUES (1512, 25, 80);

-- ----------------------------
-- Table structure for article_config
-- ----------------------------
DROP TABLE IF EXISTS `article_config`;
CREATE TABLE `article_config`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` enum('default','no_home','hide') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'default' COMMENT '文章状态',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '是否文章加密',
  `is_encrypt` tinyint NULL DEFAULT 0 COMMENT '是否加密',
  `is_draft` tinyint NULL DEFAULT 0 COMMENT '是否为草稿',
  `is_del` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  `article_id` int NOT NULL COMMENT '对应的文章id',
  `comment` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否开启评论',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `article_config_pk_2`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_config
-- ----------------------------
INSERT INTO `article_config` VALUES (2, 'default', '', 0, 0, 0, 2, 1);
INSERT INTO `article_config` VALUES (49, 'default', '', 0, 0, 0, 20, 1);
INSERT INTO `article_config` VALUES (50, 'default', '', 0, 0, 0, 22, 1);
INSERT INTO `article_config` VALUES (51, 'default', '', 0, 0, 0, 14, 1);
INSERT INTO `article_config` VALUES (53, 'default', '', 0, 0, 0, 16, 1);
INSERT INTO `article_config` VALUES (54, 'default', '', 0, 0, 0, 17, 1);
INSERT INTO `article_config` VALUES (55, 'default', '', 0, 0, 0, 18, 1);
INSERT INTO `article_config` VALUES (56, 'default', '', 0, 0, 0, 19, 1);
INSERT INTO `article_config` VALUES (58, 'default', '', 0, 0, 0, 5, 0);
INSERT INTO `article_config` VALUES (60, 'default', '', 0, 0, 0, 23, 1);
INSERT INTO `article_config` VALUES (63, 'default', '', 0, 0, 0, 24, 1);
INSERT INTO `article_config` VALUES (64, 'default', '', 0, 0, 0, 25, 1);

-- ----------------------------
-- Table structure for article_tag
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL COMMENT '文章 ID',
  `tag_id` int NOT NULL COMMENT '标签 ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `article_tag_pk_2`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_tag
-- ----------------------------
INSERT INTO `article_tag` VALUES (2, 2, 3);
INSERT INTO `article_tag` VALUES (48, 22, 89);
INSERT INTO `article_tag` VALUES (49, 14, 90);
INSERT INTO `article_tag` VALUES (53, 16, 89);
INSERT INTO `article_tag` VALUES (54, 16, 93);
INSERT INTO `article_tag` VALUES (55, 16, 94);
INSERT INTO `article_tag` VALUES (56, 17, 92);
INSERT INTO `article_tag` VALUES (57, 18, 89);
INSERT INTO `article_tag` VALUES (58, 19, 90);
INSERT INTO `article_tag` VALUES (59, 19, 91);
INSERT INTO `article_tag` VALUES (66, 23, 90);
INSERT INTO `article_tag` VALUES (67, 23, 91);
INSERT INTO `article_tag` VALUES (68, 23, 95);
INSERT INTO `article_tag` VALUES (71, 24, 96);
INSERT INTO `article_tag` VALUES (72, 25, 96);

-- ----------------------------
-- Table structure for assistant
-- ----------------------------
DROP TABLE IF EXISTS `assistant`;
CREATE TABLE `assistant`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '模型名称',
  `base_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '模型API接口',
  `api_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'API 密钥',
  `model_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '模型',
  `is_default` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否默认',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '助手管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of assistant
-- ----------------------------
INSERT INTO `assistant` VALUES (1, 'DeepSeek', 'https://api.deepseek.com', 'sk-08a8556a02f440209974b937d7205957', 'deepseek-chat', 0);
INSERT INTO `assistant` VALUES (4, '硅基流动', 'https://api.siliconflow.cn', 'sk-xznetqbrjdllhtadkeimghjzutypkxjvenbzmxtyvhbbkwal', 'Qwen/Qwen3-30B-A3B', 0);
INSERT INTO `assistant` VALUES (5, '阿里qwen', 'https://dashscope.aliyuncs.com/compatible-mode', 'sk-9286c85b5c6b42f38e716e744b720183', 'qwen-turbo', 1);

-- ----------------------------
-- Table structure for baidu_statistics
-- ----------------------------
DROP TABLE IF EXISTS `baidu_statistics`;
CREATE TABLE `baidu_statistics`  (
  `refresh_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '刷新token',
  `access_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'access_token',
  `site_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'site_id',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `app_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'app key',
  `secret_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密钥',
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '百度统计' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of baidu_statistics
-- ----------------------------
INSERT INTO `baidu_statistics` VALUES ('122.f341b434f431e421a28cbf22fa384feb.YGziDCRlkZfpROvjnWWqF_8Q0exK7l7y_ZdBbKw.X5oZJA', '121.7c055bc1c98d0059c14e34aa61a94742.YsqoH4UBwqAAIe4sgsecueDsEOQpvo57mNV2Hwe._g1aTA', '19511341', '2025-05-09 04:37:39', 'x0xWm2jLXI3pEBCIt1jKoB0HwBuzhOx1', 'f6IR3LLZBusfXn8lLbVa5FeztXAzkQcf', 1);

-- ----------------------------
-- Table structure for cate
-- ----------------------------
DROP TABLE IF EXISTS `cate`;
CREATE TABLE `cate`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类图标',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '/' COMMENT '分类链接',
  `mark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类标识',
  `level` int NULL DEFAULT NULL COMMENT '分类级别',
  `order` int NOT NULL DEFAULT 0 COMMENT '分类顺序',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'cate' COMMENT '导航还是分类',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name` ASC) USING BTREE,
  UNIQUE INDEX `cate_pk`(`mark` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cate
-- ----------------------------
INSERT INTO `cate` VALUES (1, '开发笔记', '💻', '/', 'kfbj', 0, 1, 'cate');
INSERT INTO `cate` VALUES (67, '首页', '💎', '/', 'home', 0, 0, 'nav');
INSERT INTO `cate` VALUES (68, '足迹', '⛳️', '/footprint', 'zj', 0, 9, 'nav');
INSERT INTO `cate` VALUES (70, '朋友圈', '😇', '/friend', ' pyq', 0, 10, 'nav');
INSERT INTO `cate` VALUES (71, '留言墙', '💌', '/wall/all', 'wall', 0, 11, 'nav');
INSERT INTO `cate` VALUES (72, 'GitHub', '🔥', 'https://github.com/LiuYuYang01/ThriveX-Blog', 'github', 0, 999, 'nav');
INSERT INTO `cate` VALUES (73, '统计', '📊', '/data', 'data', 0, 8, 'nav');
INSERT INTO `cate` VALUES (74, '闪念', '🏕️', '/record', 'record', 0, 9, 'nav');
INSERT INTO `cate` VALUES (78, '标签页', '🏷️', '/tags', 'bqy', 0, 12, 'nav');
INSERT INTO `cate` VALUES (80, '随笔一记', '✍️', '/', 'sbyj', 0, 2, 'cate');
INSERT INTO `cate` VALUES (81, '照片墙', '🖼️', '/album', 'xc', 0, 11, 'nav');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论者名称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论者头像',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论者邮箱',
  `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论者网站',
  `article_id` int NOT NULL COMMENT '所属文章ID',
  `comment_id` int NULL DEFAULT 0 COMMENT '二级评论',
  `audit_status` int NULL DEFAULT 0 COMMENT '是否审核',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 518 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (516, 'oneLN', '', '啊哈，多端部署，难度还是蛮大的', '', '', 23, 0, 1, '2025-04-29 15:47:21');
INSERT INTO `comment` VALUES (517, '罗远祥', 'https://luoyuanxiang.top/logo.png', '哈哈哈，还好吧', '1141306760@qq.com', 'https://luoyuanxiang.top', 23, 516, 1, '2025-04-30 21:08:03');

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配置名称',
  `value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配置值',
  `group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配置分组',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES ('covers', '[\"https://bu.dusays.com/2023/11/10/654e2da1d80f8.jpg\",\"https://bu.dusays.com/2023/11/10/654e2d719d31c.jpg\",\"https://bu.dusays.com/2023/11/10/654e2cf92cd45.jpg\",\"https://bu.dusays.com/2023/11/10/654e2cf6055b0.jpg\",\"https://bu.dusays.com/2023/11/10/654e2db0889fe.jpg\",\"https://bu.dusays.com/2023/11/10/654e2d50015a9.jpg\",\"https://bu.dusays.com/2023/11/05/65473848ed863.jpg\",\"https://bu.dusays.com/2023/11/10/654e2c870e280.jpg\",\"https://bu.dusays.com/2023/11/10/654e2c717eb73.jpg\",\"https://bu.dusays.com/2023/11/10/654e2c5d75d5b.jpg\",\"https://bu.dusays.com/2023/11/10/654e2da27801e.jpg\",\"https://bu.dusays.com/2023/11/10/654e2d2a67517.jpg\",\"https://bu.dusays.com/2023/11/10/654e2cf47f17a.jpg\",\"https://bu.dusays.com/2023/11/05/65473848ed863.jpg\"]', 'layout', '文章随机封面');
INSERT INTO `config` VALUES ('dark_logo', '', 'layout', '暗色LOGO');
INSERT INTO `config` VALUES ('description', '个人笔记记录', 'web', '网站描述');
INSERT INTO `config` VALUES ('favicon', 'https://luoyuanxiang.top/favicon.ico', 'web', '网站ico图标');
INSERT INTO `config` VALUES ('font', 'https://res.liuyuyang.net/LXGWWenKai.ttf', 'web', '网站字体');
INSERT INTO `config` VALUES ('footer', '花有重开日, 人无再少年', 'web', '网站底部信息');
INSERT INTO `config` VALUES ('is_article_layout', 'classics', 'layout', '网站布局');
INSERT INTO `config` VALUES ('keyword', '笔记,博客,罗远祥', 'web', '网站关键词');
INSERT INTO `config` VALUES ('light_logo', '', 'layout', '亮色LOGO');
INSERT INTO `config` VALUES ('reco_article', '[\"23\",\"17\",\"16\"]', 'layout', '作者推荐的文章');
INSERT INTO `config` VALUES ('right_sidebar', '[\"hotArticle\",\"newComments\",\"randomArticle\",\"author\"]', 'layout', '侧边栏布局');
INSERT INTO `config` VALUES ('social', '[\n  \"{\\\"name\\\":\\\"GitHub\\\",\\\"url\\\":\\\"https://github.com/luoyuanxiang\\\"}\",\n  \"{\\\"name\\\":\\\"Gitee\\\",\\\"url\\\":\\\"https://gitee.com/luoyuanxiang\\\"}\",\n  \"{\\\"name\\\":\\\"Juejin\\\",\\\"url\\\":\\\"https://juejin.cn/user/3104676568636152/posts\\\"}\",\n  \"{\\\"name\\\":\\\"CSDN\\\",\\\"url\\\":\\\"https://blog.csdn.net/baidu_38296324?type=blog\\\"}\",\n  \"{\\\"name\\\":\\\"QQ\\\",\\\"url\\\":\\\"http://wpa.qq.com/msgrd?v=3&uin=1141306760&site=qq&menu=yes\\\"}\"\n]', 'layout', '社交网站');
INSERT INTO `config` VALUES ('subhead', '花有重开日, 人无再少年', 'web', '网站副标题');
INSERT INTO `config` VALUES ('swiper_image', 'https://bu.dusays.com/2024/04/24/6628990012b51.jpg', 'layout', '首页轮播图');
INSERT INTO `config` VALUES ('swiper_text', '[\"System.out.print(\\\"有些梦虽然遥不可及，但并不是不可能实现!\\\"); \",\"print(\\\"互联网从不缺乏天才，而努力才是最终的入场券\\\") ;\",\"console.log(\\\"再渺小的星光，也有属于他的光芒!\\\");\"]', 'layout', '首页轮播图打字机文案');
INSERT INTO `config` VALUES ('title', '墨韵云阁', 'web', '网站名称');
INSERT INTO `config` VALUES ('url', 'https://luoyuanxiang.top', 'web', '网站地址');
INSERT INTO `config` VALUES ('record_info', '🎯 梦想做一名技术顶尖的架构师，奈何学历太低！', 'layout', '说说卡片个人介绍');
INSERT INTO `config` VALUES ('record_name', '👋罗远祥', 'layout', '说说卡片名称');
INSERT INTO `config` VALUES ('confetti', 'false', 'web', '是否开启礼花');
INSERT INTO `config` VALUES ('lantern', 'false', 'layout', '是否开启新年快乐');
INSERT INTO `config` VALUES ('globalComment', 'true', 'web', '是否全局开启评论');
INSERT INTO `config` VALUES ('icp', '蜀ICP备2025137681号-1', 'web', '备案号');

-- ----------------------------
-- Table structure for file_detail
-- ----------------------------
DROP TABLE IF EXISTS `file_detail`;
CREATE TABLE `file_detail`  (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '文件id',
  `url` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '文件访问地址',
  `size` bigint NULL DEFAULT NULL COMMENT '文件大小，单位字节',
  `filename` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `original_filename` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '原始文件名',
  `base_path` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '基础存储路径',
  `path` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '存储路径',
  `ext` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '文件扩展名',
  `content_type` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'MIME类型',
  `platform` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '存储平台',
  `th_url` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '缩略图访问路径',
  `th_filename` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '缩略图名称',
  `th_size` bigint NULL DEFAULT NULL COMMENT '缩略图大小，单位字节',
  `th_content_type` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '缩略图MIME类型',
  `object_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '文件所属对象id',
  `object_type` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '文件所属对象类型，例如用户头像，评价图片',
  `metadata` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '文件元数据',
  `user_metadata` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '文件用户元数据',
  `th_metadata` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '缩略图元数据',
  `th_user_metadata` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '缩略图用户元数据',
  `attr` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '附加属性',
  `file_acl` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '文件ACL',
  `th_file_acl` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '缩略图文件ACL',
  `hash_info` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '哈希信息',
  `upload_id` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '上传ID，仅在手动分片上传时使用',
  `upload_status` int NULL DEFAULT NULL COMMENT '上传状态，仅在手动分片上传时使用，1：初始化完成，2：上传完成',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '文件记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_detail
-- ----------------------------
INSERT INTO `file_detail` VALUES ('1916675714019438593', 'https://oss.luoyuanxiang.top/thrivex/swiper/680ee2f3e4b08588b0e0cf7e.png', 3265927, '680ee2f3e4b08588b0e0cf7e.png', '山水如画.png', 'thrivex/', 'swiper/', 'png', 'image/png', 'qiniu', NULL, NULL, NULL, NULL, NULL, NULL, '{}', '{}', '{}', '{}', '{}', NULL, NULL, '{}', NULL, NULL, '2025-04-28 02:07:47');
INSERT INTO `file_detail` VALUES ('1916684757299572738', 'https://oss.luoyuanxiang.top/thrivex/article/680eeb66e4b08588b0e0cf7f.jpeg', 196979, '680eeb66e4b08588b0e0cf7f.jpeg', 'cd69692de94f79a594b2bf1619a0ed09.jpeg', 'thrivex/', 'article/', 'jpeg', 'image/jpeg', 'qiniu', NULL, NULL, NULL, NULL, NULL, NULL, '{}', '{}', '{}', '{}', '{}', NULL, NULL, '{}', NULL, NULL, '2025-04-28 02:43:51');
INSERT INTO `file_detail` VALUES ('1916684970181472258', 'https://oss.luoyuanxiang.top/thrivex/article/680eeb99e4b08588b0e0cf80.jpeg', 62121, '680eeb99e4b08588b0e0cf80.jpeg', '1725862154539-4564f526-7980-46d0-9a33-4fa83f9e23d6.jpeg', 'thrivex/', 'article/', 'jpeg', 'image/jpeg', 'qiniu', NULL, NULL, NULL, NULL, NULL, NULL, '{}', '{}', '{}', '{}', '{}', NULL, NULL, '{}', NULL, NULL, '2025-04-28 02:44:42');

-- ----------------------------
-- Table structure for footprint
-- ----------------------------
DROP TABLE IF EXISTS `footprint`;
CREATE TABLE `footprint`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `content` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '坐标纬度',
  `images` json NULL COMMENT '图片',
  `create_time` datetime NOT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `footprint_pk_2`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '足迹' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of footprint
-- ----------------------------
INSERT INTO `footprint` VALUES (44, '成都', NULL, '成都', '104.066301,30.572961', '[]', '2025-04-21 00:00:00');
INSERT INTO `footprint` VALUES (45, '凉山', NULL, '凉山', '102.267713,27.881396', '[]', '2020-04-21 00:00:00');
INSERT INTO `footprint` VALUES (46, '广元', NULL, '广元', '105.844004,32.435774', '[]', '2019-04-24 00:00:00');
INSERT INTO `footprint` VALUES (47, '北京', NULL, '北京', '116.407387,39.904179', '[]', '2020-04-10 00:00:00');
INSERT INTO `footprint` VALUES (48, '绵阳', NULL, '绵阳', '104.679127,31.467673', '[]', '2022-12-28 00:00:00');
INSERT INTO `footprint` VALUES (49, '西昌', NULL, '西昌', '102.264166,27.894410', '[]', '2025-04-28 13:01:37');

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网站标题',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网站描述',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网站邮箱',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网站logo',
  `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网站链接',
  `rss` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order` int NOT NULL DEFAULT 0 COMMENT '友联顺序',
  `type_id` int NOT NULL COMMENT '网站所绑定的类型',
  `audit_status` int NOT NULL DEFAULT 0 COMMENT '是否审核',
  `create_time` datetime NOT NULL COMMENT '网站创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '友链' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link` VALUES (50, '宇阳', 'ThriveX 博客管理系统作者', 'liuyuyang1024@yeah.net', 'https://q1.qlogo.cn/g?b=qq&nk=3311118881&s=640', 'https://liuyuyang.net/', 'https://liuyuyang.net/api/rss', 0, 4, 1, '2024-08-13 15:13:27');
INSERT INTO `link` VALUES (51, 'LoongBlog', '努力成为最好的我自己', NULL, 'https://bucket.starlightpathserver.fun/blog/record/67e6d11ce4b0dcf86abf395f.jpg', 'https://starlightpathserver.fun', 'https://starlightpathserver.fun/api/rss', 0, 2, 1, '2025-04-26 10:22:33');
INSERT INTO `link` VALUES (52, '兔白白', '欢迎来到宇宙超级无敌萝御双修美少女兔白白的博客！', NULL, 'https://pic1.imgdb.cn/item/6804b1be58cb8da5c8b8ffa0.jpg', 'https://ydbsq123.top', 'https://ydbsq123.top/api/rss', 0, 2, 1, '2025-04-26 10:25:34');
INSERT INTO `link` VALUES (57, '博客星球', '每一个博客都是一个独立星球！', NULL, 'https://www.blogplanet.cn/img/bkxq.png', 'https://www.blogplanet.cn/', NULL, 0, 6, 1, '2025-04-28 17:49:17');

-- ----------------------------
-- Table structure for link_type
-- ----------------------------
DROP TABLE IF EXISTS `link_type`;
CREATE TABLE `link_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型名称',
  `is_admin` int NOT NULL DEFAULT 0 COMMENT '用户是否可选择',
  `order` int NOT NULL DEFAULT 0 COMMENT '显示顺序',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `type_pk_2`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '网站类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of link_type
-- ----------------------------
INSERT INTO `link_type` VALUES (1, '生活类', 0, 4);
INSERT INTO `link_type` VALUES (2, '技术类', 0, 5);
INSERT INTO `link_type` VALUES (3, '全站置顶', 1, 1);
INSERT INTO `link_type` VALUES (4, '推荐', 1, 2);
INSERT INTO `link_type` VALUES (5, '大佬', 1, 3);
INSERT INTO `link_type` VALUES (6, '聚合类', 0, 6);

-- ----------------------------
-- Table structure for oss
-- ----------------------------
DROP TABLE IF EXISTS `oss`;
CREATE TABLE `oss`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `platform` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '平台',
  `access_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'key',
  `secret_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '/' COMMENT '密钥',
  `end_point` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库所在地域',
  `bucket_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存储桶',
  `domain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '访问域名',
  `storage_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '本地存储路径',
  `path_patterns` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '本地访问路径',
  `base_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存储基础路径',
  `is_enable` int NULL DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'oss配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oss
-- ----------------------------
INSERT INTO `oss` VALUES (1, 'local', NULL, '', NULL, NULL, 'http://localhost:9999/', 'C:\\Users\\11413\\Desktop\\ThriveX\\', '/upload/**', 'upload/', 0);
INSERT INTO `oss` VALUES (8, 'qiniu', '5a38VWt627k5wKza_GAQSEjt41qJ6nv0Y1ebmYQj', '2M_CxKUMbBZfLTw1dGD-ssFTp1RO2mRbFGA3dS05', 'cn-north-1', 'luoyuanxiang', 'https://oss.luoyuanxiang.top/', NULL, NULL, 'thrivex/', 1);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限标识',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限介绍',
  `group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限分组',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `Permission_pk_2`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 'user:add', '新增用户', 'user');
INSERT INTO `permission` VALUES (2, 'user:del', '删除用户', 'user');
INSERT INTO `permission` VALUES (3, 'user:edit', '编辑用户', 'user');
INSERT INTO `permission` VALUES (4, 'user:info', '获取用户', 'user');
INSERT INTO `permission` VALUES (5, 'user:list', '获取用户列表', 'user');
INSERT INTO `permission` VALUES (6, 'user:pass', '修改用户密码', 'user');
INSERT INTO `permission` VALUES (7, 'data:add', '新增数据', 'data');
INSERT INTO `permission` VALUES (8, 'data:del', '删除数据', 'data');
INSERT INTO `permission` VALUES (9, 'article:add', '新增文章', 'article');
INSERT INTO `permission` VALUES (10, 'article:del', '删除文章', 'article');
INSERT INTO `permission` VALUES (11, 'article:reduction', '还原被删除的文章', 'article');
INSERT INTO `permission` VALUES (12, 'article:del', '批量删除文章', 'article');
INSERT INTO `permission` VALUES (13, 'article:edit', '编辑文章', 'article');
INSERT INTO `permission` VALUES (14, 'cate:add', '新增分类', 'cate');
INSERT INTO `permission` VALUES (15, 'cate:del', '删除分类', 'cate');
INSERT INTO `permission` VALUES (16, 'cate:edit', '编辑分类', 'cate');
INSERT INTO `permission` VALUES (17, 'comment:del', '删除评论', 'comment');
INSERT INTO `permission` VALUES (18, 'comment:edit', '编辑评论', 'comment');
INSERT INTO `permission` VALUES (19, 'comment:audit', '审核评论', 'comment');
INSERT INTO `permission` VALUES (22, 'config:edit', '修改项目配置', 'config');
INSERT INTO `permission` VALUES (23, 'email:dismiss', '驳回通知邮件', 'email');
INSERT INTO `permission` VALUES (24, 'file:info', '获取文件信息', 'file');
INSERT INTO `permission` VALUES (25, 'file:dir', '获取目录列表', 'file');
INSERT INTO `permission` VALUES (26, 'file:list', '获取文件列表', 'file');
INSERT INTO `permission` VALUES (27, 'file:add', '文件上传', 'file');
INSERT INTO `permission` VALUES (28, 'file:del', '删除文件', 'file');
INSERT INTO `permission` VALUES (29, 'oss:add', '新增oss配置', 'oss');
INSERT INTO `permission` VALUES (30, 'oss:del', '删除oss配置', 'oss');
INSERT INTO `permission` VALUES (31, 'oss:edit', '更新oss配置', 'oss');
INSERT INTO `permission` VALUES (32, 'oss:info', '获取oss配置', 'oss');
INSERT INTO `permission` VALUES (33, 'oss:list', '获取oss配置列表', 'oss');
INSERT INTO `permission` VALUES (34, 'oss:enable', '启用oss配置', 'oss');
INSERT INTO `permission` VALUES (35, 'oss:getEnableOss', '获取当前启用的oss配置', 'oss');
INSERT INTO `permission` VALUES (36, 'oss:getPlatform', '获取支持的oss平台', 'oss');
INSERT INTO `permission` VALUES (37, 'record:add', '新增说说', 'record');
INSERT INTO `permission` VALUES (38, 'record:del', '删除说说', 'record');
INSERT INTO `permission` VALUES (39, 'record:edit', '编辑说说', 'record');
INSERT INTO `permission` VALUES (40, 'role:add', '新增角色', 'role');
INSERT INTO `permission` VALUES (41, 'role:del', '删除角色', 'role');
INSERT INTO `permission` VALUES (42, 'role:edit', '编辑角色', 'role');
INSERT INTO `permission` VALUES (43, 'role:info', '获取角色', 'role');
INSERT INTO `permission` VALUES (44, 'role:list', '获取角色列表', 'role');
INSERT INTO `permission` VALUES (46, 'role:bindingRoute', '分配角色权限', 'role');
INSERT INTO `permission` VALUES (47, 'route:add', '新增路由', 'route');
INSERT INTO `permission` VALUES (48, 'route:del', '删除路由', 'route');
INSERT INTO `permission` VALUES (49, 'route:edit', '编辑路由', 'route');
INSERT INTO `permission` VALUES (50, 'route:info', '获取路由', 'route');
INSERT INTO `permission` VALUES (51, 'route:list', '获取路由列表', 'route');
INSERT INTO `permission` VALUES (52, 'swiper:add', '新增轮播图', 'swiper');
INSERT INTO `permission` VALUES (53, 'swiper:del', '删除轮播图', 'swiper');
INSERT INTO `permission` VALUES (54, 'swiper:edit', '编辑轮播图', 'swiper');
INSERT INTO `permission` VALUES (55, 'tag:add', '新增标签', 'tag');
INSERT INTO `permission` VALUES (56, 'tag:del', '删除标签', 'tag');
INSERT INTO `permission` VALUES (57, 'tag:edit', '编辑标签', 'tag');
INSERT INTO `permission` VALUES (58, 'wall:del', '删除留言', 'wall');
INSERT INTO `permission` VALUES (59, 'wall:edit', '编辑留言', 'wall');
INSERT INTO `permission` VALUES (60, 'wall:audit', '审核留言', 'wall');
INSERT INTO `permission` VALUES (62, 'permission:add', '新增权限', 'permission');
INSERT INTO `permission` VALUES (63, 'permission:del', '删除权限', 'permission');
INSERT INTO `permission` VALUES (64, 'permission:edit', '编辑权限', 'permission');
INSERT INTO `permission` VALUES (65, 'permission:info', '获取权限', 'permission');
INSERT INTO `permission` VALUES (66, 'permission:list', '获取权限列表', 'permission');
INSERT INTO `permission` VALUES (67, 'link:del', '删除网站', 'link');
INSERT INTO `permission` VALUES (68, 'link:edit', '编辑网站', 'link');
INSERT INTO `permission` VALUES (69, 'link:audit', '审核网站', 'link');
INSERT INTO `permission` VALUES (70, 'email:reply_wall', '回复留言', 'email');
INSERT INTO `permission` VALUES (71, 'wall:choice', '设置与取消精选留言', 'wall');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `images` json NULL COMMENT '图片',
  `create_time` datetime NOT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '闪恋' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `mark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色标识',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', 'admin', '最高权限');
INSERT INTO `role` VALUES (2, '作者', 'author', '发布文章、查看文章列表');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL COMMENT '角色ID',
  `permission_id` int NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_permission_pk_2`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 154 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (88, 1, 1);
INSERT INTO `role_permission` VALUES (89, 1, 2);
INSERT INTO `role_permission` VALUES (90, 1, 3);
INSERT INTO `role_permission` VALUES (91, 1, 4);
INSERT INTO `role_permission` VALUES (92, 1, 5);
INSERT INTO `role_permission` VALUES (93, 1, 6);
INSERT INTO `role_permission` VALUES (94, 1, 7);
INSERT INTO `role_permission` VALUES (95, 1, 8);
INSERT INTO `role_permission` VALUES (96, 1, 9);
INSERT INTO `role_permission` VALUES (97, 1, 10);
INSERT INTO `role_permission` VALUES (98, 1, 11);
INSERT INTO `role_permission` VALUES (99, 1, 13);
INSERT INTO `role_permission` VALUES (100, 1, 14);
INSERT INTO `role_permission` VALUES (101, 1, 15);
INSERT INTO `role_permission` VALUES (102, 1, 16);
INSERT INTO `role_permission` VALUES (103, 1, 17);
INSERT INTO `role_permission` VALUES (104, 1, 18);
INSERT INTO `role_permission` VALUES (105, 1, 19);
INSERT INTO `role_permission` VALUES (106, 1, 22);
INSERT INTO `role_permission` VALUES (107, 1, 23);
INSERT INTO `role_permission` VALUES (108, 1, 70);
INSERT INTO `role_permission` VALUES (109, 1, 24);
INSERT INTO `role_permission` VALUES (110, 1, 25);
INSERT INTO `role_permission` VALUES (111, 1, 26);
INSERT INTO `role_permission` VALUES (112, 1, 27);
INSERT INTO `role_permission` VALUES (113, 1, 28);
INSERT INTO `role_permission` VALUES (114, 1, 29);
INSERT INTO `role_permission` VALUES (115, 1, 30);
INSERT INTO `role_permission` VALUES (116, 1, 31);
INSERT INTO `role_permission` VALUES (117, 1, 32);
INSERT INTO `role_permission` VALUES (118, 1, 33);
INSERT INTO `role_permission` VALUES (119, 1, 34);
INSERT INTO `role_permission` VALUES (120, 1, 35);
INSERT INTO `role_permission` VALUES (121, 1, 36);
INSERT INTO `role_permission` VALUES (122, 1, 37);
INSERT INTO `role_permission` VALUES (123, 1, 38);
INSERT INTO `role_permission` VALUES (124, 1, 39);
INSERT INTO `role_permission` VALUES (125, 1, 40);
INSERT INTO `role_permission` VALUES (126, 1, 41);
INSERT INTO `role_permission` VALUES (127, 1, 42);
INSERT INTO `role_permission` VALUES (128, 1, 43);
INSERT INTO `role_permission` VALUES (129, 1, 44);
INSERT INTO `role_permission` VALUES (130, 1, 46);
INSERT INTO `role_permission` VALUES (131, 1, 47);
INSERT INTO `role_permission` VALUES (132, 1, 48);
INSERT INTO `role_permission` VALUES (133, 1, 49);
INSERT INTO `role_permission` VALUES (134, 1, 50);
INSERT INTO `role_permission` VALUES (135, 1, 51);
INSERT INTO `role_permission` VALUES (136, 1, 52);
INSERT INTO `role_permission` VALUES (137, 1, 53);
INSERT INTO `role_permission` VALUES (138, 1, 54);
INSERT INTO `role_permission` VALUES (139, 1, 55);
INSERT INTO `role_permission` VALUES (140, 1, 56);
INSERT INTO `role_permission` VALUES (141, 1, 57);
INSERT INTO `role_permission` VALUES (142, 1, 58);
INSERT INTO `role_permission` VALUES (143, 1, 59);
INSERT INTO `role_permission` VALUES (144, 1, 60);
INSERT INTO `role_permission` VALUES (145, 1, 71);
INSERT INTO `role_permission` VALUES (146, 1, 62);
INSERT INTO `role_permission` VALUES (147, 1, 63);
INSERT INTO `role_permission` VALUES (148, 1, 64);
INSERT INTO `role_permission` VALUES (149, 1, 65);
INSERT INTO `role_permission` VALUES (150, 1, 66);
INSERT INTO `role_permission` VALUES (151, 1, 67);
INSERT INTO `role_permission` VALUES (152, 1, 68);
INSERT INTO `role_permission` VALUES (153, 1, 69);

-- ----------------------------
-- Table structure for route
-- ----------------------------
DROP TABLE IF EXISTS `route`;
CREATE TABLE `route`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '路由路径',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '路由描述',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `routes_pk_2`(`id` ASC) USING BTREE,
  UNIQUE INDEX `routes_pk`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '路由' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of route
-- ----------------------------
INSERT INTO `route` VALUES (1, '/', '仪表盘');
INSERT INTO `route` VALUES (2, '/create', '创作');
INSERT INTO `route` VALUES (5, '/setup', '系统');
INSERT INTO `route` VALUES (6, '/article', '文章管理');
INSERT INTO `route` VALUES (7, '/tag', '标签管理');
INSERT INTO `route` VALUES (8, '/comment', '评论管理');
INSERT INTO `route` VALUES (9, '/cate', '分类管理');
INSERT INTO `route` VALUES (10, '/web', '网站管理');
INSERT INTO `route` VALUES (11, '/swiper', '轮播图管理');
INSERT INTO `route` VALUES (12, '/user', '用户管理');
INSERT INTO `route` VALUES (13, '/role', '角色管理');
INSERT INTO `route` VALUES (14, '/rss', '订阅中心');
INSERT INTO `route` VALUES (15, '/chart', '文件系统');
INSERT INTO `route` VALUES (17, '/iter', '更新日志');
INSERT INTO `route` VALUES (20, '/route', '路由管理');
INSERT INTO `route` VALUES (21, '/file', '文件管理');
INSERT INTO `route` VALUES (23, '/footprint', '足迹管理');
INSERT INTO `route` VALUES (24, '/work', '工作台');
INSERT INTO `route` VALUES (25, '/wall', '留言管理');
INSERT INTO `route` VALUES (26, '/draft', '草稿箱');
INSERT INTO `route` VALUES (27, '/recycle', '回收站');
INSERT INTO `route` VALUES (28, '/record', '说说管理');
INSERT INTO `route` VALUES (29, '/create_record', '闪念');
INSERT INTO `route` VALUES (30, '/storage', '存储管理');
INSERT INTO `route` VALUES (31, '/album', '照片墙');
INSERT INTO `route` VALUES (32, '/assistant', '写作助手');

-- ----------------------------
-- Table structure for route_role
-- ----------------------------
DROP TABLE IF EXISTS `route_role`;
CREATE TABLE `route_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `route_id` int NOT NULL COMMENT '路由id',
  `role_id` int NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `routes_role_pk_2`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色和路由' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of route_role
-- ----------------------------
INSERT INTO `route_role` VALUES (1, 1, 5);
INSERT INTO `route_role` VALUES (2, 10, 5);
INSERT INTO `route_role` VALUES (3, 7, 5);
INSERT INTO `route_role` VALUES (4, 9, 5);
INSERT INTO `route_role` VALUES (5, 8, 5);
INSERT INTO `route_role` VALUES (6, 6, 5);
INSERT INTO `route_role` VALUES (7, 2, 5);
INSERT INTO `route_role` VALUES (8, 5, 5);
INSERT INTO `route_role` VALUES (36, 1, 2);
INSERT INTO `route_role` VALUES (37, 7, 2);
INSERT INTO `route_role` VALUES (38, 9, 2);
INSERT INTO `route_role` VALUES (39, 8, 2);
INSERT INTO `route_role` VALUES (40, 6, 2);
INSERT INTO `route_role` VALUES (41, 2, 2);
INSERT INTO `route_role` VALUES (42, 30, 1);
INSERT INTO `route_role` VALUES (43, 27, 1);
INSERT INTO `route_role` VALUES (44, 26, 1);
INSERT INTO `route_role` VALUES (45, 25, 1);
INSERT INTO `route_role` VALUES (46, 24, 1);
INSERT INTO `route_role` VALUES (47, 23, 1);
INSERT INTO `route_role` VALUES (48, 21, 1);
INSERT INTO `route_role` VALUES (49, 14, 1);
INSERT INTO `route_role` VALUES (50, 17, 1);
INSERT INTO `route_role` VALUES (51, 20, 1);
INSERT INTO `route_role` VALUES (52, 1, 1);
INSERT INTO `route_role` VALUES (53, 2, 1);
INSERT INTO `route_role` VALUES (54, 5, 1);
INSERT INTO `route_role` VALUES (55, 6, 1);
INSERT INTO `route_role` VALUES (56, 7, 1);
INSERT INTO `route_role` VALUES (57, 8, 1);
INSERT INTO `route_role` VALUES (58, 9, 1);
INSERT INTO `route_role` VALUES (59, 10, 1);
INSERT INTO `route_role` VALUES (60, 11, 1);
INSERT INTO `route_role` VALUES (61, 12, 1);
INSERT INTO `route_role` VALUES (62, 13, 1);
INSERT INTO `route_role` VALUES (63, 15, 1);
INSERT INTO `route_role` VALUES (64, 28, 1);
INSERT INTO `route_role` VALUES (65, 29, 1);
INSERT INTO `route_role` VALUES (66, 31, 1);
INSERT INTO `route_role` VALUES (67, 32, 1);

-- ----------------------------
-- Table structure for swiper
-- ----------------------------
DROP TABLE IF EXISTS `swiper`;
CREATE TABLE `swiper`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '轮播图' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of swiper
-- ----------------------------
INSERT INTO `swiper` VALUES (1, '半山腰的风景很美，然而我还是更想到山顶看看', 'The scenery halfway up the mountain is beautiful, but I still prefer to see the mountaintop', 'https://bu.dusays.com/2023/11/10/654e2cf6055b0.jpg', '/');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (89, 'java');
INSERT INTO `tag` VALUES (90, 'docker');
INSERT INTO `tag` VALUES (91, 'docker compose');
INSERT INTO `tag` VALUES (92, 'SQL');
INSERT INTO `tag` VALUES (93, 'spring boot');
INSERT INTO `tag` VALUES (94, '多线程');
INSERT INTO `tag` VALUES (95, 'Dockerfile');
INSERT INTO `tag` VALUES (96, 'GitHub');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名称',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `avatar` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '用户头像',
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户介绍',
  `role_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户角色ID',
  `create_time` datetime NOT NULL COMMENT '用户创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_pk`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '7feaad1089dd8138df486c74f30f162d', '罗远祥', '1141306760@qq.com', 'https://luoyuanxiang.top/logo.png', '牛马', '1', '2024-08-13 15:13:27');

-- ----------------------------
-- Table structure for user_token
-- ----------------------------
DROP TABLE IF EXISTS `user_token`;
CREATE TABLE `user_token`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int NOT NULL COMMENT '用户 ID',
  `token` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户token',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_token_pk_2`(`id` ASC) USING BTREE,
  UNIQUE INDEX `user_token_pk_3`(`uid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户 token' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_token
-- ----------------------------
INSERT INTO `user_token` VALUES (90, 1, 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc0NjY5MDQ5MCwicm9sZSI6ImFkbWluIiwiaWQiOjEsImV4cCI6MTc0Njk0OTY5MH0.6KpwuTGZ426dOIWDs99W2cSpEp1T1JCfDF7DlNkdrgg');

-- ----------------------------
-- Table structure for wall
-- ----------------------------
DROP TABLE IF EXISTS `wall`;
CREATE TABLE `wall`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '神秘人' COMMENT '留言人名称',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '留言内容',
  `color` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '#ffe3944d' COMMENT '留言墙颜色',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '留言者邮箱',
  `cate_id` int NOT NULL,
  `audit_status` int NULL DEFAULT 0 COMMENT '是否审核',
  `is_choice` int NOT NULL DEFAULT 0 COMMENT '是否为精选留言',
  `create_time` datetime NOT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `wall_pk_2`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '留言墙' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wall
-- ----------------------------

-- ----------------------------
-- Table structure for wall_cate
-- ----------------------------
DROP TABLE IF EXISTS `wall_cate`;
CREATE TABLE `wall_cate`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
  `mark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类标识',
  `order` int NOT NULL COMMENT '分类顺序',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `wall_cate_pk_2`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '留言分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wall_cate
-- ----------------------------
INSERT INTO `wall_cate` VALUES (1, '全部', 'all', 1);
INSERT INTO `wall_cate` VALUES (2, '想对我说的话', 'info', 2);
INSERT INTO `wall_cate` VALUES (3, '对我的建议', 'suggest', 3);
INSERT INTO `wall_cate` VALUES (6, '其他', 'other', 6);
INSERT INTO `wall_cate` VALUES (7, '精选', 'choice', 0);

SET FOREIGN_KEY_CHECKS = 1;
