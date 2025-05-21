package hu.heropractice.converter;

import hu.heropractice.dto.HeroCreate;
import hu.heropractice.dto.HeroRead;
import hu.heropractice.model.Hero;
import hu.heropractice.model.Weapon;

public class HeroConverter {

    public static HeroRead convertModelToRead(Hero model) {
        HeroRead read = new HeroRead();
        read.setId( model.getId() );
        read.setName( model.getName() );
        read.setNationality( model.getNationality() );
        read.setCanFly( model.isCanFly() );
        read.setWeaponName(model.getWeapon().getType());
        return read;
    }

    public static Hero convertCreateToModel(HeroCreate create, Weapon weapon) {
        Hero hero = new Hero();
        hero.setName( create.getName() );
        hero.setNationality( create.getNationality() );
        hero.setCanFly( create.isCanFly() );
        hero.setWeapon(weapon);
        return hero;
    }
}
