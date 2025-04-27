package org.lab3.wweblab3.entities;

import jakarta.persistence.*;
import org.json.simple.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Ну это дока для класса, тут у нас объект (Entity) представляющий точку в БД с соответствующими методами
 * (гетерами и сеттерами), а также конструкторами для получения точек как из БД, так и "вручную" при их добавлении.
 * Кроме этого в классе содержится методы для расчета результатов и сериализации в JSON.
 */
@Entity
@Table(name="POINTS")
public class Point implements Serializable {
    private static final long serialVersionUID = 11551;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Double x;
    private Double y;
    private Double r;
    private Boolean res;
    public Point()
    {

    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public Boolean getRes() {
        return res;
    }

    public void setRes(Boolean res) {
        this.res = res;
    }

    public Point(Double x, Double y, Double r, Boolean res) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.res = res;
    }

    public Point(Double x, Double y, Double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        calculateRes();
    }

    public void calculateRes() {
        if (x >= 0 && y >= 0) this.res = y <= (r - 2 * x);
        else if (x <= 0 && y <= 0) this.res = (r * r) / 4 >= x * x + y * y;
        else if (x <= 0 && y >= 0) this.res = (x >= -r) && (y <= r);
        else this.res = false;
    }

    /**
     * Это просто документация для проекта лол
     * @param data ArrayList объектов Point, который нужно сериализовать в JSON
     * @return JSON для этой фигни
     */
    public static String toJson(ArrayList<Point> data) {
        JSONObject write_arr = new JSONObject();
        for(int i = 0 ; i < data.size() ; i++)
        {
            JSONObject write_obj = new JSONObject();
            Point _obj = data.get(i);
            write_obj.put("x",_obj.x);
            write_obj.put("y",_obj.y);
            write_obj.put("r",_obj.r);
            write_obj.put("res",_obj.res);
            write_arr.put(i,write_obj);
        }
        var res_object = new JSONObject();
        res_object.put("data",write_arr);
        return res_object.toJSONString();
    }
}
