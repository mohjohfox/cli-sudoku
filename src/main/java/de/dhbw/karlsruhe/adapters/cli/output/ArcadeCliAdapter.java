package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.models.arcade.MathProblem;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.ArcadeOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import java.util.Random;

public class ArcadeCliAdapter implements ArcadeOutputPort {

  Random random = new Random();
  private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

  @Override
  public void introduction() {
    cliOutputPort.writeLine("Welcome to the Math Arcade Game for Kids!");
    cliOutputPort.writeLine(
        "Once upon a time, in a land filled with numbers and puzzles, there lived a small knight named Sir Mathalot.");
    cliOutputPort.writeLine("Sir Mathalot was known for his incredible math skills and bravery.");
    cliOutputPort.writeLine(
        "One day, he embarked on a grand adventure through the Math Arcade to defeat the Math Monster and save the kingdom.");
    cliOutputPort.writeLine("Are you ready to join Sir Mathalot on his quest and conquer the Math Arcade?");
    cliOutputPort.writeLine("If you solve all questions on the way to solve the sudoku you will enter the castle!");
    cliOutputPort.writeLine("Let's begin our journey!");
  }

  @Override
  public void levelOne() {
    cliOutputPort.writeLine("Level 1: The Addition Trail");
    cliOutputPort.writeLine("In this level, Sir Mathalot encounters a trail filled with addition problems.");
    cliOutputPort.writeLine("To proceed, he must correctly solve each addition problem he encounters.");
    cliOutputPort.writeLine("Will you help Sir Mathalot overcome this challenge?");
    cliOutputPort.writeLine("Let's go!");
  }

  @Override
  public void levelTwo() {
    cliOutputPort.writeLine("Level 2: The Subtraction Cavern");
    cliOutputPort.writeLine("Sir Mathalot enters a dark cavern filled with subtraction puzzles.");
    cliOutputPort.writeLine("To navigate through the cavern, he must solve each subtraction problem correctly.");
    cliOutputPort.writeLine("Can you guide Sir Mathalot through this tricky terrain?");
    cliOutputPort.writeLine("Let's continue our adventure!");
  }

  @Override
  public void levelThree() {
    cliOutputPort.writeLine("Level 3: The Multiplication Maze");
    cliOutputPort.writeLine("Sir Mathalot stumbles upon a mysterious maze with multiplication challenges.");
    cliOutputPort.writeLine("To find his way out, he must solve the multiplication problems that block his path.");
    cliOutputPort.writeLine("Will you help Sir Mathalot conquer this maze?");
    cliOutputPort.writeLine("Let's keep moving forward!");
  }

  @Override
  public void levelFour() {
    cliOutputPort.writeLine("Level 4: The Division Dungeon");
    cliOutputPort.writeLine("Sir Mathalot discovers a daunting dungeon filled with division puzzles.");
    cliOutputPort.writeLine("To unlock the dungeon's secrets, he must solve each division problem he encounters.");
    cliOutputPort.writeLine("Can you aid Sir Mathalot in this division quest?");
    cliOutputPort.writeLine("Onward, brave adventurer!");
  }

  @Override
  public void levelFive() {
    cliOutputPort.writeLine("Level 5: The Math Monster's Lair");
    cliOutputPort.writeLine(
        "After a long and challenging journey, Sir Mathalot finally reaches the Math Monster's Lair.");
    cliOutputPort.writeLine("To defeat the Math Monster and save the kingdom, he must face a series of math puzzles.");
    cliOutputPort.writeLine(
        "The Math Monster's power lies in his knowledge of addition, subtraction, multiplication, and division.");
    cliOutputPort.writeLine("Will you help Sir Mathalot conquer the Math Monster and restore peace?");
    cliOutputPort.writeLine("Prepare for the ultimate showdown!");
  }

  @Override
  public void levelSix() {
    cliOutputPort.writeLine("Level 6: The Fraction Forest");
    cliOutputPort.writeLine("Sir Mathalot continues his adventure through the Fraction Forest.");
    cliOutputPort.writeLine(
        "Here, he encounters fractions and must solve problems involving addition, subtraction, multiplication, and division of fractions.");
    cliOutputPort.writeLine("Can you navigate through the Fraction Forest and help Sir Mathalot master fractions?");
    cliOutputPort.writeLine("Let's dive into the world of fractions!");
  }

  @Override
  public void levelSeven() {
    cliOutputPort.writeLine("Level 7: The Geometry Garden");
    cliOutputPort.writeLine("Sir Mathalot arrives at the Geometry Garden, a place filled with shapes and angles.");
    cliOutputPort.writeLine(
        "To unlock the garden's hidden treasures, he must solve geometry problems and identify different shapes.");
    cliOutputPort.writeLine("Will you assist Sir Mathalot in exploring the Geometry Garden?");
    cliOutputPort.writeLine("Let's discover the wonders of geometry!");
  }

