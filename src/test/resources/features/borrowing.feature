Feature: Borrowing books

  Scenario: User successfully borrows an available book
    Given Book with id 1 is available
    And User has 4 borrowed books
    When the user borrows book with id 1
    Then the borrow succeeds
    And book is marked as borrowed

  Scenario: User fails to borrow when at the limit
    Given book with id 1 is available
    And user has borrowed 5 books
    When user tries to borrow book with id 1
    Then the borrow should be rejected with message "Limit to borrowed books is 5. Return a book before you borrow another one"

  Scenario: User fails to borrow when user has 3+ books overdue
    Given book with id 1 is available
    And user has 3 books overdue
    When user tries to borrow book with id 1
    Then the borrow should be rejected with message "Overdue limit is reached. Return a book before borrowing a new one"

  Scenario: User fails to borrow when book is already borrowed
    Given book with id 1 is currently borrowed by user 1
    When user 2 tries to borrow book with id 1
    Then the borrow should be rejected with message "Book is already Borrowed"

  Scenario: User fails to borrow when book doesnt exist
    Given book with id 1 doesnt exist
    When user tries to borrow book with id 1
    Then the borrow should be rejected with message "Book Not Found"

  Scenario: User successfully returns a borrowed book
    Given book with id 1 is borrowed
    When user tries to return book with id 1
    Then the return should be successful

  Scenario: User fails to return a book which wasnt borrowed
    Given book with id 1 is not borrowed
    When user tries to return book with id 1
    Then the return should fail with message "Book is not currently borrowed"
