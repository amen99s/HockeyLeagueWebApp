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
public class Team {


		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int teamId;
		private String teamName;

//		@OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//		private List<Player> players;

		public Team(String teamName) {
			this.teamName = teamName;
		}

}


