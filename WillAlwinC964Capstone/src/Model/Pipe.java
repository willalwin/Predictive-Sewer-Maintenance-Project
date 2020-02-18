/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Will
 */
public class Pipe {
    private int objectId,
                lengthFt,
                diameter,
                stormwater,
                pci,
                pacp,
                age,
                upstreamCnt,
                slClass,
                directCnt,
                flushPerYear,
                vegScore,
                mhAngle;
            
    private float featureId,
                  fromMH,
                  slope,
                  flowTo,
                  rise,
                  qInf,
                  q,
                  dLeftTarget,
                  dn,
                  nFull,
                  sedVol,
                  sedPerYear,
                  v,
                  adjVar;
            
    private String material,
                   cfcc,
                   landuse,
                   knownIssues,
                   upsource,
                   gdbGeomattrData,
                   tech;
    private LocalDate lastMaint,
                      nextMaint;

    public Pipe(int objectId, int lengthFt, int diameter, int stormwater, float featureId, float fromMH, float slope, String material, String cfcc) {
        this.objectId = objectId;
        this.lengthFt = lengthFt;
        this.diameter = diameter;
        this.stormwater = stormwater;
        this.featureId = featureId;
        this.fromMH = fromMH;
        this.slope = slope;
        this.material = material;
        this.cfcc = cfcc;
    }

    public Pipe(int objectId, int lengthFt, int diameter, int stormwater, int pci, int pacp, int age, int upstreamCnt, int slClass, int directCnt, int flushPerYear, int vegScore, int mhAngle, float featureId, float fromMH, float slope, float flowTo, float rise, float qInf, float q, float dLeftTarget, float dn, float nFull, float sedVol, float sedPerYear, float v, float adjVar, String material, String cfcc, String landuse, String knownIssues, String upsource, String gdbGeomattrData, String tech, LocalDate lastMaint, LocalDate nextMaint) {
        this.objectId = objectId;
        this.lengthFt = lengthFt;
        this.diameter = diameter;
        this.stormwater = stormwater;
        this.pci = pci;
        this.pacp = pacp;
        this.age = age;
        this.upstreamCnt = upstreamCnt;
        this.slClass = slClass;
        this.directCnt = directCnt;
        this.flushPerYear = flushPerYear;
        this.vegScore = vegScore;
        this.mhAngle = mhAngle;
        this.featureId = featureId;
        this.fromMH = fromMH;
        this.slope = slope;
        this.flowTo = flowTo;
        this.rise = rise;
        this.qInf = qInf;
        this.q = q;
        this.dLeftTarget = dLeftTarget;
        this.dn = dn;
        this.nFull = nFull;
        this.sedVol = sedVol;
        this.sedPerYear = sedPerYear;
        this.v = v;
        this.adjVar = adjVar;
        this.material = material;
        this.cfcc = cfcc;
        this.landuse = landuse;
        this.knownIssues = knownIssues;
        this.upsource = upsource;
        this.gdbGeomattrData = gdbGeomattrData;
        this.tech = tech;
        this.lastMaint = lastMaint;
        this.nextMaint = nextMaint;
    }
    
