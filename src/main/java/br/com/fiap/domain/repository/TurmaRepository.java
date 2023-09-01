package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Aluno;
import br.com.fiap.domain.entity.Turma;

import java.util.*;

public class TurmaRepository implements Repository<Turma, Long>{

    private Set<Turma> turmas;
    public TurmaRepository() {
        turmas = new LinkedHashSet<>();
    }

    private static volatile TurmaRepository instance;
    public static TurmaRepository of(){
        TurmaRepository result = instance;
        if(Objects.nonNull( result) ){
            return result;
        }

        synchronized (TurmaRepository.class){
            if(Objects.isNull( null )){
                instance = new TurmaRepository();
            }
            return instance;
        }
    }

    @Override
    public List<Turma> findAll() {
        return turmas.stream().toList();
    }
    @Override
    public Turma findById(Long id) {
        return turmas.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Turma> findByName(String texto) {
        return turmas.stream().filter(t -> t.getCurso().getNome().toLowerCase().contains(texto.toLowerCase())).toList();
    }

    @Override
    public Turma persist(Turma turma) {
        if (Objects.isNull(turma)) return null;
        if(Objects.isNull(turma.getId())) turma.setId(turmas.size() + 1L);
        turmas.add(turma);
        return turma;
    }
}
