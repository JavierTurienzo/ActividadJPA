package com.Utad;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Mascota")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo;
    private String raza;

    @ManyToOne
    @JoinColumn(name = "dueno_id")
    private Dueno dueno;

    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
    private List<Visita> visitas;

    public Mascota(){
        super();
    }

    public Mascota(String nombre, String tipo, String raza, Dueno dueno){
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.dueno = dueno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Dueno getDueno() {
        return dueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    public List<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas( List<Visita> visitas) {
        this.visitas = visitas;
    }
}


