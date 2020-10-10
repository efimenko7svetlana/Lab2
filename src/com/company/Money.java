package com.company;

public class Money {
    public enum Measure {
        r,  // рубли
        d,  //доллар
        e,  //евро
    }

    ;

    private double value;
    private Measure type;

    /**
     * конструктор
     */
    public Money(double value, Measure type) {
        this.value = value;
        this.type = type;
    }

    /* переопределяем базовый метод toString */
    @Override
    public String toString() {

        String typeAsString = "";
        switch (this.type) {
            case r:
                typeAsString = "рублей";
                break;
            case d:
                typeAsString = "долларов";
                break;
            case e:
                typeAsString = "евро";
                break;
        }
        return String.format("%s %s", this.value, typeAsString);
    }

    /**
     * операция сложения числа
     */
    public Money add(double number) {
        Money newMoney = new Money(this.value + number, this.type);
        return newMoney;
    }

    /**
     * операция вычитания числа
     */
    public Money subtract(double number) {
        return new Money(this.value - number, this.type);
    }

    /**
     * операция умножения на число
     */
    public Money multiply(double number) {
        return new Money(this.value * number, this.type);
    }

    public Money to(Measure newType) {
        // по умолчанию новое значение совпадает со старым
        double newValue = this.value;

        // если текущий тип -- рубль
        if (this.type == Measure.r) {
            // в зависимости от того во что преобразовываем
            switch (newType) {
                case r: // если рубли
                    newValue = this.value;
                    break;
                case d: // если доллары
                    newValue = this.value / 77.75;
                    break;
                case e: // если евро
                    newValue = this.value / 91.27;
                    break;
            }
        } else if (newType == Measure.r) {
            // а это новый код, который добавляем
            // в зависимости от того во что преобразовываем
            switch (this.type) {
                case r: // если в метры, кстати это лишний пункт, он никогда не случится
                    newValue = this.value;
                    break;
                case d: // если километры
                    newValue = this.value * 77.75;
                    break;
                case e: // если мили
                    newValue = this.value * 91.27;
                    break;
            }
        }    else {
            // считаем новое значение, сначала перегоняем в метры
            // затем перегоняем в новый тип,
            // ну а затем выковыриваем значение
            newValue = this.to(Measure.r).to(newType).value;
        }
        return new Money(newValue, newType);
    }

    /**
     * операция сложения с другой длиной
     */
    public Money add(Money otherMoney) {
        // преобразуем в тип длины с которой складываем
        Money otherMoneyConverted = otherMoney.to(this.type);
        // а потом просто воспользуемся операцией сложением со значением (по сути просто числом)
        return this.add(otherMoneyConverted.value);
    }

    /**
     * операция вычитания с другой длиной
     */
    public Money subtract(Money otherMoney) {
        // преобразуем в тип длины от которой отнимаем
        Money otherMoneyConverted = otherMoney.to(this.type);
        // а потом просто воспользуемся операцией вычитания со значением (по сути просто числом)
        return this.subtract(otherMoneyConverted.value);
    }
    @Override
    public boolean equals(Object obj) {
        // так как equals позволяет сравниться с любым типом,
        // то принято добавлять проверку, является ли obj правильного типа
        // в нашем случае спрашиваем is obj instance of Money
        if (!(obj instanceof Money)) {
            // и если нет, возвращает false, то бишь объекты не равны
            return false;
        }

        // этот так называемый unboxing (распаковка),
        // в obj теоретически может лежать любой объект,
        // но так как мы условием выше фильтруем лишние классы,
        // получается что оказавшись на данной строке у нас в obj обязательно будет объект типа Length
        // и чтобы помочь компилятору принимать его за правильный тип
        // создаем дополнительную переменную которой присваиваем объект obj
        // распакованный через конструкцию (Length) в класс Length
        Money objMoney = (Money) obj;

        // ну а тут конвертим в тип this и сравниваем значения
        return objMoney.to(this.type).value == this.value;
    }
}
