package com.company.prueba.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario {
	    @Id
	    @Column(name="USU_ID")
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario_id")
	    @SequenceGenerator(name = "seq_usuario_id", sequenceName = "seq_usuario_id", allocationSize = 1)
	    private Long usuId;
	    
	    @Column(name="USU_NOMBRE")
	    private String usuNombre;
	    
	    @Column(name="USU_EMAIL")
	    private String usuEmail;
	    
	    @Column(name="USU_PASSWORD")
	    private String usuPassword;
	    
	    @Column(name="USU_ROL")
	    private String usuRol;
	    
		public Long getUsuId() {
			return usuId;
		}
		public void setUsuId(Long usuId) {
			this.usuId = usuId;
		}
		public String getUsuNombre() {
			return usuNombre;
		}
		public void setUsuNombre(String usuNombre) {
			this.usuNombre = usuNombre;
		}
		public String getUsuEmail() {
			return usuEmail;
		}
		public void setUsuEmail(String usuEmail) {
			this.usuEmail = usuEmail;
		}
		public String getUsuPassword() {
			return usuPassword;
		}
		public void setUsuPassword(String usuPassword) {
			this.usuPassword = usuPassword;
		}
		public String getUsuRol() {
			return usuRol;
		}
		public void setUsuRol(String usuRol) {
			this.usuRol = usuRol;
		}
	    
	    
}
