
import entity.Book;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import service.BookService;


import java.util.List;


public class MyTest {


    //测试redis缓存
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = context.getBean("bookServiceImpl", BookService.class);
        List<Book> books =bookServiceImpl.queryAll(0);
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println("-------------------------");
        List<Book> books2 =bookServiceImpl.queryAll(0);
        for (Book book : books2) {
            System.out.println(book);
        }
    }

    //连接redis
    @Test
    public void test2(){

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RedisTemplate<String,Object> redisTemplate = context.getBean("redisTemplate", RedisTemplate.class);
        Book book = new Book(10,"哈哈",4,"测试字段","暂无");
        redisTemplate.opsForValue().set("test",book);
        System.out.println(redisTemplate.opsForValue().get("test"));
    }
}
