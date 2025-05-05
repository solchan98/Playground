package com.example.redislettuce.domain;

import com.example.redislettuce.common.Cacheable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Cacheable {

    private String isbn;

    private String title;
    private String author;
    private String description;
    private String image;
    private String category;

    private int price;

    private String publisher;
    private String publishedAt;

    @Override
    public String toString() {
        return """
                책명       : %s
                저자       : %s
                ISBN       : %s
                설명       : %s
                이미지     : %s
                카테고리   : %s
                가격       : %d원
                출판사     : %s
                출판일     : %s
                """.formatted(title, author, isbn, description, image, category, price, publisher, publishedAt);
    }
}
