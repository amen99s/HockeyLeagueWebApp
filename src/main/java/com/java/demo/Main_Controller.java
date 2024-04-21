package com.java.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Main_Controller {

	@Autowired PlayerRepository pr;
	@Autowired TeamRepository tr;



	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}

	@RequestMapping("/main")
	public String main(Model model ,@RequestParam("team") String team) {
		model.addAttribute("team" , team);
		return "redirect:/";
	}


	@RequestMapping("/viewPlayer")
	public String view(Model model , @RequestParam("team") String team , @ModelAttribute Player player) {
		 Team teamRed = new Team("Red");
		 Team teamBlue = new Team("Blue");
		 if(tr.count()==0) {
			 tr.save(teamRed);
			 tr.save(teamBlue);
		 }
		 if(team.equals("Red")) {
			 if(player != null && player.getPlayerName()!=null) {
				 player.setTeamId(1);
				 pr.save(player);
				 }
			 model.addAttribute("players" ,pr.findByTeamId(1));

		 }else if(team.equals("Blue")) {
			 if(player != null && player.getPlayerName() != null) {
				 player.setTeamId(2);
				 pr.save(player);
			 }
			 model.addAttribute("players" ,pr.findByTeamId(2));


		 }
		 model.addAttribute("team" , team);
		 return "index2";


	}

	@RequestMapping("/viewFromIndex")
	public String viewFromIndex(Model model , @RequestParam("team") String team) {
		if(team.equals("Red")) {

				 model.addAttribute("players" ,pr.findByTeamId(1));



		 }else if(team.equals("Blue")) {

				 model.addAttribute("players" ,pr.findByTeamId(2));

			 }
		model.addAttribute("team" , team);
		return "index2";
	}


	@RequestMapping("/addPlayer")
	public String add(Model model , @ModelAttribute Player player){
		model.addAttribute("player" , player);
//		pr.save(player);
		return "playerForm";
	}

	@RequestMapping("/deletePlayer")
	public String deletePlayer(Model model ,@RequestParam("playerId") int playerId , @RequestParam("team") String team ) {
		pr.deleteById(playerId);
		if(team.equals("Red")) {
			model.addAttribute("players" , pr.findByTeamId(1));
		}else if(team.equals("Blue")) {
			model.addAttribute("players" , pr.findByTeamId(2));
		}

		model.addAttribute("team" , team);
		//model.addAttribute("players" , pr.findAll());

		return "index2";
		}


	@RequestMapping("/deleteConfirmation")
	public String deleteConfirmation(Model model , @RequestParam("playerId")int playerId , @RequestParam("playerName") String playerName , @RequestParam("teamId") int teamId , @RequestParam("team") String team) {
		model.addAttribute("playerId" , playerId);
		model.addAttribute("playerName",playerName);
		model.addAttribute("teamId" , teamId);
		model.addAttribute("team",team);
		return "deleteConfirmation";
	}


	@RequestMapping("/editPlayer")
	public String editPlayer(Model model , @RequestParam("playerId") int playerId , @RequestParam("teamId")int teamId) {
		model.addAttribute("player" , pr.findById(playerId));
		return "editPlayer";
	}

	@RequestMapping("/savePlayer")
	public String save(Model model , @ModelAttribute Player player , @RequestParam("teamId")int teamId) {
		String team = "";
		pr.save(player);
		model.addAttribute("player" , new Player());
		if(teamId == 1) {
			 team = "Red";
			model.addAttribute("players" , pr.findByTeamId(1));
		}else if(teamId == 2) {
			team = "Blue";
			model.addAttribute("players" , pr.findByTeamId(2));
		}
		model.addAttribute("player" , player);
		model.addAttribute("team" , team);
		return "index2";
	}






}
