package model;

/**
 * Created by Ксю on 07.05.2017.
 */
public class Mathemath
{
    public static double normalize(double x, double m, double d)
    {
        double x_max = 95;//25
        double x_min = 0;//0
        double y_max = 0.5;
        double y_min = 0;
        double a= x-x_min;
        double b = x_max-x_min;
        double c = y_max - y_min;
        return (a/b)*c + y_min;
    }

    public  static double normalize(double x)
    {
        return Mathemath.normalize(x, 10, 100);
    }

    public static double funActivator(double x)
    {
        double a = Math.pow(Math.E, x);
        double b = Math.pow(Math.E, -x);
        double x1 = a - b;
        double x2 = a + b;
        double y = x1/x2;
        return  y;
    }

    static double e = 1; // ошибка прогнозирования
    public static boolean condition(double y, double d)
    {
        double epsilon = 0.0001; //размер ошибки
        double err = 0.5 * Math.pow((y-d), 2); //целевая функция
        e += Math.pow((y-d), 2);
        if (err <= epsilon)
        {
            e = 0.5 * e;
            return true;
        }
        return false;
    }
}
