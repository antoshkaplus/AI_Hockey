package com.antoshkaplus.model;

/**
 * Тип хоккеиста.
 * <p/>
 * В Раунде 1 чемпионата стратегия игрока управляет двумя хоккеистами-универсалами ({@code VERSATILE}).
 * <p/>
 * В Раунде 2 команда состоит из одного {@code VERSATILE}, одного {@code FORWARD} и одного {@code DEFENCEMAN}.
 * <p/>
 * В Групповом отборе и Финале у игрока в распоряжении имеется шесть хоккеистов типа {@code RANDOM},
 * однако одновременно в игре могут участвовать только трое из них. Остальные должны находиться на скамейке запасных.
 */
public enum HockeyistType {
    /**
     * Вратарь.
     * <p/>
     * Автоматически управляемый хоккеист. Самостоятельно перемещается вдоль вертикального отрезка,
     * расположенного перед воротами. Старается держаться на одной горизонтальной линии с шайбой.
     * Скорость вратаря ограничена значением {@code game.goalieMaxSpeed}.
     * <p/>
     * Вратарь единственный среди хоккеистов взаимодействует с шайбой напрямую (как физический объект).
     */
    GOALIE,

    /**
     * Хоккеист со сбалансированными параметрами.
     */
    VERSATILE,

    /**
     * Нападающий.
     * <p/>
     * Хоккеист, основным достоинством которого является сила удара.
     * Его слабой стороной является стойкость.
     */
    FORWARD,

    /**
     * Защитник.
     * <p/>
     * Хоккеист, основным достоинством которого является стойкость.
     * Его слабой стороной является точность удара.
     */
    DEFENCEMAN,

    /**
     * Хоккеист со случайными параметрами.
     */
    RANDOM
}
