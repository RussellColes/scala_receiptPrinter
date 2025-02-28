This is a mini project for learning Scala and practising some of the basic principals of functional programming.
I undertook this project with only a few hours of learning in Scala. I used a combination of course notes, feedback from a coach and online research in order to improve my code.
The main feature of functional programming I focused on here was the concept of pure functions.
All the functionality was written using TDD.
I was working in IntelliJ IDEA.

The task was to create a Till for a cafe which could work at multiple cafes. The till should create and update orders and then finalise the order by printing a formatted receipt. 

These are the challenge instructions:
For this challenge you'll have to:
- Create a new Scala project, adding a testing library as a dependency
- Create a ReceiptPrinter class 
- Use TDD to write the code of the receipt method
- Create private methods to extract some logic from the receipt method

stretch Use TDD to create a new class called Till which takes a CafeDetail instance at initialisation.
It should have methods that:
- Show the menu
- Allow the user to add an item to their order or throw an error if what they enter is not on the menu
- Finalise the order and print the statement (by calling on the receipt printer)
