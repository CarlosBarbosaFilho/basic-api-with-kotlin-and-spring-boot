package br.com.cbgomes.promotion.resource

import br.com.cbgomes.promotion.model.dto.PromotionDto
import br.com.cbgomes.promotion.model.form.PromotionForm
import br.com.cbgomes.promotion.service.PromotionService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@Api("Endpoint to flow of promotions")
@RestController
@RequestMapping("/api/promotions")
class PromotionResource(val promotionService: PromotionService) {

    private val logger : Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping
    @ApiOperation(value = "Return all promotions")
    @ApiResponses(value = [
        ApiResponse(code = 302, message = "Return all promotions"),
        ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
        ApiResponse(code = 404, message = "Sorry, page not found"),
        ApiResponse(code = 401, message = "Sorry, unauthorized access to service, contact the administrator of system"),
        ApiResponse(code = 403, message = "Sorry, you dont have permission to access this service ")])
    @ResponseStatus(HttpStatus.FOUND)
    fun getAllPromotions(): ResponseEntity<List<PromotionDto>> {
        this.logger.info("Request to return all promotions, if exists in database")
        return ResponseEntity.ok(this.promotionService.getAllPromotions())
    }

    @PostMapping
    @ApiOperation(value = "Save promotion")
    @ApiResponses(value = [
        ApiResponse(code = 201, message = "Return the promotion created"),
        ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
        ApiResponse(code = 404, message = "Sorry, page not found"),
        ApiResponse(code = 401, message = "Sorry, unauthorized access to service, contact the administrator of system"),
        ApiResponse(code = 403, message = "Sorry, you dont have permission to access this service ")])
    @ResponseStatus(HttpStatus.CREATED)
    fun createPromotion(@RequestBody form: PromotionForm): ResponseEntity<PromotionDto>{
        this.logger.info("request to save promotion, parameters : $form" )
        return ResponseEntity.ok(this.promotionService.save(form))
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Return promotions by id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Return the promotion by id"),
        ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
        ApiResponse(code = 404, message = "Sorry, page not found"),
        ApiResponse(code = 401, message = "Sorry, unauthorized access to service, contact the administrator of system"),
        ApiResponse(code = 403, message = "Sorry, you dont have permission to access this service ")])
    @ResponseStatus(HttpStatus.OK)
    fun getPromotionById(@PathVariable("id") id: Long): ResponseEntity<PromotionDto?>{
        this.logger.info("request to return promotion with parameter : $id")
        return if (this.promotionService.getByIdPromotion(id) != null){
            ResponseEntity.ok(this.promotionService.getByIdPromotion(id))
        }else {
            this.logger.error("Entity not found to id : $id")
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remove promotions by id")
    @ApiResponses(value = [
        ApiResponse(code = 204, message = "Remove the promotion by id"),
        ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
        ApiResponse(code = 404, message = "Sorry, page not found"),
        ApiResponse(code = 401, message = "Sorry, unauthorized access to service, contact the administrator of system"),
        ApiResponse(code = 403, message = "Sorry, you dont have permission to access this service ")])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePromotionById(@PathVariable("id") id: Long): ResponseEntity<String> {
        this.logger.info("request to delete promotion with parameter : $id" )
        this.promotionService.deletePromotionById(id)
        return ResponseEntity.ok("\"Promotion removed with success\"")
    }


    @GetMapping("/promotions-by-date")
    @ApiOperation(value = "Return all promotions by date")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Return all  promotion by date"),
        ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
        ApiResponse(code = 404, message = "Sorry, page not found"),
        ApiResponse(code = 401, message = "Sorry, unauthorized access to service, contact the administrator of system"),
        ApiResponse(code = 403, message = "Sorry, you dont have permission to access this service ")])
    @ResponseStatus(HttpStatus.OK)
    fun getPromotionsByDates(
            @RequestParam("initial_date") @DateTimeFormat(pattern = "yyyy-MM-dd") initial_date: LocalDate,
            @RequestParam("final_date") @DateTimeFormat(pattern = "yyyy-MM-dd") final_date: LocalDate): ResponseEntity<List<PromotionDto>> {
        this.logger.info("request to return all promotion by date informed" )
        return ResponseEntity.ok(this.promotionService.getPromotionsByInitialDataAndFinalDate(initial_date, final_date))
    }

    @GetMapping("/exists-promotions")
    @ApiOperation(value = "checks if a promotions is active")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "checks if a promotions is active in date today"),
        ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
        ApiResponse(code = 404, message = "Sorry, page not found"),
        ApiResponse(code = 401, message = "Sorry, unauthorized access to service, contact the administrator of system"),
        ApiResponse(code = 403, message = "Sorry, you dont have permission to access this service ")])
    @ResponseStatus(HttpStatus.OK)
    fun isActive(): ResponseEntity<Any>?{
        return ResponseEntity.ok(this.promotionService.existsPromotions() as Any)
    }
}