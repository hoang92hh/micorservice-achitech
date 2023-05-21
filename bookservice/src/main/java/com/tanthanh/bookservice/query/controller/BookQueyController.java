package com.tanthanh.bookservice.query.controller;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanthanh.bookservice.query.model.BookResponseModel;
import com.tanthanh.bookservice.query.queries.GetAllBookQuery;
import com.tanthanh.bookservice.query.queries.GetBookQuery;


@RestController
@RequestMapping("/api/v1/books")
public class BookQueyController {
	@Autowired
	private QueryGateway queryGateWay;
	
	@GetMapping("/{bookId}")
	public BookResponseModel getBookDetail(@PathVariable String bookId) {
		GetBookQuery gettBooksQuery = new GetBookQuery();
		gettBooksQuery.setBookId(bookId);
		
		BookResponseModel bookResponseModel =
				 queryGateWay.query(gettBooksQuery, ResponseTypes.instanceOf(BookResponseModel.class))
				.join();
		return bookResponseModel;
	}
	
	@GetMapping
	public List<BookResponseModel> getAllBooks(){
		GetAllBookQuery getAllBooksQuery = new GetAllBookQuery();
		List<BookResponseModel> list = 
				queryGateWay.query(getAllBooksQuery, ResponseTypes.multipleInstancesOf(BookResponseModel.class))
				.join();
		return list;
	
	}
	
    

}