    public void updateEmpInDB() throws SQLException {
        //variables for dateTime conversion
        Date date = Date.valueOf(LocalDate.now());
        Time time = Time.valueOf(LocalTime.now());
        String dateTime  = date + " " + time;
        
        PreparedStatement preparedStatement = null;
        try{
            //connect to DB
            DBConnection.makeConnection();
            //create a string that holds the query with ? as user inputs
            String sql = "UPDATE U06dWx.WCCSD_PIPE SET tech = ? "
                    + "WHERE objectId = ?";
            //prepare the query
            preparedStatement = DBConnection.getConn().prepareStatement(sql); 
            //bind the values to the parameters
            preparedStatement.setString(1, tech);
            preparedStatement.setInt(7, objectId);

            preparedStatement.executeUpdate();            
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        finally{
            if(preparedStatement != null)
                preparedStatement.close();
            if(DBConnection.getConn() != null)
                DBConnection.closeConnection();
        }
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getLengthFt() {
        return lengthFt;
    }

    public void setLengthFt(int lengthFt) {
        this.lengthFt = lengthFt;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getStormwater() {
        return stormwater;
    }

    public void setStormwater(int stormwater) {
        this.stormwater = stormwater;
    }

    public int getPci() {
        return pci;
    }

    public void setPci(int pci) {
        this.pci = pci;
    }

    public int getPacp() {
        return pacp;
    }

    public void setPacp(int pacp) {
        this.pacp = pacp;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getUpstreamCnt() {
        return upstreamCnt;
    }

    public void setUpstreamCnt(int upstreamCnt) {
        this.upstreamCnt = upstreamCnt;
    }

    public int getSlClass() {
        return slClass;
    }

    public void setSlClass(int slClass) {
        this.slClass = slClass;
    }

    public int getDirectCnt() {
        return directCnt;
    }

    public void setDirectCnt(int directCnt) {
        this.directCnt = directCnt;
    }

    public int getFlushPerYear() {
        return flushPerYear;
    }

    public void setFlushPerYear(int flushPerYear) {
        this.flushPerYear = flushPerYear;
    }

    public int getVegScore() {
        return vegScore;
    }

    public void setVegScore(int vegScore) {
        this.vegScore = vegScore;
    }

    public int getMhAngle() {
        return mhAngle;
    }

    public void setMhAngle(int mhAngle) {
        this.mhAngle = mhAngle;
    }

    public float getFeatureId() {
        return featureId;
    }

    public void setFeatureId(float featureId) {
        this.featureId = featureId;
    }

    public float getFromMH() {
        return fromMH;
    }

    public void setFromMH(float fromMH) {
        this.fromMH = fromMH;
    }

    public float getSlope() {
        return slope;
    }

    public void setSlope(float slope) {
        this.slope = slope;
    }

    public float getFlowTo() {
        return flowTo;
    }

    public void setFlowTo(float flowTo) {
        this.flowTo = flowTo;
    }

    public float getRise() {
        return rise;
    }

    public void setRise(float rise) {
        this.rise = rise;
    }

    public float getqInf() {
        return qInf;
    }

    public void setqInf(float qInf) {
        this.qInf = qInf;
    }

    public float getQ() {
        return q;
    }

    public void setQ(float q) {
        this.q = q;
    }

    public float getdLeftTarget() {
        return dLeftTarget;
    }

    public void setdLeftTarget(float dLeftTarget) {
        this.dLeftTarget = dLeftTarget;
    }

    public float getDn() {
        return dn;
    }

    public void setDn(float dn) {
        this.dn = dn;
    }

    public float getnFull() {
        return nFull;
    }

    public void setnFull(float nFull) {
        this.nFull = nFull;
    }

    public float getSedVol() {
        return sedVol;
    }

    public void setSedVol(float sedVol) {
        this.sedVol = sedVol;
    }

    public float getSedPerYear() {
        return sedPerYear;
    }

    public void setSedPerYear(float sedPerYear) {
        this.sedPerYear = sedPerYear;
    }

    public float getV() {
        return v;
    }

    public void setV(float v) {
        this.v = v;
    }

    public float getAdjVar() {
        return adjVar;
    }

    public void setAdjVar(float adjVar) {
        this.adjVar = adjVar;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCfcc() {
        return cfcc;
    }

    public void setCfcc(String cfcc) {
        this.cfcc = cfcc;
    }

    public String getLanduse() {
        return landuse;
    }

    public void setLanduse(String landuse) {
        this.landuse = landuse;
    }

    public String getKnownIssues() {
        return knownIssues;
    }

    public void setKnownIssues(String knownIssues) {
        this.knownIssues = knownIssues;
    }

    public String getUpsource() {
        return upsource;
    }

    public void setUpsource(String upsource) {
        this.upsource = upsource;
    }

    public String getGdbGeomattrData() {
        return gdbGeomattrData;
    }

    public void setGdbGeomattrData(String gdbGeomattrData) {
        this.gdbGeomattrData = gdbGeomattrData;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public LocalDate getLastMaint() {
        return lastMaint;
    }

    public void setLastMaint(LocalDate lastMaint) {
        this.lastMaint = lastMaint;
    }

    public LocalDate getNextMaint() {
        return nextMaint;
    }

    public void setNextMaint(LocalDate nextMaint) {
        this.nextMaint = nextMaint;
    }

    
    

}    