package br.com.ufmt.backendsgccagendamento.tasks;

import br.com.ufmt.backendsgccagendamento.entities.Pessoa;
import br.com.ufmt.backendsgccagendamento.repositories.PessoaRepository;
import org.slf4j.Logger;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EncryptPasswordsTask {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(EncryptPasswordsTask.class);
    private final PessoaRepository pessoaRepository;
    private final PasswordEncoder passwordEncoder;

    public EncryptPasswordsTask(PessoaRepository pessoaRepository, PasswordEncoder passwordEncoder) {
        this.pessoaRepository = pessoaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent() {
        log.info("Iniciando tarefa de verificação e criptografia de senhas...");

        List<Pessoa> todasAsPessoas = pessoaRepository.findAll();

        List<Pessoa> pessoasParaAtualizar = todasAsPessoas.stream()
                .filter(pessoa -> pessoa.getPassword() != null && !pessoa.getPassword().startsWith("$2a$"))
                .collect(Collectors.toList());

        if (pessoasParaAtualizar.isEmpty()) {
            log.info("Nenhuma senha precisou ser criptografada. Tarefa finalizada.");
            return;
        }

        log.warn("Encontradas {} senhas em texto plano. Criptografando agora...", pessoasParaAtualizar.size());

        for (Pessoa pessoa : pessoasParaAtualizar) {
            String senhaPlana = pessoa.getPassword();
            String senhaCriptografada = passwordEncoder.encode(senhaPlana);
            pessoa.setSenhaCriptografada(senhaCriptografada);
            log.info("Criptografando senha para o usuário: {}", pessoa.getEmail());
        }

        pessoaRepository.saveAll(pessoasParaAtualizar);

        log.info("Sucesso! {} senhas foram criptografadas. Tarefa finalizada.", pessoasParaAtualizar.size());
    }
}