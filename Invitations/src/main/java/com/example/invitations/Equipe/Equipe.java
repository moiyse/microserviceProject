package com.example.invitations.Equipe;

import com.example.invitations.Invitation.Invitation;
import com.example.invitations.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Equipe implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idEquipe")
	private int idEquipe;
	private String nom;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;

	
	@OneToMany(mappedBy = "equipe")
	@JsonIgnore
	private List<User> membres;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "equipe")
	@JsonIgnore
	private List<Invitation> invitations;

	@OneToOne
	@JsonIgnore
	private User leader;
	
	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
		updatedAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}
}
