package org.example.gui.windows;

import com.vaadin.ui.*;

public class ConfirmationWindow extends Window {

    public ConfirmationWindow(String text){
        super("Bestätigung"); //set window caption
        center();

        //basic content for window
        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label(text));
        content.setMargin(true);
        setContent(content);

        Button reservButton = new Button("Ok, bis bald");
        reservButton.addClickListener(e -> close());

        content.addComponent(reservButton);
        content.setComponentAlignment(reservButton, Alignment.MIDDLE_CENTER);
    }
}
