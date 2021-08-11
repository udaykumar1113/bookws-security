package com.uday.api.bookws.book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/books")
public class BookController {
    @GetMapping(path = "/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable String bookId) {

        Book book = new Book(bookId, UUID.randomUUID().toString(), "API Security",
                "SKB Publishers", "01-02-2010");

        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
