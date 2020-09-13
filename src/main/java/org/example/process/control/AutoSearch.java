package org.example.process.control;

import org.example.model.dao.AutoDAO;
import org.example.model.objects.dto.Auto;

import java.util.List;

public class AutoSearch {

    private AutoSearch(){

    }

    public static AutoSearch search = null;

    public static AutoSearch getInstance(){
        if(search==null){
            search = new AutoSearch();
        }
        return search;
    }

    public List<Auto> getAutobyMarke(String marke){
        return AutoDAO.getInstance().getAutoByMarke(marke);
    }
}
