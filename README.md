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

