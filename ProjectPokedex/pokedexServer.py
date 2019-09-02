import zmq
import json
from threading import Thread
import time

database = "database/"
databaseMoves = "database/moves/"
pokemonListFile = database+"!listOfPokemon.txt"

bootReq = "boot"
bootStart = "start"
bootEnd = "end"
jsonTrue = "TRUE"
jsonFalse = "FALSE"

createReq = "create"
reqData = "data"

successResp = "success"

moveset = "moveset"

polled = []

pokemonList = []
pokemon = []

addPOST = ""


def confirmPokemon(pokedict):

	global pokemon

	if pokedict == None or type(pokedict) != type({}):
		return False

	values = ["name","id","weight","height","location","summary","type1","exclusivity"]

	for value in values:
		if not value in pokedict or pokedict[value] == "":
			return False

	types = ['BUG', 'DARK', 'DRAGON', 'ELECTRIC', 'FAIRY', 'FIGHTING', 'FIRE', 'FLYING', 'GHOST', 'GRASS', 'GROUND', 'ICE', 'NORMAL', 'POISON', 'PSYCHIC', 'ROCK', 'STEEL', 'WATER' , 'NULL']
	exclusivity = ["FIRERED", "LEAFGREEN", "BOTH"]

	if not pokedict["exclusivity"] in exclusivity:
		return False

	if not pokedict["type1"] in types:
		return False
		
	if "type2" in pokedict and not pokedict["type2"] in types:
		return False
	
	for poke in pokemon:
		poke = json.loads(poke)
		if poke["id"] == pokedict["id"] or poke["name"] == pokedict["name"]:
			print("Error duplicate id/name found")
			return False
	
	return True
	
def writePokemonList(pokemonToAdd):
	global pokemonList
	
	#if type(pokemonToAdd) == type(""):
	#	pokemonToAdd = json.loads(pokemonToAdd)
	#	if not confirmPokemon(pokemonToAdd):
	#		print("Error string loader")
	#		return
	
	#elif type(pokemonToAdd) == type({}):
	#	if not confirmPokemon(pokemonToAdd):
	#		print("Error dict loader")
	#		return
	#	
	#else:
	#	return
	
	if type(pokemonToAdd) == type(""):
		pokemonToAdd = json.loads(pokemonToAdd)
	elif type(pokemonToAdd) == type({}):
		pass
	else:
		return
	
	pokemonList+="\n"
	pokemonList+=pokemonToAdd["name"]
	
	data = open(pokemonListFile,"w")
	data.write(pokemonList)
	data.close()
	
	data = open(database+pokemonToAdd["name"]+".json","w")
	data.write(json.dumps(pokemonToAdd))
	data.close()
	
	

def readPokemonIntoMemory():
	global pokemonList
	global pokemon
	
	data = open(pokemonListFile,"r")
	text = data.read()
	data.close()
	
	pokemon = text.replace("\r\n","\n\n").replace("\r","\n\n").replace("\n\n","\n")
	pokemonList = pokemon
	pokemon = pokemon.split("\n")
	
	loaded = []
	move = 1
	for i in range(len(pokemon)):
		try:
			pokemon[i] = pokemon[i].strip()
			data = open(database+pokemon[i]+".json","r")
			pokeJson = data.read()
			data.close()
			
			fix = json.loads(pokeJson)
			try:
				data = open(databaseMoves+str(move)+".json","r")
				moveJson = data.read()
				data.close()
				
				fix[moveset] = json.loads(moveJson)
				
			except:
				print("No external moveset found for: "+pokemon[move-1])
			
			loaded.append(json.dumps(fix))
			move+=1
		except:
			print("Error loading: "+pokemon[i])
	
	pokemon = loaded
	#return loaded
	

def reqServer():
	global addPOST
	global pokemon
	
	context = zmq.Context()
	socket = context.socket(zmq.REP)
	socket.bind("tcp://127.0.0.1:6660")
	
	readPokemonIntoMemory()
    
	while True:
		
		message = socket.recv()
		message = message.decode("ascii")
		
		ret = ""
		
		try:
			dict = json.loads(message)
			
			if bootReq in dict:
				ret = json.dumps(pokemon)
				print("Client Connected")
			elif createReq in dict:
				
				confirm = {}
				confirm[successResp] = jsonFalse
				
				print("Testing")
				if confirmPokemon(dict[reqData]):
					addPOST = str(json.dumps(dict[reqData]))
					confirm = {}
					confirm[successResp] = jsonTrue
					pokemon.append(addPOST)
					writePokemonList(dict[reqData])
				
				ret = json.dumps(confirm)
				
			else:
				print("No Valid Request")
			
		except Exception as error:
			print(error)
			print("JSON Read Error: "+str(message))
		
		try:
			socket.send_string(ret)
		except:
			print("Send Error")
		





def subServer():
	global addPOST
	
	context = zmq.Context.instance()
	publisher = context.socket(zmq.PUB)
	publisher.bind("tcp://127.0.0.1:6666")

	i = 0
	
	message = addPOST
	
	while True:
		
		if message != addPOST:
			message = addPOST
			print("PUBLISHING: " + str(message))
			try:
				publisher.send_string(str(message))
			except zmq.ZMQError as e:
				if e.errno == zmq.ETERM:
					break           # Interrupted
				else:
					raise e
		
		
		time.sleep(0.05)         # Wait for 1/50th second
		
	




def main():
	runReq = Thread(target=reqServer)
	runSub = Thread(target=subServer)
	runReq.start()
	runSub.start()
	
	print("Started Servers -- Ctrl + C to exit")

	while runReq.is_alive() or runSub.is_alive():
		time.sleep(1)

	return












if(__name__ == "__main__"):
    main()
