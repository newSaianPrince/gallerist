package com.omersemizoglu.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.omersemizoglu.config.RabbitMQConfig;
import com.omersemizoglu.dto.request.DtoSaledCarIU;
import com.omersemizoglu.enums.CarStatusType;
import com.omersemizoglu.exception.BaseException;
import com.omersemizoglu.exception.ErrorMessage;
import com.omersemizoglu.exception.MessageType;
import com.omersemizoglu.model.Car;
import com.omersemizoglu.model.Customer;
import com.omersemizoglu.model.SaledCar;
import com.omersemizoglu.repository.CarRepository;
import com.omersemizoglu.repository.CustomerRepository;
import com.omersemizoglu.repository.GalleristRepository;
import com.omersemizoglu.repository.SaledCarRepository;
import com.omersemizoglu.service.SaledCarService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SaleConsumer {

    private final SaledCarService saledCarService;

    @RabbitListener(queues = RabbitMQConfig.SALE_QUEUE)
    @Transactional
    public void processSaleRequest(String message) {
        // Mesaj formatı: "customerId:galleristId:carId"
        String[] parts = message.split(":");
        Long customerId = Long.parseLong(parts[0]);
        Long galleristId = Long.parseLong(parts[1]);
        Long carId = Long.parseLong(parts[2]);

        DtoSaledCarIU dtoSaledCarIU = new DtoSaledCarIU();
        dtoSaledCarIU.setCustomerId(customerId);
        dtoSaledCarIU.setGalleristId(galleristId);
        dtoSaledCarIU.setCarId(carId);

        try {
            saledCarService.buyCar(dtoSaledCarIU);
            System.out.println("Araba basariyla satildi! Musteri ID: " + customerId + ", Araba ID: " + carId);
        } catch (Exception e) {
            System.out.println("Satis basarisiz: " + e.getMessage());
        }
    }
}
