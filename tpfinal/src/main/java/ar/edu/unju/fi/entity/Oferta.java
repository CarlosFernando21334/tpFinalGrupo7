package ar.edu.unju.fi.entity;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
@Entity
@Table(name="ofertas")
public class Oferta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;                                            
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ofe_id")
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="emp_cuit")
	private Empleador empleador;
	@Column(name="ofe_empleador")
	@OneToMany(cascade = CascadeType.ALL,
	           fetch = FetchType.EAGER)
	private List<Ciudadano> ciudadanos;
	@Column(name="ofe_vacante")
	private int vacante; 
	@Column(name="ofe_puesto")
	@NotEmpty
	private String puestoRequerido; 
	@Column(name="ofe_desc")
	private String resumenPuesto;
	@Column(name="ofe_tareas")
	private String tareas; 
	@Column(name="ofe_contacto")
	private String datosContacto;
	@Column(name="ofe_jornada")
	private String jornada;
	@Column(name="ofe_requisitos")
	private String requisitos; 
	@Column(name="ofe_salario")
	private int salario;
	@Column(name="ofe_beneficios")
	private String beneficios; 
	@Column(name="ofe_disponible")
	private boolean disponible;
	
	
	
	public Oferta()
	{
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Empleador getEmpleador() {
		return empleador;
	}
	public void setEmpleador(Empleador empleador) {
		this.empleador = empleador;
	}
	public List<Ciudadano> getCiudadanos() {
		return ciudadanos;
	}
	public void setCiudadanos(List<Ciudadano> ciudadanos) {
		this.ciudadanos = ciudadanos;
	}
	public int getVacante() {
		return vacante;
	}
	public void setVacante(int vacante) {
		this.vacante = vacante;
	}
	public String getPuestoRequerido() {
		return puestoRequerido;
	}
	public void setPuestoRequerido(String puestoRequerido) {
		this.puestoRequerido = puestoRequerido;
	}
	public String getResumenPuesto() {
		return resumenPuesto;
	}
	public void setResumenPuesto(String resumenPuesto) {
		this.resumenPuesto = resumenPuesto;
	}
	public String getTareas() {
		return tareas;
	}
	public void setTareas(String tareas) {
		this.tareas = tareas;
	}
	public String getDatosContacto() {
		return datosContacto;
	}
	public void setDatosContacto(String datosContacto) {
		this.datosContacto = datosContacto;
	}
	public String getJornada() {
		return jornada;
	}
	public void setJornada(String jornada) {
		this.jornada = jornada;
	}
	public String getRequisitos() {
		return requisitos;
	}
	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	public String getBeneficios() {
		return beneficios;
	}
	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
