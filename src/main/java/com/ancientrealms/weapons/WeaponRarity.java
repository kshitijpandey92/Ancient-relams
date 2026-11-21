package com.ancientrealms.weapons;
public enum WeaponRarity {
 COMMON(1.0),UNCOMMON(1.1),RARE(1.25),EPIC(1.45),LEGENDARY(1.7),MYTHICAL(2.0),DIVINE(2.35),ANCIENT(2.7),ETERNAL(3.0);
 private final double damageMultiplier;
 WeaponRarity(double value){damageMultiplier=Math.min(3.0,value);}
 public double damageMultiplier(){return damageMultiplier;}
}
