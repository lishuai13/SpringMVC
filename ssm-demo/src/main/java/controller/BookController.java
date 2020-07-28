package controller;

import entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

@Controller
public class BookController {
    private int index = 0;

    @Autowired
    @Qualifier(value = "bookServiceImpl")
    private BookService bookService;


    @RequestMapping("/allBook/{number}")
    public String listPage(Model model, @PathVariable int number) {
        System.out.println(1);
        System.out.println(number);
        List<Book> list;
        if (number==-1){
            number=index-1;
            list = bookService.queryAll((number-1)*5+1);
        }
        if (number==10){
            number=index+1;
            list = bookService.queryAll((number-1)*5+1);
        }
        if (number==0||number==1){
            list = bookService.queryAll(0);
        }
        else {
            list = bookService.queryAll((number-1)*5+1);
        }
        index=number;
        model.addAttribute("list", list);
        return "allBook";
    }

    @RequestMapping("/toAddBook")
    public String toAddPaper() {
        return "addBook";
    }

    @RequestMapping("/addBook")
    public String addPaper(@RequestParam("file") CommonsMultipartFile file, String bookName,
                           String bookCount, String detail, HttpServletRequest request) throws IOException {


        //获取文件名 : file.getOriginalFilename();
        String uploadFileName = file.getOriginalFilename();

        //如果文件名为空，直接回到首页！
        if ("".equals(uploadFileName)){
            return "redirect:/login.jsp";
        }
        System.out.println("上传文件名 : "+uploadFileName);

        //上传路径保存设置
        String path = "C:\\Users\\lishuai\\IdeaProjects\\SpringMVC\\ssm-demo\\web\\static\\img\\";
        //如果路径不存在，创建一个
        File realPath = new File(path);
        if (!realPath.exists()){
            realPath.mkdir();
        }
        System.out.println("上传文件保存地址："+realPath);

        InputStream is = file.getInputStream(); //文件输入流
        OutputStream os = new FileOutputStream(new File(realPath,bookName+".jpg")); //文件输出流

        //读取写出
        int len=0;
        byte[] buffer = new byte[1024];
        while ((len=is.read(buffer))!=-1){
            os.write(buffer,0,len);
            os.flush();
        }
        os.close();
        is.close();
        Book book = new Book(bookName,Integer.parseInt(bookCount),detail,bookName+".jpg");
        bookService.add(book);
        return "redirect:/allBook/0";
    }

    @RequestMapping("/toUpdateBook/{id}")
    public String toUpdateBook(Model model, @PathVariable int id) {
        Book book = bookService.select(id);
        System.out.println(book);
        model.addAttribute("book",book);
        return "updateBook";
    }

    @RequestMapping("/updateBook")
    public String updateBook(@RequestParam("file") CommonsMultipartFile file,String bookID, String bookName,
                             String bookCount, String detail,String image, HttpServletRequest request) throws IOException {


        //获取文件名 : file.getOriginalFilename();
        String uploadFileName = file.getOriginalFilename();

        //如果文件名不为空，上传
        if (!"".equals(uploadFileName)){
            //上传路径保存设置
            String path = "C:\\Users\\lishuai\\IdeaProjects\\SpringMVC\\ssm-demo\\web\\static\\img\\";
            //如果路径不存在，创建一个
            File realPath = new File(path);
            if (!realPath.exists()){
                realPath.mkdir();
            }
            System.out.println("上传文件保存地址："+realPath);

            InputStream is = file.getInputStream(); //文件输入流
            OutputStream os = new FileOutputStream(new File(realPath,bookName+".jpg")); //文件输出流

            //读取写出
            int len=0;
            byte[] buffer = new byte[1024];
            while ((len=is.read(buffer))!=-1){
                os.write(buffer,0,len);
                os.flush();
            }
            os.close();
            is.close();
        }
        System.out.println("上传文件名 : "+uploadFileName);


        Book book = new Book(Integer.parseInt(bookID),bookName,Integer.parseInt(bookCount),detail,bookName+".jpg");
        bookService.update(book);
        return "redirect:/allBook/0";
    }

    @RequestMapping("/del/{bookId}")
    public String deleteBook(@PathVariable int bookId) {
        bookService.delete(bookId);
        return "redirect:/allBook/0";
    }

    @RequestMapping("/find")
    public String find(String info,Model model) {
        List<Book> books;
        if (info==null){
            books = bookService.queryAll(0);
        }
        else{
            books = bookService.find("%" + info + "%");
        }
        model.addAttribute("list",books);
        return "allBook";
    }

    @RequestMapping("/error")
    public String error() {
        int i = 10/0;
        return null;
    }


}
