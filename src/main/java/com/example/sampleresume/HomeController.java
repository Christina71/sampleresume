package com.example.sampleresume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    MyresumeRepository myresumeRepository;

    @RequestMapping("/")
    public String listMyresumes(Model model){
        model.addAttribute("myresumes",myresumeRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String myresumeForm(Model model){
        model.addAttribute("myresume", new Myresume());
        return "myresumeform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Myresume myresume, BindingResult result)
    {
        if (result.hasErrors()){
            return "myresumeform";
        }
        myresumeRepository.save(myresume);
        return "redirect:/";
    }
        
    @RequestMapping("/detail/{id}")
    public String showMyresume(@PathVariable ("id") long id, Model model){
        model.addAttribute("myresume", myresumeRepository.findOne(id));
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateMyresume(@PathVariable ("id") long id, Model model){
        model.addAttribute("myresume", myresumeRepository.findOne(id));
        return "myresumeform";

    }
    /*@RequestMapping("/delete/{id}")
    public String delMyresume(@PathVariable ("id") long id){
       myresumeRepository.delete(id);
        return "redirect:/";
    }*/
}
