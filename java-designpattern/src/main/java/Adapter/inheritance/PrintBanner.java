package Adapter.inheritance;

import Adapter.Banner;
import Adapter.Printable;

/**
 * 어댑터 역할
 * */
public class PrintBanner extends Banner implements Printable {

    public PrintBanner(String content) {
        super(content);
    }

    @Override
    public void printWithParen() {
        this.showWithParen();
    }

    @Override
    public void printWithStar() {
        this.showWithAster();
    }
}
