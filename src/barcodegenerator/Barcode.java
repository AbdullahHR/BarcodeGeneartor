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
public class Barcode {
    private Integer id;
    private String description;
    private String generationdate;
    private Integer userid;
    private String category;
    private String type;

    public Barcode(Integer id, String description, String generationdate, Integer userid, String category, String type) {
        this.id = id;
        this.description = description;
        this.generationdate = generationdate;
        this.userid = userid;
        this.category = category;
        this.type = type;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenerationdate() {
        return generationdate;
    }

    public void setGenerationdate(String generationdate) {
        this.generationdate = generationdate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
