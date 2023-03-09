package com.waw.application;

import java.util.Optional;

import com.waw.presentation.request.BookRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.waw.common.exception.NoValidException;
import com.waw.common.exception.SavingsException;
import com.waw.domain.data.Book;
import com.waw.domain.BookRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookUseCase {

  private final BookRepository repository;

  public Book bookInsert(BookRequestDto book) throws SavingsException {
    if (book == null || book.getBookName() == null)
      throw new NoValidException("BookName Check");

    return repository.save(Book.builder().bookName(book.getBookName()).build());
  }

  public Optional<Book> bookSelect(String idx) {
    return repository.findById(Long.parseLong(idx));
  }

  public Book bookUpdate(Book book) throws SavingsException {
    if (book == null || book.getBookName() == null || book.getIdx() == null)
      throw new NoValidException("Parameter Data Check");
    Book targetBook = repository.findById(book.getIdx()).get();
    targetBook.setBookName(book.getBookName());

    return repository.save(targetBook);
  }

  public boolean bookDelete(String idx) {
    boolean result = false;

    try {

      repository.deleteById(Long.parseLong(idx));
      result = true;

    } catch (Exception e) {
      result = false;
    }

    return result;
  }
}
