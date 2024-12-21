package br.com.bprates.eventos.Sistema.de.eventos.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Evento {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nome;

        private LocalDate data;

        private LocalDateTime dataInicioInsc;

        private LocalDateTime dataFimInsc;

        private String descricao;

        @Enumerated(EnumType.STRING)
        private StatusEvento status;

        @OneToMany(mappedBy = "evento")
        private List<Inscricao> inscritos = new ArrayList<>();

        // Construtor padrão
        public Evento() {
        }

        // Construtor completo
        public Evento(Long id, String nome, LocalDate data, LocalDateTime dataInicioInsc, LocalDateTime dataFimInsc,
                      String descricao, StatusEvento status, List<Inscricao> inscritos) {
                this.id = id;
                this.nome = nome;
                this.data = data;
                this.dataInicioInsc = dataInicioInsc;
                this.dataFimInsc = dataFimInsc;
                this.descricao = descricao;
                this.status = status;
                this.inscritos = inscritos != null ? inscritos : new ArrayList<>();
        }

        // Getters e Setters
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

        public LocalDate getData() {
                return data;
        }

        public void setData(LocalDate data) {
                this.data = data;
        }

        public LocalDateTime getDataInicioInsc() {
                return dataInicioInsc;
        }

        public void setDataInicioInsc(LocalDateTime dataInicioInsc) {
                this.dataInicioInsc = dataInicioInsc;
        }

        public LocalDateTime getDataFimInsc() {
                return dataFimInsc;
        }

        public void setDataFimInsc(LocalDateTime dataFimInsc) {
                this.dataFimInsc = dataFimInsc;
        }

        public String getDescricao() {
                return descricao;
        }

        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        public StatusEvento getStatus() {
                return status;
        }

        public void setStatus(StatusEvento status) {
                this.status = status;
        }

        public List<Inscricao> getInscritos() {
                return inscritos;
        }

        public void setInscritos(List<Inscricao> inscritos) {
                this.inscritos = inscritos;
        }
}
