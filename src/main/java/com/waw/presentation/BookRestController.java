package com.waw.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.waw.presentation.response.ResponseDto;
import com.waw.presentation.request.BookRequestDto;
import com.waw.domain.data.Book;
import com.waw.application.BookUseCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Book Rest API", description = "Book 관련 Rest API 메서드")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookRestController {

  private final BookUseCase service;
  
  @Operation(summary = "Book Insert API", description = "Book 입력 API 입니다.")
  @PostMapping(value = "/insert")
  public ResponseEntity<ResponseDto> insertBook(@RequestBody BookRequestDto book) {
    ResponseDto result = ResponseDto.builder().resultData(service.bookInsert(book)).build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Operation(summary = "Book Select API", description = "Book 조회 API 입니다.")
  @GetMapping(value = "/select")
  public ResponseEntity<ResponseDto> selectBook(String idx) {
    ResponseDto result = ResponseDto.builder().resultData(service.bookSelect(idx == null ? "0" : idx.trim())).build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Operation(summary = "Book Update API", description = "Book 수정 API 입니다.")
  @PutMapping(value = "/update")
  public ResponseEntity<ResponseDto> updateBook(@RequestBody Book book) {
    ResponseDto result = ResponseDto.builder().resultData(service.bookUpdate(book)).build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Operation(summary = "Book Delete API", description = "Book 삭제 API 입니다.")
  @DeleteMapping(value = "/delete")
  public ResponseEntity<ResponseDto> deleteBook(String idx) {
    ResponseDto result = ResponseDto.builder().resultData(service.bookDelete(idx)).build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
