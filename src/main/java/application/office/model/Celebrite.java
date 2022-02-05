package application.office.model;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Celebrite")
public class Celebrite implements Serializable{
	/**
    *
    */
	private static final long serialVersionUID = 8437833828020336290L;

	@Id
	private int numCelebrite;
	private String nom;
	private String prenom;
	private String nationalite;
	private String epoque;


	 @ManyToMany
	 @JoinTable(name="AssocieA",joinColumns= @JoinColumn(name="numCelebrite"),
     inverseJoinColumns=@JoinColumn(name="codeM"))
	private Set<Monument> monuments;

	public Celebrite() {
		super();

	}

	public Celebrite(int numCelebrite, String nom, String prenom, String nationalite, String epoque) {
		super();
		this.numCelebrite = numCelebrite;
		this.nom = nom;
		this.prenom = prenom;
		this.nationalite = nationalite;
		this.epoque = epoque;
	}

	public int getNumCelebrite() {
		return numCelebrite;
	}

	public void setNumCelebrite(int numCelebrite) {
		this.numCelebrite = numCelebrite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getEpoque() {
		return epoque;
	}

	public void setEpoque(String epoque) {
		this.epoque = epoque;
	}

	@Override
	public String toString() {
		return "Celebrite [numCelebrite=" + numCelebrite + ", nom=" + nom + ", prenom=" + prenom + ", nationalite="
				+ nationalite + ", epoque=" + epoque + "]";
	}

	 public Set<Monument> getMonuments()
	    {
	        return monuments;
	    }
	    public void setMonuments(Set<Monument> monuments)
	    {
	        this.monuments = monuments;
	    }

}
