package com.assignment3.rental;

import com.assignment3.Constants;
import com.assignment3.car.CarService;
import com.assignment3.car.model.Car;
import com.assignment3.contract.Contract;
import com.assignment3.customer.CustomerService;
import com.assignment3.customer.model.Customer;
import com.assignment3.rental.model.Rental;
import com.assignment3.rental.model.dto.RentalDTO;
import com.assignment3.rental.model.dto.RentalDisplayDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final CarService carService;

    private final CustomerService customerService;

    private final RentalRepository rentalRepository;

    private final RentalMapper rentalMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("otomy98@yahoo.com");

        msg.setSubject("Car Rental Contract");
        msg.setText("This is an automated mail with the confirmation of the rental.");

        javaMailSender.send(msg);
    }

    public List<RentalDisplayDto> findAll() {
        return rentalRepository.findAll().stream()
                .map(rentalMapper::toDTO)
                .collect(Collectors.toList());
    }

    public boolean create(RentalDTO rentalDTO) {
        Car car = carService.findById(rentalDTO.getCarId());
        Customer customer = customerService.findByNameAndPersonalNb(rentalDTO.getCustomerName(), rentalDTO.getCnp());

        LocalDate from = LocalDate.parse(rentalDTO.getFrom(), Constants.formatter);
        LocalDate until = LocalDate.parse(rentalDTO.getUntil(), Constants.formatter);

        if (rentalRepository.findIfNotFreeSlot(car, from, until).isEmpty()) {
            Rental rental = Rental.builder()
                    .car(car)
                    .customer(customer)
                    .fromDate(from)
                    .untilDate(until)
                    .location(rentalDTO.getLocation())
                    .price((float) (DAYS.between(from, until) * car.getPrice()))
                    .build();

            rentalRepository.save(rental);
            carService.setRentedStatus(car, true);
            Contract.export(rental);
            return true;
        }
        else{
            List<Rental> rentals = rentalRepository.findIfNotFreeSlot(car, from, until);
            System.out.println(rentals.toString());
            System.out.println("Car is rented!");
            return false;
        }
    }


}
