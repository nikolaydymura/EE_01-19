package edu.ee.booksviewservice;

import edu.ee.booksviewservice.config.PageTitle;
import edu.ee.booksviewservice.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;

@Controller
public class BookController {

  @Autowired
  private BookService bookService;

  @RequestMapping(method = RequestMethod.GET, path = "/books")
  public String displayBooks(Model model,
                             @Autowired PageTitle pageTitle) {
    List<BookDTO> books = bookService.getBooks();
    model.addAttribute("page_title", pageTitle.getTitle());
    model.addAttribute("books", books);
    return "books_list";
  }
}
