package com.example.mybatisglobalparams;

public class CountryCodeContext {
    private static final ThreadLocal<String> countryCodeHolder = new ThreadLocal<>();

    public static void setCountryCode(String countryCode) {
        countryCodeHolder.set(countryCode);
    }

    public static String getCountryCode() {
        return countryCodeHolder.get();
    }

    public static void clear() {
        countryCodeHolder.remove();
    }
}