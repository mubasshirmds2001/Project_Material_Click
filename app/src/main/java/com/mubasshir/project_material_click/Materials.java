package com.mubasshir.project_material_click;

import com.google.firebase.database.Exclude;

public class Materials {
    private String materialid,party,date,material,quantity,rate,amount;

    public Materials(String materialid, String party, String date, String material, String quantity, String rate, String amount) {
        this.materialid = materialid;
        this.party = party;
        this.date = date;
        this.material = material;
        this.quantity = quantity;
        this.rate = rate;
        this.amount = amount;
    }
    @Exclude
    public String getMaterialid() {
        return materialid;
    }
    @Exclude
    public void setMaterialid(String materialid) {
        this.materialid = materialid;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Materials(){

    }
}
