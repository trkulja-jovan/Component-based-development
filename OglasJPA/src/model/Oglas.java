package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the oglas database table.
 * 
 */
@Entity
@Table(name="oglas")
@NamedQuery(name="Oglas.findAll", query="SELECT o FROM Oglas o")
public class Oglas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOglas;

	private int brojPregleda;

	private String text;

	//bi-directional many-to-one association to Oglaskorisnik
	@ManyToOne
	@JoinColumn(name="idKorisnik")
	private Oglaskorisnik oglaskorisnik;

	//bi-directional many-to-one association to Oglasprijava
	@OneToMany(mappedBy="oglas")
	private List<Oglasprijava> oglasprijavas;

	public Oglas() {
	}

	public int getIdOglas() {
		return this.idOglas;
	}

	public void setIdOglas(int idOglas) {
		this.idOglas = idOglas;
	}

	public int getBrojPregleda() {
		return this.brojPregleda;
	}

	public void setBrojPregleda(int brojPregleda) {
		this.brojPregleda = brojPregleda;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Oglaskorisnik getOglaskorisnik() {
		return this.oglaskorisnik;
	}

	public void setOglaskorisnik(Oglaskorisnik oglaskorisnik) {
		this.oglaskorisnik = oglaskorisnik;
	}

	public List<Oglasprijava> getOglasprijavas() {
		return this.oglasprijavas;
	}

	public void setOglasprijavas(List<Oglasprijava> oglasprijavas) {
		this.oglasprijavas = oglasprijavas;
	}

	public Oglasprijava addOglasprijava(Oglasprijava oglasprijava) {
		getOglasprijavas().add(oglasprijava);
		oglasprijava.setOglas(this);

		return oglasprijava;
	}

	public Oglasprijava removeOglasprijava(Oglasprijava oglasprijava) {
		getOglasprijavas().remove(oglasprijava);
		oglasprijava.setOglas(null);

		return oglasprijava;
	}

}