package com.company;

import java.util.Scanner;

public class JetBrainsAcademy_CoffeeMachine_2 {

    public static void main(String[] args) {
        CoffeeMachine  machine = new CoffeeMachine();
        machine.makingCoffee();
    }
}


class CoffeeMachine{

    private enum Beverages {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);


        Beverages(int water, int milk, int beans, int cost) {
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.cost = cost;
        }

        private final int water;
        private final int milk;
        private final int beans;
        private final int cost;
    }

    public static Scanner scanner = new Scanner(System.in);

    public static int waterSupply = 400;
    private static int milkSupply = 540;
    private static int beansSupply = 120;
    private static int disposableCups = 9;
    private static int money = 550;

    public void makingCoffee(){
        while(true){
            System.out.println("==============================================");
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String action = scanner.nextLine();

            if(action.equalsIgnoreCase("buy")){
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:, back = to main menu");
                String userChoice = scanner.nextLine();
                switch (userChoice){
                    case "1":
                        System.out.println(checkIngredientsForMakingACupOfCoffee(userChoice, Beverages.ESPRESSO.water, Beverages.ESPRESSO.milk, Beverages.ESPRESSO.beans, waterSupply,milkSupply,beansSupply,disposableCups));
                        calculateRemainings(Beverages.ESPRESSO.water, Beverages.ESPRESSO.milk, Beverages.ESPRESSO.beans, Beverages.ESPRESSO.cost);
                        break;
                    case "2":
                        System.out.println(checkIngredientsForMakingACupOfCoffee(userChoice, Beverages.CAPPUCCINO.water, Beverages.CAPPUCCINO.milk, Beverages.CAPPUCCINO.beans, waterSupply,milkSupply,beansSupply,disposableCups));
                        calculateRemainings(Beverages.ESPRESSO.water, Beverages.ESPRESSO.milk, Beverages.ESPRESSO.beans, Beverages.ESPRESSO.cost);
                        break;
                    case "3":
                        System.out.println(checkIngredientsForMakingACupOfCoffee(userChoice, Beverages.LATTE.water, Beverages.LATTE.milk, Beverages.LATTE.beans, waterSupply,milkSupply,beansSupply,disposableCups));
                        calculateRemainings(Beverages.LATTE.water, Beverages.LATTE.milk, Beverages.LATTE.beans, Beverages.LATTE.cost);
                        break;
                    case "back":
                        break;
                    default:
                        System.out.println("Wrong input");
                        break;
                }
            }else if(action.equalsIgnoreCase("fill")){
                fillCoffeeMachineWithIngredients();

            }else if(action.equalsIgnoreCase("take")){
                System.out.println("I gave you "+money);
                money = 0;

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

    private void fillCoffeeMachineWithIngredients(){
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
    }

    private void calculateRemainings(int waterForACupOf, int milkForACupOf, int beansForACupOf, int costOfACupOf){
        if(waterSupply-waterForACupOf >= 0 && milkSupply - milkForACupOf >=0 && beansSupply - beansForACupOf >= 0){
            waterSupply -= waterForACupOf;
            milkSupply -= milkForACupOf;
            beansSupply -= beansForACupOf;
            money += costOfACupOf;
            disposableCups--;
        }
    }

    private String isEnoughCertainIngredientOrNot(int water, int milk, int beans, int waterSupply, int milkSupply, int beansSupply, int disposableCups){
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

    private String checkIngredientsForMakingACupOfCoffee(String action, int water, int milk, int beans, int waterSupply, int milkSupply, int beansSupply, int disposableCups){

        String result = "";

        switch(action){
            case "1":
            case "2":
            case "3":
                result = isEnoughCertainIngredientOrNot(water,milk,beans,waterSupply,milkSupply,beansSupply,disposableCups);
                break;
            default:
                result = "Wrong input!";
                break;
        }

        return result;
    }

    public void printMenu(int waterSupply, int milkSupply, int beansSupply, int disposableCups, int money){
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