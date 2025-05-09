package com.wardrobe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class MainViewController {
    private User currentUser = new User("u1", "Demo User"); 

    @FXML private ImageView imagePreview;

    @FXML private ImageView shirtPreview;
    @FXML private ImageView pantsPreview;
    @FXML private ImageView shoesPreview;

    @FXML private Button uploadButton;
    @FXML private Button generateOutfit;
    @FXML private HBox categoryBox;
    @FXML private Label catInstruct;
    @FXML private Button viewSaved;

    // Wardrobe Upload page setting
    @FXML
    private void handleUploadStart() {
        uploadButton.setVisible(false);
        uploadButton.setManaged(false);

        generateOutfit.setVisible(false);
        generateOutfit.setManaged(false);

        categoryBox.setVisible(true);
        categoryBox.setManaged(true);

        catInstruct.setVisible(true);
        catInstruct.setManaged(true);

        viewSaved.setVisible(false);
        viewSaved.setManaged(false);

    }

    // Main page setting
    private void resetUI() {
        uploadButton.setVisible(true);
        uploadButton.setManaged(true);

        generateOutfit.setVisible(true);
        generateOutfit.setManaged(true);

        categoryBox.setVisible(false);
        categoryBox.setManaged(false);

        viewSaved.setVisible(true);
        viewSaved.setManaged(true);
    }
    

    private boolean handleImageUpload(String category) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload " + category);
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());
    
        if (selectedFile != null) {
            String imagePath = selectedFile.toURI().toString();

            Image image = new Image(imagePath);
            imagePreview.setImage(image);

            // Define valid colors
            List<String> validColors = Arrays.asList(
                "red", "orange", "yellow", "green", "blue", "purple", 
                "pink", "black", "white", "brown", "beige");
            
            // If the user didn't enter anything, set color as "unknown"
            String color = "unknown";
            boolean validInput = false;

            // Keep showing the dialog until a valid color is entered or the user cancels
            while (!validInput) {
                // Prompt the user to enter a color
                TextInputDialog colorDialog = new TextInputDialog();
                colorDialog.setTitle("Enter Item Color");
                colorDialog.setHeaderText("Enter the color for this " + category.toLowerCase() + ":");
                colorDialog.setContentText("Color:");
                DialogPane dialogPane = colorDialog.getDialogPane();
                dialogPane.setStyle("-fx-font-family: 'serif'; -fx-font-size: 14px;");

                Optional<String> result = colorDialog.showAndWait();
                if (result.isPresent()) {
                    color = result.get().trim().toLowerCase();
                    if (validColors.contains(color)) {
                        validInput = true; // Exit the loop if valid
                    } else {
                        showAlert1("Invalid Color", "Please enter a valid color: " + validColors);
                    }
                } else {
                    // User clicked cancel or closed the dialog
                    showAlert1("Upload Canceled", "No color was entered. Upload canceled.");
                    return false; // Exit the method
                }
            }


            // Generate a simple ID
            String itemID = UUID.randomUUID().toString();
    
            WardrobeItem item = null;
            switch (category.toLowerCase()) {
                case "shirt":
                    item = new Shirt(itemID, color, imagePath);
                    shirts.add((Shirt) item);
                    break;
                case "pants":
                    item = new Pants(itemID, color, imagePath);
                    pants.add((Pants) item);
                    break;
                case "shoes":
                    item = new Shoes(itemID, color, imagePath);
                    shoes.add((Shoes) item);
                    break;
            }
    
            if (item != null && currentUser != null) {
                currentUser.uploadItem(item);
                return true; // Successful upload
            }
    
        }
        return false;
    }

    // Error handling pop-up
    public static void showAlert1(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-font-family: 'serif'; -fx-font-size: 14px;");
        alert.showAndWait();
    }
    

    @FXML
    private void selectShirt(ActionEvent event) {
        // Only show success and reset UI if upload was successful
        if (handleImageUpload("Shirt")) {
            showInfo("Shirt Uploaded!");
            resetUI();
        }
    }

    @FXML
    private void selectPants(ActionEvent event) {
        // Only show success and reset UI if upload was successful
        if (handleImageUpload("Pants")) {
            showInfo("Pants Uploaded!");
            resetUI();
        }
    }

    @FXML
    private void selectShoes(ActionEvent event) {
        // Only show success and reset UI if upload was successful
        if (handleImageUpload("Shoes")) {
            showInfo("Shoes Uploaded!");
            resetUI();
        }
    }

    // Pop-up to confirm successful upload
    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Upload Successful");
        alert.setHeaderText(message);
        alert.setContentText(null);
        alert.setGraphic(imagePreview);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-font-family: 'serif'; -fx-font-size: 14px;");
        alert.showAndWait();
    }

    // Pop-up to display generated outfit
    private void showOutfitPopup(Outfit outfit) {
        Stage popupStage = new Stage();
        popupStage.setTitle("Generated Outfit");

        // Create image views for each clothing item
        ImageView shirtImage = new ImageView(new Image(outfit.getShirt().getImagePath()));
        ImageView pantsImage = new ImageView(new Image(outfit.getPants().getImagePath()));
        ImageView shoesImage = new ImageView(new Image(outfit.getShoes().getImagePath()));

        // Set preview sizes
        shirtImage.setFitWidth(100);
        pantsImage.setFitWidth(100);
        shoesImage.setFitWidth(100);
        shirtImage.setPreserveRatio(true);
        pantsImage.setPreserveRatio(true);
        shoesImage.setPreserveRatio(true);

        // Arrange images horizontally
        HBox imageBox = new HBox(10, shirtImage, pantsImage, shoesImage);
        imageBox.setAlignment(Pos.CENTER);

        // Create buttons for generated outfit display
        Button saveButton = new Button("Save Outfit");
        Button generateAgainButton = new Button("Generate Again");
        saveButton.setStyle("-fx-font-family: 'serif'; -fx-font-size: 14px;");
        generateAgainButton.setStyle("-fx-font-family: 'serif'; -fx-font-size: 14px;");

        // Save outfit
        saveButton.setOnAction(e -> {
            currentUser.saveOutfit(outfit); 
            popupStage.close();
        });

        generateAgainButton.setOnAction(e -> {
            popupStage.close();
        });

        HBox buttonBox = new HBox(10, saveButton, generateAgainButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20, imageBox, buttonBox);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout);
        popupStage.setScene(scene);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();
    }


    @FXML
    private void handleGenerateOutfit() {
        if (!currentUser.hasFullWardrobe()) {
            showAlert("Missing items", "Please upload at least one shirt, pants, and shoes.");
            return;
        }

        // Open a prompt to ask for color filter
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Color Filter");
        dialog.setHeaderText("Generate Outfit with Color Filter");
        dialog.setContentText("Enter a color (red, orange, yellow, green, blue, purple, pink, black, white, brown, or beige) or click Cancel for no filter:");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setStyle("-fx-font-family: 'serif'; -fx-font-size: 14px;");
        Optional<String> result = dialog.showAndWait();
        String chosenColor = result.orElse("").toLowerCase();

        // Validate color input
        List<String> validColors = Arrays.asList("red", "orange", "yellow", "green", "blue", "purple", "pink", "black", "white", "brown", "beige");
        if (!chosenColor.isEmpty() && !validColors.contains(chosenColor)) {
            showAlert("Invalid Color", "The color you entered is not valid. Generating without filter.");
            chosenColor = "";
        }

         // Generate outfit without filter if no color chosen
        Outfit outfit;
        if (chosenColor.isEmpty()) {
            outfit = OutfitGenerator.generateRandomOutfit(currentUser.getWardrobe());
        } 
    
        // If the user enters a color, attempt to filter by it
        if (!chosenColor.isEmpty() && validColors.contains(chosenColor)) {
            outfit = OutfitGenerator.generateRandomOutfitWithColor(currentUser.getWardrobe(), chosenColor);

            if (outfit == null) {
                showAlert1("No Items Found", "No " + chosenColor + " pieces in your closet!");
                return;
            }
        } 
        else {
            // If entered color is invalid, generate without color
            outfit = OutfitGenerator.generateRandomOutfit(currentUser.getWardrobe());
        }

        // Display generated outfit with error handling
        if (outfit != null) {
            showOutfitPopup(outfit);
        } else {
            showAlert("No Outfit", "Could not generate outfit.");
        }
    }

    // Error handling pop-up
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-font-family: 'serif'; -fx-font-size: 14px");
        alert.showAndWait();
    }
    
    // Wardrobe category lists
    private List<Shirt> shirts = new ArrayList<>();
    private List<Pants> pants = new ArrayList<>();
    private List<Shoes> shoes = new ArrayList<>();

    
