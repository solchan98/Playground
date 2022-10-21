package Adapter.composition;

import Adapter.Banner;
import Adapter.Printable;

public class PrintBanner implements Printable {

    private final Banner banner;

    public PrintBanner(Banner banner) {
        this.banner = banner;
    }

    @Override
    public void printWithParen() {
        banner.showWithParen();
    }

    @Override
    public void printWithStar() {
        banner.showWithAster();
    }
}
