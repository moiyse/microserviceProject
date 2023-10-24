package com.example.invitations.Equipe;



import com.example.invitations.User;

import java.util.List;

public interface IEquipeService {



    public Boolean getEquipeByLeader(User user);

	public Equipe getEquipeByUser(User user);


	Equipe createEquipe(User user, String equipeName);

	public Equipe deleteEquipeWithRemovingUserKey(Integer idEquipe);


    Equipe changeTeamName(Equipe equipe, String equipeName);

    public List<Equipe> getAll();
	public Equipe getEquipeById(Integer id);
	public Equipe addEquipe(Equipe e);
	public Equipe updateEquipe(Equipe e);
	public void deleteEquipe(Integer id);
	
}
