package org.example;

// Proxy Pattern의 Proxy 부가적인 기능(접근 제한, 로깅, 트랜잭션, 등)을 제공
public class BookServiceProxy implements BookService {

    BookService bookService;

    public BookServiceProxy(BookService bookService){
        this.bookService = bookService;
    }

    @Override
    public void rent(Book book) {
        System.out.println("db connection");
        bookService.rent(book);
        System.out.println("db disconnection");
    }

    @Override
    public void buy(Book book) {
        System.out.println("log init");
        bookService.buy(book);
        System.out.println("log end");
    }


}
