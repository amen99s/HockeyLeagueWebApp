package com.java.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int playerId;
	private String playerName;
	private String Address;
	private int teamId;

//	@ManyToOne
//	@JoinColumn(name = "teamId")
//	private Team team;

	private String Role;
	private boolean Active;

	public Player() {
		System.out.println("player created");
	}
}
