package me.jwhz.customenchant.manager;

import me.jwhz.customenchant.CustomEnchants;

public abstract class ManagerObject<I> {

    protected CustomEnchants core = CustomEnchants.getInstance();

    public abstract I getIdentifier();

}