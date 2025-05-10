# Smart Wardrobe JavaFX Application
Manage your wardrobe and generate stylish outfits easily with Smart Wardrobe! Upload your clothing items, generate outfits with color filters, and save your favorite combinations for quick reference.

## 📝 System Requirements:
- Java Development Kit (JDK) 17 or above
- JavaFX SDK 17 or above (if not bundled in the JAR)
- An IDE like IntelliJ IDEA or Eclipse if running from source

## 📁 Project Structure:
│
├── src
│   ├── com.wardrobe
│   │   ├── App.java
│   │   ├── MainViewController.java
│   │   ├── Outfit.java
│   │   ├── Shirt.java
│   │   ├── Pants.java
│   │   ├── Shoes.java
│   │   ├── WardrobeItem.java
│   │   ├── OutfitGenerator.java
│   │   ├── User.java
│   │   └── Saved.java
│   │
│   ├── resources
│   │   ├── MainView.fxml
│   │   └── images
│   │
│   └── lib 
│
└── SmartWardrobeApp.jar

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


