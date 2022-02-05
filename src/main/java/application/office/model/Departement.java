package application.office.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Departement implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1935143767958645073L;

	@Id
	private String dep;

	@OneToOne
	private Lieu chefLieu;
	private String nomDep;
	private String reg;

	@OneToMany(mappedBy ="dep", fetch=FetchType.LAZY)
	private Collection<Lieu> lieu;




	public Departement() {}



	public Departement(String dep, Lieu chefLieu, String nomDep, String reg) {
		super();
		this.dep = dep;
		this.chefLieu = chefLieu;
		this.nomDep = nomDep;
		this.reg = reg;
	}



	public String getDep() {
		return dep;
	}



	public void setDep(String dep) {
		this.dep = dep;
	}



	public Lieu getChefLieu() {
		return chefLieu;
	}



	public void setChefLieu(Lieu chefLieu) {
		this.chefLieu = chefLieu;
	}



	public String getNomDep() {
		return nomDep;
	}



	public void setNomDep(String nomDep) {
		this.nomDep = nomDep;
	}



	public String getReg() {
		return reg;
	}



	public void setReg(String reg) {
		this.reg = reg;
	}

	@Override
	public String toString() {
		return ""+dep;
	}






}