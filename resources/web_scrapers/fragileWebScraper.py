#https://pokemondb.net/firered-leafgreen/exclusive
#Exclusivity is wrong, use this URL to scrape

import requests
import json
import re

location = "D:/"

pokemon = ['bulbasaur', 'ivysaur', 'venusaur', 'charmander', 'charmeleon', 'charizard', 'squirtle', 'wartortle', 'blastoise', 'caterpie', 'metapod', 'butterfree', 'weedle', 'kakuna', 'beedrill', 'pidgey', 'pidgeotto', 'pidgeot', 'rattata', 'raticate', 'spearow', 'fearow', 'ekans', 'arbok', 'pikachu', 'raichu', 'sandshrew', 'sandslash', 'nidoran-f', 'nidorina', 'nidoqueen', 'nidoran-m', 'nidorino', 'nidoking', 'clefairy', 'clefable', 'vulpix', 'ninetales', 'jigglypuff', 'wigglytuff', 'zubat', 'golbat', 'oddish', 'gloom', 'vileplume', 'paras', 'parasect', 'venonat', 'venomoth', 'diglett', 'dugtrio', 'meowth', 'persian', 'psyduck', 'golduck', 'mankey', 'primeape', 'growlithe', 'arcanine', 'poliwag', 'poliwhirl', 'poliwrath', 'abra', 'kadabra', 'alakazam', 'machop', 'machoke', 'machamp', 'bellsprout', 'weepinbell', 'victreebel', 'tentacool', 'tentacruel', 'geodude', 'graveler', 'golem', 'ponyta', 'rapidash', 'slowpoke', 'slowbro', 'magnemite', 'magneton', 'farfetchd', 'doduo', 'dodrio', 'seel', 'dewgong', 'grimer', 'muk', 'shellder', 'cloyster', 'gastly', 'haunter', 'gengar', 'onix', 'drowzee', 'hypno', 'krabby', 'kingler', 'voltorb', 'electrode', 'exeggcute', 'exeggutor', 'cubone', 'marowak', 'hitmonlee', 'hitmonchan', 'lickitung', 'koffing', 'weezing', 'rhyhorn', 'rhydon', 'chansey', 'tangela', 'kangaskhan', 'horsea', 'seadra', 'goldeen', 'seaking', 'staryu', 'starmie', 'mr-mime', 'scyther', 'jynx', 'electabuzz', 'magmar', 'pinsir', 'tauros', 'magikarp', 'gyarados', 'lapras', 'ditto', 'eevee', 'vaporeon', 'jolteon', 'flareon', 'porygon', 'omanyte', 'omastar', 'kabuto', 'kabutops', 'aerodactyl', 'snorlax', 'articuno', 'zapdos', 'moltres', 'dratini', 'dragonair', 'dragonite', 'mewtwo', 'mew']


def ripPokemonDB(nameOfPokemon):
	tmp = requests.get("https://pokemondb.net/pokedex/"+nameOfPokemon)
	gexH = re.compile(r"th>Height.{0,}m\)",re.IGNORECASE)
	gexW = re.compile(r"th>Weight</th>.{0,}kg",re.IGNORECASE)
	gexTs = re.compile(r"th>Type</th>.{0,}</td>",re.IGNORECASE)
	width = gexW.findall(tmp.text)
	height = gexH.findall(tmp.text)
	types = gexTs.findall(tmp.text)
	print(height)
	print(width)
	print(types)




#ripPokemonDB("bulbasaur")
#ripPokemonDB("pikachu")
#ripPokemonDB("mew")

def ripPokemonDB(nameOfPokemon):
	tmp = requests.get("https://pokemondb.net/pokedex/"+nameOfPokemon)
	gexH = re.compile(r"th>Height.{0,100}m",re.DOTALL)
	gexW = re.compile(r"th>Weight</th>.{0,100}kg",re.DOTALL)
	gexTs = re.compile(r"th>Type</th>.{0,300}Species",re.DOTALL)
	gexLoc = re.compile(r'span class="igame firered">FireRed.{0,10000}Emerald',re.DOTALL)
	gexDesc = re.compile(r'span class="igame firered">FireRed.{0,10000}LeafGreen',re.DOTALL)
	weight = gexW.findall(tmp.text)
	height = gexH.findall(tmp.text)
	types = gexTs.findall(tmp.text)
	
	locations = gexLoc.findall(str(tmp.text).split("Where to find")[1].split("Emerald")[0]+"Emerald")
	desc = gexDesc.findall(str(tmp.text).split("Pokédex entries")[-1].split("Where to find")[0])

	exclusivity = "BOTH"
	if "rade/migrate" in locations[0]:
		exclusivity = "LEAFGREEN"
	
	weight = weight[0].split("(")[-1].split("&")[0]
	height = height[0].split("(")[-1].split("&")[0]
	
	types = types[0].split("\n")[2]
	types = types.split("href=")
	for i in range(len(types)):
		types[i] = types[i].split("type-")[-1].split('"')[0]
		if "/" in types[i]:
			types[i] = ""
	
	types = types[:-1]
	
	#print(locations)
	
	sloc = []
	if "small" in locations[0] :
		sloc = locations[0].split("small")[1:]
		for i in range(len(sloc)):
			sloc[i] = sloc[i].split("<")[0].split(">")[1]
		sloc = sloc[:len(sloc)-1]
	
	locations = locations[0].split("href=")[1:]
	for i in range(len(locations)):
		locations[i] = locations[i][1:].split("<")[0].split(">")[1]
	
	for sl in sloc:
		locations.append(sl)
	
	
	desc = desc[0].split("cell-med-text")[1].split("<")[0][2:]
	
	#print(desc)
	#print(locations)
	#print(height)
	#print(weight)
	#print(types)
	
	saving = {}
	saving["type1"] = "NULL"
	saving["type2"] = "NULL"
	for i in range(len(types)):
		saving["type"+str(i+1)] = types[i]
	saving["weight"] = str(weight)
	saving["height"] = str(height)
	
	saving["name"] = nameOfPokemon
	saving["location"] = ",".join(locations)
	saving["summary"] = desc
	
	if len(locations) == 1 and exclusivity == "LEAFGREEN" and "rade/migrate" in locations[0]:
		exclusivity = "BOTH"
	elif len(locations) > 1 and exclusivity == "LEAFGREEN" and not "rade/migrate" in locations[-1]:
		exclusivity = "FIRERED"
	
	saving["exclusivity"] = exclusivity
	
	return saving
	
def savePokemonToJson(name,id):
	dat = ripPokemonDB(name)
	dat["id"] = str(id)
	file = open(location+name+".json","w")
	file.write(json.dumps(dat))
	file.close()
	

def scrapeDatabase():
	for i in range(0,len(pokemon)):
		savePokemonToJson(pokemon[i],i+1)

def writeList()
	file = open(location+"!listOfPokemon.txt","w")
	file.write("\n".join(pokemon))
	file.close()

scrapeDatabase()
