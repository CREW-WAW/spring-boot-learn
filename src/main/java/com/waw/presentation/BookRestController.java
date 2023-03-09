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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@Tag(name = "Book Rest API", description = "Book 관련 Rest API 메서드")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookRestController {

  private final BookUseCase service;
  
  @Operation(summary = "Book Insert API", description = "Book 입력 API 입니다.")
  @PostMapping(value = "/books")
  public ResponseEntity<?> insertBook(@RequestBody BookRequestDto book) {
    final Book savedBook = service.bookInsert(book);
    return ResponseEntity.created(ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedBook.getIdx())
        .toUri())
        .build();
  }

  @Operation(summary = "Book Select API", description = "Book 조회 API 입니다.")
  @GetMapping(value = "/books/{idx}")
  public ResponseEntity<?> selectBook(@PathVariable("idx") String idx) {
    if (idx == null) {
      return ResponseEntity.notFound().build();
    }
    ResponseDto result = ResponseDto.builder().resultData(service.bookSelect(idx.trim())).build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Operation(summary = "Book Update API", description = "Book 수정 API 입니다.")
  @PutMapping(value = "/book/{idx}")
  public ResponseEntity<ResponseDto> updateBook(@RequestBody Book book) {
    ResponseDto result = ResponseDto.builder().resultData(service.bookUpdate(book)).build();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Operation(summary = "Book Delete API", description = "Book 삭제 API 입니다.")
  @DeleteMapping(value = "/books/{idx}")
  public ResponseEntity<?> deleteBook(@PathVariable("idx") String idx) {
    service.bookDelete(idx);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
