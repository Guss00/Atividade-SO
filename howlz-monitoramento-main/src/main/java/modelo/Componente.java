package modelo;

import java.util.ArrayList;
import java.util.List;

public class Componente {
    private Integer idComponente;
    private String tipo;
    private String modelo;
    private String numeroSerial;
    private Integer fkComputador;
    private List<Monitoramento> monitoramentos;

    public Componente() {
    }

    public Componente(String tipo, String modelo, Integer fkComputador) {
        this.tipo = tipo;
        this.modelo = modelo;
        this.fkComputador = fkComputador;
        this.monitoramentos = new ArrayList<>();
    }

    public Componente(String tipo, String modelo, Integer fkComputador, String serial) {
        this.tipo = tipo;
        this.modelo = modelo;
        this.fkComputador = fkComputador;
        this.monitoramentos = new ArrayList<>();
    }

    public Integer getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Integer idComponente) {
        this.idComponente = idComponente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getFkComputador() {
        return fkComputador;
    }

    public void setFkComputador(Integer fkComputador) {
        this.fkComputador = fkComputador;
    }

    public List<Monitoramento> getMonitoramentos() {
        return monitoramentos;
    }

    public void setMonitoramentos(List<Monitoramento> monitoramentos) {
        this.monitoramentos = monitoramentos;
    }

    public String getNumeroSerial() {
        return numeroSerial;
    }

    public void setNumeroSerial(String numeroSerial) {
        this.numeroSerial = numeroSerial;
    }

    @Override
    public String toString() {
        return "Componente{" +
                "tipo=" + tipo +
                ", modelo='" + modelo + '\'' +
                ", fkComputador=" + fkComputador +
                ", monitoramentos=" + monitoramentos +
                '}';
    }
}
