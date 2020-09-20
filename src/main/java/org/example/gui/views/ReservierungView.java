package org.example.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.example.gui.components.TopPanel;
import org.example.gui.ui.MyUI;
import org.example.model.dao.AutoDAO;
import org.example.model.objects.dto.Auto;
import org.example.model.objects.dto.User;
import org.example.services.util.Views;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservierungView extends VerticalLayout implements View {
    User user;

    public void enter(ViewChangeListener.ViewChangeEvent event){

        //User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        user = ((MyUI) UI.getCurrent()).getUser();

        //falls User nicht eingeloggt, kann er Main nicht accessen und wird auf Loginpage delegiert
        if(user == null){
            UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
        }else {
            this.setUp();
        }
    }
    public void setUp(){

        //keine Linie angezeigt, deshalb Label explizit erstellt mit Width Änderung
        this.addComponent(new TopPanel());
        Label hr = new Label("<hr />", ContentMode.HTML);
        hr.setWidth(100f, Unit.PERCENTAGE);
        this.addComponent(hr);

        setMargin(true);

        final HorizontalLayout horizon = new HorizontalLayout();

        Grid<Auto> grid = new Grid<>();
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);

        List<Auto> auto = new ArrayList<Auto>();

        List<Integer> resList = null;
        try {
            resList = AutoDAO.getInstance().getAutoIDbyResID(user.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ReservierungView.class.getName()).log(Level.SEVERE, null, ex);
        }

        for(int i=0; i<resList.size();i++){
            try {
                auto.add(AutoDAO.getInstance().getAutoByAutoID(resList.get(i)));
            } catch (SQLException ex) {
                Logger.getLogger(ReservierungView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
