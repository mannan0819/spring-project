package com.example.demo;

import com.example.demo.dao.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.*;
import com.example.demo.model.Ticket;
import com.example.demo.model.TicketGroup;
import com.example.demo.model.User;

import javassist.tools.rmi.ObjectNotFoundException;

@Controller
public class HomeController {
	
	private static Map<String, User> usrrepo = new HashMap<>();
		
	@Autowired
	UserRepository usrRepo;
	
	@Autowired
	TicketRepository ticketRepo;
	
	@Autowired
	TicketGrpRepo ticketGrpRepo;
	
	
	@RequestMapping (value="/sec")
	public String sec(Authentication auth, Model model) {
		myUserDetails usrDetail = (myUserDetails) auth.getPrincipal();
		model.addAttribute("Name", usrDetail.getFirtname());
		return "seccheck";
	}
	
	
	@RequestMapping(value ="/")
	public String index(Model model) {
		model.addAttribute("ticketGrp", ticketGrpRepo.findAll());
		//System.out.println(ticketRepo.findAll());
		//System.out.println("Index page");
		return "index";
	}
	
	@RequestMapping(value = "/home")
	public String Home () {
		System.out.println("HOME CONTROLLER REACHED");
		//String s = ms.lstUser();
		return "home";
	}
	
	@RequestMapping(value = "/logi")
	public String logi(Model model) {
		System.out.println("HOME CONTROLLER REACHED");
		List<TicketGroup> t = ticketGrpRepo.findAll();
		List<Ticket> ti = ticketRepo.findAll();
		System.out.println(ticketGrpRepo.findAll());
		System.out.println(ticketRepo.findAll());
		System.out.println(t.get(0).getName());
		System.out.println(ti.get(0).getTitle());
		model.addAttribute("tick", ti);
		//String s = ms.lstUser();
		return "log";
	}
	
	@RequestMapping(value = "/test")
	public User tes() {
		System.out.println("HOME CONTROLLER REACHED");
		return new User("Mannan", "Arain");
	}
	
	@RequestMapping(value = "/log")
	public String log(Model model) {
		System.out.println("log reached");
		model.addAttribute("ticket", new Ticket());
		return "create-ticket";
	}
	
	
	@RequestMapping(value = "/cticket/{Id}")
	public String createTicket(@PathVariable Long Id, Model model) {
		System.out.println("crete tic reached");
		model.addAttribute("ticket", new Ticket());
		model.addAttribute("groupid", Id);
		return "create-ticket";
	}
	
	
	@PostMapping (value = "/cticket")
	public String submitTicket (@ModelAttribute Ticket ticket, Model model) {
		model.addAttribute("ticket", ticket);
		ticket.setCreated(LocalDate.now());
		ticket.setEdited(LocalDate.now());
		ticketRepo.save(ticket);
		//System.out.println(ticket);
		return "redirect:/";
	}
	
	@RequestMapping(value="/ticket/{Id}")
	public String ticketDetail(@PathVariable Long Id, Model model) {
		model.addAttribute("ticket", ticketRepo.findById(Id));
		return "ticket-detail";
	}
	
	@RequestMapping(value = "/dticket/{Id}")
	public String dTicket(@PathVariable Long Id, Model model) {
		System.out.println("crete tic reached");
		Ticket tic = ticketRepo.findById(Id).get();
		model.addAttribute("ticket", tic);
		//System.out.println(tic);
		return "deticket";
	}
	
	@RequestMapping(value = "/group/{Id}")
	public String GroupId(@PathVariable Long Id, Model model){	
		System.out.println("The number is " + Id);

		List<TicketGroup> t = ticketGrpRepo.findAll();
		List<Ticket> ti = ticketRepo.findAll();
		List<Ticket> tiG = ticketRepo.findAllByGroupid(Id);
		
		model.addAttribute("tics", tiG);
		model.addAttribute("groupid", Id);
		//System.out.println(Id);
		//System.out.println(ti);
		//System.out.println(tiG);
		return "tickets";
	}
	
