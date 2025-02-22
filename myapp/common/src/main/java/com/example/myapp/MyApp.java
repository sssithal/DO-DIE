
package com.example.myapp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.components.CheckBoxList;
import com.codename1.components.SpanLabel;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
//import com.codename1.ui.ProgressBar;
import com.codename1.ui.Graphics;

// Import for ProgressBar

//import com.codename1.ui.spinner.ProgressBar;

// Import for Label
import com.codename1.ui.Label;
// import com.codename1.ui.list;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextComponentPassword;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.MultipleSelectionListModel;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.example.myapp.CharacterSelectionPage;
import com.example.myapp.MyApp.CharacterStatusPage;
import com.example.myapp.MyApp.CustomProgressBar;
import com.example.myapp.MyApp.PreviousFormSetter;
import com.example.myapp.MyApp.SettingsPage;
import com.example.myapp.Task;
import com.example.myapp.TaskManager;
import com.example.myapp.MyApp.WeeklySummaryPage;
import com.codename1.ui.TextArea;
import static com.codename1.ui.CN.*;
import java.io.InputStream;  // Import for InputStream
import com.codename1.io.Util;  // Import for Util
import java.io.OutputStream;


import java.io.ByteArrayInputStream;
import java.io.IOException;
//Import for swiftUI translated functions
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.prefs.Preferences;
import com.codename1.l10n.SimpleDateFormat;

import com.codename1.ui.Display;
//import com.codename1.ui.Style;
import java.util.Timer;
import java.util.TimerTask;

import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.Component;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;


public class MyApp extends com.codename1.system.Lifecycle {
    private Form signInForm;

    

    //@Override
    //public void init(Object context) {
    //    Storage.init("MyAppStorage");  // Choose an appropriate storage name for your app
    //}

    @Override
    public void runApp() {
        
        signInForm = new Form("DO-DIE", BoxLayout.y());

        Label spacer = new Label();
        spacer.setPreferredH(Display.getInstance().convertToPixels(25)); // Adjust the height as needed

        // Add the spacer and the container to the form
        signInForm.add(spacer);


        TextComponent usernameCaption = new TextComponent();
        usernameCaption.label("username");
        //TextArea usernameSpace = new TextArea();

        TextComponentPassword passwordField = new TextComponentPassword();
        passwordField.label("password");
       // TextArea passwordSpace = new TextArea();

        signInForm.add(usernameCaption);
       // signInForm.add(usernameSpace);
        signInForm.add(passwordField);
       // signInForm.add(passwordSpace);

        Button signInButton = new Button("Sign in");
        signInForm.add(signInButton);
        signInButton.addActionListener(e -> signIn(usernameCaption.getText(), passwordField.getText()));

        signInForm.getToolbar().addMaterialCommandToSideMenu("DO-DIE Sign In Page", FontImage.MATERIAL_CHECK, 4, e -> hello());

                //signInForm.getToolbar().addMaterialCommandToSideMenu("DO-DIE Sign In Page", FontImage.MATERIAL_CHECK, 4, e -> hello());
        //signInForm.show();

        // Adding "Forgot Password?" button at the bottom
        Button forgotPasswordButton = new Button("Forgot Password?");
        forgotPasswordButton.addActionListener(e -> {
            Dialog forgotPasswordDialog = new Dialog("Reset Password");
            forgotPasswordDialog.setLayout(new BorderLayout());

            TextArea emailField = new TextArea(1, 20);
            //TextArea feedbackArea = new TextArea(5, 20);
            emailField.setHint("Enter your email");
            //emailField.setPreferredW(Display.getInstance().getDisplayWidth() / 2); 
            Button submitButton = new Button("Submit");
            submitButton.addActionListener(submitEvent -> {
                // Logic to handle the email submission
                String email = emailField.getText();
                // For example: resetPassword(email);
                
                // Show confirmation dialog
                Dialog.show("Email Sent", "A password reset link has been sent to: " + email, "OK", null);

                // Close the forgot password dialog
                forgotPasswordDialog.dispose();
        });
            forgotPasswordDialog.add(BorderLayout.CENTER, emailField);
            forgotPasswordDialog.add(BorderLayout.SOUTH, submitButton);

            forgotPasswordDialog.show();

        });
        signInForm.add(forgotPasswordButton);
        signInForm.show();
    }

