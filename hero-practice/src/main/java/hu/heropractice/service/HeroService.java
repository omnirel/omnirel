package hu.heropractice.service;

import hu.heropractice.converter.HeroConverter;
import hu.heropractice.dto.HeroCreate;
import hu.heropractice.dto.HeroRead;
import hu.heropractice.dto.HeroUpdate;
import hu.heropractice.model.Hero;
import hu.heropractice.model.Weapon;
import hu.heropractice.repository.HeroRepository;
import hu.heropractice.repository.WeaponRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeroService {

    @Autowired
    private HeroRepository repository;

    @Autowired
    private WeaponRepository weaponRepository;

    public HeroRead getHero(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Hero not found");
        }
        return HeroConverter.convertModelToRead(repository.getReferenceById(id));
    }

    public HeroRead addHero(@Valid HeroCreate create) {
        Weapon weapon = weaponRepository.getReferenceById(create.getWeaponId());
        if (!weaponRepository.existsById(weapon.getId())){
            throw new RuntimeException("Weapon not found");
        }
        Hero createdHero = HeroConverter.convertCreateToModel(create, weapon);
        return HeroConverter.convertModelToRead(repository.save(createdHero));
    }

    public HeroRead updateHero(int id, @Valid HeroUpdate update) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Hero not found");
        }
        Hero updatingHero = repository.getReferenceById(id);
        if (update.getCanFly() != null){
            updatingHero.setCanFly(update.getCanFly());
        }
        if (update.getWeaponId() != null){
            Weapon newWeapon = weaponRepository.getReferenceById(update.getWeaponId());
            if (!weaponRepository.existsById(newWeapon.getId())){
                throw new RuntimeException("Weapon not found");
            }
            updatingHero.setWeapon(newWeapon);
        }
        Hero updatedHero = repository.save(updatingHero);
        return HeroConverter.convertModelToRead(updatedHero);
    }
}
