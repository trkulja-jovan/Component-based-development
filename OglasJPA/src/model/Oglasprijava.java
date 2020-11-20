package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the oglasprijava database table.
 * 
 */
@Entity
@NamedQuery(name="Oglasprijava.findAll", query="SELECT o FROM Oglasprijava o")
public class Oglasprijava implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPrijava;

	private String text;

	//bi-directional many-to-one association to Ogla
	@ManyToOne
	@JoinColumn(name="idOglas")
	private Oglas oglas;

	//bi-directional many-to-one association to Oglaskorisnik
	@ManyToOne
	@JoinColumn(name="idKorisnik")
	private Oglaskorisnik oglaskorisnik;

	public Oglasprijava() {
	}

	public int getIdPrijava() {
		return this.idPrijava;
	}

	public void setIdPrijava(int idPrijava) {
		this.idPrijava = idPrijava;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Oglas getOglas() {
		return this.oglas;
	}

	public void setOglas(Oglas ogla) {
		this.oglas = ogla;
	}

	public Oglaskorisnik getOglaskorisnik() {
		return this.oglaskorisnik;
	}

	public void setOglaskorisnik(Oglaskorisnik oglaskorisnik) {
		this.oglaskorisnik = oglaskorisnik;
	}

}