package com.erison.bookclub.controllers;

import com.erison.bookclub.models.Book;
import com.erison.bookclub.models.User;
import com.erison.bookclub.models.UserLogin;
import com.erison.bookclub.services.BookService;
import com.erison.bookclub.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;


    @GetMapping("/")
    public String home(Model model , @ModelAttribute( "newUser") User newUser,
                       @ModelAttribute("newLogin")User newLogin , HttpSession session){
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        if (loggedInUserId!=null){
            return "redirect:/home";
        }
        model.addAttribute("newUser",new User());
        model.addAttribute("newLogin",new UserLogin());
        return "index";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser")User newUser , BindingResult result,
                           Model model, HttpSession session){
        userService.register(newUser,result);
        if (result.hasErrors()){
            model.addAttribute("newLogin",new UserLogin());
            return "index";
        }
        session.setAttribute("loggedInUserId",newUser.getId());
        return "redirect:/home";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin")UserLogin newLogin,BindingResult result
            ,Model model,HttpSession session){
        User user = userService.login(newLogin,result);
        if (result.hasErrors()){
            model.addAttribute("newUser",new User());
            return "index";
        }
        session.setAttribute("loggedInUserId",user.getId());
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String dashboard(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model){
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        if (loggedInUserId == null){
            return "redirect:/";
        }
        User loggedInUser = userService.findUser(loggedInUserId);
        model.addAttribute("user",loggedInUser);

        // this code prevent user going back at dashboard after logout

        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        return "home";
    }

    //book controller


    @GetMapping("/home")
    public String home(HttpSession session , Model model){
        if (session.getAttribute("loggedInUserId")==null){
            return "redirect:/";
        }
        model.addAttribute("books",bookService.all());
        model.addAttribute("user",userService.findUser((Long) session.getAttribute("loggedInUserId")));
        return "home";
    }

    @GetMapping("/books/new")
    public String newB(@ModelAttribute("book")Book book,HttpSession session, Model model){
    User user = userService.findUser((Long) session.getAttribute("loggedInUserId"));
    model.addAttribute("user",user);
    return "new";
    }

    @PostMapping("/books")
    public String createB(@Valid @ModelAttribute("book")Book book,BindingResult result){

        if (result.hasErrors()){
            return "new";
        }
        bookService.createB(book);
        return "redirect:/home";
    }

    @GetMapping("/books/{id}")
    public String bookDetail(Model model, @PathVariable("id")Long id,HttpSession session){

        if (session.getAttribute("loggedInUserId")==null){
            return "redirect:/home";
        }
        Book book = bookService.findById(id);
        model.addAttribute("book",book);
        model.addAttribute("user",userService.findUser((Long) session.getAttribute("loggedInUserId")));
        return "book";
    }

    @GetMapping("/books/{id}/edit")
    public String theEditBook(Model model, @PathVariable("id")Long id,HttpSession session) {

        if (session.getAttribute("loggedInUserId") == null) {
            return "redirect:/home";

        }
        Book bookE = bookService.findById(id);
        model.addAttribute("bookE",bookE);
        return "editBook";
    }

    @PutMapping("/books/{id}/update")

    public String update(@Valid @ModelAttribute("bookE")Book bookE ,BindingResult result,Model model,
                         @PathVariable("id")Long id,HttpSession session) {

        Long userId = (Long) session.getAttribute("loggedInUserId");
        User userLogged = userService.findUser(userId);
        if (result.hasErrors()) {
            return "editBook";

        }else {
            bookE.setUser(userLogged);
            bookService.updateB(bookE);
            return "redirect:/home";
        }

    }

    @DeleteMapping("/books/{id}/delete")
    public String delete(@PathVariable("id")Long id,HttpSession session){

        bookService.delete(id);
        return "redirect:/home";

    }

        @RequestMapping("/logout")
        public String logout (HttpServletRequest request, HttpSession session ){
            session.invalidate();
            return "redirect:/";
        }
    }

