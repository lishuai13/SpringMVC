package service;

import entity.Book;

import java.util.List;
import java.util.Map;

public interface BookService {

    List<Book> queryAll();

    Book select(int id);

    int delete(int id);

    int add(Book book);

    int update(Book book);
}
