package org.example;

// Proxy Pattern 에서 리얼 서브젝트(해야할 일)
public class DefaultBookService implements BookService {

    @Override
    public void rent(Book book){
        System.out.println("book title = " + book.getTitle());
    }

    @Override
    public void buy(Book book) {
        System.out.println("buy title = " + book.getTitle());
    }

}