    private void signIn(String username, String password) {
        if (isValidCredentials(username, password)) {
            showTaskOverview();
        } else {
            Dialog.show("Invalid Credentials", "Please check your username and password", "OK", null);
        }
    }

    private boolean isValidCredentials(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }

    private void hello() {
        Dialog.show("Sign Up Complete!", "Do you confirm these updates", "Yes", "No");
    }

    public void showSignInForm() {
        signInForm.showBack();
    }

    // public void showHomePage() {
    //     HomePage homePage = new HomePage(this);
    //     homePage.show();
    // }

    public void showTaskOverview(){
        // Use TaskManager to manage tasks across pages
        TasksOverviewPage tasksOverviewPage = new TasksOverviewPage(this, TaskManager.getInstance());
        tasksOverviewPage.show();
    }

    public void showCharacterSelection(){
        CharacterSelectionPage charSelectPage = new CharacterSelectionPage(this);
        charSelectPage.show();
    }

    public void showCharacterStatus() {
        CharacterStatusPage characterStatusPage = new CharacterStatusPage(this);
        characterStatusPage.show();
    }



    public void saveFeedback(String feedback) {
        try {
            Storage.getInstance().writeObject("feedback", feedback);
            System.out.println("Feedback saved successfully");
        } catch(Exception err) {
            err.printStackTrace();
            Dialog.show("Error", "Unable to save feedback.", "OK", null);
        }
    }

    public void checkAndDisplayFeedback() {
        String feedback = readFeedback();
        if (feedback != null) {
            Dialog.show(":)", "Feedback retrieved: " + feedback, "OK", null);
        } else {
            Dialog.show("Error", "No feedback found.", "OK", null);
        }
    }

    public String readFeedback() {
        try {
            // Attempt to read the feedback from storage
            InputStream is = Storage.getInstance().createInputStream("feedback");
            byte[] bytes = Util.readInputStream(is);
            is.close();
            
            // Convert bytes to string
            String savedFeedback = new String(bytes, "UTF-8");
            return savedFeedback;
        } catch (IOException err) {
            err.printStackTrace();
            Dialog.show("Error", "Unable to read feedback.", "OK", null);
        }
        return null; // Return null if there was an error
    }



        


    

    //pet class
    public class Pet {
        private String name;
        private Date birthday;
        private Date lastMeal;
        private Date lastDrink;
        long appStartTime = System.currentTimeMillis();
    
        public Pet(Date lastMeal, Date lastDrink) {
            this.name = "Yoshi";
            this.birthday = new Date();
            this.lastMeal = lastMeal;
            this.lastDrink = lastDrink;
        }
    
        public int getAge() {
            long elapsedTime = System.currentTimeMillis() - appStartTime;
            long seconds = elapsedTime / 1000;
            return (int) seconds;
        }
    
        public boolean isAboutToDie() {
            long timeThreshold = 259200; // 3 days in seconds
            long timeSinceLastMeal = new Date().getTime() - lastMeal.getTime();
            long timeSinceLastDrink = new Date().getTime() - lastDrink.getTime();
            return timeSinceLastMeal >= timeThreshold && timeSinceLastDrink >= timeThreshold;
        }
    
        public String getHappinessLevel() {
            String hunger = getHunger();
            String thirst = getThirst();
            return (hunger.equals("Hungry") || thirst.equals("Thirsty")) ? "Unhappy" : "Happy";
        }

