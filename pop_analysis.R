pop_type <- c("aristocrats", "farmers", "bureaucrats", "clergymen", "laborers", "slaves", "soldiers", "peasants"
              , "clerks", "craftsmen", "officers", "artisans", "serfs")
great_britain <- c(33750, 786100, 34400, 46275, 0, 0, 95125, 0, 29450, 431450, 11100, 368550, 0 )
germany <- c(55165, 5088042, 16936, 62458, 0, 0, 127142, 0, 2980, 36075, 6035, 459132, 0)
united_states <- c(22701, 3022294, 8009, 32990, 0, 532225, 111043, 0, 1240, 57750, 3970, 309528, 0)

countries <- data.frame(pop_type, great_britain, germany, united_states)

View(countries)

#% in each country of all pops
percentage <- function(country) {
  round((country / sum(country) * 100), 2)
}

percentage(great_britain)

countries_percentage <- data.frame(pop_type, percentage(great_britain), percentage(germany), percentage(united_states))

View(countries_percentage)

#urban vs rural(farmers, serfs, slaves, aristocrats)