  @Override
  public void levelEight() {
    cliOutputPort.writeLine("Level 8: The Time Travel Adventure");
    cliOutputPort.writeLine("Sir Mathalot embarks on a thrilling time travel adventure.");
    cliOutputPort.writeLine(
        "He encounters time-related math problems, including calculating durations, reading clocks, and solving time word problems.");
    cliOutputPort.writeLine("Can you join Sir Mathalot on this time travel quest?");
    cliOutputPort.writeLine("Let's journey through the dimensions of time!");
  }

  @Override
  public void levelNine() {
    cliOutputPort.writeLine("Level 9: The Data Dungeon");
    cliOutputPort.writeLine(
        "Sir Mathalot finds himself in the depths of the Data Dungeon, a place filled with graphs, charts, and statistics.");
    cliOutputPort.writeLine(
        "To uncover the dungeon's secrets, he must analyze data, interpret graphs, and solve data-related problems.");
    cliOutputPort.writeLine("Can you help Sir Mathalot conquer the Data Dungeon?");
    cliOutputPort.writeLine("Let's dive into the world of data!");
  }

  @Override
  public void levelTen() {
    cliOutputPort.writeLine("Level 10: The Final Challenge");
    cliOutputPort.writeLine("Congratulations on reaching this Level!");
    cliOutputPort.writeLine("Sir Mathalot faces the final challenge, a test of all his math skills.");
    cliOutputPort.writeLine(
        "He must solve a variety of math problems, including addition, subtraction, multiplication, division, fractions, geometry, time, and data.");
    cliOutputPort.writeLine("Can you stand with Sir Mathalot and help him overcome this ultimate test?");
    cliOutputPort.writeLine("Prepare for the final showdown!");
  }

  @Override
  public void levelEleven() {
    cliOutputPort.writeLine("Level 11: The Money Market");
    cliOutputPort.writeLine("Sir Mathalot enters the bustling Money Market, a place filled with coins and currency.");
    cliOutputPort.writeLine(
        "To navigate through the market, he must solve money-related math problems, including counting coins, making change, and calculating total costs.");
    cliOutputPort.writeLine(
        "Can you guide Sir Mathalot through the Money Market and help him master the world of money?");
    cliOutputPort.writeLine("Let's dive into the exciting realm of finances!");
  }

  @Override
  public void levelTwelve() {
    cliOutputPort.writeLine("Level 12: The Probability Puzzle");
    cliOutputPort.writeLine("Sir Mathalot encounters a mysterious puzzle involving probability.");
    cliOutputPort.writeLine(
        "To solve the puzzle, he must understand concepts like likelihood, chance, and probability calculations.");
    cliOutputPort.writeLine("Will you help Sir Mathalot unravel the secrets of probability?");
    cliOutputPort.writeLine("Let's embark on this mind-boggling journey!");
  }

  @Override
  public void levelThirteen() {
    cliOutputPort.writeLine("Level 13: The Measurement Mission");
    cliOutputPort.writeLine("Sir Mathalot embarks on a critical mission to master measurements.");
    cliOutputPort.writeLine(
        "He encounters various measurement tools and must solve problems involving length, weight, capacity, and temperature conversions.");
    cliOutputPort.writeLine("Can you aid Sir Mathalot in this crucial measurement mission?");
    cliOutputPort.writeLine("Let's embrace the world of measurements!");
  }

  @Override
  public void levelFourteen() {
    cliOutputPort.writeLine("Level 14: The Algebra Adventure");
    cliOutputPort.writeLine("Sir Mathalot embarks on an adventurous journey through the realm of algebra.");
    cliOutputPort.writeLine("He encounters algebraic expressions, equations, and word problems.");
    cliOutputPort.writeLine(
        "To overcome this challenge, he must simplify expressions, solve equations, and find unknown values.");
    cliOutputPort.writeLine("Will you join Sir Mathalot in this thrilling algebraic adventure?");
    cliOutputPort.writeLine("Let's unlock the secrets of algebra!");
  }

  @Override
  public void levelFifteen() {
    cliOutputPort.writeLine("Level 15: The Time Warp");
    cliOutputPort.writeLine(
        "Sir Mathalot finds himself caught in a mysterious time warp, where time behaves strangely.");
    cliOutputPort.writeLine(
        "To navigate through the time warp, he must solve time-related puzzles, including calculating durations, time zones, and solving time word problems.");
    cliOutputPort.writeLine("Can you guide Sir Mathalot through this time-bending challenge?");
    cliOutputPort.writeLine("Let's unravel the mysteries of time!");
  }

  @Override
  public void levelSixteen() {
    cliOutputPort.writeLine("Level 16: The Logic Labyrinth");
    cliOutputPort.writeLine(
        "Sir Mathalot enters the Logic Labyrinth, a place filled with mind-bending logical puzzles.");
    cliOutputPort.writeLine(
        "To navigate through the labyrinth, he must solve problems involving logical reasoning, patterns, and sequences.");
    cliOutputPort.writeLine("Can you help Sir Mathalot unravel the secrets of logic and conquer the labyrinth?");
    cliOutputPort.writeLine("Let's embark on this perplexing journey of logic!");
  }

