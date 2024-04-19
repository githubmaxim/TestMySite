Feature: Test my website

  Scenario: Test main page
    Given open Webdriver1 for site "http://localhost:8080"
    When check working the button Working_with_fields
    And check working the button Fields
    And check working the button Working_with_files
    Then check working the button Files


  Scenario: Test  "Working with fields" / "Fields" page
    Given open Webdriver2 for site "http://localhost:8080/workingWithFields.html"
    When check working the button On_Main to go to another page
    And check working the button Main to go to enother page
    And check working the button Working_with_files to go to another page
    And check working the button Files to go to enother page
    And check working the button load_all_Users to get data for all users
    And check working the button Create_user to upload new user data
    And check working the button Delete
    Then ckeck working the button Send that load data to the server using form method

#
#  Scenario: Test  "Working wit files" / "Files" page
#    Given open Webdriver3 for site "http://localhost:8080"
#    When
#    And
#    Then
