package com.example.kyrsovaia.controllers;

import com.example.kyrsovaia.models.Zakaz;
import com.example.kyrsovaia.repositories.ZakazRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

import static com.example.kyrsovaia.EmailService.SendingEmail;

@Controller
public class ZakazController {
    @Autowired
    private ZakazRepository zakazRepository;
    private String List;

    @GetMapping("/zapis")
    public String ZapisPage( Model model) {
        Iterable<Zakaz> zakaz = zakazRepository.findAll();
        model.addAttribute("zakaz", zakaz);
        return "zapis";
    }
    @PostMapping("/zapis")
    public String add(@RequestParam String name, @RequestParam String email, @RequestParam String number, @RequestParam String datepicker) throws MessagingException, IOException {
        Zakaz zakaz = new Zakaz(name, number, email,datepicker);

        zakazRepository.save(zakaz);
        SendingEmail(email, name);

        return "zapis";
    }
    @PostMapping("/auth")
    public String add(@RequestParam String username, @RequestParam String password, Model model) throws MessagingException, IOException {
        Iterable<Zakaz> zakaz = zakazRepository.findAll();
        model.addAttribute("zakaz", zakaz);
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://ec2-63-32-248-14.eu-west-1.compute.amazonaws.com:5432/da2ics1jud0o5l?sslmode=require";
            String login = "pcfwdokavhrggy";
            String passworddb = "ddfcbe9141fa16c1851fb6009bc6418e8dc054b1dca5d4a2f34905a0a3302e33";

            Connection con = DriverManager.getConnection(url, login, passworddb);
            try {
                Statement stmt = con.createStatement();
                Statement stmt1 = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT username FROM data_vladelets");
                ResultSet ps = stmt1.executeQuery("SELECT password FROM data_vladelets");
                while (rs.next() && ps.next()) {

                    String name = rs.getString("username") ;

                    String pass =ps.getString("password") ;
                    if ((Objects.equals(name, username)) && (Objects.equals(pass, password))){

                        List = "data_priema_zakaza";
                        break;
                         }
                    else {List = "auth";};
                }
                rs.close();
                stmt.close();
                ps.close();
                stmt1.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return List;
    }
}
