package com.model.liveUpdates;

/**
 * Created by asaf on 17/02/2016.
 */
public class LiveSumData {

        String id;
        Double maofPL;
        Double B;
        Double primiumPL;
        Double pricePL;
        Double callPL;
        Double F;
        Double G;
        Double maofStrike;
        Double putStrike;
        Double J;
        Double K;
        Double priceStrike;
        Double primiumStrike;
        Double N;
        Double Butterfly1;
        Double Butterfly2;
        Double Butterfly3;
        Double date;
        Double cashFlow;
        Double teoCall;
        Double teoPut;



    public LiveSumData() {    }

    public LiveSumData(Double maofPL, Double b, Double primiumPL, Double pricePL, Double callPL, Double f, Double g, Double maofStrike, Double putStrike, Double j, Double k, Double priceStrike, Double primiumStrike, Double n, Double butterfly1, Double butterfly2, Double butterfly3, Double date, Double cashFlow, Double teoCall, Double teoPut) {
        this.maofPL = maofPL;
        B = b;
        this.primiumPL = primiumPL;
        this.pricePL = pricePL;
        this.callPL = callPL;
        F = f;
        G = g;
        this.maofStrike = maofStrike;
        this.putStrike = putStrike;
        J = j;
        K = k;
        this.priceStrike = priceStrike;
        this.primiumStrike = primiumStrike;
        N = n;
        Butterfly1 = butterfly1;
        Butterfly2 = butterfly2;
        Butterfly3 = butterfly3;
        this.date = date;
        this.cashFlow = cashFlow;
        this.teoCall = teoCall;
        this.teoPut = teoPut;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getMaofPL() {
        return maofPL;
    }

    public void setMaofPL(Double maofPL) {
        this.maofPL = maofPL;
    }

    public Double getB() {
        return B;
    }

    public void setB(Double b) {
        B = b;
    }

    public Double getPrimiumPL() {
        return primiumPL;
    }

    public void setPrimiumPL(Double primiumPL) {
        this.primiumPL = primiumPL;
    }

    public Double getPricePL() {
        return pricePL;
    }

    public void setPricePL(Double pricePL) {
        this.pricePL = pricePL;
    }

    public Double getCallPL() {
        return callPL;
    }

    public void setCallPL(Double callPL) {
        this.callPL = callPL;
    }

    public Double getF() {
        return F;
    }

    public void setF(Double f) {
        F = f;
    }

    public Double getG() {
        return G;
    }

    public void setG(Double g) {
        G = g;
    }

    public Double getMaofStrike() {
        return maofStrike;
    }

    public void setMaofStrike(Double maofStrike) {
        this.maofStrike = maofStrike;
    }

    public Double getPutStrike() {
        return putStrike;
    }

    public void setPutStrike(Double putStrike) {
        this.putStrike = putStrike;
    }

    public Double getJ() {
        return J;
    }

    public void setJ(Double j) {
        J = j;
    }

    public Double getK() {
        return K;
    }

    public void setK(Double k) {
        K = k;
    }

    public Double getPriceStrike() {
        return priceStrike;
    }

    public void setPriceStrike(Double priceStrike) {
        this.priceStrike = priceStrike;
    }

    public Double getPrimiumStrike() {
        return primiumStrike;
    }

    public void setPrimiumStrike(Double primiumStrike) {
        this.primiumStrike = primiumStrike;
    }

    public Double getN() {
        return N;
    }

    public void setN(Double n) {
        N = n;
    }

    public Double getButterfly1() {
        return Butterfly1;
    }

    public void setButterfly1(Double butterfly1) {
        Butterfly1 = butterfly1;
    }

    public Double getButterfly2() {
        return Butterfly2;
    }

    public void setButterfly2(Double butterfly2) {
        Butterfly2 = butterfly2;
    }

    public Double getButterfly3() {
        return Butterfly3;
    }

    public void setButterfly3(Double butterfly3) {
        Butterfly3 = butterfly3;
    }

    public Double getDate() {
        return date;
    }

    public void setDate(Double date) {
        this.date = date;
    }

    public Double getCashFlow() {
        return cashFlow;
    }

    public void setCashFlow(Double cashFlow) {
        this.cashFlow = cashFlow;
    }

    public Double getTeoCall() {
        return teoCall;
    }

    public void setTeoCall(Double teoCall) {
        this.teoCall = teoCall;
    }

    public Double getTeoPut() {
        return teoPut;
    }

    public void setTeoPut(Double teoPut) {
        this.teoPut = teoPut;
    }
}