        private long calcTimeSince(Date data) {
            long seconds = (long) (-data.getTime() + new Date().getTime()) / 1000;
            return seconds;
        }

        public String getHunger() {
            long timeSince = calcTimeSince(lastMeal);
            String string = "";
            if (timeSince >= 0 && timeSince < 3) {
                string = "Satisfied";
            } else if (timeSince >= 3 && timeSince < 6) {
                string = "Getting hungry...";
            } else if (timeSince >= 6) {
                string = "Hungry";
            } else if (timeSince <= 10) {
                string = "Dying";
            } else {
                string = "IDK";
            }
            return string;
        }
    
        public String getThirst() {
            long timeSince = calcTimeSince(lastDrink);
            String string = "";
            if (timeSince >= 0 && timeSince < 30) {
                string = "Satisfied";
            } else if (timeSince >= 30 && timeSince < 60) {
                string = "Getting thirsty...";
            } else if (timeSince >= 60) {
                string = "Thirsty";
            } else if (timeSince <= 259200) {
                string = "Dying";
            } else {
                string = "IDK";
            }
            return string;
        }

        public Date getLastMeal() {
            return lastMeal;
        }
    
        public Date getLastDrink() {
            return lastDrink;
        }
    }
    

    //Pet Repository that helps with storing data
    public class PetRepository {

        private static final String PET_KEY = "PET_KEY";
        private Pet pet;
    
        public PetRepository() {
            String petData = (String) Storage.getInstance().readObject(PET_KEY);
            if (petData != null) {
                this.pet = parsePetFromString(petData);
                System.out.println("Pet data successfully retrieved!");
            } else {
                this.pet = new Pet(new Date(), new Date());
            }
        }
    
        public Pet loadData() {
            return this.pet;
        }
    
