# Honey-Response 组件
## 简介
###Honey-Response:一个适用于JavaWeb应用,提供统一Rest响应以及优化异常与断言书写的基础组件。其定位是一个开箱即用、易用、简洁、代码侵入性低、支持自定义的企业级基础组件。
## 特点
###[1]Honey-Response会基于springboot starter原理 自动装配，所以如果你觉得我默认封装的配置不符合你实际业务场景，你可以方便自定义自己的配置来继续使用；  
###[2]Honey-response可以让你自定义异常变得相当方便，就像定义一个枚举常量一样。他底层模仿了Assert，可以让你的异常和断言结合同时使用。一行代码解决断言并抛出自定义异常，并且拥有良好的代码语义性；  
###[3]Honey-Response支持多环境适配返回Rest响应数据，无论你使用常规springboot的profiles或者适应携程的Apollo都可以完美支持  
###[4]Honey-Response提供了国际化消息，你的系统想要实现国际化消息，无需任何配置即可实现。当然你得提供国际化消息文件；  
###[5]Honey-Response可以让你的controller层变得相当简洁。  
###[6]Honey-Response提供了@HoneyConditionalOnProperty注解，用法与springboot的@ConditionalOnProperty一致。他可以解决当你使用Apollo并将系统配置托管于其内置的Eureka时，而你又想使用@ConditionalOnProperty进行条件装配bean时，spring提供的这个注解在collectProperties时无法收集到你目前系统的配置文件(因为你已经将配置托管于apollo)而导致注解里name的属性key无法解析成功而失效  
因此你可以考虑使用@HoneyConditionalOnProperty注解。
## 如何使用
### [1]引入依赖
```java  
    <!-- honey-response-->
    <dependency>
        <groupId>com.eboy</groupId>
        <artifactId>honey-response</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
```
### [2]简洁干净的controller及其响应格式
####Honey-Response会在如果你使用spring的参数校验框架时，就像第三个例子那样，会响应message将多个参数校验失败的结果拼装返回，类似这样："message": "honeyId: 不能为空, honeyName: 不能为空"
```java  
  @GetMapping("/success")
  public HoneyResponse success() {
      return HoneyResponse.success("hello honey-response");
  }
  @GetMapping("/failure")
  public HoneyResponse failure() {
      return HoneyResponse.failure(HoneyUCExceptionException.INVALID_TOKEN);
  }
  @PostMapping("/test")
  @AuthSkip
  public HoneyResponse test(@Validated @RequestBody Honey honey){
      return HoneyResponse.success(honeyResponseService.test());
  }
响应格式：{"success":true,"code":200,"message":null,"data":"hello honey-response"}
```
### [3]自定义异常以及断言用法
####Honey-Response内部提供了HoneyArgsException(参数相关的)、HoneyCommonException(通用)、HoneyServletException(servlet)以及HoneyUCException(用户相关)四大异常枚举 默认定义了系统中常见的异常。如果你觉得我的不好用或者不全，你可以自定义。
####用法格式：
```java  
   HoneyCommonExceptionException.SERVER_ERROR.assertIsTrue(boolean expression);
             |                         |            |
   实现HoneyExceptionAssert         枚举常量     Assert断言语法
   接口的异常枚举类                                      
```
### 自定义异常
####实现HoneyResponse提供的HoneyExceptionAssert接口，然后像定义枚举常量一样定义你的异常，后面就可以像上面的语法一样使用了
```java  
  public enum MyException implements HoneyExceptionAssert {
      这里自定义你的异常
      /**
       * honey-response exception
       */
      HONEY_RESPONSE_EXCEPTION(500, "honey-response exception.");
      /**
       * 返回码
       */
      private int code;
      /**
       * 返回消息
       */
      private String message;
  
      MyException(int code, String message) {
          this.code = code;
          this.message = message;
      }
      @Override
      public int getCode() {
          return code;
      }
      @Override
      public String getMessage() {
          return message;
      }
  }                                      
```
### [4]多环境适配以及全局异常捕获
####Honey-Response默认提供了HoneyEnvConfig的装配，他可以读取到springboot的profiles或者apollo的多种环境设置的运行环境属性。Honey-Response可以根据多环境适配来响应数据内容就是依靠他实现的。目前Honey-Response默认会对生产环境中不适合把具体的异常信息展示给用户的异常信息屏蔽转换掉。
#### Honey-Response默认提供了HoneyExceptionDefaultHandler，里面实现了分别捕获HoneyBusinessException(业务异常)、HoneyBaseException(自定义异常)、handleServletException(Controller上一层相关异常)、handleBindException(参数绑定异常)、handleValidException(参数校验异常)以及handleException(未知异常)
####当然我也知道我不可能罗列齐全，还有你可能使用是其他分布式配置中心，所以Honey-Response欢迎你使用自定义的。
####自定义多环境适配读取：
```java  
  @Component
  public class MyHoneyEncConfig implements HoneyEnvConfig{
      
      @Override
      public HoneyEnvConfigProperties setHoneyEnvConfig() {
          // 书写你设置环境配置规则
          return null;
      }
  
      @Override
      public String getSystemEnv() {
          // 书写你的获取当前环境的规则
          return null;
      }
  }
```
####自定义全局异常捕获处理器
```java  
    @ControllerAdvice
    @ConditionalOnWebApplication
    public class MyHoneyResponseExceptionHandler implements HoneyExceptionHandler {
         书写你的异常捕获规则
    }
```
### [5]国际化消息支持
####Honey-Response默认提供了HoneyMessage的装配和MessageSource绑定i18n资源，来支持国际化消息，你只需要在你的应用的resources下创建i18n文件并提供相应国际化消息文件即可。由于HoneyMessage的寻国际化消息文件的code是默认异常枚举类的枚举常量的code值，所以想要生效，你得接受这个规则。当然你觉得不爽，你就是要这二者就是不一样，你可以自定义属于你的HoneyMessage.具体如下
```java  
  @Component
  public class HoneyMessageImpl implements HoneyMessage {
      // 注入MessageSource i18n资源绑定，如果你未自定义过，会默认注入Honey-Response提供的messageSource
      @Resource(name = "messageSource")
      private MessageSource messageSource;
  
      @Override
      public String getMessage(HoneyBaseException e) {
         // 书写你的国际化寻码以及消息组装规则
      }
      public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
          return messageSource.getMessage(code, args, locale);
      }
  }
```
####如果你的i18n资源不想放在resources跟下，你可以自定义属于你的i18n资源绑定器，具体如下：
```java  
    @Bean("messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
        // 书写你的绑定规则
        return messageSource;
    }
```
### [6]@HoneyConditionalOnProperty注解
####解决当你使用Apollo并将系统配置托管于其内置的Eureka时，而你又想使用@ConditionalOnProperty进行条件装配bean时，spring提供的这个注解在collectProperties时无法收集到你目前系统的配置文件(因为你已经将配置托管于apollo)而导致注解里name的属性key无法解析成功而失效
####用法与springboot的@ConditionalOnProperty一致。就像这样    
```java  
  @HoneyConditionalOnProperty(prefix = "",name = "",havingValue = "",matchIfMissing = true,enableApollo = true )
```
