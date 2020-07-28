package dao;

import entity.Book;

import java.util.List;

public interface BookMapper {

    List<Book> queryAll(int number);

    Book select(int id);

    int delete(int id);

    int add(Book book);

    int update(Book book);

    List<Book> find(String info);
}
