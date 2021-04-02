package com.projeto.beerstock.controller;

import com.projeto.beerstock.dto.QuantityDTO;
import com.projeto.beerstock.exception.BeerStockEndsException;
import com.projeto.beerstock.exception.BeerStockExceededException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.projeto.beerstock.dto.BeerDTO;
import com.projeto.beerstock.exception.BeerAlreadyRegisteredException;
import com.projeto.beerstock.exception.BeerNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api("Manages beer stock")
public interface BeerControllerDocs {

    @ApiOperation(value = "Beer creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success beer creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    BeerDTO createBeer(BeerDTO beerDTO) throws BeerAlreadyRegisteredException;

    @ApiOperation(value = "Returns beer found by a given name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success beer found in the system"),
            @ApiResponse(code = 404, message = "Beer with given name not found.")
    })
    BeerDTO findByName(@PathVariable String name) throws BeerNotFoundException;

    @ApiOperation(value = "Returns a list of all beers registered in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all beers registered in the system"),
    })
    List<BeerDTO> listBeers();

    @ApiOperation(value = "Delete a beer found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success beer deleted in the system"),
            @ApiResponse(code = 404, message = "Beer with given id not found.")
    })
    void deleteById(@PathVariable Long id) throws BeerNotFoundException;

    @ApiOperation(value = "Increment quantity of beers in stock")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Final quantity cannot be greater than max quantity"),
            @ApiResponse(code = 404, message = "Beer with given id not found.")
    })
    BeerDTO increment(@PathVariable Long id, @RequestBody QuantityDTO quantityDTO) throws BeerNotFoundException, BeerStockExceededException;

    @ApiOperation(value = "Decrement quantity of beers in stock")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Final quantity cannot be smaller than zero"),
            @ApiResponse(code = 404, message = "Beer with given id not found.")
    })
    BeerDTO decrement(@PathVariable Long id, @RequestBody QuantityDTO quantityDTO) throws BeerNotFoundException, BeerStockEndsException;
}
