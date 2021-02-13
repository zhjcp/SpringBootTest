import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
//import org.apache.shiro.ini.IniSecurityManagerFactory;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
//import org.apache.shiro.lang.util.Factory;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Simple Quickstart application showing how to use Shiro's API.
 *
 * @since 0.9 RC2
 */
public class Quickstart {

    //日志：使用log而不是原始的System.out.println()
    private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);


    public static void main(String[] args) {
        //固定代码——搭建Shiro的使用环境；获取ini配置、创建单例
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);//是单例


        //重点代码

        // 获取当前的用户对象
        Subject currentUser = SecurityUtils.getSubject();

        //官方注释： Do some stuff with a Session (no need for a web or EJB container!!!)  ---> 可以脱离web使用！！！
        // 通过当前用户获取该用户的session，并使用session存取值
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            log.info("Retrieved the correct value! [" + value + "]");
        }

        // let's login the current user so we can check against roles and permissions:
        //判断当前用户是否已被认证
        if (!currentUser.isAuthenticated()) {
            //通过用户的账号、密码生成令牌（Token）
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            //设置记住我
            token.setRememberMe(true);
            try {
                //执行登录操作
                currentUser.login(token);
            } catch (UnknownAccountException uae) {//异常1：未知账号异常  要在ini文件中有才是已知账号，参考shiro.ini的[users]部分
                log.info("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {//异常2：密码错误异常
                log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {//异常3：账号锁定异常
                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) {//异常：捕获更多的异常
                //unexpected condition?  error?
            }
        }

        //say who they are:
        //print their identifying principal (in this case, a username):
        //currentUser.getPrincipal() 通过类似的方式可以获取用户信息
        log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

        //test a role:
        //粗粒度
        //判断当前用户是否拥有某个角色(角色 == 权限的集合)
        //权限的配置情况参考shiro.ini的[roles]部分
        if (currentUser.hasRole("schwartz")) {
            log.info("May the Schwartz be with you!");
        } else {
            log.info("Hello, mere mortal.");
        }

        //test a typed permission (not instance-level)
        //细粒度
        //判断当前用户是否有某个特定的权限
        if (currentUser.isPermitted("lightsaber:wield")) {
            log.info("You may use a lightsaber ring.  Use it wisely.");
        } else {
            log.info("Sorry, lightsaber rings are for schwartz masters only.");
        }

        //a (very powerful) Instance Level permission:
        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
            log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
            log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }

        //all done - log out!
        //设置注销
        currentUser.logout();

        //结束启动
        System.exit(0);
    }
}
