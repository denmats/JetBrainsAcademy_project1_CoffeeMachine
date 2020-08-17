package com.company;


import java.util.Scanner;

public class JetBrainsAcademy_CoffeeMachine_1 {

    public static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        int waterSupply = 400;
        int milkSupply = 540;
        int beansSupply = 120;
        int disposableCups = 9;
        int money = 550;

        int waterForACupOfEspresso = 250;
        int milkForACupOfEspresso = 0;
        int beansForACupOfEspresso = 16;
        int costOfACupOfEspresso = 4;

        int waterForACupOfLatte = 350;
        int milkForACupOfLatte = 75;
        int beansForACupOfLatte = 20;
        int costOfACupOfLatte = 7;

        int waterForACupOfCappuccino = 200;
        int milkForACupOfCappuccino = 100;
        int beansForACupOfCappuccino = 12;
        int costOfACupOfCappuccino = 6;

        while(true){
            System.out.println("==============================================");
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String action = scanner.nextLine();

            if(action.equalsIgnoreCase("buy")){
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:, back = to main menu");
                String userChoice = scanner.nextLine();
                switch (userChoice){
                    case "1":
                        System.out.println(checkIngredientsForMakingACupOfCoffee(userChoice,waterForACupOfEspresso, milkForACupOfEspresso,beansForACupOfEspresso,waterSupply,milkSupply,beansSupply,disposableCups));

                        if(waterSupply-waterForACupOfEspresso >= 0 && milkSupply - milkForACupOfEspresso >=0 && beansSupply - beansForACupOfEspresso >= 0){
                            waterSupply -= waterForACupOfEspresso;
                            milkSupply -= milkForACupOfEspresso;
                            beansSupply -= beansForACupOfEspresso;
                            money += costOfACupOfEspresso;
                            disposableCups--;
                        }

                        break;
                    case "2":
                        System.out.println(checkIngredientsForMakingACupOfCoffee(userChoice,waterForACupOfCappuccino, milkForACupOfCappuccino,beansForACupOfCappuccino,waterSupply,milkSupply,beansSupply,disposableCups));

                        if(waterSupply-waterForACupOfLatte >= 0 && milkSupply - milkForACupOfLatte >=0 && beansSupply - beansForACupOfLatte >= 0){
                        waterSupply -= waterForACupOfLatte;
                        milkSupply -= milkForACupOfLatte;
                        beansSupply -= beansForACupOfLatte;
                        money += costOfACupOfLatte;
                        disposableCups--;
                        }
                        break;
                    case "3":
                        System.out.println(checkIngredientsForMakingACupOfCoffee(userChoice,waterForACupOfCappuccino, milkForACupOfCappuccino,beansForACupOfCappuccino,waterSupply,milkSupply,beansSupply,disposableCups));

                        if(waterSupply-waterForACupOfCappuccino >= 0 && milkSupply - milkForACupOfCappuccino >=0 && beansSupply - beansForACupOfCappuccino >= 0){
                            waterSupply -= waterForACupOfCappuccino;
                            milkSupply -= milkForACupOfCappuccino;
                            beansSupply -= beansForACupOfCappuccino;
                            money += costOfACupOfCappuccino;
                            disposableCups--;
                        }
                        break;
                    case "back":
                        break;
                    default:
                        System.out.println("Wrong input");
                        break;
                }
            }else if(action.equalsIgnoreCase("fill")){
                System.out.println("Write how many ml of water do you want to add: ");
                int addedWater = scanner.nextInt();
                waterSupply += addedWater;
                System.out.println("Write how many ml of milk do you want to add: ");
                int addedMilk = scanner.nextInt();
                milkSupply += addedMilk;
                System.out.println("Write how many grams of coffee beans do you want to add: ");
                int addedBeans = scanner.nextInt();
                beansSupply += addedBeans;
                System.out.println("Write how many disposable cups of coffee do you want to add: ");
                int addedCups = scanner.nextInt();
                disposableCups += addedCups;

               // printMenu(waterSupply,milkSupply,beansSupply,disposableCups,money);
            }else if(action.equalsIgnoreCase("take")){
                System.out.println("I gave you "+money);
                money = 0;

               // printMenu(waterSupply,milkSupply,beansSupply,disposableCups,money);
            }else if(action.equalsIgnoreCase("remaining")){
                printMenu(waterSupply,milkSupply,beansSupply,disposableCups,money);
            }
            else if(action.equalsIgnoreCase("exit")){
                break;
            }
            else{
                System.out.println("Wrong input");
            }
        }
    }

    public static String isEnoughCertainIngredientOrNot(int water, int milk, int beans, int waterSupply, int milkSupply, int beansSupply, int disposableCups){
        String isEnoughSupplies = "";


        if(water - waterSupply > 0){
            isEnoughSupplies = "Sorry, not enough water!";
        }
        else if(milk - milkSupply > 0) {
            isEnoughSupplies = "Sorry, not enough milk!";
        }
        else if(beans - beansSupply > 0) {
            isEnoughSupplies = "Sorry, not enough beans!";
        }
        else if(disposableCups <= 0) {
            isEnoughSupplies = "Sorry, not enough cups!";
        }
        else isEnoughSupplies = "I have enough resources, making you a coffee!";

        return isEnoughSupplies;
    }

    public static String checkIngredientsForMakingACupOfCoffee(String action, int water, int milk, int beans, int waterSupply, int milkSupply, int beansSupply, int disposableCups){

        String result = "";

        switch(action){
            case "1":
                result = isEnoughCertainIngredientOrNot(water,milk,beans,waterSupply,milkSupply,beansSupply,disposableCups);
                break;
            case "2":
                result = isEnoughCertainIngredientOrNot(water,milk,beans,waterSupply,milkSupply,beansSupply,disposableCups);
                break;
            case "3":
                result = isEnoughCertainIngredientOrNot(water,milk,beans,waterSupply,milkSupply,beansSupply,disposableCups);
                break;
            default:
                System.out.println("Wrong input!");
                break;
        }

        return result;
    }


    public static void printMenu(int waterSupply, int milkSupply, int beansSupply, int disposableCups, int money){
        System.out.println("The coffee machine has: ");
        if ((waterSupply >= 0)) {
            System.out.println(waterSupply + "  of water");
        } else {
            System.out.println(" 0 of water");
        }

        if ((milkSupply >= 0)) {
            System.out.println(milkSupply + "  of milk");
        } else {
            System.out.println(" 0 of milk");
        }

        if ((beansSupply >= 0)) {
            System.out.println(beansSupply + "  of beans");
        } else {
            System.out.println(" 0 of beans");
        }

        if ((disposableCups >= 0)) {
            System.out.println(disposableCups + "  of cups");
        } else {
            System.out.println(" 0 of disposable cups");
        }

        System.out.println(money+" of money");
    }
}




