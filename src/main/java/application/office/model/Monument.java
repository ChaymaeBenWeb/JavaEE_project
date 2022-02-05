package application.office.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Monument implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 3825993248842249538L;

	@Id
	private String codeM;
	private String nomM;
	private String proprietaire;
	private String typeMonument;
	private float longitude;
	private float latitude;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Lieu.class)
	@JoinColumn(name="codeLieu")
	private Lieu codeLieu;


	@ManyToMany//Association de monuments ves celebrit
	@JoinTable(name="AssocieA",joinColumns= @JoinColumn(name="codeM"),
    inverseJoinColumns=@JoinColumn(name="numCelebrite"))


	private Set<Celebrite> celebrites;


	public Monument() {
	}
	public Monument(String codeM, String nomM, String proprietaire, String typeMonument, float longitude,
			float latitude) {
		super();
		this.codeM = codeM;
		this.nomM = nomM;
		this.proprietaire = proprietaire;
		this.typeMonument = typeMonument;
		this.longitude = longitude;
		this.latitude = latitude;
	}


	public String getCodeM() {
		return codeM;
	}
	public void setCodeM(String codeM) {
		this.codeM = codeM;
	}

	public String getNomM() {
		return nomM;
	}
	public void setNomM(String nomM) {
		this.nomM = nomM;
	}


	public Lieu getCodeLieu() {
		return codeLieu;
	}
	public void setCodeLieu(Lieu codeLieu) {
		this.codeLieu = codeLieu;
	}
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	public String getTypeMonument() {
		return typeMonument;
	}
	public void setTypeMonument(String typeMonument) {
		this.typeMonument = typeMonument;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Monument [codeM=" + codeM + ", nomM=" + nomM + ", proprietaire=" + proprietaire + ", typeMonument="
				+ typeMonument + ", longitude=" + longitude + ", latitude=" + latitude + ", codeLieu=" + codeLieu
				+ ", celebrites=" + celebrites + "]";
	}


	public Set<Celebrite> getCelebrites(){
	    return celebrites;
	}
	public void setCelebrites(Set<Celebrite> celebrites){
	    this.celebrites = celebrites;
	}







}

