package com.zl.blog.controller;

import com.zl.blog.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author zl
 * @version 1.0
 * @date 2020/1/29 21:34
 */
public class IndexController {
    @Autowired
    private UserService userService;


    @RequestMapping("/register")
    public String register(Model model, HttpServletResponse httpResponse,
                           @RequestParam String username, @RequestParam String password
            ,@RequestParam(value = "next",required = false)String next){
        Map<String,String> map = userService.register(username,password);
        if(map.containsKey("ticket")){
            Cookie cookie = new Cookie("ticket",map.get("ticket"));
            cookie.setPath("/");
            httpResponse.addCookie(cookie);
            if(StringUtils.isNotBlank(next)){
                return "redirect:"+next;
            }
            return "redirect:/";
        }else {
            model.addAttribute("msg", map.get("msg"));
            return "login";
        }
    }

    @RequestMapping("/login")
    public String login(Model model, HttpServletResponse httpResponse, @RequestParam String username,@RequestParam String password,@RequestParam(value = "next",required = false)String next){
        Map<String,String> map = userService.login(username,password);
        if(map.containsKey("ticket")){
            Cookie cookie = new Cookie("ticket", map.get("ticket"));
            cookie.setPath("/");
            httpResponse.addCookie(cookie);
            if(StringUtils.isNotBlank(next)){
                return "redirect:"+next;
            }
            return "redirect:/";
        }else {
            model.addAttribute("msg", map.get("msg"));
            return "login";
        }
    }
}
