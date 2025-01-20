package com.example.servico_1.dto;

public class ClienteDTO {
	
    private Long id;
    
    private String nome;
    
    private String email;
    
    private Boolean isActive;
    
    private Long advogadoId;

	public ClienteDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getAdvogadoId() {
		return advogadoId;
	}

	public void setAdvogadoId(Long advogadoId) {
		this.advogadoId = advogadoId;
	}   
}