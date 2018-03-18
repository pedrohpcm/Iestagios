/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Date;

/**
 *
 * @author joaovictor
 */
public class Log {
    private Date modificationdate;
    private String modifiedtable;
    private String modifiedfield;
    private String modifier;
    private String content;
    private String operation;

    public Date getModificationdate() {
        return modificationdate;
    }

    public void setModificationdate(Date modificationdate) {
        this.modificationdate = modificationdate;
    }

    public String getModifiedtable() {
        return modifiedtable;
    }

    public void setModifiedtable(String modifiedtable) {
        this.modifiedtable = modifiedtable;
    }

    public String getModifiedfield() {
        return modifiedfield;
    }

    public void setModifiedfield(String modifiedfield) {
        this.modifiedfield = modifiedfield;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
