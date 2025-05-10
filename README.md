# Smart Wardrobe JavaFX Application
Manage your wardrobe and generate stylish outfits easily with Smart Wardrobe! Upload your clothing items, generate outfits with color filters, and save your favorite combinations for quick reference.

## 📝 System Requirements:

## 🛠️ Prerequisites
- Java JDK 11 or above (matching the Maven pom.xml configuration).
- Maven 3.6+ for building the project.
- JavaFX SDK 13 if not bundled with your JDK.

## 📁 Project Structure:
smart-wardrobe/
├── pom.xml
├── src
│   └── main
│       ├── java
│       │   └── com.wardrobe
│       │       ├── App.java                  --> Main Application Entry Point
│       │       ├── MainViewController.java   --> Main Controller for UI interactions
│       │       ├── Outfit.java               --> Outfit object containing Shirt, Pants, Shoes
│       │       ├── OutfitGenerator.java      --> Logic to generate random outfits
│       │       ├── Pants.java                --> Pants item class
│       │       ├── Shirt.java                --> Shirt item class
│       │       ├── Shoes.java                --> Shoes item class
│       │       ├── User.java                 --> User class managing wardrobe items
│       │       └── WardrobeItem.java         --> Abstract class for all wardrobe items
│       └── resources
│           └── com.wardrobe
│               ├── MainView.fxml             --> FXML layout file
│               └── images                    --> Default images for clothes
│                   ├── pants 1.png
│                   ├── shirt 1.png
│                   ├── shoes 1.png
│                   └── ... (more images)
└── target
    └── SmartWardrobeApp.jar (Will be generated after build)

## ⚙️ Building the Project
#### Open a terminal in the root directory (smart-wardrobe) and run the following commands:

### 1. Clean and package the project:
  mvn clean package

### 2. Generate the executable JAR:
  mvn javafx:run

### Alternatively, you can find the JAR in:
  target/SmartWardrobeApp.jar

## ▶️ Running the Application
#### To launch the application, use one of the following commands:

### If JavaFX is bundled with your JDK:
java -jar target/SmartWardrobeApp.jar
### If JavaFX is not bundled:
java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml -jar target/SmartWardrobeApp.jar
#### Replace /path/to/javafx/lib with the path to your JavaFX SDK.









## 🚀 How to Run the Application:
Option 1: Using the JAR File (Recommended)
- Open your terminal (Command Prompt, PowerShell, or Terminal).
- Navigate to the directory where SmartWardrobeApp.jar is located.
- Run the application with the command:
java -jar SmartWardrobeApp.jar

If JavaFX is not bundled in the JAR, use:
java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml -jar SmartWardrobeApp.jar

Option 2: Running from Source (IDE Required)
- Open the project in your IDE (IntelliJ IDEA or Eclipse).
- Make sure JavaFX SDK is properly configured in Project Settings.
- Right-click on MainView.java → Run.

## 💡 How to Use the Application:

1️⃣ Upload Wardrobe Item:
- Click "Upload Wardrobe Item".
- Choose a category: Shirt, Pants, or Shoes.
- Select an image from your file system.
- Enter the color of the item (you will be prompted in a pop-up).
- The item is added to your virtual wardrobe.

2️⃣ Generate an Outfit:
- Click "Generate an Outfit".
- You will be prompted to enter a color filter (red, blue, green, etc.) or select "No" to skip filtering.
- If a matching color is found, an outfit is displayed in a pop-up window with options to:
- Save Outfit: Saves the outfit for future reference.
- Generate Again: Creates a new outfit combination.
- If no items match the selected color, you will see an alert: "No [color] pieces in your closet!"

3️⃣ View Saved Outfits:
- Click "View Saved Outfits".
- A pop-up window displays all your saved outfits, arranged side by side.
- Each outfit is shown with the shirt, pants, and shoes in vertical order.


