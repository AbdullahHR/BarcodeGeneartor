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
public class Category {
    private Integer id;
    private String category;
    private String categorydescription;

    public Category(Integer id, String category, String categorydescription) {
        this.id = id;
        this.category = category;
        this.categorydescription = categorydescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategorydescription() {
        return categorydescription;
    }

    public void setCategorydescription(String categorydescription) {
        this.categorydescription = categorydescription;
    }
    
}
