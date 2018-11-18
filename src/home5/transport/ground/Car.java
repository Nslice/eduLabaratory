package home5.transport.ground;

import home5.transport.Vehicle;


public class Car extends Vehicle
{
    /**
     * Мощность двигателя, лошадиные силы (hp)
     */
    protected double power = -1;

    /**
     * Максимальный объем бака, литры (L)
     */
    protected double fuelTankCapacity = -1;

    /**
     * Расход на 100 км, литры (L)
     */
    protected double fuelConsumption = -1;

    /**
     * Количество топлива, литры (L)
     */
    protected double fuelLevel = 0.0;


    //------------------------------------------------------------------------


    /****************************************************
     *                КОНСТРУКТОРЫ
     ****************************************************/

    public Car(int ccode) throws CountryError
    {
        super(ccode);
    }

    public Car(int ccode, String model, double weight, double maxSpeed)
            throws CountryError
    {
        super(ccode, model, weight, maxSpeed);
    }

    public Car(Vehicle obj)
    {
        super(obj);
    }

    /**
     * Проверяет инициализирован объект полностью или нет.
     *
     * @return false - если не все поля инициализированы
     */
    @Override
    public boolean constructed()
    {
        return super.constructed() &&
                (power > 0 && fuelTankCapacity > 0 && fuelConsumption > 0);
    }

    /****************************************************
     *                МЕТОДЫ ДОСТУПА
     ****************************************************/

    public double getPower()
    {
        return power;
    }

    public double getFuelTankCapacity()
    {
        return fuelTankCapacity;
    }

    public double getFuelConsumption()
    {
        return fuelConsumption;
    }

    public double getFuelLevel()
    {
        return fuelLevel;
    }

    public void setPower(double power)
    {
        this.power = power;
    }

    public void setFuelTankCapacity(double fuelTankCapacity)
    {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public void setFuelConsumption(double fuelConsumption)
    {
        this.fuelConsumption = fuelConsumption;
    }

    public void setFuelLevel(double fuelLevel)
    {
        if (fuelLevel > fuelTankCapacity)
            this.fuelLevel = fuelTankCapacity;
        else
            this.fuelLevel = fuelLevel;
    }


    /****************************************************
     *                ДРУГИЕ МЕТОДЫ
     ****************************************************/

    /**
     * Заправить бак.
     *
     * @param liters кол-во литров
     * @return false, если не вместилось или объект недоинициализирован
     */
    public boolean fillTank(double liters)
    {
        if (!constructed())
            return false;
        if (fuelLevel + liters <= fuelTankCapacity)
        {
            fuelLevel += liters;
            return true;
        }
        else return false;
    }

    /**
     * Ехать.
     *
     * @param kilometres сколько надо ехать
     * @return километров проехано
     */
    public int drive(int kilometres)
    {
        if (!constructed())
            return 0;
        int resM = 0;
        final double litresOnKm = fuelConsumption / 100; //расход на 1 км

        while ((fuelLevel - litresOnKm) > 0 && kilometres > 0)
        {
            kilometres--;
            resM++;
            fuelLevel -= litresOnKm;
        }
        return resM;
    }

    /**
     * Выводит всю информацию.
     *
     * @return
     */
    @Override
    public String toString()
    {
        String str = "Country: " + country + "\n" +
                "Model: " + model + "\n" +
                "Weight: " + weight + " kg\n" +
                "Max.speed: " + maxSpeed + " km/h\n" +
                "Power: " + power + " hp\n" +
                "Fuel tank capacity: " + fuelTankCapacity + " L\n" +
                "Fuel consumption on 100 km: " + fuelConsumption + " L\n" +
                "Fuel level: " + fuelLevel + " L";
        return str;
    }

}