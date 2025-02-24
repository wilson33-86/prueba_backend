package com.company.prueba.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Comerciante")
public class Comerciante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comerciante_id")
    @SequenceGenerator(name = "seq_comerciante_id", sequenceName = "seq_comerciante_id", allocationSize = 1)
    private Integer comId;

    @Column(name = "com_razons")
    private String comRazons;

    @Column(name = "com_municipio")
    private String comMunicipio;

    @Column(name = "com_tel")
    private String comTel;

    @Column(name = "com_email")
    private String comEmail;

    @Column(name = "com_fecha_registro")
    private LocalDateTime comFechaRegistro;

    @Column(name = "com_estado")
    private String comEstado;

    @Column(name = "com_fecha_actualizacion")
    private LocalDateTime comFechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "com_usu_upd", referencedColumnName = "usu_id")
    private Usuario comUsuUpd;

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public String getComRazons() {
		return comRazons;
	}

	public void setComRazons(String comRazons) {
		this.comRazons = comRazons;
	}

	public String getComMunicipio() {
		return comMunicipio;
	}

	public void setComMunicipio(String comMunicipio) {
		this.comMunicipio = comMunicipio;
	}

	public String getComTel() {
		return comTel;
	}

	public void setComTel(String comTel) {
		this.comTel = comTel;
	}

	public String getComEmail() {
		return comEmail;
	}

	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}

	public LocalDateTime getComFechaRegistro() {
		return comFechaRegistro;
	}

	public void setComFechaRegistro(LocalDateTime comFechaRegistro) {
		this.comFechaRegistro = comFechaRegistro;
	}

	public String getComEstado() {
		return comEstado;
	}

	public void setComEstado(String comEstado) {
		this.comEstado = comEstado;
	}

	public LocalDateTime getComFechaActualizacion() {
		return comFechaActualizacion;
	}

	public void setComFechaActualizacion(LocalDateTime comFechaActualizacion) {
		this.comFechaActualizacion = comFechaActualizacion;
	}

	public Usuario getComUsuUpd() {
		return comUsuUpd;
	}

	public void setComUsuUpd(Usuario comUsuUpd) {
		this.comUsuUpd = comUsuUpd;
	}

   
}

