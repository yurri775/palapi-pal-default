package com.example.palapi.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pals")
public class Pal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "key_code", unique = true)
    private String key;

    @Column(name = "image_path")
    private String image;

    @Column(name = "name")
    private String name;

    @Column(name = "wiki_url")
    private String wiki;

    @ElementCollection
    @CollectionTable(name = "pal_types", joinColumns = @JoinColumn(name = "pal_id"))
    @Column(name = "type")
    private List<String> types;

    @Column(name = "wiki_image_url")
    private String imageWiki;

    @ElementCollection
    @CollectionTable(name = "pal_suitabilities", joinColumns = @JoinColumn(name = "pal_id"))
    private List<Suitability> suitability;

    @ElementCollection
    @CollectionTable(name = "pal_drops", joinColumns = @JoinColumn(name = "pal_id"))
    @Column(name = "drop_item")
    private List<String> drops;

    @Embedded
    private Aura aura;

    @Column(name = "description", length = 1000)
    private String description;

    @ElementCollection
    @CollectionTable(name = "pal_skills", joinColumns = @JoinColumn(name = "pal_id"))
    private List<Skill> skills;

    @Embedded
    private PalStats stats;

    @Column(name = "asset_name")
    private String asset;

    @Column(name = "genus")
    private String genus;

    @Column(name = "rarity")
    private Integer rarity;

    @Column(name = "price")
    private Integer price;

    @Column(name = "size")
    private String size;

    @Embedded
    private PalMaps maps;

    // Nested classes for complex structures
    @Embeddable
    public static class Suitability {
        @Column(name = "work_type")
        private String type;

        @Column(name = "work_image")
        private String image;

        @Column(name = "work_level")
        private Integer level;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public Integer getLevel() {
			return level;
		}

		public void setLevel(Integer level) {
			this.level = level;
		}
        
        
    }

    @Embeddable
    public static class Aura {
        @Column(name = "aura_name")
        private String name;

        @Column(name = "aura_description", length = 1000)
        private String description;

        @Column(name = "tech")
        private String tech;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getTech() {
			return tech;
		}

		public void setTech(String tech) {
			this.tech = tech;
		}
        
    }

    @Embeddable
    public static class Skill {
        @Column(name = "skill_level")
        private Integer level;

        @Column(name = "skill_name")
        private String name;

        @Column(name = "skill_type")
        private String type;

        @Column(name = "cooldown")
        private Integer cooldown;

        @Column(name = "power")
        private Integer power;

        @Column(name = "skill_description", length = 1000)
        private String description;

		public Integer getLevel() {
			return level;
		}

		public void setLevel(Integer level) {
			this.level = level;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Integer getCooldown() {
			return cooldown;
		}

		public void setCooldown(Integer cooldown) {
			this.cooldown = cooldown;
		}

		public Integer getPower() {
			return power;
		}

		public void setPower(Integer power) {
			this.power = power;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
        
        
    }

    @Embeddable
    public static class PalStats {
        @Column(name = "hp")
        private Integer hp;

        @Embedded
        private AttackStats attack;

        @Column(name = "defense")
        private Integer defense;

        @Embedded
        private SpeedStats speed;

        @Column(name = "stamina")
        private Integer stamina;

        @Column(name = "support")
        private Integer support;

        @Column(name = "food")
        private Integer food;

        public Integer getHp() {
			return hp;
		}

		public void setHp(Integer hp) {
			this.hp = hp;
		}

		public AttackStats getAttack() {
			return attack;
		}

		public void setAttack(AttackStats attack) {
			this.attack = attack;
		}

		public Integer getDefense() {
			return defense;
		}

		public void setDefense(Integer defense) {
			this.defense = defense;
		}

		public SpeedStats getSpeed() {
			return speed;
		}

		public void setSpeed(SpeedStats speed) {
			this.speed = speed;
		}

		public Integer getStamina() {
			return stamina;
		}

		public void setStamina(Integer stamina) {
			this.stamina = stamina;
		}

		public Integer getSupport() {
			return support;
		}

		public void setSupport(Integer support) {
			this.support = support;
		}

		public Integer getFood() {
			return food;
		}

		public void setFood(Integer food) {
			this.food = food;
		}

		@Embeddable
        public static class AttackStats {
            @Column(name = "melee_attack")
            private Integer melee;

            @Column(name = "ranged_attack")
            private Integer ranged;

			public Integer getMelee() {
				return melee;
			}

			public void setMelee(Integer melee) {
				this.melee = melee;
			}

			public Integer getRanged() {
				return ranged;
			}

			public void setRanged(Integer ranged) {
				this.ranged = ranged;
			}
            
            
        }

        @Embeddable
        public static class SpeedStats {
            @Column(name = "ride_speed")
            private Integer ride;

            @Column(name = "run_speed")
            private Integer run;

            @Column(name = "walk_speed")
            private Integer walk;

			public Integer getRide() {
				return ride;
			}

			public void setRide(Integer ride) {
				this.ride = ride;
			}

			public Integer getRun() {
				return run;
			}

			public void setRun(Integer run) {
				this.run = run;
			}

			public Integer getWalk() {
				return walk;
			}

			public void setWalk(Integer walk) {
				this.walk = walk;
			}
            
        }
    }

    @Embeddable
    public static class PalMaps {
        @Column(name = "day_map")
        private String day;

        @Column(name = "night_map")
        private String night;

		public String getDay() {
			return day;
		}

		public void setDay(String day) {
			this.day = day;
		}

		public String getNight() {
			return night;
		}

		public void setNight(String night) {
			this.night = night;
		}
        
    }
    
    public boolean hasType(String type) {
        return this.types.contains(type);
    }

    // Getters and Setters (omitted for brevity, but should be implemented)
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWiki() {
		return wiki;
	}

	public void setWiki(String wiki) {
		this.wiki = wiki;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public String getImageWiki() {
		return imageWiki;
	}

	public void setImageWiki(String imageWiki) {
		this.imageWiki = imageWiki;
	}

	public List<Suitability> getSuitability() {
		return suitability;
	}

	public void setSuitability(List<Suitability> suitability) {
		this.suitability = suitability;
	}

	public List<String> getDrops() {
		return drops;
	}

	public void setDrops(List<String> drops) {
		this.drops = drops;
	}

	public Aura getAura() {
		return aura;
	}

	public void setAura(Aura aura) {
		this.aura = aura;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public PalStats getStats() {
		return stats;
	}

	public void setStats(PalStats stats) {
		this.stats = stats;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public String getGenus() {
		return genus;
	}

	public void setGenus(String genus) {
		this.genus = genus;
	}

	public Integer getRarity() {
		return rarity;
	}

	public void setRarity(Integer rarity) {
		this.rarity = rarity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public PalMaps getMaps() {
		return maps;
	}

	public void setMaps(PalMaps maps) {
		this.maps = maps;
	}
	
	@Override
	public String toString() {
		return "Pal [id=" + id + ", key=" + key + ", image=" + image + ", name=" + name + ", wiki=" + wiki + ", types="
				+ types + ", imageWiki=" + imageWiki + ", suitability=" + suitability + ", drops=" + drops + ", aura="
				+ aura + ", description=" + description + ", skills=" + skills + ", stats=" + stats + ", asset=" + asset
				+ ", genus=" + genus + ", rarity=" + rarity + ", price=" + price + ", size=" + size + ", maps=" + maps
				+ "]";
	}

}