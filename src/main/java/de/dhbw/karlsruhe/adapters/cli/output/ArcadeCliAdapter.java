package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.models.MathProblem;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.ArcadeOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.Random;

public class ArcadeCliAdapter implements ArcadeOutputPort {

    Random random = new Random();
    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void introduction() {
        cliOutputPort.write("Welcome to the Math Arcade Game for Kids!");
        cliOutputPort.write("Once upon a time, in a land filled with numbers and puzzles, there lived a small knight named Sir Mathalot.");
        cliOutputPort.write("Sir Mathalot was known for his incredible math skills and bravery.");
        cliOutputPort.write("One day, he embarked on a grand adventure through the Math Arcade to defeat the Math Monster and save the kingdom.");
        cliOutputPort.write("Are you ready to join Sir Mathalot on his quest and conquer the Math Arcade?");
        cliOutputPort.write("If you solve all questions on the way to solve the sudoku you will enter the castle!");
        cliOutputPort.write("Let's begin our journey!");
    }

    @Override
    public void levelOne() {
        cliOutputPort.write("Level 1: The Addition Trail");
        cliOutputPort.write("In this level, Sir Mathalot encounters a trail filled with addition problems.");
        cliOutputPort.write("To proceed, he must correctly solve each addition problem he encounters.");
        cliOutputPort.write("Will you help Sir Mathalot overcome this challenge?");
        cliOutputPort.write("Let's go!");
    }

    @Override
    public void levelTwo() {
        cliOutputPort.write("Level 2: The Subtraction Cavern");
        cliOutputPort.write("Sir Mathalot enters a dark cavern filled with subtraction puzzles.");
        cliOutputPort.write("To navigate through the cavern, he must solve each subtraction problem correctly.");
        cliOutputPort.write("Can you guide Sir Mathalot through this tricky terrain?");
        cliOutputPort.write("Let's continue our adventure!");
    }

    @Override
    public void levelThree() {
        cliOutputPort.write("Level 3: The Multiplication Maze");
        cliOutputPort.write("Sir Mathalot stumbles upon a mysterious maze with multiplication challenges.");
        cliOutputPort.write("To find his way out, he must solve the multiplication problems that block his path.");
        cliOutputPort.write("Will you help Sir Mathalot conquer this maze?");
        cliOutputPort.write("Let's keep moving forward!");
    }

    @Override
    public void levelFour() {
        cliOutputPort.write("Level 4: The Division Dungeon");
        cliOutputPort.write("Sir Mathalot discovers a daunting dungeon filled with division puzzles.");
        cliOutputPort.write("To unlock the dungeon's secrets, he must solve each division problem he encounters.");
        cliOutputPort.write("Can you aid Sir Mathalot in this division quest?");
        cliOutputPort.write("Onward, brave adventurer!");
    }

    @Override
    public void levelFive() {
        cliOutputPort.write("Level 5: The Math Monster's Lair");
        cliOutputPort.write("After a long and challenging journey, Sir Mathalot finally reaches the Math Monster's Lair.");
        cliOutputPort.write("To defeat the Math Monster and save the kingdom, he must face a series of math puzzles.");
        cliOutputPort.write("The Math Monster's power lies in his knowledge of addition, subtraction, multiplication, and division.");
        cliOutputPort.write("Will you help Sir Mathalot conquer the Math Monster and restore peace?");
        cliOutputPort.write("Prepare for the ultimate showdown!");
    }

    @Override
    public void levelSix() {
        cliOutputPort.write("Level 6: The Fraction Forest");
        cliOutputPort.write("Sir Mathalot continues his adventure through the Fraction Forest.");
        cliOutputPort.write("Here, he encounters fractions and must solve problems involving addition, subtraction, multiplication, and division of fractions.");
        cliOutputPort.write("Can you navigate through the Fraction Forest and help Sir Mathalot master fractions?");
        cliOutputPort.write("Let's dive into the world of fractions!");
    }

    @Override
    public void levelSeven() {
        cliOutputPort.write("Level 7: The Geometry Garden");
        cliOutputPort.write("Sir Mathalot arrives at the Geometry Garden, a place filled with shapes and angles.");
        cliOutputPort.write("To unlock the garden's hidden treasures, he must solve geometry problems and identify different shapes.");
        cliOutputPort.write("Will you assist Sir Mathalot in exploring the Geometry Garden?");
        cliOutputPort.write("Let's discover the wonders of geometry!");
    }

