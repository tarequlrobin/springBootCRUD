package com.tarequlrobin.springBootCRUD.controller;

import com.tarequlrobin.springBootCRUD.model.Customer;
import com.tarequlrobin.springBootCRUD.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    private CustomerService customerService;

    public HomeController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String home(@RequestParam(value = "name", required = false, defaultValue = "") String name
            , Model model) {
        List<Customer> customers = customerService.findAllCustomer();
        model.addAttribute("customers", customers);
        return "home";
    }

    @GetMapping("/create")
    public String addCustomer(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "create";
    }

    @PostMapping("/save")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model){
        if(bindingResult.hasErrors()){
            return "/create";
        }

        redirectAttributes.addFlashAttribute("message", "Customer saved successfully");

        customerService.saveCustomer(customer);
        return "redirect:/";
    }

    @GetMapping("customer/{id}/delete")
    public String deleteCustomerById(@PathVariable Long id, RedirectAttributes redirectAttributes){
        customerService.deleteCustomerById(id);
        redirectAttributes.addFlashAttribute("message", "Customer deleted successfully!");
        return "redirect:/";
    }

    @GetMapping("customer/{id}/edit")
    public String updateCustomer(@PathVariable long id, Model model){
        Customer customer = customerService.findCustomerById(id).orElse(new Customer());
        model.addAttribute("customer", customer);
        //customerService.saveCustomer(customer);
        return "/create";
    }
}
