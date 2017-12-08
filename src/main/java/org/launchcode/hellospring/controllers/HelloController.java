package org.launchcode.hellospring.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping(value="hello")
public class HelloController {

    @RequestMapping()
    @ResponseBody
    public String index(HttpServletRequest request){
        String name = request.getParameter("name");

        if (name == null) {
            name = "World";
        }

        return "Hello " + name;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloForm(){
        String html = "<form method='post'>" +
                          "<input type='text' name='name' />"  +
                          "<input type='submit' value='Greet Me!' />" +
                      "</form>";
        return html;
    }

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    @ResponseBody
    public String helloPost(HttpServletRequest request) {
        String name = request.getParameter("name");
        return "Hello " + name;
    }

    @RequestMapping("/language")
    @ResponseBody
    public String languageForm(){
        String html =
                "<form method='post' action='/messageLanguage'>" +
                    "<input type=text name=name></>" +
                    "&nbsp; &nbsp" +
                    "<select name='language' size='0'" +
                        "<option value='Bonjour'>French</option>" +
                        "<option value='Hola'>Spanish</option>" +
                        "<option value='Salaam' >Persian (Farsi)</option>" +
                        "<option value='Marhaba'>Arabic</option>" +
                        "<option value='Ni Hau'>Mandarin</option>" +
                        "<option value=Hi selected>English</option>" +
                    "</select>" +
                    "&ensp;" +
                    "<input type=submit value='Greet me in the language!'></>" +
                "</form>";
        return html;
    }

    @RequestMapping(value="messageLanguage", method = RequestMethod.POST)
    @ResponseBody
    public static String createMessage(HttpServletRequest request){
        String name = request.getParameter("name");
        String language = request.getParameter("language");
        return language + " " + name + "!";
    }

    @RequestMapping("hello/{name}")
    @ResponseBody
    public String helloUrlSegment(@PathVariable String name){
        return "Hello " + name;
    }

    @RequestMapping("goodbye")
//    @ResponseBody
    public String goodbye() {
        /*return "Goodbye";*/
        return "redirect:";

    }
}
