import json
import requests

from bs4 import BeautifulSoup

SEREBII = "https://www.serebii.net/pokedex-sm/"
SEREBII_EXTENSION = ".shtml"

MOVE_DB_LOCATION = "moves/"

def scrape_pokemon_movelists():
    for i in range(1, 152):
        write_movelist_to_database(i)

def write_movelist_to_database(pokedex_number):
    movelist = parse_movelist_for_pokemon("%03d" % pokedex_number)
    with open(MOVE_DB_LOCATION + str(pokedex_number) + ".json", "w") as file:
        file.write(json.dumps(movelist))

def parse_movelist_for_pokemon(pokemon):
    soup = get_soup_for_pokemon(pokemon)
    learnset = get_learnset(soup)

    movelist = []

    for move in learnset:
        if is_move(move):
            movelist.append(scrape_move(move))

    return movelist

def get_soup_for_pokemon(pokemon):
    response = requests.get(SEREBII + pokemon + SEREBII_EXTENSION)
    soup = BeautifulSoup(response.content, "html.parser")

    return soup

def get_learnset(soup):
    tag = soup.find("a", {"name": "usmlevel"})
    if tag != None:
        return tag.parent.parent.parent

    return soup.find("li", {"title": "Sun/Moon/Ultra Sun/Ultra Moon"}).find("table", class_ = "dextable")

def is_move(move):
    try:
        tag = move.find_all("td")
        return len(tag) > 1
    except AttributeError:
        return False

def scrape_move(move):
    scraped_move = {}

    level = get_level(move)
    name = get_name(move)
    type = get_type(move)
    category = get_category(move)
    power = get_power(move)
    accuracy = get_accuracy(move)
    pp = get_pp(move)

    scraped_move["level"] = level
    scraped_move["name"] = name
    scraped_move["type"] = type
    scraped_move["category"] = category
    scraped_move["power"] = power
    scraped_move["accuracy"] = accuracy
    scraped_move["pp"] = pp

    return scraped_move

def get_level(move):
    level = move.find("td").text
    try:
        return int(level)
    except ValueError:
        return 1

def get_name(move):
    name = move.find_all("td")[1].text
    return name

def get_type(move):
    type = move.find_all("td")[2].find("img")["src"]
    return type[17:-4].upper()

def get_category(move):
    category = move.find_all("td")[3].find("img")["src"]
    formatted_category = category[17:-4]
    if formatted_category == "other":
        return "STATUS"

    return formatted_category.upper()

def get_power(move):
    power = move.find_all("td")[4].text
    try:
        return int(power)
    except ValueError:
        return 0

def get_accuracy(move):
    accuracy = move.find_all("td")[5].text
    try:
        return int(accuracy)
    except ValueError:
        return 100

def get_pp(move):
    pp = move.find_all("td")[6].text
    return int(pp)

if __name__ == "__main__":
    scrape_pokemon_movelists()
