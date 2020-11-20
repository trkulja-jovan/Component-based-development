package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the oglaskorisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Oglaskorisnik.findAll", query="SELECT o FROM Oglaskorisnik o")
public class Oglaskorisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKorisnik;

	private String nickname;

	private String password;

	private String username;

	//bi-directional many-to-one association to Ogla
	@OneToMany(mappedBy="oglaskorisnik")
	private List<Oglas> oglas;

	//bi-directional many-to-one association to Oglasprijava
	@OneToMany(mappedBy="oglaskorisnik")
	private List<Oglasprijava> oglasprijavas;

	public Oglaskorisnik() {
	}

	public int getIdKorisnik() {
		return this.idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Oglas> getOglas() {
		return this.oglas;
	}

	public void setOglas(List<Oglas> oglas) {
		this.oglas = oglas;
	}

	public Oglas addOgla(Oglas ogla) {
		getOglas().add(ogla);
		ogla.setOglaskorisnik(this);

		return ogla;
	}

	public Oglas removeOgla(Oglas ogla) {
		getOglas().remove(ogla);
		ogla.setOglaskorisnik(null);

		return ogla;
	}

	public List<Oglasprijava> getOglasprijavas() {
		return this.oglasprijavas;
	}

	public void setOglasprijavas(List<Oglasprijava> oglasprijavas) {
		this.oglasprijavas = oglasprijavas;
	}

	public Oglasprijava addOglasprijava(Oglasprijava oglasprijava) {
		getOglasprijavas().add(oglasprijava);
		oglasprijava.setOglaskorisnik(this);

		return oglasprijava;
	}

	public Oglasprijava removeOglasprijava(Oglasprijava oglasprijava) {
		getOglasprijavas().remove(oglasprijava);
		oglasprijava.setOglaskorisnik(null);

		return oglasprijava;
	}

}