	@RequestMapping(value="/deletetic/{Id}")
	public String DeleteTicket(@PathVariable Long Id, Model model) {
		Ticket tic = ticketRepo.findById(Id).get();
		model.addAttribute("ticket", tic);
		return "deleteticket";
	}
	
	@PostMapping(value="/deletetic/{Id}")
	public String DeleteTicketPost(@PathVariable Long Id, Model model) {
		Ticket tic = ticketRepo.findById(Id).get();
		Long grpId = tic.getGroupid();
		ticketRepo.deleteById(Id);
		return "redirect:/group/" + grpId;
	}
	
	
	@RequestMapping(value="/edittic/{Id}")
	public String EditTicket(@PathVariable Long Id, Model model) {
		Ticket tic = ticketRepo.findById(Id).get();

		model.addAttribute("ticket", tic);
		System.out.println("edit called " + tic);
		return "edit-ticket";
	}
	
	@PostMapping(value="/edittic")
	public String PostEditTicket(@ModelAttribute Ticket ticket, Model model) {
		model.addAttribute("ticket", ticket);
		ticket.setEdited(LocalDate.now());
		//System.out.println(ticket);

		System.out.println("edit POST called " + ticket);
		ticketRepo.save(ticket);
		
		Long grpId = ticket.getGroupid();

		return "redirect:/group/" + grpId;
	}
	
	
	
	
	
	@RequestMapping(value = "/cgroup")
	public String createGroup(Model model) {
		System.out.println("crete tic group reached");
		model.addAttribute("Grp", new TicketGroup());
		return "create-group";
	}
	
	
	@PostMapping (value = "/cgroup")
	public String createGroupPost (@ModelAttribute TicketGroup Grp) {
		Grp.setCreated(LocalDate.now());
		System.out.println(Grp);
		ticketGrpRepo.save(Grp);
		
		//System.out.println(ticket);
		return "redirect:/";
	}
	
	
	@RequestMapping(value="/deletegroup/{Id}")
	public String DeleteGrp(@PathVariable Long Id, Model model) {
		TicketGroup grp = ticketGrpRepo.findById(Id).get();
		model.addAttribute("grp", grp);
		return "deletegroup";
	}
	
	@PostMapping(value="/deletegroup/{Id}")
	public String DeleteGrpPost(@PathVariable Long Id) {
		TicketGroup grp = ticketGrpRepo.findById(Id).get();

		///NEED TO DELETE ALL TICCKETS
		ticketGrpRepo.deleteById(Id);
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	//User
	
	//List All
	@GetMapping(value = "/user")
	public List<User> test() {	
		System.out.println("GEt REQ");
		return usrRepo.findAll();
	}

	//Find 1
	@GetMapping(value = "/user/{Id}")
	public Optional<User> UserId(@PathVariable Long Id) {
		System.out.println("GEt REQ single");
		return usrRepo.findById(Id);
				//.orElseThrow(()-> new UserNotFoundException(Id));
	}
	
	//Insert
	@PostMapping(value = "/user")
	public User adduser(@RequestBody User usrs) {
		System.out.println("POST REQ");
		System.out.println(usrs);
		//System.out.println(usrs.getId());
		
		
		return usrRepo.save(usrs);
	}
	
	//Delete
	@DeleteMapping(value = "/user/{Id}")
	public void removeUser(@PathVariable Long Id) {
		 usrRepo.deleteById(Id);
	}
	
	//Update
	@PutMapping(value = "/user/{Id}")
	public User updateUser (@RequestBody User newUser, @PathVariable Long Id) {
		return usrRepo.findById(Id)
			.map(user ->{
				user.setFisrtname(newUser.getFisrtname());
				user.setLastname(newUser.getLastname());
				return usrRepo.save(user);})
			.orElseGet(()->{
				newUser.setId(Id);
				return usrRepo.save(newUser);
			});
	}
	
	
	
	//TICKETS 
	
	
}
