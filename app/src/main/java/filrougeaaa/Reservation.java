package filrougeaaa;

import java.sql.Date;

import filrougeaaa.utils.Model;

public class Reservation extends Model{
    protected Date date;
    protected int nb_customer;



    @Override
    public boolean get(int id) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean save() {
        // TODO Auto-generated method stub
        return false;
    }

    //#region get/set
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getNb_customer() {
        return nb_customer;
    }
    public void setNb_customer(int nb_customer) {
        this.nb_customer = nb_customer;
    }
   //#endregion
    
}