    @Override
    public void levelEight() {
        cliOutputPort.write("Level 8: The Time Travel Adventure");
        cliOutputPort.write("Sir Mathalot embarks on a thrilling time travel adventure.");
        cliOutputPort.write("He encounters time-related math problems, including calculating durations, reading clocks, and solving time word problems.");
        cliOutputPort.write("Can you join Sir Mathalot on this time travel quest?");
        cliOutputPort.write("Let's journey through the dimensions of time!");
    }

    @Override
    public void levelNine() {
        cliOutputPort.write("Level 9: The Data Dungeon");
        cliOutputPort.write("Sir Mathalot finds himself in the depths of the Data Dungeon, a place filled with graphs, charts, and statistics.");
        cliOutputPort.write("To uncover the dungeon's secrets, he must analyze data, interpret graphs, and solve data-related problems.");
        cliOutputPort.write("Can you help Sir Mathalot conquer the Data Dungeon?");
        cliOutputPort.write("Let's dive into the world of data!");
    }

    @Override
    public void levelTen() {
        cliOutputPort.write("Level 10: The Final Challenge");
        cliOutputPort.write("Congratulations on reaching this Level!");
        cliOutputPort.write("Sir Mathalot faces the final challenge, a test of all his math skills.");
        cliOutputPort.write("He must solve a variety of math problems, including addition, subtraction, multiplication, division, fractions, geometry, time, and data.");
        cliOutputPort.write("Can you stand with Sir Mathalot and help him overcome this ultimate test?");
        cliOutputPort.write("Prepare for the final showdown!");
    }

    @Override
    public void levelEleven() {
        cliOutputPort.write("Level 11: The Money Market");
        cliOutputPort.write("Sir Mathalot enters the bustling Money Market, a place filled with coins and currency.");
        cliOutputPort.write("To navigate through the market, he must solve money-related math problems, including counting coins, making change, and calculating total costs.");
        cliOutputPort.write("Can you guide Sir Mathalot through the Money Market and help him master the world of money?");
        cliOutputPort.write("Let's dive into the exciting realm of finances!");
    }

    @Override
    public void levelTwelve() {
        cliOutputPort.write("Level 12: The Probability Puzzle");
        cliOutputPort.write("Sir Mathalot encounters a mysterious puzzle involving probability.");
        cliOutputPort.write("To solve the puzzle, he must understand concepts like likelihood, chance, and probability calculations.");
        cliOutputPort.write("Will you help Sir Mathalot unravel the secrets of probability?");
        cliOutputPort.write("Let's embark on this mind-boggling journey!");
    }

    @Override
    public void levelThirteen() {
        cliOutputPort.write("Level 13: The Measurement Mission");
        cliOutputPort.write("Sir Mathalot embarks on a critical mission to master measurements.");
        cliOutputPort.write("He encounters various measurement tools and must solve problems involving length, weight, capacity, and temperature conversions.");
        cliOutputPort.write("Can you aid Sir Mathalot in this crucial measurement mission?");
        cliOutputPort.write("Let's embrace the world of measurements!");
    }

    @Override
    public void levelFourteen() {
        cliOutputPort.write("Level 14: The Algebra Adventure");
        cliOutputPort.write("Sir Mathalot embarks on an adventurous journey through the realm of algebra.");
        cliOutputPort.write("He encounters algebraic expressions, equations, and word problems.");
        cliOutputPort.write("To overcome this challenge, he must simplify expressions, solve equations, and find unknown values.");
        cliOutputPort.write("Will you join Sir Mathalot in this thrilling algebraic adventure?");
        cliOutputPort.write("Let's unlock the secrets of algebra!");
    }

    @Override
    public void levelFifteen() {
        cliOutputPort.write("Level 15: The Time Warp");
        cliOutputPort.write("Sir Mathalot finds himself caught in a mysterious time warp, where time behaves strangely.");
        cliOutputPort.write("To navigate through the time warp, he must solve time-related puzzles, including calculating durations, time zones, and solving time word problems.");
        cliOutputPort.write("Can you guide Sir Mathalot through this time-bending challenge?");
        cliOutputPort.write("Let's unravel the mysteries of time!");
    }

    @Override
    public void levelSixteen() {
        cliOutputPort.write("Level 16: The Logic Labyrinth");
        cliOutputPort.write("Sir Mathalot enters the Logic Labyrinth, a place filled with mind-bending logical puzzles.");
        cliOutputPort.write("To navigate through the labyrinth, he must solve problems involving logical reasoning, patterns, and sequences.");
        cliOutputPort.write("Can you help Sir Mathalot unravel the secrets of logic and conquer the labyrinth?");
        cliOutputPort.write("Let's embark on this perplexing journey of logic!");
    }