@FXML private void showSavedOutfitsPopup() {
    List<Outfit> savedOutfits = currentUser.getSavedOutfits();

    if (savedOutfits.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Empty Favorites Closet");
        alert.setHeaderText("No saved outfits found!");
        alert.setContentText("Generate an outfit and save it to your Favorites closet.");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-font-family: 'serif'; -fx-font-size: 14px;");
        alert.showAndWait();
        return;
    }

    Stage popupStage = new Stage();
    popupStage.initModality(Modality.APPLICATION_MODAL);
    popupStage.setTitle("Saved Outfits");

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setFitToWidth(true);
    scrollPane.setPrefSize(800, 400);

    HBox outfitsBox = new HBox(20);
    outfitsBox.setPadding(new Insets(20));
    outfitsBox.setAlignment(Pos.TOP_LEFT);

    for (Outfit outfit : savedOutfits) {
        VBox outfitBox = new VBox(10);
        outfitBox.setAlignment(Pos.CENTER);

        ImageView shirtImg = new ImageView(new Image(outfit.getShirt().getImagePath()));
        ImageView pantsImg = new ImageView(new Image(outfit.getPants().getImagePath()));
        ImageView shoesImg = new ImageView(new Image(outfit.getShoes().getImagePath()));

        shirtImg.setFitWidth(100);
        pantsImg.setFitWidth(100);
        shoesImg.setFitWidth(100);

        shirtImg.setPreserveRatio(true);
        pantsImg.setPreserveRatio(true);
        shoesImg.setPreserveRatio(true);

        outfitBox.getChildren().addAll(shirtImg, pantsImg, shoesImg);
        outfitsBox.getChildren().add(outfitBox);
    }

    scrollPane.setContent(outfitsBox);

    Scene popupScene = new Scene(scrollPane);
    popupStage.setScene(popupScene);
    popupStage.show();
}

}