  @Override
  public void levelSeventeen() {
    cliOutputPort.writeLine("Level 17: The Geometry Galaxy");
    cliOutputPort.writeLine("Sir Mathalot arrives in the Geometry Galaxy, a place filled with geometric wonders.");
    cliOutputPort.writeLine(
        "He must solve geometry problems, identify shapes, calculate areas, perimeters, and angles.");
    cliOutputPort.writeLine("Will you join Sir Mathalot on this cosmic geometry adventure?");
    cliOutputPort.writeLine("Let's explore the wonders of geometry in the galaxy!");
  }

  @Override
  public void levelEighteen() {
    cliOutputPort.writeLine("Level 18: The Fractions Fortress");
    cliOutputPort.writeLine("Sir Mathalot encounters the Fractions Fortress, a stronghold of fractions.");
    cliOutputPort.writeLine(
        "To conquer the fortress, he must solve problems involving fractions, including addition, subtraction, multiplication, and division.");
    cliOutputPort.writeLine(
        "Can you guide Sir Mathalot through the Fractions Fortress and conquer the world of fractions?");
    cliOutputPort.writeLine("Let's master the realm of fractions!");
  }

  @Override
  public void levelNineteen() {
    cliOutputPort.writeLine("Level 19: The Data Dungeon");
    cliOutputPort.writeLine(
        "Sir Mathalot delves into the depths of the Data Dungeon, a place filled with data and statistics.");
    cliOutputPort.writeLine(
        "To unlock the dungeon's secrets, he must analyze data, interpret graphs, and solve data-related problems.");
    cliOutputPort.writeLine("Will you help Sir Mathalot conquer the Data Dungeon?");
    cliOutputPort.writeLine("Let's dive into the world of data!");
  }

  @Override
  public void levelTwenty() {
    cliOutputPort.writeLine("Level 20: The Final Challenge");
    cliOutputPort.writeLine("Congratulations on reaching Level 20!");
    cliOutputPort.writeLine("Sir Mathalot faces the final challenge, a test of all his math skills.");
    cliOutputPort.writeLine(
        "He must solve a variety of math problems, including addition, subtraction, multiplication, division, fractions, geometry, time, data, logic, algebra, and more.");
    cliOutputPort.writeLine("Can you stand with Sir Mathalot and help him overcome this ultimate test?");
    cliOutputPort.writeLine("Prepare for the final showdown!");
  }

  @Override
  public void conclusion() {
    cliOutputPort.writeLine("Congratulations, brave adventurer!");
    cliOutputPort.writeLine(
        "You have successfully completed all the levels of the Math Arcade game with Sir Mathalot.");
    cliOutputPort.writeLine("Your math skills and bravery have saved the kingdom from the Math Monster's grasp.");
    cliOutputPort.writeLine("You solved the Sudoku and made your way to the castle!");
    cliOutputPort.writeLine("You are a true math champion!");
    cliOutputPort.writeLine("Thank you for playing the Math Arcade game for kids.");
    cliOutputPort.writeLine("We hope you had a great time and continue your math journey.");
    cliOutputPort.writeLine("Stay curious, keep learning, and always embrace the power of math!");
    cliOutputPort.writeLine("Goodbye!");
  }

  @Override
  public void emptyLine() {
    cliOutputPort.writeEmptyLine();
  }

  @Override
  public void sudokuIntroduction() {
    cliOutputPort.writeLine("To reach the castle Sir Mathalot has to solve the following Sudoku:");
  }

  @Override
  public void congratulationAfterSolving() {
    int option = this.random.nextInt();
    if (option < 0.4) {
      cliOutputPort.writeLine("Congratulations on completing the previous Level!");
    } else if (option < 0.8) {
      cliOutputPort.writeLine("Well done on completing the previous level!");
    } else {
      cliOutputPort.writeLine("Congratulations on defeating the Math Monster!");
    }
  }

  @Override
  public void mathProblem(MathProblem mathProblemToSolve) {
    cliOutputPort.writeLine("A almost impossible to solve math problem has arisen.");
    cliOutputPort.writeLine("But as soon as Sir Mathalot has a closer look he realizes, that it's very easy.");
    cliOutputPort.writeLine("Please type in the answer of the following math problem:");

    cliOutputPort.writeEmptyLine();

    cliOutputPort.writeLine(mathProblemToSolve.getProblemAsText());
  }

  @Override
  public void optionError() {
    cliOutputPort.writeLine("Invalid Input - Please enter a valid number!");
  }

  @Override
  public void correctAnswer() {
    cliOutputPort.writeLine("Yeah, that's correct!");
    cliOutputPort.writeLine("Congratulations. You are getting closer to Sir Mathalots castle!");
  }

  @Override
  public void wrongAnswer() {
    cliOutputPort.writeLine("Pity, that's not the correct answer!");
    cliOutputPort.writeLine("Please try again to solve the question.");
    cliOutputPort.writeLine("Stay calm and breath in and out. Sir Mathalot will honour it.");
  }

  @Override
  public void singleMathProblem(MathProblem mathProblemToSolve) {
    cliOutputPort.writeLine(mathProblemToSolve.getProblemAsText());
  }

}
