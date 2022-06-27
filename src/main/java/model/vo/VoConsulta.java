/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.vo;

import java.sql.ResultSet;

/**
 *
 * @author Daniel
 */
public class VoConsulta {
    private ResultSet rs;

    public VoConsulta(ResultSet rs) {
        this.rs = rs;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    
}
