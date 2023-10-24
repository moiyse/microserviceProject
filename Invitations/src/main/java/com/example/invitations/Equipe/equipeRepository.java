package com.example.invitations.Equipe;

import com.example.invitations.Equipe.Equipe;
import com.example.invitations.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface equipeRepository extends JpaRepository<Equipe, Integer>{


    @Query("select count(e) from Equipe e where e.leader=:user")
    public int getEquipeByLeader(@Param("user") User user);

}
