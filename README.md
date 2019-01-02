#  SpringCloud的G版本应用
**一.cloud-eureka-server eureka注册中心集群**
1. 这是使用的是伪集群，节点peer1，peer2，peer3都是映射IP本地地址
2. 利用IDEA启动多个实例来启动注册中心集群

**二.cloud-service-provider服务提供者**
1. 向注册中心注册一个服务名为service-provider-helloworld的服务
2. 利用IDEA启动多个实例来启动服务提供者集群

**三.cloud-service-consumer服务消费者**
1. 首先开启服务发现，连接eureka注册中心集群
2. 通过feign的方式来调用远程服务
3. openfeign是feign和rabbion的结合，默认开启负载均衡(轮询算法)
4. hystrix断路器使用（详细代码参考HelloHystrix），配置文件中的feign.hystrix.enable=true开启断路器
5. 添加spring-cloud-starter-netflix-hystrix-dashboard依赖，使用@EnableHystrixDashboard注解开启熔断监控
6. 熔断监控集群使用Turbine(公司网络爆炸，idea无法一步创建springboot项目，后期整合)

**四.cloud-config-git配置中心**
1. git方式的配置中心的服务中心
2. 引入依赖，配置git信息可以在线读取git仓库中的配置文件
3. 配置中心的高可用，引入eureka客户端,可以配置多个配置中心
4. cloud-config-git-client配置中心客户端，可读取配置文件中的信息，需要在bootstrap中配置config的server地址，因为bootstrap配置最先加载，如果在application中配置会默认访问端口为8888的配置中心server

**五.cloud-gateway-zuul网关**
1. zuul入门篇，引入eureka客户端，zuul依赖，通过@EnableZuulProxy注解开启zuul网关，在通过配置进行了路由转发
