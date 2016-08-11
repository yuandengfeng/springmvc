package com.controller;

/**
 * Created by Administrator on 2016/2/11.
 */

import com.mvc.pro.MUser;
import com.mvc.service.MUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user.do")
@SessionAttributes({"u","a"}) //将ModeMap中属性名字为U，a的放入session中。这样request和session中都有了
public class MUsercontroller {

    @Autowired
    private MUserService userService;

    @RequestMapping(params = "method=reg1")
//    @RequestMapping("/reg.do")
    public String reg1(String uname)
    {
        System.out.println("MuserControllerreg1");
        userService.add(uname);
        return "index";
    }

    @RequestMapping(params = "method=reg2")
    public ModelAndView reg2(MUser user)
    {
        System.out.println("MuserControllerreg2");
        System.out.println(user.getUname());
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping(params = "method=reg3")
    public String reg3(@RequestParam("uname")String uu,HttpServletRequest req,ModelMap map)
    {
        System.out.println("MuserControllerreg3");
        map.put("a","aaaa");
        req.getSession().setAttribute("c","cc");
        userService.add(uu);
        return "index";
    }
   @RequestMapping(params = "method=reg4")
   public String reg4(@RequestParam("uname")String uu,HttpServletRequest req,ModelMap map)
   {
       System.out.println("MuserControllerreg4");
       map.put("a","aaaa");
       map.put("u","uuuu");
       req.getSession().setAttribute("c","cccc");
       userService.add(uu);
       return "index";
   }

    @RequestMapping(params = "method=reg5")
    //@ModelAttribute可以将ModelMap中属性的值通过该注解自动赋给指定变量
    public String reg5(@ModelAttribute("u")String u,ModelMap map)
    {
        System.out.println("MuserControllerreg5");
        System.out.println(u);
        return "redirect:http://localhost:8080"; //重定向
    }

    @RequestMapping(params = "method=reg6")
    public ModelAndView reg6(String uname)
    {
       ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        MUser u = new MUser();
        u.setUname("光谷");
        MUser u2 = new MUser();
        u2.setUname("世界");
//        mv.addObject(u);//直接放入对象，属性名为首字母小写的类名。一般建议手动增加属性名称
        mv.addObject(u);
        mv.addObject("uu",u2);
        return mv;  //在request中获取值

    }

}
