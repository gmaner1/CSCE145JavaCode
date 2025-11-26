package Lab10A;

public class Lab10B {
	public static void main(String[] args) {
//		//Create a new zookeeper named Aiden
//		ZooKeeper Aiden = new ZooKeeper();
//		
//		//Create animals
//		Lion lion1 = new Lion("Brad", 5, 160.0, 8);
//		Lion lion2 = new Lion("Chad", 12, 240.0, 15);
//		
//		Elephant elephant1 = new Elephant("Lucas", 10, 5999.0, 120.0);
//		Elephant elephant2 = new Elephant("Martinez", 8, 4267.6, 110.5);
//		
//		Penguin penguin1 = new Penguin("Michael", 3, 20.5);
//		Penguin penguin2 = new Penguin("Milford", 6, 25.3);
//		
//		//Add animals to Aiden's zoo
//		Aiden.addAnimal(lion1);
//		Aiden.addAnimal(lion2);
//		Aiden.addAnimal(elephant1);
//		Aiden.addAnimal(elephant2);
//		Aiden.addAnimal(penguin1);
//		Aiden.addAnimal(penguin2);
//		
//		System.out.println("=== Welcome to Aiden's Zoo! ===");
//		
//		//Feed the animals
//		System.out.println("\n=== Feeding Time ===");
//		Aiden.feedAllAnimals();
//		
//		//Hear the animals' sounds
//		System.out.println("\n=== Sound Check ===");
//		Aiden.makeAllSounds();
//		
//		//Check for the heaviest animal
//		System.out.println("\n=== Heaviest Animal ===");
//		Animal heaviest = Aiden.getHeaviestAnimal();
//		if(heaviest != null) {
//			System.out.println(heaviest);
//		} else {
//			System.out.println("There are no animals in Aiden's zoo!");
//		}
//		
//		//Have the animals do their actions
//		System.out.println("\n=== Animal Actions ===");
//		System.out.println(lion1.hunt());
//		System.out.println(elephant1.spray());
//		System.out.println(penguin1.waddle());
		
		// Create ZooKeeper
		ZooKeeper keeper = new ZooKeeper();
		
		// Create animals
		Lion simba = new Lion("Simba", 5, 190.0, 7);
		Elephant ella = new Elephant("Ella", 12, 5400.0, 180.0);
		Penguin penny = new Penguin("Penny", 3, 15.0);
		
		// Add animals at specific positions
		keeper.addAnimal(simba, 0);
		keeper.addAnimal(ella, 2);
		keeper.addAnimal(penny, 5);
		
		// Create visitor
		ZooVisitor visitor = new ZooVisitor("Elijah Lee Barbee");
		
		// Test 1: Visit occupied exhibit
		System.out.println("=== Test 1: Visit Occupied Exhibit ===");
		try {
			visitor.visitExhibit(keeper, 0);
			System.out.println();
		} catch (EmptyExhibitException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		// Test 2: Visit empty exhibit
		System.out.println("=== Test 2: Visit Empty Exhibit ===");
		try {
			visitor.visitExhibit(keeper, 1);
			System.out.println();
		} catch (EmptyExhibitException e) {
			System.out.println("Error: " + e.getMessage());
			System.out.println();
		}
		
		// Test 3: Visit another occupied exhibit
		System.out.println("=== Test 3: Visit Another Animal ===");
		try {
			visitor.visitExhibit(keeper, 5);
			System.out.println();
		} catch (EmptyExhibitException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		// Test 4: Visit invalid exhibit number
		System.out.println("=== Test 4: Invalid Exhibit Number ===");
		try {
			visitor.visitExhibit(keeper, 15);
		} catch (EmptyExhibitException e) {
			System.out.println("Error: " + e.getMessage());
			System.out.println();
		}
		
		// Test 5: Visit another occupied exhibit
		System.out.println("=== Test 5: Visit Another Animal ===");
		try {
			visitor.visitExhibit(keeper, 2);
			System.out.println();
		} catch (EmptyExhibitException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	public static class Animal {
		//Private fields
		private String name;
		private int age;
		private double weight;
		private String species;
		//Constructor that takes all four parameters
		public Animal(String name, int age, double weight, String species) {
			this.name = name;
			this.age = age;
			this.weight = weight;
			this.species = species;
		}
		//Setter methods for age and weight
		public void setAge(int age) {
			this.age = age;
		}
		public void setWeight(double weight) {
			this.weight = weight;
		}
		//Getter methods for all fields
		public String getName() {
			return name;
		}
		public int getAge() {
			return age;
		}
		public double getWeight() {
			return weight;
		}
		public String getSpecies() {
			return species;
		}
		//Methods that return strings
		public String makeSound() {
			return "The animal makes a sound";
		}
		public String eat(String food) {
			return name + " the " + species + " is eating " + food;
		}
		//Nicely formatted string with all animal info
		@Override
		public String toString() {
			return "Name: " + name + "\nSpecies: " + species + "\nAge: " + age + " years\n" + "Weight: " + weight + " kg";
		}
		//Random action method
		public String randomAction() {
			int choice = (int)(Math.random() * 3);
			if(choice == 0) {
				return this.makeSound();
			} else if(choice == 1) {
				return this.eat("food");
			} else {
				return this.getSpecies() + " does something interesting";
			}
		}
	}
	public static class Lion extends Animal {
		private int prideSize;
		
		public Lion(String name, int age, double weight, int prideSize) {
			super(name, age, weight, "Lion");
			this.prideSize = prideSize;
		}
		@Override
		public String makeSound() {
			return "The lion roars!";
		}
		@Override
		public String eat(String food) {
			return "The lion tears into the " + food + " with sharp teeth";
		}
		
		public String hunt() {
			return "The lion with a pride size of " + prideSize + " hunts for its prey";
		}
		public String sleep() {
			return "The lion naps in the sun";
		}
		@Override
		public String randomAction() {
			int choice = (int)(Math.random() * 3);
			if(choice == 0) {
				return this.makeSound();
			} else if(choice == 1) {
				return this.hunt();
			} else {
				return this.sleep();
			}
		}
	}
	public static class Elephant extends Animal {
		private double tuskLength;
		
		public Elephant(String name, int age, double weight, double tuskLength) {
			super(name, age, weight, "Elephant");
			this.tuskLength = tuskLength;
		}
		@Override
		public String makeSound() {
			return "The elephant trumpets!";
		}
		@Override
		public String eat(String food) {
			return "The elephant uses its trunk to eat " + food;
		}
		
		public String spray() {
			return "The elephant with a tusk length of " + tuskLength + " cm sprays water out of its trunk";
		}
		public String bathe() {
			return "The elephant rolls in the mud";
		}
		@Override
		public String randomAction() {
			int choice = (int)(Math.random() * 3);
			if(choice == 0) {
				return this.makeSound();
			} else if(choice == 1) {
				return this.spray();
			} else {
				return this.bathe();
			}
		}
	}
	public static class Penguin extends Animal {
		public boolean canSwim = true;
		
		public Penguin(String name, int age, double weight) {
			super(name, age, weight, "Penguin");
		}
		@Override
		public String makeSound() {
			return "The penguin squawks!";
		}
		@Override
		public String eat(String food) {
			return "The penguin swallows the " + food + " whole";
		}
		public String waddle() {
			return "The penguin waddles across the ice";
		}
		public String slide() {
			return "The penguin slides on its belly";
		}
		@Override
		public String randomAction() {
			int choice = (int)(Math.random() * 3);
			if(choice == 0) {
				return this.makeSound();
			} else if(choice == 1) {
				return this.waddle();
			} else {
				return this.slide();
			}
		}
	}
	public static class ZooKeeper {
		private Animal[] animals;
		private int animalCount;
		
		public ZooKeeper() {
			animals = new Animal[10];
			animalCount = 0;
		}
		
		public void addAnimal(Animal animal) {
			if(animalCount < animals.length) {
				animals[animalCount] = animal;
				animalCount++;
			} else {
				System.out.println("Not enough space for a new animal");
			}
		}
		public void feedAllAnimals() {
			for (int i = 0; i < animals.length; i++) {
		        if (animals[i] != null) {
		            String food;
		            switch (animals[i].getSpecies()) {
		                case "Lion":
		                    food = "meat";
		                    break;
		                case "Elephant":
		                    food = "vegetables";
		                    break;
		                case "Penguin":
		                    food = "fish";
		                    break;
		                default:
		                    food = "food";
		                    break;
		            }
		            System.out.println(animals[i].eat(food));
		        }
		    }
		}
		public void makeAllSounds() {
			for(int i = 0; i < animals.length; i++) {
				if(animals[i] != null) {
					System.out.println(animals[i].makeSound());
				}
			}
		}
		public Animal getHeaviestAnimal() {
			Animal heaviest = null;
			for(int i = 0; i < animals.length; i++) {
				if(animals[i] != null) {
					if(heaviest == null || animals[i].getWeight() > heaviest.getWeight()) {
						heaviest = animals[i];
					}
				}
			}
			return heaviest;
		}
		public void addAnimal(Animal animal, int position) {
			if(position < 0 || position > 9) {
				System.out.println("Invalid position");
			} else if(animals[position] != null) {
				System.out.println("Exhibit at position " + position + " is already occupied");
			} else {
				animals[position] = animal;
				animalCount++;
			}
		}
		public Animal getAnimalAt(int position) {
			if(position < 0 || position > 9) {
				return null;
			} else {
				return animals[position];
			}
		}
	}
		public static class EmptyExhibitException extends Exception {
			public EmptyExhibitException(String message) {
				super(message);
		}
	}
		public static class ZooVisitor {
			private String name;
			
			public ZooVisitor(String name) {
				this.name = name;
			}
			public void visitExhibit(ZooKeeper keeper, int position) throws EmptyExhibitException{
				if(position < 0 || position > 9) {
					throw new EmptyExhibitException("Invalid exhibit number");
				}
				Animal animal = keeper.getAnimalAt(position);
				if(animal == null) {
					throw new EmptyExhibitException("Exhibit " + position + " is empty!");
				}
				System.out.println(name + " is visiting " + animal.getName());
				
				int numBehaviors = (int)(Math.random() * 5) + 1;
				for(int i = 1; i <= numBehaviors; i++) {
					System.out.println(animal.randomAction());
				}
			}
		}
}
