# wxcloudrun-springboot
![GitHub package.json dependency version (prod)](https://img.shields.io/badge/maven-3.6.0-green)
![GitHub package.json dependency version (prod)](https://img.shields.io/badge/jdk-11-green)

基于微信云托管 Java Springboot 框架模版开发的快艇Dice小程序后端。

## 小程序界面

### 初始界面

![](./imgs/lobby.png#center)

### 人机对战界面

<img src="./imgs/robot.png#center" style="zoom:50%;" />

### 本地对战界面

<img src="./imgs//local.png#center" style="zoom:50%;" />

### 统计界面

<img src="./imgs/statistics.png#center" style="zoom:50%;" />

### 规则界面

<img src="./imgs/rule1.png#center" style="zoom:50%;" />

<img src="./imgs/rule2.png#center" style="zoom:50%;" />

## 目录结构说明
~~~
.
├── Dockerfile                      Dockerfile 文件
├── LICENSE                         LICENSE 文件
├── README.md                       README 文件
├── container.config.json           模板部署「服务设置」初始化配置（二开请忽略）
├── mvnw                            mvnw 文件，处理mevan版本兼容问题
├── mvnw.cmd                        mvnw.cmd 文件，处理mevan版本兼容问题
├── pom.xml                         pom.xml文件
├── settings.xml                    maven 配置文件
├── springboot-cloudbaserun.iml     项目配置文件
└── src                             源码目录
    └── main                        源码主目录
        ├── java                    业务逻辑目录
        └── resources               资源文件目录
~~~


## 服务 API 文档

### `GET /dice/local/init`

本地对战初始化

#### 请求参数

无

#### 响应结果

- `List<Player>`：玩家列表



### `GET /dice/robot/init`

人机对战初始化

#### 请求参数

无

#### 响应结果

- `List<Player>`：玩家列表

  


### `POST /dice/local/roll`

本地对战摇骰子

#### 请求参数

- `DiceRequest`: 包含两个骰子信息的请求

#### 响应结果

- `List<List<Dice>>`：骰子列表的列表

  


### `POST /dice/robot/roll`

本地对战摇骰子

#### 请求参数

- `DiceRequest`: 包含两个骰子信息的请求

#### 响应结果

- `DiceReply`：包含两个骰子和人机加减倍率机制的响应

  


### `POST /dice/pattern`

获取牌型

#### 请求参数

- `DiceRequest`: 包含两个骰子信息的请求

#### 响应结果

- `List<String>`： 牌型列表

  


### `POST /dice/updateChip`

更新筹码

#### 请求参数

- `DiceRequest`: 包含两个骰子信息的请求

#### 响应结果

- `List<Integer>` ：筹码列表



### `POST /statistics/update`

更新统计信息

#### 请求参数

- `Statistics`: 更新信息

#### 响应结果

无




### `GET /statistics/get`

获取统计信息

#### 请求参数

- `String token`: 用户token
- `String openid`: 用户openid

#### 响应结果

- `Statistics`: 统计信息



### `GET /isLogin`

检测当前用户是否为第一次登录

#### 请求参数

- `String token`: 用户token

#### 响应结果

- `boolean`: 用户是否登录

  


### `GET /login`

将第一次登录的用户信息存入数据库并返回其token

#### 请求参数

- `String code`: 用户小程序返回的code
- `String nickname`:用户昵称
- `String avatarurl`: 用户头像url

#### 响应结果

- `String token`:给用户的token



### `GET /getProfile`

获取用户信息

#### 请求参数

- `String token`: 用户token

#### 响应结果

- `UserLogin`:用户存在数据库的个人信息




## License

本程序使用 GNU General Public License v3.0 许可证。详情请参阅 [LICENSE](../../LICENSE) 文件。
