package com.mygdx.game.Multiplayer;

/**
 * Created by tuskeb on 2017. 02. 22..
 */

public enum MessageTypes {

    /**
     * KLIENS: A kliens küld HELLO üzeneteket 5 másodpercenként. A szerver visszaküldi az online játékosok listáját. Ha 20 mp-ig nem küld valaki hello üzenetet, akkor offline lesz.
     */
    HELLO(10),

    /**
     * SZERVER: A szerver felhasználó listát küld.
     */
    LST(11),

    /**
     * SZERVER: A server nem küld felhasználó listát, mert a kapcsolat a HELLO üzenetek kimaradása miatt elveszett.
     */
    CONNECTIONLOST(12),

    /**
     * KLIENS: A kliens megtámad egy másik, online klienst.
     * SZERVER: A szerver közli a klienssel, hogy megtámadták.
     */
    ATTACK(20),

    /**
     * SZERVER: A harc elfogadva. Egyik fél sem harcolt eddig.
     */
    ATTACKACCEPT(21),

    /**
     * SZERVER: A harc elutasítva. Az egyik fél harcba lépett a felkérés és a hello üzenet közt.
     */
    ATTACKREFUSE(22),

    /**
     * KLIENS: A kliens offline állapotba álítja magát, azaz mégsem akar harcolni.
     */
    DISCONNECT(30),

    /**
     * SZERVER: Szétkapcsolódás sikeres.
     */
    DISCONNECTACK(31),

    /**
     * KLIENS: A kliens elküldi kapcsolódási kérelmét, saját adatait a támadáshoz és védekezéshez használt erőkkel.
     */
    CONNECT(40),

    /**
     * SZERVER: Kapcsolódás sikeres, adatokat a szerver feldolgozta.
     */
    CONNECTACK(41),

    /**
     * KLIENS: A kliens lekéri a módosult adatokat.
     */
    GETDATA(50),

    /**
     * SZERVER: A szerver az adatokat elküldte.
     */
    DATA(51),

    /**
     * SZERVER: Még nincs adat a harc kimeneteléről. (Időzítési hibák miatt lett beépítve.)
     */
    NODATAYET(52),


    /**
     * SZERVER: A szerver visszautasítja a kapcsolatot, jelszó hiba.
     */
    AUTHFAILED(60),

    /**
     * SZERVER: A szerver elfogadja a kapcsolatot. Minden adatküldés előtt ellenőrzi a hozzáférést.
     */
    AUTHACCEPT(61),

    /**
     * SZERVER: A szerveren a jelenlegi felhasználónév foglalt, és a jelszó nem egyezik. A felhasználónév nem használható a regisztráció lejáratáig.
     */
    AUTHUSERNAMEFAILED(62),

    /**
     * Az üzenet nem megfelelő.
     */
    ERROR(404),

    /**
     * Ismeretlen üzenet jött.
     */
    UNKNOWN(101010);

    private int value;

    private MessageTypes(int value) {
        this.value = value;
    }
}
