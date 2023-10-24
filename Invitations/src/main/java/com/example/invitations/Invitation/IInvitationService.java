package com.example.invitations.Invitation;


import com.example.invitations.User;

import java.util.List;

public interface IInvitationService {

    public List<Invitation> getAllInvitationSentByUser(User user);

    public List<Invitation> getAllInvitationReceivedByUser(User user);

    public void deleteInvitationOnRefuse(int id_invitation);

    public Invitation changeStatusOfInvitation(User user,int idInvitation,String invitationStatus);

    public Boolean sendInvitationByEmail(User sender,String emailReceiver);

}
