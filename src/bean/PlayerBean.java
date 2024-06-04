package bean;

import data.Imagedata;
import java.awt.image.BufferedImage;

import java.io.Serializable;



public class PlayerBean implements Serializable{

    private int level; // 等級
    private int hp; // 生命值
    private int attack; // 攻擊力
    private int defend; // 防禦力
    private int money; // 金錢
    private int exp; // 經驗
    private int Ykey; // 黃色鑰匙數
    private int Bkey; // 藍色鑰匙數
    private int Rkey; // 紅色鑰匙數

    private int toward; // 當前朝向 0-左 1-下 2-右 3-上
    private int posX; // X 坐標值
    private int posY; // Y 坐標值

    // 構造器 遊戲開始時主角的初始屬性
    public PlayerBean() {

        this.level = 1; // 初始等級 1
        this.hp = 450; // 初始生命值 450
        this.attack = 40; // 初始攻擊力 40
        this.defend = 10; // 初始防禦力 10
        this.money = 0; // 初始金錢 0
        this.exp = 25; // 初始經驗值 0
        this.Ykey = 0; // 初始黃鑰匙數 0
        this.Bkey = 0; // 初始藍鑰匙數 0
        this.Rkey = 0; // 初始紅鑰匙數 0

        this.toward = 1; // 初始朝向
        this.posX = 5; // 初始 X坐標
        this.posY = 9; // 初始 Y坐標


    }

    public void move(int cx, int cy) {
        posX = cx;
        posY = cy;
    }

    public BufferedImage draw() {
        if (toward == 0)
            return Imagedata.playerMap.get(-1);
        if (toward == 1)
            return Imagedata.playerMap.get(-2);
        if (toward == 2)
            return Imagedata.playerMap.get(-3);
        if (toward == 3)
            return Imagedata.playerMap.get(-4);
        return null;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefend() {
        return defend;
    }

    public void setDefend(int defend) {
        this.defend = defend;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getYkey() {
        return Ykey;
    }

    public void setYkey(int ykey) {
        Ykey = ykey;
    }

    public int getBkey() {
        return Bkey;
    }

    public void setBkey(int bkey) {
        Bkey = bkey;
    }

    public int getRkey() {
        return Rkey;
    }

    public void setRkey(int rkey) {
        Rkey = rkey;
    }

    public int getToward() {
        return toward;
    }

    public void setToward(int toward) {
        this.toward = toward;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}