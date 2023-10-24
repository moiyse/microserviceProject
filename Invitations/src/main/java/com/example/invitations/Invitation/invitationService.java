package com.example.invitations.Invitation;

import com.example.invitations.User;
import com.example.invitations.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class invitationService implements IInvitationService {


    @Autowired
    InvitationRepository invitationRepository;

    @Autowired
    userRepository userRep;

    @Override
    public List<Invitation> getAllInvitationSentByUser(User user) {
        return invitationRepository.getInvitationsSentByUser(user);
    }

    @Override
    public Boolean sendInvitationByEmail(User sender,String emailReceiver){
        Invitation invitation = new Invitation();
        Optional<User> receiver = userRep.getByEmail(emailReceiver);
        User senderObject = userRep.getByEmail(sender.getEmail()).get();
        if(receiver != null){
            invitation.setEquipe(senderObject.getEquipe());
            invitation.setReceiver(receiver.get());
            invitation.setSender(senderObject);
            invitation.setDateEnvoi(new Date());
            invitationRepository.save(invitation);
            return true;
        }
        return null;
    }



    @Override
    public List<Invitation> getAllInvitationReceivedByUser(User user) {
        User userObject = userRep.getByEmail(user.getEmail()).get();
        List<Invitation> invitationsReceived = new ArrayList<Invitation>();
        List<Invitation> invitations = invitationRepository.findAll();
        if(!invitations.isEmpty())
        {
            invitations.forEach(invitation -> {
                if(invitation.getStatus().equals(InvitationStatus.OPENED) || invitation.getStatus().equals(InvitationStatus.PENDING))
                {
                    userObject.getInvitationsReceived().forEach(invitationReceivedIteration -> {
                        if(invitation.getIdInvitation() == invitationReceivedIteration.getIdInvitation())
                            invitationsReceived.add(invitation);
                    });
                }

            });
        }

        System.out.println("result of inviations received in the getAllReceivedByUser is : "+invitationsReceived);
        return invitationsReceived;
    }


    public void deleteInvitationOnRefuse(int id_invitation){
        Optional<Invitation> invitationObject = invitationRepository.findById(id_invitation);
        if(invitationObject != null){
            invitationRepository.deleteById(invitationObject.get().getIdInvitation());
        }
        else
            System.out.println("null pointer exception in deleteInvitationOnRefuse methode");
    }

    @Override
    public Invitation changeStatusOfInvitation(User user,int idInvitation,String invitationStatus) {
        Optional<Invitation> invitationObject = invitationRepository.findById(idInvitation);
        if(invitationObject != null)
        {
            if(invitationStatus.toLowerCase().equals("opened"))
                invitationObject.get().setStatus(InvitationStatus.OPENED);
            else if(invitationStatus.toLowerCase().equals("withdrawn"))
                invitationObject.get().setStatus(InvitationStatus.WITHDRAWN);
            else if(invitationStatus.toLowerCase().equals("pending"))
                invitationObject.get().setStatus(InvitationStatus.PENDING);
            else if(invitationStatus.toLowerCase().equals("accepted"))
                invitationObject.get().setStatus(InvitationStatus.ACCEPTED);
            else if(invitationStatus.toLowerCase().equals("refused"))
                invitationObject.get().setStatus(InvitationStatus.REFUSED);
            else
                return null;
            invitationObject.get().setReceiver(user);
            return invitationRepository.save(invitationObject.get());
        }
        else
            return null;

    }


}
