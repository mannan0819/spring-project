package com.example.demo;

import com.example.demo.dao.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.*;
import com.example.demo.model.Ticket;
import com.example.demo.model.Comment;
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

	@Autowired
	CommentRepo commentRepo;


	@RequestMapping (value="/test")
	public String testing(Authentication auth, Model model) {
		System.out.println(auth);
		model.addAttribute("activePage", "Home");
		model.addAttribute("ticketGrp", ticketGrpRepo.findAll());
		if(auth != null) {
			myUserDetails usrDetail = (myUserDetails) auth.getPrincipal();
			model.addAttribute("Name", usrDetail.getFirtname());
		}
		else {
			model.addAttribute("Name", "Not logged in");
		}
		return "testst";
	}




	//------User----------------------
	@RequestMapping(value = "/createuser")
	public String createUser(Model model) {
		model.addAttribute("User", new User());
		return "create-user";
	}


	@PostMapping (value = "/createuser")
	public String submitUser (@Valid User user, BindingResult br, Model model) {
		System.out.println("Here");
		if(br.hasErrors()) {
			System.out.println(br.toString());
			System.out.println("NOT VALIDATED");
			model.addAttribute("User", user);
			return "create-user";

		}
		System.out.println("Here" + br.toString());

		System.out.println(user);
		usrRepo.save(user);
		return "redirect:/";
	}


	//------------------GROUP---------------


	@RequestMapping(value ="/")
	public String index(Authentication auth, Model model) {
		model.addAttribute("activePage", "Home");
		model.addAttribute("ticketGrp", ticketGrpRepo.findAll());
		if(auth != null) {
			myUserDetails usrDetail = (myUserDetails) auth.getPrincipal();
			model.addAttribute("Name", usrDetail.getFirtname());
		}
		else {
			model.addAttribute("Name", "Not logged in");
		}
		//System.out.println(ticketRepo.findAll());
		//System.out.println("Index page");
		return "index";
	}

	
	
	
	
	@RequestMapping(value = "/cgroup")
	public String createGroup(Authentication auth, Model model) {
		System.out.println("crete tic group reached");
		if(auth != null) {
			myUserDetails usrDetail = (myUserDetails) auth.getPrincipal();
			model.addAttribute("Name", usrDetail.getFirtname());
		}
		else {
			model.addAttribute("Name", "Not logged in");
		}
		model.addAttribute("Grp", new TicketGroup());
		model.addAttribute("activePage", "cGroup");
		model.addAttribute("ticketGrp", ticketGrpRepo.findAll());
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
	public String DeleteGrp(@PathVariable Long Id, Model model, Authentication auth) {
		TicketGroup grp = ticketGrpRepo.findById(Id).get();
		model.addAttribute("grp", grp);
		if(auth != null) {
			myUserDetails usrDetail = (myUserDetails) auth.getPrincipal();
			model.addAttribute("Name", usrDetail.getFirtname());
		}
		else {
			model.addAttribute("Name", "Not logged in");
		}
		model.addAttribute("ticketGrp", ticketGrpRepo.findAll());
		model.addAttribute("activePage", "ticks");
		model.addAttribute("activeId", Id);
		return "deletegroup";
	}

	@PostMapping(value="/deletegroup/{Id}")
	public String DeleteGrpPost(@PathVariable Long Id) {
		TicketGroup grp = ticketGrpRepo.findById(Id).get();

		
		///NEED TO DELETE ALL TICCKETS
		ticketGrpRepo.deleteById(Id);
		return "redirect:/";
	}

	
	
	
	
	//---------------------TICKETS----------------------------

	@RequestMapping(value = "/group/{Id}")
	public String GroupId(@PathVariable Long Id, Model model, Authentication auth){	
		System.out.println("The number is " + Id);
		List<TicketGroup> t = ticketGrpRepo.findAll();
		List<Ticket> ti = ticketRepo.findAll();
		List<Ticket> tiG = ticketRepo.findAllByGroupid(Id);

		model.addAttribute("ticketGrp", t);
		model.addAttribute("tics", tiG);
		model.addAttribute("groupid", Id);
		model.addAttribute("activePage", "ticks");
		if(auth != null) {
			myUserDetails usrDetail = (myUserDetails) auth.getPrincipal();
			model.addAttribute("Name", usrDetail.getFirtname());
		}
		else {
			model.addAttribute("Name", "Not logged in");
		}

		return "tickets";
	}
	
	
	
	
	@RequestMapping(value = "/cticket/{Id}")
	public String createTicket(@PathVariable Long Id, Model model, Authentication auth) {
		System.out.println("crete tic reached");
		if(auth != null) {
			myUserDetails usrDetail = (myUserDetails) auth.getPrincipal();
			model.addAttribute("Name", usrDetail.getFirtname());
		}
		else {
			model.addAttribute("Name", "Not logged in");
		}
		model.addAttribute("ticket", new Ticket());
		model.addAttribute("ticketGrp", ticketGrpRepo.findAll());
		model.addAttribute("activePage", "ticks");
		model.addAttribute("activeId", Id);
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
	public String ticketDetail(@PathVariable Long Id, Model model, Authentication auth) {
		Ticket tic = ticketRepo.findById(Id).get();
		model.addAttribute("ticket", tic);
		if(auth != null) {
			myUserDetails usrDetail = (myUserDetails) auth.getPrincipal();
			model.addAttribute("Name", usrDetail.getFirtname());
		}
		else {
			model.addAttribute("Name", "Not logged in");
		}
		model.addAttribute("ticketGrp", ticketGrpRepo.findAll());
		model.addAttribute("activePage", "ticks");
		model.addAttribute("activeId", tic.getGroupid());
		return "ticket-detail";
	}

	@RequestMapping(value = "/dticket/{Id}")
	public String dTicket(@PathVariable Long Id, Model model, Authentication auth) {
		System.out.println("crete tic reached");
		
		Ticket tic = ticketRepo.findById(Id).get();
		model.addAttribute("ticket", tic);
		
		List<Comment> comms = commentRepo.findAllByTicketid(tic.getId());
		model.addAttribute("comms", comms);
		model.addAttribute("ticketGrp", ticketGrpRepo.findAll());
		model.addAttribute("activePage", "ticks");
		model.addAttribute("activeId", tic.getGroupid());
		model.addAttribute("comment", new Comment());
		if(auth != null) {
			myUserDetails usrDetail = (myUserDetails) auth.getPrincipal();
			model.addAttribute("Name", usrDetail.getFirtname());
		}
		else {
			model.addAttribute("Name", "Not logged in");
		}
		System.out.println("THIS" + comms);
		return "deticket";
	}

	
	@PostMapping (value = "/comment")
	public String submitComment (@ModelAttribute Comment comment) {

		comment.setCreated(LocalDate.now());
		comment.setEdited(LocalDate.now());
		comment.setUsrid((long) 0);

		System.out.println(comment);
		commentRepo.save(comment);
		//ticketRepo.save(ticket);
		//System.out.println(ticket);
		return "redirect:/dticket/" + comment.getTicketid();
	}

	@RequestMapping(value="/deletetic/{Id}")
	public String DeleteTicket(@PathVariable Long Id, Model model, Authentication auth) {
		Ticket tic = ticketRepo.findById(Id).get();
		model.addAttribute("ticket", tic);
		if(auth != null) {
			myUserDetails usrDetail = (myUserDetails) auth.getPrincipal();
			model.addAttribute("Name", usrDetail.getFirtname());
		}
		else {
			model.addAttribute("Name", "Not logged in");
		}
		model.addAttribute("ticketGrp", ticketGrpRepo.findAll());
		model.addAttribute("activePage", "ticks");
		model.addAttribute("activeId", tic.getGroupid());
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
	public String EditTicket(@PathVariable Long Id, Model model, Authentication auth) {
		Ticket tic = ticketRepo.findById(Id).get();

		model.addAttribute("ticket", tic);
		System.out.println("edit called " + tic);
		if(auth != null) {
			myUserDetails usrDetail = (myUserDetails) auth.getPrincipal();
			model.addAttribute("Name", usrDetail.getFirtname());
		}
		else {
			model.addAttribute("Name", "Not logged in");
		}
		model.addAttribute("ticketGrp", ticketGrpRepo.findAll());
		model.addAttribute("activePage", "ticks");
		model.addAttribute("activeId", tic.getGroupid());
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







	@RequestMapping(value = "/log")
	public String log(Model model) {
		System.out.println("log reached");
		model.addAttribute("ticket", new Ticket());
		return "create-ticket";
	}


	@RequestMapping(value = "/username")
	@ResponseBody
	public String currentUserName(Authentication authentication) {
		return authentication.getName();
	}

	@RequestMapping (value="/sec")
	public String sec(Authentication auth, Model model) {
		if(auth != null) {
			myUserDetails usrDetail = (myUserDetails) auth.getPrincipal();
			model.addAttribute("Name", usrDetail.getFirtname());
		}
		else {
			model.addAttribute("Name", "Not logged in");
		}
		return "seccheck";
	}

	@RequestMapping(value = "/home")
	public String Home() {
		System.out.println("HOME CONTROLLER REACHED");
		//String s = ms.lstUser();
		return "home";
	}
	@RequestMapping(value = "/login")
	public String login() {
		System.out.println("login controller");
		//String s = ms.lstUser();
		return "login1";
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
		return "login1";
	}

	//User

	//List All
	@GetMapping(value = "/user")
	public List<User> test(Model model) {
		 model.addAttribute("ticket", ticketRepo.findById((long) 1));
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
