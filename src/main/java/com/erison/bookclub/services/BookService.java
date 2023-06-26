package com.erison.bookclub.services;

import com.erison.bookclub.models.Book;
import com.erison.bookclub.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public Book findById(Long id){
        Optional<Book>result = bookRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        return null;
    }


    public List<Book>all(){
        return bookRepository.findAll();
    }

    public Book createB(Book book){
        return bookRepository.save(book);
    }
    public Book updateB(Book book){
        return bookRepository.save(book);
    }

    public void delete(Long id){
        bookRepository.deleteById(id);
    }
}
