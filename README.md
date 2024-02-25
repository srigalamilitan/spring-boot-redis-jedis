# CRUD REDIS AND JEDIS SPRING BOOT

spring-boot-redis-jedis
Learning Spring boot using JEDIS

Prerequisites
1. Install Redis Stack
2. Install Redis using Docker
3. Script Install redis-stack on docker
   ```
   docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 -e REDIS_ARGS="--requirepass root" -v /path/your/local/machine:/data redis/redis-stack:latest
   ```

Pom.xml
``` xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>5.0.0</version>
        </dependency>
    </dependencies>
```

config file
``` java
@Configuration
public class RedisConfig {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration=new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("127.0.0.1");
        redisStandaloneConfiguration.setPort(6379);
        redisStandaloneConfiguration.setPassword("root");
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }
    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;


    }
}

```
## Test using Powershell Windows
1. Save user
   ``` ps
   $headers = New-Object "System.Collections.Generic.Dictionary[[String],[String]]"
   $headers.Add("Content-Type", "application/json")
   $body = @"
    {
    `"id`": 100,
    `"firstname`": `"krisna`",
    `"lastname`": `"putra`",
    `"emailId`": `"krisna.putra@merdeka.id`",
    `"age`": 30
    }
    "@
   $response = Invoke-RestMethod '127.0.0.1:8080/user' -Method 'POST' -Headers $headers -Body $body
   $response | ConvertTo-Json
   ```


2. Get all user

```ps
  $response = Invoke-RestMethod '127.0.0.1:8080/user' -Method 'GET' -Headers $headers
  $response | ConvertTo-Json
```

3. Get by id

  ```ps
 $response = Invoke-RestMethod '127.0.0.1:8080/user/100' -Method 'GET' -Headers $headers
 $response | ConvertTo-Json
  ```

4. Delete user
   
```ps
  $response = Invoke-RestMethod '127.0.0.1:8080/user/100' -Method 'DELETE' -Headers $headers
  $response | ConvertTo-Json
```
