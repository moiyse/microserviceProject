package com.example.invitations.Invitation;


import com.example.invitations.Invitation.Invitation;
import com.example.invitations.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation, Integer> {

    @Query("Select i from Invitation i where i.sender=:user and (i.status='PENDING' or i.status='OPENED' or i.status='ACCEPTED' or i.status='LEFTTEAM')")
    public List<Invitation> getInvitationsSentByUser(@Param("user") User user);


}