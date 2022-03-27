package org.example;

import net.bytebuddy.ByteBuddy;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class BookServiceProxyServiceTest {

    //BookService bookService = new BookServiceProxy(new DefaultBookService());

    //Dynamic Proxy 런타임에 특정 인터페이스들을 구현하는 클래스 또는 인스턴스를 만드는 기술 (**인터페이스 타입만 가능함**)
    /*BookService bookService = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class},
            new InvocationHandler() {
                BookService bookService = new DefaultBookService(); // 리얼 서브젝트(실제 해야하는 일)

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if( method.getName().equals("rent")) { // method 이름이 rent 인것만 적용되고
                        System.out.println("DB connection");
                        Object invoke = method.invoke(bookService, args);
                        System.out.println("DB disconnection");
                        return invoke;
                    }

                    return method.invoke(bookService, args); // 나머지는 그대로 뱉는다
                    // 단점 : 유연하지 못하다 계속 생길때마다 위에처럼 만들어줘야 하기 때문에
                }
            });
    // 프록시 인스턴스 만들기
    */

    // 인터페이스 말고 클래스의 프록시가 필요할 때 (cglib 라이브러리 이용)
    /*MethodInterceptor handler = new MethodInterceptor() {
        DefaultBookService defaultBookService = new DefaultBookService();
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            if(method.getName().equals("rent")){
                System.out.println("DB Connection");
                Object invoke = method.invoke(defaultBookService, objects);
                System.out.println("DB Disconnection");
                return invoke;
            }

            return method.invoke(defaultBookService, objects);
        }
    };

    BookService bookService = (BookService) Enhancer.create(BookService.class, handler);*/

    @Test
    public void di() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends DefaultBookService> proxyClass = new ByteBuddy().subclass(DefaultBookService.class)
                .make().load(BookService.class.getClassLoader()).getLoaded();
        DefaultBookService bookService = proxyClass.getConstructor(null).newInstance(); // Class 오브젝트에서 인스턴스를 만드는 방법

        Book book = new Book();
        book.setTitle("spring");
        bookService.rent(book);
    }
}