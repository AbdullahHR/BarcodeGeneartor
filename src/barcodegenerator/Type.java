/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcodegenerator;

/**
 *
 * @author Ahr97
 */
public class Type {
    private Integer id;
    private String barcodetype;
    private String typedescription;
    private String usage;

    public Type(Integer id, String barcodetype, String typedescription, String usage) {
        this.id = id;
        this.barcodetype = barcodetype;
        this.typedescription = typedescription;
        this.usage = usage;
    }

    public Integer getId() {
        return id;
    }

    public String getBarcodetype() {
        return barcodetype;
    }

    public void setBarcodetype(String barcodetype) {
        this.barcodetype = barcodetype;
    }

    public String getTypedescription() {
        return typedescription;
    }

    public void setTypedescription(String typedescription) {
        this.typedescription = typedescription;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }
    
    
}
