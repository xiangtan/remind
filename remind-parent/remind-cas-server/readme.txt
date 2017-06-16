1   修改cas.properties
    a   修改默认语言
        locale.default=zh_CN
    b   redis ticket 支持
        # Redis Ticket Registry
        redis.pool.maxTotal=4096
        redis.pool.maxIdle=200
        redis.pool.maxWaitMillis=3000
        redis.pool.testOnBorrow=true
        redis.pool.testOnReturn=true

        redis.sentinel.mastername=mymaster
        redis.sentinel.servers=192.168.7.178:26379
    c   修改默认登录用户信息
        accept.authn.users=yicai::zyw1010

 2  修改applicationContext.xml
    a   修改注解扫描包支持
        <context:component-scan base-package="org.jasig.cas,com.fsmeeting" />

 3  修改propertyFileConfigurer.xml
    a   修改casProperties的文件位置
        <util:properties id="casProperties" location="file:/fsmeeting/conf/cas.properties" />

 4  修改deployerConfigContext.xml
    a   添加redis Ticket注册支持
        <!--<alias name="defaultTicketRegistry" alias="ticketRegistry" />-->
        <alias name="redisTicketRegistry" alias="ticketRegistry" />