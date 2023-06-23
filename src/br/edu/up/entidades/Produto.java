package br.edu.up.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String codigo;
	private String nome;
	private float precoCompra;
	private float markup;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPrecoCompra() {
		return precoCompra;
	}
	public void setPrecoCompra(float precoCompra) {
		this.precoCompra = precoCompra;
	}
	public float getMarkup() {
		return markup;
	}
	public void setMarkup(float markup) {
		this.markup = markup;
	}
}
