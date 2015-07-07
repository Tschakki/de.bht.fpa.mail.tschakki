package controller;

/**
 * Created by tschakki on 07.07.15.
 */
public abstract class Filter extends FpaMessageLoader{

    FpaMessageLoader fpaMessageLoader;
    String filterCriteria;

    public Filter(FpaMessageLoader fpaMessageLoader, String filterCrit){
        this.fpaMessageLoader = fpaMessageLoader;
        this.filterCriteria = filterCrit;
    }
}
