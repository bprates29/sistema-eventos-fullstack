package br.com.bprates.eventos.Sistema.de.eventos.repository;

import br.com.bprates.eventos.Sistema.de.eventos.model.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    Page<Evento> findByNome(String nomeEvento, Pageable paginacao);
}
