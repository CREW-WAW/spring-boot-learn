package com.waw.presentation;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value = "MainRestController")
@RestController
@RequestMapping("/api")
public class BookRestController {

  @Autowired
  private BookUseCase service;
  
  @ApiOperation(value = "JPA 입력 API", tags = "JPA")
  @ApiImplicitParams({
    @ApiImplicitParam
    (name = "bookName", value = "책 이름", required = true),
  })
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public ResponseEntity<ResponseDto> insert(@RequestBody BookRequestDto book) {
    ResponseDto result = ResponseDto.builder().resultData(service.bookInsert(book)).build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @ApiImplicitParams({
    @ApiImplicitParam
    (name = "idx", value = "Index", required = true),
  })
  @ApiOperation(value = "JPA 조회 API", tags = "JPA")
  @RequestMapping(value = "/select", method = RequestMethod.GET)
  public ResponseEntity<ResponseDto> select(String idx) {
    ResponseDto result = ResponseDto.builder().resultData(service.bookSelect(idx == null ? "0" : idx.trim())).build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @ApiImplicitParams({
    @ApiImplicitParam
    (name = "idx", value = "조회 Index", required = true),
    @ApiImplicitParam
    (name = "bookName", value = "책 이름", required = true),
  })
  @ApiOperation(value = "JPA 수정 API", tags = "JPA")
  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  public ResponseEntity<ResponseDto> update(@RequestBody Book book) {
    ResponseDto result = ResponseDto.builder().resultData(service.bookUpdate(book)).build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @ApiOperation(value = "JPA 삭제 API", tags = "JPA")
  @ApiImplicitParams({
    @ApiImplicitParam
    (name = "idx", value = "조회 Index", required = true),
  })
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public ResponseEntity<ResponseDto> delete(String idx) {
    ResponseDto result = ResponseDto.builder().resultData(service.bookDelete(idx)).build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
