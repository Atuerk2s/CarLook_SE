package org.example.gui.ui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import org.example.gui.views.LoginView;
import org.example.gui.views.MainView;
import org.example.gui.views.RegistrationView;
import org.example.model.objects.dto.User;
import org.example.services.util.Views;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Title("CarLook - Buchungssystem")
@PreserveOnRefresh
public class MyUI extends UI {

    private User user = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Navigator navi = new Navigator(this, this);

        navi.addView(Views.MAIN, MainView.class);
        navi.addView(Views.LOGIN, LoginView.class);
        navi.addView(Views.REG, RegistrationView.class);

        UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    public MyUI getMyUI(){
        return (MyUI) UI.getCurrent();
    }

}