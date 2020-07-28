package service;

import dao.BookMapper;
import entity.Book;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public class BookServiceImpl implements BookService{

    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper=bookMapper;
    }

    @Cacheable(value = "testCache")
    @Override
    public List<Book> queryAll(int number) {
        return bookMapper.queryAll(number);
    }

    @Override
    public Book select(int id) {
        return bookMapper.select(id);
    }

    @Override
    public int delete(int id) {
        return bookMapper.delete(id);
    }

    @Override
    public int add(Book book) {
        return bookMapper.add(book);
    }

    @Override
    public int update(Book book) {
        return bookMapper.update(book);
    }

    @Override
    public List<Book> find(String info) {
        return bookMapper.find(info);
    }


}
