from random import randint


def get_valid_keyword():
    keywords = ["adidas", "nike"]
    return keywords[randint(0, len(keywords)-1)]


def get_random_score():
    return randint(1,4)


def get_gender_tab():
    return randint(1,3)
