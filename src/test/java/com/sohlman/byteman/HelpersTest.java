package com.sohlman.byteman;


import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.Cookie;

public class HelpersTest {


    @Before
    public void setUp() {

    }

    @Test
    public void test() {
        Cookie cookie1 = new Cookie("eka","toka");
        cookie1.setPath("/hello");
        Cookie cookie2 = new Cookie("eka2","toka3");
        cookie2.setPath("/hello");
        System.out.println(Helpers.cookiesToJson(new Cookie[] {cookie1, cookie1}));

    }
}