        public void saveData(Pet pet) {
            String petData = convertPetToString(pet);
            Storage.getInstance().writeObject(PET_KEY, petData);
            System.out.println("Data saved at: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
    
        public void clearData() {
            Storage.getInstance().deleteStorageFile(PET_KEY);
            System.out.println("Data cleared at: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
    
        private String convertPetToString(Pet pet) {
            // Convert Pet data to a string representation
            // Example: "lastMeal=1642684800000&lastDrink=1642684800000"
            return "lastMeal=" + pet.getLastMeal().getTime() +
                   "&lastDrink=" + pet.getLastDrink().getTime();
        }
    
        private Pet parsePetFromString(String petData) {
            try {
                // Split the string into key-value pairs
                String[] pairs = petData.split("&");
                long lastMeal = Long.parseLong(pairs[0].split("=")[1]);
                long lastDrink = Long.parseLong(pairs[1].split("=")[1]);
    
                return new Pet(new Date(lastMeal), new Date(lastDrink));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }


    
  //  Now, the CharacterStatusPage class uses a Label to display the character status. You can update the character status by calling the updateCharacterStatus method with the desired status string. This should simplify the representation of character status without the need for a progress bar. If you have any specific requirements or adjustments, feel free to let me know!
    public interface PreviousFormSetter {
        void setPreviousForm(Form previousForm);
    }
    
    public class SettingsPage extends Form implements PreviousFormSetter {

        private Form previousForm;
    
        public SettingsPage() {
            super("Settings", BoxLayout.y());

            Image settingsIcon = FontImage.createMaterial(FontImage.MATERIAL_SETTINGS, new Style());
            Label settingsIconLabel = new Label(settingsIcon);

            this.add(settingsIconLabel);
    
            addButton("Account", () -> showSettingsPage(new AccountSettingsPage(this)));
            addButton("Privacy", () -> showSettingsPage(new PrivacySettingsPage(this)));
            addButton("Notification", () -> showSettingsPage(new NotificationSettingsPage(this)));
            addButton("Contact Support", () -> showSettingsPage(new ContactSupportPage(this)));
            addButton("Rate Us", () -> showSettingsPage(new RateUsPage(this)));
    
            // Add separation between buttons
            addAll(addSeparation(), getButtonsContainer(), addSeparation());
    
            // Add a back button to return to the previous form
            Button backButton = new Button("Back");
            backButton.addActionListener(e -> goToPreviousPage());
            add(backButton);
        }
    
        private void addButton(String label, Runnable action) {
            Button button = new Button(label);
            button.addActionListener(e -> action.run());
            add(button);
    
            // Debug statement
            System.out.println("Button added: " + label);
        }
    
        private Container addSeparation() {
            return BoxLayout.encloseY(new Label(), new Label()); // Add space between buttons
        }
    
        private Container getButtonsContainer() {
            return new Container(BoxLayout.y());
        }
    
        private void showSettingsPage(Form settingsPage) {
            settingsPage.show();
        }
    
    
        @Override
    
        public void setPreviousForm(Form previousForm) {
            this.previousForm = previousForm;
        }

        private void goToPreviousPage() {
            if (previousForm != null) {
                previousForm.showBack();
            }
        }
    }
    
    // AccountSettingsPage
    public class AccountSettingsPage extends Form {

        private final Form previousForm;

        public AccountSettingsPage(Form previousForm) {
            super("Account Settings", BoxLayout.y());
            this.previousForm = previousForm;


            Button goToPreviousPageButton = new Button("Go to Previous Page");
            goToPreviousPageButton.addActionListener(e -> goToPreviousPage());

            // Days of the week
            String[] accountSettings = {"Profile", "Password & Security", "Your Information", "Personal Details", "Ad Preferences", "Payments"};
    
            for (String setting : accountSettings) {
                
                // Day label
                Label accountSettingLabel = new Label(setting);
                accountSettingLabel.getAllStyles().setFgColor(0x000000);
                add(accountSettingLabel);
    
                // Spacing between days
                //add(createSpacer());
            }

            //add(contentContainer);
            Label spacer = new Label();
            spacer.setPreferredH(Display.getInstance().convertToPixels(30)); // Adjust the height as needed

            // Add the spacer and the container to the form
            this.add(spacer);
            add(goToPreviousPageButton);
        }

        private void goToPreviousPage() {
            if (previousForm != null) {
                previousForm.showBack();
            }
        }
    }

    // PrivacySettingsPage
    public class PrivacySettingsPage extends Form {

        private final Form previousForm;
        

        public PrivacySettingsPage(Form previousForm) {
            super("Privacy Settings", BoxLayout.y());
            this.previousForm = previousForm;


            Button goToPreviousPageButton = new Button("Go to Previous Page");
            goToPreviousPageButton.addActionListener(e -> goToPreviousPage());

            Container contentContainer = new Container(BoxLayout.y());
            String privacyText = "Absolutely! It's always interesting to think about the vastness of the universe and how we, as humans, fit into it. We're on this relatively small planet, spinning around a ball of fiery gas, in a galaxy that's just one of billions. It's a humbling thought." +
            "Then there's the marvel of human ingenuity. From the creation of simple tools and fire to modern technology like smartphones and space rockets, our journey has been incredible. We're constantly pushing the boundaries of what's possible, exploring new frontiers, both on Earth and beyond." +
            "YAP YAP YAP YAP BLAH BLAH BLAH BLAH YAP YAP YAP YAP BLAH BLAH BLAH. If y'all notice this, you're attentive asf LOL. fascinating to consider the evolution of languages too. How they've grown, adapted, and morphed over thousands of years. Languages are like living, breathing entities," +
            "constantly evolving with us. It's a testament to our need to communicate, to share ideas, and to connect with one another. And let's not forget about the little joys of everyday life, a freshly brewed cup of coffee, the smell of rain, a good book, or a heartwarming conversation with a friend." + 
            "These simple pleasures add so much value to our lives, often more than never than we realize. Oh and also Andrea, Christy, and I slayed we da best team ever! It's these small moments that string together to form the tapestry of our lives. So you're basically signing your life away to this app (jk lol unless...)" +
            "Sprinkle Sprinkle.";
            
            contentContainer.add(new SpanLabel(privacyText));

            //CheckBox checkBox = new CheckBox(t.getTaskName());
            //taskContainer.add(checkBox);

            add(contentContainer);

            Label spacer = new Label();
            spacer.setPreferredH(Display.getInstance().convertToPixels(10)); // Adjust the height as needed

            // Add the spacer and the container to the form
            this.add(spacer);

            // CheckBox with a label
            CheckBox agreeCheckBox = new CheckBox("I agree to terms and conditions");
            // Optionally, you can set the CheckBox to be initially unchecked
            agreeCheckBox.setSelected(false);
            contentContainer.add(agreeCheckBox); // Add the CheckBox to the container

            add(goToPreviousPageButton);

            Label spacer1 = new Label();
            spacer1.setPreferredH(Display.getInstance().convertToPixels(5)); // Adjust the height as needed

            // Add the spacer and the container to the form
            this.add(spacer1);
        }


        private void goToPreviousPage() {
            if (previousForm != null) {
                previousForm.showBack();
            }
        }
    }

    // NotificationSettingsPage
    public class NotificationSettingsPage extends Form {

        private final Form previousForm;
        private boolean isToggled;

        public NotificationSettingsPage(Form previousForm) {
            super("Notification", BoxLayout.y());
            this.previousForm = previousForm;

            
            isToggled = false;

            
            Button toggleButton = new Button("OFF");
            toggleButton.getAllStyles().setBgTransparency(255); // Ensure background is opaque
            toggleButton.getAllStyles().setBgColor(0xFFFFA0A9); // Start with red background


            toggleButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    isToggled = !isToggled; 

                    if (isToggled) {
                        toggleButton.setText("ON"); 
                        toggleButton.getAllStyles().setBgColor(0xFF77DD77); // Set background to green
                    } else {
                        toggleButton.setText("OFF"); 
                        toggleButton.getAllStyles().setBgColor(0xFFFF8C9A); // Set background to green
                        
                    }
                    toggleButton.repaint(); // Repaint the button to update the style
                }
            });

        // Add the toggle button to the form
            add(toggleButton);

            // Add notification settings components and logic here

            Button goToPreviousPageButton = new Button("Go to Previous Page");
            goToPreviousPageButton.addActionListener(e -> goToPreviousPage());

            Label spacer = new Label();
            spacer.setPreferredH(Display.getInstance().convertToPixels(80)); // Adjust the height as needed

            // Add the spacer and the container to the form
            this.add(spacer);


            //add(contentContainer);
            add(goToPreviousPageButton);
        }

        private void goToPreviousPage() {
            if (previousForm != null) {
                previousForm.showBack();
            }
        }
    }

    // ContactSupportPage
    public class ContactSupportPage extends Form {

        private final Form previousForm;

        public ContactSupportPage(Form previousForm) {
            super("Contact Support", BoxLayout.y());
            this.previousForm = previousForm;

            Label spacer1 = new Label();
            spacer1.setPreferredH(Display.getInstance().convertToPixels(8)); // Adjust the height as needed

            // Add the spacer and the container to the form
            this.add(spacer1);

            
        // Creating labels for contact information
            //Label contactHeaderLabel = new Label("Contact Us:");
            Label contactAndreaLabel = new Label("Andrea Chen - ychen729@ucsc.edu");
            Label contactChristyLabel = new Label("Christy Jose - cmjose@ucsc.edu");
            Label contactShipraLabel = new Label("Shipra Ithal - sithal@ucsc.edu");

            // Adding labels to a container
            Container contactContainer = new Container(BoxLayout.y());
            contactContainer.addAll(contactAndreaLabel, contactChristyLabel, contactShipraLabel);



            Button goToPreviousPageButton = new Button("Go to Previous Page");
            goToPreviousPageButton.addActionListener(e -> goToPreviousPage());

            //Container contentContainer = new Container(BoxLayout.y());
            //contentContainer.addAll(contactLabel, phoneNumberLabel);

            add(contactContainer);

            Label spacer = new Label();
            spacer.setPreferredH(Display.getInstance().convertToPixels(68)); // Adjust the height as needed

            // Add the spacer and the container to the form
            this.add(spacer);
            add(goToPreviousPageButton);
        }

        private void goToPreviousPage() {
            if (previousForm != null) {
                previousForm.showBack();
            }
        }
    }

    // RateUsPage
    public class RateUsPage extends Form {

        private final Form previousForm;

        public RateUsPage(Form previousForm) {
            super("Rate Us", BoxLayout.y());
            this.previousForm = previousForm;

            // Add rate us components and logic here
            Image thumbsUpIcon = FontImage.createMaterial(FontImage.MATERIAL_THUMB_UP, new Style());
            Image thumbsDownIcon = FontImage.createMaterial(FontImage.MATERIAL_THUMB_DOWN, new Style());

            Button thumbsUpButton = new Button(thumbsUpIcon );
            Button thumbsDownButton = new Button(thumbsDownIcon );

            thumbsUpButton.addActionListener(e -> showPositiveFeedbackForm());
            thumbsDownButton.addActionListener(e -> {new ContactSupportPage(previousForm).show(); // Assuming ContactSupportPage has a constructor accepting a Form
            });

            this.add(thumbsUpButton);
            this.add(thumbsDownButton);

            Button goToPreviousPageButton = new Button("Go to Previous Page");
            goToPreviousPageButton.addActionListener(e -> goToPreviousPage());

            //Container contentContainer = new Container(BoxLayout.y());
            //contentContainer.add(new SpanLabel("Rate us content goes here"));

            //add(contentContainer);
            add(goToPreviousPageButton);

            Label spacer = new Label();
            spacer.setPreferredH(Display.getInstance().convertToPixels(30)); // Adjust the height as needed

            // Add the spacer and the container to the form
            this.add(spacer);

            // Create the style for the icon
            Style s = new Style();
            s.setFont(FontImage.getMaterialDesignFont().derive(Display.getInstance().convertToPixels(30), Font.STYLE_PLAIN));

            // Create the character icon
            s.setFgColor(0xffa0a9); // Set the foreground color to red (in ARGB format)
            Image heartIcon = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);

            // Use the icon in a button
            Label heartIconLabel = new Label(heartIcon);
            // Set background color
            Style labelStyle = heartIconLabel.getAllStyles();
            labelStyle.setBgColor(0xe89091); // Green background color
            labelStyle.setBgTransparency(255); // Opaque background

            // Create a container with a centered layout for the icon
            Container centerContainer = new Container(new FlowLayout(Component.CENTER));
            centerContainer.add(heartIconLabel);

            this.add(centerContainer);

            Label spacer1 = new Label();
            spacer1.setPreferredH(Display.getInstance().convertToPixels(10)); // Adjust the height as needed

            // Add the spacer and the container to the form
            this.add(spacer1);

            Button checkFeedbackButton = new Button("People love us!!");
            checkFeedbackButton.addActionListener(e -> checkAndDisplayFeedback());
            add(checkFeedbackButton);
        }
        private void showPositiveFeedbackForm() {
            Dialog feedbackDialog = new Dialog("Positive Feedback");

            TextArea feedbackArea = new TextArea(5, 20);
            feedbackArea.setHint("Type your feedback here...");

            Button submitButton = new Button("Submit");
            submitButton.addActionListener(e -> {
                String feedback = feedbackArea.getText();
            // Here, you can handle the feedback, like sending it to a server or email
            // For now, let's just show a confirmation dialog
                if(!feedback.isEmpty()) {
                    saveFeedback(feedback); // Call the method to save feedback
                    Dialog.show("Thank You", "Your feedback has been received.", "OK", null);
                    feedbackDialog.dispose(); // Close the dialog after submission
                }
            });
        Button doneButton = new Button("Done");
        doneButton.addActionListener(e -> feedbackDialog.dispose()); // Close the dialog 

        feedbackDialog.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        feedbackDialog.addAll(feedbackArea, submitButton, doneButton);

        feedbackDialog.show();
        }


        private void goToPreviousPage() {
            if (previousForm != null) {
                previousForm.showBack();
            }
        }
    }

    public static class CustomProgressBar extends Container {

        private float progress;

        public CustomProgressBar() {
            this.progress = 0.5f; // Set the initial progress value
            getAllStyles().setBgColor(0xFFFFFF); // Set the background color
            getAllStyles().setBorder(Border.createLineBorder(1, 0x000000)); // Set the border color and size
        }

        public float getProgress() {
            return progress;
        }

        public void setProgress(float progress) {
            if (progress >= 0 && progress <= 1) {
                this.progress = progress;
                repaint(); // Trigger repaint to update the progress bar
            }
        }

        @Override
        protected void paintBackground(Graphics g) {
            super.paintBackground(g);

            // Calculate the width of the filled area based on the progress
            int fillWidth = (int) (getWidth() * progress);

            // Set the color of the filled area
            g.setColor(0x007AFF); // You can customize the color here

            // Draw the filled area
            g.fillRect(getX(), getY(), fillWidth, getHeight());
        }

        @Override
        protected Dimension calcPreferredSize() {
            return new Dimension(260, 37); // Set the preferred size of your progress bar
        }
    }

    public class WeeklySummaryPage extends Form {

        private final Form previousForm;
    
        public WeeklySummaryPage(Form previousForm) {
            super("Weekly Summary", BoxLayout.y());
            this.previousForm = previousForm;
    
            // Days of the week
            String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    
            for (String day : days) {
                // Day label
                Label dayLabel = new Label(day);
                dayLabel.getAllStyles().merge(createDayLabelStyle());
                add(dayLabel);
    
                // Custom progress bar
                CustomProgressBar customProgressBar = new CustomProgressBar();
                add(customProgressBar);
    
                // Spacing between days
                add(createSpacer());
            }
    
            // Set the back command to return to the previous form
            getToolbar().setBackCommand("Back", e -> goToPreviousPage());
        }
    
        private void goToPreviousPage() {
            if (previousForm != null) {
                previousForm.showBack();
            }
        }
    
        private Style createDayLabelStyle() {
            Style dayLabelStyle = new Style();
            dayLabelStyle.setBgColor(0xFFFFFF);
            dayLabelStyle.setFgColor(0x000000);
            dayLabelStyle.setBorder(Border.createLineBorder(1, 0x000000));
            //dayLabelStyle.setFont(Font.createTrueTypeFont("Inter", "Inter-Regular.ttf", Font.STYLE_PLAIN, 20));
            //dayLabelStyle.setFont(Font.createTrueTypeFont("Inter", "Inter-Regular.ttf").derive(Font.STYLE_PLAIN, 20));
            dayLabelStyle.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
    
            return dayLabelStyle;
        }
    
        private Label createSpacer() {
            Label spacer = new Label();
            spacer.getAllStyles().merge(createSpacerStyle());
            return spacer;
        }
    
        private Style createSpacerStyle() {
            Style spacerStyle = new Style();
            spacerStyle.setBgColor(0xFFFFFF);
            spacerStyle.setBgTransparency(255);
            return spacerStyle;
        }
        
    }
    
    
    
    
    //Christy's section: Tasks

    //Andrea's section:  Achievements, Settings

    //Dawn's section: Characters

}