package com.example.springkadaiform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {

    // GET /form : 初期表示
    @GetMapping("/form")
    public String showForm(@ModelAttribute("contactForm") ContactForm form) {
        // @ModelAttribute で空の contactForm をModelへ自動載せ
        return "contactFormView";
    }

    // POST /form : バリデーション → NGなら同画面、OKなら /confirm へリダイレクト（PRG）
    @PostMapping("/form")
    public String submit(
            @Validated @ModelAttribute("contactForm") ContactForm form,
            BindingResult bindingResult,
            RedirectAttributes ra) {

        if (bindingResult.hasErrors()) {
            return "contactFormView";
        }
        ra.addFlashAttribute("contactForm", form);
        return "redirect:/confirm";
    }

    // GET /confirm : 確認画面（直アクセス時は /form へ戻す）
    @GetMapping("/confirm")
    public String confirm(@ModelAttribute("contactForm") ContactForm form, Model model) {
        if (!model.containsAttribute("contactForm") || form.getName() == null) {
            return "redirect:/form";
        }
        return "confirmView";
    }

    // ルート -> /form
    @GetMapping("/")
    public String root() { return "redirect:/form"; }
}
