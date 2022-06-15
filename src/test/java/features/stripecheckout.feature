@Ebookstripe
Feature: Ebooks Signup Login test
  Scenario: Ebooks Signup Login test
  Given Go to the team Home page 
  Then click on signup button
  Then User creates a new account
  Then verify that user is successfully signup 
  Then click on ebooks button
  Then verify the checkout process via stripe
  Then delete the account
  Then verify the account is successfully deleted
 