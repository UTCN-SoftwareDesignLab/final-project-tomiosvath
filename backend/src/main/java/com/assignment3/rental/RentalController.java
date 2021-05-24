package com.assignment3.rental;

import com.assignment3.Constants;
import com.assignment3.contract.Contract;
import com.assignment3.rental.model.dto.RentalDTO;
import com.assignment3.rental.model.dto.RentalDisplayDto;
import lombok.RequiredArgsConstructor;
import mail.EmailServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.assignment3.UrlMapping.RENTAL;

@RestController
@RequiredArgsConstructor
@RequestMapping(RENTAL)
public class RentalController {

    private final RentalService rentalService;

    @GetMapping
    public List<RentalDisplayDto> allRentals(){
        return rentalService.findAll();
    }

    @PostMapping
    public void create(@RequestBody RentalDTO rentalDTO, HttpServletResponse response){
        if (rentalService.create(rentalDTO)){
            try {
                rentalService.sendEmail();
                InputStream is = new FileInputStream(Constants.contractName);
                org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
                response.flushBuffer();
                is.close();
                Contract.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
