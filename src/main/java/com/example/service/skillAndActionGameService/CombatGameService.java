package com.example.service.skillAndActionGameService;

import com.example.model.skillAndActionGame.combatGame.CombatCharacter;

public interface CombatGameService {
    public void chooseCharacter(CombatCharacter combatCharacter);
    public void generateEnemy();
}
