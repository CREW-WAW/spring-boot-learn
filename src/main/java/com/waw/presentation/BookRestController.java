package com.waw.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.waw.presentation.response.ResponseDto;
import com.waw.presentation.request.BookRequestDto;
import com.waw.domain.data.Book;
import com.waw.application.BookUseCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Book Rest API", description = "Book 관련 Rest API 메서드")
@RestController
@RequestMapping("/api")
public class BookRestController {

  @Autowired
  private BookUseCase service;
  
  @Operation(summary = "JPA API", description = "JPA 입력 API 입니다.")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public ResponseEntity<ResponseDto> insert(@RequestBody BookRequestDto book) {
    ResponseDto result = ResponseDto.builder().resultData(service.bookInsert(book)).build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Operation(summary = "JPA API", description = "JPA 조회 API 입니다.")
  @RequestMapping(value = "/select", method = RequestMethod.GET)
  public ResponseEntity<ResponseDto> select(String idx) {
    ResponseDto result = ResponseDto.builder().resultData(service.bookSelect(idx == null ? "0" : idx.trim())).build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Operation(summary = "JPA API", description = "JPA 수정 API 입니다.")
  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  public ResponseEntity<ResponseDto> update(@RequestBody Book book) {
    ResponseDto result = ResponseDto.builder().resultData(service.bookUpdate(book)).build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Operation(summary = "JPA API", description = "JPA 삭제 API 입니다.")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public ResponseEntity<ResponseDto> delete(String idx) {
    ResponseDto result = ResponseDto.builder().resultData(service.bookDelete(idx)).build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
