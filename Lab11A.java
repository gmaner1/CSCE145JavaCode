package Lab11A;

import java.util.ArrayList;

public class Lab11A {
	public interface Feedable {
		void feed(String type);
		boolean isHungry();
	}
	
	public abstract static class Animal implements Feedable {
		//Properties
		private String name;
		private int age;
		private int energyLevel;
		
		//Constructor
		public Animal(String name, int age, int energyLevel) {
			this.name = name;
			this.age = age;
			this.energyLevel = Math.max(0, Math.min(100, energyLevel));
		}
		
		//Abstract method
		public abstract String makeSound();
		
		//Concrete method
		@Override
		public String toString() {
			return String.format(name, age, energyLevel);
		}
		
		//Helper methods
		public void setEnergyLevel(int energy) {
            this.energyLevel = Math.max(0, Math.min(100, energy));
        }

        public int getEnergyLevel() {
            return energyLevel;
        }
        
        public String getName() {
        	return name;
        }
	}	
		//Exceptions
		public static class InvalidFoodException extends Exception {
			public InvalidFoodException(String animalName, String foodType) {
				super(animalName + " cannot eat " + foodType + "!");
			}
		}
		public static class LowEnergyException extends Exception {
			public LowEnergyException(String animalName) {
				super(animalName + " is too tired to perform this action!");
			}
		}
		//Animal Types
		public static class Lion extends Animal {
			public Lion(String name, int age, int energyLevel) {
				super(name, age, energyLevel);
			}
			@Override
			public String makeSound() {
				return "ROAR!";
			}
			@Override
			public void feed(String foodType) {
				try {
	                if (!foodType.equalsIgnoreCase("meat")) {
	                    throw new InvalidFoodException(getName(), foodType);
	                }
	                setEnergyLevel(getEnergyLevel() + 20);
	                System.out.println(getName() + " ate the " + foodType + "! " + getName() + "'s Energy: " + getEnergyLevel());
	            } catch (InvalidFoodException e) {
	                System.out.println(e.getMessage());
	            }
		}
			@Override
			public boolean isHungry() {
				return getEnergyLevel() < 50;
			}
		}
		public static class Elephant extends Animal {
			public Elephant(String name, int age, int energyLevel) {
				super(name, age, energyLevel);
			}
			@Override
			public String makeSound() {
				return "TRUMPET!";
			}
			@Override
			public void feed(String foodType) {
				try {
	                if (!foodType.equalsIgnoreCase("plants") && !foodType.equalsIgnoreCase("vegetables")) {
	                    throw new InvalidFoodException(getName(), foodType);
	                }
	                setEnergyLevel(getEnergyLevel() + 15);
	                System.out.println(getName() + " ate the " + foodType + "! " + getName() + "'s Energy: " + getEnergyLevel());
	            } catch (InvalidFoodException e) {
	                System.out.println(e.getMessage());
	            }
			}
			@Override
			public boolean isHungry() {
				return getEnergyLevel() < 50;
			}
		}
		public static class Penguin extends Animal {
			public Penguin(String name, int age, int energyLevel) {
				super(name, age, energyLevel);
			}
			@Override
			public String makeSound() {
				return "SQUAWK!";
			}
			@Override
			public void feed(String foodType) {
				try {
	                if (!foodType.equalsIgnoreCase("fish")) {
	                    throw new InvalidFoodException(getName(), foodType);
	                }
	                setEnergyLevel(getEnergyLevel() + 12);
	                System.out.println(getName() + " ate the " + foodType + "! " + getName() + "'s Energy: " + getEnergyLevel());
	            } catch (InvalidFoodException e) {
	                System.out.println(e.getMessage());
	            }
		}
			@Override
			public boolean isHungry() {
				return getEnergyLevel() < 50;
			}
			//Special penguin swim method
			public void swim() {
				try {
			        if (getEnergyLevel() < 20) {
			            throw new LowEnergyException(getName());
			        }
			        setEnergyLevel(getEnergyLevel() + 10);
			        System.out.println(getName() + " is swimming! Energy now: " + getEnergyLevel());
			    } catch (LowEnergyException e) {
			        System.out.println(e.getMessage());
			    }
			}
		}
		//Zoo Class
		public static class Zoo {
			private ArrayList<Lab11A.Animal> animals;
			
			public Zoo() {
		        animals = new ArrayList<>();
		    }
			
			public void addAnimal(Animal animal) {
				animals.add(animal);
			}
			
			public void feedAllAnimals(String foodType) {
				for(Lab11A.Animal animal : animals) {
					try {
						animal.feed(foodType);
					} catch (Exception e) {
						System.out.println(animal.getName() + " cannot be fed this due to this error: " + e.getMessage());
					}
				}
			}
			public void makeAllSoundsIfEnergetic() {
				for(Lab11A.Animal animal : animals) {
					try {
						if(animal.getEnergyLevel() < 20) {
							throw new Lab11A.LowEnergyException(animal.getName());
						}
						System.out.println(animal.getName() + " makes the sound: " + animal.makeSound());
					} catch (Lab11A.LowEnergyException e){
						System.out.println(e.getMessage());
					}
				}
			}
		}
		//Main method for testing
		public static void main(String[] args) {
			System.out.println("=== Welcome to my Zoo! ===");

		    // Animals in the zoo
		    Zoo zoo = new Zoo();

		    Lion aiden = new Lion("Aiden", 5, 40);
		    Elephant tripp = new Elephant("Tripp", 12, 50);
		    Penguin harris = new Penguin("Harris", 3, 25);

		    zoo.addAnimal(aiden);
		    zoo.addAnimal(tripp);
		    zoo.addAnimal(harris);

		    // Feeding the animals all foods
		    System.out.println("\n=== Feeding the animals meat ===");
		    zoo.feedAllAnimals("meat");    
		    System.out.println("\n=== Feeding the animals plants ===");
		    zoo.feedAllAnimals("plants");    
		    System.out.println("\n=== Feeding the animals fish ===");
		    zoo.feedAllAnimals("fish");       

		    // Reduce animal energy
		    System.out.println("\n=== Some of the animals are losing energy! ===");
		    aiden.setEnergyLevel(15);
		    harris.setEnergyLevel(10);
		    System.out.println(aiden.getName() + " energy: " + aiden.getEnergyLevel());
		    System.out.println(harris.getName() + " energy: " + harris.getEnergyLevel());

		    // Tired animals perform actions
		    System.out.println("\n=== The animals are producing sounds! (some are tired) ==");
		    zoo.makeAllSoundsIfEnergetic();

		    System.out.println("\n=== Trying to make tired Harris the penguin swim ===");
		    try {
		        harris.swim();
		    } catch (Exception e) {
		        System.out.println("Caught exception: " + e.getMessage());
		    }

		    // Exceptions
		    System.out.println("\n=== Feeding meat to Tripp the elephant ===");
		    try {
		        tripp.feed("meat");
		    } catch (Exception e) {
		        System.out.println("Caught exception: " + e.getMessage());
		    }

		    System.out.println("\n=== I hope you enjoyed your stay! ===");
		}
	}

