package com.tanthanh.bookservice.query.projection;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tanthanh.bookservice.command.data.Book;
import com.tanthanh.bookservice.command.data.BookRepository;
import com.tanthanh.bookservice.query.model.BookResponseModel;
import com.tanthanh.bookservice.query.queries.GetAllBookQuery;
import com.tanthanh.bookservice.query.queries.GetBookQuery;

@Component
public class BookProjection {
  @Autowired
  private BookRepository bookRepository;
  
  @QueryHandler
  public BookResponseModel handle(GetBookQuery getBooksQuery) {
	  BookResponseModel model = new BookResponseModel();
	  Book book = bookRepository.getById(getBooksQuery.getBookId());
	  BeanUtils.copyProperties(book, model);
	  return model;
  }
  
  @QueryHandler
  public List<BookResponseModel> handle(GetAllBookQuery getAllBooksQuery){
	  List<Book> listEntity = bookRepository.findAll();
	  List<BookResponseModel> listBook = new ArrayList();
	  listEntity.forEach(item ->{
		  BookResponseModel model = new BookResponseModel();
		  BeanUtils.copyProperties(item, model);
		  listBook.add(model);
		  
	  });
	  
	  return listBook;
  }
   
}
