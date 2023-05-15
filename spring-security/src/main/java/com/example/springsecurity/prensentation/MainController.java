package com.example.springsecurity.prensentation;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Controller
public class MainController {

    @GetMapping("/")
    public void intro(
            HttpServletResponse response,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws IOException {
        response.setContentType(MediaType.TEXT_HTML_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter writer = response.getWriter();
        writer.write("<html lang=\"kr\">");
        writer.write("<body>");
        writer.write("<div>" +  userDetails.getUsername() + "님 안녕하세요!" + "</div>");
        writer.write("<html>");
    }

    @GetMapping("/admin")
    public void adminIntro(
            HttpServletResponse response,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws IOException {
        response.setContentType(MediaType.TEXT_HTML_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter writer = response.getWriter();
        writer.write("<html lang=\"kr\">");
        writer.write("<body>");
        writer.write("<h3>관리자 페이지</h3>");
        writer.write("<div>" +  userDetails.getUsername() + " 관리자님 안녕하세요!" + "</div>");
        writer.write("<html>");
    }

    @GetMapping("/seller")
    public void sellerIntro(
            HttpServletResponse response,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws IOException {
        response.setContentType(MediaType.TEXT_HTML_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter writer = response.getWriter();
        writer.write("<html lang=\"kr\">");
        writer.write("<body>");
        writer.write("<h3>판매자 페이지</h3>");
        writer.write("<div>" +  userDetails.getUsername() + " 판매자님 안녕하세요!" + "</div>");
        writer.write("<html>");
    }

    @GetMapping("/customer")
    public void customerPage(
            HttpServletResponse response,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws IOException {
        response.setContentType(MediaType.TEXT_HTML_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter writer = response.getWriter();
        writer.write("<html lang=\"kr\">");
        writer.write("<body>");
        writer.write("<h3>구매자 페이지</h3>");
        writer.write("<div>" +  userDetails.getUsername() + "님 안녕하세요!" + "</div>");
        writer.write("<html>");
    }
}
