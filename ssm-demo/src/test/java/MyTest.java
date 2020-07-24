
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.BookService;
import service.BookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyTest {


        @Test
        public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = context.getBean("bookServiceImpl", BookService.class);
//        List<Book> books =bookServiceImpl.queryAll();
//        for (Book book : books) {
//            System.out.println(book);
//        }


    }
}
