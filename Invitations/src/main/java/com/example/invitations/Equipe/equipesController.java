package com.example.invitations.Equipe;

import com.example.invitations.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@RequestMapping("oauth/teams")
@CrossOrigin(origins = {"http://localhost:4200" , "http://localhost:4201"})
public class equipesController {

	@Autowired(required=false)
	IEquipeService IEquipe;
	

	@PostMapping("/equipe/getEquipeByLeader")
	public Boolean getEquipeByLeader(@RequestBody User user){
		return IEquipe.getEquipeByLeader(user);
	}
		
	@GetMapping("/equipe/Get")
	public List<Equipe> GetAll() {
		return IEquipe.getAll();
	}

	@GetMapping("/equipe/Get/{id}")
	public Equipe Get(@PathVariable Integer id) {
		return IEquipe.getEquipeById(id);
	}

	@PostMapping("/equipe/Post")
	public Equipe Post(@RequestBody Equipe e) {
		return IEquipe.addEquipe(e);
	}

	@PutMapping("/equipe/Update")
	public Equipe Update(@RequestBody Equipe e) {
		return IEquipe.updateEquipe(e);
	}

	@DeleteMapping("/equipe/Delete/{id}")
	public void Delete(@PathVariable Integer id) {
		IEquipe.deleteEquipe(id);
	}

	@PostMapping("/equipe/getEquipeByUser")
	public Equipe getEquipeByUser(@RequestBody User user){
		return IEquipe.getEquipeByUser(user);
	}

	@PostMapping("/equipe/createEquipeOfHackathon/{equipeName}/{idHackathon}")
	public Equipe createEquipeOfHackathon(@RequestBody User user,@PathVariable("equipeName")String equipeName,@PathVariable("idHackathon")int idHackathon) {
		return IEquipe.createEquipe(user,equipeName);
	}

	@DeleteMapping("/equipe/deleteEquipeWithRemovingUserKey/{idEquipe}")
	public Equipe deleteEquipeWithRemovingUserKey(@PathVariable("idEquipe") int idEquipe)
	{
		return IEquipe.deleteEquipeWithRemovingUserKey(idEquipe);
	}

	@PutMapping("/equipe/changeTeamName/{equipeName}")
	public Equipe changeTeamName(@RequestBody Equipe equipe,@PathVariable("equipeName") String equipeName)
	{
		return IEquipe.changeTeamName(equipe,equipeName);
	}
	
}
