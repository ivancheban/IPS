package com.servlets.customer;

import com.dto.TariffDto;
import com.model.Customer;
import com.service.CustomerService;
import com.service.CustomerServiceImpl;
import com.service.TariffService;
import com.service.TariffServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/user/cabinet")
public class PersonalCabinetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TariffService tariffService = new TariffServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();

        String phone = (String) req.getSession().getAttribute("phone");
        int customerId = customerService.findByPhoneNumber(phone).getId();

        Customer customer = customerService.findByPhoneNumber(phone);

        String fullName = customer.getName() + " " + customer.getSurname();
        String phoneNumber = customer.getPhone();
        String email = customer.getEmail();
        int balance = customer.getBalance();

        req.getSession().setAttribute("customerId", customerId);
        req.getSession().setAttribute("fullName", fullName);
        req.getSession().setAttribute("phoneNumber", phoneNumber);
        req.getSession().setAttribute("email", email);
        req.getSession().setAttribute("balance",balance);

        List<TariffDto> tariffDtoList = tariffService.findAllSubscription(customerId);
        req.getSession().setAttribute("tariffsSubscriptions", tariffDtoList);

        resp.sendRedirect("/personal-cabinet.jsp");
    }

}
