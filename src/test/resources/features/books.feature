Feature: Book CRUD operations

  Scenario: User successfully adds a book
    Given library has less than max books
    When user adds book with id 1, title "test", author "Saad" and category "testCategory"
    Then add succeeds
    And book appears in list of all books

  Scenario: User fails to add a book when library is full
    Given library has reached max books
    When user tries to add a new book
    Then add fails

  Scenario: User retrieves all books
    Given library has 2 books
    When user requests all books
    Then 2 books are returned

  Scenario: User retrieves a book by id
    Given book with id 1 exists
    When user requests book with id 1
    Then book with id 1 is returned

  Scenario: User fails to retrieve a book that doesnt exist
    Given book with id 99 doesnt exist
    When user requests book with id 99
    Then no book is returned

  Scenario: User successfully deletes a book
    Given book with id 1 exists
    When user deletes book with id 1
    Then delete succeeds
    And book no longer appears in list of all books

  Scenario: User fails to delete a book that doesnt exist
    Given book with id 99 doesnt exist
    When user deletes book with id 99
    Then delete fails