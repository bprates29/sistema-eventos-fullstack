package br.com.bprates.eventos.Sistema.de.eventos.repository;

import br.com.bprates.eventos.Sistema.de.eventos.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
}
