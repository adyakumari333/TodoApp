package com.in28minutes.springboot.TodoApp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.in28minutes.springboot.TodoApp.model.Todo;
import com.in28minutes.springboot.TodoApp.model.User;
import com.in28minutes.springboot.TodoApp.repository.TodoRepository;
import com.in28minutes.springboot.TodoApp.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;
    
    @Autowired
    private UserRepository userRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    @GetMapping("/todos")
    public String listTodos(Model model, Authentication authentication) {
        String username = getLoggedinUsername(); // Get logged-in username
        List<Todo> todos = todoRepository.findByUsername(username);
        model.addAttribute("todos", todos);
        return "todos";
    }

    @GetMapping("/todos/add")
    public String showAddTodoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "add-todo";
    }

    @PostMapping("/todos")
    public String addTodo(@Valid @ModelAttribute Todo todo, BindingResult bindingResult, 
                          Authentication authentication, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            error -> error.getField(),
                            error -> error.getDefaultMessage()
                    )));
            return "add-todo";
        }

        // Set the username from the authenticated user
        String username = getLoggedinUsername();
        todo.setUsername(username);

        // Retrieve the user object associated with the logged-in username
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            todo.setUser(userOptional.get());  // Set the user for the todo
        } else {
            // Handle the case where the user is not found (you can log or throw an exception)
            return "redirect:/todos";
        }

        // Ensure targetDate is set, default to current date if null
        if (todo.getTargetDate() == null) {
            todo.setTargetDate(new Date());
        }
        
        todo.setDone(false);

        todoRepository.save(todo);  // Save the new todo
        return "redirect:/todos";
    }

    @GetMapping("/todos/edit/{id}")
    public String showEditTodoForm(@PathVariable Long id, Model model, Authentication authentication) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();

            // Ensure the logged-in user owns the todo
            if (!todo.getUsername().equals(getLoggedinUsername())) {
                return "redirect:/todos";
            }

            model.addAttribute("todo", todo);
            return "edit-todo";
        }
        return "redirect:/todos";
    }

    @PostMapping("/todos/update/{id}")
    public String updateTodo(@PathVariable Long id, @Valid @ModelAttribute Todo todo, 
                             BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "edit-todo";
        }

        // Ensure the username remains unchanged and is associated with the logged-in user
        todo.setId(id);
        todo.setUsername(getLoggedinUsername());

        // Retrieve the user object associated with the logged-in username
        Optional<User> userOptional = userRepository.findByUsername(getLoggedinUsername());
        if (userOptional.isPresent()) {
            todo.setUser(userOptional.get());  // Ensure user is set before saving
        } else {
            // Handle the case where the user is not found (you can log or throw an exception)
            return "redirect:/todos";
        }

        todoRepository.save(todo);  // Save the updated todo
        return "redirect:/todos";
    }

    @GetMapping("/todos/delete/{id}")
    public String deleteTodo(@PathVariable Long id, Authentication authentication) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();

            // Ensure the logged-in user owns the todo
            if (!todo.getUsername().equals(getLoggedinUsername())) {
                return "redirect:/todos";
            }

            todoRepository.deleteById(id);
        }
        return "redirect:/todos";
    }

    private String getLoggedinUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}

