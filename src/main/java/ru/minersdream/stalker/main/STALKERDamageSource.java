package ru.minersdream.stalker.main;

import net.minecraft.util.*;

public class STALKERDamageSource extends DamageSource
{
    public static final DamageSource FROM_CHEMANOMALIES = new DamageSource("fromChemAnomalies").setMagicDamage();
    public static final DamageSource FROM_THERMANOMALIES = new DamageSource("fromThermAnomalies").setMagicDamage();
    public static final DamageSource FROM_EXPLANOMALIES = new DamageSource("fromExplosionAnomalies").setMagicDamage();
    public static final DamageSource FROM_ELECANOMALIES = new DamageSource("fromElectricalAnomalies").setMagicDamage();
    public static final DamageSource FROM_BLOWOUT= new DamageSource("fromBlowout").setMagicDamage();
    public static final DamageSource FROM_PSIZONE= new DamageSource("fromPsiZone").setMagicDamage();
    public static final DamageSource FROM_RADZONE= new DamageSource("fromRadZone").setMagicDamage();
    public static final DamageSource FROM_CHEMZONE= new DamageSource("fromChemZone").setMagicDamage();
    public static final DamageSource FROM_THERMZONE= new DamageSource("fromThermZone").setMagicDamage();
    
    public STALKERDamageSource(final String damageTypeIn) {
        super(damageTypeIn);
    }

}
