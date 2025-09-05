package br.com.ufmt.backendsgccagendamento.producers;

import br.com.ufmt.backendsgccagendamento.dtos.ConsultaStatusDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConsultaProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    public void publishMessage(ConsultaStatusDTO dto) {
        rabbitTemplate.convertAndSend(queue, dto);
    }
}