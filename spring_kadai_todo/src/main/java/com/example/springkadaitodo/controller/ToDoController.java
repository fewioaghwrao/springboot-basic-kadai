package com.example.springkadaitodo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springkadaitodo.entity.ToDo;
import com.example.springkadaitodo.service.ToDoService;

@Controller
@RequestMapping("/todo")
public class ToDoController {
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public String adminUser(Model model) {
        // 最新のユーザーリストを取得
        List<ToDo> todos = toDoService.getAllTodos();

        // ビューにユーザーリストを渡す
        model.addAttribute("todos", todos);

        return "todoView";
    }
}