    @Override
    public void levelSeventeen() {
        cliOutputPort.write("Level 17: The Geometry Galaxy");
        cliOutputPort.write("Sir Mathalot arrives in the Geometry Galaxy, a place filled with geometric wonders.");
        cliOutputPort.write("He must solve geometry problems, identify shapes, calculate areas, perimeters, and angles.");
        cliOutputPort.write("Will you join Sir Mathalot on this cosmic geometry adventure?");
        cliOutputPort.write("Let's explore the wonders of geometry in the galaxy!");
    }

    @Override
    public void levelEighteen() {
        cliOutputPort.write("Level 18: The Fractions Fortress");
        cliOutputPort.write("Sir Mathalot encounters the Fractions Fortress, a stronghold of fractions.");
        cliOutputPort.write("To conquer the fortress, he must solve problems involving fractions, including addition, subtraction, multiplication, and division.");
        cliOutputPort.write("Can you guide Sir Mathalot through the Fractions Fortress and conquer the world of fractions?");
        cliOutputPort.write("Let's master the realm of fractions!");
    }

    @Override
    public void levelNineteen() {
        cliOutputPort.write("Level 19: The Data Dungeon");
        cliOutputPort.write("Sir Mathalot delves into the depths of the Data Dungeon, a place filled with data and statistics.");
        cliOutputPort.write("To unlock the dungeon's secrets, he must analyze data, interpret graphs, and solve data-related problems.");
        cliOutputPort.write("Will you help Sir Mathalot conquer the Data Dungeon?");
        cliOutputPort.write("Let's dive into the world of data!");
    }

    @Override
    public void levelTwenty() {
        cliOutputPort.write("Level 20: The Final Challenge");
        cliOutputPort.write("Congratulations on reaching Level 20!");
        cliOutputPort.write("Sir Mathalot faces the final challenge, a test of all his math skills.");
        cliOutputPort.write("He must solve a variety of math problems, including addition, subtraction, multiplication, division, fractions, geometry, time, data, logic, algebra, and more.");
        cliOutputPort.write("Can you stand with Sir Mathalot and help him overcome this ultimate test?");
        cliOutputPort.write("Prepare for the final showdown!");
    }

    @Override
    public void conclusion() {
        cliOutputPort.write("Congratulations, brave adventurer!");
        cliOutputPort.write("You have successfully completed all the levels of the Math Arcade game with Sir Mathalot.");
        cliOutputPort.write("Your math skills and bravery have saved the kingdom from the Math Monster's grasp.");
        cliOutputPort.write("You solved the Sudoku and made your way to the castle!");
        cliOutputPort.write("You are a true math champion!");
        cliOutputPort.write("Thank you for playing the Math Arcade game for kids.");
        cliOutputPort.write("We hope you had a great time and continue your math journey.");
        cliOutputPort.write("Stay curious, keep learning, and always embrace the power of math!");
        cliOutputPort.write("Goodbye!");
    }

    @Override
    public void emptyLine() {
        cliOutputPort.writeEmptyLine();
    }

    @Override
    public void sudokuIntroduction() {
        cliOutputPort.write("To reach the castle Sir Mathalot has to solve the following Sudoku:");
    }

    @Override
    public void congratulationAfterSolving() {
        int option = this.random.nextInt();
        if (option < 0.4) {
            cliOutputPort.write("Congratulations on completing the previous Level!");
        } else if (option < 0.8) {
            cliOutputPort.write("Well done on completing the previous level!");
        } else {
            cliOutputPort.write("Congratulations on defeating the Math Monster!");
        }
    }

    @Override
    public void mathProblem(MathProblem mathProblemToSolve) {
        cliOutputPort.write("A almost impossible to solve math problem has arisen.");
        cliOutputPort.write("But as soon as Sir Mathalot has a closer look he realizes, that it's very easy.");
        cliOutputPort.write("Please type in the answer of the following math problem:");

        cliOutputPort.writeEmptyLine();

        cliOutputPort.write(mathProblemToSolve.getProblemAsText());
    }

    @Override
    public void optionError() {
        cliOutputPort.write("Invalid Input - Please enter a valid number!");
    }

    @Override
    public void correctAnswer() {
        cliOutputPort.write("Yeah, that's correct!");
        cliOutputPort.write("Congratulations. You are getting closer to Sir Mathalots castle!");
    }

    @Override
    public void wrongAnswer() {
        cliOutputPort.write("Pity, that's not the correct answer!");
        cliOutputPort.write("Please try again to solve the question.");
        cliOutputPort.write("Stay calm and breath in and out. Sir Mathalot will honour it.");
    }

}
