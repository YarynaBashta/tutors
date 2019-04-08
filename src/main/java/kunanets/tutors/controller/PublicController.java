package kunanets.tutors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PublicController {


//    @RequestMapping("/sub")
//    public String subjectPage() {
//        return "subject.html";
//    }

    @RequestMapping("/upload")
    public String imagePage() {
        return "index.html";
    }

    @RequestMapping("/home")
    public String mainPage() {  return "subject.html";
    }
    @RequestMapping("/sign")
    public String signPage() {
        return "index.html";
    }

    @RequestMapping("/lev")
    public String levelsPage() {
        return "levels.html";
    }

    @RequestMapping("/sub")
    public String subjectPage() {
        return "subject.html";
    }

}