package Adapter;

import Adapter.inheritance.PrintBanner;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdapterTest {

    private OutputStream out;

    void initOutput() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    void adapterByInheritance() {
        Printable printBanner = new PrintBanner("Hello");
        Banner api = new Banner("Hello");

        initOutput();
        printBanner.printWithParen();
        printBanner.printWithStar();
        String printBannerOutput = out.toString();

        initOutput();
        api.showWithParen();
        api.showWithAster();
        String apiBannerOutput = out.toString();

        assertEquals(printBannerOutput, apiBannerOutput);
    }

    @Test
    void adapterByComposition() {
        Banner api = new Banner("Hello");
        Printable printBanner = new Adapter.composition.PrintBanner(api);

        initOutput();
        printBanner.printWithParen();
        printBanner.printWithStar();
        String printBannerOutput = out.toString();

        initOutput();
        api.showWithParen();
        api.showWithAster();
        String apiBannerOutput = out.toString();

        assertEquals(printBannerOutput, apiBannerOutput);
    }
}
