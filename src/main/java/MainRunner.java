import java.util.Locale;
import java.util.Scanner;

public class MainRunner
{

    static int pDrinksError(int input)
    {
        if (input>0)
        {
            return input;
        }
        String[] ingredients = {"water", "milk", "beans", "cups", "money"};
        String mycode = input + "";
        //System.out.println(mycode);
        for (int ii = 0; ii < 5; ii++)
        {
            char fourtest = mycode.charAt(mycode.length()-(1+ii));
            if (fourtest == '4')
            {
                System.out.println("not enough " + ingredients[ii]);
            }
            if (fourtest == '-')break;
        }
        return -1;
    }
    static int possibleDrinks( int waterin, int milkin, int beansin, int cupsin, int moneyin, Drink din)
    {
        int retval = waterin + milkin + beansin + cupsin + moneyin;
        // initialize with max value so no chance of it being too small for comparisons
        int errorcode = 0;
        /*
        -4 for not enough water,
        -40 for not enough milk,
        -400 for not enough coffee,
        -4000 for not enough cups,
        -40000 for not enough money
         */
        boolean enough = true;
        int retsPerW = retval;
        int retsPerM = retval;
        int retsPerB = retval;
        int retsPerC = retval;
        int retsPer$ = retval;
        if (din.getWater()>0)
        {
            retsPerW = waterin / din.getWater();
        }
        if (din.getMilk()>0)
        {
            retsPerM = milkin / din.getMilk();
        }
        if (din.getBeans()>0)
        {
            retsPerB = beansin / din.getBeans();
        }
        if (din.getCups()>0)
        {
            retsPerC = cupsin / din.getCups();
        }
        if (din.getMoney()>0)
        {
            retsPer$ = moneyin / din.getMoney();
        }


        int[] myRetsArray = {retsPerW,retsPerM,retsPerB,retsPerC,retsPer$};
        for (int ii = 0; ii < 5; ii ++)
        {
            int amount = myRetsArray[ii];
            if (retval>amount && amount>0){
                retval = amount;
            }
            else if (amount == 0)
            {
                errorcode += (int)(-4 * Math.pow(10,ii));
                enough = false;
            }
        }
        if (enough)return retval;
        else return errorcode;

    }
    public static void main(String[] args)
    {
        //main start
        System.out.println("Main Start");

        /*
        For the espresso, the coffee machine needs 250 ml of water and 16 g of coffee beans. It costs $4.
        For the latte, the coffee machine needs 350 ml of water, 75 ml of milk, and 20 g of coffee beans. It costs $7.
        For the cappuccino, the coffee machine needs 200 ml of water, 100 ml of milk, and 12 g of coffee. It costs $6.
         */
        Drink eDrink    = new Drink("Espresso",250,0,16,1,4);
        Drink lDrink    = new Drink("Latte",350,75,20,1,7);
        Drink cDrink    = new Drink("Cappuccino",200,100,12,1,6);
        boolean repeat = true;
        Scanner scanner = new Scanner(System.in);
        int water = 400;
        int milk = 540;
        int beans = 120;
        int cups = 9;
        int money = 550;
        int[] WMBC$ = {water,milk,beans,cups,money};
        String[] ingredientNames = {"water","milk","beans","cups","money"};
        //water,milk,beans,cups,money

        while (repeat)
        {
            System.out.println("Write Action (buy, fill, take, remaining, exit)");
            String userInput = scanner.nextLine().toLowerCase();
            if (userInput.equals(""))userInput = " ";
            char usrInChar = userInput.charAt(0);
            System.out.println("userInput: "+userInput);
            switch (usrInChar)
            {
                case 'e': //exit
                {
                    repeat = false;
                    System.out.println("Goodbye!");
                    break;
                }
                case 'b': //buy
                {

                    System.out.println("What do you want to buy?\n" +
                            "\t1 - espresso\n\t2 - latte\n\t3 - cappuccino\nelse - main menu");
                    String userOrder = scanner.nextLine().toLowerCase();
                    if (userOrder.equals(""))userOrder = " ";
                    char orderChar = userOrder.charAt(0);
                    System.out.println("userOrder: "+ userOrder);
                    int drinks;
                    boolean valid = false;
                    Drink userDrink = eDrink;
                    switch  (orderChar)
                    {
                        case '1':
                        {
                            userDrink = eDrink;
                            valid = true;
                            break;

                        }
                        case '2':
                        {
                            userDrink = lDrink;
                            valid = true;
                            break;
                        }
                        case '3':
                        {
                            userDrink = cDrink;
                            valid = true;
                            break;
                        }
                        default:
                        {
                            valid = false;
                        }



                    }


                    if (valid)
                    {


                        drinks = pDrinksError(possibleDrinks(WMBC$[0],WMBC$[1],WMBC$[2],WMBC$[3],WMBC$[4], userDrink));
                        if (drinks >0)
                        {

                            WMBC$[0] -= userDrink.getWater();
                            WMBC$[1] -= userDrink.getMilk();
                            WMBC$[2] -= userDrink.getBeans();
                            WMBC$[3] -= userDrink.getCups();
                            WMBC$[4] -= userDrink.getMoney();
                            System.out.println("Made 1 " + userDrink.getName());
                        }
                    }

                    break;
                }
                case 'f': //fill
                {

                    String[] fillStringList = new String[5];
                    int[] fillIntList = new int[5];

                    System.out.println("Type an integer to add that much of the following");
                    for (int ii = 0; ii < 5; ii++)
                    {
                        System.out.println(ingredientNames[ii]+": ");
                        fillStringList[ii] = scanner.nextLine().toLowerCase();
                        fillIntList[ii] = Integer.parseInt(fillStringList[ii]);
                        //System.out.println(WMBC$[ii]);
                        WMBC$[ii]+= fillIntList[ii];
                        //System.out.println(WMBC$[ii]);
                    }
                    break;
                }
                case 't': //take
                {
                    System.out.println(userInput+" not yet supported");
                    break;
                }
                case 'r': //remaining
                {
                    System.out.println(
                            "The coffee machine has:\n" +
                                    WMBC$[0] + "\t water\n" +
                                    WMBC$[1] + "\t milk\n" +
                                    WMBC$[2] + "\t beans\n" +
                                    WMBC$[3] + "\t cups\n" +
                                    WMBC$[4] + "\t money\n");
                    break;
                }
                default:
                    System.out.println("INVALID INPUT!");
            }
        }


    }
    //end of main
}
