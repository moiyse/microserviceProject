package com.example.invitations.Invitation;

import com.example.invitations.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200" , "http://localhost:4201"})
public class invitationController {

    @Autowired
    IInvitationService invitationService;

    @PostMapping("/invitation/getInvitationsSentByUser")
    public List<Invitation> getAllInvitationSentByUser(@RequestBody User user){

        return invitationService.getAllInvitationSentByUser(user);
    }

    @PostMapping("/invitation/getInvitationsReceivedByUser")
    public List<Invitation> getAllInvitationReceivedByUser(@RequestBody User user){
        //System.out.println("invitations received : " + invitationService.getAllInvitationReceivedByUser(user));
        return invitationService.getAllInvitationReceivedByUser(user);
    }

    @DeleteMapping("/invitation/deleteInvitationOnRefuse/{idInvitation}")
    public void deleteInvitationOnRefuse(@PathVariable("idInvitation") int idInvitation){
        invitationService.deleteInvitationOnRefuse(idInvitation);
    }

    @PostMapping("/invitation/sendInvitationByEmail/{emailReceiver}")
    public Boolean sendInvitationByEmail(@RequestBody User sender,@PathVariable("emailReceiver")String emailReceiver ){
        return invitationService.sendInvitationByEmail(sender,emailReceiver);
    }

    @PutMapping("/invitation/changeStatusOfInvitation/{idInvitation}/{status}")
    public Invitation changeStatusOfInvitation(@RequestBody User user,@PathVariable("idInvitation") int idInvitation,@PathVariable("status") String status){
        return invitationService.changeStatusOfInvitation(user,idInvitation,status);
    }

}
