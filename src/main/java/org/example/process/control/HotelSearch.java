package org.example.process.control;

import org.example.model.dao.AutoDAO;
import org.example.model.objects.dto.Auto;

import java.util.List;

public class HotelSearch {

    Auto Auto1 = new Auto("BMW", 1, 1992, "4-Türer");
    Auto Auto2 = new Auto("AUDI", 2, 1993, "Schiebedach");
    Auto Auto3 = new Auto("Mercedes", 3, 1994, "Mattschwarz");

    private HotelSearch(){

    }

    public static HotelSearch search = null;

    public static HotelSearch getInstance(){
        if(search==null){
            search = new HotelSearch();
        }
        return search;
    }

    public List<Auto> getAutoByMarke(String marke){
        return AutoDAO.getInstance().getAutoByMarke(marke);
    }
}
